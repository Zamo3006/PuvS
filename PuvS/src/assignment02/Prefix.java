package assignment02;

import java.util.Arrays;

public class Prefix {

	public static void main(String[] args) {
		long start = 0;
		long end = 0;
		int length = (int) Math.pow(2, 10);
		int max = 1000;
		ParallelPrefix pp = new ParallelPrefix();
		ParallelPrefixExec pe = new ParallelPrefixExec(length, 4);
//		long[] times = new long[max];
//		for (int i = 0; i < max; i++) {
//			pe.resetArray();
//			start = System.nanoTime();
//			pe.parallelPrefix(4);
//			end = System.nanoTime();
//			long exTime = end - start;
//			times[i] = exTime;
//		}
//		pe.shutdown();
//		Arrays.sort(times);
//		System.out.println("Executor-Median, 4 Threads: " + times[0]);
//		
//		ParallelPrefixExec pe2 = new ParallelPrefixExec(length, 8);
//		long[] times3 = new long[max];
//		for (int i = 0; i < max; i++) {
//			pe2.resetArray();
//			start = System.nanoTime();
//			pe2.parallelPrefix(40);
//			end = System.nanoTime();
//			long exTime = end - start;
//			times3[i] = exTime;
//		}
//		pe2.shutdown();
//		Arrays.sort(times3);
//		System.out.println("Executor-Median, 8 Threads: " + times3[0]);
//
		long[] times2 = new long[max];
		for (int i = 0; i < max; i++) {
			pp.initializeArray(length);
			start = System.nanoTime();
			pp.seqPrefixSum();
			end = System.nanoTime();
			long seqTime = end - start;
			times2[i] = seqTime;
		}
		Arrays.sort(times2);
		System.out.println("Seq-Median: " + times2[0]);
		
		long[] times4 = new long[max];
		for (int i = 0; i < max; i++) {
			pp.initializeArray(length);
			start = System.nanoTime();
			 pp.streamPrefix();
			 end = System.nanoTime();
			 long parTime = end - start;
			times4[i] = parTime;
		}
		Arrays.sort(times4);
		System.out.println("Stream-Median: " + times4[0]);


		
		// pp.initializeArray(length);
		//
		// // pp.printArray();
		// start = System.nanoTime();
		// pp.streamPrefix();
		// end = System.nanoTime();
		// long parTime = end - start;
		// // pp.printArray();
		// System.out.println(parTime);
	}
}
