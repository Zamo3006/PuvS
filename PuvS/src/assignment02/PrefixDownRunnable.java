package assignment02;

public class PrefixDownRunnable implements Runnable{
	
	private int[] data;
	private int offset;
	private int start;
	private int end;
	
	public PrefixDownRunnable(int[] data, int offset, int start, int end) {
		this.data = data;
		this.offset = offset;
		this.start = start;
		this.end = end;
	}

	@Override
	public void run() {
		add();
		
	}
	
	private void add(){
		for (int i = Math.max(1,start); i < Math.min(end, data.length); i++) {
			data[i] += offset;
		}
	}
}
