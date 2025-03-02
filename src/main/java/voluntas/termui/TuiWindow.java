package voluntas.termui;

import java.util.HashMap;

public class TuiWindow extends TuiElement {

  private HashMap<String, TuiElement> elements = new HashMap<>();

  private TuiWindow(Builder builder) {
    super(builder.id,
        builder.startCol,
        builder.startRow,
        builder.width,
        builder.height,
        builder.title,
        builder.borderHorizontal,
        builder.borderVertical,
        builder.borderTopLeft,
        builder.borderTopRight,
        builder.borderBottomLeft,
        builder.borderBottomRight);
  }

  @Override
  void render() {
    // Sides
    for (int row = startRow; row < startRow + height; row++) {
      TuiUtils.typeAtPos(startCol, row, borderVertical);
      TuiUtils.typeAtPos((startCol + width) - 1, row, borderVertical);
    }
    // Top & Bot
    for (int col = startCol; col < startCol + width; col++) {
      TuiUtils.typeAtPos(col, startRow, borderHorizontal);
      TuiUtils.typeAtPos(col, (startRow + height) - 1, borderHorizontal);
    }
    // Corners
    TuiUtils.typeAtPos(startCol, startRow, borderTopLeft);
    TuiUtils.typeAtPos(startCol + width - 1, startRow, borderTopRight);
    TuiUtils.typeAtPos(startCol, startRow + height - 1, borderBottomLeft);
    TuiUtils.typeAtPos(startCol + width - 1, startRow + height - 1, borderBottomRight);
    // Title
    if (title != null)
      TuiUtils.typeAtPos(startCol + (width / 2) - (title.length() / 2) - 2, startRow, " " + title + " ");
    // Child elements
    for (TuiElement element : elements.values()) {
      element.render();
    }
  }

  public void addElement(TuiElement element) {
    if (elements.containsKey(element.id)) {
      throw new IllegalStateException(String.format("Element with ID '%s' already exists", element.id));
    }
    // nested elements take relative size & position of window
    element.startCol = this.startCol + (int) Math.round(((double) this.width / 100) * (double) element.startCol);
    element.startRow = this.startRow + (int) Math.round(((double) this.height / 100) * (double) element.startRow);
    this.elements.put(element.id, element);
  }

  public static class Builder {

    private String id;
    private int startCol;
    private int startRow;
    private int width;
    private int height;
    private String title;
    private String borderHorizontal = "═";
    private String borderVertical = "║";
    private String borderTopLeft = "╔";
    private String borderTopRight = "╗";
    private String borderBottomLeft = "╚";
    private String borderBottomRight = "╝";

    public Builder(int startCol, int startRow) {
      this.startCol = startCol;
      this.startRow = startRow;
    }

    public Builder setSize(int width, int height) {
      this.width = width;
      this.height = height;
      return this;
    }

    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    public Builder setTitle(String title) {
      this.title = title;
      return this;
    }

    public Builder setBorder(String horizontal, String vertical) {
      this.borderHorizontal = horizontal;
      this.borderVertical = vertical;
      return this;
    }

    public Builder setBorderCorners(String topLeft, String topRight, String bottomLeft, String bottomRight) {
      this.borderTopLeft = topLeft;
      this.borderTopRight = topRight;
      this.borderBottomLeft = bottomLeft;
      this.borderBottomRight = bottomRight;
      return this;
    }

    public TuiWindow build() {
      return new TuiWindow(this);
    }

  }

}
