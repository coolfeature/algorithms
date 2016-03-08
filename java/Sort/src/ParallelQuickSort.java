

public class ParallelQuickSort {
	
	public static void spawnSort (Runnable task) {
		Thread t = new Thread(task);
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static void quickSort(final int[] arr, final int left, final int right, final int cores) {
		final int pIndex = QuickSort.partition(arr, left, right);
		if (cores > 0) {
			spawnSort(new Runnable() {
				@Override
				public void run() {
					if (left < pIndex - 1) {
						quickSort(arr, left, pIndex - 1, cores - 1);
					}
					if (pIndex < right) {
						quickSort(arr, pIndex, right, cores - 1 );
					}
				}
			});	
		} else {
			if (left < pIndex - 1) {
				QuickSort.quickSort(arr, left, pIndex - 1);
			}
			if (pIndex < right) {
				QuickSort.quickSort(arr, pIndex, right);
			}
		}

	}
	
	public static void quickSort(int[] arr) {
		if (arr.length == 0) return;
		int left = 0;
		int right = arr.length - 1;
		int cores = Runtime.getRuntime().availableProcessors();
		quickSort(arr,left,right,cores);
	}

}
