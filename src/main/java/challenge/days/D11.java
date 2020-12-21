package challenge.days;

import challenge.Solution;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javafx.util.Pair;

public class D11 extends Solution {

  private static final String INPUT_FILE = "input-11.txt";
  private static final String PART_ONE = "Total seated: %d";

  private D11(String inputFile) {
    super(inputFile);
  }

  public static void main(String[] args) {
    D11 solution = new D11(INPUT_FILE);
    solution.run();
  }

  public void partOne() {
    System.out.println("# Part 1 #");

    List<List<String>> seats = lines.stream()
        .map(a -> Arrays.asList(a.split("")))
        .collect(Collectors.toList());

    long previousSeated = 0L;
    long currentSeated = -1L;
    while (currentSeated != previousSeated) {
      previousSeated = currentSeated;
      seats = runCycle(seats);
      currentSeated = countOccupied(seats);
    }

    System.out.println(String.format(PART_ONE, currentSeated));
  }


  private List<List<String>> runCycle(List<List<String>> seats) {
    List<List<String>> newSeats = new ArrayList<>();
    int ySize = seats.get(0).size();
    for (int x = 0; x < seats.size(); x++) {
      List<String> newRow = new ArrayList<>();
      for (int y = 0; y < ySize; y++) {
        String cur = seats.get(x).get(y);
        switch (cur) {
          case "L":
            if (getSurroundingOccupied(seats, x, y) == 0) {
              newRow.add("#");
            } else {
              newRow.add("L");
            }
            break;
          case "#":
            if (getSurroundingOccupied(seats, x, y) >= 4) {
              newRow.add("L");
            } else {
              newRow.add("#");
            }
            break;
          case ".":
            newRow.add(".");
            break;
        }
      }
      newSeats.add(newRow);
    }
    return newSeats;
  }

  private int getSurroundingOccupied(List<List<String>> obj, int x, int y) {
    Pair<Integer, Integer> n = new Pair<>(x - 1, y);
    Pair<Integer, Integer> ne = new Pair<>(x - 1, y + 1);
    Pair<Integer, Integer> e = new Pair<>(x, y + 1);
    Pair<Integer, Integer> se = new Pair<>(x + 1, y + 1);
    Pair<Integer, Integer> s = new Pair<>(x + 1, y);
    Pair<Integer, Integer> sw = new Pair<>(x + 1, y - 1);
    Pair<Integer, Integer> w = new Pair<>(x, y - 1);
    Pair<Integer, Integer> nw = new Pair<>(x - 1, y - 1);

    List<Pair<Integer, Integer>> surrounding = Arrays.asList(n, ne, e, se, s, sw, w, nw);

    int surroundingCount = 0;
    for (Pair<Integer, Integer> pair : surrounding) {
      surroundingCount += checkCoOrdsAreOccupied(obj, pair);
    }

    return surroundingCount;
  }

  private int checkCoOrdsAreOccupied(List<List<String>> obj, Pair<Integer, Integer> pair) {
    int x = pair.getKey();
    int y = pair.getValue();
    List<String> list = obj.get(0);
    if ((x >= 0 && x < obj.size()) && (y >= 0 && y < list.size()) &&
        isOccupied(obj, x, y)) {
      return 1;
    }
    return 0;
  }

  private boolean isOccupied(List<List<String>> obj, int x, int y) {
    return obj.get(x).get(y).equals("#");
  }

  private long countOccupied(List<List<String>> seats) {
    return seats.stream()
        .mapToLong(this::countOccupiedInList).sum();
  }

  private long countOccupiedInList(List<String> list) {
    return list.stream()
        .filter(this::isOccupiedChar).count();
  }

  private boolean isOccupiedChar(String s) {
    return s.equals("#");
  }

  public void partTwo() {
    System.out.println("# Part 2 #");

    // ...
  }
}
