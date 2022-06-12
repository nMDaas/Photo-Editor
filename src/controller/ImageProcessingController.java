package controller;

import java.util.Map;

import model.ImageProcessingModel;

/**
 * This interface represents the operations offered by the image processing controller. These
 * include process() that runs continuously, getImages() which returns the
 * controller's store of images and printMessage() which prints a message
 */
public interface ImageProcessingController {
  /**
   * Processes the user's input and applies the respective command.
   *
   * @throws IllegalStateException if the command is invalid.
   */
  void process() throws IllegalStateException;

  /**
   * Returns the hashmap of stored images.
   *
   * @return the hashmap of stored images.
   */
  Map<String, ImageProcessingModel> getImages();

  /**
   * Prints the message according the input.
   *
   * @param message the message.
   * @throws IllegalStateException if unable to print the message.
   */
  void printMessage(String message);

}
