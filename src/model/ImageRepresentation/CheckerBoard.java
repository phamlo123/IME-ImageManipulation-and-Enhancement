package model.ImageRepresentation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class CheckerBoard {

  private int boardSize;
  private int squareSize;
  private Image image;
  private JFrame jFrame;
  private CheckerBoard(int boardSize, int squareSize, Image image) {
    this.boardSize = boardSize;
    this.squareSize = squareSize;
    this.image = image;
  }


  public static class CheckerBoardCreator {
    CheckerBoard checkerBoard;

    CheckerBoardCreator() {
      this.checkerBoard = create();
    }

    public static CheckerBoard create() {
      return new CheckerBoard (16, 16,
          new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB));
    }
  }

  public static CheckerBoard createCheckerBoard() {
   return CheckerBoardCreator.create();
  }

  private void paint() {
    Graphics graphics = image.getGraphics();
    graphics.setColor(Color.BLUE);
    graphics.fillRect(0, 0, boardSize, squareSize);

    graphics.setColor(Color.RED);
    for (int i = 0; i < boardSize; i++) {
      for (int j = i % 2; j < boardSize; j += 2) {
        graphics.fillRect(i * squareSize, j * squareSize, squareSize, squareSize);
      }
    }
  }

  public static void main(String[] arg) {
    CheckerBoard checkerBoard = createCheckerBoard();
    checkerBoard.paint();
  }
}
