package challenge;

import java.util.List;
import utils.FileUtil;

public abstract class Solution {

  private static final String FINISH_MSG = "Part %s took: %f seconds.";
  private static final float NANOS = 1000 * 1000 * 1000;

  protected List<String> lines;

  public Solution(String inputFile) {
    lines = FileUtil.readLines(inputFile);
  }

  public void run() {
    long startTime = System.nanoTime();

    partOne();
    long partOneEndTime = System.nanoTime();

    partTwo();
    long partTwoEndTime = System.nanoTime();

    System.out.println();
    System.out.println();
    System.out.println(String.format(FINISH_MSG, "one", (partOneEndTime - startTime) / NANOS));
    System.out.println(String.format(FINISH_MSG, "two", (partTwoEndTime - partOneEndTime) / NANOS));
    System.out.println(String.format(FINISH_MSG, "total", (partTwoEndTime - startTime) / NANOS));
    System.out.println();
    System.out.println();
  }

  public abstract void partOne();

  public abstract void partTwo();
}