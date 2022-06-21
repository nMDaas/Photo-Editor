package controller.commands;

import java.util.Scanner;

import controller.ImageGUIController;
import controller.ImageProcessingController;
import model.ImageProcessingModel;
import view.ImageProcessingViewGUI;

/**
 * Represents the load class which helps create an image by loading it.
 */
public class Load implements ImageProcessingCommand {
  ImageProcessingModel model;
  protected ImageProcessingViewGUI view;


  public Load(ImageProcessingModel model,
              ImageProcessingViewGUI view) {
    if (view == null || model == null) {
      throw new IllegalArgumentException("Controller, model and view cannot be null.");
    }
    this.view = view;
    this.model = model;
  }

  /**
   * Helps to load the image depending on the file type.
   */
  @Override
  public void execute() {
    String filename = view.getFilePath();
    if (!(filename.equals("File path will appear here"))) {
      String fileFormat = filename.substring(filename.length() - 4, filename.length());
      if (fileFormat.contains("ppm")) {
        new LoadPPM(filename, model, view).loadFile();
      } else if (fileFormat.contains("jpeg") ||
              fileFormat.contains("png") ||
              fileFormat.contains("jpg")) {
        new LoadJPEGPNG(filename, model, view).loadFile();
      } else {
        view.showErrorMessage("File format is not supported.");
      }
    }

  }
}
