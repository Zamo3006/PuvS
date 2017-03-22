package assignment01;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class ParallelSum {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		int length = 1000;
		int parts = 10;

		int[] data = initializeArray(length);

		int seq_sum = seq_sum(data);
		int exec_serv_sum = exec_serv_sum(data, parts);
		int fork_join_sum = fork_join_sum(data, parts);
		int stream_sum = stream_sum(data);

		System.out.println("Seq_Sum: " + seq_sum);
		System.out.println("Executer_Service_Sum: " + exec_serv_sum);
		System.out.println("Fork_Join_Sum: " + fork_join_sum);
		System.out.println("Stream_Sum: " + stream_sum);
	}

	public static int[] initializeArray(int length) {
		int[] data = new Random().ints(length).toArray();
		return data;
	}

	public static int seq_sum(int[] data) {
		int sum = 0;
		for (int date : data) {
			sum += date;
		}
		return sum;
	}

	public static int exec_serv_sum(int[] data, int parts) throws InterruptedException, ExecutionException {
		ExecutorService es = Executors.newFixedThreadPool(4);
		List<Future<Integer>> f = new LinkedList<Future<Integer>>();
		int partLength = data.length / parts;
		partLength += data.length % parts == 0 ? 0 : 1;
		int sum = 0;

		for (int i = 0; i < parts; i++) {
			Callable<Integer> c = new SumCallable(data, i * partLength, (i + 1) * partLength - 1);
			Future<Integer> fut = es.submit(c);
			f.add(fut);
		}

		for (Future<Integer> fut : f) {
			sum += fut.get();
		}

		es.shutdown();
		return sum;
	}

	public static int fork_join_sum(int[] data, int parts) {
		ForkJoinPool pool = new ForkJoinPool(4);

		int sum = pool.invoke(new SumWorker(data, 0, data.length - 1, data.length / parts));

		pool.shutdown();
		return sum;
	}

	public static int stream_sum(int[] data) {
		int sum = Arrays.stream(data).parallel().reduce(0, Integer::sum);
		return sum;

	}
}
