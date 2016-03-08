
/**
 * QuickSort algorithm has O(n log n) complexity.
 * @author r585397
 *
 */
public class QuickSort {
	
	protected static int partition(int[] arr, int left, int right) {
		int i = left, j = right;
		int pivot = arr[( i + j )/2];
		while (i <= j) {
			while (arr[i] < pivot) {
				i++;
			}
			while(arr[j] > pivot) {
				j--;
			}
			if (i <= j) {
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
				j--;
			}
		}
		return i;
	}
	
	protected static void quickSort(int[] arr, int left, int right) {
		int pIndex = partition(arr, left, right);
		if (left < pIndex - 1) {
			quickSort(arr, left, pIndex - 1);
		}
		if (pIndex < right) {
			quickSort(arr, pIndex, right);
		}
	}
	
	public static void quickSort(int[] arr) {
		if (arr == null || arr.length == 0) return;
		int left = 0;
		int right = arr.length - 1;
		quickSort(arr,left,right);
	}

}
