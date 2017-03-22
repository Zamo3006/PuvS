package assignment01;

import java.util.concurrent.RecursiveTask;

@SuppressWarnings("serial")
public class SumWorker extends RecursiveTask<Integer> {

	private int[] data;
	private int partSize;
	private int start;
	private int end;

	public SumWorker(int[] data, int start, int end, int partSize) {
		this.data = data;
		this.partSize = partSize;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		int sum = 0;
		if (end - start <= partSize) {
			for (int i = start; i <= end; i++) {
				sum += data[i];
			}
		} else {
			int middle = start + (end - start) / 2;
			SumWorker sum1 = new SumWorker(data, start, middle - 1, partSize);
			SumWorker sum2 = new SumWorker(data, middle, end, partSize);
			sum1.fork();
			sum2.fork();

			sum = sum1.join() + sum2.join();
		}
		return sum;
	}

}
