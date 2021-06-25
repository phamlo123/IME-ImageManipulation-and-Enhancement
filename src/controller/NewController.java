package controller;

import gui.SwingFeaturesFrame;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.imagerepresentation.multilayers.MultiLayers;

public class NewController implements ActionListener, Features {

  private SwingFeaturesFrame swingFeaturesFrame;
  private final MultiLayers model;

  public NewController(MultiLayers model, SwingFeaturesFrame swingFeaturesFrame)
      throws IllegalArgumentException {
    if (model == null || swingFeaturesFrame == null) {
      throw new IllegalArgumentException();
    }
    this.swingFeaturesFrame = swingFeaturesFrame;
    this.model = model;
    swingFeaturesFrame.setListener(this);
  }


  public void actionPerformed(ActionEvent arg0) {
    // TODO Auto-generated method stub
    switch (arg0.getActionCommand()) {
      case "password button":
        pDisplay.setText(new String(pfield.getPassword()));
        pfield.setText("");
        break;
      case "RB1":
        radioDisplay.setText("Radio button 1 was selected");
        break;

      case "RB2":
        radioDisplay.setText("Radio button 2 was selected");
        break;

      case "RB3":
        radioDisplay.setText("Radio button 3 was selected");
        break;

      case "RB4":
        radioDisplay.setText("Radio button 4 was selected");
        break;

      case "Size options":
        if (arg0.getSource() instanceof JComboBox) {
          JComboBox<String> box = (JComboBox<String>) arg0.getSource();
          comboboxDisplay.setText("You selected: " + (String) box.getSelectedItem());

        }
        break;
      case "Color chooser":
        Color col = JColorChooser.showDialog(SwingFeaturesFrame.this, "Choose a color",
            colorChooserDisplay.getBackground());
        colorChooserDisplay.setBackground(col);
        break;
      case "Open file": {
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG & GIF Images", "jpg", "gif");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(SwingFeaturesFrame.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          fileOpenDisplay.setText(f.getAbsolutePath());
        }
      }
      break;
      case "Save file": {
        final JFileChooser fchooser = new JFileChooser(".");
        int retvalue = fchooser.showSaveDialog(SwingFeaturesFrame.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          fileSaveDisplay.setText(f.getAbsolutePath());
        }
      }
      break;
      case "Message":
        JOptionPane.showMessageDialog(SwingFeaturesFrame.this, "This is a demo message", "Message",
            JOptionPane.PLAIN_MESSAGE);
        JOptionPane.showMessageDialog(SwingFeaturesFrame.this, "You are about to be deleted.",
            "Last Chance", JOptionPane.WARNING_MESSAGE);
        JOptionPane.showMessageDialog(SwingFeaturesFrame.this, "You have been deleted.", "Too late",
            JOptionPane.ERROR_MESSAGE);
        JOptionPane
            .showMessageDialog(SwingFeaturesFrame.this, "Please start again.", "What to do next",
                JOptionPane.INFORMATION_MESSAGE);
        break;
      case "Input":
        swingFeaturesFrame.setText("Please enter your username");
        break;
      case "Input1":
        swingFeaturesFrame.setText("Please enter image file name");
        break;
      case "Input2":
        swingFeaturesFrame.setText("Please enter text file name");
        break;
      case "Input3":
        swingFeaturesFrame.setText("Please enter name for this image");
        break;
      case "Input4":
        swingFeaturesFrame
            .setText("Please enter base name for these images");
        break;
      case "Mosaic":
        swingFeaturesFrame
            .setText(JOptionPane.showInputDialog("Please enter the number of seeds"));
        break;
      case "Option": {
        String[] options = {"Uno", "Dos", "Tres", "Cuatro", "Cinco", "seis", "siete", "ocho",
            "nueve", "dies"};
      }
      break;
    }
  }

  @Override
  public void blur() {

  }

  @Override
  public void sharpen() {

  }
}


