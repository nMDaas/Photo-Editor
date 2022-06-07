package controller;

import java.util.Map;

import model.ImageProcessingModel;

public class BlueComponent implements ImageProcessingCommand {
  String image;
  Map<String, ImageProcessingModel> images;
  String newImage;

  public BlueComponent(String image, Map<String, ImageProcessingModel> images, String newImage) {
    this.image = image;
    this.images = images;
    this.newImage = newImage;
  }

  @Override
  public void go() {
    ImageProcessingModel model = images.get(image);
    ImageProcessingModel blueModel = model.blueComponent(newImage);
    images.put(newImage, blueModel);
  }
}
