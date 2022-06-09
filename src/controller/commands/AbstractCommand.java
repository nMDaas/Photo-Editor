package controller.commands;

import java.util.Map;

import controller.ImageProcessingController;
import model.ImageProcessingModel;

abstract public class AbstractCommand implements ImageProcessingCommand {
  protected String image;
  protected ImageProcessingController controller;
  protected String newImage;

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

  @Override
  public void go() {
    Map<String, ImageProcessingModel> images = controller.getImages();
    ImageProcessingModel model = images.get(image);
    if (model == null) {
      throw new IllegalArgumentException("This image does not exist.");
    }
    ImageProcessingModel modifiedModel = doCommand(model, controller);
    images.put(newImage, modifiedModel);
  }

  abstract public ImageProcessingModel doCommand(ImageProcessingModel model,
                                                 ImageProcessingController control);
}
