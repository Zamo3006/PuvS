package assignment02;

import java.util.Arrays;
import java.util.function.IntBinaryOperator;

public class ParallelPrefix {
	private int[] data;
	private IntBinaryOperator op = new MyIntOperator();
	
    public class MyIntOperator implements IntBinaryOperator {
        @Override
        public int applyAsInt(int left, int right) {
            return left+right;
        }
    }
 

	public void initializeArray(int length) {
		data = new int[length];
		for (int i = 0; i < length; i++) {
			data[i] = i + 1;
		}
	}

	public void printArray() {
		for (int date : data) {
			System.out.print(date + " ");
		}
		System.out.println();
	}
	
	public void printArray(int i) {
		System.out.println(data[data.length-1]);
	}

	public void seqPrefixSum() {
		for (int i = 1; i < data.length; i++) {
			data[i] += data[i - 1];
		}
	}
	
	public void streamPrefix(){
		Arrays.parallelPrefix(data, op);
	}
	public void parPrefixSum() {
		for(int d = 0; d<Math.log(data.length)/Math.log(2);d++){
			
		}
		
		data[data.length-1]=0;
		
		for(int d = (int) (Math.log(data.length)/Math.log(2)); d>0;d--){
			
		}
	}
//	 for d = 0 to log2(n) – 1 do 
//	      for all k = 0 to n – 1 by 2^(d+1) in parallel do 
//	           x[k +  2^(d+1) – 1] = x[k +  2^d  – 1] + x[k +  2^(d+1) – 1]
	
//	 x[n – 1] = 0
//			 for d = log2(n) – 1 down to 0 do 
//			       for all k = 0 to n – 1 by 2^(d+1) in parallel do 
//			            t = x[k +  2^d  – 1]
//			            x[k +  2^d  – 1] = x[k +  2^(d+1) – 1]
//			            x[k +  2^(d+1) – 1] = t +  x[k +  2^(d+1) – 1]
}
