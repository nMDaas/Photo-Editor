package model.commands;

import model.ImageProcessingModel;

public interface ModelCommand {
  ImageProcessingModel go(ImageProcessingModel model);
}
