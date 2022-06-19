package controller.commands;

import controller.ImageProcessingController;
import model.ImageModel;
import model.ImageProcessingModel;

/**
 * Represents the lumacomponent class which helps create an image manipulated according to its
 * luma component.
 */
public class LumaComponent extends AbstractCommand {

  /**
   * Constructs a {@code LumaComponent} with its fields initialized to themselves.
   *
   * @param image      the name of the image the user is trying to manipulate.
   * @param controller the controller.
   * @param newImage   the new filename.
   */
  public LumaComponent(String image, ImageProcessingModel model,
                        ImageProcessingController controller, String newImage) {
    super(image, model, controller, newImage);
  }

  /**
   * Helps create a greyscale image with the image's luma-component and its respective message.
   *
   * @param model      the model.
   * @param controller the controller.
   * @return the greyscale image with the luma-component of the image.
   */
  @Override
  public ImageModel doCommand(ImageModel model,
                              ImageProcessingController controller) {
    controller.printMessage(newImage + " created through luma greyscale of " + image + ".");
    return model.lumaComponent();
  }
}
