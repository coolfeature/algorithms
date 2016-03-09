
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;;

public class ParallelQuickSortFutureList {
	
	private final ExecutorService executor;
	private final int cores;
	private final List<Future> futures;
	
	public ParallelQuickSortFutureList (int cores) {
		this.cores = cores;
		this.futures = Collections.synchronizedList(new ArrayList<Future>());
		this.executor = Executors.newFixedThreadPool(cores);
	}

	protected void quickSort(final int[] arr, final int left, final int right) {
		quickSort(arr, left, right, this.cores);
		while(!this.futures.isEmpty()) {
			try {
				this.futures.get(0).get();
				this.futures.remove(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	private void quickSort(final int[] arr, final int left, final int right, final int cores) {
		final int pIndex = QuickSort.partition(arr, left, right);
		if (cores > 0) {
			this.futures.add(executor.submit(new Runnable() {
				@Override
				public void run() {
					if (left < pIndex - 1) {
						quickSort(arr, left, pIndex - 1, cores - 1);
					}
					if (pIndex < right) {
						quickSort(arr, pIndex, right, cores - 1 );
					}
				}
			}));	
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
		int cores = Runtime.getRuntime().availableProcessors();
		ParallelQuickSortFutureList sorter = new ParallelQuickSortFutureList(cores);
		int left = 0;
		int right = arr.length - 1;
		sorter.quickSort(arr, left, right);
	}

}
