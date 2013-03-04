package psy.lob.saw.mergeq;

import psy.lob.saw.mergeq.ArrayMergingDeque;
import psy.lob.saw.mergeq.MergingQueue;

public class LinkedMergingQueueTest extends MergingQueueTest {
	@Override
	protected MergingQueue<Integer, Integer> createMQ() {
		return new ArrayMergingDeque<>();
	}

}
