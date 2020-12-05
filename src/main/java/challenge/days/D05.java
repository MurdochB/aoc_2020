package challenge.days;

import challenge.Solution;
import java.util.Arrays;
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

    int curSeat = seatIds.get(0); // start on lowest seat id
    for (int seat : seatIds) {
      if (seat != curSeat) {
        break;
      }
      curSeat++;
    }
    System.out.println(String.format(PART_TWO, curSeat));
  }

  private int getSeatNumber(String boardingCard) {
    String row = boardingCard.substring(0, 7);
    String col = boardingCard.substring(7, 10);
    int rowTotal = 0;
    int colTotal = 0;
    for (int i = 0; i < row.length(); i++) {
      if (isBack(row.charAt(i))) {
        rowTotal += getRowValue(i);
      }
    }
    for (int i = 0; i < col.length(); i++) {
      if (isRight(col.charAt(i))) {
        colTotal += getColValue(i);
      }
    }
    return (rowTotal * 8) + colTotal;
  }

  private boolean isBack(char c) {
    return c == 'B';
  }

  private boolean isRight(char c) {
    return c == 'R';
  }

  private int getRowValue(int i) {
    List<Integer> rowValues = Arrays.asList(64, 32, 16, 8, 4, 2, 1);
    return rowValues.get(i);
  }

  private int getColValue(int i) {
    List<Integer> colValues = Arrays.asList(4, 2, 1);
    return colValues.get(i);
  }
}
