package model;

import model.pixel.Pixel;

public interface ImageProcessingModel {
  ImageProcessingModel createCopy();
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
  void setPixelAt(int row, int col, Pixel p);
  int getMax();
}
