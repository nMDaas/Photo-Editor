package controller.commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import controller.ImageProcessingController;
import model.ImageModel;
import model.ImageProcessingModel;
import model.pixel.Pixel;
import model.pixel.RGBPixel;

/**
 * Represents the load class which helps create an image by loading it.
 */
public class Load implements ImageProcessingCommand {
  String filename;
  ImageProcessingController controller;
  Scanner scan;

  /**
   * Constructs {@code Load} with its fields initialized to themselves.
   *
   * @param filename   the name of the file path.
   * @param controller the controller.
   * @param scan       the scanner.
   */
  public Load(String filename, ImageProcessingController controller, Scanner scan) {
    if (filename.equals("")) {
      throw new IllegalArgumentException("Invalid file path.");
    }
    if (controller == null || scan == null) {
      throw new IllegalArgumentException("Controller and scanner cannot be null.");
    }
    this.filename = filename;
    this.controller = controller;
    this.scan = scan;
  }

  /**
   * Helps to load the image.
   */
  @Override
  public void execute() {

    String fileFormat = filename.substring(filename.length() - 4, filename.length());
    if (fileFormat.contains("ppm")) {
      new LoadPPM(filename, controller, scan).loadFile();
    }
    else if (fileFormat.contains("jpeg") ||
            fileFormat.contains("png") ||
            fileFormat.contains("jpg")) {
      new LoadJPEGPNG(filename, controller, scan).loadFile();
    }
    else {
      controller.printMessage("File format is not supported.");
    }

  }
}
