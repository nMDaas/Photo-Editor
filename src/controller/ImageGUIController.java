package controller;

import java.awt.event.ActionEvent;
import java.io.IOException;

import model.ImageModel;

public interface ImageGUIController {
  String processCommand(String command) throws IOException;

  /**
   * Start the program, i.e. give control to the controller
   */
  void go();
  void actionPerformed(ActionEvent e);
  void printMessage(String message) throws IOException;
  void renderError(String message) throws IOException;

}
