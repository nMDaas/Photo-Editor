package controller.commands;

import controller.ImageGUIController;
import controller.ImageProcessingController;
import model.ImageModel;
import model.ImageProcessingModel;
import view.ImageProcessingViewGUI;

/**
 * Represents the redcomponent class which helps create an image manipulated according to its
 * red component.
 */
public class RedComponent extends AbstractCommand {

  public RedComponent(String image, ImageProcessingModel model,
                      ImageProcessingController controller, String newImage) {
    super(image, model, controller, newImage);
  }

  public RedComponent(ImageProcessingModel model,
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
    controller.printMessage(newImage + " created through red channel of " + image + ".");
    return model.redComponent();
  }
}
