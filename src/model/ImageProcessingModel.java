package model;

import model.pixel.Pixel;

/**
 * Represents the interface for the ImageProcessingModel. Represents an image and provides methods
 * to manipulate the image.
 */
public interface ImageProcessingModel {
  /**
   * creates a copy of the image.
   *
   * @return the copy
   */
  ImageProcessingModel createCopy();

  /**
   * creates a red channel of the image.
   *
   * @return a new ImageProcessingModel with a red channel
   */
  ImageProcessingModel redComponent();

  /**
   * creates a green channel of the image.
   *
   * @return a new ImageProcessingModel with a green channel
   */
  ImageProcessingModel greenComponent();

  /**
   * creates a blue channel of the image.
   *
   * @return a new ImageProcessingModel with a red channel
   */
  ImageProcessingModel blueComponent();

  /**
   * creates a value greyscale of the image.
   *
   * @return a new ImageProcessingModel with a value greyscale
   */
  ImageProcessingModel valueComponent();

  /**
   * creates a intensity greyscale of the image.
   *
   * @return a new ImageProcessingModel with a intensity greyscale
   */
  ImageProcessingModel intensityComponent();

  /**
   * creates a luma greyscale of the image.
   *
   * @return a new ImageProcessingModel with a luma greyscale
   */
  ImageProcessingModel lumaComponent();

  /**
   * creates a horizontally flipped of the image.
   *
   * @return a new ImageProcessingModel with a horizontal flip
   */
  ImageProcessingModel flipHorizontal();

  /**
   * creates a vertically flipped of the image.
   *
   * @return a new ImageProcessingModel with a vertical flip
   */
  ImageProcessingModel flipVertical();

  /**
   * brightens the image by a value.
   *
   * @param val the value that can be positive or negative
   * @return a new ImageProcessingModel with changed brightness
   */
  ImageProcessingModel brighten(int val);

  /**
   * gets the height of the image.
   *
   * @return the height as an int
   */
  int getHeight();

  /**
   * gets the width of the image.
   *
   * @return the width as an int
   */
  int getWidth();

  /**
   * Gets the pixel at a row, col.
   *
   * @param row the row number
   * @param col the column number
   * @return the pixel
   */
  Pixel getPixelAt(int row, int col);

  /**
   * sets the pixel at a row, col to another pixel's value.
   *
   * @param row the row number
   * @param col the column number
   * @param p   the pixel
   */
  void setPixelAt(int row, int col, Pixel p);

  /**
   * gets the max value of the image.
   *
   * @return the max value as an int
   */
  int getMax();
}
