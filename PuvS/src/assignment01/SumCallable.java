package assignment01;

import java.util.concurrent.Callable;

public class SumCallable implements Callable<Integer> {

	private int[] data;
	private int start;
	private int end;

	public SumCallable(int[] data, int start, int end) {
		this.data = data;
		this.start = start;
		this.end = end;
	}

	@Override
	public Integer call() throws Exception {
		int sum = 0;
		for (int i = start; i <= Math.min(end, data.length - 1); i++) {
			sum += data[i];
		}
		return sum;
	}

}
