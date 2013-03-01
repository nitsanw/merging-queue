package com.pls.mergeq;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

/**
 * A merging queue for values with keys. Null key/values are not welcome.
 * 
 * @author nitsanw
 *
 * @param <KEY>
 * @param <VAL>
 */
public class MergingDeque<KEY, VAL> {
	private final ArrayDeque<KEY> keyQueue = new ArrayDeque<KEY>();
	private final Map<KEY, VAL> lastValMap = new HashMap<KEY, VAL>();

	public VAL poll() {
		if (keyQueue.isEmpty())
			return null;
		return lastValMap.remove(keyQueue.pollFirst());
	}
	public VAL pop() {
		if (keyQueue.isEmpty())
			return null;
		return lastValMap.remove(keyQueue.pollLast());
	}
	public void offer(KEY key, VAL val) {
		assert key != null && val != null;
		VAL lastVal = lastValMap.put(key, val);
		if (lastVal == null) {
			keyQueue.add(key);
		}
	}
	public boolean isEmpty() {
		return keyQueue.isEmpty();
	}
}
