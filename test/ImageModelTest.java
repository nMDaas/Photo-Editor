import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import model.ImageModel;
import model.ImageProcessingModel;
import model.Pixel;

import static org.junit.Assert.assertEquals;

public class ImageModelTest {

  Pixel pixel1;
  Pixel pixel2;
  Pixel pixel3;
  Pixel pixel4;
  Pixel pixel5;
  Pixel pixel6;
  Pixel [][] pixels;
  ImageProcessingModel model;

  @Before
  public void setUp() {
    pixel1 = new Pixel(0, 10, 10);
    pixel2 = new Pixel(10, 0, 0);
    pixel3 = new Pixel(0, 10, 0);
    pixel4 = new Pixel(10, 10, 10);
    pixel5 = new Pixel(5, 10, 7);
    pixel6 = new Pixel(0, 5, 10);

    pixels = new Pixel[2][2];
    pixels[0][0] = pixel1;
    pixels[0][1] = pixel2;
    pixels[1][0] = pixel3;
    pixels[1][1] = pixel4;
    model = new ImageModel(2,2, pixels,10);
  }

  // tests for constructor

  @Test
  public void testConstructor1() {
    ImageProcessingModel model =
            new ImageModel( 10, 10, new Pixel[10][10], 255);

    assertEquals(10, model.getWidth());
    assertEquals(10, model.getHeight());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDisallowsNullPixels() {
    ImageProcessingModel model =
            new ImageModel(10, 10, null, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDisallowsNegHeight() {
    ImageProcessingModel model =
            new ImageModel( -1, 10, new Pixel[10][10], 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDisallowsNegWidth() {
    ImageProcessingModel model =
            new ImageModel( 10, -1, new Pixel[10][10], 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDisallowsNegMax() {
    ImageProcessingModel model =
            new ImageModel( 10, 10, new Pixel[10][10], -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDisallowsInvalidMax() {
    ImageProcessingModel model =
            new ImageModel( 10, 10, new Pixel[10][10], 256);
  }

  // test red-component

  @Test
  public void testRedComponent() {
    ImageProcessingModel modelRed = model.redComponent();

    assertEquals(2, modelRed.getHeight());
    assertEquals(2, modelRed.getWidth());
    assertEquals(10, modelRed.getMax());
    assertEquals(new Pixel(0, 0, 0), modelRed.getPixelAt(0, 0));
    assertEquals(new Pixel(10, 10, 10), modelRed.getPixelAt(0, 1));
    assertEquals(new Pixel(0, 0, 0), modelRed.getPixelAt(1, 0));
    assertEquals(new Pixel(10, 10, 10), modelRed.getPixelAt(1, 1));
  }

  @Test
  public void testGreenComponent() {
    ImageProcessingModel modelGreen = model.greenComponent();

    assertEquals(2, modelGreen.getHeight());
    assertEquals(2, modelGreen.getWidth());
    assertEquals(10, modelGreen.getMax());
    assertEquals(new Pixel(10, 10, 10), modelGreen.getPixelAt(0,0));
    assertEquals(new Pixel(0, 0, 0),modelGreen.getPixelAt(0,1));
    assertEquals(new Pixel(0, 0, 0), modelGreen.getPixelAt(1,0));
    assertEquals(new Pixel(10, 10, 10), modelGreen.getPixelAt(1,1));
  }

  @Test
  public void testBlueComponent() {
    ImageProcessingModel modelBlue = model.blueComponent();

    assertEquals(2, modelBlue.getHeight());
    assertEquals(2, modelBlue.getWidth());
    assertEquals(10, modelBlue.getMax());
    assertEquals(new Pixel(10, 10, 10), modelBlue.getPixelAt(0,0));
    assertEquals(new Pixel(0, 0, 0), modelBlue.getPixelAt(0,1));
    assertEquals(new Pixel(10, 10, 10), modelBlue.getPixelAt(1,0));
    assertEquals(new Pixel(10, 10, 10), modelBlue.getPixelAt(1,1));
  }

  @Test
  public void testValueComponent() {
    ImageProcessingModel modelValue = model.valueComponent();

    assertEquals(2, modelValue.getHeight());
    assertEquals(2, modelValue.getWidth());
    assertEquals(10, modelValue.getMax());
    assertEquals(new Pixel(10, 10, 10), modelValue.getPixelAt(0, 0));
    assertEquals(new Pixel(10, 10, 10), modelValue.getPixelAt(0, 1));
    assertEquals(new Pixel(10, 10, 10), modelValue.getPixelAt(1, 0));
    assertEquals(new Pixel(10, 10, 10), modelValue.getPixelAt(1, 1));
  }

  @Test
  public void testIntensityComponent() {
    ImageProcessingModel modelIntensity = model.intensityComponent();

    assertEquals(2, modelIntensity.getHeight());
    assertEquals(2, modelIntensity.getWidth());
    assertEquals(10, modelIntensity.getMax());
    assertEquals(new Pixel(7, 7, 7), modelIntensity.getPixelAt(0, 0));
    assertEquals(new Pixel(3, 3, 3), modelIntensity.getPixelAt(0, 1));
    assertEquals(new Pixel(3, 3, 3), modelIntensity.getPixelAt(1, 0));
    assertEquals(new Pixel(10, 10, 10),modelIntensity.getPixelAt(1, 1));
  }

  @Test
  public void testLumaComponent() {
    ImageProcessingModel modelLuma = model.lumaComponent();

    assertEquals(modelLuma.getHeight(), 2);
    assertEquals(modelLuma.getWidth(), 2);
    assertEquals(modelLuma.getMax(), 10);
    assertEquals(new Pixel(8, 8, 8), modelLuma.getPixelAt(0, 0));
    assertEquals(new Pixel(2, 2, 2), modelLuma.getPixelAt(0, 1));
    assertEquals(new Pixel(1, 1, 1), modelLuma.getPixelAt(1, 0));
    assertEquals(new Pixel(10, 10, 10), modelLuma.getPixelAt(1, 1));
  }

  @Test
  public void testFlipHorizontalOddWidth() {
    Pixel [][] pixelsOriginal = new Pixel[2][3];
    pixelsOriginal[0][0] = pixel1;
    pixelsOriginal[0][1] = pixel2;
    pixelsOriginal[0][2] = pixel3;
    pixelsOriginal[1][0] = pixel4;
    pixelsOriginal[1][1] = pixel5;
    pixelsOriginal[1][2] = pixel6;

    ImageProcessingModel modelOriginal =
            new ImageModel(2,3, pixelsOriginal,10);


    ImageProcessingModel modelFlipped = modelOriginal.flipHorizontal();

    assertEquals(2, modelFlipped.getHeight());
    assertEquals(3, modelFlipped.getWidth());
    assertEquals(10, modelFlipped.getMax());
    assertEquals(pixel3, modelFlipped.getPixelAt(0, 0));
    assertEquals(pixel2, modelFlipped.getPixelAt(0, 1));
    assertEquals(pixel1, modelFlipped.getPixelAt(0, 2));
    assertEquals(pixel6, modelFlipped.getPixelAt(1, 0));
    assertEquals(pixel5, modelFlipped.getPixelAt(1, 1));
    assertEquals(pixel4, modelFlipped.getPixelAt(1, 2));
  }

  @Test
  public void testFlipHorizontalEvenWidth() {
    ImageProcessingModel modelFlipped = model.flipHorizontal();

    assertEquals(2, modelFlipped.getHeight());
    assertEquals(2, modelFlipped.getWidth());
    assertEquals(10, modelFlipped.getMax());
    assertEquals(pixel2, modelFlipped.getPixelAt(0, 0));
    assertEquals(pixel1, modelFlipped.getPixelAt(0, 1));
    assertEquals(pixel4, modelFlipped.getPixelAt(1, 0));
    assertEquals(pixel3, modelFlipped.getPixelAt(1, 1));
  }


  @Test
  public void testFlipVerticalOddWidth() {
    Pixel [][] pixelsOriginal = new Pixel[3][2];
    pixelsOriginal[0][0] = pixel1;
    pixelsOriginal[0][1] = pixel2;
    pixelsOriginal[1][0] = pixel3;
    pixelsOriginal[1][1] = pixel4;
    pixelsOriginal[2][0] = pixel5;
    pixelsOriginal[2][1] = pixel6;

    ImageProcessingModel modelOriginal =
            new ImageModel(3,2, pixelsOriginal,10);


    ImageProcessingModel modelFlipped = modelOriginal.flipVertical();

    assertEquals(3, modelFlipped.getHeight());
    assertEquals(2, modelFlipped.getWidth());
    assertEquals(10, modelFlipped.getMax());
    assertEquals(pixel5, modelFlipped.getPixelAt(0, 0));
    assertEquals(pixel6, modelFlipped.getPixelAt(0, 1));
    assertEquals(pixel3, modelFlipped.getPixelAt(1, 0));
    assertEquals(pixel4, modelFlipped.getPixelAt(1, 1));
    assertEquals(pixel1, modelFlipped.getPixelAt(2, 0));
    assertEquals(pixel2, modelFlipped.getPixelAt(2, 1));
  }

  @Test
  public void testFlipVerticalEvenWidth() {
    ImageProcessingModel modelFlipped = model.flipVertical();

    assertEquals(2, modelFlipped.getHeight());
    assertEquals(2, modelFlipped.getWidth());
    assertEquals(10, modelFlipped.getMax());
    assertEquals(pixel3, modelFlipped.getPixelAt(0, 0));
    assertEquals(pixel4, modelFlipped.getPixelAt(0, 1));
    assertEquals(pixel1, modelFlipped.getPixelAt(1, 0));
    assertEquals(pixel2, modelFlipped.getPixelAt(1, 1));
  }

  @Test
  public void testBrightenPositive() {
    ImageProcessingModel modelBright = model.brighten(10);

    assertEquals(2, modelBright.getHeight());
    assertEquals(2, modelBright.getWidth());
    assertEquals(20, modelBright.getMax());
    assertEquals(new Pixel(10, 20, 20), modelBright.getPixelAt(0, 0));
    assertEquals(new Pixel(20, 10, 10), modelBright.getPixelAt(0, 1));
    assertEquals(new Pixel(10, 20, 10), modelBright.getPixelAt(1, 0));
    assertEquals(new Pixel(20, 20, 20), modelBright.getPixelAt(1, 1));
  }

  @Test
  public void testBrightenPositive2() {
    ImageProcessingModel modelBright = model.brighten(246);

    assertEquals(2, modelBright.getHeight());
    assertEquals(2, modelBright.getWidth());
    assertEquals(255, modelBright.getMax());
    assertEquals(new Pixel(246, 255, 255), modelBright.getPixelAt(0, 0));
    assertEquals(new Pixel(255, 246, 246), modelBright.getPixelAt(0, 1));
    assertEquals(new Pixel(246, 255, 246), modelBright.getPixelAt(1, 0));
    assertEquals(new Pixel(255, 255, 255), modelBright.getPixelAt(1, 1));
  }

  @Test
  public void testBrightenNegative() {
    ImageProcessingModel modelDark = model.brighten(-5);

    assertEquals(2, modelDark.getHeight());
    assertEquals(2, modelDark.getWidth());
    assertEquals(5, modelDark.getMax());
    assertEquals(new Pixel(0, 5, 5), modelDark.getPixelAt(0, 0));
    assertEquals(new Pixel(5, 0, 0), modelDark.getPixelAt(0, 1));
    assertEquals(new Pixel(0, 5, 0), modelDark.getPixelAt(1, 0));
    assertEquals(new Pixel(5, 5, 5), modelDark.getPixelAt(1, 1));
  }

  @Test
  public void testGetWidth() {
    assertEquals(2, model.getWidth());
  }

  @Test
  public void testGetHeight() {
    assertEquals(2, model.getHeight());
  }

  @Test
  public void testGetMax() {
    assertEquals(10, model.getMax());

    model.brighten(15);
    assertEquals(25, model.getMax());

    model.brighten(-5);
    assertEquals(20, model.getMax());
  }

  @Test
  public void testGetPixels() {
    assertEquals(new Pixel(0, 10, 10), model.getPixelAt(0,0));
    assertEquals(new Pixel(10, 0, 0), model.getPixelAt(0,1));
    assertEquals(new Pixel(0, 10, 0), model.getPixelAt(1,0));
    assertEquals(new Pixel(10, 10, 10), model.getPixelAt(1,1));
  }

}