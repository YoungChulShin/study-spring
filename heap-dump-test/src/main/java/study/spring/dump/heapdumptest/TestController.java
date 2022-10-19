package study.spring.dump.heapdumptest;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @PostMapping("/heap-memory-test")
  public String heapMemoryTest() {
    List<TestData> testDataList = new ArrayList<>();
    for (int i = 0; i < 100000000; i++) {
      testDataList.add(new TestData("test " + i, i));
    }

    return "done";
  }

  private class TestData {
    private String testName;
    private int testNumber;

    public TestData(String testName, int testNumber) {
      this.testName = testName;
      this.testNumber = testNumber;
    }
  }
}