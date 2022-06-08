package model;

public interface ImageProcessingModel {
  ImageProcessingModel redComponent();
  ImageProcessingModel greenComponent();
  ImageProcessingModel blueComponent();
  ImageProcessingModel valueComponent();
  ImageProcessingModel intensityComponent();
  ImageProcessingModel lumaComponent();
  ImageProcessingModel flipHorizontal();

  ImageProcessingModel flipVertical();

  ImageProcessingModel brighten(int val);
  int getHeight();
  int getWidth();
  Pixel getPixelAt(int row, int col);
  int getMax();
}
