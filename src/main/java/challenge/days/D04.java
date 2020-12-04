package challenge.days;

import challenge.Solution;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    String wholeInput = lines.get(0);
    String[] split = wholeInput.split(",");
    List<String> passportDetails = Arrays.asList(split);
    Long validPassports = passportDetails.stream()
        .map(Passport::new)
        .filter(Passport::isValidPt2)
        .count();
    // 185 too high
    System.out.println(validPassports);
  }

  class Passport {
    private final String HEX_WEBCOLOR_PATTERN = "^#([a-fA-F0-9]{6})$";

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

    private boolean isValidPt2() {
      List<String> neededProps = Arrays.asList("ecl", "pid", "eyr", "hcl", "byr", "iyr", "hgt");
      for (String needed : neededProps) {
        if (!deets.containsKey(needed)) {
          return false;
        }
      }
      int byr = Integer.parseInt(deets.get("byr"));
      if (byr < 1920 || byr > 2002)
        return false;

      int iyr = Integer.parseInt(deets.get("iyr"));
      if (iyr < 2010 || iyr > 2020)
        return false;

      int eyr = Integer.parseInt(deets.get("eyr"));
      if (eyr < 2020 || eyr > 2030)
        return false;

      String hgt = deets.get("hgt");

      String[] hgtAndType = hgt.split("cm|in");

      if(hgt.contains("cm")){
        int size = Integer.parseInt(hgtAndType[0]);
        if (size < 150 || size > 193)
          return false;
      } else if (hgt.contains("in")) {
        int size = Integer.parseInt(hgtAndType[0]);
        if (size < 59 || size > 76)
          return false;
      } else {
        return false;
      }

      String hcl = deets.get("hcl");
      if(!isValidHex(hcl))
        return false;

      String ecl = deets.get("ecl");
      List<String> validEcls = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
      if(!validEcls.contains(ecl))
        return false;

      String pid = deets.get("pid");
      if (pid.length() != 9)
        return false;

      return true;
    }

    private final Pattern pattern = Pattern.compile(HEX_WEBCOLOR_PATTERN);
    private boolean isValidHex(final String colorCode) {
      Matcher matcher = pattern.matcher(colorCode);
      return matcher.matches();
    }
  }


}
