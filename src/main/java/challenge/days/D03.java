package challenge.days;

import challenge.Solution;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

public class D03 extends Solution {

  private static final String INPUT_FILE = "input-3.txt";

  private static final String PART_ONE = "Trees hit: %d\n";
  private static final String PART_TWO = "Trees hit multiplied: %d\n";

  private D03(String inputFile) {
    super(inputFile);
  }

  public static void main(String[] args) {
    D03 solution = new D03(INPUT_FILE);
    solution.run();
  }

  public void partOne() {
    System.out.println("# Part 1 #");
    int hitTrees = runWithAngle(new Pair<>(3, 1));
    System.out.println(String.format(PART_ONE, hitTrees));
  }

  public void partTwo() {
    System.out.println("# Part 2 #");
    List<Pair<Integer, Integer>> angles = new ArrayList<>();
    angles.add(new Pair<>(1, 1));
    angles.add(new Pair<>(3, 1));
    angles.add(new Pair<>(5, 1));
    angles.add(new Pair<>(7, 1));
    angles.add(new Pair<>(1, 2));

    long hitTreesPerAngle = angles.stream()
        .map(this::runWithAngle)
        .map(BigInteger::valueOf)
        .reduce(BigInteger.ONE, BigInteger::multiply)
        .longValue();

    System.out.println(String.format(PART_TWO, hitTreesPerAngle));
  }

  private int runWithAngle(Pair<Integer, Integer> angle) {
    int hitTrees = 0;
    int curX = 0;
    int curY = 1;
    int deltaX = angle.getKey();
    int deltaY = angle.getValue();

    for (String l : lines) {
      curY++;
      if (curY >= deltaY) {
        curY = 0;
        String[] split = l.split("");
        if (isTree(split[curX])) {
          hitTrees++;
        }
        curX += deltaX;
        if (curX >= l.length()) {
          curX -= l.length();
        }
      }
    }
    return hitTrees;
  }

  private boolean isTree(String c) {
    return "#".equals(c);
  }
}
