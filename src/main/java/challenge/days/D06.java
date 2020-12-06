package challenge.days;

import challenge.Solution;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import utils.FileUtil;

public class D06 extends Solution {

  private static final String INPUT_FILE = "input-6.txt";
  private static final String PART_ONE = "Count for any yes: %d\n";
  private static final String PART_TWO = "Count for all yes: %d\n";

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
        .mapToInt(this::getAnswerCountForGroup)
        .sum();

    System.out.println(String.format(PART_ONE, totalAnyAnswers));
  }

  public void partTwo() {
    System.out.println("# Part 2 #");

    String[] groups = wholeInput.split("\n\n");
    int totalAllAnswers = Arrays.stream(groups)
        .mapToInt(this::getNumAnswersCommonAcrossGroup)
        .sum();

    System.out.println(String.format(PART_TWO, totalAllAnswers));
  }

  private int getNumAnswersCommonAcrossGroup(String input) {
    String[] eachPerson = input.split("\n");
    List<Set<String>> eachSet = Arrays
        .stream(eachPerson)
        .map(this::createSetForInput)
        .collect(Collectors.toList());

    Set<String> all = new HashSet<>(eachSet.get(0));
    eachSet.forEach(all::retainAll);
    return all.size();
  }

  private int getAnswerCountForGroup(String input) {
    Set<String> set = createSetForInput(input);
    return set.size();
  }

  private Set<String> createSetForInput(String input) {
    Set<String> set = new HashSet<>();
    for (char c : input.toCharArray()) {
      if (c != '\n') {
        set.add(String.valueOf(c));
      }
    }
    return set;
  }
}
