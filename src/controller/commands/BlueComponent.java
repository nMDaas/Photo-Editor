package controller.commands;

import controller.ImageProcessingController;
import model.ImageModel;
import model.ImageProcessingModel;

/**
 * Represents the BlueComponent class which helps create an image manipulated according to its
 * blue component.
 */
public class BlueComponent extends AbstractCommand {

  /**
   * Constructs a {@code BlueComponent} with its fields initialized to themselves.
   *
   * @param image      the name of the image the user is trying to manipulate.
   * @param controller the controller.
   * @param newImage   the new filename.
   */
  public BlueComponent(String image, ImageProcessingModel model,
                       ImageProcessingController controller, String newImage) {
    super(image, model, controller, newImage);
  }

  /**
   * Helps create a greyscale image with the blue-component of the image and its respective message.
   *
   * @param model      the model.
   * @param controller the controller.
   * @return the greyscale image with the blue-component of the image.
   */
  @Override
  public ImageModel doCommand(ImageModel model,
                              ImageProcessingController controller) {
    controller.printMessage(newImage + " created through blue channel of " + image + ".");
    return model.blueComponent();
  }
}
