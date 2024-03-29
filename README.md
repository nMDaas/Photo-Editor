# Introduction
The packages in this src file represent the model, controller and view of an Image
program. This image processing program can take ppm images from a file path and
edit the images based on user choice.

## CONTROLLER PACKAGE

### ImageProcessingController
This represents the interface of the program's
controller. This interface requires any implementation of ImageProcessingController to:
- process user input via the process() method until there is no more user input
- print messages via the printMessage() method to the program's ImageProcessingView
  implementation
- return the images stored by the ImageProcessingController via getImages()

### ImageProcessingControllerImpl
This is an implementation of ImageProcessingController
that takes in an ImageProcessingView, Readable and Scanner. This is initialized
with a hashmap of known commands and can perform the following functionalities:
- process() - to continue taking input from the user until no input remains,
  execute commands based on user input, and throw exceptions if the user inputs
  invalid input
- printMessage() - to print any message to the ImageProcessingView and throw
  an IllegalStateException if there is an IOException
- getImages() - to return the images stored by the ImageProcessingController

### ImageGUIController
This represents the interface of the program's
controller that renders the program with GUI. A new interface was created because
there were some methods of the older interface that implementations of the controller
with the GUI did not need. This interface requires any implementation
of ImageProcessingController to:
- process a command via the process(String) method and execute the command on the model
- extract action commands via the actionPerformed(ActionEvent) method that is passed on to the
  process(String) method
- make the view visible via go()
- print messages via the printMessage() method to the program's ImageProcessingView
  implementation
- print errors via the renderError() method to the program's ImageProcessingView
  implementation

### ImageGUIControllerImpl
This is an implementation of ImageGUIController
hat takes in an ImageProcessingView, Readable and Scanner. Additional
constructors were added to its implementations and its extensions to support GUI
implementations ImageGUIControllerImpl and ImageProcessingViewGUIThis.
This implements all its inherited methods along with:
- setCommands(), a private method that adds to the hashmap, knownCommands

### CONTROLLER.COMMANDS PACKAGE

ImageProcessingCommand - This interface is an interface for all of
ImageProcessingControllerImpl's or ImageGUIControllerImpl's known commands based on user input.
This interface has only one method - go() - that executes the command.

Load - Load implements ImageProcessingCommand and loads an image to the file path
provided, depending on the type of the file. A message is output if the file format
provided is unrecognized or not supported.


LoadJPEGPNG - This class loads JPEG and PNG FILES through the single method loadFile().
If the file is invalid, a message is printed to say that the file was not found. If the image
name provided is an empty string, a message saying that more input is required is printed.
If the scanner runs out of input, an appropriate message is printed. If anything other than
an Integer is input for the pixels, an appropriate message is printed.


LoadPPM - This class loads PPM FILES through the single method loadFile().
If the file is invalid, a message is printed to say that the file was not found. If the image
name provided is an empty string, a message saying that more input is required is printed.
If the scanner runs out of input, an appropriate message is printed. If anything other than
an Integer is input for the pixels, an appropriate message is printed.

Save - Save implements ImageProcessingCommand and saves an image to the file path
depending on the file format. A message is output if the file format provided is unrecognized
or not supported.

SaveJPEG - SaveJPEG is a class that saves jpeg images specifically using the specific saveFile()
method. If the path is invalid, a "file not found" message is printed. If the image
is not found, an appropriate message is printed.

SavePNG - SavePNG is a class that saves png images specifically using the specific saveFile()
method. If the path is invalid, a "file not found" message is printed. If the image
is not found, an appropriate message is printed.

SavePPM - SavePPM is a class that saves ppm images specifically using the specific saveFile()
method. If the path is invalid, a "file not found" message is printed. If the image
is not found, an appropriate message is printed.

AbstractCommand - AbstractCommand implements ImageProcessingCommand and is
an abstract command for all commands except for Load and Save. This has two
methods:
- go() - this creates a new edited model based on the command and adds it to the
  ImageProcessingController's hashmap of images
- doCommand() - a factory method that executes the command by calling the extended
  class, depending on whichever it is

RedComponent - RedComponent extends AbstractCommand. Its doCommand() implementation
returns the image with the red channel and prints a message of completion.

GreenComponent - GreenComponent extends AbstractCommand. Its doCommand() implementation
returns the image with a green channel and prints a message of completion.

BlueComponent - BlueComponent extends AbstractCommand. Its doCommand() implementation
returns the image with a blue channel and prints a message of completion.

ValueComponent - ValueComponent extends AbstractCommand. Its doCommand() implementation
returns a greyscale image created by calculating value and prints a message of completion.

IntensityComponent - IntensityComponent extends AbstractCommand. Its doCommand()
implementation returns a greyscale image created by calculating intensity and
prints a message of completion.

LumaComponent - LumaComponent extends AbstractCommand. Its doCommand() implementation
returns a greyscale image created by calculating luma and prints a message of completion.

