package controller.commands;

/**
 * Represents the interface for the different known commands the user can input except
 * for load and save.
 */
public interface ImageProcessingCommand {

  /**
   * Helps to implement the commands the user inputs.
   */
  void execute();
}
