package study.spring.request_reponse_log.support.servlet;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.ByteArrayInputStream;

public class BufferedServletInputStream extends ServletInputStream {

    private ByteArrayInputStream inputStream;

    public BufferedServletInputStream(byte[] contents) {
        this.inputStream = new ByteArrayInputStream(contents);
    }

    @Override
    public int available() {
        return inputStream.available();
    }

    @Override
    public int read() {
        return inputStream.read();
    }

    @Override
    public int read(byte[] b, int off, int len) {
        return inputStream.read(b, off, len);
    }

    @Override
    public boolean isFinished() {
        return inputStream.available() == 0;
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void setReadListener(ReadListener listener) {

    }
}
