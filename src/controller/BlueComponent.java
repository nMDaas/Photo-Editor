package controller;

import java.util.Map;

import model.ImageProcessingModel;

public class BlueComponent implements ImageProcessingCommand {
  String image;
  ImageProcessingController controller;
  String newImage;

  public BlueComponent(String image, ImageProcessingController controller, String newImage) {
    this.image = image;
    this.controller = controller;
    this.newImage = newImage;
  }

  @Override
  public void go() {
    Map<String, ImageProcessingModel> images = controller.getImages();
    ImageProcessingModel model = images.get(image);
    ImageProcessingModel blueModel = model.blueComponent(newImage);
    images.put(newImage, blueModel);
    controller.printMessage(newImage + " created through blue channel of " + image + ".");
  }
}
