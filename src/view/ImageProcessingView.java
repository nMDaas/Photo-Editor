package view;

import java.io.IOException;

/**
 * Represents the interface of the Image Processor's view. It only renders a Message
 * to the view.
 */
public interface ImageProcessingView {
  void renderMessage(String message) throws IOException;
}
