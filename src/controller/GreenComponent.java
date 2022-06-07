package controller;

import java.util.Map;

import model.ImageProcessingModel;

public class GreenComponent implements ImageProcessingCommand{
  String image;
  Map<String, ImageProcessingModel> images;
  String newImage;

  public GreenComponent(String image, Map<String, ImageProcessingModel> images, String newImage) {
    this.image = image;
    this.images = images;
    this.newImage = newImage;
  }

  @Override
  public void go() {
    ImageProcessingModel model = images.get(image);
    ImageProcessingModel greenModel = model.greenComponent(newImage);
    images.put(newImage, greenModel);
  }
}
