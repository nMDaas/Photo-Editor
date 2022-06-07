package controller;

import java.util.Map;

import model.ImageProcessingModel;

public class ValueComponent implements ImageProcessingCommand {
  String image;
  ImageProcessingController controller;
  String newImage;

  public ValueComponent(String image, ImageProcessingController controller, String newImage) {
    this.image = image;
    this.controller = controller;
    this.newImage = newImage;
  }

  @Override
  public void go() {
    Map<String, ImageProcessingModel> images = controller.getImages();
    ImageProcessingModel model = images.get(image);
    ImageProcessingModel valueModel = model.valueComponent(newImage);
    images.put(newImage, valueModel);
    controller.printMessage("Value greyscale of " + image + " created as " + newImage + ".");
  }
}
