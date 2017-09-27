import org.junit.Assert;
import org.junit.Test;


public class primworkTest {


	@Test
	public void testGcd() {
		int[] array=new int[]{25,2};
		Assert.assertArrayEquals(array,primwork.gcd(50, 4));
		int[] arr=new int[]{2,25};
		Assert.assertArrayEquals(arr,primwork.gcd(4, 50));
	}

	@Test
	public void testCal() {
		int[] array1=new int[]{5,6};
		int[] array2=new int[]{1,6};
		int[] array3=new int[]{-1,6};
		int[] array4=new int[]{1,6};
		int[] array5=new int[]{2,3};
		Assert.assertArrayEquals(array1,primwork.cal(1, 2, 1, 3, 0));
		Assert.assertArrayEquals(array2,primwork.cal(1, 2, 1, 3, 1));
		Assert.assertArrayEquals(array3,primwork.cal(1, 3, 1, 2, 1));
		Assert.assertArrayEquals(array4,primwork.cal(1, 2, 1, 3, 2));
		Assert.assertArrayEquals(array5,primwork.cal(1, 3, 1, 2, 3));
	}

	


	@Test
	public void testCalculate() {
		int[] arr1=new int[]{3,2};
		int[][] c=new int[][]{{1,1},{1,2},{0,0}};
		Assert.assertArrayEquals(arr1, primwork.calculate(c, 1, 1));
	}

	@Test
	public void testPrecedence() {
		Assert.assertEquals(0, primwork.precedence(0, 1));
		Assert.assertEquals(0, primwork.precedence(0, 0));
		Assert.assertEquals(-1, primwork.precedence(1, 2));
		Assert.assertEquals(1, primwork.precedence(2, 1));
	}

	//@Test
	//public void testMain() {
		//String str=1000+"";
		//String[] s=new String[]{str};
		//Assert.assertEquals(1, primwork.main(s));
	//}

}
