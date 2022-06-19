import org.junit.Before;
import org.junit.Test;

import model.ImageModelImpl;
import model.ImageModel;
import model.pixel.Pixel;
import model.pixel.RGBPixel;

import static org.junit.Assert.assertEquals;

/**
 * tests for ImageModel.
 */
public class ImageModelTest {

  Pixel pixel1;
  Pixel pixel2;
  Pixel pixel3;
  Pixel pixel4;
  Pixel pixel5;
  Pixel pixel6;
  Pixel[][] pixels;
  ImageModel model;

  @Before
  public void setUp() {
    pixel1 = new RGBPixel(0, 10, 10);
    pixel2 = new RGBPixel(10, 0, 0);
    pixel3 = new RGBPixel(0, 10, 0);
    pixel4 = new RGBPixel(10, 10, 10);
    pixel5 = new RGBPixel(5, 10, 7);
    pixel6 = new RGBPixel(0, 5, 10);

    pixels = new Pixel[2][2];
    pixels[0][0] = pixel1;
    pixels[0][1] = pixel2;
    pixels[1][0] = pixel3;
    pixels[1][1] = pixel4;
    model = new ImageModelImpl(2, 2, pixels, 10);
  }

  // tests for constructor

  @Test
  public void testConstructor1() {
    ImageModel model =
            new ImageModelImpl(10, 10, new Pixel[10][10], 255);

    assertEquals(10, model.getWidth());
    assertEquals(10, model.getHeight());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDisallowsNullPixels() {
    ImageModel model =
            new ImageModelImpl(10, 10, null, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDisallowsNegHeight() {
    ImageModel model =
            new ImageModelImpl(-1, 10, new Pixel[10][10], 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDisallowsNegWidth() {
    ImageModel model =
            new ImageModelImpl(10, -1, new Pixel[10][10], 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDisallowsNegMax() {
    ImageModel model =
            new ImageModelImpl(10, 10, new Pixel[10][10], -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDisallowsInvalidMax() {
    ImageModel model =
            new ImageModelImpl(10, 10, new Pixel[10][10], 256);
  }

  @Test
  public void testCreateCopy() {
    ImageModel copy = model.createCopy();

    assertEquals(2, copy.getHeight());
    assertEquals(2, copy.getWidth());
    assertEquals(10, copy.getMax());
    assertEquals(new RGBPixel(0, 10, 10), copy.getPixelAt(0, 0));
    assertEquals(new RGBPixel(10, 0, 0), copy.getPixelAt(0, 1));
    assertEquals(new RGBPixel(0, 10, 0), copy.getPixelAt(1, 0));
    assertEquals(new RGBPixel(10, 10, 10), copy.getPixelAt(1, 1));

    // check original model is contact
    assertEquals(new RGBPixel(0, 10, 10), model.getPixelAt(0, 0));
    assertEquals(new RGBPixel(10, 0, 0), model.getPixelAt(0, 1));
    assertEquals(new RGBPixel(0, 10, 0), model.getPixelAt(1, 0));
    assertEquals(new RGBPixel(10, 10, 10), model.getPixelAt(1, 1));
  }

  @Test
  public void testRedComponent() {
    ImageModel modelRed = model.redComponent();

    assertEquals(2, modelRed.getHeight());
    assertEquals(2, modelRed.getWidth());
    assertEquals(10, modelRed.getMax());
    assertEquals(new RGBPixel(0, 0, 0), modelRed.getPixelAt(0, 0));
    assertEquals(new RGBPixel(10, 10, 10), modelRed.getPixelAt(0, 1));
    assertEquals(new RGBPixel(0, 0, 0), modelRed.getPixelAt(1, 0));
    assertEquals(new RGBPixel(10, 10, 10), modelRed.getPixelAt(1, 1));

    // check original model is contact
    assertEquals(new RGBPixel(0, 10, 10), model.getPixelAt(0, 0));
    assertEquals(new RGBPixel(10, 0, 0), model.getPixelAt(0, 1));
    assertEquals(new RGBPixel(0, 10, 0), model.getPixelAt(1, 0));
    assertEquals(new RGBPixel(10, 10, 10), model.getPixelAt(1, 1));
  }

  @Test
  public void testGreenComponent() {
    ImageModel modelGreen = model.greenComponent();

    assertEquals(2, modelGreen.getHeight());
    assertEquals(2, modelGreen.getWidth());
    assertEquals(10, modelGreen.getMax());
    assertEquals(new RGBPixel(10, 10, 10), modelGreen.getPixelAt(0, 0));
    assertEquals(new RGBPixel(0, 0, 0), modelGreen.getPixelAt(0, 1));
    assertEquals(new RGBPixel(0, 0, 0), modelGreen.getPixelAt(1, 0));
    assertEquals(new RGBPixel(10, 10, 10), modelGreen.getPixelAt(1, 1));

    // check original model is contact
    assertEquals(new RGBPixel(0, 10, 10), model.getPixelAt(0, 0));
    assertEquals(new RGBPixel(10, 0, 0), model.getPixelAt(0, 1));
    assertEquals(new RGBPixel(0, 10, 0), model.getPixelAt(1, 0));
    assertEquals(new RGBPixel(10, 10, 10), model.getPixelAt(1, 1));
  }

  @Test
  public void testBlueComponent() {
    ImageModel modelBlue = model.blueComponent();

    assertEquals(2, modelBlue.getHeight());
    assertEquals(2, modelBlue.getWidth());
    assertEquals(10, modelBlue.getMax());
    assertEquals(new RGBPixel(10, 10, 10), modelBlue.getPixelAt(0, 0));
    assertEquals(new RGBPixel(0, 0, 0), modelBlue.getPixelAt(0, 1));
    assertEquals(new RGBPixel(10, 10, 10), modelBlue.getPixelAt(1, 0));
    assertEquals(new RGBPixel(10, 10, 10), modelBlue.getPixelAt(1, 1));

    // check original model is contact
    assertEquals(new RGBPixel(0, 10, 10), model.getPixelAt(0, 0));
    assertEquals(new RGBPixel(10, 0, 0), model.getPixelAt(0, 1));
    assertEquals(new RGBPixel(0, 10, 0), model.getPixelAt(1, 0));
    assertEquals(new RGBPixel(10, 10, 10), model.getPixelAt(1, 1));
  }

  @Test
  public void testValueComponent() {
    ImageModel modelValue = model.valueComponent();

    assertEquals(2, modelValue.getHeight());
    assertEquals(2, modelValue.getWidth());
    assertEquals(10, modelValue.getMax());
    assertEquals(new RGBPixel(10, 10, 10), modelValue.getPixelAt(0, 0));
    assertEquals(new RGBPixel(10, 10, 10), modelValue.getPixelAt(0, 1));
    assertEquals(new RGBPixel(10, 10, 10), modelValue.getPixelAt(1, 0));
    assertEquals(new RGBPixel(10, 10, 10), modelValue.getPixelAt(1, 1));

    // check original model is contact
    assertEquals(new RGBPixel(0, 10, 10), model.getPixelAt(0, 0));
    assertEquals(new RGBPixel(10, 0, 0), model.getPixelAt(0, 1));
    assertEquals(new RGBPixel(0, 10, 0), model.getPixelAt(1, 0));
    assertEquals(new RGBPixel(10, 10, 10), model.getPixelAt(1, 1));
  }

  @Test
  public void testIntensityComponent() {
    ImageModel modelIntensity = model.intensityComponent();

    assertEquals(2, modelIntensity.getHeight());
    assertEquals(2, modelIntensity.getWidth());
    assertEquals(10, modelIntensity.getMax());
    assertEquals(new RGBPixel(7, 7, 7), modelIntensity.getPixelAt(0, 0));
    assertEquals(new RGBPixel(3, 3, 3), modelIntensity.getPixelAt(0, 1));
    assertEquals(new RGBPixel(3, 3, 3), modelIntensity.getPixelAt(1, 0));
    assertEquals(new RGBPixel(10, 10, 10), modelIntensity.getPixelAt(1, 1));

    // check original model is contact
    assertEquals(new RGBPixel(0, 10, 10), model.getPixelAt(0, 0));
    assertEquals(new RGBPixel(10, 0, 0), model.getPixelAt(0, 1));
    assertEquals(new RGBPixel(0, 10, 0), model.getPixelAt(1, 0));
    assertEquals(new RGBPixel(10, 10, 10), model.getPixelAt(1, 1));
  }

  @Test
  public void testLumaComponent() {
    ImageModel modelLuma = model.lumaComponent();

    assertEquals(modelLuma.getHeight(), 2);
    assertEquals(modelLuma.getWidth(), 2);
    assertEquals(modelLuma.getMax(), 10);
    assertEquals(new RGBPixel(8, 8, 8), modelLuma.getPixelAt(0, 0));
    assertEquals(new RGBPixel(2, 2, 2), modelLuma.getPixelAt(0, 1));
    assertEquals(new RGBPixel(1, 1, 1), modelLuma.getPixelAt(1, 0));
    assertEquals(new RGBPixel(10, 10, 10), modelLuma.getPixelAt(1, 1));

    // check original model is contact
    assertEquals(new RGBPixel(0, 10, 10), model.getPixelAt(0, 0));
    assertEquals(new RGBPixel(10, 0, 0), model.getPixelAt(0, 1));
    assertEquals(new RGBPixel(0, 10, 0), model.getPixelAt(1, 0));
    assertEquals(new RGBPixel(10, 10, 10), model.getPixelAt(1, 1));
  }

  @Test
  public void testFlipHorizontalOddWidth() {
    Pixel[][] pixelsOriginal = new Pixel[2][3];
    pixelsOriginal[0][0] = pixel1;
    pixelsOriginal[0][1] = pixel2;
    pixelsOriginal[0][2] = pixel3;
    pixelsOriginal[1][0] = pixel4;
    pixelsOriginal[1][1] = pixel5;
    pixelsOriginal[1][2] = pixel6;

    ImageModel modelOriginal =
            new ImageModelImpl(2, 3, pixelsOriginal, 10);


    ImageModel modelFlipped = modelOriginal.flipHorizontal();

    assertEquals(2, modelFlipped.getHeight());
    assertEquals(3, modelFlipped.getWidth());
    assertEquals(10, modelFlipped.getMax());
    assertEquals(pixel3, modelFlipped.getPixelAt(0, 0));
    assertEquals(pixel2, modelFlipped.getPixelAt(0, 1));
    assertEquals(pixel1, modelFlipped.getPixelAt(0, 2));
    assertEquals(pixel6, modelFlipped.getPixelAt(1, 0));
    assertEquals(pixel5, modelFlipped.getPixelAt(1, 1));
    assertEquals(pixel4, modelFlipped.getPixelAt(1, 2));

    // check that original image is intact
    assertEquals(pixel1, modelOriginal.getPixelAt(0, 0));
    assertEquals(pixel2, modelOriginal.getPixelAt(0, 1));
    assertEquals(pixel3, modelOriginal.getPixelAt(0, 2));
    assertEquals(pixel4, modelOriginal.getPixelAt(1, 0));
    assertEquals(pixel5, modelOriginal.getPixelAt(1, 1));
    assertEquals(pixel6, modelOriginal.getPixelAt(1, 2));
  }

  @Test
  public void testFlipHorizontalEvenWidth() {
    ImageModel modelFlipped = model.flipHorizontal();

    assertEquals(2, modelFlipped.getHeight());
    assertEquals(2, modelFlipped.getWidth());
    assertEquals(10, modelFlipped.getMax());
    assertEquals(pixel2, modelFlipped.getPixelAt(0, 0));
    assertEquals(pixel1, modelFlipped.getPixelAt(0, 1));
    assertEquals(pixel4, modelFlipped.getPixelAt(1, 0));
    assertEquals(pixel3, modelFlipped.getPixelAt(1, 1));

    // check original model is contact
    assertEquals(new RGBPixel(0, 10, 10), model.getPixelAt(0, 0));
    assertEquals(new RGBPixel(10, 0, 0), model.getPixelAt(0, 1));
    assertEquals(new RGBPixel(0, 10, 0), model.getPixelAt(1, 0));
    assertEquals(new RGBPixel(10, 10, 10), model.getPixelAt(1, 1));
  }


  @Test
  public void testFlipVerticalOddWidth() {
    Pixel[][] pixelsOriginal = new Pixel[3][2];
    pixelsOriginal[0][0] = pixel1;
    pixelsOriginal[0][1] = pixel2;
    pixelsOriginal[1][0] = pixel3;
    pixelsOriginal[1][1] = pixel4;
    pixelsOriginal[2][0] = pixel5;
    pixelsOriginal[2][1] = pixel6;

    ImageModel modelOriginal =
            new ImageModelImpl(3, 2, pixelsOriginal, 10);


    ImageModel modelFlipped = modelOriginal.flipVertical();

    assertEquals(3, modelFlipped.getHeight());
    assertEquals(2, modelFlipped.getWidth());
    assertEquals(10, modelFlipped.getMax());
    assertEquals(pixel5, modelFlipped.getPixelAt(0, 0));
    assertEquals(pixel6, modelFlipped.getPixelAt(0, 1));
    assertEquals(pixel3, modelFlipped.getPixelAt(1, 0));
    assertEquals(pixel4, modelFlipped.getPixelAt(1, 1));
    assertEquals(pixel1, modelFlipped.getPixelAt(2, 0));
    assertEquals(pixel2, modelFlipped.getPixelAt(2, 1));

    // check original model is contact
    assertEquals(pixel1, modelOriginal.getPixelAt(0, 0));
    assertEquals(pixel2, modelOriginal.getPixelAt(0, 1));
    assertEquals(pixel3, modelOriginal.getPixelAt(1, 0));
    assertEquals(pixel4, modelOriginal.getPixelAt(1, 1));
    assertEquals(pixel5, modelOriginal.getPixelAt(2, 0));
    assertEquals(pixel6, modelOriginal.getPixelAt(2, 1));
  }

  @Test
  public void testFlipVerticalEvenWidth() {
    ImageModel modelFlipped = model.flipVertical();

    assertEquals(2, modelFlipped.getHeight());
    assertEquals(2, modelFlipped.getWidth());
    assertEquals(10, modelFlipped.getMax());
    assertEquals(pixel3, modelFlipped.getPixelAt(0, 0));
    assertEquals(pixel4, modelFlipped.getPixelAt(0, 1));
    assertEquals(pixel1, modelFlipped.getPixelAt(1, 0));
    assertEquals(pixel2, modelFlipped.getPixelAt(1, 1));

    // check original model is contact
    assertEquals(new RGBPixel(0, 10, 10), model.getPixelAt(0, 0));
    assertEquals(new RGBPixel(10, 0, 0), model.getPixelAt(0, 1));
    assertEquals(new RGBPixel(0, 10, 0), model.getPixelAt(1, 0));
    assertEquals(new RGBPixel(10, 10, 10), model.getPixelAt(1, 1));
  }

  @Test
  public void testBrightenPositive() {
    ImageModel modelBright = model.brighten(10);

    assertEquals(2, modelBright.getHeight());
    assertEquals(2, modelBright.getWidth());
    assertEquals(20, modelBright.getMax());
    assertEquals(new RGBPixel(10, 20, 20), modelBright.getPixelAt(0, 0));
    assertEquals(new RGBPixel(20, 10, 10), modelBright.getPixelAt(0, 1));
    assertEquals(new RGBPixel(10, 20, 10), modelBright.getPixelAt(1, 0));
    assertEquals(new RGBPixel(20, 20, 20), modelBright.getPixelAt(1, 1));

    // check original model is contact
    assertEquals(new RGBPixel(0, 10, 10), model.getPixelAt(0, 0));
    assertEquals(new RGBPixel(10, 0, 0), model.getPixelAt(0, 1));
    assertEquals(new RGBPixel(0, 10, 0), model.getPixelAt(1, 0));
    assertEquals(new RGBPixel(10, 10, 10), model.getPixelAt(1, 1));
  }

  @Test
  public void testBrightenPositive2() {
    ImageModel modelBright = model.brighten(246);

    assertEquals(2, modelBright.getHeight());
    assertEquals(2, modelBright.getWidth());
    assertEquals(255, modelBright.getMax());
    assertEquals(new RGBPixel(246, 255, 255), modelBright.getPixelAt(0, 0));
    assertEquals(new RGBPixel(255, 246, 246), modelBright.getPixelAt(0, 1));
    assertEquals(new RGBPixel(246, 255, 246), modelBright.getPixelAt(1, 0));
    assertEquals(new RGBPixel(255, 255, 255), modelBright.getPixelAt(1, 1));

    // check original model is contact
    assertEquals(new RGBPixel(0, 10, 10), model.getPixelAt(0, 0));
    assertEquals(new RGBPixel(10, 0, 0), model.getPixelAt(0, 1));
    assertEquals(new RGBPixel(0, 10, 0), model.getPixelAt(1, 0));
    assertEquals(new RGBPixel(10, 10, 10), model.getPixelAt(1, 1));
  }

  @Test
  public void testBrightenNegative() {
    ImageModel modelDark = model.brighten(-5);

    assertEquals(2, modelDark.getHeight());
    assertEquals(2, modelDark.getWidth());
    assertEquals(5, modelDark.getMax());
    assertEquals(new RGBPixel(0, 5, 5), modelDark.getPixelAt(0, 0));
    assertEquals(new RGBPixel(5, 0, 0), modelDark.getPixelAt(0, 1));
    assertEquals(new RGBPixel(0, 5, 0), modelDark.getPixelAt(1, 0));
    assertEquals(new RGBPixel(5, 5, 5), modelDark.getPixelAt(1, 1));

    // check original model is contact
    assertEquals(new RGBPixel(0, 10, 10), model.getPixelAt(0, 0));
    assertEquals(new RGBPixel(10, 0, 0), model.getPixelAt(0, 1));
    assertEquals(new RGBPixel(0, 10, 0), model.getPixelAt(1, 0));
    assertEquals(new RGBPixel(10, 10, 10), model.getPixelAt(1, 1));
  }

  @Test
  public void testBlurLowerBoundary() {
    Pixel[][] pixelsOriginal = new Pixel[2][3];
    pixelsOriginal[0][0] = new RGBPixel(0, 0, 0);
    pixelsOriginal[0][1] = new RGBPixel(0, 0, 0);
    pixelsOriginal[0][2] = pixel3;
    pixelsOriginal[1][0] = pixel4;
    pixelsOriginal[1][1] = pixel5;
    pixelsOriginal[1][2] = pixel6;

    ImageModel modelOriginal =
            new ImageModelImpl(2, 3, pixelsOriginal, 10);

    ImageModel blurredModel = modelOriginal.blurImage();

    assertEquals(new RGBPixel(2, 2, 2), blurredModel.getPixelAt(0, 0));
    assertEquals(new RGBPixel(1, 3, 2), blurredModel.getPixelAt(0, 1));
    assertEquals(new RGBPixel(0, 4, 2), blurredModel.getPixelAt(0, 2));
    assertEquals(new RGBPixel(3, 4, 3), blurredModel.getPixelAt(1, 0));
    assertEquals(new RGBPixel(3, 5, 4), blurredModel.getPixelAt(1, 1));
    assertEquals(new RGBPixel(1, 4, 3), blurredModel.getPixelAt(1, 2));

    // check original model is contact
    assertEquals(new RGBPixel(0, 0, 0), modelOriginal.getPixelAt(0, 0));
    assertEquals(new RGBPixel(0, 0, 0), modelOriginal.getPixelAt(0, 1));
    assertEquals(pixel3, modelOriginal.getPixelAt(0, 2));
    assertEquals(pixel4, modelOriginal.getPixelAt(1, 0));
    assertEquals(pixel5, modelOriginal.getPixelAt(1, 1));
    assertEquals(pixel6, modelOriginal.getPixelAt(1, 2));

  }

  @Test
  public void testBlurUpperBoundary() {
    Pixel[][] pixelsOriginal = new Pixel[3][3];
    pixelsOriginal[0][0] = new RGBPixel(255, 255, 255);
    pixelsOriginal[0][1] = new RGBPixel(255, 255, 255);
    pixelsOriginal[0][2] = new RGBPixel(255, 255, 255);
    pixelsOriginal[1][0] = new RGBPixel(255, 255, 255);
    pixelsOriginal[1][1] = new RGBPixel(255, 255, 255);
    pixelsOriginal[1][2] = new RGBPixel(255, 255, 255);
    pixelsOriginal[2][0] = new RGBPixel(255, 255, 255);
    pixelsOriginal[2][1] = new RGBPixel(255, 255, 255);
    pixelsOriginal[2][2] = new RGBPixel(255, 255, 255);

    ImageModel modelOriginal =
            new ImageModelImpl(3, 3, pixelsOriginal, 10);

    ImageModel blurredModel = modelOriginal.blurImage();

    assertEquals(new RGBPixel(143, 143, 143), blurredModel.getPixelAt(0, 0));
    assertEquals(new RGBPixel(191, 191, 191), blurredModel.getPixelAt(0, 1));
    assertEquals(new RGBPixel(143, 143, 143), blurredModel.getPixelAt(0, 2));
    assertEquals(new RGBPixel(191, 191, 191), blurredModel.getPixelAt(1, 0));
    assertEquals(new RGBPixel(255, 255, 255), blurredModel.getPixelAt(1, 1));
    assertEquals(new RGBPixel(191, 191, 191), blurredModel.getPixelAt(1, 2));
    assertEquals(new RGBPixel(143, 143, 143), blurredModel.getPixelAt(2, 0));
    assertEquals(new RGBPixel(191, 191, 191), blurredModel.getPixelAt(2, 1));
    assertEquals(new RGBPixel(143, 143, 143), blurredModel.getPixelAt(2, 2));

    // check original model is contact
    assertEquals(new RGBPixel(255, 255, 255), modelOriginal.getPixelAt(0, 0));
    assertEquals(new RGBPixel(255, 255, 255), modelOriginal.getPixelAt(0, 1));
    assertEquals(new RGBPixel(255, 255, 255), modelOriginal.getPixelAt(0, 2));
    assertEquals(new RGBPixel(255, 255, 255), modelOriginal.getPixelAt(1, 0));
    assertEquals(new RGBPixel(255, 255, 255), modelOriginal.getPixelAt(1, 1));
    assertEquals(new RGBPixel(255, 255, 255), modelOriginal.getPixelAt(1, 2));
    assertEquals(new RGBPixel(255, 255, 255), modelOriginal.getPixelAt(2, 0));
    assertEquals(new RGBPixel(255, 255, 255), modelOriginal.getPixelAt(2, 1));
    assertEquals(new RGBPixel(255, 255, 255), modelOriginal.getPixelAt(2, 2));

  }

  @Test
  public void testSharpen() {
    Pixel[][] pixelsOriginal = new Pixel[3][4];
    pixelsOriginal[0][0] = pixel1;
    pixelsOriginal[0][1] = pixel2;
    pixelsOriginal[0][2] = pixel3;
    pixelsOriginal[0][3] = pixel4;
    pixelsOriginal[1][0] = pixel5;
    pixelsOriginal[1][1] = pixel6;
    pixelsOriginal[1][2] = new RGBPixel(10, 11, 11);
    pixelsOriginal[1][3] = new RGBPixel(30, 30, 200);
    pixelsOriginal[2][0] = new RGBPixel(50, 11, 11);
    pixelsOriginal[2][1] = new RGBPixel(10, 255, 11);
    pixelsOriginal[2][2] = new RGBPixel(255, 11, 16);
    pixelsOriginal[2][3] = new RGBPixel(10, 11, 11);

    ImageModel modelOriginal =
            new ImageModelImpl(3, 4, pixelsOriginal, 255);

    ImageModel sharpModel = modelOriginal.sharpenImage();

    assertEquals(new RGBPixel(0, 0, 8), sharpModel.getPixelAt(0, 0));
    assertEquals(new RGBPixel(0, 0, 0), sharpModel.getPixelAt(0, 1));
    assertEquals(new RGBPixel(0, 0, 50), sharpModel.getPixelAt(0, 2));
    assertEquals(new RGBPixel(0, 0, 57), sharpModel.getPixelAt(0, 3));
    assertEquals(new RGBPixel(0, 76, 14), sharpModel.getPixelAt(1, 0));
    assertEquals(new RGBPixel(79, 78, 0), sharpModel.getPixelAt(1, 1));
    assertEquals(new RGBPixel(84, 90, 72), sharpModel.getPixelAt(1, 2));
    assertEquals(new RGBPixel(99, 11, 209), sharpModel.getPixelAt(1, 3));
    assertEquals(new RGBPixel(19, 73, 13), sharpModel.getPixelAt(2, 0));
    assertEquals(new RGBPixel(83, 255, 0), sharpModel.getPixelAt(2, 1));
    assertEquals(new RGBPixel(255, 83, 72), sharpModel.getPixelAt(2, 2));
    assertEquals(new RGBPixel(80, 0, 64), sharpModel.getPixelAt(2, 3));


    // check original model is contact
    assertEquals(pixel1, modelOriginal.getPixelAt(0, 0));
    assertEquals(pixel2, modelOriginal.getPixelAt(0, 1));
    assertEquals(pixel3, modelOriginal.getPixelAt(0, 2));
    assertEquals(pixel4, modelOriginal.getPixelAt(0, 3));
    assertEquals(pixel5, modelOriginal.getPixelAt(1, 0));
    assertEquals(pixel6, modelOriginal.getPixelAt(1, 1));
    assertEquals(new RGBPixel(10, 11, 11), modelOriginal.getPixelAt(1, 2));
    assertEquals(new RGBPixel(30, 30, 200), modelOriginal.getPixelAt(1, 3));
    assertEquals(new RGBPixel(50, 11, 11), modelOriginal.getPixelAt(2, 0));
    assertEquals(new RGBPixel(10, 255, 11), modelOriginal.getPixelAt(2, 1));
    assertEquals(new RGBPixel(255, 11, 16), modelOriginal.getPixelAt(2, 2));
    assertEquals(new RGBPixel(10, 11, 11), modelOriginal.getPixelAt(2, 3));

  }

  @Test
  public void testSetGreyscale() {
    ImageModel modelGreyscale = model.setGreyscale();

    assertEquals(2, modelGreyscale.getHeight());
    assertEquals(2, modelGreyscale.getWidth());
    assertEquals(10, modelGreyscale.getMax());
    assertEquals(new RGBPixel(8, 8, 8), modelGreyscale.getPixelAt(0, 0));
    assertEquals(new RGBPixel(2, 2, 2), modelGreyscale.getPixelAt(0, 1));
    assertEquals(new RGBPixel(1, 1, 1), modelGreyscale.getPixelAt(1, 0));
    assertEquals(new RGBPixel(10, 10, 10), modelGreyscale.getPixelAt(1, 1));


    assertEquals(new RGBPixel(8, 8, 8), modelGreyscale.getPixelAt(0, 0));
    assertEquals(new RGBPixel(2, 2, 2), modelGreyscale.getPixelAt(0, 1));
    assertEquals(new RGBPixel(1, 1, 1), modelGreyscale.getPixelAt(1, 0));
    assertEquals(new RGBPixel(10, 10, 10), modelGreyscale.getPixelAt(1, 1));

    // check original model is contact
    assertEquals(new RGBPixel(0, 10, 10), model.getPixelAt(0, 0));
    assertEquals(new RGBPixel(10, 0, 0), model.getPixelAt(0, 1));
    assertEquals(new RGBPixel(0, 10, 0), model.getPixelAt(1, 0));
    assertEquals(new RGBPixel(10, 10, 10), model.getPixelAt(1, 1));
  }

  @Test
  public void testSetSepia() {
    ImageModel modelSepia = model.setSepia();

    assertEquals(modelSepia.getHeight(), 2);
    assertEquals(modelSepia.getWidth(), 2);
    assertEquals(modelSepia.getMax(), 14);
    assertEquals(new RGBPixel(10, 7, 9), modelSepia.getPixelAt(0, 0));
    assertEquals(new RGBPixel(4, 3, 3), modelSepia.getPixelAt(0, 1));
    assertEquals(new RGBPixel(2, 1, 2), modelSepia.getPixelAt(1, 0));
    assertEquals(new RGBPixel(14, 9, 12), modelSepia.getPixelAt(1, 1));

    // check original model is contact
    assertEquals(new RGBPixel(0, 10, 10), model.getPixelAt(0, 0));
    assertEquals(new RGBPixel(10, 0, 0), model.getPixelAt(0, 1));
    assertEquals(new RGBPixel(0, 10, 0), model.getPixelAt(1, 0));
    assertEquals(new RGBPixel(10, 10, 10), model.getPixelAt(1, 1));
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

    ImageModel brighter = model.brighten(15);
    assertEquals(25, brighter.getMax());

    ImageModel darker = brighter.brighten(-5);
    assertEquals(20, darker.getMax());
  }

  @Test
  public void testGetPixelAt() {
    assertEquals(new RGBPixel(0, 10, 10), model.getPixelAt(0, 0));
    assertEquals(new RGBPixel(10, 0, 0), model.getPixelAt(0, 1));
    assertEquals(new RGBPixel(0, 10, 0), model.getPixelAt(1, 0));
    assertEquals(new RGBPixel(10, 10, 10), model.getPixelAt(1, 1));
  }

  @Test
  public void testSetPixelAt() {
    model.setPixelAt(0, 0, pixel1);
    model.setPixelAt(0, 1, pixel1);
    model.setPixelAt(1, 0, pixel1);
    model.setPixelAt(1, 1, pixel1);
    assertEquals(new RGBPixel(0, 10, 10), model.getPixelAt(0, 0));
    assertEquals(new RGBPixel(0, 10, 10), model.getPixelAt(0, 1));
    assertEquals(new RGBPixel(0, 10, 10), model.getPixelAt(1, 0));
    assertEquals(new RGBPixel(0, 10, 10), model.getPixelAt(1, 1));
  }

}