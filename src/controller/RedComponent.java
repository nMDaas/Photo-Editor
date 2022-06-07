package controller;

import java.util.Map;

import model.ImageProcessingModel;

public class RedComponent implements ImageProcessingCommand {

  String image;
  ImageProcessingController controller;
  String newImage;

  public RedComponent(String image, ImageProcessingController controller, String newImage) {
    this.image = image;
    this.controller = controller;
    this.newImage = newImage;
  }

  @Override
  public void go() {
    Map<String, ImageProcessingModel> images = controller.getImages();
    ImageProcessingModel model = images.get(image);
    ImageProcessingModel redModel = model.redComponent(newImage);
    images.put(newImage, redModel);
    controller.printMessage(newImage + " created through red channel of " + image + ".");
  }
}
