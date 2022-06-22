package controller.commands;

import controller.ImageGUIController;
import controller.ImageProcessingController;
import model.ImageModel;
import model.ImageProcessingModel;
import view.ImageProcessingViewGUI;

/**
 * Represents the brighten class which helps create an image manipulated according to its given
 * brightness value.
 */
public class Brighten extends AbstractCommand {

  int value;

  public Brighten(ImageProcessingModel model,
                  ImageProcessingViewGUI view) {
    super(model, view);
    this.value = view.getBrightenValue();
  }

  public Brighten(String image, ImageProcessingModel model,
                  ImageProcessingController controller, int value, String newImage) {
    super(image, model, controller, newImage);
    this.value = value;
  }

  /**
   * A factory method that does the specific command by calling the extended class.
   *
   * @param model the ImageProcessingModel to perform the command on
   * @return a new ImageProcessingModel
   */
  @Override
  public ImageModel doCommand(ImageModel model) {
    controller.printMessage(newImage + " created by changing brightness of " + image + ".");
    return model.brighten(value);
  }
}
