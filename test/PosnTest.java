import org.junit.Before;
import org.junit.Test;

import model.Posn;

import static org.junit.Assert.assertEquals;


/**
 * Tests for the Posn class.
 */
public class PosnTest {

  Posn p1;
  Posn p2;
  Posn p3;
  Posn p4;
  Posn p5;

  @Before
  public void init() {
    this.p1 = new Posn(4, 3, 2.3);
    this.p2 = new Posn(-1, 3, 2.3);
    this.p3 = new Posn(1, -3, 2.3);
    this.p4 = new Posn(4, 7, 2.3);
    this.p5 = new Posn(7, 4, 2.3);
  }

  @Test
  public void testValidInitialization() {
    init();

    assertEquals(true, p1.isValid(7, 7));
    assertEquals(false, p2.isValid(7, 7));
    assertEquals(false, p3.isValid(7, 7));

  }

  @Test
  public void testIsValid() {
    init();

    assertEquals(true, p1.isValid(7, 7));
    assertEquals(false, p2.isValid(7, 7));
    assertEquals(false, p3.isValid(7, 7));
    assertEquals(false, p4.isValid(7, 7));
    assertEquals(false, p5.isValid(7, 7));

  }
}