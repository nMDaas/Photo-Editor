package controller;

import java.util.Map;

import model.ImageProcessingModel;

public class IntensityComponent extends AbstractCommand {
  String image;
  ImageProcessingController controller;
  String newImage;

  public IntensityComponent(String image, ImageProcessingController controller, String newImage) {
    super(image, controller, newImage);
  }

  @Override
  public ImageProcessingModel doCommand(ImageProcessingModel model) {
    controller.printMessage(newImage + " created through intensity greyscale of " + image + ".");
    return model.intensityComponent();
  }
}
