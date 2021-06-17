
This docs contains an overview of the design for this project.

List of interfaces and their purposes:
- PpmInterface: this interface contains all of the operation we wish to be able to perform with a PPM implementation or object. At the high level, the goal of these methods is to be able to process a PPM file and its information in a readable manner
- Images Interface: this interface is parameterized with an unknown data type T, which will give us flexibility in the future to be able to cover other types of images (currently only PPM) that we wish to perform our filtering onto. Therefore the purpose of this interface is to give clients a way to access the high level operations that they want to do on a PPM file.
- ImagingOperation: This interface purpose is to help the Images interface to dealing with the filtering process, therefore its only method is the helper method for applying a certain kernel into a given data type.

Classes and their purposes:
- PPM (implementing PpmInterface): this class helps process a PPM file and store its information in a logical way. It contains methods and constructors to export a PPM file and also to get color information from a PPM object in the form of integers. It also provides a way to compare two PPM object
- ImageImpl (implementing Images): this class is an abstract class and is parameterized by a type T so its only purpose is to make sure there is no code duplication in the future where there is a different version of images that are similar to PPM that clients want the model to support with the same type of filtering
- ImageImplPPM (implement Images): this class extends the ImageImpl class parameterized by PPM. This is where the implementation of the filtering operation occur. 
- ImagingOpsPPM(implemting ImagingOperation<PPM>): This class is an abstract class so its purpose is to remove code duplications when processing the filtering operations in its subclasses. 
- FilteringOPearitonPPM(extends ImagingOpsPPM and implements ImagingOperation): This class implements a given matrix multiplication that is in the Instructions. 
- ColoringOpetionsPPM(extends ImagingOpsPPM and implements ImagingOperation): This class implements a given matrix multiplication that is in the Instructions. 
-Arithmetic: This is a util class that contains methods for multiplying matrices 
-ImageUtil: This is a util class that contains methods for reading a PPM file and create a PPM object from a given PPM file.
-Matrices: This is an Enum class that constructs different types of matrices that serve as kernel in filtering
-ImageOps: Enum class to help categorize kinds of filtering operations
-Coloring: Enum class to help categorize 3 kinds of basic color in a pixel

CITATION FOR NORTHEASTERN.PPN: Image of Behrakis Health Sciences Center. Northeastern University. 11 Jun. 2021, https://www.northeastern.edu/graduate/tour-campuses/boston.

-sample.ppm: http://www.cs.cornell.edu/courses/cs664/2003fa/images/

