import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.util.Map;

import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import model.ImageModel;
import model.ImageProcessingModel;
import model.pixel.Pixel;
import model.pixel.RGBPixel;
import view.ImageProcessingView;
import view.ImageProcessingViewImpl;

import static org.junit.Assert.*;

public class ImageProcessingControllerImplTest {

  ImageProcessingModel fourBy4;
  Pixel blackLuma;
  Pixel greenPixel;
  Pixel pinkLuma;
  Pixel [][] fourBy4Pixels;

  @Before
  public void init() {
    blackLuma = new RGBPixel(0, 0, 0);
    greenPixel = new RGBPixel(0, 119, 255);
    pinkLuma = new RGBPixel(255, 255, 0);

    fourBy4Pixels = new Pixel[4][4];
    fourBy4Pixels[0][0] = blackLuma;
    fourBy4Pixels[0][1] = blackLuma;
    fourBy4Pixels[0][2] = blackLuma;
    fourBy4Pixels[0][3] = pinkLuma;
    fourBy4Pixels[1][0] = blackLuma;
    fourBy4Pixels[1][1] = greenPixel;
    fourBy4Pixels[1][2] = blackLuma;
    fourBy4Pixels[1][3] = blackLuma;
    fourBy4Pixels[2][0] = blackLuma;
    fourBy4Pixels[2][1] = blackLuma;
    fourBy4Pixels[2][2] = greenPixel;
    fourBy4Pixels[2][3] = blackLuma;
    fourBy4Pixels[3][0] = pinkLuma;
    fourBy4Pixels[3][1] = blackLuma;
    fourBy4Pixels[3][2] = blackLuma;
    fourBy4Pixels[3][3] = blackLuma;

    fourBy4 = new ImageModel(4, 4, fourBy4Pixels, 255);
  }

