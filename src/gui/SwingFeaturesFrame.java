package gui;

import controller.NewController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * This class opens the main window, that has different elements illustrated in it. It also doubles
 * up as all the listeners for simplicity. Such a design is not recommended in general.
 */

public class SwingFeaturesFrame extends JFrame implements ItemListener, ListSelectionListener {

  private final JLabel imageLabel;

  private JPanel imagePanel;


  private JLabel checkboxDisplay;

  private final JButton downSizePanelButton;
  private final JButton mosaicPanelButton;
  private final JButton sharpenButton;
  private final JButton createGrayScaleButton;
  private final JButton createSepiaButton;
  private final JButton saveAllImagesButton;
  private final JButton saveAnImageButton;
  private final JButton blurButton;
  private final JButton loadImagePanelButton;
  private final JButton loadAllImagesButton;
  private final JScrollPane imageScroll;

  private final JButton createCheckerBoardButton;
  private final JButton createDefaultCheckerBoardButton;
  private final JButton makeVisibleButton;
  private final JButton makeInvisibleButton;
  private final JButton addLayerButton;
  private final JButton removeLayerButton;
  private final JButton setCurrenButton;

  private final JButton downSizePanelButton2;
  private final JButton mosaicPanelButton2;
  private final JButton sharpenButton2;
  private final JButton createGrayScaleButton2;
  private final JButton createSepiaButton2;
  private final JButton saveAllImagesButton2;
  private final JButton saveAnImageButton2;
  private final JButton blurButton2;
  private final JButton loadImagePanelButton2;
  private final JButton loadAllImagesButton2;

  private final JButton createCheckerBoardButton2;
  private final JButton createDefaultCheckerBoardButton2;
  private final JButton makeVisibleButton2;
  private final JButton makeInvisibleButton2;
  private final JButton addLayerButton2;
  private final JButton removeLayerButton2;
  private final JButton setCurrenButton2;

  private JLabel currentDisplay;
  private JLabel numLayerDisplay;

  final static String FILE = "File";
  final static String FILTERING = "Filtering";
  final static String GENERAL = "General";


  private JList<String> listOfStrings;
  private JList<Integer> listOfIntegers;


  public SwingFeaturesFrame() {
    super();
    setTitle("Image Processor");
    setSize(1000, 1000);

    JPanel mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BorderLayout());
    //scroll bars around this main panel
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    imagePanel = new JPanel();

    TitledBorder title = BorderFactory.createTitledBorder("Image");
    title.setTitleJustification(TitledBorder.CENTER);
    imagePanel.setBorder(title);
    imagePanel.add(new JScrollPane());
    imagePanel.setLayout(new BorderLayout());
    imagePanel.setSize(new Dimension(490, 500));
    mainPanel.add(imagePanel, BorderLayout.CENTER);

    imageLabel = new JLabel();
    imageScroll = new JScrollPane(imageLabel);
    imagePanel.add(imageScroll);

    //Filtering Boxes
    JPanel filteringBoxesPanel = new JPanel();

    TitledBorder title2 = BorderFactory.createTitledBorder("Filtering Options");
    title2.setTitleJustification(TitledBorder.CENTER);
    filteringBoxesPanel.setBorder(title2);
    filteringBoxesPanel.setPreferredSize(new Dimension(150, 120));
    filteringBoxesPanel.setLayout(new GridLayout(18, 1, 0, 0));
    mainPanel.add(filteringBoxesPanel, BorderLayout.WEST);

    blurButton = new JButton("Blur");
    blurButton.setSize(new Dimension(150, 20));
    blurButton.setActionCommand("Blur");
    blurButton.setMaximumSize(new Dimension(150, 20));

    sharpenButton = new JButton("Sharpen");
    sharpenButton.setSize(new Dimension(150, 20));
    sharpenButton.setActionCommand("Sharpen");
    sharpenButton.setMaximumSize(new Dimension(150, 20));

    createGrayScaleButton = new JButton("Create Grayscale");
    createGrayScaleButton.setSize(new Dimension(150, 20));
    createGrayScaleButton.setActionCommand("Gray");
    createGrayScaleButton.setMaximumSize(new Dimension(150, 20));

    createSepiaButton = new JButton("Create Sepia");
    createSepiaButton.setSize(new Dimension(150, 20));
    createSepiaButton.setActionCommand("Sepia");
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

    JPanel otherOptions = new JPanel();

    otherOptions.setLayout(new BorderLayout());

    otherOptions.setPreferredSize(new Dimension(250, 100));
    mainPanel.add(otherOptions, BorderLayout.EAST);

    JPanel checkerBoard = new JPanel();
    TitledBorder checkerBoardTitle = BorderFactory.createTitledBorder("Checker Board");
    checkerBoardTitle.setTitleJustification(TitledBorder.CENTER);
    checkerBoard.setBorder(checkerBoardTitle);
    checkerBoard.setLayout(new GridLayout(4, 2, 0, 0));
    checkerBoard.setPreferredSize(new Dimension(200, 200));

