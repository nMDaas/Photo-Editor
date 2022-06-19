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
import model.pixel.RGBPixel;

/**
 * ImageModel is an implementation of ImageProcessingModel that has a height, width,
 * an array of pixels and a max value. It implements all its inherited methods.
 */
public class ImageModelImpl implements ImageModel {

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
  public ImageModelImpl(int height, int width, Pixel[][] pixels, int max) {
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
  public ImageModel createCopy() {
    Pixel[][] newPixels = new Pixel[this.height][this.width];

    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
        Pixel oldPixel = this.getPixelAt(row, col);
        Pixel newPixel = oldPixel.createCopy();
        newPixels[row][col] = newPixel;
      }
    }

    return new ImageModelImpl(this.height, this.width, newPixels, this.max);
  }

  @Override
  public ImageModel redComponent() {
    PixelWiseProcessor p = new MakeRed();
    return p.changePixels(this);
  }

  @Override
  public ImageModel greenComponent() {
    PixelWiseProcessor p = new MakeGreen();
    return p.changePixels(this);
  }

  @Override
  public ImageModel blueComponent() {
    PixelWiseProcessor p = new MakeBlue();
    return p.changePixels(this);
  }

  @Override
  public ImageModel valueComponent() {
    PixelWiseProcessor p = new ValueGreyscale();
    return p.changePixels(this);
  }

  @Override
  public ImageModel intensityComponent() {
    PixelWiseProcessor p = new IntensityGreyscale();
    return p.changePixels(this);
  }

  @Override
  public ImageModel lumaComponent() {
    PixelWiseProcessor p = new LumaGreyscale();
    return p.changePixels(this);
  }

  @Override
  public ImageModel flipHorizontal() {

    ImageModel horizontalImage = this.createCopy();

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
  public ImageModel flipVertical() {

    ImageModel verticalImage = this.createCopy();

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
  public ImageModel brighten(int val) {
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

  /**
   * created a blurred image.
   * @return blurred image
   */
  @Override
  public ImageModel blurImage() {

    ImageModel blurredImage = this.createCopy();

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

        blurredImage = this.applyKernel(kernel, blurredImage, row, col);
      }
    }

    return blurredImage;
  }

  /**
   * created a sharpened image.
   * @return sharper image
   */
  @Override
  public ImageModel sharpenImage() {

    ImageModel sharpImage = this.createCopy();

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

        sharpImage = this.applyKernel(kernel, sharpImage, row, col);
      }
    }

    return sharpImage;
  }

  /**
   * Creates a model with a greyscale filter.
   * @return the filtered model
   */
  @Override
  public ImageModel setGreyscale() {
    PixelWiseProcessor p = new SetGreyscale();
    return p.changePixels(this);
  }

  /**
   * Creates a model with a sepia filter.
   * @return the filtered model
   */
  @Override
  public ImageModel setSepia() {
    PixelWiseProcessor p = new SetSepia();
    return p.changePixels(this);
  }

  /**
   * applies the kernel to a pixel in the new edited model.
   * @param kernel an array of Posn that contains the surrounding pixels and their multiples
   * @param model the ediiting model
   * @param row the row of the pixel
   * @param col the column of the pixel
   * @return the ImageProcessingModel with the edited pixel
   */
  private ImageModel applyKernel(Posn [] kernel,
                                 ImageModel model,
                                 int row, int col) {
    double runningRed = 0;
    double runningGreen = 0;
    double runningBlue = 0;
    double doubleRed = 0;
    double doubleGreen = 0;
    double doubleBlue = 0;
    for (Posn p : kernel) {
      if (p.isValid(model.getHeight(), model.getWidth())) {
        Pixel pixel = this.getPixelAt(p.row, p.col);
        doubleRed = pixel.getColor(0) * p.multiple;
        doubleGreen = pixel.getColor(1) * p.multiple;
        doubleBlue = pixel.getColor(2) * p.multiple;

        runningRed = runningRed + doubleRed;
        runningGreen = runningGreen + doubleGreen;
        runningBlue = runningBlue + doubleBlue;
      }
    }
    Pixel newPixel = new RGBPixel(0, 0, 0);
    int red = newPixel.clipValue((int) Math.round(runningRed));
    int green = newPixel.clipValue((int) Math.round(runningGreen));
    int blue = newPixel.clipValue((int) Math.round(runningBlue));
    newPixel = new RGBPixel(red, blue, green);
    model.setPixelAt(row, col, newPixel);
    return model;
  }

}