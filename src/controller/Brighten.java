package controller;

import java.util.Map;

import model.ImageModel;
import model.ImageProcessingModel;

public class Brighten implements ImageProcessingCommand {
  String image;
  ImageProcessingController controller;
  int value;
  String newImage;
  public Brighten(String image, ImageProcessingController controller, int value, String newImage) {
    this.image = image;
    this.controller = controller;
    this.value = value;
    this.newImage = newImage;
  }

  @Override
  public void go() {
    Map<String, ImageProcessingModel> images = controller.getImages();
    ImageProcessingModel model = images.get(image);
    ImageProcessingModel brightenedModel = model.brighten(value, newImage);
    images.put(newImage, brightenedModel);
    controller.printMessage("Brightened image " + image + " to " + newImage + ".");
  }
}
