package challenge.days;

import challenge.Solution;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import javafx.util.Pair;

public class D13 extends Solution {

  private static final String INPUT_FILE = "input-13.txt";
  private static final String PART_ONE = "Earliest bus * wait time = %d";

  private D13(String inputFile) {
    super(inputFile);
  }

  public static void main(String[] args) {
    D13 solution = new D13(INPUT_FILE);
    solution.run();
  }

  public void partOne() {
    System.out.println("# Part 1 #");

    Integer earliestDepartTime = Integer.parseInt(lines.get(0));
    List<String> busses = Arrays.asList(lines.get(1).split(","));

    busses.stream()
        .filter(b -> !"x".equals(b))
        .map(b -> calculateWaitTime(earliestDepartTime, Integer.parseInt(b)))
        .min(Comparator.comparing(Pair::getKey))
        .map(p -> p.getKey() * p.getValue())
        .ifPresent(result -> System.out.println(String.format(PART_ONE, result)));
  }

  private Pair<Integer, Integer> calculateWaitTime(Integer earliestDepartTime, Integer timetable) {
    return new Pair<>(timetable - ((earliestDepartTime) % timetable), timetable);
  }

  public void partTwo() {
    System.out.println("# Part 2 #");

    // ...
  }
}
