import controller.ImageController;
import controller.SimpleImageController;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import model.imagerepresentation.multilayers.MultiLayeredImagesOperations;
import model.imagerepresentation.multilayers.MultiLayers;

/**
 * Main class to run JAR file.
 */
public class Main {


  /**
   * This is run point for our program, which initialize the controller.
   *
   * @param args program arg
   * @throws FileNotFoundException if file is not found.
   */
  public static void main(String[] args) throws FileNotFoundException {

    MultiLayers multiLayers = new MultiLayeredImagesOperations();
    ImageController controller = new SimpleImageController(multiLayers,
        new InputStreamReader(System.in), System.out);
    controller.processInteractive();
  }
}
