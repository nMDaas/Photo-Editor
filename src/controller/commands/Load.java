package controller.commands;

import java.io.IOException;
import java.util.Scanner;

import controller.ImageGUIController;
import controller.ImageProcessingController;
import model.ImageProcessingModel;
import view.ImageProcessingView;
import view.ImageProcessingViewGUI;

/**
 * Represents the load class which helps create an image by loading it.
 */
public class Load implements ImageProcessingCommand {
  String filename;
  ImageProcessingView view;
  Scanner scan;

  ImageProcessingModel model;


  /**
   * Constructs {@code Load} with its fields initialized to themselves.
   *
   * @param filename   the name of the file path.
   * @param view the view.
   * @param scan       the scanner.
   */
  public Load(String filename, ImageProcessingModel model,
              ImageProcessingView view, Scanner scan) {
    if (filename.equals("")) {
      throw new IllegalArgumentException("Invalid file path.");
    }
    if (view == null || scan == null || model == null) {
      throw new IllegalArgumentException("View, model and scanner cannot be null.");
    }
    this.filename = filename;
    this.view = view;
    this.scan = scan;
    this.model = model;
  }

  public Load(ImageProcessingModel model,
              ImageProcessingViewGUI view) {
    if (view == null || model == null) {
      throw new IllegalArgumentException("Controller, model and view cannot be null.");
    }
    this.view = view;
    this.model = model;
    filename = view.getFilePath();
  }

  /**
   * Helps to load the image depending on the file type.
   */
  @Override
  public void execute() throws IOException {
    if (!(filename.equals("File path will appear here"))) {
      String fileFormat = filename.substring(filename.length() - 4, filename.length());
      if (fileFormat.contains("ppm")) {
        new LoadPPM(filename, model, view, scan).loadFile();
      } else if (fileFormat.contains("jpeg") ||
              fileFormat.contains("png") ||
              fileFormat.contains("jpg")) {
        new LoadJPEGPNG(filename, model, view, scan).loadFile();
      } else {
        view.renderMessage("File format is not supported.");
      }
    }

  }
}