  // general tests
  @Test
  public void testInvalidCommand() {
    Reader in = new StringReader("3 pics/test4x4.ppm test");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 4);
    assertEquals("Invalid command.", outArray[0]);
    assertEquals("Invalid command.", outArray[1]);
    assertEquals("Invalid command.", outArray[2]);

  }

  // tests for valueComponent

  @Test
  public void testValueComponent() {
    Reader in = new StringReader("load res/test4x4.ppm test value-component test value\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();

    Pixel blackValue = new RGBPixel(0, 0, 0);
    Pixel pinkValue = new RGBPixel(255, 255, 255);
    Pixel greenValue = new RGBPixel(255, 255, 255);

    Map<String, ImageProcessingModel> images = c.getImages();
    assertEquals(4, images.get("value").getHeight());
    assertEquals(4, images.get("value").getWidth());
    assertEquals(255, images.get("value").getMax());
    assertEquals(blackValue, images.get("value").getPixelAt(0, 0));
    assertEquals(blackValue, images.get("value").getPixelAt(0, 1));
    assertEquals(blackValue, images.get("value").getPixelAt(0, 2));
    assertEquals(pinkValue, images.get("value").getPixelAt(0, 3));
    assertEquals(blackValue, images.get("value").getPixelAt(1, 0));
    assertEquals(greenValue, images.get("value").getPixelAt(1, 1));
    assertEquals(blackValue, images.get("value").getPixelAt(1, 2));
    assertEquals(blackValue, images.get("value").getPixelAt(1, 3));
    assertEquals(blackValue, images.get("value").getPixelAt(2, 0));
    assertEquals(blackValue, images.get("value").getPixelAt(2, 1));
    assertEquals(greenValue, images.get("value").getPixelAt(2, 2));
    assertEquals(blackValue, images.get("value").getPixelAt(2, 3));
    assertEquals(pinkValue, images.get("value").getPixelAt(3, 0));
    assertEquals(blackValue, images.get("value").getPixelAt(3, 1));
    assertEquals(blackValue, images.get("value").getPixelAt(3, 2));
    assertEquals(blackValue, images.get("value").getPixelAt(3, 3));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testValueInvalidImage() {
    Reader in = new StringReader("load pics/invalid.ppm test value-component"
            + " test value\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
  }

  @Test
  public void testValueInvalidNoMoreInput() {
    Reader in = new StringReader("load res/test4x4.ppm test value-component test\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("More input required.", finalOut);
  }

  // tests for intensityComponent()

  @Test
  public void testIntensityComponent() {
    Reader in = new StringReader("load res/test4x4.ppm test intensity-component test intensity\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();

    Pixel blackIntensity = new RGBPixel(0, 0, 0);
    Pixel pinkIntensity = new RGBPixel(170, 170, 170);
    Pixel greenIntensity = new RGBPixel(125, 125, 125);

    Map<String, ImageProcessingModel> images = c.getImages();
    assertEquals(4, images.get("intensity").getHeight());
    assertEquals(4, images.get("intensity").getWidth());
    assertEquals(170, images.get("intensity").getMax());
    assertEquals(blackIntensity, images.get("intensity").getPixelAt(0, 0));
    assertEquals(blackIntensity, images.get("intensity").getPixelAt(0, 1));
    assertEquals(blackIntensity, images.get("intensity").getPixelAt(0, 2));
    assertEquals(pinkIntensity, images.get("intensity").getPixelAt(0, 3));
    assertEquals(blackIntensity, images.get("intensity").getPixelAt(1, 0));
    assertEquals(greenIntensity, images.get("intensity").getPixelAt(1, 1));
    assertEquals(blackIntensity, images.get("intensity").getPixelAt(1, 2));
    assertEquals(blackIntensity, images.get("intensity").getPixelAt(1, 3));
    assertEquals(blackIntensity, images.get("intensity").getPixelAt(2, 0));
    assertEquals(blackIntensity, images.get("intensity").getPixelAt(2, 1));
    assertEquals(greenIntensity, images.get("intensity").getPixelAt(2, 2));
    assertEquals(blackIntensity, images.get("intensity").getPixelAt(2, 3));
    assertEquals(pinkIntensity, images.get("intensity").getPixelAt(3, 0));
    assertEquals(blackIntensity, images.get("intensity").getPixelAt(3, 1));
    assertEquals(blackIntensity, images.get("intensity").getPixelAt(3, 2));
    assertEquals(blackIntensity, images.get("intensity").getPixelAt(3, 3));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testIntensityInvalidImage() {
    Reader in = new StringReader("load res/invalid.ppm test intensity-component"
            + " test intensity\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
  }

  @Test
  public void testIntensityInvalidNoMoreInput() {
    Reader in = new StringReader("load res/test4x4.ppm test intensity-component test\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("More input required.", finalOut);
  }

  // tests for lumaComponent()

  @Test
  public void testLumaComponent() {
    Reader in = new StringReader("load res/test4x4.ppm test luma-component test luma\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();

    Pixel blackLuma = new RGBPixel(0, 0, 0);
    Pixel pinkLuma = new RGBPixel(73, 73, 73);
    Pixel greenLuma = new RGBPixel(191, 191, 191);

    Map<String, ImageProcessingModel> images = c.getImages();
    assertEquals(4, images.get("luma").getHeight());
    assertEquals(4, images.get("luma").getWidth());
    assertEquals(191, images.get("luma").getMax());
    assertEquals(blackLuma, images.get("luma").getPixelAt(0, 0));
    assertEquals(blackLuma, images.get("luma").getPixelAt(0, 1));
    assertEquals(blackLuma, images.get("luma").getPixelAt(0, 2));
    assertEquals(pinkLuma, images.get("luma").getPixelAt(0, 3));
    assertEquals(blackLuma, images.get("luma").getPixelAt(1, 0));
    assertEquals(greenLuma, images.get("luma").getPixelAt(1, 1));
    assertEquals(blackLuma, images.get("luma").getPixelAt(1, 2));
    assertEquals(blackLuma, images.get("luma").getPixelAt(1, 3));
    assertEquals(blackLuma, images.get("luma").getPixelAt(2, 0));
    assertEquals(blackLuma, images.get("luma").getPixelAt(2, 1));
    assertEquals(greenLuma, images.get("luma").getPixelAt(2, 2));
    assertEquals(blackLuma, images.get("luma").getPixelAt(2, 3));
    assertEquals(pinkLuma, images.get("luma").getPixelAt(3, 0));
    assertEquals(blackLuma, images.get("luma").getPixelAt(3, 1));
    assertEquals(blackLuma, images.get("luma").getPixelAt(3, 2));
    assertEquals(blackLuma, images.get("luma").getPixelAt(3, 3));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testLumaInvalidImage() {
    Reader in = new StringReader("load res/invalid.ppm test luma-component test luma\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
  }

  @Test
  public void testLumaInvalidNoMoreInput() {
    Reader in = new StringReader("load res/test4x4.ppm test luma-component test\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("More input required.", finalOut);
  }


  // tests for Save()

  @Test
  public void testSave() {
    Reader in = new StringReader("load res/test4x4.ppm test save pics/test.ppm test\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("Saved image test.", finalOut);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidImage() {
    Reader in = new StringReader("load res/test4x4.ppm test save res/test.ppm invalid\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 2);
    String finalOut = outArray[0];
  }

  @Test
  public void testInvalidFilePath() {
    Reader in = new StringReader("load res/test4x4.ppm test save invalid/test.ppm test\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 2);
    assertEquals("Loaded file as test.", outArray[0]);
    assertEquals("", outArray[1]);
  }

  @Test
  public void testNoMoreInput() {
    Reader in = new StringReader("load res/test4x4.ppm test save invalid/test.ppm\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("More input required.", finalOut);
  }


  public void testNoMoreInput2() {
    Reader in = new StringReader("load res/test4x4.ppm test save\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 2);
    String finalOut = outArray[0];
    assertEquals("hello", outString);
  }

  // tests for PrintMessage()

  @Test
  public void testPrintMessage() {
    Reader in = new StringReader("load res/test4x4.ppm test horizontal-flip test flip\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.printMessage("hello");
    String outString = output.toString();
    String[] outArray = outString.split("\n", 2);
    String finalOut = outArray[0];
    assertEquals("hello", finalOut);
  }

  @Test(expected = IllegalStateException.class)
  public void testPrintMessageCorruptAppendable() {
    Reader in = new StringReader("load res/test4x4.ppm test horizontal-flip test flip\n");
    Appendable output = new CorruptAppendable();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.printMessage("hello");
    String outString = output.toString();
    String[] outArray = outString.split("\n", 2);
    String finalOut = outArray[0];
  }

  @Test
  public void testPrintMessageCorruptReadable() {
    Readable in = new CorruptReadable();
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.printMessage("hello");
    String outString = output.toString();
    String[] outArray = outString.split("\n", 2);
    String finalOut = outArray[0];
  }

  @Test
  public void testPrintMessageWithMockView() {
    Reader in = new StringReader("load res/test4x4.ppm test horizontal-flip test flip\n");
    StringBuilder log = new StringBuilder();
    ImageProcessingView view = new MockView(log);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.printMessage("hello");
    String logString = log.toString();
    String[] logArray = logString.split("\n", 2);
    String finalLog = logArray[0];
    assertEquals("message = hello", finalLog);
  }

  // tests for getImages()

  @Test
  public void testGetImages() {
    Reader in = new StringReader("load res/test4x4.ppm test horizontal-flip test flip\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();

    Map<String, ImageProcessingModel> images = c.getImages();
    assertEquals(2, images.size());
    assertEquals(4, images.get("test").getHeight());
    assertEquals(4, images.get("test").getWidth());
    assertEquals(255, images.get("test").getMax());
    assertEquals(blackLuma, images.get("test").getPixelAt(0, 0));
    assertEquals(blackLuma, images.get("test").getPixelAt(0, 1));
    assertEquals(blackLuma, images.get("test").getPixelAt(0, 2));
    assertEquals(pinkLuma, images.get("test").getPixelAt(0, 3));
    assertEquals(blackLuma, images.get("test").getPixelAt(1, 0));
    assertEquals(greenPixel, images.get("test").getPixelAt(1, 1));
    assertEquals(blackLuma, images.get("test").getPixelAt(1, 2));
    assertEquals(blackLuma, images.get("test").getPixelAt(1, 3));
    assertEquals(blackLuma, images.get("test").getPixelAt(2, 0));
    assertEquals(blackLuma, images.get("test").getPixelAt(2, 1));
    assertEquals(greenPixel, images.get("test").getPixelAt(2, 2));
    assertEquals(blackLuma, images.get("test").getPixelAt(2, 3));
    assertEquals(pinkLuma, images.get("test").getPixelAt(3, 0));
    assertEquals(blackLuma, images.get("test").getPixelAt(3, 1));
    assertEquals(blackLuma, images.get("test").getPixelAt(3, 2));
    assertEquals(blackLuma, images.get("test").getPixelAt(3, 3));

    assertEquals(4, images.get("flip").getHeight());
    assertEquals(4, images.get("flip").getWidth());
    assertEquals(255, images.get("flip").getMax());
    assertEquals(pinkLuma, images.get("flip").getPixelAt(0, 0));
    assertEquals(blackLuma, images.get("flip").getPixelAt(0, 1));
    assertEquals(blackLuma, images.get("flip").getPixelAt(0, 2));
    assertEquals(blackLuma, images.get("flip").getPixelAt(0, 3));
    assertEquals(blackLuma, images.get("flip").getPixelAt(1, 0));
    assertEquals(blackLuma, images.get("flip").getPixelAt(1, 1));
    assertEquals(greenPixel, images.get("flip").getPixelAt(1, 2));
    assertEquals(blackLuma, images.get("flip").getPixelAt(1, 3));
    assertEquals(blackLuma, images.get("flip").getPixelAt(2, 0));
    assertEquals(greenPixel, images.get("flip").getPixelAt(2, 1));
    assertEquals(blackLuma, images.get("flip").getPixelAt(2, 2));
    assertEquals(blackLuma, images.get("flip").getPixelAt(2, 3));
    assertEquals(blackLuma, images.get("flip").getPixelAt(3, 0));
    assertEquals(blackLuma, images.get("flip").getPixelAt(3, 1));
    assertEquals(blackLuma, images.get("flip").getPixelAt(3, 2));
    assertEquals(pinkLuma, images.get("flip").getPixelAt(3, 3));
  }

}
