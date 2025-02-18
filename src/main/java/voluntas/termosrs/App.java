package voluntas.termosrs;

import java.util.Scanner;

public class App {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    Tui tui = new Tui().create();

    while (true) {
      tui.printLayout();
      String input = scanner.nextLine();
      if (input.equals("quit")) {
        break;
      }
    }

    scanner.close();

  }

}
