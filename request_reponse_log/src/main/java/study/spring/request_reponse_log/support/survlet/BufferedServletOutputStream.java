package study.spring.request_reponse_log.support.survlet;

import org.apache.commons.io.output.TeeOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.IOException;
import java.io.OutputStream;

public class BufferedServletOutputStream extends ServletOutputStream {

    private final TeeOutputStream targetStream;

    public BufferedServletOutputStream(OutputStream one, OutputStream two) {
        targetStream = new TeeOutputStream(one, two);
    }

    @Override
    public void write(int arg) throws IOException {
        this.targetStream.write(arg);
    }

    @Override
    public void write(byte[] buf, int off, int len) throws IOException {
        this.targetStream.write(buf, off, len);
    }

    @Override
    public void flush() throws IOException {
        super.flush();
        this.targetStream.flush();
    }

    @Override
    public void close() throws IOException {
        super.close();
        this.targetStream.close();
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {

    }
}