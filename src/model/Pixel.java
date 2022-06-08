package model;

import java.util.Objects;

public class Pixel {
  private int red;
  private int blue;
  private int green;

  public Pixel(int red, int blue, int green) {
    if (red < 0 || blue < 0 || green < 0) {
      throw new IllegalArgumentException("rgb values cannot be negative.");
    }

    if (red > 255 || blue > 255 || green > 255) {
      throw new IllegalArgumentException("rgb values cannot greater than 255.");
    }
    this.red = red;
    this.blue = blue;
    this.green = green;
  }

  public void brighten(int val){
    this.red = this.setValue(this.red, val);
    this.blue = this.setValue(this.blue, val);
    this.green = this.setValue(this.green, val);
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

  public int findValue() {
    int maxValue = this.red;

    if (this.green > maxValue){
      maxValue = this.green;
    }

    if (this.blue > maxValue){
      maxValue = this.blue;
    }

    return maxValue;
  }

  public double findIntensity() {
    return (this.red + this.blue + this.green)/3.0;
  }

  public double findLuma() {
    return (0.2126 * Double.valueOf(this.red)) +
            (0.7152 * Double.valueOf(this.green)) +
            (0.0722 * Double.valueOf(this.blue));
  }

  public void setRedComponent() {
    this.green = this.red;
    this.blue = this.red;
  }

  public void setGreenComponent() {
    this.red = this.green;
    this.blue = this.green;
  }

  public void setBlueComponent() {
    this.red = this.blue;
    this.green = this.blue;
  }

  public void setValueComponent() {
    int value = this.findValue();
    this.red = value;
    this.green = value;
    this.blue = value;
  }

  public void setIntensityComponent() {
    Double doubleIntensity = this.findIntensity(); // problem here: intensity is double, pixel rgb is int
    int intensity = (int)Math.round(doubleIntensity);
    this.red = intensity;
    this.green = intensity;
    this.blue = intensity;
  }

  public void setLumaComponent() {
    Double doubleLuma = this.findLuma(); // problem here: intensity is double, pixel rgb is int
    int luma = (int)Math.round(doubleLuma);
    this.red = luma;
    this.green = luma;
    this.blue = luma;
  }

  public int getRed(){
    return this.red;
  }

  public int getGreen(){
    return this.green;
  }

  public int getBlue(){
    return this.blue;
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }

    if (!(that instanceof Pixel)) {
      return false;
    }

    return ((Pixel) that).red == this.red
            && ((Pixel) that).green == this.green
            && ((Pixel) that).blue == this.blue;
  }

  @Override
  public int hashCode() {
    return Objects.hash(red, green, blue);
  }

}
