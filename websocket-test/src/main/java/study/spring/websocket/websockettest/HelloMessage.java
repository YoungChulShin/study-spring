package study.spring.websocket.websockettest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HelloMessage {

  private String name;
  private String channelId;

  public boolean isValid() {
    return name != null
        && !name.isEmpty()
        && channelId != null
        && !channelId.isEmpty();
  }
}
