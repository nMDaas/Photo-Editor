package controller;

import java.util.Map;

import model.ImageModel;
import model.ImageProcessingModel;

public class HorizontalFlip implements ImageProcessingCommand {

  String image;
  Map<String, ImageProcessingModel> images;
  String newImage;

  public HorizontalFlip(String image, Map<String, ImageProcessingModel> images, String newImage) {
    this.image = image;
    this.images = images;
    this.newImage = newImage;
  }

  @Override
  public void go() {
    ImageProcessingModel model = images.get(image);
    ImageProcessingModel horizontallyFlippedModel = model.flipHorizontal(this.newImage);
    images.put(newImage, horizontallyFlippedModel);

  }
}
