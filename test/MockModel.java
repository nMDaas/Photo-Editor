import java.util.Objects;

import model.ImageModel;
import model.pixel.Pixel;

/**
 * A MockModel created to test the controller in isolation. This returns log
 * values to check correct pass of input.
 */
public class MockModel implements ImageModel {

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
  public ImageModel createCopy() {
    return null;
  }

  @Override
  public ImageModel redComponent() {
    return null;
  }

  @Override
  public ImageModel greenComponent() {
    return null;
  }

  @Override
  public ImageModel blueComponent() {
    return null;
  }

  @Override
  public ImageModel valueComponent() {
    return null;
  }

  @Override
  public ImageModel intensityComponent() {
    return null;
  }

  @Override
  public ImageModel lumaComponent() {
    return null;
  }

  @Override
  public ImageModel flipHorizontal() {
    return null;
  }

  @Override
  public ImageModel flipVertical() {
    return null;
  }

  @Override
  public ImageModel brighten(int val) {
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

  @Override
  public ImageModel blurImage() {
    return null;
  }

  @Override
  public ImageModel sharpenImage() {
    return null;
  }

  @Override
  public ImageModel setGreyscale() {
    return null;
  }

  @Override
  public ImageModel setSepia() {
    log.append("Sepia called");
    return null;
  }
}
