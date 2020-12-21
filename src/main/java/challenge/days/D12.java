package challenge.days;

import challenge.Solution;

public class D12 extends Solution {

  private static final String INPUT_FILE = "input-12.txt";
  private static final String PART_ONE = "Manhattan distance to starting point: %d";

  private D12(String inputFile) {
    super(inputFile);
  }

  public static void main(String[] args) {
    D12 solution = new D12(INPUT_FILE);
    solution.run();
  }

  private long pos_north = 0;
  private long pos_east = 0;
  private int facing = 2;

  public void partOne() {
    System.out.println("# Part 1 #");
    lines.forEach(this::readInst);

    System.out.println(String.format(PART_ONE, Math.abs(pos_north) + Math.abs(pos_east)));
  }

  private void readInst(String instruction) {
    String inst = instruction.split("")[0];
    int val = Integer.parseInt(instruction.substring(1));
    switch (inst) {
      case "N":
        pos_north += val;
        break;
      case "E":
        pos_east += val;
        break;
      case "S":
        pos_north -= val;
        break;
      case "W":
        pos_east -= val;
        break;
      case "F":
        switch (facing) {
          case 1:
            pos_north += val;
            break;
          case 2:
            pos_east += val;
            break;
          case 3:
            pos_north -= val;
            break;
          case 4:
            pos_east -= val;
            break;
        }
        break;
      case "L":
        val = val / 90;
        facing -= val;
        facing = correctFacing(facing);
        break;
      case "R":
        val = val / 90;
        facing += val;
        facing = correctFacing(facing);
        break;
    }

  }

  private int correctFacing(int facing) {
    if (facing < 1) {
      facing += 4;
    } else if (facing > 4) {
      facing -= 4;
    }
    return facing;
  }

  public void partTwo() {
    System.out.println("# Part 2 #");

    // ...
  }
}
