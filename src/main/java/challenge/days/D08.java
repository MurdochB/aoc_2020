package challenge.days;

import challenge.Solution;
import java.util.HashSet;
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
    for (String l : lines) {
      if (isVerbJmpOrNop(l)) {
        int programVal = runProgram(true, curId);
        if (programVal != -1) {
          System.out.println(String.format(PART_TWO, programVal));
          break;
        }
      }
      curId++;
    }
  }

  private int runProgram(boolean requireContinuation, int flipInstructionAt) {
    boolean programWillContinue = false;
    int acc = 0;
    int curInstruction = 0;
    Set<Integer> visitedInstructions = new HashSet<>();

    while (isNewInstruction(visitedInstructions, curInstruction) &&
        !isNextInstructionOutOfBounds(curInstruction)) {
      visitedInstructions.add(curInstruction);
      String[] inst = lines.get(curInstruction).split(" ");

      String instVerb = inst[0];
      Integer instValue = Integer.parseInt(inst[1]);

      if (curInstruction == flipInstructionAt) {
        instVerb = flipVerb(instVerb);
      }
      switch (instVerb) {
        case "jmp":
          curInstruction += instValue;
          break;
        case "acc":
          acc += instValue;
          curInstruction += 1;
          break;
        case "nop":
          curInstruction += 1;
          break;
      }
      programWillContinue = isNextInstructionOutOfBounds(curInstruction);
    }
    if (!requireContinuation || programWillContinue) {
      return acc;
    } else {
      return -1;
    }
  }

  private boolean isVerbJmpOrNop(String instructionVerb) {
    return instructionVerb.contains("jmp") || instructionVerb.contains("nop");
  }

  private String flipVerb(String instructionVerb) {
    if ("jmp".equals(instructionVerb)) {
      return "nop";
    } else {
      return "jmp";
    }
  }

  private boolean isNextInstructionOutOfBounds(int instructionId) {
    return instructionId >= lines.size();
  }

  private boolean isNewInstruction(Set<Integer> visitedInstructions, int instructionId) {
    return !visitedInstructions.contains(instructionId);
  }
}
