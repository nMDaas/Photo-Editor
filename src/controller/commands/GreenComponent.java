package controller.commands;

import controller.ImageProcessingController;
import model.ImageProcessingModel;

/**
 * Represents the greencomponent class which helps create an image manipulated according to its
 * green component.
 */
public class GreenComponent extends AbstractCommand {

  /**
   * Constructs a {@code GreenComponent} with its fields initialized to themselves.
   *
   * @param image      the name of the image the user is trying to manipulate.
   * @param controller the controller.
   * @param newImage   the new filename.
   */
  public GreenComponent(String image, ImageProcessingController controller, String newImage) {
    super(image, controller, newImage);
  }

  /**
   * Helps create a greyscale image with the image's green-component and its respective message.
   *
   * @param model      the model.
   * @param controller the controller.
   * @return the greyscale image with the green-component of the image.
   */
  @Override
  public ImageProcessingModel doCommand(ImageProcessingModel model,
                                        ImageProcessingController controller) {
    controller.printMessage(newImage + " created through green channel of " + image + ".");
    return model.greenComponent();
  }
}
