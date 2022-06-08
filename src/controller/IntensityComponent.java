package controller;

import java.util.Map;

import model.ImageProcessingModel;

public class IntensityComponent implements ImageProcessingCommand {
  String image;
  ImageProcessingController controller;
  String newImage;

  public IntensityComponent(String image, ImageProcessingController controller, String newImage) {
    this.image = image;
    this.controller = controller;
    this.newImage = newImage;
  }

  @Override
  public void go() {
    Map<String, ImageProcessingModel> images = controller.getImages();
    ImageProcessingModel model = images.get(image);
    ImageProcessingModel intensityComponent = model.intensityComponent();
    images.put(newImage, intensityComponent);
    controller.printMessage("Intensity greyscale of " + image + " created as " + newImage + ".");
  }
}
