package challenge.days;

import challenge.Solution;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class D14 extends Solution {

  private static final String INPUT_FILE = "input-14.txt";
  private static final String PART_ONE = "Sum of all memory = %d";

  private D14(String inputFile) {
    super(inputFile);
  }

  public static void main(String[] args) {
    D14 solution = new D14(INPUT_FILE);
    solution.run();
  }

  public void partOne() {
    System.out.println("# Part 1 #");

    String mask = "";
    Map<String, Long> mem = new HashMap<>();

    for (String line : lines) {
      if (line.startsWith("mask = ")) {
        // update mask
        mask = line.split("mask = ")[1];
      } else {
        // add to memory
        mem.put(getMemLocation(line), getValue(mask, line));
      }
    }

    long sum = mem.entrySet()
        .stream()
        .mapToLong(Entry::getValue)
        .sum();

    System.out.println(String.format(PART_ONE, sum));
  }

  private String getMemLocation(String line) {
    return line.split("mem\\[")[1].split("] = ")[0];
  }

  private Long getValue(String mask, String line) {
    Long baseValue = Long.parseLong(line.split("] = ")[1]);
    Binary bin = new Binary(mask, baseValue);

    return bin.getLongValue();
  }

  class Binary {

    private Map<Integer, Long> values;

    Binary(String mask, Long l) {
      setBaseValues(l);
      applyMask(mask);
    }

    private void setBaseValues(Long l) {
      values = new HashMap<>();
      int index = 0;
      while (l != 0L) {
        if (l % 2L != 0) {
          values.put(index, 1L);
        }
        ++index;
        l = l >>> 1;
      }
    }

    private void applyMask(String mask) {
      StringBuilder sb = new StringBuilder(mask);
      char[] charMask = sb.reverse().toString().toCharArray();
      for (int i = 0; i < charMask.length; i++) {
        if (charMask[i] != 'X') {
          values.put(i, Long.parseLong(String.valueOf(charMask[i])));
        }
      }
    }

    public Long getLongValue() {
      long value = 0L;
      for (Entry<Integer, Long> entry : values.entrySet()) {
        if (entry.getValue() == 1L){
          value += Math.pow(2L, entry.getKey());
        }
      }
      return value;
    }
  }

  public void partTwo() {
    System.out.println("# Part 2 #");

    // ...
  }
}
