package challenge.days;

import challenge.Solution;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class D04 extends Solution {

  private static final String INPUT_FILE = "input-4-modified.txt";

  private D04(String inputFile) {
    super(inputFile);
  }

  public static void main(String[] args) {
    D04 solution = new D04(INPUT_FILE);
    solution.run();
  }

  public void partOne() {
    System.out.println("# Part 1 #");

    String wholeInput = lines.get(0);
    String[] split = wholeInput.split(",");
    List<String> passportDetails = Arrays.asList(split);
    Long validPassports = passportDetails.stream()
        .map(Passport::new)
        .filter(Passport::isValid)
        .count();

    System.out.println(validPassports);

  }

  public void partTwo() {
    System.out.println("# Part 2 #");

    // ...
  }

  class Passport {

    private HashMap<String, String> deets = new HashMap<>();

    Passport(String details) {
      String[] props = details.split(" ");
      for (String p : props) {
        String[] keyVal = p.split(":");
        deets.put(keyVal[0], keyVal[1]);
      }
    }

    private boolean isValid() {
      List<String> neededProps = Arrays.asList("ecl", "pid", "eyr", "hcl", "byr", "iyr", "hgt");
      for (String needed : neededProps) {
        if (!deets.containsKey(needed)) {
          return false;
        }
      }
      return true;
    }


  }


}
