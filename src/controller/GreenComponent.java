package controller;

import java.util.Map;

import model.ImageProcessingModel;

public class GreenComponent implements ImageProcessingCommand{
  String image;
  ImageProcessingController controller;
  String newImage;

  public GreenComponent(String image, ImageProcessingController controller, String newImage) {
    this.image = image;
    this.controller = controller;
    this.newImage = newImage;
  }

  @Override
  public void go() {
    Map<String, ImageProcessingModel> images = controller.getImages();
    ImageProcessingModel model = images.get(image);
    ImageProcessingModel greenModel = model.greenComponent(newImage);
    images.put(newImage, greenModel);
    controller.printMessage(newImage + " created through green channel of " + image + ".");
  }
}
