package controller.commands;

import java.util.Map;

import controller.ImageProcessingController;
import model.ImageProcessingModel;

/**
 * Represents the abstract class for the different commands the controller can handle.
 */
abstract public class AbstractCommand implements ImageProcessingCommand {
  protected String image;
  protected ImageProcessingController controller;
  protected String newImage;

  /**
   * Constructs a {@code AbstractCommand} with its fields initialized to themselves.
   *
   * @param image      the name of the image the user is trying to manipulate.
   * @param controller the controller.
   * @param newImage   the new filename.
   */
  public AbstractCommand(String image, ImageProcessingController controller, String newImage) {
    if (image.equals("")) {
      throw new IllegalArgumentException("Image with no name does not exist.");
    }
    if (newImage.equals("")) {
      throw new IllegalArgumentException("New filename cannot be empty.");
    }
    if (controller == null) {
      throw new IllegalArgumentException("Controller cannot be null.");
    }
    this.image = image;
    this.controller = controller;
    this.newImage = newImage;
  }

  /**
   * Performs the command and stores the new image in the hashmap.
   */
  @Override
  public void execute() {
    Map<String, ImageProcessingModel> images = controller.getImages();
    ImageProcessingModel model = images.get(image);
    if (model == null) {
      throw new IllegalArgumentException("This image does not exist.");
    }
    ImageProcessingModel modifiedModel = doCommand(model, controller);
    images.put(newImage, modifiedModel);
  }

  /**
   * A factory method that does the specific command by calling the extended class.
   *
   * @param model   the ImageProcessingModel to perform the command on
   * @param control the ImageProcessingController to be passed
   * @return a new ImageProcessingModel
   */
  abstract protected ImageProcessingModel doCommand(ImageProcessingModel model,
                                                    ImageProcessingController control);
}
