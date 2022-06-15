import org.junit.Before;
import org.junit.Test;

import model.pixel.Pixel;
import model.pixel.RGBPixel;

import static org.junit.Assert.assertEquals;


/**
 * tests for RGBPixel.
 */
public class RGBPixelTest {

  Pixel p1;
  Pixel p2;
  Pixel p3;
  Pixel p4;

  @Before
  public void init() {
    this.p1 = new RGBPixel(50, 130, 115);
    this.p2 = new RGBPixel(255, 30, 190);
    this.p3 = new RGBPixel(130, 130, 40);
    this.p4 = new RGBPixel(50, 70, 70);
  }

  @Test
  public void testValidInitialization() {
    init();

    assertEquals(50, p1.getColor(0));
    assertEquals(130, p1.getColor(2));
    assertEquals(115, p1.getColor(1));

    assertEquals(255, p2.getColor(0));
    assertEquals(30, p2.getColor(2));
    assertEquals(190, p2.getColor(1));

  }

  @Test(expected = IllegalArgumentException.class)
  public void testDisallowsNegativeR() {
    new RGBPixel(-50, 130, 115);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDisallowsNegativeG() {
    new RGBPixel(50, 130, -115);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDisallowsNegativeB() {
    new RGBPixel(50, -130, 115);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDisallowsLargeR() {
    new RGBPixel(256, 130, 115);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDisallowsLargeG() {
    new RGBPixel(50, 130, 256);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDisallowsLargeB() {
    new RGBPixel(50, 256, 115);
  }

  @Test
  public void testBrighten() {
    init();

    p1.brighten(25);
    assertEquals(75, p1.getColor(0));
    assertEquals(155, p1.getColor(2));
    assertEquals(140, p1.getColor(1));

    p1.brighten(200);
    assertEquals(255, p1.getColor(0));
    assertEquals(255, p1.getColor(2));
    assertEquals(255, p1.getColor(1));

    p1.brighten(-300);
    assertEquals(0, p1.getColor(0));
    assertEquals(0, p1.getColor(2));
    assertEquals(0, p1.getColor(1));

    p2.brighten(30);
    assertEquals(255, p2.getColor(0));
    assertEquals(60, p2.getColor(2));
    assertEquals(220, p2.getColor(1));

  }

  @Test
  public void testSetValueComponent() {
    init();

    p1.setValueComponent();
    p2.setValueComponent();
    p3.setValueComponent();
    p4.setValueComponent();

    assertEquals(130, p1.getColor(0));
    assertEquals(130, p1.getColor(1));
    assertEquals(130, p1.getColor(2));

    assertEquals(255, p2.getColor(0));
    assertEquals(255, p2.getColor(1));
    assertEquals(255, p2.getColor(2));

    assertEquals(130, p3.getColor(0));
    assertEquals(130, p3.getColor(1));
    assertEquals(130, p3.getColor(2));

    assertEquals(70, p4.getColor(0));
    assertEquals(70, p4.getColor(1));
    assertEquals(70, p4.getColor(2));


  }

  @Test
  public void testSetIntensityComponent() {
    init();

    p1.setIntensityComponent();
    p2.setIntensityComponent();

    assertEquals(98, p1.getColor(0), 0.001);
    assertEquals(98, p1.getColor(1), 0.001);
    assertEquals(98, p1.getColor(2), 0.001);


    assertEquals(158, p2.getColor(0), 0.001);
    assertEquals(158, p2.getColor(1), 0.001);
    assertEquals(158, p2.getColor(2), 0.001);

  }

  @Test
  public void testSetLumaComponent() {
    init();

    p1.setLumaComponent();
    p2.setLumaComponent();

    assertEquals(102, p1.getColor(0), 0.001);
    assertEquals(102, p1.getColor(1), 0.001);
    assertEquals(102, p1.getColor(2), 0.001);


    assertEquals(192, p2.getColor(0), 0.001);
    assertEquals(192, p2.getColor(1), 0.001);
    assertEquals(192, p2.getColor(2), 0.001);

  }

  @Test
  public void testSetColorRed() {
    init();

    p1.setColor(0);
    assertEquals(50, p1.getColor(0));
    assertEquals(50, p1.getColor(1));
    assertEquals(50, p1.getColor(2));
  }

  @Test
  public void testSetColorGreen() {
    init();

    p1.setColor(1);
    assertEquals(115, p1.getColor(0));
    assertEquals(115, p1.getColor(1));
    assertEquals(115, p1.getColor(2));
  }

  @Test
  public void testSetColorBlue() {
    init();

    p1.setColor(2);
    assertEquals(130, p1.getColor(0));
    assertEquals(130, p1.getColor(1));
    assertEquals(130, p1.getColor(2));
  }

  @Test
  public void testGetColor() {
    assertEquals(130, p3.getColor(0));
    assertEquals(40, p3.getColor(1));
    assertEquals(130, p3.getColor(2));

    assertEquals(50, p4.getColor(0));
    assertEquals(70, p4.getColor(1));
    assertEquals(70, p4.getColor(2));
  }

  @Test
  public void testGreyscale() {
    init();

    p1.setLumaComponent();
    p2.setLumaComponent();

    assertEquals(102, p1.getColor(0), 0.001);
    assertEquals(102, p1.getColor(1), 0.001);
    assertEquals(102, p1.getColor(2), 0.001);


    assertEquals(192, p2.getColor(0), 0.001);
    assertEquals(192, p2.getColor(1), 0.001);
    assertEquals(192, p2.getColor(2), 0.001);
  }

  @Test
  public void testSepia() {
    init();
    //this.p3 = new RGBPixel(130, 130, 40);
    //this.p4 = new RGBPixel(50, 70, 70);
    p3.setSepia();
    p4.setSepia();

    assertEquals(106, p3.getColor(0), 0.001);
    assertEquals(95, p3.getColor(1), 0.001);
    assertEquals(74, p3.getColor(2), 0.001);


    assertEquals(87, p4.getColor(0), 0.001);
    assertEquals(77, p4.getColor(1), 0.001);
    assertEquals(60, p4.getColor(2), 0.001);
  }

  @Test
  public void testCreateCopy() {
    Pixel newPixel = p4.createCopy();
    assertEquals(50, newPixel.getColor(0));
    assertEquals(70, newPixel.getColor(1));
    assertEquals(70, newPixel.getColor(2));
  }

  @Test
  public void testEquals() {
    Pixel newPixel = p4.createCopy();
    assertEquals(true, p4.equals(newPixel));
    assertEquals(false, p4.equals(p3));
    assertEquals(false, p4.equals(3));
  }

}