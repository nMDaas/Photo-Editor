package controller.commands;

import java.io.IOException;

import controller.ImageGUIController;
import controller.ImageProcessingController;
import model.ImageModel;
import model.ImageProcessingModel;
import view.ImageProcessingViewGUI;

/**
 * Represents the BlueComponent class which helps create an image manipulated according to its
 * blue component.
 */
public class BlueComponent extends AbstractCommand {

  public BlueComponent(ImageProcessingModel model,
                         ImageProcessingViewGUI view) {
    super(model, view);
  }

  public BlueComponent(String image, ImageProcessingModel model,
                       ImageProcessingController controller, String newImage) {
    super(image, model, controller, newImage);
  }

  /**
   * Helps create a greyscale image with the blue-component of the image and its respective message.
   *
   * @param model      the model.
   * @return the greyscale image with the blue-component of the image.
   */
  @Override
  public ImageModel doCommand(ImageModel model) throws IOException {
    if (view == null) {
      controller.printMessage(newImage + " created through blue channel of " + image + ".");
    }
    if (controller == null) {
      view.renderMessage(newImage + " created through blue channel of " + image + ".");
    }
    return model.blueComponent();
  }
}
