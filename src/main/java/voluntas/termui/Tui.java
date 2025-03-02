package voluntas.termui;

import java.util.HashMap;

public class Tui {

  private static Tui instance;
  private HashMap<String, TuiWindow> windows = new HashMap<>();

  private Tui() {
  }

  public static Tui getInstance() {
    if (instance == null) {
      instance = new Tui();
    }
    return instance;
  }

  public void addWindow(TuiWindow window) {
    if (windows.containsKey(window.id)) {
      throw new IllegalStateException(String.format("Window with ID '%s' already exists", window.id));
    }
    this.windows.put(window.id, window);
  }

  public void renderWindows() {
    // clear terminal
    System.out.println("\033[2J");
    for (TuiWindow window : windows.values()) {
      window.render();
    }
  }

}
