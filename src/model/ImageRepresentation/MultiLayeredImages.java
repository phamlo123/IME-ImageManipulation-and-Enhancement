package model.ImageRepresentation;

import java.util.List;
import java.util.Objects;

public class MultiLayeredImages implements MultiLayers{
  private List<ImageFormat> imageLayers;


  public MultiLayeredImages(List<ImageFormat> imageLayers) {
    Objects.requireNonNull(imageLayers);
    this.imageLayers = imageLayers;
  }


  @Override
  public void saveImages() {

  }

  @Override
  public void loadImages() {

  }

  @Override
  public void addLayer(ImageFormat image) throws IllegalArgumentException {

  }

  @Override
  public void removeLayer(ImageFormat image) throws IllegalArgumentException {

  }
  
  
}
