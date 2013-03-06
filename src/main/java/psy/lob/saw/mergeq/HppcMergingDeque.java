package psy.lob.saw.mergeq;

import com.carrotsearch.hppc.ObjectArrayDeque;
import com.carrotsearch.hppc.ObjectObjectOpenHashMap;

/**
 * A merging queue for values with keys. Null key/values are not welcome.
 * 
 * @author nitsanw
 * 
 * @param <KEY>
 * @param <VAL>
 */
public class HppcMergingDeque<KEY, VAL> implements MergingQueue<KEY, VAL> {
	private final ObjectArrayDeque<KEY> keyQueue = new ObjectArrayDeque<KEY>(1024);
	private final ObjectObjectOpenHashMap<KEY, VAL> lastValMap = 
			new ObjectObjectOpenHashMap<KEY, VAL>(1024);

	@Override
    public VAL poll() {
		return lastValMap.remove(keyQueue.removeFirst());
	}

	@Override
    public void offer(KEY key, VAL val) {
		assert key != null && val != null;
		VAL lastVal = lastValMap.put(key, val);
		if (lastVal == null) {
			keyQueue.addLast(key);
		}
	}

	@Override
    public boolean isEmpty() {
		return keyQueue.isEmpty();
	}
	@Override
	public void clear() {
	    lastValMap.clear();
	    keyQueue.clear();
	}
}
