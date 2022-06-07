package controller;

import java.util.Map;

import model.ImageProcessingModel;

public class RedComponent implements ImageProcessingCommand {

  String image;
  Map<String, ImageProcessingModel> images;
  String newImage;

  public RedComponent(String image, Map<String, ImageProcessingModel> images, int value, String newImage) {
    this.image = image;
    this.images = images;
    this.newImage = newImage;
  }

  @Override
  public void go() {
    ImageProcessingModel model = images.get(image);
    ImageProcessingModel redModel = model.redComponent(newImage);
    images.put(newImage, redModel);
  }
}
