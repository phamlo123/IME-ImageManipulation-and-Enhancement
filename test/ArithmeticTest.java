import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.ImageRepresentation.util.Arithmetic;
import model.Matrices;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArithmeticTest {

  @Test
  public void testHelperForMultiplying() {
    List<List<Integer>> listA = new ArrayList<>();
    List<List<Double>> listB = Matrices.MATRIX_FOR_BLURRING.getMatrix();
    for (int i = 0; i < 3; i++) {
      listA.add(Arrays.asList(150, 200, 170));
    }

    List<List<Integer>> listD =  Arithmetic.helperForMultiplying(listA, listB);

    List<List<Integer>> listC = new ArrayList<>();

    listC.add(Arrays.asList(93,135,101));
    listC.add(Arrays.asList(125, 180, 135));
    listC.add(Arrays.asList(93, 135, 101));

    for(int i = 0; i<3;i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(listC.get(i).get(j), listD.get(i).get(j));
      }
    }
  }
}
