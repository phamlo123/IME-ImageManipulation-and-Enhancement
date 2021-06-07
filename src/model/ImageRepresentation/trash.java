package model.ImageRepresentation;

public class trash {

  /**
   private static List<List<Integer>> helperForMultiplying(List<List<Integer>> channel,
   List<List<Double>> matrix) {
   int height = channel.size();
   for (int row = 0; row < height; row++) {
   for (int column = 0; column < channel.get(row).size(); column++) {
   double temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9;
   try {
   temp1 = channel.get(row - 1).get(column - 1) * matrix.get(0).get(0);
   } catch (IndexOutOfBoundsException e) {
   temp1 = 0;
   }
   try {
   temp2 = channel.get(row - 1).get(column) * matrix.get(0).get(1);
   } catch (IndexOutOfBoundsException e) {
   temp2 = 0;
   }
   try {
   temp3 = channel.get(row - 1).get(column + 1) * matrix.get(0).get(2);
   } catch (IndexOutOfBoundsException e) {
   temp3 = 0;
   }
   try {
   temp4 = channel.get(row).get(column - 1) * matrix.get(1).get(0);
   } catch (IndexOutOfBoundsException e) {
   temp4 = 0;
   }
   try {
   temp5 =channel.get(row).get(column) * matrix.get(1).get(1);
   } catch (IndexOutOfBoundsException e) {
   temp5=0;
   }
   try {
   temp6 = channel.get(row).get(column + 1) * matrix.get(1).get(2);
   }catch (IndexOutOfBoundsException e) {
   temp6=0;
   }
   try {
   temp7 = channel.get(row + 1).get(column - 1) * matrix.get(2).get(0);
   } catch (IndexOutOfBoundsException e) {
   temp7 = 0;
   }
   try {
   temp8 = channel.get(row + 1).get(column) * matrix.get(2).get(1);
   } catch (IndexOutOfBoundsException e) {
   temp8 = 0;
   }
   try {
   temp9 = channel.get(row + 1).get(column + 1) * matrix.get(2).get(2);
   } catch (IndexOutOfBoundsException e) {
   temp9=0;
   }
   double sum = temp1+temp2+temp3+temp4+temp5+temp6+temp7+temp8+temp9;

   int a;
   if(sum > 0) {
   a = (int) (Math.min(sum, 255));
   } else {
   a = (int) (Math.max(sum, 0));
   }
   channel.get(row).set(column, a);
   }
   }
   return channel;
   }
   */
}
