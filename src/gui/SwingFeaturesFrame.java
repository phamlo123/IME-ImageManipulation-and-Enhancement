package gui;

import controller.ImageController;
import controller.NewController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.crypto.dsig.XMLObject;

/**
 * This class opens the main window, that has different elements illustrated in it. It also doubles
 * up as all the listeners for simplicity. Such a design is not recommended in general.
 */

public class SwingFeaturesFrame extends JFrame implements ItemListener, ListSelectionListener {

  private JPanel mainPanel;
  private JScrollPane mainScrollPane;
  private JPanel imagePanel;
  private JPanel filteringBoxesPanel;
  private JPanel checkerBoard;
  private JPanel dialogBoxesPanel;
  private JPanel otherOptions;
  private JPanel saveAnImage;
  private JPanel saveAllImage;
  private JPanel loadImagePanel;
  private JPanel loadAllImages;


  private JLabel checkboxDisplay;

  private JButton downSizePanelButton;
  private JButton mosaicPanelButton;
  private JButton sharpenButton;
  private JButton createGrayScaleButton;
  private JButton createSepiaButton;
  private JButton saveAllImagesButton;
  private JButton saveAnImageButton;
  private JButton blurButton;
  private JButton loadImagePanelButton;
  private JButton loadAllImagesButton;


  private JList<String> listOfStrings;
  private JList<Integer> listOfIntegers;


