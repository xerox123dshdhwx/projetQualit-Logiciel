package timer;

public class PeriodicTimer implements Timer {

	private int period;
	private int next;
	private RandomTimer moreOrLess = null;
	
	public PeriodicTimer(int at) {
		this.period = at;
		this.next = at;
	}
	

	
	public PeriodicTimer(int period, int at) {
		this.period = period;
		this.next = at;
	}

	
	public int getPeriod() {
		return this.period;
	}
	
	
	@Override
	public Integer next() {
		
		int i =  this.next;
		
		if(this.moreOrLess != null) {
			this.next = this.period + (int)(this.moreOrLess.next() - this.moreOrLess.getMean());
		}else {
			this.next = this.period;
		}
		
		return i;
	}


	@Override
	public boolean hasNext() {
		return true;
	}

}
