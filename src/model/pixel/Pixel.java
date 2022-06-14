package model.pixel;

/**
 * Represents the pixels within an image and its respective methods.
 */
public interface Pixel {

  /**
   * Brightens image.
   *
   * @param val to brighten by (positive or negative)
   */
  public void brighten(int val);

  /**
   * sets color to particular index correlating to a color.
   *
   * @param index index of color number
   */
  public void setColor(int index);

  /**
   * creates a greyscale version of pixel based on value.
   */
  public void setValueComponent();

  /**
   * creates a greyscale version of pixel based on intensity.
   */
  public void setIntensityComponent();

  /**
   * creates a greyscale version of pixel based on luma.
   */
  public void setLumaComponent();

  /**
   * gets the color value of a pixel based on the index of the value.
   *
   * @param index of color
   * @return the value of the color
   */
  public int getColor(int index);

  /**
   * creates a copy of the pixel.
   *
   * @return the copy
   */
  public Pixel createCopy();

  public void multiplyPixel(double multiple);

  public void setGreyscale();

  public void setSepia();
}
