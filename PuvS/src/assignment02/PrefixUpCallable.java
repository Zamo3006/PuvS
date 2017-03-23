package assignment02;

import java.util.concurrent.Callable;

public class PrefixUpCallable implements Callable<Integer>{
	
	private int[] data;
	private int start;
	private int end;
	
	public PrefixUpCallable(int[] data, int start, int end) {
		this.data = data;
		this.start = start;
		this.end = end;
	}

	@Override
	public Integer call() throws Exception {
		seqPrefixSum();
		return data[end-1];
	}
	
	private void seqPrefixSum() {
		for (int i = start+1; i < Math.min(end, data.length); i++) {
			data[i] += data[i - 1];
		}
	}
}
