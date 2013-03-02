package com.pls.mergeq;

public class LinkedMergingQueueTest extends MergingQueueTest {
	@Override
	protected MergingQueue<Integer, Integer> createMQ() {
		return new ArrayMergingDeque<>();
	}

}
