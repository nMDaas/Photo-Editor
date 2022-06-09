package model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import model.commands.ChangeBrightness;
import model.commands.IntensityGreyscale;
import model.commands.LumaGreyscale;
import model.commands.MakeBlue;
import model.commands.MakeGreen;
import model.commands.MakeRed;
import model.commands.ValueGreyscale;
import model.commands.ModelCommand;
import model.pixel.Pixel;

public class ImageModel implements ImageProcessingModel {

  private final int height;
  private final int width;
  private final Pixel[][] pixels;
  private int max;

  Map<String, Function<Integer, ModelCommand>> imageCommands = new HashMap<>();

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

    imageCommands.put("makeRed",
            scan -> new MakeRed());
    imageCommands.put("makeBlue",
            scan -> new MakeBlue());
    imageCommands.put("makeGreen",
            scan -> new MakeGreen());
    imageCommands.put("valueGreyscale",
            scan -> new ValueGreyscale());
    imageCommands.put("lumaGreyscale",
            scan -> new LumaGreyscale());
    imageCommands.put("intensityGreyscale",
            scan -> new IntensityGreyscale());
    imageCommands.put("brighten",
            scan -> new ChangeBrightness(scan));

  }

  @Override
  public ImageProcessingModel redComponent() {
    Function<Integer, ModelCommand> cmd =
            imageCommands.getOrDefault("makeRed", null);
    ModelCommand c = cmd.apply(0);
    return c.go(this);
  }

  @Override
  public ImageProcessingModel greenComponent() {
    Function<Integer, ModelCommand> cmd =
            imageCommands.getOrDefault("makeGreen", null);
    ModelCommand c = cmd.apply(0);
    return c.go(this);
  }

  @Override
  public ImageProcessingModel blueComponent() {
    Function<Integer, ModelCommand> cmd =
            imageCommands.getOrDefault("makeBlue", null);
    ModelCommand c = cmd.apply(0);
    return c.go(this);
  }

  @Override
  public ImageProcessingModel valueComponent() {
    Function<Integer, ModelCommand> cmd =
            imageCommands.getOrDefault("valueGreyscale", null);
    ModelCommand c = cmd.apply(0);
    return c.go(this);
  }

  @Override
  public ImageProcessingModel intensityComponent() {
    Function<Integer, ModelCommand> cmd =
            imageCommands.getOrDefault("intensityGreyscale", null);
    ModelCommand c = cmd.apply(0);
    return c.go(this);
  }

  @Override
  public ImageProcessingModel lumaComponent() {
    Function<Integer, ModelCommand> cmd =
            imageCommands.getOrDefault("lumaGreyscale", null);
    ModelCommand c = cmd.apply(0);
    return c.go(this);
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
    Function<Integer, ModelCommand> cmd =
            imageCommands.getOrDefault("brighten", null);
    ModelCommand c = cmd.apply(val);
    return c.go(this);
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