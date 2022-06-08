package model;

public class ImageModel implements ImageProcessingModel{

  private final int height;
  private final int width;
  private final Pixel[][] pixels;
  private int max;

  public ImageModel(int height, int width, Pixel[][] pixels, int max) {
    if (pixels == null){
      throw new IllegalArgumentException();
    }

    if (height < 0 || width < 0 || max < 0 || max > 255){
      throw new IllegalArgumentException();
    }

    this.height = height;
    this.width = width;
    this.pixels = pixels;
    this.max = max;
  }

  @Override
  public ImageProcessingModel redComponent() {

    ImageModel redImage =
            new ImageModel(this.height, this.width, this.pixels, this.max);

    for (int row = 0; row <= (redImage.height - 1); row = row + 1) {
      for (int col = 0; col <= (redImage.width - 1); col = col + 1) {
        redImage.pixels[row][col].setRedComponent();
      }
    }

    return redImage;

  }

  @Override
  public ImageProcessingModel greenComponent() {

    ImageModel greenImage =
            new ImageModel(this.height, this.width, this.pixels, this.max);

    for (int row = 0; row <= (greenImage.height - 1); row = row + 1) {
      for (int col = 0; col <= (greenImage.width - 1); col = col + 1) {
        greenImage.pixels[row][col].setGreenComponent();
      }
    }

    return greenImage;

  }

  @Override
  public ImageProcessingModel blueComponent() {

    ImageModel blueImage =
            new ImageModel( this.height, this.width, this.pixels, this.max);

    for (int row = 0; row <= (blueImage.height - 1); row = row + 1) {
      for (int col = 0; col <= (blueImage.width - 1); col = col + 1) {
        blueImage.pixels[row][col].setBlueComponent();
      }
    }

    return blueImage;
  }

  @Override
  public ImageProcessingModel valueComponent() {

    ImageModel valueImage =
            new ImageModel( this.height, this.width, this.pixels, this.max);

    for (int row = 0; row <= (valueImage.height - 1); row = row + 1) {
      for (int col = 0; col <= (valueImage.width - 1); col = col + 1) {
        valueImage.pixels[row][col].setValueComponent();
      }
    }

    return valueImage;

  }

  @Override
  public ImageProcessingModel intensityComponent() {

    ImageModel intensityImage =
            new ImageModel( this.height, this.width, this.pixels, this.max);

    for (int row = 0; row <= (intensityImage.height - 1); row = row + 1) {
      for (int col = 0; col <= (intensityImage.width - 1); col = col + 1) {
        intensityImage.pixels[row][col].setIntensityComponent();
      }
    }

    return intensityImage;

  }

  @Override
  public ImageProcessingModel lumaComponent() {

    ImageModel lumaImage =
            new ImageModel(this.height, this.width, this.pixels, this.max);

    for (int row = 0; row <= (lumaImage.height - 1); row = row + 1) {
      for (int col = 0; col <= (lumaImage.width - 1); col = col + 1) {
        lumaImage.pixels[row][col].setLumaComponent();
      }
    }

    return lumaImage;

  }

  @Override
  public ImageProcessingModel flipHorizontal() {

    ImageModel horizontalImage =
            new ImageModel(this.height, this.width, this.pixels, this.max);

    double midpoint = (Double.valueOf(horizontalImage.width) / 2.0) - 0.5;

    for (int row = 0; row <= (horizontalImage.height - 1); row = row + 1) {
      for (int col = 0; col <= midpoint; col = col + 1) {
        Pixel pixelLeft = horizontalImage.pixels[row][col];
        int colRight = (int) (midpoint + (midpoint - col));
        Pixel pixelRight = horizontalImage.pixels[row][colRight];
        Pixel tempPixel = pixelRight;
        horizontalImage.pixels[row][colRight] = pixelLeft;
        horizontalImage.pixels[row][col] = tempPixel;
      }
    }

    return horizontalImage;

  }

  @Override
  public ImageProcessingModel flipVertical() {

    ImageModel verticalImage =
            new ImageModel(this.height, this.width, this.pixels, this.max);

    double midpoint = (Double.valueOf(verticalImage.height) / 2.0) - 0.5;

    for (int row = 0; row <= midpoint; row = row + 1) {
      for (int col = 0; col <= (verticalImage.width - 1); col = col + 1) {
        Pixel pixelUp = verticalImage.pixels[row][col];
        int rowDown = (int) (midpoint + (midpoint - row));
        Pixel pixelDown = verticalImage.pixels[rowDown][col];
        Pixel tempPixel = pixelDown;
        verticalImage.pixels[rowDown][col] = pixelUp;
        verticalImage.pixels[row][col] = tempPixel;
      }
    }

    return verticalImage;

  }

  @Override
  public ImageProcessingModel brighten(int val) {

    ImageModel brightenImage =
            new ImageModel(this.height, this.width, this.pixels, this.max);

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
  public Pixel getPixelAt(int row, int col) {
    return this.pixels[row][col];
  }

  @Override
  public int getMax() {
    return this.max;
  }
}
