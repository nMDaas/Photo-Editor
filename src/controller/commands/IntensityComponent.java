package controller.commands;

import controller.ImageProcessingController;
import model.ImageModel;
import model.ImageProcessingModel;

/**
 * Represents the intensity component class which helps create an image manipulated
 * according to its intensity component.
 */
public class IntensityComponent extends AbstractCommand {

  /**
   * Constructs a {@code IntensityComponent} with its fields initialized to themselves.
   *
   * @param image      the name of the image the user is trying to manipulate.
   * @param controller the controller.
   * @param newImage   the new filename.
   */
  public IntensityComponent(String image, ImageProcessingModel model,
                        ImageProcessingController controller, String newImage) {
    super(image, model, controller, newImage);
  }

  /**
   * Helps create a greyscale image with the image's intensity-component and its respective message.
   *
   * @param model      the model.
   * @param controller the controller.
   * @return the greyscale image with the intensity-component of the image.
   */
  @Override
  public ImageModel doCommand(ImageModel model,
                              ImageProcessingController controller) {
    controller.printMessage(newImage + " created through intensity greyscale of " + image + ".");
    return model.intensityComponent();
  }
}
