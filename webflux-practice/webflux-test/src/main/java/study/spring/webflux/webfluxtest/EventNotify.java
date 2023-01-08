package study.spring.webflux.webfluxtest;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class EventNotify {

  private List<String> events = new ArrayList<>();

  private boolean change = false;

  public void add(String data) {
    this.events.add(data);
    this.change = true;
  }

  public boolean getChange() {
    return this.change;
  }

  public List<String> getEvents() {
    this.change = false;
    return this.events;
  }
}
