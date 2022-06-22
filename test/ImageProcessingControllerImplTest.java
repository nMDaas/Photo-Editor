import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.util.Map;

import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import model.ImageModelImpl;
import model.ImageModel;
import model.pixel.Pixel;
import model.pixel.RGBPixel;
import view.ImageProcessingView;
import view.ImageProcessingViewImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * tests for ImageProcessingControllerImplTest.
*/

public class ImageProcessingControllerImplTest {

  Pixel p1;
  Pixel p2;
  Pixel p3;
  Pixel p4;

  ImageProcessingControllerImpl c1;
  ImageProcessingControllerImpl c2;
  ImageProcessingControllerImpl c3;
  ImageProcessingControllerImpl c4;

  ImageModel fourBy4;
  Pixel blackLuma;
  Pixel greenPixel;
  Pixel pinkLuma;
  Pixel[][] fourBy4Pixels;

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

    fourBy4 = new ImageModelImpl(4, 4, fourBy4Pixels, 255);
  }

  @Test
  public void testInvalidInitialization() {
    try {
      Readable in = null;
      StringBuilder dontCareOutput = new StringBuilder();
      ImageProcessingViewImpl view = new ImageProcessingViewImpl(dontCareOutput);
      this.c1 = new ImageProcessingControllerImpl(in, view);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      Readable in = new StringReader("load pics/smallNew.ppm smallNew\n");
      ImageProcessingViewImpl view = null;
      this.c2 = new ImageProcessingControllerImpl(in, view);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // do nothing
    }
  }

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

  @Test
  public void testOverwrite() {
    Reader in = new StringReader("load pics/test4x4.ppm test save pics/test.ppm test");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    assertEquals("Saved image.", outArray[1]);
  }


  @Test
  public void testLoad() {
    init();
    Reader in = new StringReader("load pics/Koala.ppm koala\n");
    StringBuilder dontCareOutput = new StringBuilder();
    ImageProcessingViewImpl view = new ImageProcessingViewImpl(dontCareOutput);
    ImageProcessingControllerImpl controller = new ImageProcessingControllerImpl(in, view);
    controller.process();
    String outString = dontCareOutput.toString();
    String[] outArray = outString.split("\n", 2);
    String finalOut = outArray[0];
    assertEquals("Loaded file as koala.", finalOut);
  }

  @Test
  public void testLoadSmallNew() {
    init();
    Reader in = new StringReader("load pics/smallNew.ppm smallNew\n");
    StringBuilder dontCareOutput = new StringBuilder();
    ImageProcessingViewImpl view = new ImageProcessingViewImpl(dontCareOutput);
    ImageProcessingControllerImpl controller = new ImageProcessingControllerImpl(in, view);
    controller.process();
    String outString = dontCareOutput.toString();
    String[] outArray = outString.split("\n", 2);
    String finalOut = outArray[0];
    assertEquals("Loaded file as smallNew.", finalOut);
  }

  @Test
  public void testLoadJPEG() {
    Reader in = new StringReader("load pics/portrait.jpeg portrait\n");
    StringBuilder dontCareOutput = new StringBuilder();
    ImageProcessingViewImpl view = new ImageProcessingViewImpl(dontCareOutput);
    ImageProcessingControllerImpl controller = new ImageProcessingControllerImpl(in, view);
    controller.process();
    String outString = dontCareOutput.toString();
    String[] outArray = outString.split("\n", 2);
    String finalOut = outArray[0];
    assertEquals("Loaded file as portrait.\n", outString);
  }

  @Test
  public void testLoadPNG() {
    Reader in = new StringReader("load pics/icons.png icons\n");
    StringBuilder dontCareOutput = new StringBuilder();
    ImageProcessingViewImpl view = new ImageProcessingViewImpl(dontCareOutput);
    ImageProcessingControllerImpl controller = new ImageProcessingControllerImpl(in, view);
    controller.process();
    String outString = dontCareOutput.toString();
    String[] outArray = outString.split("\n", 2);
    String finalOut = outArray[0];
    assertEquals("Loaded file as icons.", finalOut);
  }

  @Test
  public void testLoadImages() {
    Reader in = new StringReader("load pics/test4x4.ppm test\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();


    Map<String, ImageModel> images = c.getModelImages();
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

  }

  @Test
  public void testLoadInvalidImage() {
    Reader in = new StringReader("load pics/invalid.ppm test\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[0];
    assertEquals("File pics/invalid.ppm not found!", finalOut);
  }

  @Test
  public void testLoadInvalidNoMoreInput() {
    Reader in = new StringReader("load pics/test4x4.ppm\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 2);
    String finalOut = outArray[0];
    assertEquals("More input required.", finalOut);
  }


  @Test
  public void testLoadBrighten() {
    Reader in = new StringReader("load pics/Koala.ppm koala brighten koala 40 brighter\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("brighter created by changing brightness of koala.", finalOut);
  }

  @Test
  public void testLoadBrightenJPEG() {
    Reader in = new StringReader("load pics/portrait.jpeg portrait brighten portrait " +
            "40 brighterPortrait\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("brighterPortrait created by changing brightness " +
            "of portrait.", finalOut);
  }

  @Test
  public void testLoadBrightenPNG() {
    Reader in = new StringReader("load pics/icons.png icons brighten icons " +
            "40 brighterIcons\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("brighterIcons created by changing brightness " +
            "of icons.", finalOut);
  }

  @Test
  public void testLoadBrightenSmallNew() {
    Reader in = new StringReader("load pics/smallNew.ppm smallNew brighten " +
            "smallNew 40 brighterSmallNew\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("brighterSmallNew created by changing "
            + "brightness of smallNew.", finalOut);
  }

  @Test
  public void testBrightenImages() {
    init();
    Reader in = new StringReader("load pics/test4x4.ppm test brighten test 40 brighten\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();

    Pixel blackBrighten = new RGBPixel(40, 40, 40);
    Pixel pinkBrighten = new RGBPixel(255, 255, 40);
    Pixel greenBrighten = new RGBPixel(40, 159, 255);


    Map<String, ImageModel> images = c.getModelImages();
    assertEquals(4, images.get("brighten").getHeight());
    assertEquals(4, images.get("brighten").getWidth());
    assertEquals(255, images.get("brighten").getMax());
    assertEquals(blackBrighten, images.get("brighten").getPixelAt(0, 0));
    assertEquals(blackBrighten, images.get("brighten").getPixelAt(0, 1));
    assertEquals(blackBrighten, images.get("brighten").getPixelAt(0, 2));
    assertEquals(pinkBrighten, images.get("brighten").getPixelAt(0, 3));
    assertEquals(blackBrighten, images.get("brighten").getPixelAt(1, 0));
    assertEquals(greenBrighten, images.get("brighten").getPixelAt(1, 1));
    assertEquals(blackBrighten, images.get("brighten").getPixelAt(1, 2));
    assertEquals(blackBrighten, images.get("brighten").getPixelAt(1, 3));
    assertEquals(blackBrighten, images.get("brighten").getPixelAt(2, 0));
    assertEquals(blackBrighten, images.get("brighten").getPixelAt(2, 1));
    assertEquals(greenBrighten, images.get("brighten").getPixelAt(2, 2));
    assertEquals(blackBrighten, images.get("brighten").getPixelAt(2, 3));
    assertEquals(pinkBrighten, images.get("brighten").getPixelAt(3, 0));
    assertEquals(blackBrighten, images.get("brighten").getPixelAt(3, 1));
    assertEquals(blackBrighten, images.get("brighten").getPixelAt(3, 2));
    assertEquals(blackBrighten, images.get("brighten").getPixelAt(3, 3));
  }

  @Test
  public void testBrightenInvalidImage() {
    Reader in = new StringReader("load pics/invalid.ppm test " +
            "brighten test 40 brighten\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    assertEquals("File pics/invalid.ppm not found!This image does not exist.",
            outString);
  }

  @Test
  public void testBrightenInvalidNoMoreInput() {
    Reader in = new StringReader("load pics/test4x4.ppm test brighten test 40\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("More input required.", finalOut);
  }

  @Test
  public void testCheckBrighten() {
    Reader in = new StringReader("load pics/test4x4.ppm test brighten test 40 " +
            "brighten\n");
    StringBuilder dontCareOutput = new StringBuilder();
    ImageProcessingView view = new MockView(dontCareOutput);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.printMessage("correct");
    String logString = dontCareOutput.toString();
    String[] logArray = logString.split("\n", 2);
    String finalLog = logArray[0];
    assertEquals("message = correct", finalLog);
  }


  @Test
  public void testLoadDarken() {
    Reader in = new StringReader("load pics/Koala.ppm koala brighten koala -40 darker\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("darker created by changing brightness of koala.", finalOut);
  }

  @Test
  public void testLoadDarkenSmallNew() {
    Reader in = new StringReader("load pics/smallNew.ppm smallNew brighten " +
            "smallNew -40 darkerSmallNew\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("darkerSmallNew created by changing brightness of smallNew.", finalOut);
  }


  @Test
  public void testLoadDarkenJPEG() {
    Reader in = new StringReader("load pics/portrait.jpeg portrait brighten portrait " +
            "-40 darkerPortrait\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("darkerPortrait created by changing brightness " +
            "of portrait.", finalOut);
  }

  @Test
  public void testLoadDarkenPNG() {
    Reader in = new StringReader("load pics/icons.png icons brighten icons " +
            "-40 darkerIcons\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("darkerIcons created by changing brightness " +
            "of icons.", finalOut);
  }


  @Test
  public void testLoadHorizontalFlip() {
    Reader in = new StringReader("load pics/Koala.ppm koala " +
            "horizontal-flip koala koala-horizontal\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("koala-horizontal created by horizontally flipping koala.", finalOut);
  }

  @Test
  public void testLoadHorizontalFlipSmallNew() {
    Reader in = new StringReader("load pics/smallNew.ppm smallNew " +
            "horizontal-flip smallNew smallNew-horizontal\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("smallNew-horizontal created by horizontally flipping smallNew.", finalOut);
  }

  @Test
  public void testHorizontalFlipJPEG() {
    Reader in = new StringReader("load pics/portrait.jpeg portrait horizontal-flip portrait " +
            "portrait-horizontal\n");
    StringBuilder dontCareOutput = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(dontCareOutput);
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, view);
    controller.process();
    String outString = dontCareOutput.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("portrait-horizontal created by " +
            "horizontally flipping portrait.", finalOut);
  }

  @Test
  public void testHorizontalFlipPNG() {
    Reader in = new StringReader("load pics/icons.png icons horizontal-flip icons " +
            "icons-horizontal\n");
    StringBuilder dontCareOutput = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(dontCareOutput);
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, view);
    controller.process();
    String outString = dontCareOutput.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("icons-horizontal created by " +
            "horizontally flipping icons.", finalOut);
  }

  @Test
  public void testHFlipImages() {
    init();
    Reader in = new StringReader("load pics/test4x4.ppm test " +
            "horizontal-flip test test-horizontal\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();


    Map<String, ImageModel> images = c.getModelImages();
    assertEquals(4, images.get("test-horizontal").getHeight());
    assertEquals(4, images.get("test-horizontal").getWidth());
    assertEquals(255, images.get("test-horizontal").getMax());
    assertEquals(pinkLuma, images.get("test-horizontal").getPixelAt(0, 0));
    assertEquals(blackLuma, images.get("test-horizontal").getPixelAt(0, 1));
    assertEquals(blackLuma, images.get("test-horizontal").getPixelAt(0, 2));
    assertEquals(blackLuma, images.get("test-horizontal").getPixelAt(0, 3));
    assertEquals(blackLuma, images.get("test-horizontal").getPixelAt(1, 0));
    assertEquals(blackLuma, images.get("test-horizontal").getPixelAt(1, 1));
    assertEquals(greenPixel, images.get("test-horizontal").getPixelAt(1, 2));
    assertEquals(blackLuma, images.get("test-horizontal").getPixelAt(1, 3));
    assertEquals(blackLuma, images.get("test-horizontal").getPixelAt(2, 0));
    assertEquals(greenPixel, images.get("test-horizontal").getPixelAt(2, 1));
    assertEquals(blackLuma, images.get("test-horizontal").getPixelAt(2, 2));
    assertEquals(blackLuma, images.get("test-horizontal").getPixelAt(2, 3));
    assertEquals(blackLuma, images.get("test-horizontal").getPixelAt(3, 0));
    assertEquals(blackLuma, images.get("test-horizontal").getPixelAt(3, 1));
    assertEquals(blackLuma, images.get("test-horizontal").getPixelAt(3, 2));
    assertEquals(pinkLuma, images.get("test-horizontal").getPixelAt(3, 3));
  }


  @Test
  public void testHFlip4By3() {
    init();
    Reader in = new StringReader("load pics/test4x3.ppm test43 " +
            "horizontal-flip test43 test43-horizontal\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();


    Map<String, ImageModel> images = c.getModelImages();
    assertEquals(4, images.get("test43-horizontal").getHeight());
    assertEquals(3, images.get("test43-horizontal").getWidth());
    assertEquals(255, images.get("test43-horizontal").getMax());
    assertEquals(blackLuma, images.get("test43-horizontal").getPixelAt(0, 0));
    assertEquals(blackLuma, images.get("test43-horizontal").getPixelAt(0, 1));
    assertEquals(blackLuma, images.get("test43-horizontal").getPixelAt(0, 2));
    assertEquals(blackLuma, images.get("test43-horizontal").getPixelAt(1, 0));
    assertEquals(greenPixel, images.get("test43-horizontal").getPixelAt(1, 1));
    assertEquals(blackLuma, images.get("test43-horizontal").getPixelAt(1, 2));
    assertEquals(greenPixel, images.get("test43-horizontal").getPixelAt(2, 0));
    assertEquals(blackLuma, images.get("test43-horizontal").getPixelAt(2, 1));
    assertEquals(blackLuma, images.get("test43-horizontal").getPixelAt(2, 2));
    assertEquals(blackLuma, images.get("test43-horizontal").getPixelAt(3, 0));
    assertEquals(blackLuma, images.get("test43-horizontal").getPixelAt(3, 1));
    assertEquals(pinkLuma, images.get("test43-horizontal").getPixelAt(3, 2));

  }

  @Test
  public void testHFlipImages3By4() {
    init();
    Reader in = new StringReader("load pics/test3x4.ppm test34 " +
            "horizontal-flip test34 test34-horizontal\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();


    Map<String, ImageModel> images = c.getModelImages();
    assertEquals(3, images.get("test34-horizontal").getHeight());
    assertEquals(4, images.get("test34-horizontal").getWidth());
    assertEquals(255, images.get("test34-horizontal").getMax());
    assertEquals(blackLuma, images.get("test34-horizontal").getPixelAt(0, 0));
    assertEquals(blackLuma, images.get("test34-horizontal").getPixelAt(0, 1));
    assertEquals(blackLuma, images.get("test34-horizontal").getPixelAt(0, 2));
    assertEquals(blackLuma, images.get("test34-horizontal").getPixelAt(0, 3));
    assertEquals(blackLuma, images.get("test34-horizontal").getPixelAt(1, 0));
    assertEquals(blackLuma, images.get("test34-horizontal").getPixelAt(1, 1));
    assertEquals(blackLuma, images.get("test34-horizontal").getPixelAt(1, 2));
    assertEquals(greenPixel, images.get("test34-horizontal").getPixelAt(1, 3));
    assertEquals(blackLuma, images.get("test34-horizontal").getPixelAt(2, 0));
    assertEquals(blackLuma, images.get("test34-horizontal").getPixelAt(2, 1));
    assertEquals(pinkLuma, images.get("test34-horizontal").getPixelAt(2, 2));
    assertEquals(greenPixel, images.get("test34-horizontal").getPixelAt(2, 3));
  }


  @Test
  public void testHorizontalInvalidImage() {
    Reader in = new StringReader("load pics/invalid.ppm test horizontal-flip"
            + " test test-horizontal\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    assertEquals("File pics/invalid.ppm not found!This image does not exist.",
            outString);
  }

  @Test
  public void testHorizontalInvalidNoMoreInput() {
    Reader in = new StringReader("load pics/test4x4.ppm test horizontal-flip test\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("More input required.", finalOut);
  }

  @Test
  public void testLoadVerticalFlip() {
    Reader in = new StringReader("load pics/Koala.ppm koala " +
            "vertical-flip koala koala-vertical\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("koala-vertical created by vertically flipping koala.", finalOut);
  }

  @Test
  public void testLoadVerticalFlipSmallNew() {
    Reader in = new StringReader("load pics/smallNew.ppm smallNew " +
            "vertical-flip smallNew smallNew-vertical\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("smallNew-vertical created by vertically flipping smallNew.", finalOut);
  }

  @Test
  public void testVerticalFlipJPEG() {
    Reader in = new StringReader("load pics/portrait.jpeg portrait vertical-flip portrait " +
            "portrait-vertical\n");
    StringBuilder dontCareOutput = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(dontCareOutput);
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, view);
    controller.process();
    String outString = dontCareOutput.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("portrait-vertical created by " +
            "vertically flipping portrait.", finalOut);
  }

  @Test
  public void testVerticalFlipPNG() {
    Reader in = new StringReader("load pics/icons.png icons vertical-flip icons " +
            "icons-vertical\n");
    StringBuilder dontCareOutput = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(dontCareOutput);
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, view);
    controller.process();
    String outString = dontCareOutput.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("icons-vertical created by " +
            "vertically flipping icons.", finalOut);
  }


  @Test
  public void testVFlipImages() {
    init();
    Reader in = new StringReader("load pics/test4x4.ppm test " +
            "vertical-flip test test-vertical\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();


    Map<String, ImageModel> images = c.getModelImages();
    assertEquals(4, images.get("test-vertical").getHeight());
    assertEquals(4, images.get("test-vertical").getWidth());
    assertEquals(255, images.get("test-vertical").getMax());
    assertEquals(pinkLuma, images.get("test-vertical").getPixelAt(0, 0));
    assertEquals(blackLuma, images.get("test-vertical").getPixelAt(0, 1));
    assertEquals(blackLuma, images.get("test-vertical").getPixelAt(0, 2));
    assertEquals(blackLuma, images.get("test-vertical").getPixelAt(0, 3));
    assertEquals(blackLuma, images.get("test-vertical").getPixelAt(1, 0));
    assertEquals(blackLuma, images.get("test-vertical").getPixelAt(1, 1));
    assertEquals(greenPixel, images.get("test-vertical").getPixelAt(1, 2));
    assertEquals(blackLuma, images.get("test-vertical").getPixelAt(1, 3));
    assertEquals(blackLuma, images.get("test-vertical").getPixelAt(2, 0));
    assertEquals(greenPixel, images.get("test-vertical").getPixelAt(2, 1));
    assertEquals(blackLuma, images.get("test-vertical").getPixelAt(2, 2));
    assertEquals(blackLuma, images.get("test-vertical").getPixelAt(2, 3));
    assertEquals(blackLuma, images.get("test-vertical").getPixelAt(3, 0));
    assertEquals(blackLuma, images.get("test-vertical").getPixelAt(3, 1));
    assertEquals(blackLuma, images.get("test-vertical").getPixelAt(3, 2));
    assertEquals(pinkLuma, images.get("test-vertical").getPixelAt(3, 3));
  }


  @Test
  public void testVFlip4By3() {
    init();
    Reader in = new StringReader("load pics/test4x3.ppm test43 " +
            "vertical-flip test43 test43-vertical\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();


    Map<String, ImageModel> images = c.getModelImages();
    assertEquals(4, images.get("test43-vertical").getHeight());
    assertEquals(3, images.get("test43-vertical").getWidth());
    assertEquals(255, images.get("test43-vertical").getMax());
    assertEquals(pinkLuma, images.get("test43-vertical").getPixelAt(0, 0));
    assertEquals(blackLuma, images.get("test43-vertical").getPixelAt(0, 1));
    assertEquals(blackLuma, images.get("test43-vertical").getPixelAt(0, 2));
    assertEquals(blackLuma, images.get("test43-vertical").getPixelAt(1, 0));
    assertEquals(blackLuma, images.get("test43-vertical").getPixelAt(1, 1));
    assertEquals(greenPixel, images.get("test43-vertical").getPixelAt(1, 2));
    assertEquals(blackLuma, images.get("test43-vertical").getPixelAt(2, 0));
    assertEquals(greenPixel, images.get("test43-vertical").getPixelAt(2, 1));
    assertEquals(blackLuma, images.get("test43-vertical").getPixelAt(2, 2));
    assertEquals(blackLuma, images.get("test43-vertical").getPixelAt(3, 0));
    assertEquals(blackLuma, images.get("test43-vertical").getPixelAt(3, 1));
    assertEquals(blackLuma, images.get("test43-vertical").getPixelAt(3, 2));

  }

  @Test
  public void testVFlipImages3By4() {
    init();
    Reader in = new StringReader("load pics/test3x4.ppm test34 " +
            "vertical-flip test34 test34-vertical\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();


    Map<String, ImageModel> images = c.getModelImages();
    assertEquals(3, images.get("test34-vertical").getHeight());
    assertEquals(4, images.get("test34-vertical").getWidth());
    assertEquals(255, images.get("test34-vertical").getMax());
    assertEquals(greenPixel, images.get("test34-vertical").getPixelAt(0, 0));
    assertEquals(pinkLuma, images.get("test34-vertical").getPixelAt(0, 1));
    assertEquals(blackLuma, images.get("test34-vertical").getPixelAt(0, 2));
    assertEquals(blackLuma, images.get("test34-vertical").getPixelAt(0, 3));
    assertEquals(greenPixel, images.get("test34-vertical").getPixelAt(1, 0));
    assertEquals(blackLuma, images.get("test34-vertical").getPixelAt(1, 1));
    assertEquals(blackLuma, images.get("test34-vertical").getPixelAt(1, 2));
    assertEquals(blackLuma, images.get("test34-vertical").getPixelAt(1, 3));
    assertEquals(blackLuma, images.get("test34-vertical").getPixelAt(2, 0));
    assertEquals(blackLuma, images.get("test34-vertical").getPixelAt(2, 1));
    assertEquals(blackLuma, images.get("test34-vertical").getPixelAt(2, 2));
    assertEquals(blackLuma, images.get("test34-vertical").getPixelAt(2, 3));
  }


  @Test
  public void testVerticalInvalidImage() {
    Reader in = new StringReader("load pics/invalid.ppm test vertical-flip"
            + " test test-vertical\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    assertEquals("File pics/invalid.ppm not found!This image does not exist.",
            outString);
  }

  @Test
  public void testVerticalInvalidNoMoreInput() {
    Reader in = new StringReader("load pics/test4x4.ppm test vertical-flip test\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("More input required.", finalOut);
  }

  @Test
  public void testLoadVerticalHorizontalFlip() {
    Reader in = new StringReader("load pics/Koala.ppm koala " +
            "vertical-flip koala koala-vertical save pics/koala-vertical.ppm koala-vertical " +
            "horizontal-flip koala-vertical koala-vertical-horizontal\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 5);
    assertEquals("Saved image.",
            outArray[2]);
    assertEquals("koala-vertical-horizontal created by horizontally "
            + "flipping koala-vertical.", outArray[3]);
  }


  @Test
  public void testLoadRed() {
    Reader in = new StringReader("load pics/Koala.ppm koala " +
            "red-component koala koala-red\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 5);
    assertEquals("koala-red created through red channel of koala.",
            outArray[1]);
  }

  @Test
  public void testLoadRedSmallNew() {
    Reader in = new StringReader("load pics/smallNew.ppm smallNew " +
            "red-component smallNew smallNew-red\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 5);
    assertEquals("smallNew-red created through red channel of smallNew.",
            outArray[1]);
  }

  @Test
  public void testLoadRedJPEG() {
    Reader in = new StringReader("load pics/portrait.jpeg portrait " +
            "red-component portrait portrait-red\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 5);
    assertEquals("portrait-red created through red channel of portrait.",
            outArray[1]);
  }

  @Test
  public void testLoadRedPNG() {
    Reader in = new StringReader("load pics/icons.png icons " +
            "red-component icons icons-red\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 5);
    assertEquals("icons-red created through red channel of icons.",
            outArray[1]);
  }

  @Test
  public void testRedImages() {
    init();
    Reader in = new StringReader("load pics/test4x4.ppm test " +
            "red-component test test-red\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();

    Pixel blackRed = new RGBPixel(0, 0, 0);
    Pixel pinkRed = new RGBPixel(255, 255, 255);
    Pixel greenRed = new RGBPixel(0, 0, 0);


    Map<String, ImageModel> images = c.getModelImages();
    assertEquals(4, images.get("test-red").getHeight());
    assertEquals(4, images.get("test-red").getWidth());
    assertEquals(255, images.get("test-red").getMax());
    assertEquals(blackRed, images.get("test-red").getPixelAt(0, 0));
    assertEquals(blackRed, images.get("test-red").getPixelAt(0, 1));
    assertEquals(blackRed, images.get("test-red").getPixelAt(0, 2));
    assertEquals(pinkRed, images.get("test-red").getPixelAt(0, 3));
    assertEquals(blackRed, images.get("test-red").getPixelAt(1, 0));
    assertEquals(greenRed, images.get("test-red").getPixelAt(1, 1));
    assertEquals(blackRed, images.get("test-red").getPixelAt(1, 2));
    assertEquals(blackRed, images.get("test-red").getPixelAt(1, 3));
    assertEquals(blackRed, images.get("test-red").getPixelAt(2, 0));
    assertEquals(blackRed, images.get("test-red").getPixelAt(2, 1));
    assertEquals(greenRed, images.get("test-red").getPixelAt(2, 2));
    assertEquals(blackRed, images.get("test-red").getPixelAt(2, 3));
    assertEquals(pinkRed, images.get("test-red").getPixelAt(3, 0));
    assertEquals(blackRed, images.get("test-red").getPixelAt(3, 1));
    assertEquals(blackRed, images.get("test-red").getPixelAt(3, 2));
    assertEquals(blackRed, images.get("test-red").getPixelAt(3, 3));
  }

  @Test
  public void testRedInvalidImage() {
    Reader in = new StringReader("load pics/invalid.ppm test red-component"
            + " test test-red\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    assertEquals("File pics/invalid.ppm not found!This image does not exist.",
            outString);
  }

  @Test
  public void testRedInvalidNoMoreInput() {
    Reader in = new StringReader("load pics/test4x4.ppm test red-component test\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 5);
    assertEquals("More input required.",
            outArray[1]);
  }

  @Test
  public void testLoadGreen() {
    Reader in = new StringReader("load pics/Koala.ppm koala " +
            "green-component koala koala-green\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 5);
    assertEquals("koala-green created through green channel of koala.",
            outArray[1]);
  }

  @Test
  public void testLoadGreenSmallNew() {
    Reader in = new StringReader("load pics/smallNew.ppm smallNew " +
            "green-component smallNew smallNew-green\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 5);
    assertEquals("smallNew-green created through green channel of smallNew.",
            outArray[1]);
  }

  @Test
  public void testLoadGreenJPEG() {
    Reader in = new StringReader("load pics/portrait.jpeg portrait " +
            "green-component portrait portrait-green\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 5);
    assertEquals("portrait-green created through green channel of portrait.",
            outArray[1]);
  }

  @Test
  public void testLoadGreenPNG() {
    Reader in = new StringReader("load pics/icons.png icons " +
            "green-component icons icons-green\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    assertEquals("icons-green created through green channel of icons.",
            outArray[1]);
  }

  @Test
  public void testGreenImages() {
    init();
    Reader in = new StringReader("load pics/test4x4.ppm test " +
            "green-component test test-green\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();

    Pixel blackGreen = new RGBPixel(0, 0, 0);
    Pixel pinkGreen = new RGBPixel(0, 0, 0);
    Pixel greenGreen = new RGBPixel(255, 255, 255);


    Map<String, ImageModel> images = c.getModelImages();
    assertEquals(4, images.get("test-green").getHeight());
    assertEquals(4, images.get("test-green").getWidth());
    assertEquals(255, images.get("test-green").getMax());
    assertEquals(blackGreen, images.get("test-green").getPixelAt(0, 0));
    assertEquals(blackGreen, images.get("test-green").getPixelAt(0, 1));
    assertEquals(blackGreen, images.get("test-green").getPixelAt(0, 2));
    assertEquals(pinkGreen, images.get("test-green").getPixelAt(0, 3));
    assertEquals(blackGreen, images.get("test-green").getPixelAt(1, 0));
    assertEquals(greenGreen, images.get("test-green").getPixelAt(1, 1));
    assertEquals(blackGreen, images.get("test-green").getPixelAt(1, 2));
    assertEquals(blackGreen, images.get("test-green").getPixelAt(1, 3));
    assertEquals(blackGreen, images.get("test-green").getPixelAt(2, 0));
    assertEquals(blackGreen, images.get("test-green").getPixelAt(2, 1));
    assertEquals(greenGreen, images.get("test-green").getPixelAt(2, 2));
    assertEquals(blackGreen, images.get("test-green").getPixelAt(2, 3));
    assertEquals(pinkGreen, images.get("test-green").getPixelAt(3, 0));
    assertEquals(blackGreen, images.get("test-green").getPixelAt(3, 1));
    assertEquals(blackGreen, images.get("test-green").getPixelAt(3, 2));
    assertEquals(blackGreen, images.get("test-green").getPixelAt(3, 3));
  }

  @Test
  public void testGreenInvalidImage() {
    Reader in = new StringReader("load pics/invalid.ppm test green-component"
            + " test test-green\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    assertEquals("File pics/invalid.ppm not found!This image does not exist.",
            outString);
  }

  @Test
  public void testGreenInvalidNoMoreInput() {
    Reader in = new StringReader("load pics/test4x4.ppm test green-component test\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    assertEquals("More input required.",
            outArray[1]);
  }

  @Test
  public void testLoadBlue() {
    Reader in = new StringReader("load pics/Koala.ppm koala " +
            "blue-component koala koala-blue\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    assertEquals("koala-blue created through blue channel of koala.",
            outArray[1]);
  }

  @Test
  public void testLoadBlueSmallNew() {
    Reader in = new StringReader("load pics/smallNew.ppm smallNew " +
            "blue-component smallNew smallNew-blue\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 5);
    assertEquals("smallNew-blue created through blue channel of smallNew.",
            outArray[1]);
  }

  @Test
  public void testLoadBlueJPEG() {
    Reader in = new StringReader("load pics/portrait.jpeg portrait " +
            "blue-component portrait portrait-blue\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 5);
    assertEquals("portrait-blue created through blue channel of portrait.",
            outArray[1]);
  }

  @Test
  public void testLoadBluePNG() {
    Reader in = new StringReader("load pics/icons.png icons " +
            "blue-component icons icons-blue\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 5);
    assertEquals("icons-blue created through blue channel of icons.",
            outArray[1]);
  }

  @Test
  public void testBlueImages() {
    init();
    Reader in = new StringReader("load pics/test4x4.ppm test " +
            "blue-component test test-blue\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();

    Pixel blackBlue = new RGBPixel(0, 0, 0);
    Pixel greenBlue = new RGBPixel(119, 119, 119);
    Pixel pinkBlue = new RGBPixel(255, 255, 255);


    Map<String, ImageModel> images = c.getModelImages();
    assertEquals(4, images.get("test-blue").getHeight());
    assertEquals(4, images.get("test-blue").getWidth());
    assertEquals(255, images.get("test-blue").getMax());
    assertEquals(blackBlue, images.get("test-blue").getPixelAt(0, 0));
    assertEquals(blackBlue, images.get("test-blue").getPixelAt(0, 1));
    assertEquals(blackBlue, images.get("test-blue").getPixelAt(0, 2));
    assertEquals(pinkBlue, images.get("test-blue").getPixelAt(0, 3));
    assertEquals(blackBlue, images.get("test-blue").getPixelAt(1, 0));
    assertEquals(greenBlue, images.get("test-blue").getPixelAt(1, 1));
    assertEquals(blackBlue, images.get("test-blue").getPixelAt(1, 2));
    assertEquals(blackBlue, images.get("test-blue").getPixelAt(1, 3));
    assertEquals(blackBlue, images.get("test-blue").getPixelAt(2, 0));
    assertEquals(blackBlue, images.get("test-blue").getPixelAt(2, 1));
    assertEquals(greenBlue, images.get("test-blue").getPixelAt(2, 2));
    assertEquals(blackBlue, images.get("test-blue").getPixelAt(2, 3));
    assertEquals(pinkBlue, images.get("test-blue").getPixelAt(3, 0));
    assertEquals(blackBlue, images.get("test-blue").getPixelAt(3, 1));
    assertEquals(blackBlue, images.get("test-blue").getPixelAt(3, 2));
    assertEquals(blackBlue, images.get("test-blue").getPixelAt(3, 3));
  }

  @Test
  public void testBlueInvalidImage() {
    Reader in = new StringReader("load pics/invalid.ppm test blue-component"
            + " test test-blue\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    assertEquals("File pics/invalid.ppm not found!This image does not exist.",
            outString);
  }

  @Test
  public void testBlueInvalidNoMoreInput() {
    Reader in = new StringReader("load pics/test4x4.ppm test blue-component test\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    assertEquals("More input required.",
            outArray[1]);
  }

  @Test
  public void testLoadIntensity() {
    Reader in = new StringReader("load pics/test4x4.ppm test " +
            "intensity-component test test-intensity\n");
    StringBuilder dontCareOutput = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(dontCareOutput);
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, view);
    controller.process();
    String outString = dontCareOutput.toString();
    String[] outArray = outString.split("\n", 3);
    assertEquals("test-intensity created through intensity greyscale of test.",
            outArray[1]);

  }

  @Test
  public void testLoadValue() {
    Reader in = new StringReader("load pics/test4x4.ppm test " +
            "value-component test test-blue\n");
    StringBuilder dontCareOutput = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(dontCareOutput);
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, view);
    controller.process();
    String outString = dontCareOutput.toString();
    String[] outArray = outString.split("\n", 3);
    assertEquals("test-blue created through value greyscale of test.",
            outArray[1]);

  }

  @Test
  public void testLoadVerticalHorizontalFlipSmallNew() {
    Reader in = new StringReader("load pics/smallNew.ppm smallNew " +
            "vertical-flip smallNew smallNew-vertical save pics/smallNew-vertical.ppm " +
            "smallNew-vertical " +
            "horizontal-flip smallNew-vertical smallNew-vertical-horizontal\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 4);
    assertEquals("smallNew-vertical created by vertically flipping smallNew.",
            outArray[1]);

  }

  @Test
  public void testLoadIntensitySavePPM() {
    Reader in = new StringReader("load pics/test4x4.ppm test " +
            "intensity-component test test-intensity " +
            "save res/test-intensity.ppm test-intensity\n");
    StringBuilder dontCareOutput = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(dontCareOutput);
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, view);
    controller.process();
    String outString = dontCareOutput.toString();
    String[] outArray = outString.split("\n", 4);
    assertEquals("Saved image.", outArray[2]);

  }

  @Test
  public void testLoadIntensitySavePNG() {
    Reader in = new StringReader("load pics/icons.png test " +
            "intensity-component test test-intensity " +
            "save res/testSave.png test-intensity\n");
    StringBuilder dontCareOutput = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(dontCareOutput);
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, view);
    controller.process();
    String outString = dontCareOutput.toString();
    String[] outArray = outString.split("\n", 4);
    assertEquals("Saved image.", outArray[2]);

  }

  @Test
  public void testLoadIntensitySaveJPEG() {
    Reader in = new StringReader("load pics/portrait.jpeg test " +
            "intensity-component test test-intensity " +
            "save res/testSave.jpeg test-intensity\n");
    StringBuilder dontCareOutput = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(dontCareOutput);
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, view);
    controller.process();
    String outString = dontCareOutput.toString();
    String[] outArray = outString.split("\n", 3);
    assertEquals("Saved image.\n", outArray[2]);

  }

  // tests for valueComponent

  @Test
  public void testValueComponentPPM() {
    Reader in = new StringReader("load pics/test4x4.ppm test value-component test value\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();

    Pixel blackValue = new RGBPixel(0, 0, 0);
    Pixel pinkValue = new RGBPixel(255, 255, 255);
    Pixel greenValue = new RGBPixel(255, 255, 255);

    Map<String, ImageModel> images = c.getModelImages();
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

  @Test
  public void testValueComponentJPEG() {
    Reader in = new StringReader("load pics/portrait.jpeg test value-component test value\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("value created through value greyscale of test.", finalOut);
  }

  @Test
  public void testValueComponentPNG() {
    Reader in = new StringReader("load pics/icons.png test value-component test value\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("value created through value greyscale of test.", finalOut);
  }

  @Test
  public void testValueInvalidImage() {
    Reader in = new StringReader("load pics/invalid.ppm test value-component"
            + " test value\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    assertEquals("File pics/invalid.ppm not found!This image does not exist.",
            outString);
  }

  @Test
  public void testValueInvalidNoMoreInputPPM() {
    Reader in = new StringReader("load pics/test4x4.ppm test value-component test\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("More input required.", finalOut);
  }

  @Test
  public void testValueInvalidNoMoreInputPNG() {
    Reader in = new StringReader("load pics/icons.png test value-component test\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("More input required.", finalOut);
  }

  @Test
  public void testValueInvalidNoMoreInputJPEG() {
    Reader in = new StringReader("load pics/portrait.jpeg test value-component test\n");
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
    Reader in = new StringReader("load pics/test4x4.ppm test intensity-component test intensity\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();

    Pixel blackIntensity = new RGBPixel(0, 0, 0);
    Pixel pinkIntensity = new RGBPixel(170, 170, 170);
    Pixel greenIntensity = new RGBPixel(125, 125, 125);

    Map<String, ImageModel> images = c.getModelImages();
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

  @Test
  public void testIntensityComponentJPEG() {
    Reader in = new StringReader("load pics/portrait.jpeg test intensity-component test value\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("value created through intensity greyscale of test.", finalOut);
  }

  @Test
  public void testIntensityComponentPNG() {
    Reader in = new StringReader("load pics/icons.png test intensity-component test value\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("value created through intensity greyscale of test.", finalOut);
  }

  @Test
  public void testIntensityInvalidImage() {
    Reader in = new StringReader("load pics/invalid.ppm test intensity-component"
            + " test intensity\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    assertEquals("File pics/invalid.ppm not found!This image does not exist.",
            outString);
  }

  @Test
  public void testIntensityInvalidNoMoreInput() {
    Reader in = new StringReader("load pics/test4x4.ppm test intensity-component test\n");
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
    Reader in = new StringReader("load pics/test4x4.ppm test luma-component test luma\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();

    Pixel blackLuma = new RGBPixel(0, 0, 0);
    Pixel pinkLuma = new RGBPixel(73, 73, 73);
    Pixel greenLuma = new RGBPixel(191, 191, 191);

    Map<String, ImageModel> images = c.getModelImages();
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

  @Test
  public void testLumaComponentJPEG() {
    Reader in = new StringReader("load pics/portrait.jpeg test luma-component test value\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("value created through luma greyscale of test.", finalOut);
  }

  @Test
  public void testLumaComponentPNG() {
    Reader in = new StringReader("load pics/icons.png test luma-component test value\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("value created through luma greyscale of test.", finalOut);
  }

  @Test
  public void testLumaInvalidImage() {
    Reader in = new StringReader("load pics/invalid.ppm test luma-component test luma\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    assertEquals("File pics/invalid.ppm not found!This image does not exist.",
            outString);
  }

  @Test
  public void testLumaInvalidNoMoreInput() {
    Reader in = new StringReader("load pics/test4x4.ppm test luma-component test\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("More input required.", finalOut);
  }

  // test MakeGreyscale
  @Test
  public void testMakeGreyscalePPM() {
    Reader in = new StringReader("load pics/test4x4.ppm test make-greyscale test grey\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();

    Pixel blackLuma = new RGBPixel(0, 0, 0);
    Pixel pinkLuma = new RGBPixel(73, 73, 73);
    Pixel greenLuma = new RGBPixel(191, 191, 191);

    Map<String, ImageModel> images = c.getModelImages();
    assertEquals(4, images.get("grey").getHeight());
    assertEquals(4, images.get("grey").getWidth());
    assertEquals(191, images.get("grey").getMax());
    assertEquals(blackLuma, images.get("grey").getPixelAt(0, 0));
    assertEquals(blackLuma, images.get("grey").getPixelAt(0, 1));
    assertEquals(blackLuma, images.get("grey").getPixelAt(0, 2));
    assertEquals(pinkLuma, images.get("grey").getPixelAt(0, 3));
    assertEquals(blackLuma, images.get("grey").getPixelAt(1, 0));
    assertEquals(greenLuma, images.get("grey").getPixelAt(1, 1));
    assertEquals(blackLuma, images.get("grey").getPixelAt(1, 2));
    assertEquals(blackLuma, images.get("grey").getPixelAt(1, 3));
    assertEquals(blackLuma, images.get("grey").getPixelAt(2, 0));
    assertEquals(blackLuma, images.get("grey").getPixelAt(2, 1));
    assertEquals(greenLuma, images.get("grey").getPixelAt(2, 2));
    assertEquals(blackLuma, images.get("grey").getPixelAt(2, 3));
    assertEquals(pinkLuma, images.get("grey").getPixelAt(3, 0));
    assertEquals(blackLuma, images.get("grey").getPixelAt(3, 1));
    assertEquals(blackLuma, images.get("grey").getPixelAt(3, 2));
    assertEquals(blackLuma, images.get("grey").getPixelAt(3, 3));
  }

  @Test
  public void testMakeGreyscaleComponentJPEG() {
    Reader in = new StringReader("load pics/portrait.jpeg test make-greyscale test grey\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("grey created through greyscale of test.", finalOut);
  }

  @Test
  public void testMakeGreyscaleComponentPNG() {
    Reader in = new StringReader("load pics/icons.png test make-greyscale test grey\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("grey created through greyscale of test.", finalOut);
  }

  @Test
  public void testMakeGreyscaleInvalidImage() {
    Reader in = new StringReader("load pics/invalid.ppm test make-greyscale test luma\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    assertEquals("File pics/invalid.ppm not found!This image does not exist.",
            outString);
  }

  @Test
  public void testGreyscaleInvalidNoMoreInput() {
    Reader in = new StringReader("load pics/test4x4.ppm test make-greyscale test\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("More input required.", finalOut);
  }

  // test MakeSepia
  @Test
  public void testMakeSepia() {
    Reader in = new StringReader("load pics/test4x4.ppm test make-sepia test sepia\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();

    Pixel blackSepia = new RGBPixel(0, 0, 0);
    Pixel pinkSepia = new RGBPixel(148, 103, 132);
    Pixel greenSepia = new RGBPixel(219, 152, 195);

    Map<String, ImageModel> images = c.getModelImages();
    assertEquals(4, images.get("sepia").getHeight());
    assertEquals(4, images.get("sepia").getWidth());
    assertEquals(219, images.get("sepia").getMax());
    assertEquals(blackSepia, images.get("sepia").getPixelAt(0, 0));
    assertEquals(blackSepia, images.get("sepia").getPixelAt(0, 1));
    assertEquals(blackSepia, images.get("sepia").getPixelAt(0, 2));
    assertEquals(pinkSepia, images.get("sepia").getPixelAt(0, 3));
    assertEquals(blackSepia, images.get("sepia").getPixelAt(1, 0));
    assertEquals(greenSepia, images.get("sepia").getPixelAt(1, 1));
    assertEquals(blackSepia, images.get("sepia").getPixelAt(1, 2));
    assertEquals(blackSepia, images.get("sepia").getPixelAt(1, 3));
    assertEquals(blackSepia, images.get("sepia").getPixelAt(2, 0));
    assertEquals(blackSepia, images.get("sepia").getPixelAt(2, 1));
    assertEquals(greenSepia, images.get("sepia").getPixelAt(2, 2));
    assertEquals(blackSepia, images.get("sepia").getPixelAt(2, 3));
    assertEquals(pinkSepia, images.get("sepia").getPixelAt(3, 0));
    assertEquals(blackSepia, images.get("sepia").getPixelAt(3, 1));
    assertEquals(blackSepia, images.get("sepia").getPixelAt(3, 2));
    assertEquals(blackSepia, images.get("sepia").getPixelAt(3, 3));
  }

  @Test
  public void testMakeSepiaComponentJPEG() {
    Reader in = new StringReader("load pics/portrait.jpeg test make-sepia test sepia\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("sepia created through greyscale of test.", finalOut);
  }

  @Test
  public void testMakeSepiaComponentPNG() {
    Reader in = new StringReader("load pics/icons.png test make-sepia test sepia\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("sepia created through greyscale of test.", finalOut);
  }

  @Test
  public void testMakeSepiaInvalidImage() {
    Reader in = new StringReader("load pics/invalid.ppm test make-sepia test sepia\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    assertEquals("File pics/invalid.ppm not found!This image does not exist.",
            outString);
  }

  @Test
  public void testSepiaInvalidNoMoreInput() {
    Reader in = new StringReader("load pics/test4x4.ppm test luma-component sepia\n");
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
  public void testSavePPM1() {
    Reader in = new StringReader("load pics/test4x4.ppm test save pics/test.ppm test\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("Saved image.", finalOut);
  }

  @Test
  public void testSavePPM2() {
    Reader in = new StringReader("load pics/test4x4.ppm test save pics/test.jpeg test\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("Saved image.", finalOut);
  }

  @Test
  public void testSavePPM3() {
    Reader in = new StringReader("load pics/test4x4.ppm test save pics/test.png test\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("Saved image.", finalOut);
  }


  @Test
  public void testSaveJPEG1() {
    Reader in = new StringReader("load pics/portrait.jpeg test save pics/test.ppm test\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("Saved image.", finalOut);
  }

  @Test
  public void testSaveJPEG2() {
    Reader in = new StringReader("load pics/portrait.jpeg test save pics/test.jpeg test\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("Saved image.", finalOut);
  }

  @Test
  public void testSaveJPEG3() {
    Reader in = new StringReader("load pics/portrait.jpeg test save pics/test.png test\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("Saved image.", finalOut);
  }

  @Test
  public void testSavePNG1() {
    Reader in = new StringReader("load pics/icons.png test save pics/test.ppm test\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    assertEquals("Saved image.", outArray[1]);
  }

  @Test
  public void testSavePNG2() {
    Reader in = new StringReader("load pics/icons.png test save pics/test.jpeg test\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("Saved image.", finalOut);
  }

  @Test
  public void testSavePNG3() {
    Reader in = new StringReader("load pics/icons.png test save pics/test.png test\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();
    String outString = output.toString();
    String[] outArray = outString.split("\n", 3);
    String finalOut = outArray[1];
    assertEquals("Saved image.", finalOut);
  }

  // tests for PrintMessage()

  @Test
  public void testPrintMessage() {
    Reader in = new StringReader("load pics/test4x4.ppm test horizontal-flip test flip\n");
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
    Reader in = new StringReader("load pics/test4x4.ppm test horizontal-flip test flip\n");
    Appendable output = new CorruptAppendable();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.printMessage("hello");
    String outString = output.toString();
    String[] outArray = outString.split("\n", 2);
    String finalOut = outArray[0];
  }

  @Test
  public void testPrintMessageWithMockView() {
    Reader in = new StringReader("load pics/test4x4.ppm test horizontal-flip test flip\n");
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
    Reader in = new StringReader("load pics/test4x4.ppm test horizontal-flip test flip\n");
    StringBuilder output = new StringBuilder();
    ImageProcessingView view = new ImageProcessingViewImpl(output);
    ImageProcessingController c = new ImageProcessingControllerImpl(in, view);
    c.process();

    Map<String, ImageModel> images = c.getModelImages();
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
