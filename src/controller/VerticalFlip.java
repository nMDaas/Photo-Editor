package controller;

import java.util.Map;

import model.ImageModel;
import model.ImageProcessingModel;

public class VerticalFlip implements ImageProcessingCommand {

  String image;
  Map<String, ImageProcessingModel> images;
  String newImage;

  public VerticalFlip(String image, Map<String, ImageProcessingModel> images, String newImage) {
    this.image = image;
    this.images = images;
    this.newImage = newImage;
  }

  @Override
  public void go() {
    ImageProcessingModel model = images.get(image);
    ImageProcessingModel verticallyFlippedModel = model.flipVertical(this.newImage);
    images.put(newImage, verticallyFlippedModel);
  }
}
