package challenge.days;

import challenge.Solution;
import java.util.List;
import java.util.stream.Collectors;

public class D05 extends Solution {

  private static final String INPUT_FILE = "input-5.txt";
  private static final String PART_ONE = "highest seat is: %d\n";
  private static final String PART_TWO = "my seat is: %d";

  private D05(String inputFile) {
    super(inputFile);
  }

  public static void main(String[] args) {
    D05 solution = new D05(INPUT_FILE);
    solution.run();
  }

  public void partOne() {
    System.out.println("# Part 1 #");

    Integer highestSeat = lines.stream()
        .mapToInt(this::getSeatNumber)
        .max()
        .orElse(-1);

    System.out.println(String.format(PART_ONE, highestSeat));
  }

  public void partTwo() {
    System.out.println("# Part 2 #");

    List<Integer> seatIds = lines.stream()
        .map(this::getSeatNumber)
        .sorted()
        .collect(Collectors.toList());

    int curSeat = seatIds.get(0);
    for (int seat : seatIds) {
      if (seat != curSeat) {
        break;
      }
      curSeat++;
    }
    System.out.println(String.format(PART_TWO, curSeat));
  }

  private int getSeatNumber(String boardingCard) {
    String col = boardingCard.substring(0, 7);
    String row = boardingCard.substring(7, 10);
    int colTotal = 0;
    int rowTotal = 0;
    for (int i = 0; i < col.length(); i++) {
      if (isBack(col.charAt(i))) {
        colTotal += getAddedVal(i);
      }
    }
    for (int i = 0; i < row.length(); i++) {
      if (isRight(row.charAt(i))) {
        rowTotal += getAddedValRow(i);
      }

    }

    return (colTotal * 8) + rowTotal;
  }

  private boolean isBack(char c) {
    return c == 'B';
  }

  private boolean isRight(char c) {
    return c == 'R';
  }

  private int getAddedVal(int i) {
    if (i == 0) {
      return 64;
    }
    if (i == 1) {
      return 32;
    }
    if (i == 2) {
      return 16;
    }
    if (i == 3) {
      return 8;
    }
    if (i == 4) {
      return 4;
    }
    if (i == 5) {
      return 2;
    }
    if (i == 6) {
      return 1;
    }
    return -1;
  }

  private int getAddedValRow(int i) {
    if (i == 0) {
      return 4;
    }
    if (i == 1) {
      return 2;
    }
    if (i == 2) {
      return 1;
    }

    return -1;
  }
}
