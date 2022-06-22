import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import view.ImageProcessingViewImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * tests for ImageProcessingViewImplTest.
 */
public class ImageProcessingViewImplTest {

  ImageProcessingViewImpl v1;
  //ImageProcessingViewImpl v2;


  @Before
  public void init() {

    this.v1 = new ImageProcessingViewImpl();

  }

  @Test
  public void testValidInitialization() {
    StringBuilder output = new StringBuilder();
    this.v1 = new ImageProcessingViewImpl(output);

    try {
      v1.renderError("hello");
    } catch (IOException e) {
      throw new IllegalStateException("Unexpected exception thrown");
    }

    assertEquals("hello", output.toString());
  }

  @Test
  public void testInvalidInitialization() {
    try {
      ImageProcessingViewImpl v2 = new ImageProcessingViewImpl(null);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // do nothing
    }
  }

  @Test
  public void testRenderMessage() {
    StringBuilder output = new StringBuilder();
    this.v1 = new ImageProcessingViewImpl(output);

    try {
      v1.renderError("hello");
    } catch (IOException e) {
      throw new IllegalStateException("Unexpected exception thrown");
    }

    assertEquals("hello", output.toString());
  }

  @Test(expected = IOException.class)
  public void testInvalidRenderMessage() throws IOException {
    Appendable out = new CorruptAppendable();
    this.v1 = new ImageProcessingViewImpl(out);
    v1.renderError("hello");
  }

}
