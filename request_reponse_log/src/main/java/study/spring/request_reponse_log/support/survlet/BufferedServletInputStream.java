package study.spring.request_reponse_log.support.survlet;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.ByteArrayInputStream;

public class BufferedServletInputStream extends ServletInputStream {

    private ByteArrayInputStream inputStream;

    public BufferedServletInputStream(byte[] contents) {
        // create a new input stream from the cached request body
        this.inputStream = new ByteArrayInputStream(contents);
    }

    @Override
    public int available() {
        return this.inputStream.available();
    }

    @Override
    public int read() {
        return this.inputStream.read();
    }

    @Override
    public int read(byte[] buf, int off, int len) {
        return this.inputStream.read(buf, off, len);
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
    public void setReadListener(ReadListener readListener) {

    }
}