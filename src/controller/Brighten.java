package controller;

import java.util.Map;

import model.ImageModel;
import model.ImageProcessingModel;

public class Brighten implements ImageProcessingCommand {
  String image;
  Map<String, ImageProcessingModel> images;
  int value;
  String newImage;
  public Brighten(String image, Map<String, ImageProcessingModel> images, int value, String newImage) {
    this.image = image;
    this.images = images;
    this.value = value;
    this.newImage = newImage;
  }

  @Override
  public void go() {
    ImageProcessingModel model = images.get(image);
    ImageProcessingModel brightenedModel = model.brighten(value, newImage);
    images.put(newImage, brightenedModel);
  }
}
