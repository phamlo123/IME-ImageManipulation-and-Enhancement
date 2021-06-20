import controller.ImageController;
import controller.SimpleImageController;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import model.ImageRepresentation.multiLayers.MultiLayeredImagesOperations;
import model.ImageRepresentation.multiLayers.MultiLayers;

public class Main {


  public static void main(String[] args) throws FileNotFoundException {

    MultiLayers multiLayers = new MultiLayeredImagesOperations();
    ImageController controller = new SimpleImageController(multiLayers,
        new InputStreamReader(System.in), System.out);
    controller.processInteractive();
  }
}
