import controller.NewController;
import gui.ImageViewImpl;
import java.io.FileNotFoundException;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
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
  public static void main(String[] args) {

    MultiLayers images = new MultiLayeredImagesOperations();
    ImageViewImpl view = new ImageViewImpl();
    NewController controller = new NewController(images, view);
    view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    view.setVisible(true);
    try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    } catch (UnsupportedLookAndFeelException e) {
      // handle exception
    } catch (ClassNotFoundException e) {
      // handle exception
    } catch (InstantiationException e) {
      // handle exception
    } catch (IllegalAccessException e) {
      // handle exception
    } catch (Exception e) {
    }
  }
}
