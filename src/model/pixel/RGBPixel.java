package model.pixel;

import java.util.Objects;

/**
 * Represents an RGB Pixel that has three colors where red = 0, green = 1 and
 * blue = 2 in the array of pixels.
 */
public class RGBPixel implements Pixel {
  private int[] colors = new int[3];

  /**
   * Constructs {@code RGBPixel} with its fields initialzied to themselves.
   *
   * @param red   the value of the red component.
   * @param blue  the value of the blue component.
   * @param green the value of the green component.
   * @throws IllegalArgumentException if values are negative or over 255.
   */
  public RGBPixel(int red, int blue, int green) {
    if (red < 0 || blue < 0 || green < 0) {
      throw new IllegalArgumentException("rgb values cannot be negative.");
    }

    if (red > 255 || blue > 255 || green > 255) {
      throw new IllegalArgumentException("rgb values cannot greater than 255.");
    }

    this.colors[0] = red;
    this.colors[1] = green;
    this.colors[2] = blue;
  }

  /**
   * Brightens each pixel.
   *
   * @param val the value the pixel is being brightened by.
   */
  public void brighten(int val) {
    this.colors[0] = this.clipValue(this.colors[0] + val);
    this.colors[1] = this.clipValue(this.colors[1] + val);
    this.colors[2] = this.clipValue(this.colors[2] + val);
  }

  /**
   * Returns a clipped rgb value that is between 0 and 255 inclusive.
   * @param value to be clipped
   * @return clipped value
   */
  public int clipValue(int value) {
    if (value > 255) {
      return 255;
    } else if (value < 0) {
      return 0;
    } else {
      return value;
    }
  }

  /**
   * Finds the maximum value.
   *
   * @return the maximum value.
   */
  private int findValue() {
    int maxValue = this.colors[0];

    if (this.colors[1] > maxValue) {
      maxValue = this.colors[1];
    }

    if (this.colors[2] > maxValue) {
      maxValue = this.colors[2];
    }

    return maxValue;
  }

  /**
   * Finds the intensity of pixels.
   *
   * @return the intensity of the pixels.
   */
  private double findIntensity() {
    return (this.colors[0] + this.colors[1] + this.colors[2]) / 3.0;
  }

  /**
   * Finds the luma of pixels.
   *
   * @return the luma of the pixels.
   */
  private double findLuma() {
    return (0.2126 * Double.valueOf(this.colors[0]))
            + (0.7152 * Double.valueOf(this.colors[1]))
            + (0.0722 * Double.valueOf(this.colors[2]));
  }

  /**
   * Sets the color of the pixels to one color.
   *
   * @param index the index of the color.
   */
  public void setColor(int index) {
    this.colors[0] = this.colors[index];
    this.colors[1] = this.colors[index];
    this.colors[2] = this.colors[index];

  }

  /**
   * Sets the value of the pixels to the overall value.
   */
  public void setValueComponent() {
    int value = this.findValue();
    this.colors[0] = value;
    this.colors[1] = value;
    this.colors[2] = value;
  }

  /**
   * Sets the intensity of the pixels to the overall intensity.
   */
  public void setIntensityComponent() {
    Double doubleIntensity = this.findIntensity();
    int intensity = (int) Math.round(doubleIntensity);
    this.colors[0] = this.clipValue(intensity);
    this.colors[1] = this.clipValue(intensity);
    this.colors[2] = this.clipValue(intensity);
  }

  /**
   * Sets the luma of the pixels to the overall luma.
   */
  public void setLumaComponent() {
    Double doubleLuma = this.findLuma();
    int luma = (int) Math.round(doubleLuma);
    this.colors[0] = this.clipValue(luma);
    this.colors[1] = this.clipValue(luma);
    this.colors[2] = this.clipValue(luma);
  }

  /**
   * Gets the color of the pixel.
   *
   * @param index the index of the pixel.
   * @return the color of the pixel.
   */
  public int getColor(int index) {
    return this.colors[index];
  }

  /**
   * creates a copy of the image.
   *
   * @return the copies image
   */
  @Override
  public Pixel createCopy() {
    return new RGBPixel(this.getColor(0), this.getColor(2), this.getColor(1));
  }

  /**
   * creates a greyscale version of pixel based on its filter.
   */
  @Override
  public void setGreyscale() {
    int newRed = findGreyscale();
    int newGreen = findGreyscale();
    int newBlue = findGreyscale();

    this.colors[0] = newRed;
    this.colors[1] = newGreen;
    this.colors[2] = newBlue;
  }

  /**
   * helper to findGreyscale that returns the greyscale value for a rgb value.
   * @return the greyscale value
   */
  private int findGreyscale() {
    int oldRed = this.colors[0];
    int oldGreen = this.colors[1];
    int oldBlue = this.colors[2];

    Double doubleGreyscale = (oldRed * 0.2126) +
            (oldGreen * 0.7152) +
            (oldBlue * 0.0722);
    return this.clipValue((int) Math.round(doubleGreyscale));
  }

  /**
   *  creates a sepia version of pixel based on its filter.
   */
  @Override
  public void setSepia() {
    int newRed = findSepia(0);
    int newGreen = findSepia(1);
    int newBlue = findSepia(2);

    this.colors[0] = newRed;
    this.colors[1] = newGreen;
    this.colors[2] = newBlue;
  }

  /**
   * a helper to setSepia that finds the sepia values based on the
   * index of the color.
   * @param index of color
   * @return the sepia int value
   */
  private int findSepia(int index) {
    int oldRed = this.colors[0];
    int oldGreen = this.colors[1];
    int oldBlue = this.colors[2];

    double newVal = 0;

    if (index == 0) {
      newVal = (oldRed * 0.393) + (oldGreen * 0.769) + (oldBlue * 0.189);
    }
    else if (index == 1) {
      newVal = (oldRed * 0.349) + (oldGreen * 0.686) + (oldBlue * 0.168);
    }
    else {
      newVal = (oldRed * 0.272) + (oldGreen * 0.534) + (oldBlue * 0.131);
    }

    return this.clipValue((int) Math.round(newVal));

  }

  /**
   * Overrides equals.
   *
   * @param that the Object being compared.
   * @return true if the objects are equal.
   */
  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }

    if (!(that instanceof Pixel)) {
      return false;
    }

    return ((RGBPixel) that).colors[0] == this.colors[0]
            && ((RGBPixel) that).colors[1] == this.colors[1]
            && ((RGBPixel) that).colors[2] == this.colors[2];
  }

  /**
   * Overrides hashcode.
   *
   * @return the hashcode.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.colors[0], this.colors[1], this.colors[2]);
  }

}
