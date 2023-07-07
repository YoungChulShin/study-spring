package study.backend.java.httprequestresponse.config.log.model;

import com.google.common.collect.Iterables;
import com.google.common.collect.ObjectArrays;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ContentType;

public class BufferedRequestWrapper extends HttpServletRequestWrapper {

  private org.apache.commons.io.output.ByteArrayOutputStream outputStream;
  private Map<String, String[]> parameterMap;
  private static final String[] IP_HEADER_CANDIDATES = {
      "X-Forwarded-For",
      "Proxy-Client-IP",
      "WL-Proxy-Client-IP",
      "HTTP_X_FORWARDED_FOR",
      "HTTP_X_FORWARDED",
      "HTTP_X_CLUSTER_CLIENT_IP",
      "HTTP_CLIENT_IP",
      "HTTP_FORWARDED_FOR",
      "HTTP_FORWARDED",
      "HTTP_VIA",
      "REMOTE_ADDR"
  };

  public BufferedRequestWrapper(HttpServletRequest request) {
    super(request);
  }

  @Override
  public ServletInputStream getInputStream() throws IOException {
    if (outputStream == null) {
      cacheInputStream();
    }
    return new BufferedServletInputStream(outputStream.toByteArray());
  }

  @Override
  public BufferedReader getReader() throws IOException {
    return new BufferedReader(new InputStreamReader(getInputStream()));
  }

  @Override
  public String getParameter(String key) {
    String[] values = getParameterValues(key);
    return values != null && values.length > 0 ? values[0] : null;
  }

  @Override
  public String[] getParameterValues(String key) {
    return getParameterMap().get(key);
  }

  @Override
  public Map<String, String[]> getParameterMap() {
    if (parameterMap == null) {
      Map<String, String[]> result = new LinkedHashMap<>();
      decode(getQueryString(), result);
      decode(getPostBodyAsString(), result);
      parameterMap = Collections.unmodifiableMap(result);
    }
    return parameterMap;
  }

  @Override
  public String getRemoteAddr() {
    Optional<String> address = Arrays.stream(IP_HEADER_CANDIDATES).filter(header -> {
      String tmp = getHeader(header);
      return StringUtils.isNotEmpty(tmp) && !StringUtils.equalsIgnoreCase("unknown", tmp);
    }).findFirst();

    if (!address.isPresent()) {
      return super.getRemoteAddr();
    }

    return StringUtils.substringBefore(getHeader(address.get()), ",");
  }

  @Override
  public String getCharacterEncoding() {
    String enc = super.getCharacterEncoding();
    return enc != null ? enc : CharEncoding.UTF_8;
  }

  private Charset getCharacterSet() {
    return Charset.forName(getCharacterEncoding());
  }

  private String getPostBodyAsString() {
    try {
      if (outputStream == null) {
        cacheInputStream();
      }
      return IOUtils.toString(getInputStream(), getCharacterEncoding());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private void toMap(Iterable<NameValuePair> inputParams, Map<String, String[]> toMap) {
    for (NameValuePair e : inputParams) {
      String key = e.getName();
      String value = e.getValue();
      if (toMap.containsKey(key)) {
        String[] newValue = ObjectArrays.concat(toMap.get(key), value);
        toMap.put(key, newValue);
      } else {
        toMap.put(key, new String[]{value});
      }
    }
  }

  private void cacheInputStream() throws IOException {
    // Cache the inputStream in order to read it multiple times. For convenience, I use apache.commons IOUtils
    outputStream = new ByteArrayOutputStream();
    IOUtils.copy(super.getInputStream(), outputStream);
  }

  private void decode(String queryString, Map<String, String[]> result) {
    if (queryString != null)
      toMap(decodeParams(queryString), result);
  }

  private Iterable<NameValuePair> decodeParams(String body) {
    Iterable<NameValuePair> params = URLEncodedUtils.parse(body, getCharacterSet());
    try {
      String cts = getContentType();
      if (cts != null) {
        ContentType ct = ContentType.parse(cts);
        if (ct.getMimeType().equals(ContentType.APPLICATION_FORM_URLENCODED.getMimeType())) {
          List<NameValuePair> postParams = URLEncodedUtils.parse(IOUtils.toString(getReader()), getCharacterSet());
          params = Iterables.concat(params, postParams);
        }
      }
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
    return params;
  }
}
