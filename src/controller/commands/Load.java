package controller.commands;

import java.util.Scanner;

import controller.ImageProcessingController;
import model.ImageProcessingModel;

/**
 * Represents the load class which helps create an image by loading it.
 */
public class Load implements ImageProcessingCommand {
  String filename;
  ImageProcessingModel model;
  ImageProcessingController controller;
  Scanner scan;

  /**
   * Constructs {@code Load} with its fields initialized to themselves.
   *
   * @param filename   the name of the file path.
   * @param controller the controller.
   * @param scan       the scanner.
   */
  public Load(String filename, ImageProcessingModel model,
              ImageProcessingController controller, Scanner scan) {
    if (filename.equals("")) {
      throw new IllegalArgumentException("Invalid file path.");
    }
    if (controller == null || scan == null || model == null) {
      throw new IllegalArgumentException("Controller, model and scanner cannot be null.");
    }
    this.filename = filename;
    this.controller = controller;
    this.scan = scan;
    this.model = model;
  }

  /**
   * Helps to load the image depending on the file type.
   */
  @Override
  public void execute() {

    String fileFormat = filename.substring(filename.length() - 4, filename.length());
    if (fileFormat.contains("ppm")) {
      new LoadPPM(filename, model, controller, scan).loadFile();
    }
    else if (fileFormat.contains("jpeg") ||
            fileFormat.contains("png") ||
            fileFormat.contains("jpg")) {
      new LoadJPEGPNG(filename, model, controller, scan).loadFile();
    }
    else {
      controller.printMessage("File format is not supported.");
    }

  }
}
