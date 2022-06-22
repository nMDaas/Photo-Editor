package controller.commands;

import java.io.IOException;

import controller.ImageProcessingController;
import model.ImageProcessingModel;
import view.ImageProcessingView;
import view.ImageProcessingViewGUI;

/**
 * Represents the save class which helps create an image by saving it.
 */
public class Save implements ImageProcessingCommand {

  String image;
  String path;

  ImageProcessingModel model;
  protected ImageProcessingView view;

  /**
   * Constructs a {@code Save} with its fields initialized to themselves.
   *
   * @param path       the path name.
   * @param image      the image name.
   */
  public Save(ImageProcessingModel model, ImageProcessingView view,
              String path, String image) {
    if (path.equals("")) {
      throw new IllegalArgumentException("Invalid file path..");
    }
    if (image.equals("")) {
      throw new IllegalArgumentException("Image name cannot be empty.");
    }
    if (view == null ) {
      throw new IllegalArgumentException("Controller cannot be null.");
    }
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }
    this.image = image;
    this.view = view;
    this.path = path;
    this.model = model;
  }


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
  public void execute() throws IOException {

    if (path == null) {
      path = view.saveToFilePath();
    }

    String fileFormat = path.substring(path.length() - 4, path.length());
    if (fileFormat.contains("ppm")) {
      new SavePPM(image, model, view, path).saveFile();
    }
    else if (fileFormat.contains("jpeg") ||
            fileFormat.contains("jpg")) {
      new SaveJPEG(image, model, view, path).saveFile();
    }
    else if (fileFormat.contains("png")) {
      new SavePNG(image, model, view, path).saveFile();
    }
    else {
      view.renderError("File format is not supported.");
    }

    path = null;

    //controller.printMessage("Saved image " + this.image + ".");

  }
}
