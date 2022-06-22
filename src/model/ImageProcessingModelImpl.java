package model;

import java.util.HashMap;
import java.util.Map;

public class ImageProcessingModelImpl implements ImageProcessingModel {

  private Map<String, ImageModel> images = new HashMap<>();

  @Override
  public Map<String, ImageModel> getImages() {
    return this.images;
  }
}
