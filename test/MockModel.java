import java.util.Objects;

import model.ImageProcessingModel;
import model.pixel.Pixel;

/**
 * A MockModel created to test the controller in isolation. This returns log
 * values to check correct pass of input.
 */
public class MockModel implements ImageProcessingModel {

  final StringBuilder log;

  /**
   * Makes a MockView by taking a log.
   *
   * @param log a StringBuilder used by the MockView
   */
  public MockModel(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }


  @Override
  public ImageProcessingModel createCopy() {
    return null;
  }

  @Override
  public ImageProcessingModel redComponent() {
    return null;
  }

  @Override
  public ImageProcessingModel greenComponent() {
    return null;
  }

  @Override
  public ImageProcessingModel blueComponent() {
    return null;
  }

  @Override
  public ImageProcessingModel valueComponent() {
    return null;
  }

  @Override
  public ImageProcessingModel intensityComponent() {
    return null;
  }

  @Override
  public ImageProcessingModel lumaComponent() {
    return null;
  }

  @Override
  public ImageProcessingModel flipHorizontal() {
    return null;
  }

  @Override
  public ImageProcessingModel flipVertical() {
    return null;
  }

  @Override
  public ImageProcessingModel brighten(int val) {
    log.append(String.format("brighten by = " + val));
    return null;
  }

  @Override
  public int getHeight() {
    return 0;
  }

  @Override
  public int getWidth() {
    return 0;
  }

  @Override
  public Pixel getPixelAt(int row, int col) {
    log.append(String.format("row = " + row + " col = " + col));
    return null;
  }

  @Override
  public void setPixelAt(int row, int col, Pixel p) {
    log.append(String.format("row = " + row + " col = " + col + " Pixel = " + p));
  }

  @Override
  public int getMax() {
    return 0;
  }
}
