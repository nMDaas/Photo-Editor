package controller;

import java.util.Map;

import model.ImageProcessingModel;

public interface ImageProcessingController {
  void process() throws IllegalStateException;

  Map<String, ImageProcessingModel> getImages();
  void printMessage(String message);

}