    createCheckerBoardButton = new JButton("Create New Checker Board");
    createCheckerBoardButton.setActionCommand("Checker");
    checkerBoard.add(createCheckerBoardButton);
    createDefaultCheckerBoardButton = new JButton("Create New Default Checker Board");
    createDefaultCheckerBoardButton.setActionCommand("Default");
    checkerBoard.add(createDefaultCheckerBoardButton);

    JPanel layers = new JPanel();
    TitledBorder layersTitle = BorderFactory.createTitledBorder("Layers");
    layersTitle.setTitleJustification(TitledBorder.CENTER);
    layers.setBorder(layersTitle);
    layers.setLayout(new GridLayout(10, 1, 0, 0));
    makeInvisibleButton = new JButton("Make Invisible");
    makeVisibleButton = new JButton("Make Visible");
    makeInvisibleButton.setActionCommand("Invisible");
    makeVisibleButton.setActionCommand("Visible");
    layers.add(makeInvisibleButton);
    layers.add(makeVisibleButton);
    addLayerButton = new JButton("Add a Layer");
    addLayerButton.setActionCommand("Add Layer");
    removeLayerButton = new JButton("Remove a Layer");
    removeLayerButton.setActionCommand("Remove Layer");
    layers.add(addLayerButton);
    layers.add(removeLayerButton);

    setCurrenButton = new JButton("Set Current");
    setCurrenButton.setActionCommand("Set Current");

    layers.add(setCurrenButton);

    otherOptions.add(checkerBoard, BorderLayout.SOUTH);
    otherOptions.add(layers, BorderLayout.CENTER);

    JPanel currentLayerPanel = new JPanel();
    currentLayerPanel.setLayout(new GridLayout(2, 2, 0, 0));

    JLabel currentIndex = new JLabel("Current Layer ");
    currentIndex.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    currentLayerPanel.add(currentIndex);

    currentDisplay = new JLabel("-1");
    currentDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    currentLayerPanel.add(currentDisplay);

    JLabel layerNumber = new JLabel("Number of Layers ");
    layerNumber.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    currentLayerPanel.add(layerNumber);

    numLayerDisplay = new JLabel("0");
    numLayerDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    currentLayerPanel.add(numLayerDisplay);

    otherOptions.add(currentLayerPanel, BorderLayout.NORTH);

    //dialog boxes
    JPanel dialogBoxesPanel = new JPanel();
    TitledBorder title3 = BorderFactory.createTitledBorder("File");
    title3.setTitleJustification(TitledBorder.CENTER);
    dialogBoxesPanel.setBorder(title3);
    dialogBoxesPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    dialogBoxesPanel.setLayout(new FlowLayout());

    mainPanel.add(dialogBoxesPanel, BorderLayout.PAGE_END);

    //load an Image
    JPanel loadImagePanel = new JPanel();
    loadImagePanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(loadImagePanel);
    loadImagePanelButton = new JButton("Load an Image");
    loadImagePanelButton.setActionCommand("Import");
    loadImagePanel.add(loadImagePanelButton);

    //Load All Images
    JPanel loadAllImages = new JPanel();
    loadAllImages.setLayout(new FlowLayout());
    dialogBoxesPanel.add(loadAllImages);
    loadAllImagesButton = new JButton("Load All Images");
    loadAllImagesButton.setActionCommand("Import All");
    loadAllImages.add(loadAllImagesButton);

    //Save an Image
    JPanel saveAnImage = new JPanel();
    saveAnImage.setLayout(new FlowLayout());
    dialogBoxesPanel.add(saveAnImage);
    saveAnImageButton = new JButton("Save");
    saveAnImageButton.setActionCommand("Export");
    saveAnImage.add(saveAnImageButton);

    //Save all Images
    JPanel saveAllImage = new JPanel();
    saveAllImage.setLayout(new FlowLayout());
    dialogBoxesPanel.add(saveAllImage);
    saveAllImagesButton = new JButton("Save All");
    saveAllImagesButton.setActionCommand("Export All");
    saveAllImage.add(saveAllImagesButton);

    loadImagePanelButton2 = new JButton("Load");
    loadImagePanelButton2.setActionCommand(loadImagePanelButton.getActionCommand());
    loadAllImagesButton2 = new JButton("Load All");
    loadAllImagesButton2.setActionCommand(loadAllImagesButton.getActionCommand());

    saveAnImageButton2 = new JButton("Save");
    saveAnImageButton2.setActionCommand(saveAnImageButton2.getActionCommand());

    saveAllImagesButton2 = new JButton("Save All");
    saveAllImagesButton2.setActionCommand(saveAllImagesButton.getActionCommand());

    blurButton2 = new JButton("Blur");
    blurButton2.setActionCommand(blurButton.getActionCommand());

    sharpenButton2 = new JButton("Sharpen");
    sharpenButton2.setActionCommand(sharpenButton.getActionCommand());

