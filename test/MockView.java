import java.io.IOException;
import java.util.Objects;

import view.ImageProcessingView;

public class MockView implements ImageProcessingView {

  final StringBuilder log;

  /**
   * Makes a MockView by taking a log.
   * @param log a StringBuilder used by the MockView
   */
  public MockView(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  @Override
  public void renderMessage(String message) throws IOException {
    log.append(String.format("message = " + message));
  }
}
