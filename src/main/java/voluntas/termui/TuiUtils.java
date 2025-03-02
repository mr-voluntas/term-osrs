package voluntas.termui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class TuiUtils {

  public static void typeAtPos(int col, int row, String text) {
    System.out.printf("\033[%d;%dH%s", row, col, text);
  }

  // if terminal width/height is odd.. add 1. This is done to avoid odd rendering
  // issues.
  public static int getWidth = (getTermDimentions()[0] % 2 == 0) ? getTermDimentions()[0] : getTermDimentions()[0] + 1;
  public static int getHeight = (getTermDimentions()[1] % 2 == 0) ? getTermDimentions()[1] : getTermDimentions()[1] + 1;

  private static int[] getTermDimentions() {
    ProcessBuilder processBuilder = new ProcessBuilder("sh", "-c", "stty size < /dev/tty");
    try {
      Process process = processBuilder.start();
      BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String[] dimentions = input.readLine().split(" ");
      int[] sizes = { Integer.parseInt(dimentions[1]), Integer.parseInt(dimentions[0]) };
      return sizes;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
