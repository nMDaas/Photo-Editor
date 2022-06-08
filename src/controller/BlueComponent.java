package controller;

import java.util.Map;

import model.ImageProcessingModel;

public class BlueComponent extends AbstractCommand {
  String image;
  ImageProcessingController controller;
  String newImage;

  public BlueComponent(String image, ImageProcessingController controller, String newImage) {
    super(image, controller, newImage);
  }

  @Override
  public ImageProcessingModel doCommand(ImageProcessingModel model) {
    controller.printMessage(newImage + " created through blue channel of " + image + ".");
    return model.blueComponent();
  }
}
