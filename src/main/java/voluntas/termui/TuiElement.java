package voluntas.termui;

public abstract class TuiElement {

  protected String id;
  protected int startCol;
  protected int startRow;
  protected int width;
  protected int height;
  protected String title;
  protected String borderHorizontal;
  protected String borderVertical;
  protected String borderTopLeft;
  protected String borderTopRight;
  protected String borderBottomLeft;
  protected String borderBottomRight;

  protected TuiElement(String id, int startCol, int startRow, int width, int height, String title,
      String borderHorizontal,
      String borderVertical, String borderTopLeft, String borderTopRight, String borderBottomLeft,
      String borderBottomRight) {
    this.id = id;
    this.startCol = startCol;
    this.startRow = startRow;
    this.width = (width * TuiUtils.getWidth) / 100;
    this.height = (height * TuiUtils.getHeight) / 100;
    this.title = title;
    this.borderHorizontal = borderHorizontal;
    this.borderVertical = borderVertical;
    this.borderTopLeft = borderTopLeft;
    this.borderTopRight = borderTopRight;
    this.borderBottomLeft = borderBottomLeft;
    this.borderBottomRight = borderBottomRight;
  }

  abstract void render();

}
