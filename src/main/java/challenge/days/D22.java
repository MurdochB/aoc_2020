package challenge.days;

import challenge.Solution;
import java.util.ArrayList;
import java.util.List;

public class D22 extends Solution {

  private static final String INPUT_FILE = "input-22.txt";

  private D22(String inputFile) {
    super(inputFile);
  }

  public static void main(String[] args) {
    D22 solution = new D22(INPUT_FILE);
    solution.run();
  }

  public void partOne() {
    System.out.println("# Part 1 #");

    List<String> player1 = new ArrayList<>();
    List<String> player2 = new ArrayList<>();

    boolean switchDeck = false;
    for (String line : lines) {
      if (line.equals("Player 1:") || line.equals("")) {
        continue;
      }
      if (line.equals("Player 2:")) {
        switchDeck = true;
        continue;
      }
      if (switchDeck) {
        player2.add(line);
      } else {
        player1.add(line);
      }
    }

    while (!(player1.isEmpty() || player2.isEmpty())) {
      playGame(player1, player2);
      System.out.println("we played");
    }

    System.out.println("Player 1: " + player1);
    System.out.println("Player 2: " + player2);

    long score = 0;
    for (int i = 0; i < player1.size(); i++) {
      score += (Integer.parseInt(player1.get(i)) * (player1.size() - i));
    }

    System.out.println(score);

  }

  private void playGame(List<String> player1, List<String> player2) {
    int p1 = Integer.parseInt(player1.get(0));
    int p2 = Integer.parseInt(player2.get(0));

    if (p1 > p2) {
      player1.add(player1.get(0));
      player1.add(player2.get(0));
    } else {
      player2.add(player2.get(0));
      player2.add(player1.get(0));
    }
    player1.remove(0);
    player2.remove(0);
  }

  public void partTwo() {
    System.out.println("# Part 2 #");

    // ...
  }
}
