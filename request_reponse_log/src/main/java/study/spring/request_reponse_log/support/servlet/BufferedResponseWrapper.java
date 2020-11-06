package study.spring.request_reponse_log.support.servlet;


import org.apache.commons.codec.CharEncoding;
import org.springframework.util.FastByteArrayOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class BufferedResponseWrapper extends HttpServletResponseWrapper {

    private FastByteArrayOutputStream content;
    private BufferedServletOutputStream outputStream;
    private PrintWriter writer;
    private int statusCode = 200;
    private Integer contentLength;

    public BufferedResponseWrapper(HttpServletResponse response) throws IOException {
        super(response);
        content = new FastByteArrayOutputStream(1024);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (outputStream == null) {
            outputStream = new BufferedServletOutputStream(super.getOutputStream(), content);
        }
        return outputStream;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (writer == null) {
            writer = new PrintWriter(new OutputStreamWriter(content, this.getCharacterEncoding()), true);
        }

        return writer;
    }

    @Override
    public void flushBuffer() throws IOException {
        if (writer != null) {
            writer.flush();
        } else if (outputStream != null) {
            outputStream.flush();
        }
    }

    @Override
    public void setContentLength(int len) {
        if (len > this.content.size()) {
            this.content.resize(len);
        }

        this.contentLength = len;
    }

    @Override
    public String getCharacterEncoding() {
        String enc = super.getCharacterEncoding();
        return enc != null ? enc : CharEncoding.UTF_8;
    }

    @Override
    public void sendError(int sc) throws IOException {

        try {
            super.sendError(sc);
        } catch (IllegalStateException var3) {
            super.setStatus(sc);
        }

        this.statusCode = sc;
    }

    @Override
    public void setStatus(int sc) {
        super.setStatus(sc);
        this.statusCode = sc;
    }

    @Override
    public int getStatus() {
        return statusCode;
    }

    @Override
    public ServletResponse getResponse() {
        return this;
    }

    @Override
    public void setContentLengthLong(long len) {
        if (len > Integer.MAX_VALUE) {
            throw new IllegalArgumentException(String.format("Content-Length exceeds ContentCachingResponseWrapper's maximum (%d): %d", Integer.MAX_VALUE, len));
        } else {
            int lenInt = (int) len;
            if (lenInt > this.content.size()) {
                this.content.resize(lenInt);
            }

            this.contentLength = lenInt;
        }
    }

    @Override
    public void setBufferSize(int size) {
        if (size > this.content.size()) {
            this.content.resize(size);
        }
    }

    @Override
    public void resetBuffer() {
        this.content.reset();
    }

    @Override
    public void reset() {
        super.reset();
        this.content.reset();
    }

    public byte[] getContentAsByteArray() {
        return this.content.toByteArray();
    }

    public InputStream getContentInputStream() {
        return this.content.getInputStream();
    }

    public int getContentSize() {
        return this.content.size();
    }

    public void copyBodyToResponse() throws IOException {
        this.copyBodyToResponse(true);
    }

    protected void copyBodyToResponse(boolean complete) throws IOException {
        if (this.content.size() > 0) {
            HttpServletResponse rawResponse = (HttpServletResponse) super.getResponse();
            if ((complete || this.contentLength != null) && !rawResponse.isCommitted()) {
                rawResponse.setContentLength(complete ? this.content.size() : this.contentLength);
                this.contentLength = null;
            }

            this.content.writeTo(rawResponse.getOutputStream());
            this.content.reset();
            if (complete) {
                super.flushBuffer();
            }
        }
    }
}