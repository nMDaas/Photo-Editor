package controller.commands;

import java.io.IOException;

import controller.ImageGUIController;
import controller.ImageProcessingController;
import model.ImageModel;
import model.ImageProcessingModel;
import view.ImageProcessingViewGUI;

/**
 * Represents the lumacomponent class which helps create an image manipulated according to its
 * luma component.
 */
public class LumaComponent extends AbstractCommand {

  /**
   * Constructs a {@code LumaComponent} with its fields initialized to themselves.
   *
   * @param image      the name of the image the user is trying to manipulate.
   * @param controller the controller.
   * @param newImage   the new filename.
   */
  public LumaComponent(String image, ImageProcessingModel model,
                       ImageProcessingController controller, String newImage) {
    super(image, model, controller, newImage);
  }

  public LumaComponent(ImageProcessingModel model,
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
      controller.printMessage(newImage + " created through luma greyscale of " + image + ".");
    }
    if (controller == null) {
      view.renderMessage(newImage + " created through luma greyscale of " + image + ".");
    }

    return model.lumaComponent();
  }
}
