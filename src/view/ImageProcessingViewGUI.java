package view;

import java.awt.event.ActionListener;

import javax.swing.*;

import controller.ImageGUIController;
import controller.ImageGUIControllerImpl;

public interface ImageProcessingViewGUI extends ImageProcessingView {
  void makeVisible();
  int getBrightenValue();
  void refresh();
  String getFilePath();
  String saveToFilePath();
  void setController(ImageGUIController controller);
  void viewSetUp();

}
