package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.ImageGUIController;
import controller.ImageGUIControllerImpl;
import model.ImageProcessingModel;

public class ViewGUIImpl extends JFrame implements ImageProcessingViewGUI, ActionListener {

  JLabel fileOpenDisplay;
  JLabel fileSaveDisplay;
  JTextField brightenInput;
  ImageProcessingModel model;
  JButton load;
  ImageGUIController controller;

  public ViewGUIImpl(ImageProcessingModel model){
    super();
    this.model = model;
  }

  public void viewSetUp() {

    this.setTitle("Image Processor");
    this.setSize(700, 700);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    //image panel
    JPanel imagePanel = new JPanel();
    imagePanel.setPreferredSize(new Dimension(500, 500));
    this.add(imagePanel, BorderLayout.CENTER);

    JLabel imageLabel = new ImageLabel(this, model);
    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
    imageScrollPane.setPreferredSize(new Dimension(500, 500));
    imagePanel.add(imageScrollPane);


    // histogram panel
    JPanel histogramPanel = new HistogramPanel(this, model);
    histogramPanel.setBorder(BorderFactory.createTitledBorder("Image Histogram:"));
    histogramPanel.setPreferredSize(new Dimension(600, 500));
    this.add(histogramPanel, BorderLayout.EAST);

    //loadSave panel
    JPanel loadSavePanel = new JPanel();
    loadSavePanel.setBorder(BorderFactory.createTitledBorder(""));
    loadSavePanel.setLayout(new BoxLayout(loadSavePanel, BoxLayout.PAGE_AXIS));
    this.add(loadSavePanel, BorderLayout.SOUTH);

    // load panel
    JPanel loadPanel = new JPanel();
    loadPanel.setBorder(BorderFactory.createTitledBorder("Load Image"));
    loadPanel.setLayout(new FlowLayout());
    loadSavePanel.add(loadPanel);
    load = new JButton("Open a file");
    load.setActionCommand("load");
    load.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        final JFileChooser fChooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPEG, PPM & PNG Images", "jpeg", "png", "ppm");
        fChooser.setFileFilter(filter);
        int retValue = fChooser.showOpenDialog(ViewGUIImpl.this);
        if (retValue == JFileChooser.APPROVE_OPTION) {
          File f = fChooser.getSelectedFile();
          fileOpenDisplay.setText(f.getAbsolutePath());
        }
        controller.actionPerformed(e);
      }
    });
    loadPanel.add(load);
    fileOpenDisplay = new JLabel("File path will appear here");
    loadPanel.add(fileOpenDisplay);

    // save panel
    JPanel savePanel = new JPanel();
    savePanel.setBorder(BorderFactory.createTitledBorder("Save Image"));
    savePanel.setLayout(new FlowLayout());
    loadSavePanel.add(savePanel);
    JButton save = new JButton("Save the image");
    save.setActionCommand("save");
    save.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        final JFileChooser fChooser = new JFileChooser(".");
        int retValue = fChooser.showSaveDialog(ViewGUIImpl.this);
        if (retValue == JFileChooser.APPROVE_OPTION) {
          File f = fChooser.getSelectedFile();
          fileSaveDisplay.setText(f.getAbsolutePath());
        }
        controller.actionPerformed(e);
      }
    });
    savePanel.add(save);
    fileSaveDisplay = new JLabel("File path will appear here");
    savePanel.add(fileSaveDisplay);

    // button panel
    JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
    buttonPanel.setBorder(BorderFactory.createTitledBorder("Buttons"));
    //buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.WEST);

    // color channel panels
    JPanel colorChannelPanel = new JPanel();
    colorChannelPanel.setBorder(BorderFactory.createTitledBorder("RGB Color Channels"));
    colorChannelPanel.setLayout(new BoxLayout(colorChannelPanel, BoxLayout.PAGE_AXIS));
    buttonPanel.add(colorChannelPanel);
    JLabel channelDisplay = new JLabel("Choose a RGB color channel:");
    colorChannelPanel.add(channelDisplay);
    String[]options = {"red-component", "green-component", "blue-component"};
    JComboBox<String> comboBox1 = new JComboBox<String>();
    comboBox1.setActionCommand("Color channel options:");
    comboBox1.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        String s = (String) comboBox1.getSelectedItem();
        try {
          controller.processCommand(s);
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
      }
    });
    //comboBox1.addActionListener(this);
    for (int i = 0; i < options.length; i++) {
      comboBox1.addItem(options[i]);
    }
    colorChannelPanel.add(comboBox1);
    buttonPanel.add(colorChannelPanel);


    // greyscale channel panels
    JPanel greyChannelPanel = new JPanel();
    greyChannelPanel.setBorder(BorderFactory.createTitledBorder("Greyscale Channels"));
    greyChannelPanel.setLayout(new BoxLayout(greyChannelPanel, BoxLayout.PAGE_AXIS));
    buttonPanel.add(greyChannelPanel);
    JLabel greyChannelDisplay = new JLabel("Choose a grey color channel:");
    greyChannelPanel.add(greyChannelDisplay);
    String[]optionsGrey = {"value-component", "intensity-component", "luma-component"};
    JComboBox<String> comboBox2 = new JComboBox<String>();
    comboBox2.setActionCommand("Greyscale channel options:");
    comboBox2.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        String s = (String) comboBox2.getSelectedItem();
        try {
          controller.processCommand(s);
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
      }
    });
    for (int i = 0; i < optionsGrey.length; i++) {
      comboBox2.addItem(optionsGrey[i]);
    }
    greyChannelPanel.add(comboBox2);
    buttonPanel.add(greyChannelPanel);


    // filter channel panels
    JPanel filterChannelPanel = new JPanel();
    filterChannelPanel.setBorder(BorderFactory.createTitledBorder("Filters"));
    filterChannelPanel.setLayout(new BoxLayout(filterChannelPanel, BoxLayout.PAGE_AXIS));
    buttonPanel.add(filterChannelPanel);
    JLabel filterChannelDisplay = new JLabel("Choose a filter:");
    filterChannelPanel.add(filterChannelDisplay);
    String[]optionsFilter = {"make-greyscale", "make-sepia"};
    JComboBox<String> comboBox3 = new JComboBox<String>();
    comboBox3.setActionCommand("Filter options");
    comboBox3.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        String s = (String) comboBox3.getSelectedItem();
        try {
          controller.processCommand(s);
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
      }
    });
    for (int i = 0; i < optionsFilter.length; i++) {
      comboBox3.addItem(optionsFilter[i]);
    }
    filterChannelPanel.add(comboBox3);
    buttonPanel.add(filterChannelPanel);


    // flips panels
    JPanel flipsPanel = new JPanel();
    flipsPanel.setBorder(BorderFactory.createTitledBorder("Flip Image"));
    flipsPanel.setLayout(new BoxLayout(flipsPanel, BoxLayout.PAGE_AXIS));
    buttonPanel.add(flipsPanel);
    JLabel flipsDisplay = new JLabel("Flip image by:");
    flipsPanel.add(flipsDisplay);
    String[]optionsFlip = {"horizontal-flip", "vertical-flip"};
    JComboBox<String> comboBox4 = new JComboBox<String>();
    comboBox4.setActionCommand("Flip options");
    comboBox4.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        String s = (String) comboBox4.getSelectedItem();
        try {
          controller.processCommand(s);
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
      }
    });
    for (int i = 0; i < optionsFlip.length; i++) {
      comboBox4.addItem(optionsFlip[i]);
    }
    flipsPanel.add(comboBox4);
    buttonPanel.add(flipsPanel);

    // blur button
    JButton blur = new JButton("Blur");
    blur.setActionCommand("blur");
    blur.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        controller.actionPerformed(e);
      }
    });
    buttonPanel.add(blur);

    // sharpen button
    JButton sharpen = new JButton("Sharpen");
    sharpen.setActionCommand("sharpen");
    sharpen.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        controller.actionPerformed(e);
      }
    });
    buttonPanel.add(sharpen);

    // brighten panel
    JPanel brightenPanel = new JPanel();
    brightenPanel.setBorder(BorderFactory.createTitledBorder("Change Brightness"));
    brightenPanel.setLayout(new BoxLayout(brightenPanel, BoxLayout.PAGE_AXIS));
    buttonPanel.add(brightenPanel);

    // brighten text
    brightenInput = new JTextField("0");
    brightenPanel.add(brightenInput);

    // brighten button
    JButton brighten = new JButton("Brighten");
    brighten.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          controller.processCommand("brighten");
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
      }
    });
    brightenPanel.add(brighten);

    this.pack();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public int getBrightenValue() {
    String value = brightenInput.getText();
    this.brightenInput.setText("");
    return Integer.parseInt(value);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public String getFilePath() {
    return fileOpenDisplay.getText();
  }

  @Override
  public String saveToFilePath() {
    return fileSaveDisplay.getText();
  }

  @Override
  public void setController(ImageGUIController controller) {
    this.controller = controller;
  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
  }

  @Override
  public void renderError(String message) throws IOException {
    JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void renderMessage(String message) throws IOException {
    JOptionPane.showMessageDialog(this, message, "Successful!", JOptionPane.PLAIN_MESSAGE);
  }
}
