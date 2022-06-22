package controller.commands;

import java.io.IOException;

import controller.ImageGUIController;
import controller.ImageProcessingController;
import model.ImageModel;
import model.ImageProcessingModel;
import view.ImageProcessingViewGUI;

/**
 * Represents the makesepia class which helps create an image manipulated through the
 * sepia filter.
 */
public class MakeSepia extends AbstractCommand {

  public MakeSepia(String image, ImageProcessingModel model,
                   ImageProcessingController controller, String newImage) {
    super(image, model, controller, newImage);
  }

  public MakeSepia(ImageProcessingModel model,
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
      view.renderMessage(newImage + " created through sepia filter of " + image + ".");
    }

    return model.setSepia();
  }
}
