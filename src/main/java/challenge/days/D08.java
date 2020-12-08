package challenge.days;

import challenge.Solution;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class D08 extends Solution {

  private static final String INPUT_FILE = "input-8.txt";

  private static final String PART_ONE = "acc when loop begins: %d";
  private static final String PART_TWO = "acc when loop continues: %d";

  private D08(String inputFile) {
    super(inputFile);
  }

  public static void main(String[] args) {
    D08 solution = new D08(INPUT_FILE);
    solution.run();
  }


  public void partOne() {
    System.out.println("# Part 1 #");

    int acc = runProgram(false, -1);
    System.out.println(String.format(PART_ONE, acc));
  }

  public void partTwo() {
    System.out.println("# Part 2 #");

    int curId = 0;
    List<Integer> flipList = new ArrayList<>();
    for (String l: lines){
      if(l.contains("jmp") || l.contains("nop"))
        flipList.add(curId);
      curId++;
    }

    for (Integer i : flipList) {
      int programVal = runProgram(true, i);
      if (programVal != -1){
        System.out.println(programVal);
      }
    }
  }

  private int runProgram(boolean requireContinuation, int flipInstructionAt){
    boolean programWillContinue = false;
    int acc = 0;
    int curInstruction = 0;
    Set<Integer> visitedInstructions = new HashSet<>();

    while (isNewInstruction(visitedInstructions, curInstruction) && !isNextInstructionOutOfBounds(curInstruction)) {
      visitedInstructions.add(curInstruction);
      String[] inst = lines.get(curInstruction).split(" ");

      String instructionVerb = inst[0];
      Integer instructionValue = Integer.parseInt(inst[1]);

      if (curInstruction == flipInstructionAt) {
        instructionVerb = flipVerb(instructionVerb);
      }
      switch (instructionVerb) {
        case "jmp":
          curInstruction += instructionValue;
          break;
        case "acc":
          acc += instructionValue;
          curInstruction += 1;
          break;
        case "nop":
          curInstruction += 1;
          break;
      }
      programWillContinue = isNextInstructionOutOfBounds(curInstruction);
    }
    if(!requireContinuation || programWillContinue){
      return acc;
    }
    else
      return -1;
  }

  private String flipVerb(String instructionVerb) {
    if ("jmp".equals(instructionVerb)) {
      return  "nop";
    } else {
      return "jmp";
    }
  }
  private boolean isNextInstructionOutOfBounds(int instructionId){
    return instructionId >= lines.size();
  }
  private boolean isNewInstruction(Set<Integer> visitedInstructions, int instructionId) {
    return !visitedInstructions.contains(instructionId);
  }




}
