package challenge.days;

import challenge.Solution;
import java.util.List;
import java.util.stream.Collectors;

public class D10 extends Solution {

  private static final String INPUT_FILE = "input-10.txt";

  private D10(String inputFile) {
    super(inputFile);
  }

  public static void main(String[] args) {
    D10 solution = new D10(INPUT_FILE);
    solution.run();
  }

  public void partOne() {
    System.out.println("# Part 1 #");

    List<Integer> adapters = lines.stream()
        .mapToInt(Integer::parseInt)
        .sorted()
        .boxed()
        .collect(Collectors.toList());

    int previous = 0;
    int difOne = 0;
    int difThree = 1; // Add one +3 for the highest rated adapter
    for (Integer adapter : adapters) {
      int dif = adapter - previous;
      if (dif == 1) {
        difOne += 1;
      }
      if (dif == 3) {
        difThree += 1;
      }
      previous = adapter;
    }

    System.out.println(difOne * difThree);
  }

  public void partTwo() {
    System.out.println("# Part 2 #");

    List<Integer> adapters = lines.stream()
        .mapToInt(Integer::parseInt)
        .sorted()
        .boxed()
        .collect(Collectors.toList());

    adapters.forEach(System.out::println);
  }
}