  public SwingFeaturesFrame() {
    super();
    setTitle("Image Processor");
    setSize(1000, 1000);

    mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BorderLayout());
    //scroll bars around this main panel
    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);


    imagePanel = new JPanel();

    TitledBorder title = BorderFactory.createTitledBorder("Image");
    title.setTitleJustification(TitledBorder.CENTER);
    imagePanel.setBorder(title);
    imagePanel.add(new JScrollPane());
    imagePanel.setLayout(new BorderLayout());
    imagePanel.setSize(new Dimension(500, 500));
    mainPanel.add(imagePanel, BorderLayout.CENTER);

    //Filtering Boxes
    filteringBoxesPanel = new JPanel();

    TitledBorder title2 = BorderFactory.createTitledBorder("Filtering Options");
    title2.setTitleJustification(TitledBorder.CENTER);
    filteringBoxesPanel.setBorder(title2);
    filteringBoxesPanel.setPreferredSize(new Dimension(150, 120));
    filteringBoxesPanel.setLayout(new GridLayout(18, 1, 0, 0));
    mainPanel.add(filteringBoxesPanel, BorderLayout.WEST);

    blurButton = new JButton("Blur");
    blurButton.setSize(new Dimension(150, 20));
    blurButton.setMaximumSize(new Dimension(150, 20));

    sharpenButton = new JButton("Sharpen");
    sharpenButton.setSize(new Dimension(150, 20));
    sharpenButton.setMaximumSize(new Dimension(150, 20));

    createGrayScaleButton = new JButton("Create Grayscale");
    createGrayScaleButton.setSize(new Dimension(150, 20));
    createGrayScaleButton.setMaximumSize(new Dimension(150, 20));

    createSepiaButton = new JButton("Create Sepia");
    createSepiaButton.setSize(new Dimension(150, 20));
    createSepiaButton.setMaximumSize(new Dimension(150, 20));

    downSizePanelButton = new JButton("Downsize");
    downSizePanelButton.setSize(new Dimension(150, 20));
    downSizePanelButton.setActionCommand("Downsize");

    downSizePanelButton.setMaximumSize(new Dimension(150, 20));

    mosaicPanelButton = new JButton("Create Mosaic");
    mosaicPanelButton.setSize(new Dimension(150, 20));
    mosaicPanelButton.setActionCommand("Mosaic");
    mosaicPanelButton.setMaximumSize(new Dimension(150, 20));

    filteringBoxesPanel.add(blurButton);
    filteringBoxesPanel.add(sharpenButton);
    filteringBoxesPanel.add(createGrayScaleButton);

    filteringBoxesPanel.add(createSepiaButton);
    filteringBoxesPanel.add(downSizePanelButton);
    filteringBoxesPanel.add(mosaicPanelButton);

    otherOptions = new JPanel();

    TitledBorder title4 = BorderFactory.createTitledBorder("Other Options");
    title4.setTitleJustification(TitledBorder.CENTER);
    otherOptions.setBorder(title4);
    otherOptions.setLayout(new GridLayout(0, 1, 10, 0));

    otherOptions.setPreferredSize(new Dimension(250, 100));
    mainPanel.add(otherOptions, BorderLayout.EAST);

    checkerBoard = new JPanel();
    checkerBoard.setLayout(new BorderLayout());
    checkerBoard.setPreferredSize(new Dimension(200, 200));

    //dialog boxes
    dialogBoxesPanel = new JPanel();
    TitledBorder title3 = BorderFactory.createTitledBorder("File");
    title3.setTitleJustification(TitledBorder.CENTER);
    dialogBoxesPanel.setBorder(title3);
    dialogBoxesPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    dialogBoxesPanel.setLayout(new FlowLayout());

    mainPanel.add(dialogBoxesPanel, BorderLayout.PAGE_END);



    //load an Image
    loadImagePanel = new JPanel();
    loadImagePanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(loadImagePanel);
    loadImagePanelButton = new JButton("Load an Image");
    loadImagePanelButton.setActionCommand("Input1");
    loadImagePanel.add(loadImagePanelButton);

    //Load All Images
    loadAllImages = new JPanel();
    loadAllImages.setLayout(new FlowLayout());
    dialogBoxesPanel.add(loadAllImages);
    loadAllImagesButton = new JButton("Load All Images");
    loadAllImagesButton.setActionCommand("Input2");
    loadAllImages.add(loadAllImagesButton);

    //Save an Image
    saveAnImage = new JPanel();
    saveAnImage.setLayout(new FlowLayout());
    dialogBoxesPanel.add(saveAnImage);
    saveAnImageButton = new JButton("Save");
    saveAnImageButton.setActionCommand("Input3");
    saveAnImage.add(saveAnImageButton);

    //Save all Images
    saveAllImage = new JPanel();
    saveAllImage.setLayout(new FlowLayout());
    dialogBoxesPanel.add(saveAllImage);
    saveAllImagesButton = new JButton("Save All");
    saveAllImagesButton.setActionCommand("Input4");
    saveAllImage.add(saveAllImagesButton);


  }




  @Override
  public void itemStateChanged(ItemEvent arg0) {
    // TODO Auto-generated method stub
    String who = ((JCheckBox) arg0.getItemSelectable()).getActionCommand();

    switch (who) {
      case "CB1":
        if (arg0.getStateChange() == ItemEvent.SELECTED) {
          checkboxDisplay.setText("Check box 1 was selected");
        } else {
          checkboxDisplay.setText("Check box 1 was deselected");
        }
        break;
      case "CB2":
        if (arg0.getStateChange() == ItemEvent.SELECTED) {
          checkboxDisplay.setText("Check box 2 was selected");
        } else {
          checkboxDisplay.setText("Check box 2 was deselected");
        }
        break;
      case "CB3":
        if (arg0.getStateChange() == ItemEvent.SELECTED) {
          checkboxDisplay.setText("Check box 3 was selected");
        } else {
          checkboxDisplay.setText("Check box 3 was deselected");
        }
        break;
      case "CB4":
        if (arg0.getStateChange() == ItemEvent.SELECTED) {
          checkboxDisplay.setText("Check box 4 was selected");
        } else {
          checkboxDisplay.setText("Check box 4 was deselected");
        }
        break;

      case "CB5":
        if (arg0.getStateChange() == ItemEvent.SELECTED) {
          checkboxDisplay.setText("Check box 5 was selected");
        } else {
          checkboxDisplay.setText("Check box 5 was deselected");
        }
        break;

    }
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    // We don't know which list called this callback, because we're using it
    // for two lists.  In practice, you should use separate listeners
    JOptionPane.showMessageDialog(SwingFeaturesFrame.this,
        "The source object is " + e.getSource(), "Source", JOptionPane.PLAIN_MESSAGE);
    // Regardless, the event information tells us which index was selected
    JOptionPane.showMessageDialog(SwingFeaturesFrame.this,
        "The changing index is " + e.getFirstIndex(), "Index", JOptionPane.PLAIN_MESSAGE);
    // This gets us the string value that's currently selected
    JOptionPane.showMessageDialog(SwingFeaturesFrame.this,
        "The current string item is " + this.listOfStrings.getSelectedValue(), "Selected string",
        JOptionPane.PLAIN_MESSAGE);
    // This gets us the integer value that's currently selected
    JOptionPane.showMessageDialog(SwingFeaturesFrame.this,
        "The current number item is " + this.listOfIntegers.getSelectedValue(), "Selected integer",
        JOptionPane.PLAIN_MESSAGE);
  }



  public void setListener(NewController controller) {

    downSizePanelButton.addActionListener(controller);
    mosaicPanelButton.addActionListener(controller);
    sharpenButton.addActionListener(controller);
    createGrayScaleButton.addActionListener(controller);
    createSepiaButton.addActionListener(controller);
    saveAllImagesButton.addActionListener(controller);
    saveAnImageButton.addActionListener(controller);
    blurButton.addActionListener(controller);
    loadImagePanelButton.addActionListener(controller);
    loadAllImagesButton.addActionListener(controller);
  }

  public void setText(String string) {
    JOptionPane.showInputDialog(string);
  }

}
