package controller.commands;

import controller.ImageGUIController;
import controller.ImageProcessingController;
import model.ImageModel;
import model.ImageProcessingModel;
import view.ImageProcessingViewGUI;

/**
 * Sharpen represents the command class to sharpen an image.
 */
public class Sharpen extends AbstractCommand {

  /**
   * Creates a Sharpen class.
   * @param image the name of the image to be edited
   * @param controller the controller
   * @param newImage the name of the edited image
   */
  public Sharpen(ImageProcessingModel model,
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
    return model.sharpenImage();
  }
}
