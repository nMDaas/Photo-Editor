package view;

import java.io.IOException;

/**
 * Implementation of ImageProcessingView that implements inherited methods.
 */
public class ImageProcessingViewImpl implements ImageProcessingView {

  private final Appendable appendable;

  /**
   * Creates an ImageProcessingViewImpl.
   * @param appendable the appendable.
   */
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
  public void renderError(String message) throws IOException {
    this.appendable.append(message);
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.appendable.append(message);
  }
}
