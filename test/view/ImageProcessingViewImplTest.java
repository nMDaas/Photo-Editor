package view;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImageProcessingViewImplTest {

  ImageProcessingViewImpl v1;
  ImageProcessingViewImpl v2;


  @Before
  public void init() {

    this.v1 = new ImageProcessingViewImpl();
    this.v2 = new ImageProcessingViewImpl(null);

  }

  @Test
  public void testInvalidInitialization() {
    try {
      this.v2 = new ImageProcessingViewImpl(null);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // do nothing
    }
  }

}