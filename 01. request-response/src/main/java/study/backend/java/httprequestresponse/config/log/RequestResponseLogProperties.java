package study.backend.java.httprequestresponse.config.log;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RequestResponseLogProperties {

  @Value("${interceptor.request-response-log.exclude-methods}")
  private String excludeMethods;

  @Value("${interceptor.request-response-log.exclude-patterns}")
  private String excludePatterns;

  @Value("${interceptor.request-response-log.exclude-headers}")
  private String excludeHeaders;

  @Value("${interceptor.request-response-log.exclude-status}")
  private String excludeStatus;

  private String[] excludeMethodArray;

  private String[] excludePatternArray;

  private String[] excludeStatusArray;

  private Set<String> excludeHeaderSet;

  public String[] getExcludeMethodArray() {
    if (excludeMethodArray == null) {
      this.excludeMethodArray = getSplitArray(excludeMethods);
    }
    return this.excludeMethodArray;
  }

  public String[] getExcludePatternArray() {
    if (excludePatternArray == null) {
      this.excludePatternArray = getSplitArray(excludePatterns);
    }
    return this.excludePatternArray;
  }

  public String[] getExcludeStatusArray() {
    if (excludeStatusArray == null) {
      this.excludeStatusArray = getSplitArray(excludeStatus);
    }
    return this.excludeStatusArray;
  }

  public Set<String> getExcludeHeaderSet() {
    if (this.excludeHeaderSet == null) {
      Set<String> tempSet = new HashSet<>();
      Arrays.stream(getSplitArray(excludeHeaders)).map(String::toLowerCase).forEach(tempSet::add);
      this.excludeHeaderSet = tempSet;
    }
    return this.excludeHeaderSet;
  }

  public boolean isExcludeMethods(final String method) {
    return Arrays.stream(getExcludeMethodArray()).anyMatch(
        excludeMethod -> StringUtils.equalsIgnoreCase(excludeMethod, method)
    );
  }

  public boolean isExcludeStatus(final int status) {
    return Arrays.stream(getExcludeStatusArray()).anyMatch(
        excludeStatus -> excludeStatus.equals(String.valueOf(status))
    );
  }

  private String[] getSplitArray(String csv) {
    return csv.split("\\s?,\\s?");
  }
}