Brighten - Brighten extends AbstractCommand. Its doCommand() implementation
returns an image with changed brightness and prints a message of completion.

HorizontalFlip - HorizontalFlip extends AbstractCommand. Its doCommand() implementation
returns an image horizontally flipped and prints a message of completion.

VerticalFlip - VerticalFlip extends AbstractCommand. Its doCommand() implementation
returns an image vertically flipped and prints a message of completion.

Blur - Blur extends AbstractCommand. Its doCommand() implementation
returns a blurred image and prints a message of completion.

Sharpen - Sharpen extends AbstractCommand. Its doCommand() implementation
returns a sharpened image and prints a message of completion.

MakeGreyscale - MakeGreyscale extends AbstractCommand. Its doCommand() implementation
returns a greyscale image and prints a message of completion.

MakeSepia - MakeSepia extends AbstractCommand. Its doCommand() implementation
returns an image with a sepia filter and prints a message of completion.

## MODEL PACKAGE

### ImageProcessingModel
Since it would be inappropriate to have the stored images
in the controller, this interface was created as the model of the program with one
method:
- getImages() - returns the hashmap of loaded or edited images

### ImageProcessingModelImpl
This represents an implementation of ImageProcessingModel
that implements all its inherited methods

### ImageModel
This interface (earlier represented the model) represents an
image. It supports the following methods:
- redComponent() - creates an image's red channel
- greenComponent() - creates an image's green channel
- blueComponent() - creates an image's blue channel
- valueComponent() - creates an image's greyscale based on value
- intensityComponent() - creates an image's greyscale based on intensity
- lumaComponent() - creates an image's greyscale based on luma
- brighten(int val) - creates an image that is brightened/darkened by a certain value
- flipHorizontal() - creates an image that is horizontally flipped
- flipVertical() - creates an image that is vertically flipped
- getWidth() - return's the image's width
- getHeight() - return's the image's height
- getPixelAt(int row, int col) - returns the pixel at a certain row and column
- setPixelAt(int row, int col, Pixel p) - sets the pixel at a certain row
  and column to a given pixel
- getMax() - returns the max value of a pixel in the image
- createCopy() - returns a copy of the ImageProcessingModel
- blurImage() - creates a blurred image
- sharpenImage() - creates a sharpened image
- setGreyscale() - creates a greyscale image
- setSepia() - creates an image with a sepia filter

### ImageModelImpl
ImageModelImpl is an implementation of ImageModel and implements
all its inherited methods. It also implements applyKernel()
- applyKernel(Posn [] kernel, ImageProcessingModel model, int row, int col) - returns
  an ImageProcessingModel with a kernel applied to a pixel at a specific row and column (col)

### MODEL.COMMANDS PACKAGE

PixelWiseProcessor - PixelWise processor processes each image's pixels and supports the
following methods:
- go(ImageProcessingModel model) - this creates a new model and through a for loop,
  mutates each pixel by calling the abstract doCommand(Pixel p)
- doCommand(Pixel p) - this is a factory method that mutates p based on the command

ChangeBrightness - ChangeBrightness extends AbstractModelCommand. It implements
doCommand(Pixel p) that increases a single pixel's brightness.

ValueGreyscale - ValueGreyscale extends AbstractModelCommand. It implements
doCommand(Pixel p) that converts pixel p to greyscale based on value.

IntensityGreyscale - IntensityGreyscale extends AbstractModelCommand. It implements
doCommand(Pixel p) that converts pixel p to greyscale based on intensity.

LumaGreyscale - LumaGreyscale extends AbstractModelCommand. It implements
doCommand(Pixel p) that converts pixel p to greyscale based on luma.

MakeRed - MakeRed extends AbstractModelCommand. It implements
doCommand(Pixel p) that converts pixel p to a red channel.

MakeGreen - MakeGreen extends AbstractModelCommand. It implements
doCommand(Pixel p) that converts pixel p to a green channel.

MakeBlue - MakeBlue extends AbstractModelCommand. It implements
doCommand(Pixel p) that converts pixel p to a blue channel.

SetGreyscale - setGreyscale extends AbstractModelCommand. It implements
doCommand(Pixel p) that converts pixel p to its greyscale version

SetSepia - setSepia extends AbstractModelCommand. It implements
doCommand(Pixel p) that converts pixel p to a pixel with a sepia filter


## VIEW PACKAGE

### ImageProcessingView
This interface represents the view of the program. It only
supports one method - renderMessage(String message) that prints the message.

### ImageProcessingViewImpl
ImageProcessingViewImpl implements ImageProcessingView. It
only takes in an appendable and only implements renderMessage(String message).
An IOException is thrown if there is one.

