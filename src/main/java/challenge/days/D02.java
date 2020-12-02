package challenge.days;

import challenge.Solution;

public class D02 extends Solution {

  private static final String INPUT_FILE = "input-2.txt";

  private static final String PART_ONE = "Number of valid passwords: %d\n";
  private static final String PART_TWO = "Number of valid passwords: %d\n";

  private D02(String inputFile) {
    super(inputFile);
  }

  public static void main(String[] args) {
    D02 solution = new D02(INPUT_FILE);
    solution.run();
  }

  public void partOne() {
    System.out.println("# Part 1 #");

    long validPasswords = lines.stream()
        .map(Password::new)
        .filter(Password::rule1Validity)
        .count();

    System.out.println(String.format(PART_ONE, validPasswords));
  }

  public void partTwo() {
    System.out.println("# Part 2 #");

    long validPasswords = lines.stream()
        .map(Password::new)
        .filter(Password::rule2Validity)
        .count();

    System.out.println(String.format(PART_TWO, validPasswords));
  }

  class Password {

    private int min;
    private int max;
    private String letter;
    private String password;

    Password(String policyAndPass) {
      final String[] policyPassword = policyAndPass.split(": ");
      final String[] minMaxLetter = policyPassword[0].split(" ");
      final String[] minMax = minMaxLetter[0].split("-");
      letter = minMaxLetter[1];
      min = Integer.parseInt(minMax[0]);
      max = Integer.parseInt(minMax[1]);
      password = policyPassword[1];
    }

    private boolean rule1Validity() {
      final long count = password.chars()
          .filter(ch -> ch == letter.toCharArray()[0])
          .count();
      return (count >= min) && (count <= max);
    }

    private boolean rule2Validity() {
      boolean first = password.substring(min - 1, min).equals(letter);
      boolean second = password.substring(max - 1, max).equals(letter);
      return first ^ second;
    }
  }
}