    createGrayScaleButton2 = new JButton("GrayScale");
    createGrayScaleButton2.setActionCommand(createGrayScaleButton.getActionCommand());

    createSepiaButton2 = new JButton("Sepia");
    createSepiaButton2.setActionCommand(createSepiaButton.getActionCommand());

    downSizePanelButton2 = new JButton("DownSize");
    downSizePanelButton2.setActionCommand(downSizePanelButton.getActionCommand());

    mosaicPanelButton2 = new JButton("Mosaic");
    mosaicPanelButton2.setActionCommand(mosaicPanelButton.getActionCommand());

    makeVisibleButton2 = new JButton("Visible");
    makeVisibleButton2.setActionCommand(makeVisibleButton.getActionCommand());

    makeInvisibleButton2 = new JButton("Invisible");
    makeInvisibleButton2.setActionCommand(makeInvisibleButton.getActionCommand());

    addLayerButton2 = new JButton("Add Layer");
    addLayerButton2.setActionCommand(addLayerButton.getActionCommand());

    removeLayerButton2 = new JButton("Remove Layer");
    removeLayerButton2.setActionCommand(removeLayerButton.getActionCommand());

    setCurrenButton2 = new JButton("Set Current");
    setCurrenButton2.setActionCommand(setCurrenButton.getActionCommand());

    createCheckerBoardButton2 = new JButton("Create Checker");
    createCheckerBoardButton2.setActionCommand(createCheckerBoardButton.getActionCommand());

    createDefaultCheckerBoardButton2 = new JButton("Create Default Checker");
    createDefaultCheckerBoardButton2
        .setActionCommand(createDefaultCheckerBoardButton.getActionCommand());

    //menu

    JTabbedPane menu = new JTabbedPane();
    JPanel file = new JPanel();
    file.setLayout(new FlowLayout());
    file.add(loadImagePanelButton2);
    file.add(loadAllImagesButton2);
    file.add(saveAnImageButton2);
    file.add(saveAllImagesButton2);

    JPanel filtering = new JPanel();
    filtering.setLayout(new FlowLayout());
    filtering.add(blurButton2);
    filtering.add(sharpenButton2);
    filtering.add(createGrayScaleButton2);
    filtering.add(createSepiaButton2);
    filtering.add(downSizePanelButton2);
    filtering.add(mosaicPanelButton2);

    JPanel general = new JPanel();
    general.setLayout(new FlowLayout());
    general.add(makeVisibleButton2);
    general.add(makeInvisibleButton2);
    general.add(addLayerButton2);
    general.add(removeLayerButton2);
    general.add(setCurrenButton2);
    general.add(createCheckerBoardButton2);
    general.add(createDefaultCheckerBoardButton2);

    menu.addTab(FILE, file);
    menu.addTab(FILTERING, filtering);
    menu.addTab(GENERAL, general);

    mainPanel.add(menu, BorderLayout.NORTH);

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
    createCheckerBoardButton.addActionListener(controller);
    createDefaultCheckerBoardButton.addActionListener(controller);
    makeVisibleButton.addActionListener(controller);
    makeInvisibleButton.addActionListener(controller);
    addLayerButton.addActionListener(controller);
    removeLayerButton.addActionListener(controller);
    setCurrenButton.addActionListener(controller);

    downSizePanelButton2.addActionListener(controller);
    mosaicPanelButton2.addActionListener(controller);
    sharpenButton2.addActionListener(controller);
    createGrayScaleButton2.addActionListener(controller);
    createSepiaButton2.addActionListener(controller);
    saveAllImagesButton2.addActionListener(controller);
    saveAnImageButton2.addActionListener(controller);
    blurButton2.addActionListener(controller);
    loadImagePanelButton2.addActionListener(controller);
    loadAllImagesButton2.addActionListener(controller);
    createCheckerBoardButton2.addActionListener(controller);
    createDefaultCheckerBoardButton2.addActionListener(controller);
    makeVisibleButton2.addActionListener(controller);
    makeInvisibleButton2.addActionListener(controller);
    addLayerButton2.addActionListener(controller);
    removeLayerButton2.addActionListener(controller);
    setCurrenButton2.addActionListener(controller);
  }

  public String getText(String string) {
    return JOptionPane.showInputDialog(string);
  }

  public void setText(String string) {
    JOptionPane.showMessageDialog(this, string);
  }

  public void setImage(BufferedImage image) {
    imageLabel.setIcon(new ImageIcon(image));
    imageScroll.setViewportView(imageLabel);
    imagePanel.requestFocus();
  }


  public void saveFile() {
    final JFileChooser fchooser = new JFileChooser(".");
    int retvalue = fchooser.showSaveDialog(SwingFeaturesFrame.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
    }
  }


  public void setCurrentDisplay(String currentDisplay, String numLayer) {
    this.currentDisplay.setText(currentDisplay);
    this.numLayerDisplay.setText(numLayer);
  }
}
