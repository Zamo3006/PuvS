package assignment01; 

import java.util.concurrent.Callable;

public class SumCallable implements Callable<Integer> {

	private int[] data;

	public SumCallable(int[] data) {
		this.data = data;
	}

	@Override
	public Integer call() throws Exception {
		return ParallelSum.seq_sum(data);
	}

}
