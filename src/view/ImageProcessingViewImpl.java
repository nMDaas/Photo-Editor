package view;

import java.io.IOException;

public class ImageProcessingViewImpl implements ImageProcessingView {

  private final Appendable appendable;

  public ImageProcessingViewImpl(Appendable appendable) {
    if (appendable == null) {
      throw new IllegalArgumentException("appendable is null");
    }

    this.appendable = appendable;
  }

  // TODO: do we need this?
  public ImageProcessingViewImpl() {
    this(System.out);
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.appendable.append(message);
  }
}
