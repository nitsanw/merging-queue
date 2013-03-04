package psy.lob.saw.mergeq;

import static org.junit.Assert.*;

import org.junit.Test;

import psy.lob.saw.mergeq.MergingQueue;

public abstract class MergingQueueTest {
	@Test
	public void testIsEmpty() {
		MergingQueue<Integer, Integer> mergingQ = createMQ();
		assertTrue(mergingQ.isEmpty());
		mergingQ.offer(1, 1);
		assertTrue(!mergingQ.isEmpty());
		assertEquals(1, mergingQ.poll().intValue());
		assertTrue(mergingQ.isEmpty());
	}
	
	@Test
	public void testFIFO(){
		MergingQueue<Integer, Integer> mergingQ = createMQ();
		mergingQ.offer(1, 1);
		mergingQ.offer(2, 2);
		mergingQ.offer(3, 3);
		assertEquals(1, mergingQ.poll().intValue());
		assertEquals(2, mergingQ.poll().intValue());
		assertEquals(3, mergingQ.poll().intValue());
	}
	
	@Test
	public void testMerging(){
		MergingQueue<Integer, Integer> mergingQ = createMQ();
		mergingQ.offer(1, 1);
		mergingQ.offer(2, 2);
		mergingQ.offer(3, 3);
		mergingQ.offer(1, 4);
		assertEquals(4, mergingQ.poll().intValue());
		assertEquals(2, mergingQ.poll().intValue());
		assertEquals(3, mergingQ.poll().intValue());
		assertTrue(mergingQ.isEmpty());
	}
	@Test
	public void testClear(){
		MergingQueue<Integer, Integer> mergingQ = createMQ();
		mergingQ.offer(1, 1);
		mergingQ.offer(2, 2);
		mergingQ.offer(3, 3);
		assertTrue(!mergingQ.isEmpty());
		mergingQ.clear();
		assertTrue(mergingQ.isEmpty());
	}

	protected abstract MergingQueue<Integer, Integer> createMQ();

}
