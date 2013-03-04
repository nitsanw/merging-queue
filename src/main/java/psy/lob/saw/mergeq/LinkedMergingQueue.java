package psy.lob.saw.mergeq;

import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * A merging queue for values with keys. Null key/values are not welcome. Implemented
 * using a LinkedHashMap.
 * 
 * @author nitsanw
 *
 * @param <KEY>
 * @param <VAL>
 */
public class LinkedMergingQueue<KEY, VAL>  implements MergingQueue<KEY, VAL>{
	private final LinkedHashMap<KEY, VAL> lastValMap = new LinkedHashMap<KEY, VAL>(1024);

	@Override
	public VAL poll() {
		if (lastValMap.isEmpty())
			return null;
		Iterator<KEY> iterator = lastValMap.keySet().iterator();
		return lastValMap.remove(iterator.next());
	}
	
	@Override
	public void offer(KEY key, VAL val) {
		assert key != null && val != null;
		lastValMap.put(key, val);
	}
	
	@Override
	public boolean isEmpty() {
		return lastValMap.isEmpty();
	}
	
	@Override
	public void clear() {
	    lastValMap.clear();
	}
}
