package challenge.days;

import challenge.Solution;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class D15 extends Solution {

  private static final String INPUT_FILE = "input-15.txt";
  private static final String PART_ONE = "2020th number spoken = %d";
  private static final String PART_TWO = "30000000th number spoken = %d";

  private D15(String inputFile) {
    super(inputFile);
  }

  public static void main(String[] args) {
    D15 solution = new D15(INPUT_FILE);
    solution.run();
  }

  public void partOne() {
    System.out.println("# Part 1 #");

    List<Integer> game = Arrays.stream(lines.get(0).split(","))
        .map(Integer::parseInt)
        .collect(Collectors.toList());

    while (game.size() < 2020) {
      Integer last = game.get(game.size() - 1);
      List<Integer> subList = game.subList(0, game.size() - 1);
      int lastAppeared = subList.lastIndexOf(last);
      if (lastAppeared == -1) {
        // never appeared
        game.add(0);
      } else {
        game.add(game.size() - 1 - lastAppeared);
      }
    }
    System.out.println(String.format(PART_ONE, game.get(game.size() - 1)));
  }

  public void partTwo() {
    System.out.println("# Part 2 #");

    List<Long> starting = Arrays.stream(lines.get(0).split(","))
        .map(Long::parseLong)
        .collect(Collectors.toList());

    HashMap<Long, Long> game = new HashMap<>();
    int i = 0;
    for (; i < starting.size(); i++) {
      game.put(starting.get(i), (long) i);
    }

    long last = starting.get(i - 1);
    for (; i < 30000000 - 1; i++) {
      Long prev = game.get(last);
      game.put(last, (long) i);
      last = prev == null ? 0 : i - prev;
    }

    System.out.println(String.format(PART_TWO, last));
  }
}
