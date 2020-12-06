package challenge.days;

import challenge.Solution;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import utils.FileUtil;

public class D06 extends Solution {

  private static final String INPUT_FILE = "input-6.txt";
  private static final String PART_ONE = "Any yes: %d\n";

  String wholeInput = FileUtil.readWholeFile(INPUT_FILE);

  private D06(String inputFile) {
    super(inputFile);
  }

  public static void main(String[] args) {
    D06 solution = new D06(INPUT_FILE);
    solution.run();
  }

  public void partOne() {
    System.out.println("# Part 1 #");
    String[] groups = wholeInput.split("\n\n");
    int totalAnyAnswers = Arrays.stream(groups)
        .mapToInt(this::getUniqueCount)
        .sum();

    System.out.println(String.format(PART_ONE, totalAnyAnswers));

  }

  private int getUniqueCount(String input) {
    Set<String> set = new HashSet<>();
    for (char c : input.toCharArray()) {
      if (c != '\n') {
        set.add(String.valueOf(c));
      }
    }
    return set.size();
  }

  public void partTwo() {
    System.out.println("# Part 2 #");

    // ...
  }
}
