package challenge.days;

import challenge.Solution;
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
    // Input file was collapsed into one line, separated with commas
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
    System.out.println(validPassports);
  }

  class Passport {

    private static final String HEX_COLOUR_PATTERN = "^#([a-fA-F0-9]{6})$";

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

      return validateByr(Integer.parseInt(deets.get("byr"))) &&
          validateIyr(Integer.parseInt(deets.get("iyr"))) &&
          validateEyr(Integer.parseInt(deets.get("eyr"))) &&
          validateHgt(deets.get("hgt")) &&
          validateHcl(deets.get("hcl")) &&
          validateEcl(deets.get("ecl")) &&
          validatePid(deets.get("pid"));
    }

    private boolean validateByr(int byr) {
      return byr >= 1920 && byr <= 2002;
    }

    private boolean validateIyr(int iyr) {
      return iyr >= 2010 && iyr <= 2020;
    }

    private boolean validateEyr(int eyr) {
      return eyr >= 2020 && eyr <= 2030;
    }

    private boolean validateHgt(String hgt) {
      String[] hgtAndType = hgt.split("cm|in");
      if (hgt.contains("cm")) {
        int size = Integer.parseInt(hgtAndType[0]);
        return size >= 150 && size <= 193;
      } else if (hgt.contains("in")) {
        int size = Integer.parseInt(hgtAndType[0]);
        return size >= 59 && size <= 76;
      } else {
        return false;
      }
    }

    private boolean validateHcl(String hcl) {
      return isValidHex(hcl);
    }

    private boolean validateEcl(String ecl) {
      List<String> validEcls = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
      return validEcls.contains(ecl);
    }

    private boolean validatePid(String pid) {
      return pid.length() == 9;
    }

    private final Pattern pattern = Pattern.compile(HEX_COLOUR_PATTERN);

    private boolean isValidHex(final String colorCode) {
      Matcher matcher = pattern.matcher(colorCode);
      return matcher.matches();
    }
  }
}
