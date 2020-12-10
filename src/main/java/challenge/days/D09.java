package challenge.days;

import challenge.Solution;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class D09 extends Solution {

  private static final String INPUT_FILE = "input-9.txt";

  private static final String PART_ONE = "Odd one out: %d";

  private int indexOfOddOneOut = -1;

  private D09(String inputFile) {
    super(inputFile);
  }

  public static void main(String[] args) {
    D09 solution = new D09(INPUT_FILE);
    solution.run();
  }

  public void partOne() {
    System.out.println("# Part 1 #");
    int preambleLength = 25;
    BigInteger oddOneOut = null;

    List<Long> mappedLines = lines.stream()
        .map(Long::new)
        .collect(Collectors.toList());

    for (int i = 0; i < mappedLines.size(); i++) {
      if (i < preambleLength) {
        // no checks
        continue;
      }
      if (!doTwoValuesAddUp(mappedLines.subList(i - preambleLength, i), mappedLines.get(i))) {
        indexOfOddOneOut = i;
        oddOneOut = new BigInteger(lines.get(i));
      }
    }
    System.out.println(String.format(PART_ONE, oddOneOut));

  }

  public void partTwo() {
    System.out.println("# Part 2 #");

    long goal = Long.parseLong(lines.get(indexOfOddOneOut));

    List<Long> mappedLines = lines.stream()
        .map(Long::new)
        .collect(Collectors.toList());

    for (int startingPoint = 0; startingPoint < indexOfOddOneOut; startingPoint++) {
      long sum = 0;
      List<Long> theseLongs = new ArrayList<>();
      for (int test = startingPoint; test <= indexOfOddOneOut; test++){
        theseLongs.add(mappedLines.get(test));
        sum += mappedLines.get(test);
        if (sum == goal){
          List<Long> sortedList = theseLongs.stream().sorted().collect(Collectors.toList());
          long min = sortedList.get(0);
          long max = sortedList.get(sortedList.size() - 1);
          System.out.println(min + max);
        }
      }
    }


  }

  private boolean doTwoValuesAddUp(List<Long> nums, Long goal) {
    for (int i = 0; i < nums.size(); i++) {
      Long first = nums.get(i);
      for (int j = 0; j < nums.size(); j++) {
        if (i != j) {
          Long second = nums.get(j);
          if (first + second == goal) {
            return true;
          }
        }
      }
    }
    return false;
  }
}
