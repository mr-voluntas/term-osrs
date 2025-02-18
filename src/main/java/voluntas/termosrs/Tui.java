package voluntas.termosrs;

import java.io.IOException;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class Tui {

  private int termHeight;
  private int termWidth;
  private Terminal terminal;

  public Tui create() {
    try {
      terminal = TerminalBuilder.terminal();
      termHeight = terminal.getHeight();
      termWidth = terminal.getWidth();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return this;
  }

  /**
   * print text on row/col
   * 
   * @param text text to print
   * @param row  row to print on
   * @param col  col to print on
   */
  public void printAt(String text, int row, int col) {
    System.out.printf("\033[%d;%dH%s", row, col, text);
    System.out.printf("\033[%d;%dH", row, col);
  }

  /**
   * Prints the game layout
   */
  public void printLayout() {
    clearTerm();
    final int horSplit = (termWidth / 10) * 7;
    int vertSplitLeft = (termHeight / 10) * 7;
    int vertSplitRight = (termHeight / 10) * 5;
    for (int row = 0; row < termHeight; row++) {
      // Top of term
      if (row == 0) {
        printAt("=".repeat(termWidth), row, 0);
      }
      // Below top and above last
      if (row > 1 && row < termHeight) {
        printAt("|", row, horSplit);
      }
      // Vertical split left side
      if (row == vertSplitLeft) {
        printAt("-".repeat(horSplit - 1), row, 0);
      }
      // Vertical split right side
      if (row == vertSplitRight) {
        printAt("-".repeat(termWidth - horSplit), row, horSplit + 1);
      }
      // Bottom of term
      if (row == termHeight - 1) {
        printAt("=".repeat(termWidth), row, 0);
      }
    }
    // Very bottom (where user input is)
    printAt("Command: ", termHeight, 0);
    setCursorPos(termHeight, 10);
  }

  private void clearTerm() {
    System.out.println("\033[2J");
  }

  /**
   * set cursor position on row/col
   * 
   * @param row move cursor to row
   * @param col move cursor to col
   */
  private void setCursorPos(int row, int col) {
    System.out.printf("\033[%d;%dH", row, col);
  }

}
