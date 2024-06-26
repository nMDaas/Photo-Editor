package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import controller.commands.BlueComponent;
import controller.commands.Blur;
import controller.commands.Brighten;
import controller.commands.GreenComponent;
import controller.commands.HorizontalFlip;
import controller.commands.ImageProcessingCommand;
import controller.commands.IntensityComponent;
import controller.commands.Load;
import controller.commands.LumaComponent;
import controller.commands.MakeGreyscale;
import controller.commands.MakeSepia;
import controller.commands.RedComponent;
import controller.commands.Save;
import controller.commands.Sharpen;
import controller.commands.ValueComponent;
import controller.commands.VerticalFlip;
import model.ImageModel;
import model.ImageProcessingModel;
import view.ImageProcessingViewGUI;

public class ImageGUIControllerImpl implements ImageGUIController, ActionListener {
  private ImageProcessingModel model;
  private ImageProcessingViewGUI view;

  private Map<String, ImageProcessingCommand> knownCommands = new HashMap<>();


  public ImageGUIControllerImpl(ImageProcessingModel model, ImageProcessingViewGUI view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException("model and view cannot be null");
    }
    this.model = model;
    this.view = view;
    this.setCommands();
    view.setController(this);
    view.viewSetUp();
  }

  private void setCommands() {
    knownCommands.put("load", new Load(model, view));
    knownCommands.put("brighten", new Brighten(model, view));
    knownCommands.put("horizontal-flip", new HorizontalFlip(model, view));
    knownCommands.put("vertical-flip",  new VerticalFlip(model, view));
    knownCommands.put("red-component", new RedComponent(model, view));
    knownCommands.put("green-component", new GreenComponent(model, view));
    knownCommands.put("blue-component", new BlueComponent(model, view));
    knownCommands.put("value-component", new ValueComponent(model, view));
    knownCommands.put("intensity-component", new IntensityComponent(model, view));
    knownCommands.put("luma-component", new LumaComponent(model, view));
    knownCommands.put("save", new Save(model, view));
    knownCommands.put("blur", new Blur(model, view));
    knownCommands.put("sharpen", new Sharpen(model, view));
    knownCommands.put("make-greyscale", new MakeGreyscale(model, view));
    knownCommands.put("make-sepia", new MakeSepia(model, view));
  }

  @Override
  public String processCommand(String command) throws IOException {
    String userInstruction = command;
    ImageProcessingCommand cmd =
            knownCommands.getOrDefault(userInstruction, null);

    if (cmd == null) {
      this.renderError("Invalid command.");
    } else {
      try {
        cmd.execute();
        view.refresh();
      } catch (NoSuchElementException e) {
        view.renderError("More input required.");
      } catch (NumberFormatException e) {
        view.renderError("Invalid number input.");
      }
    }

    return "";
  }

  /**
   * Start the program, i.e. give control to the controller
   */
  @Override
  public void go() {
    this.view.makeVisible();
  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      String status = processCommand(e.getActionCommand());
    } catch (Exception ex) {
      try {
        view.renderError(ex.getMessage());
      } catch (IOException exc) {
        throw new RuntimeException(exc);
      }
    }
  }

  @Override
  public void printMessage(String message) throws IOException {
    view.renderMessage(message);
  }

  @Override
  public void renderError(String message) throws IOException {
    view.renderError(message);
  }

}
