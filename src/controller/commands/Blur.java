package controller.commands;

import controller.ImageGUIController;
import controller.ImageProcessingController;
import model.ImageModel;
import model.ImageProcessingModel;
import view.ImageProcessingViewGUI;

/**
 * Represents the blur class which helps create an image manipulated by blurring it.
 */
public class Blur extends AbstractCommand {

  public Blur(ImageProcessingModel model,
              ImageProcessingViewGUI view) {
    super(model, view);
  }

  public Blur(String image, ImageProcessingModel model,
              ImageProcessingController controller, String newImage) {
    super(image, model, controller, newImage);
  }


  /**
   * A factory method that does the specific command by calling the extended class.
   *
   * @param model   the ImageProcessingModel to perform the command on
   * @return a new ImageProcessingModel
   */
  @Override
  public ImageModel doCommand(ImageModel model) {
    controller.printMessage(newImage + " created by blurring " + image + ".");
    return model.blurImage();
  }

}
