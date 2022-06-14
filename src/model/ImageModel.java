package model;

import model.commands.ChangeBrightness;
import model.commands.IntensityGreyscale;
import model.commands.LumaGreyscale;
import model.commands.MakeBlue;
import model.commands.MakeGreen;
import model.commands.MakeRed;
import model.commands.PixelWiseProcessor;
import model.commands.SetGreyscale;
import model.commands.SetSepia;
import model.commands.ValueGreyscale;
import model.pixel.Pixel;

/**
 * ImageModel is an implementation of ImageProcessingModel that has a height, width,
 * an array of pixels and a max value. It implements all its inherited methods.
 */
public class ImageModel implements ImageProcessingModel {

  private final int height;
  private final int width;
  private final Pixel[][] pixels;
  private int max;

  /**
   * creates a new ImageModel.
   * @param height image height
   * @param width image width
   * @param pixels image's pixels
   * @param max image's max value
   */
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
    Pixel[][] newPixels = new Pixel[this.height][this.width];

    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
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
    return p.changePixels(this);
  }

  @Override
  public ImageProcessingModel greenComponent() {
    PixelWiseProcessor p = new MakeGreen();
    return p.changePixels(this);
  }

  @Override
  public ImageProcessingModel blueComponent() {
    PixelWiseProcessor p = new MakeBlue();
    return p.changePixels(this);
  }

  @Override
  public ImageProcessingModel valueComponent() {
    PixelWiseProcessor p = new ValueGreyscale();
    return p.changePixels(this);
  }

  @Override
  public ImageProcessingModel intensityComponent() {
    PixelWiseProcessor p = new IntensityGreyscale();
    return p.changePixels(this);
  }

  @Override
  public ImageProcessingModel lumaComponent() {
    PixelWiseProcessor p = new LumaGreyscale();
    return p.changePixels(this);
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
    return p.changePixels(this);
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

  @Override
  public ImageProcessingModel blurImage() {

    ImageProcessingModel blurredImage = this.createCopy();

    for (int row = 0; row <= (blurredImage.getHeight() - 1); row = row + 1) {
      for (int col = 0; col <= (blurredImage.getWidth() - 1); col = col + 1) {
        Posn [] kernel = new Posn[9];
        Posn topLeft = new Posn(row - 1, col - 1, 0.0625);
        kernel[0] = topLeft;
        Posn top = new Posn(row - 1, col, 0.125);
        kernel[1] = top;
        Posn topRight = new Posn(row - 1, col + 1, 0.0625);
        kernel[2] = topRight;
        Posn left = new Posn(row, col - 1, 0.125);
        kernel[3] = left;
        kernel[4] = new Posn(row, col, 0.25);
        Posn right = new Posn(row, col + 1, 0.125);
        kernel[5] = right;
        Posn bottomLeft = new Posn(row + 1, col - 1, 0.0625);
        kernel[6] = bottomLeft;
        Posn bottom = new Posn(row + 1, col, 0.125);
        kernel[7] = bottom;
        Posn bottomRight = new Posn(row + 1, col + 1, 0.0625);
        kernel[8] = bottomRight;

        blurredImage = this.applyMultiples(kernel, blurredImage);
      }
    }

    return blurredImage;
  }

  @Override
  public ImageProcessingModel sharpenImage() {

    ImageProcessingModel sharpImage = this.createCopy();

    for (int row = 0; row <= (sharpImage.getHeight() - 1); row = row + 1) {
      for (int col = 0; col <= (sharpImage.getWidth() - 1); col = col + 1) {
        Posn [] kernel = new Posn[25];
        kernel[0] = new Posn(row - 2, col - 2, -0.125);
        kernel[1] = new Posn(row - 2, col - 1, -0.125);
        kernel[2] = new Posn(row - 2, col, -0.125);
        kernel[3] = new Posn(row - 2, col + 1, -0.125);
        kernel[4] = new Posn(row - 2, col + 2, -0.125);
        kernel[5] = new Posn(row - 1, col - 2, -0.125);
        kernel[6] = new Posn(row - 1, col - 1, 0.25);
        kernel[7] = new Posn(row - 1, col, 0.25);
        kernel[8] = new Posn(row - 1, col + 1, 0.25);
        kernel[9] = new Posn(row - 1, col + 2, -0.125);
        kernel[10] = new Posn(row, col - 2, -0.125);
        kernel[11] = new Posn(row, col - 1, 0.25);
        kernel[12] = new Posn(row, col, 1);
        kernel[13] = new Posn(row, col + 1, 0.25);
        kernel[14] = new Posn(row, col + 2, -0.125);
        kernel[15] = new Posn(row + 1, col - 2, -0.125);
        kernel[16] = new Posn(row + 1, col - 1, 0.25);
        kernel[17] = new Posn(row + 1, col, 0.25);
        kernel[18] = new Posn(row + 1, col + 1, 0.25);
        kernel[19] = new Posn(row + 1, col + 2, -0.125);
        kernel[20] = new Posn(row + 2, col - 2, -0.125);
        kernel[21] = new Posn(row + 2, col - 1, -0.125);
        kernel[22] = new Posn(row + 2, col, -0.125);
        kernel[23] = new Posn(row + 2, col + 1, -0.125);
        kernel[24] = new Posn(row + 2, col + 2, -0.125);

        sharpImage = this.applyMultiples(kernel, sharpImage);
      }
    }

    return sharpImage;
  }

  @Override
  public ImageProcessingModel setGreyscale() {
    PixelWiseProcessor p = new SetGreyscale();
    return p.changePixels(this);
  }

  @Override
  public ImageProcessingModel setSepia() {
    PixelWiseProcessor p = new SetSepia();
    return p.changePixels(this);
  }

  private ImageProcessingModel applyMultiples(Posn [] kernel, ImageProcessingModel model) {
    for (Posn p : kernel) {
      if (p.checkValid(model.getHeight(), model.getWidth())) {
        model.getPixelAt(p.row, p.col).multiplyPixel(p.multiple);
      }
    }

    return model;
  }
}