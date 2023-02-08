package study.spring.dump.heapdumptest;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MemoryTestService {

  public void test() {
    List<TestData> testDataList = new ArrayList<>();
    for (int i = 0; i < 100000000; i++) {
      testDataList.add(new TestData("test " + i, i));
    }
  }

  private static class TestData {
    private String testName;
    private int testNumber;

    public TestData(String testName, int testNumber) {
      this.testName = testName;
      this.testNumber = testNumber;
    }
  }

}
