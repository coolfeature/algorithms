import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		int items = 100000;
		quickSort(items); //100000 : 15
		parallelQuickSortFutureList(items); //100000 : 17
		parallelQuickSortThreadJoin(items); //100000 : 41
	}

	private static int[] randomNumbers(int howMany) {
		int[] arr = new int[howMany];
		for (int i = 0; i < arr.length; i++) {
			int n = (int) (Math.random() * (howMany-1) + 1);
			arr[i] = n;
		}
		return arr;
	}
	
	private static void quickSort(int items) {
		int[] arr = randomNumbers(items);
		System.out.println(Arrays.toString(arr));
		long start = System.currentTimeMillis();
		QuickSort.quickSort(arr);
		long end = System.currentTimeMillis();
		System.out.println("[" + (end-start) + "]" + Arrays.toString(arr));
	}

	private static void parallelQuickSortFutureList(int items) {
		int[] arr = randomNumbers(items);
		System.out.println(Arrays.toString(arr));
		long start = System.currentTimeMillis();
		ParallelQuickSortFutureList.quickSort(arr);
		long end = System.currentTimeMillis();
		System.out.println("[" + (end-start) + "]" + Arrays.toString(arr));
		System.out.println("Second highest: " + get2ndHighestUnique(arr));
	}
	
	private static void parallelQuickSortThreadJoin(int items) {
		int[] arr = randomNumbers(items);
		System.out.println(Arrays.toString(arr));
		long start = System.currentTimeMillis();
		ParallelQuickSortThreadJoin.quickSort(arr);
		long end = System.currentTimeMillis();
		System.out.println("[" + (end-start) + "]" + Arrays.toString(arr));
		System.out.println("Second highest: " + get2ndHighestUnique(arr));
	}
	
	private static int get2ndHighestUnique(int[] sortedArr) {
		int index = sortedArr.length - 2;
		while(index >= 0) {
			if (sortedArr[index] == sortedArr[index-1] || sortedArr[index] == sortedArr[index+1]) {
				index--;
			} else {
				return sortedArr[index];
			}
		}
		throw new IllegalStateException("No unique values");
	}

}
