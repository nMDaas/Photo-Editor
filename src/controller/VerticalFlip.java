package controller;

import java.util.Map;

import model.ImageModel;
import model.ImageProcessingModel;

public class VerticalFlip implements ImageProcessingCommand {

  String image;
  ImageProcessingController controller;
  String newImage;

  public VerticalFlip(String image, ImageProcessingController controller, String newImage) {
    this.image = image;
    this.controller = controller;
    this.newImage = newImage;
  }

  @Override
  public void go() {
    Map<String, ImageProcessingModel> images = controller.getImages();
    ImageProcessingModel model = images.get(image);
    ImageProcessingModel verticallyFlippedModel = model.flipVertical(this.newImage);
    images.put(newImage, verticallyFlippedModel);
    controller.printMessage("Vertically flipped image " + image + " to " + newImage + ".");
  }
}
