package controller;

import java.util.Map;

import model.ImageProcessingModel;

public class LumaComponent implements ImageProcessingCommand {
  String image;
  Map<String, ImageProcessingModel> images;
  String newImage;

  public LumaComponent(String image, Map<String, ImageProcessingModel> images, String newImage) {
    this.image = image;
    this.images = images;
    this.newImage = newImage;
  }

  @Override
  public void go() {
    ImageProcessingModel model = images.get(image);
    ImageProcessingModel lumaModel = model.valueComponent(newImage);
    images.put(newImage, lumaModel);
  }
}
