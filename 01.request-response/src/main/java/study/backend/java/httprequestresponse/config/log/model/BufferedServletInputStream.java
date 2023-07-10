package study.backend.java.httprequestresponse.config.log.model;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import lombok.Getter;

@Getter
public class BufferedServletInputStream extends ServletInputStream {

  private ByteArrayInputStream inputStream;

  public BufferedServletInputStream(byte[] contents) {
    this.inputStream = new ByteArrayInputStream(contents);
  }

  @Override
  public int available() throws IOException {
    return this.inputStream.available();
  }

  @Override
  public boolean isFinished() {
    return this.inputStream.available() == 0;
  }

  @Override
  public boolean isReady() {
    return true;
  }

  @Override
  public int read() throws IOException {
    return this.inputStream.read();
  }

  @Override
  public void setReadListener(ReadListener listener) {

  }
}
