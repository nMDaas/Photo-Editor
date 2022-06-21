package controller.commands;

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

  /**
   * A factory method that does the specific command by calling the extended class.
   *
   * @param model the ImageProcessingModel to perform the command on
   * @return a new ImageProcessingModel
   */
  @Override
  public ImageModel doCommand(ImageModel model) {
    return model.flipHorizontal();
  }

}
