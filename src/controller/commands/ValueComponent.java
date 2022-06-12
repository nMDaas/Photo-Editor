package controller.commands;

import controller.ImageProcessingController;
import model.ImageProcessingModel;

/**
 * Represents the valuecomponent class which helps create an image manipulated according to its
 * value component.
 */
public class ValueComponent extends AbstractCommand {

  /**
   * Constructs a {@code ValueComponent} with its fields initialized to themselves.
   *
   * @param image      the name of the image the user is trying to manipulate.
   * @param controller the controller.
   * @param newImage   the new filename.
   */
  public ValueComponent(String image, ImageProcessingController controller, String newImage) {
    super(image, controller, newImage);
  }

  /**
   * Helps create a greyscale image with the image's value-component and its respective message.
   *
   * @param model      the model.
   * @param controller the controller.
   * @return the greyscale image with the value-component of the image.
   */
  @Override
  public ImageProcessingModel doCommand(ImageProcessingModel model,
                                        ImageProcessingController controller) {
    controller.printMessage(newImage + " created through value greyscale of " + image + ".");
    return model.valueComponent();
  }
}
