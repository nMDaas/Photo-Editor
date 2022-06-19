package controller.commands;

import controller.ImageProcessingController;
import model.ImageProcessingModel;

/**
 * Represents the save class which helps create an image by saving it.
 */
public class Save implements ImageProcessingCommand {

  String image;
  ImageProcessingController controller;
  ImageProcessingModel model;
  String path;

  /**
   * Constructs a {@code Save} with its fields initialized to themselves.
   *
   * @param controller the controller.
   * @param path       the path name.
   * @param image      the image name.
   */
  public Save(ImageProcessingModel model, ImageProcessingController controller,
              String path, String image) {
    if (path.equals("")) {
      throw new IllegalArgumentException("Invalid file path..");
    }
    if (image.equals("")) {
      throw new IllegalArgumentException("Image name cannot be empty.");
    }
    if (controller == null ) {
      throw new IllegalArgumentException("Controller cannot be null.");
    }
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }
    this.image = image;
    this.controller = controller;
    this.path = path;
    this.model = model;
  }

  /**
   * Helps to save the image depending on the file type.
   */
  @Override
  public void execute() {

    String fileFormat = path.substring(path.length() - 4, path.length());
    if (fileFormat.contains("ppm")) {
      new SavePPM(image, model, controller, path).saveFile();
    }
    else if (fileFormat.contains("jpeg") ||
            fileFormat.contains("jpg")) {
      new SaveJPEG(image, model, controller, path).saveFile();
    }
    else if (fileFormat.contains("png")) {
      new SavePNG(image, model, controller, path).saveFile();
    }
    else {
      controller.printMessage("File format is not supported.");
    }

    controller.printMessage("Saved image " + this.image + ".");

  }
}
