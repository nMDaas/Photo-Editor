package controller;

import java.util.Map;

import model.ImageProcessingModel;

public class LumaComponent implements ImageProcessingCommand {
  String image;
  ImageProcessingController controller;
  String newImage;

  public LumaComponent(String image, ImageProcessingController controller, String newImage) {
    this.image = image;
    this.controller = controller;
    this.newImage = newImage;
  }

  @Override
  public void go() {
    Map<String, ImageProcessingModel> images = controller.getImages();
    ImageProcessingModel model = images.get(image);
    ImageProcessingModel lumaModel = model.lumaComponent(newImage);
    images.put(newImage, lumaModel);
    controller.printMessage("Luma greyscale of " + image + " created as " + newImage + ".");
  }
}
