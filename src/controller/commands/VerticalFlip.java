package controller.commands;

import controller.ImageProcessingController;
import controller.commands.AbstractCommand;
import model.ImageProcessingModel;

/**
 * Represents the verticalflip class which helps create an image by vertically flipping it.
 */
public class VerticalFlip extends AbstractCommand {

  /**
   * Constructs a {@code VerticalFlip} with its fields initialized to themselves.
   *
   * @param image      the name of the image the user is trying to manipulate.
   * @param controller the controller.
   * @param newImage   the new filename.
   */
  public VerticalFlip(String image, ImageProcessingController controller, String newImage) {
    super(image, controller, newImage);
  }

  /**
   * Helps vertically flip the image and prints its respective message.
   *
   * @param model      the model.
   * @param controller the controller.
   * @return the flipped image.
   */
  @Override
  public ImageProcessingModel doCommand(ImageProcessingModel model,
                                        ImageProcessingController controller) {
    controller.printMessage(newImage + " created by vertically flipping " + image + ".");
    return model.flipVertical();
  }
}
