package controller.commands;

import java.util.Map;

import controller.ImageGUIController;
import controller.ImageProcessingController;
import model.ImageModel;
import model.ImageProcessingModel;
import view.ImageProcessingViewGUI;

/**
 * Represents the abstract class for the different commands the controller can handle.
 */
abstract public class AbstractCommand implements ImageProcessingCommand {
  protected ImageProcessingModel model;
  protected ImageProcessingViewGUI view;


  public AbstractCommand(ImageProcessingModel model,
                         ImageProcessingViewGUI view) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }
    if (view == null) {
      throw new IllegalArgumentException("View cannot be null.");
    }
    this.model = model;
    this.view = view;
  }

  /**
   * Performs the command and stores the new image in the hashmap.
   */
  @Override
  public void execute() {
    //Map<String, ImageModel> images = model.getImages();
    ImageModel model = this.model.getImages()[0];
    /*if (model == null) {
      throw new IllegalArgumentException("No image loaded.");
    }*/
    if (model != null) {
      ImageModel modifiedModel = doCommand(model);
      this.model.getImages()[0] = modifiedModel;
    }
    //images.put(newImage, modifiedModel);
  }

  /**
   * A factory method that does the specific command by calling the extended class.
   *
   * @param model   the ImageProcessingModel to perform the command on
   * @return a new ImageProcessingModel
   */
  abstract protected ImageModel doCommand(ImageModel model);
}
