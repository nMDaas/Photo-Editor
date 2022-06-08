package model.pixel;

import java.util.Objects;

public class RGBPixel implements Pixel {
  private int [] colors = new int[3];

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

  public void brighten(int val){
    this.colors[0] = this.setValue(this.colors[0], val);
    this.colors[1] = this.setValue(this.colors[1], val);
    this.colors[2] = this.setValue(this.colors[2], val);
  }

  private int setValue(int currentVal, int valAdd){
    int sum = currentVal + valAdd;
    if (sum > 255){
      return 255;
    }
    else if (sum < 0){
      return 0;
    }
    else {
      return sum;
    }
  }

  private int findValue() {
    int maxValue = this.colors[0];

    if (this.colors[1] > maxValue){
      maxValue = this.colors[1];
    }

    if (this.colors[2] > maxValue){
      maxValue = this.colors[2];
    }

    return maxValue;
  }

  private double findIntensity() {
    return (this.colors[0] + this.colors[1] + this.colors[2])/3.0;
  }

  private double findLuma() {
    return (0.2126 * Double.valueOf(this.colors[0])) +
            (0.7152 * Double.valueOf(this.colors[1])) +
            (0.0722 * Double.valueOf(this.colors[2]));
  }

  public void setColor(int index) {
    this.colors[0] = this.colors[index];
    this.colors[1] = this.colors[index];
    this.colors[2] = this.colors[index];

  }

  public void setValueComponent() {
    int value = this.findValue();
    this.colors[0] = value;
    this.colors[1] = value;
    this.colors[2] = value;
  }

  public void setIntensityComponent() {
    Double doubleIntensity = this.findIntensity(); // problem here: intensity is double, pixel rgb is int
    int intensity = (int)Math.round(doubleIntensity);
    this.colors[0] = intensity;
    this.colors[1] = intensity;
    this.colors[2] = intensity;
  }

  public void setLumaComponent() {
    Double doubleLuma = this.findLuma(); // problem here: intensity is double, pixel rgb is int
    int luma = (int)Math.round(doubleLuma);
    this.colors[0] = luma;
    this.colors[1] = luma;
    this.colors[2] = luma;
  }

  public int getColor(int index) {
    return this.colors[index];
  }

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

  @Override
  public int hashCode() {
    return Objects.hash(this.colors[0], this.colors[1], this.colors[2]);
  }

}
