package model;

public interface ImageProcessingModel {
  ImageProcessingModel redComponent(String dest);
  ImageProcessingModel greenComponent(String dest);
  ImageProcessingModel blueComponent(String dest);
  ImageProcessingModel valueComponent(String dest);
  ImageProcessingModel intensityComponent(String dest);
  ImageProcessingModel lumaComponent(String dest);


  ImageProcessingModel flipHorizontal(String dest);

  ImageProcessingModel flipVertical(String dest);

  ImageProcessingModel brighten(int val, String dest);
}
