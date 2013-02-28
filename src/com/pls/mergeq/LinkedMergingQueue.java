package com.pls.mergeq;

import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * A merging queue for values with keys. Null key/values are not welcome.
 * 
 * @author nitsanw
 *
 * @param <KEY>
 * @param <VAL>
 */
public class LinkedMergingQueue<KEY, VAL> {
	private final LinkedHashMap<KEY, VAL> lastValMap = new LinkedHashMap<KEY, VAL>();

	public VAL poll() {
		if (lastValMap.isEmpty())
			return null;
		Iterator<KEY> iterator = lastValMap.keySet().iterator();
		return lastValMap.remove(iterator.next());
	}
	
	public void offer(KEY key, VAL val) {
		assert key != null && val != null;
		lastValMap.put(key, val);
	}
	public boolean isEmpty() {
		return lastValMap.isEmpty();
	}
}
