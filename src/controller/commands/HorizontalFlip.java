package controller.commands;

import controller.ImageProcessingController;
import model.ImageProcessingModel;

/**
 * Represents the horizontalflip class which helps create an image manipulated by horizontally
 * flipping it.
 */
public class HorizontalFlip extends AbstractCommand {

  /**
   * Constructs a {@code HorizontalFlip} with its fields initialized to themselves.
   *
   * @param image      the name of the image the user is trying to manipulate.
   * @param controller the controller.
   * @param newImage   the new filename.
   */
  public HorizontalFlip(String image, ImageProcessingController controller, String newImage) {
    super(image, controller, newImage);
  }

  /**
   * Helps horizontally flip the image and prints its respective message.
   *
   * @param model      the model.
   * @param controller the controller.
   * @return the flipped image.
   */
  @Override
  public ImageProcessingModel doCommand(ImageProcessingModel model,
                                        ImageProcessingController controller) {
    controller.printMessage(newImage + " created by horizontally flipping " + image + ".");
    return model.flipHorizontal();
  }
}
