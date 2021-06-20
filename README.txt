
This docs contains an overview of the design for this project.

INTERFACES AND THEIR PURPOSES:
Model:
- ImageFormat: this interface contains all of the operation we wish to be able to perform with an Image implementation or object. At the high level, the goal of these methods is to be able to process an image in our internal representation of images (list of lists of colors, each representing a pixel). 
- ImagesOperations Interface: Therefore the purpose of this interface is to give clients a way to perform the high level filtering and coloring operations that they want to do on an Image object.
- Converter interface: this interface provides mechanisms to convert images of different format externally into our internal representation of an image, and vice-versa. This is like a bridge for our images and external images
- ImagingOperation: This interface purpose is to help the Images interface to dealing with the filtering process, therefore its only method is the helper method for applying a certain kernel into a given data type.

- Multilayers interface: this interface provides methods that client will want to call on a multilayers image object.

Controller:
- Image Controller Interface: This interface acts as the Controller client for our program, giving user ways to interactively communicate with the program

- Image Command: This interface utilize the Command design pattern that provides a connect point between the Model and the Controller




CLASSES AND THEIR PURPOSES:
- MultiLayeredImagesOperations (implements Multilayers interface): This class contains list of layers, each can either be empty or contain one imageFormat object. 
It also has a ImagesOperations that can be used to perform filtering operations on the image object on the current layer. 
 
- Image (implementing ImageFormat): this class helps process an internal image and store its information in a logical way. It contains methods and constructors to import image files and also to get color information from them in the form of integers. It also provides a way to compare two imageFormat objects

- ImagesOperationsImpl (implement ImagesOperations): this class extends the ImageImpl class. This is where the implementation of the filtering operation occur. 

- ImagingOPs(implemting ImagingOperation<ImageFormat>): This class is an abstract class so its purpose is to remove code duplications when processing the filtering operations in its subclasses. 

- ColoringOperation(extends ImagingOPs and implements ImagingOperation): This class implements a given matrix multiplication that is in the Instructions. 

- FilteringOperation(extends ImagingOPs and implements ImagingOperation): This class implements a given matrix multiplication that is in the Instructions. 

-Arithmetic: This is a util class that contains methods for multiplying matrices 

-ImageUtil: This is a util class that contains methods for reading a PPM file and create a PPM object from a given PPM file.

-Matrices: This is an Enum class that constructs different types of matrices that serve as kernel in filtering

-ImageOps: Enum class to help categorize kinds of filtering operations

-Coloring: Enum class to help categorize 3 kinds of basic color in a pixel

-FileFormat: Enum class to define supported image file type in our program

Controller:
- SimpleImageController(implements ImageController): this class implements the Controller interface and is responsible to creating command objects according to user  
inputs.
- Other command classes: these classes each is responsible for one public method in the MultiLayers interface as a way to delegate execution to the model.


CITATION FOR NORTHEASTERN.PPN: Image of Behrakis Health Sciences Center. Northeastern University. 11 Jun. 2021, https://www.northeastern.edu/graduate/tour-campuses/boston.

-sample.ppm: http://www.cs.cornell.edu/courses/cs664/2003fa/images/


NOTE: due to jpeg files automatically compress its data content, we cannot directly compare two JPEG images after exporting, even though their content are almost the same and they look identical. 

HOW TO USE JAR FILE: 
Make sure any files that you would like to use are in the same directory as the JAR file
Run the terminal with the command below: 
java -jar hwork5.jar 

A list of available command will appear. You can call any command avaiable. However, note that a command after successfully run will not show message that it was successfully run. If some inputs are incorrect it will ask the user to re-enter the input until they are correct. You can quit the program by typing 


Some design changes: 
- ImagesOperations is not parameterized any more. We now know we have a consistent internal representation of images. 
- We rename PPM to Image and completely got rid of PPM class. We put additional methods in ImageUtil class to help read and write a PPM image file since ImageIO does not support PPM
- We renamed some other classes and interfaces for better readability but their purposes are the same as before. 
- Most of the program is function now. However, due to time constraint and complexity in testing, we 
Have not but will implement exportAll function so that all the exported files are in the same folder as opposed to being in the main file.
