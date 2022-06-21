package controller.commands;

import controller.ImageGUIController;
import controller.ImageProcessingController;
import model.ImageModel;
import model.ImageProcessingModel;
import view.ImageProcessingViewGUI;

/**
 * Represents the BlueComponent class which helps create an image manipulated according to its
 * blue component.
 */
public class BlueComponent extends AbstractCommand {

  public BlueComponent(ImageProcessingModel model,
                         ImageProcessingViewGUI view) {
    super(model, view);
  }

  /**
   * Helps create a greyscale image with the blue-component of the image and its respective message.
   *
   * @param model      the model.
   * @return the greyscale image with the blue-component of the image.
   */
  @Override
  public ImageModel doCommand(ImageModel model) {
    return model.blueComponent();
  }
}
