package pattern.behavior.template;


public class TemplateTest {
	public static void test(String[] args){
		String exp = "8+8";
		AbstractCalculator cal = new Plus();
		int result = cal.calculate(exp, "\\+");
		System.out.println( result);
	}
	
	  static void main(String[] args)
      {

          // 对整型数组排序
          int[] intArray = new int[]{5, 3, 12, 8, 10};
          IntBubbleSorter sorter = new IntBubbleSorter();
          sorter.Sort(intArray);
          for(int item : intArray){
              System.out.println(item + " ");
          }
          System.out.println(" ");

          // 对浮点数排序
          float[] floatArray = new float[] { 5.0f, 3.0f, 12.0f, 8.0f, 10.0f };
          FloatBubbleSorter floatSorter = new FloatBubbleSorter();
          floatSorter.Sort(floatArray);
          for (float item : floatArray)
          {
              System.out.println(item + " ");
          }

      }
 }

