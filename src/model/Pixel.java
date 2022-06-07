package model;

public class Pixel {
  private int red;
  private int blue;
  private int green;

  public Pixel(int red, int blue, int green) {
    this.red = red;
    this.blue = blue;
    this.green = green;
  }

  public void brighten(int val){

    int redNew = this.red + val;
    int blueNew = this.blue + val;
    int greenNew = this.green + val;

    if (redNew > 255){
      redNew = 255;
    }

    if (redNew < 0){
      redNew = 0;
    }

    if (blueNew > 255){
      blueNew = 255;
    }

    if (blueNew < 0){
      blueNew = 0;
    }

    if (greenNew > 255){
      greenNew = 255;
    }

    if (greenNew < 0){
      greenNew = 0;
    }

    this.red = redNew;
    this.blue = blueNew;
    this.green = greenNew;
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
    return (this.red + this.blue + this.green)/3;
  }

  public double findLuma() {
    return (0.2126 * this.red) + (0.7152 * this.green) + (0.0722 * this.blue);
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
    int intensity = doubleIntensity.intValue();
    this.red = intensity;
    this.green = intensity;
    this.blue = intensity;
  }

  public void setLumaComponent() {
    Double doubleLuma = this.findLuma(); // problem here: intensity is double, pixel rgb is int
    int luma = doubleLuma.intValue();
    this.red = luma;
    this.green = luma;
    this.blue = luma;
  }

}
