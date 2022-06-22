import java.io.IOException;
import java.util.Objects;

import controller.ImageGUIController;
import view.ImageProcessingViewGUI;

public class MockViewGUI implements ImageProcessingViewGUI {
  final StringBuilder log;

  /**
   * Makes a MockView by taking a log.
   *
   * @param log a StringBuilder used by the MockView
   */
  public MockViewGUI(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }


  @Override
  public void renderError(String message) throws IOException {
    log.append(String.format("message = " + message));
  }

  @Override
  public void renderMessage(String message) throws IOException {
    log.append(String.format("message = " + message));
  }

  @Override
  public String getFilePath() {
    return null;
  }

  @Override
  public String saveToFilePath() {
    return null;
  }

  @Override
  public void makeVisible() {

  }

  @Override
  public int getBrightenValue() {
    return 0;
  }

  @Override
  public void refresh() {

  }

  @Override
  public void setController(ImageGUIController controller) {
  }

  @Override
  public void viewSetUp() {

  }
}
