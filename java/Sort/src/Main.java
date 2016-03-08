import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		testQuickSort();
		testParallelQuickSort();
	}

	private static int[] randomNumbers(int howMany) {
		int[] arr = new int[howMany];
		for (int i = 0; i < arr.length; i++) {
			int n = (int) (Math.random() * (howMany-1) + 1);
			arr[i] = n;
		}
		return arr;
	}
	
	private static void testQuickSort() {
		int[] arr = randomNumbers(10000);
		System.out.println(Arrays.toString(arr));
		long start = System.currentTimeMillis();
		QuickSort.quickSort(arr);
		long end = System.currentTimeMillis();
		System.out.println("[" + (end-start) + "]" + Arrays.toString(arr));
	}

	private static void testParallelQuickSort() {
		int[] arr = randomNumbers(10000);
		System.out.println(Arrays.toString(arr));
		long start = System.currentTimeMillis();
		ParallelQuickSort.quickSort(arr);
		long end = System.currentTimeMillis();
		System.out.println("[" + (end-start) + "]" + Arrays.toString(arr));
	}

}
