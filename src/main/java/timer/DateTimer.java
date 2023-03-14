package timer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;

public class DateTimer  implements Timer {
	
	ArrayList<Integer> lapsTimes;
	Iterator<Integer> it;
	
	public DateTimer(TreeSet<Integer> dates) {
		this.lapsTimes = new ArrayList<>();
		Integer last;
		Integer current=0;
		
		Iterator<Integer> itr = dates.iterator();
		while (itr.hasNext()) {
			last = current;
			current = itr.next();
			this.lapsTimes.add(current-last);
		}
		this.it = this.lapsTimes.iterator();

	}
	
	public DateTimer(ArrayList<Integer> lapsTimes) {
		this.lapsTimes = new ArrayList<>(lapsTimes);
		this.it = this.lapsTimes.iterator();
	}

	@Override
	public boolean hasNext() {
		return it.hasNext();
	}

	@Override
	public Integer next() {
		return it.next();
	}

}
