package assignment02;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelPrefixExec {
	private ExecutorService es;
	private int[] data;
	private List<Future<Integer>> fut = new LinkedList<Future<Integer>>();
	private int length;

	public ParallelPrefixExec(int length, int parts){
		es = Executors.newFixedThreadPool(parts);
		initializeArray(length);
		this.length = length;
	}
	
	public void resetArray(){
		initializeArray(length);
	}
	
	private  void initializeArray(int length) {
		data = new int[length];
		for (int i = 0; i < length; i++) {
			data[i] = i + 1;
		}
	}
	
	private void printArray() {
		for (int date : data) {
			System.out.print(date + " ");
		}
		System.out.println();
	}
	
	public void parallelPrefix(int parts){
		int partLength = data.length / parts;
		
		for (int i = 0; i < parts; i++) {
			Callable<Integer> c = new PrefixUpCallable(data, i * partLength, (i + 1) * partLength);
			Future<Integer> f = es.submit(c);
			fut.add(f);
		}
		int offset = 0;
		for (int i =  1; i < parts; i++) {
			try {
				offset += fut.get(i-1).get();
			} catch (Exception e) {
				e.printStackTrace();
			} 
			es.submit(new PrefixDownRunnable(data, offset, i * partLength, (i + 1) * partLength));
		}
		
	}
	
	public void shutdown(){
//		printArray();
		es.shutdown();
	}
}
