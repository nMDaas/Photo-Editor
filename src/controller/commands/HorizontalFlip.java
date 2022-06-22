package controller.commands;

import java.io.IOException;

import controller.ImageGUIController;
import controller.ImageProcessingController;
import model.ImageModel;
import model.ImageProcessingModel;
import view.ImageProcessingViewGUI;

/**
 * Represents the horizontalflip class which helps create an image manipulated by horizontally
 * flipping it.
 */
public class HorizontalFlip extends AbstractCommand {

  public HorizontalFlip(ImageProcessingModel model,
                        ImageProcessingViewGUI view) {
    super(model, view);
  }

  public HorizontalFlip(String image, ImageProcessingModel model,
                        ImageProcessingController controller, String newImage) {
    super(image, model, controller, newImage);
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
      controller.printMessage(newImage + " created by horizontally flipping " + image + ".");
    }
    if (controller == null) {
      view.renderMessage(newImage + " created by horizontally flipping " + image + ".");
    }

    return model.flipHorizontal();
  }

}
