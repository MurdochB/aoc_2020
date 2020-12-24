package challenge.days;

import challenge.Solution;
import java.util.ArrayList;
import java.util.List;

public class D17 extends Solution {

  private static final String INPUT_FILE = "input-17.txt";
  private static final String PART_ONE = "Sum of all homework = %d";

  private D17(String inputFile) {
    super(inputFile);
  }

  public static void main(String[] args) {
    D17 solution = new D17(INPUT_FILE);
    solution.run();
  }

  public void partOne() {
    System.out.println("# Part 1 #");

    long sum = lines.stream()
        .map(s -> s.replace(" ", ""))
        .mapToLong(this::solve)
        .sum();
    System.out.println(String.format(PART_ONE, sum));
  }

  private long solve(String s) {
    // get parens and solve them
    List<String> expr = new ArrayList<>();
    char[] charArray = s.toCharArray();

    for (int i = 0; i < charArray.length; i++) {
      char c = charArray[i];
      if (c == '(') {
        // separate and evaluate
      }
      expr.add(String.valueOf(c));
    }

    long total = 0;
    String op = "+";
    for (String e : expr) {
      if (e.equals("*") || e.equals("+")) {
        op = e;
      } else if (e.equals("(") || e.equals(")")) {
        // do nothing
      } else {
        if ("+".equals(op)) {
          total += Integer.valueOf(e);
        } else if ("*".equals(op)) {
          total *= Integer.valueOf(e);
        }
      }
    }

    return total;
  }

  public void partTwo() {
    System.out.println("# Part 2 #");

    // ...
  }
}
