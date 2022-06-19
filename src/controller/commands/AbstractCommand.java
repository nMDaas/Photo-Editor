package controller.commands;

import java.util.Map;

import controller.ImageProcessingController;
import model.ImageModel;
import model.ImageProcessingModel;

/**
 * Represents the abstract class for the different commands the controller can handle.
 */
abstract public class AbstractCommand implements ImageProcessingCommand {
  protected String image;
  protected ImageProcessingModel model;
  protected ImageProcessingController controller;
  protected String newImage;

  /**
   * Constructs a {@code AbstractCommand} with its fields initialized to themselves.
   *
   * @param image      the name of the image the user is trying to manipulate.
   * @param model       the ImageProcessingModel.
   * @param newImage   the new filename.
   */
  public AbstractCommand(String image, ImageProcessingModel model,
                         ImageProcessingController controller, String newImage) {
    if (image.equals("")) {
      throw new IllegalArgumentException("Image with no name does not exist.");
    }
    if (newImage.equals("")) {
      throw new IllegalArgumentException("New filename cannot be empty.");
    }
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }
    if (controller == null) {
      throw new IllegalArgumentException("Controller cannot be null.");
    }
    this.image = image;
    this.model = model;
    this.newImage = newImage;
    this.controller = controller;
  }

  /**
   * Performs the command and stores the new image in the hashmap.
   */
  @Override
  public void execute() {
    Map<String, ImageModel> images = model.getImages();
    ImageModel model = images.get(image);
    if (model == null) {
      throw new IllegalArgumentException("This image does not exist.");
    }
    ImageModel modifiedModel = doCommand(model, controller);
    images.put(newImage, modifiedModel);
  }

  /**
   * A factory method that does the specific command by calling the extended class.
   *
   * @param model   the ImageProcessingModel to perform the command on
   * @param control the ImageProcessingController to be passed
   * @return a new ImageProcessingModel
   */
  abstract protected ImageModel doCommand(ImageModel model,
                                          ImageProcessingController control);
}
