package controller.commands;

import java.io.IOException;

import controller.ImageGUIController;
import controller.ImageProcessingController;
import model.ImageModel;
import model.ImageProcessingModel;
import view.ImageProcessingViewGUI;

/**
 * Represents the greencomponent class which helps create an image manipulated according to its
 * green component.
 */
public class GreenComponent extends AbstractCommand {

  public GreenComponent(ImageProcessingModel model,
                        ImageProcessingViewGUI view) {
    super(model, view);
  }

  public GreenComponent(String image, ImageProcessingModel model,
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
      controller.printMessage(newImage + " created through green channel of " + image + ".");
    }
    if (controller == null) {
      view.renderMessage(newImage + " created through green channel of " + image + ".");
    }
    return model.greenComponent();
  }

}
