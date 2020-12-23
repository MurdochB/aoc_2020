package challenge.days;

import challenge.Solution;
import java.util.ArrayList;
import java.util.List;

public class D16 extends Solution {

  private static final String INPUT_FILE = "input-16.txt";
  private static final String PART_ONE = "Total invalid properties = %d";

  private List<Rule> rules;
  private String yourTicket;
  private List<String> nearby;

  private D16(String inputFile) {
    super(inputFile);
  }

  public static void main(String[] args) {
    D16 solution = new D16(INPUT_FILE);
    solution.run();
  }

  public void partOne() {
    System.out.println("# Part 1 #");
    parseInput();

    int totalInvalidProps = nearby.stream()
        .mapToInt(v -> getSumOfInvalidProperties(rules, v))
        .sum();
    System.out.println(String.format(PART_ONE, totalInvalidProps));
  }

  private Integer getSumOfInvalidProperties(List<Rule> rules, String s) {
    List<Integer> props = new ArrayList<>();
    String[] split = s.split(",");
    for (String item : split) {
      props.add(Integer.parseInt(item));
    }

    return props.stream()
        .filter(p -> isInvalid(rules, p))
        .mapToInt(Integer::intValue)
        .sum();

  }

  private boolean isInvalid(List<Rule> rules, Integer p) {
    for (Rule rule : rules) {
      if (rule.testValue(p)) {
        return false;
      }
    }
    return true;
  }

  public void partTwo() {
    System.out.println("# Part 2 #");

    // ...
  }

  private void parseInput() {
    rules = new ArrayList<>();
    yourTicket = "";
    nearby = new ArrayList<>();

    int phase = 1; // 1 rules, 2 your ticket, 3 nearby tickets
    for (String line : lines) {
      if (line.equals("your ticket:") || line.equals("nearby tickets:")) {
        phase++;
        continue;
      }
      switch (phase) {
        case 1:
          if (!line.equals("")) {
            rules.add(new Rule(line));
          }
          break;
        case 2:
          yourTicket = line;
          break;
        case 3:
          nearby.add(line);
          break;
      }
    }
  }

  class Rule {

    private int min1;
    private int max1;
    private int min2;
    private int max2;

    Rule(String rule) {
      String wholeRule = rule.split(": ")[1];
      String[] rules = wholeRule.split(" or ");
      String[] rule1 = rules[0].split("-");
      String[] rule2 = rules[1].split("-");
      min1 = Integer.parseInt(rule1[0]);
      max1 = Integer.parseInt(rule1[1]);
      min2 = Integer.parseInt(rule2[0]);
      max2 = Integer.parseInt(rule2[1]);
    }

    boolean testValue(int val) {
      return (val >= min1 && val <= max1) || (val >= min2 && val <= max2);
    }
  }

}
