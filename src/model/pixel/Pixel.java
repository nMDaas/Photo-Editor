package model.pixel;

import java.util.Objects;

public interface Pixel {
  public void brighten(int val);
  public void setColor(int index);
  public void setValueComponent();
  public void setIntensityComponent();
  public void setLumaComponent();
  public int getColor(int index);

}
