package voluntas.termui;

import java.util.Scanner;

public class Sandbox {

  public static void main(String[] args) {

    Scanner input = new Scanner(System.in);

    Tui tui = Tui.getInstance();

    TuiWindow main = new TuiWindow.Builder(0, 0)
        .setId("main").setSize(100, 100).setTitle("Main window")
        .build();
    TuiWindow nestL = new TuiWindow.Builder(0, 0)
        .setId("nestL").setSize(60, 100).setTitle("Nest Left")
        .build();
    TuiWindow nestR = new TuiWindow.Builder(60, 0)
        .setId("nestR").setSize(40, 100).setTitle("Nest Right")
        .build();

    main.addElement(nestL);
    main.addElement(nestR);
    tui.addWindow(main);

    while (true) {
      tui.renderWindows();
      System.out.println(input.nextLine());

    }

  }

}
