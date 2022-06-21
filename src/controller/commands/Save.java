package controller.commands;

import controller.ImageGUIController;
import controller.ImageProcessingController;
import model.ImageProcessingModel;
import view.ImageProcessingViewGUI;

/**
 * Represents the save class which helps create an image by saving it.
 */
public class Save implements ImageProcessingCommand {


  ImageProcessingModel model;
  protected ImageProcessingViewGUI view;


  public Save(ImageProcessingModel model,
              ImageProcessingViewGUI view) {


    if (view == null) {
      throw new IllegalArgumentException("Image name cannot be empty.");
    }
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }
    this.model = model;
    this.view = view;
  }

  /**
   * Helps to save the image depending on the file type.
   */
  @Override
  public void execute() {
    String path = view.saveToFilePath();
    String fileFormat = path.substring(path.length() - 4, path.length());
    if (fileFormat.contains("ppm")) {
      new SavePPM(path, model, view).saveFile();
    }
    else if (fileFormat.contains("jpeg") ||
            fileFormat.contains("jpg")) {
      new SaveJPEG(path, model, view).saveFile();
    }
    else if (fileFormat.contains("png")) {
      new SavePNG(path, model, view).saveFile();
    }
    else {
      view.showErrorMessage("File format is not supported.");
    }

    //controller.printMessage("Saved image " + this.image + ".");

  }
}
