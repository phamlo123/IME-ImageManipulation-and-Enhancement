package gui;

import controller.NewController;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import model.imagerepresentation.Image;
import model.imagerepresentation.ImageFormat;
import model.imagerepresentation.multilayers.MultiLayeredImagesOperations;
import model.imagerepresentation.multilayers.MultiLayers;

/**
 * This example shows the different user interface elements in Java Swing. Please use these examples
 * as guidelines only to see how to use them. This example has not been designed very well, it is
 * only meant to illustrate code snippets.
 * <p>
 * Feel free to try out different modifications to see how the program changes
 */

public class SwingFeatures {

  public static void main(String[] args) {

    /*
    SwingFeaturesFrame.setDefaultLookAndFeelDecorated(false);
    SwingFeaturesFrame frame = new SwingFeaturesFrame();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    try {
      // Set cross-platform Java L&F (also called "Metal")
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

      //UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName());

      //   UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
      //    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
      //    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
      //    {
      //       if ("Nimbus".equals(info.getName())) {
      //          UIManager.setLookAndFeel(info.getClassName());
      //         break;
      //    }
      // }
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

     */

    //MultiLayers images = new MultiLayeredImagesOperations(new ArrayList<>(
      //  Arrays.asList(new Image("Koala.jpg"), new Image("Jellyfish.jpg"))));
    MultiLayers images = new MultiLayeredImagesOperations();
    SwingFeaturesFrame view = new SwingFeaturesFrame();
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
