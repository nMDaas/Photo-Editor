package controller;

import java.awt.event.ActionEvent;

import model.ImageModel;

public interface ImageGUIController {
  String processCommand(String command);

  /**
   * Start the program, i.e. give control to the controller
   */
  void go();
  ImageModel getImage();
  void actionPerformed(ActionEvent e);
}
