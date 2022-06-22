package controller.commands;

import java.io.IOException;

import controller.ImageGUIController;
import controller.ImageProcessingController;
import model.ImageModel;
import model.ImageProcessingModel;
import view.ImageProcessingViewGUI;

/**
 * Represents the MakeGreyscale class which helps create an image manipulated through the
 * greyscale filter.
 */
public class MakeGreyscale extends AbstractCommand {

  /**
   * Constructor that creates a new MakeGreyScale.
   * @param image the image name to be edited
   * @param controller the controller
   * @param newImage the name of the new edited image
   */
  public MakeGreyscale(String image, ImageProcessingModel model,
                       ImageProcessingController controller, String newImage) {
    super(image, model, controller, newImage);
  }

  public MakeGreyscale(ImageProcessingModel model,
                       ImageProcessingViewGUI view) {
    super(model, view);
  }

  /**
   * A factory method that does the specific command by calling the extended class.
   *
   * @param model the ImageProcessingModel to perform the command on
   * @return a new ImageProcessingModel
   */
  @Override
  public ImageModel doCommand(ImageModel model) throws IOException {
    if (view == null) {
      controller.printMessage(newImage + " created through greyscale of " + image + ".");
    }
    if (controller == null) {
      view.renderMessage(newImage + " created through greyscale of " + image + ".");
    }

    return model.setGreyscale();
  }
}
