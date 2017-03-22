package assignment01;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

@SuppressWarnings("serial") 
public class SumWorker extends RecursiveTask<Integer> {

	private int[] data;
	private int partSize;

	public SumWorker(int[] data, int partSize) {
		this.data = data;
		this.partSize = partSize;
	}

	@Override
	protected Integer compute() {
		int sum = 0;
		if (data.length <= partSize) {
			sum = ParallelSum.seq_sum(data);
		} else {
			SumWorker sum1 = new SumWorker(Arrays.copyOfRange(data, 0, data.length / 2), partSize);
			SumWorker sum2 = new SumWorker(Arrays.copyOfRange(data, data.length / 2 , data.length ), partSize);
			sum1.fork();
			sum2.fork();

			sum = sum1.join() + sum2.join();
		}
		return sum;
	}

}
