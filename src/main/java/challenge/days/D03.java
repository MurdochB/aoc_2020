package challenge.days;

import challenge.Solution;

public class D03 extends Solution {

  private static final String INPUT_FILE = "input-3.txt";

  private D03(String inputFile) {
    super(inputFile);
  }

  public static void main(String[] args) {
    D03 solution = new D03(INPUT_FILE);
    solution.run();
  }

  public void partOne() {
//    System.out.println("# Part 1 #");
//    int hitTrees = 0;
//
//    int cur_x = 0;
//    int xDelt = 3;
//
//    for(String l : lines){
//      System.out.println("Working on : [" + l + "] x=" + cur_x + " length=" + l.length());
//      String[] split = l.split("");
//      if(isTree(split[cur_x])){
//        hitTrees++;
//      }
//      cur_x += xDelt;
//      if (cur_x >= l.length()){
//        cur_x -= l.length();
//      }
//    }
//    System.out.println(hitTrees);
//

  }

  public void partTwo() {
    System.out.println("# Part 2 #");
    int hitTrees = 0;

    int cur_x = 0;
    int cur_y = 1;
    int xDelt = 1;
    int yDelt = 2;

    for(String l : lines){
      if (cur_y % yDelt == 0){
        System.out.println("skipping");
        cur_y++;
        continue;
      }
      System.out.println("Working on : [" + l + "] x=" + cur_x + " length=" + l.length());
      String[] split = l.split("");
      if(isTree(split[cur_x])){
        hitTrees++;
      }
      cur_y += 1;
      cur_x += xDelt;
      if (cur_x >= l.length()){
        cur_x -= l.length();
      }
    }
    System.out.println(hitTrees);


    // Right 1, down 1 (86)
    // Right 3, down 1. (159)
    // Right 5, down 1. (97)
    // Right 7, down 1. (88)
    // Right 1, down 2. (55)
    // 86 * 159 * 97 * 88 * 55 =

  }

  private boolean isSafe(String c) {
    return ".".equals(c);
  }

  private boolean isTree(String c) {
    return "#".equals(c);
  }
}
