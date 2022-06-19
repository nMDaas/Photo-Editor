package controller.commands;

import controller.ImageProcessingController;
import model.ImageModel;
import model.ImageProcessingModel;

/**
 * Represents the redcomponent class which helps create an image manipulated according to its
 * red component.
 */
public class RedComponent extends AbstractCommand {

  /**
   * Constructs a {@code RedComponent} with its fields initialized to themselves.
   *
   * @param image      the name of the image the user is trying to manipulate.
   * @param controller the controller.
   * @param newImage   the new filename.
   */
  public RedComponent(String image, ImageProcessingModel model,
                        ImageProcessingController controller, String newImage) {
    super(image, model, controller, newImage);
  }

  /**
   * Helps create a greyscale image with the image's red-component and its respective message.
   *
   * @param model      the model.
   * @param controller the controller.
   * @return the greyscale image with the red-component of the image.
   */
  @Override
  public ImageModel doCommand(ImageModel model,
                              ImageProcessingController controller) {
    controller.printMessage(newImage + " created through red channel of " + image + ".");
    return model.redComponent();
  }
}
