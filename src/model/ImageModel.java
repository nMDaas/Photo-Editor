package model;

public class ImageModel implements ImageProcessingModel{
  private final String name;
  private final int height;
  private final int width;
  private final Pixel[][] pixels;
  private int max;

  public ImageModel(String name, int height, int width, Pixel[][] pixels, int max) {
    this.name = name;
    this.height = height;
    this.width = width;
    this.pixels = pixels;
    this.max = max;
  }

  @Override
  public ImageProcessingModel redComponent(String dest) {

    ImageModel redImage =
            new ImageModel(dest, this.height, this.width, this.pixels, this.max);

    for (int row = 0; row <= (redImage.height - 1); row = row + 1) {
      for (int col = 0; col <= (redImage.width - 1); col = col + 1) {
        redImage.pixels[row][col].setRedComponent();
      }
    }

    return redImage;

  }

  @Override
  public ImageProcessingModel greenComponent(String dest) {

    ImageModel greenImage =
            new ImageModel(dest, this.height, this.width, this.pixels, this.max);

    for (int row = 0; row <= (greenImage.height - 1); row = row + 1) {
      for (int col = 0; col <= (greenImage.width - 1); col = col + 1) {
        greenImage.pixels[row][col].setGreenComponent();
      }
    }

    return greenImage;

  }

  @Override
  public ImageProcessingModel blueComponent(String dest) {

    ImageModel blueImage =
            new ImageModel(dest, this.height, this.width, this.pixels, this.max);

    for (int row = 0; row <= (blueImage.height - 1); row = row + 1) {
      for (int col = 0; col <= (blueImage.width - 1); col = col + 1) {
        blueImage.pixels[row][col].setBlueComponent();
      }
    }

    return blueImage;
  }

  @Override
  public ImageProcessingModel valueComponent(String dest) {

    ImageModel valueImage =
            new ImageModel(dest, this.height, this.width, this.pixels, this.max);

    for (int row = 0; row <= (valueImage.height - 1); row = row + 1) {
      for (int col = 0; col <= (valueImage.width - 1); col = col + 1) {
        valueImage.pixels[row][col].setValueComponent();
      }
    }

    return valueImage;

  }

  @Override
  public ImageProcessingModel intensityComponent(String dest) {

    ImageModel intensityImage =
            new ImageModel(dest, this.height, this.width, this.pixels, this.max);

    for (int row = 0; row <= (intensityImage.height - 1); row = row + 1) {
      for (int col = 0; col <= (intensityImage.width - 1); col = col + 1) {
        intensityImage.pixels[row][col].setIntensityComponent();
      }
    }

    return intensityImage;

  }

  @Override
  public ImageProcessingModel lumaComponent(String dest) {

    ImageModel lumaImage =
            new ImageModel(dest, this.height, this.width, this.pixels, this.max);

    for (int row = 0; row <= (lumaImage.height - 1); row = row + 1) {
      for (int col = 0; col <= (lumaImage.width - 1); col = col + 1) {
        lumaImage.pixels[row][col].setLumaComponent();
      }
    }

    return lumaImage;

  }

  @Override
  public ImageProcessingModel flipHorizontal(String dest) {

    ImageModel horizontalImage =
            new ImageModel(dest, this.height, this.width, this.pixels, this.max);

    double midpoint = (horizontalImage.width / 2) - 0.5;

    for (int row = 0; row <= (horizontalImage.height - 1); row = row + 1) {
      for (int col = 0; col <= midpoint; col = col + 1) {
        Pixel pixelLeft = horizontalImage.pixels[row][col];
        int colRight = (int) ((int) midpoint + (midpoint - col));
        Pixel pixelRight = horizontalImage.pixels[row][colRight];
        Pixel tempPixel = pixelRight;
        pixelRight = pixelLeft;
        pixelLeft = tempPixel;
      }
    }

    return horizontalImage;

  }

  @Override
  public ImageProcessingModel flipVertical(String dest) {

    ImageModel verticalImage =
            new ImageModel(dest, this.height, this.width, this.pixels, this.max);

    double midpoint = (verticalImage.height / 2) - 0.5;

    for (int row = 0; row <= midpoint; row = row + 1) {
      for (int col = 0; col <= (verticalImage.width - 1); col = col + 1) {
        Pixel pixelUp = verticalImage.pixels[row][col];
        int rowDown = (int) ((int) midpoint + (midpoint - row));
        Pixel pixelDown = verticalImage.pixels[row][rowDown];
        Pixel tempPixel = pixelDown;
        pixelDown = pixelUp;
        pixelUp = tempPixel;
      }
    }

    return verticalImage;

  }

  @Override
  public ImageProcessingModel brighten(int val, String dest) {

    ImageModel brightenImage =
            new ImageModel(dest, this.height, this.width, this.pixels, this.max);

    for (int row = 0; row <= (this.height - 1); row = row + 1) {
      for (int col = 0; col <= (this.width - 1); col = col + 1) {
        this.pixels[row][col].brighten(val);
      }
    }

    return brightenImage;
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
  public Pixel[][] getPixels() {
    return this.pixels;
  }

  @Override
  public int getMax() {
    return this.max;
  }
}
