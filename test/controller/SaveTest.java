package controller;

import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import view.ImageProcessingView;
import view.ImageProcessingViewImpl;

import static org.junit.Assert.*;

public class SaveTest {

  @Test
  public void testLoadSave(){
    Reader in = new StringReader("load pics/Koala.ppm koala horizontal-flip koala koala-red save pics/koalaRed.ppm koala-red");
    ImageProcessingView view = new ImageProcessingViewImpl();
    ImageProcessingController controller = new ImageProcessingControllerImpl(in, view);
    controller.process();
  }

}