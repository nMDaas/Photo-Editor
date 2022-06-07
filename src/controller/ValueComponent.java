package controller;

import java.util.Map;

import model.ImageProcessingModel;

public class ValueComponent implements ImageProcessingCommand {
  String image;
  Map<String, ImageProcessingModel> images;
  String newImage;

  public ValueComponent(String image, Map<String, ImageProcessingModel> images, String newImage) {
    this.image = image;
    this.images = images;
    this.newImage = newImage;
  }

  @Override
  public void go() {
    ImageProcessingModel model = images.get(image);
    ImageProcessingModel valueModel = model.valueComponent(newImage);
    images.put(newImage, valueModel);
  }
}
