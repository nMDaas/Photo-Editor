package controller;

import java.util.Map;

import model.ImageProcessingModel;

public class RedComponent extends AbstractCommand {
  String image;
  ImageProcessingController controller;
  String newImage;

  public RedComponent(String image, ImageProcessingController controller, String newImage) {
    super (image, controller, newImage);
  }

  @Override
  public ImageProcessingModel doCommand(ImageProcessingModel model) {
    controller.printMessage(newImage + " created through red channel of " + image + ".");
    return model.redComponent();
  }
}
