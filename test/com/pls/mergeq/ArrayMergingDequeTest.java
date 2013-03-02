package com.pls.mergeq;

public class ArrayMergingDequeTest extends MergingQueueTest {
	@Override
	protected MergingQueue<Integer, Integer> createMQ() {
		return new ArrayMergingDeque<>();
	}

}
