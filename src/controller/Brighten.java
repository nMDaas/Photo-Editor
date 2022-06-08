package controller;

import java.util.Map;

import model.ImageModel;
import model.ImageProcessingModel;

public class Brighten extends AbstractCommand {
  String image;
  ImageProcessingController controller;
  int value;
  String newImage;
  public Brighten(String image, ImageProcessingController controller, int value, String newImage) {
    super(image, controller, newImage);
    this.value = value;
  }

  @Override
  public ImageProcessingModel doCommand(ImageProcessingModel model) {
    controller.printMessage(newImage + " created by changing brightness of " + image + ".");
    return model.brighten(this.value);
  }
}
