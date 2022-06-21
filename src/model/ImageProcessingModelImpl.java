package model;

import java.util.HashMap;
import java.util.Map;

public class ImageProcessingModelImpl implements ImageProcessingModel {

  private ImageModel [] images = new ImageModel[1];

  @Override
  public ImageModel [] getImages() {
    return this.images;
  }
}
