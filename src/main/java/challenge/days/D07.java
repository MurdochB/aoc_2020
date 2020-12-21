package challenge.days;

import challenge.Solution;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class D07 extends Solution {

  private static final String INPUT_FILE = "input-7.txt";

  private D07(String inputFile) {
    super(inputFile);
  }

  public static void main(String[] args) {
    D07 solution = new D07(INPUT_FILE);
    solution.run();
  }

  public void partOne() {
    System.out.println("# Part 1 #");

    List<Bag> bags = lines.stream().sorted()
        .map(Bag::new)
        .collect(Collectors.toList());

    Set<String> wantedBags = new HashSet<>();
    wantedBags.add("shiny gold");

    int previousSize = 0;
    while (wantedBags.size() > previousSize) {
      previousSize = wantedBags.size();
      for (Bag bag : bags) {
        if (bag.hasAny(wantedBags)) {
          wantedBags.add(bag.color);
        }
      }
    }
    System.out.println(wantedBags.size() - 1);

  }

  public void partTwo() {
    System.out.println("# Part 2 #");

    List<Bag> bags = lines.stream().sorted()
        .map(Bag::new)
        .collect(Collectors.toList());

    int bagCount = 0;
    Bag one = findBagWithColor(bags, "shiny gold");
    // go through all the contain and keep track of how many times ...

    // System.out.println(test);



    // ...
  }

  private Bag findBagWithColor(List<Bag> bags, String color){
    for (Bag bag : bags) {
      if(bag.color.equals(color))
        return bag;
    }
    return null;
  }

  class Bag {

    String color;
    Map<String, Integer> contains;

    public Bag(String line) {
      String[] split = line.split(" bags contain ");
      this.color = split[0];
      contains = new HashMap<>();
      String[] otherBags = split[1].replace(".", "").split(", ");
      for (String b : otherBags) {
        b = b.replace(" bags", "");
        b = b.replace(" bag", "");
        if (!b.equals("no other")) {
          String bagName = b.substring(1);
          int count = Integer.parseInt(b.substring(0, 1));
          contains.put(bagName.trim(), count);
        }
      }
    }

    public boolean hasAny(Set<String> bagname) {
      for (String b : bagname) {
        if (contains.containsKey(b)) {
          return true;
        }
      }
      return false;
    }

    public String toString() {
      return color + "[" + contains + "]";
    }
  }
}
