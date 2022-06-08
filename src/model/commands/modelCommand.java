package model.commands;

import controller.ImageProcessingController;
import model.ImageProcessingModel;

public interface modelCommand {
  ImageProcessingModel go(ImageProcessingModel model);
}