### ImageProcessingViewGUI
This interface represents the view of the program that
displays the GUI of the program. This is an extension of ImageProcessingView
as it adds additional functionality. It supports the following methods:
- makeVisible() - makes the view visible
- getBrightenValue() - which returns the value the brightness needs to be changed by
  from the user
- refresh() - updates the view after implementation of commands
- setController(ImageGUIController) - sets the view's controller to the given
- viewSetUp() - sets up the view's JPanels, JLabels, etc. that it needs
- getFilePath() - gets the file path input by the user to load an image
- saveToFilePath() - gets the file path input by the user to save an image to a path

### ViewGUIImpl
This implements ImageProcessingViewGUI. It implements all its
inherited methods.

### Histogram
This represents a histogram of an image. It has an array containing values
for the RGB histograms of an image as fields as well as an ImageModel as a field. It
implements the methods:
- generateHistograms() - fills up the arrays fields of the histogram based on the ImageModel
- returnHistogram(int) - returns an array containing the Histogram data depending on the index.
index 0 is red, index 1 is green and index 2 is blue.

### ImageLabel
This extends JLabel and implements the inherited paintComponent(Graphics)
method. This ImageLabel is responsible for displaying the image on the GUI and was created
as an additional class to allow the image to refresh

### HistogramPanel
This extends JPanel and implements the inherited paintComponent(Graphics)
method. This HistogramPanel is responsible for displaying the histogram on the GUI and was created
as an additional class to allow the image to refresh. drawHistogram() was used as a private
helper method to help the paintComponent() method


## PIXELS PACKAGE

### Pixel
This interface represents a pixel that can have multiple values.
Its implementations inherit the following methods:
- brighten(int val) - brightens the pixel by a value (positive or negative)
- setColor(int index) - channels the pixel based on an index that correlates to
  one of the pixel's value
- getColor(int index) - gets the value of one of the pixel's values,
  based on an index
- setValueComponent() - converts the pixel to greyscale based on value
- setIntensityComponent() - converts the pixel to greyscale based on intensity
- setLumaComponent() - converts the pixel to greyscale based on luma
- createCopy() - returns a copy of the Pixel
- setGreyscale() - returns a copy of the Pixel
- setSepia() - returns a copy of the Pixel
- clipValue() - clips value of a pixel, ensuring that it is between 0 and 255
inclusive. This method was added to the Pixel interface and made public so it
is easily accessible by the ImageProcessingModel

### RGBPixel 
RGBPixel implements the Pixel interface and represents a pixel that
is contains red, blue and green values. These colors are held in the colors array
where index 0 = red, index 1 = green, and index 2 = blue. RGBPixel implements all of
its inherited methods along with:
- setValue(int currentVal, int valAdd) - returns an int for the value of one of the
  pixel's values after increasing/decreasing while ensuring that this value, v is
  between 0 and 255 inclusive. If lesser than 0 or greater than 255, v is clipped at
  0 or 255 respectively. v is passed onto the brighten(int val) method
- findValue() - calculates the value of the pixel
- findIntensity() - calculates the intensity of the pixel
- findLuma() - calculates the luma of the pixel
- findGreyscale() - a helper to setGreyscale() that calculates the values
  of the pixel in greyscale
- findSepia() - a helper to setSepia() that calculates the values
  of the pixel when a sepia filter is applied
- equals(Object that) - Overrides the equals method to set two pixels to be equal to
  each other if their r values, g values and b values are equal
- hashCode - Overrides the hashCode() method


### Posn 
Posn represents a cell or pixel in a kernel. It has a row, column and a
multiple. It has only one method. This class was made to ensure a cleaner design and produce
more readable code with less duplication.
- isValid(int height, int width) - Confirms whether this pixel is valid, based on the
imageProcessingModel's height and width

## INSTRUCTIONS TO USE THE IMAGE PROCESSOR
- Run the main method in the Main class
- Copy paste entire script of commands below

## Sample Commands for Command Line
load res/test.png test
brighten test 50 brighter save res/brighter.png brighter
brighten test -50 darker save res/darker.png darker
horizontal-flip test hflip save res/horizontalFlip.png hflip
vertical-flip test vflip save res/verticalFlip.png vflip
red-component test testRed save res/testRed.png testRed
green-component test testGreen save res/testGreen.png testGreen
blue-component test testBlue save res/testBlue.png testBlue
value-component test testValue save res/testValue.png testValue
intensity-component test intensity save res/testIntensity.png intensity
luma-component test testLuma save res/testLuma.png testLuma
blur test blurred save res/testBlur.png blurred
sharpen test sharp save res/testSharpen.png sharp
make-greyscale test grey save res/testGrey.png grey
make-sepia test sepia save res/testSepia.png sepia\n

load ../../pics/Koala.ppm koala

## Citations
test4x4.ppm was obtained from (link example4p6) :
http://people.uncw.edu/tompkinsj/112/texnh/assignments/imageFormat.html

test.png in the res/ folder or any other image was created by us.
