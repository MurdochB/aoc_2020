package challenge.days;

import challenge.Solution;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.util.Pair;

public class D01 extends Solution {

  private static final String INPUT_FILE = "input-1.txt";

  private static final String PART_ONE = "%s x %s = %s\n";
  private static final String PART_TWO = "%s x %s x %s = %s";

  private D01(String inputFile) {
    super(inputFile);
  }

  public static void main(String[] args) {
    D01 solution = new D01(INPUT_FILE);
    solution.run();
  }

  public void partOne() {
    System.out.println("# Part 1 #");

    List<Integer> intLines = lines.stream()
        .map(Integer::parseInt)
        .collect(Collectors.toList());

    getTwo(intLines, 2020)
        .ifPresent(p -> System.out.println(String.format(
            PART_ONE, p.getKey(), p.getValue(), (p.getKey() * p.getValue()))));

  }

  public void partTwo() {
    System.out.println("# Part 2 #");

    List<Integer> intLines = lines.stream()
        .map(Integer::parseInt)
        .collect(Collectors.toList());

    for (Integer v : intLines) {
      int goal = 2020 - v;
      getTwo(intLines, goal)
          .ifPresent(p -> System.out.println(String.format(
              PART_TWO, v, p.getKey(), p.getValue(), (v * p.getKey() * p.getValue()))));
    }
  }

  private Optional<Pair<Integer, Integer>> getTwo(List<Integer> input, int total) {
    for (Integer i : input) {
      int goal = (total - i);
      if (input.contains(goal)) {
        // TODO fix the bug where this can use the same number twice
        return Optional.of(new Pair<>(i, goal));
      }
    }
    return Optional.empty();
  }
}
