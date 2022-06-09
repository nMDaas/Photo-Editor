package model;

import model.commands.ChangeBrightness;
import model.commands.IntensityGreyscale;
import model.commands.LumaGreyscale;
import model.commands.MakeBlue;
import model.commands.MakeGreen;
import model.commands.MakeRed;
import model.commands.PixelWiseProcessor;
import model.commands.ValueGreyscale;
import model.pixel.Pixel;

public class ImageModel implements ImageProcessingModel {

  private final int height;
  private final int width;
  private final Pixel[][] pixels;
  private int max;

  public ImageModel(int height, int width, Pixel[][] pixels, int max) {
    if (pixels == null) {
      throw new IllegalArgumentException();
    }

    if (height < 0 || width < 0 || max < 0 || max > 255) {
      throw new IllegalArgumentException();
    }

    this.height = height;
    this.width = width;
    this.pixels = pixels;
    this.max = max;
  }

  @Override
  public ImageProcessingModel createCopy() {
    Pixel [][] newPixels = new Pixel [this.height][this.width];

    for (int row = 0; row < this.height; row ++) {
      for (int col = 0; col < this.width; col ++) {
        Pixel oldPixel = this.getPixelAt(row, col);
        Pixel newPixel = oldPixel.createCopy();
        newPixels[row][col] = newPixel;
      }
    }

    return new ImageModel(this.height, this.width, newPixels, this.max);
  }

  @Override
  public ImageProcessingModel redComponent() {
    PixelWiseProcessor p = new MakeRed();
    return p.go(this);
  }

  @Override
  public ImageProcessingModel greenComponent() {
    PixelWiseProcessor p = new MakeGreen();
    return p.go(this);
  }

  @Override
  public ImageProcessingModel blueComponent() {
    PixelWiseProcessor p = new MakeBlue();
    return p.go(this);
  }

  @Override
  public ImageProcessingModel valueComponent() {
    PixelWiseProcessor p = new ValueGreyscale();
    return p.go(this);
  }

  @Override
  public ImageProcessingModel intensityComponent() {
    PixelWiseProcessor p = new IntensityGreyscale();
    return p.go(this);
  }

  @Override
  public ImageProcessingModel lumaComponent() {
    PixelWiseProcessor p = new LumaGreyscale();
    return p.go(this);
  }

  @Override
  public ImageProcessingModel flipHorizontal() {

    ImageProcessingModel horizontalImage = this.createCopy();

    double midpoint = (Double.valueOf(horizontalImage.getWidth()) / 2.0) - 0.5;

    for (int row = 0; row <= (horizontalImage.getHeight() - 1); row = row + 1) {
      for (int col = 0; col <= midpoint; col = col + 1) {
        Pixel pixelLeft = horizontalImage.getPixelAt(row, col);
        int colRight = (int) (midpoint + (midpoint - col));
        Pixel pixelRight = horizontalImage.getPixelAt(row, colRight);
        Pixel tempPixel = pixelRight;
        horizontalImage.setPixelAt(row, colRight, pixelLeft.createCopy());
        horizontalImage.setPixelAt(row, col, tempPixel.createCopy());
      }
    }

    return horizontalImage;

  }

  @Override
  public ImageProcessingModel flipVertical() {

    ImageProcessingModel verticalImage = this.createCopy();

    double midpoint = (Double.valueOf(verticalImage.getHeight()) / 2.0) - 0.5;

    for (int row = 0; row <= midpoint; row = row + 1) {
      for (int col = 0; col <= (verticalImage.getWidth() - 1); col = col + 1) {
        Pixel pixelUp = verticalImage.getPixelAt(row, col);
        int rowDown = (int) (midpoint + (midpoint - row));
        Pixel pixelDown = verticalImage.getPixelAt(rowDown, col);
        Pixel tempPixel = pixelDown;
        verticalImage.setPixelAt(rowDown, col, pixelUp.createCopy());
        verticalImage.setPixelAt(row, col, tempPixel.createCopy());
      }
    }

    return verticalImage;

  }

  @Override
  public ImageProcessingModel brighten(int val) {
    PixelWiseProcessor p = new ChangeBrightness(val);
    return p.go(this);
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public Pixel getPixelAt(int row, int col) {
    return this.pixels[row][col];
  }

  @Override
  public void setPixelAt(int row, int col, Pixel p) {
    this.pixels[row][col] = p;
  }

  @Override
  public int getMax() {

    int max = -1;

    for (int row = 0; row <= (this.height - 1); row = row + 1) {
      for (int col = 0; col <= (this.width - 1); col = col + 1) {
        Pixel thePixel = this.pixels[row][col];
        if (thePixel.getColor(0) > max) {
          max = thePixel.getColor(0);
        }
        if (thePixel.getColor(1) > max) {
          max = thePixel.getColor(1);
        }
        if (thePixel.getColor(2) > max) {
          max = thePixel.getColor(2);
        }
      }
    }

    return max;
  }
}