package controller;

import java.util.Map;

import model.ImageProcessingModel;

public class IntensityComponent implements ImageProcessingCommand {
  String image;
  Map<String, ImageProcessingModel> images;
  String newImage;

  public IntensityComponent(String image, Map<String, ImageProcessingModel> images, String newImage) {
    this.image = image;
    this.images = images;
    this.newImage = newImage;
  }

  @Override
  public void go() {
    ImageProcessingModel model = images.get(image);
    ImageProcessingModel intensityComponent = model.intensityComponent(newImage);
    images.put(newImage, intensityComponent);
  }
}
