package controller.commands;

import java.io.IOException;

import controller.ImageGUIController;
import controller.ImageProcessingController;
import model.ImageModel;
import model.ImageProcessingModel;
import view.ImageProcessingViewGUI;

/**
 * Represents the intensity component class which helps create an image manipulated
 * according to its intensity component.
 */
public class IntensityComponent extends AbstractCommand {

  public IntensityComponent(ImageProcessingModel model,
                            ImageProcessingViewGUI view) {
    super(model, view);
  }

  public IntensityComponent(String image, ImageProcessingModel model,
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
      controller.printMessage(newImage + " created through intensity greyscale of " + image + ".");
    }
    if (controller == null) {
      view.renderMessage(newImage + " created through intensity greyscale of " + image + ".");
    }

    return model.intensityComponent();
  }


}
