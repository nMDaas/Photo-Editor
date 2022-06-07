package controller;

import java.util.Map;

import model.ImageModel;
import model.ImageProcessingModel;

public class HorizontalFlip implements ImageProcessingCommand {

  String image;
  ImageProcessingController controller;
  String newImage;

  public HorizontalFlip(String image, ImageProcessingController controller, String newImage) {
    this.image = image;
    this.controller = controller;
    this.newImage = newImage;
  }

  @Override
  public void go() {
    Map<String, ImageProcessingModel> images = controller.getImages();
    ImageProcessingModel model = images.get(image);
    ImageProcessingModel horizontallyFlippedModel = model.flipHorizontal(this.newImage);
    images.put(newImage, horizontallyFlippedModel);
    controller.printMessage("Horizontally flipped image " + image + " to " + newImage + ".");
  }
}
