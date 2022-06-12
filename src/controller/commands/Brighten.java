package controller.commands;

import controller.ImageProcessingController;
import model.ImageProcessingModel;

/**
 * Represents the brighten class which helps create an image manipulated according to its given
 * brighteness value.
 */
public class Brighten extends AbstractCommand {

  int value;

  /**
   * Constructs a {@code Brighten} with its fields initialized to themselves.
   *
   * @param image      the name of the image the user is trying to manipulate.
   * @param controller the controller.
   * @param value      the brightness value the image is manipulated by.
   * @param newImage   the new filename.
   */
  public Brighten(String image, ImageProcessingController controller, int value, String newImage) {
    super(image, controller, newImage);
    this.value = value;
  }

  /**
   * Helps brighten the image and prints its respective message.
   *
   * @param model      the model.
   * @param controller the controller.
   * @return the brightened image.
   */
  @Override
  public ImageProcessingModel doCommand(ImageProcessingModel model,
                                        ImageProcessingController controller) {
    controller.printMessage(newImage + " created by changing brightness of " + image + ".");
    return model.brighten(this.value);
  }
}
