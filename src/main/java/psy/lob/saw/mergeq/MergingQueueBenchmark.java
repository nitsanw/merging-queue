package psy.lob.saw.mergeq;


import java.util.Random;

import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class MergingQueueBenchmark {

	private final MergingQueue<Integer, Integer> mergingQ;

	public MergingQueueBenchmark() {
		String type = System.getProperty("merge.impl");
		if (type == null || type.equals("array")) {
			mergingQ = new ArrayMergingDeque<>();
		} else if (type.equals("linked")) {
			mergingQ = new LinkedMergingQueue<>();
		} else if (type.equals("hppc")) {
			mergingQ = new HppcMergingDeque<>();
		} else {
			throw new IllegalArgumentException();
		}
	}

	private int keyIterator = 0;
	private final Integer[] keys = new Integer[1000];

	@Setup
	public void prepare() {
		mergingQ.clear();
		Random randy = new Random(0L);
		for (int i = 0; i < keys.length; i++) {
			keys[i] = randy.nextInt(64);
		}
	}

	/**
	 * put 1k entries in, then poll the queue until it is empty
	 * @return
	 */
	@GenerateMicroBenchmark
	public int measureOffer1000PollUntilEmpty() {
		for (int i = 0; i < keys.length; i++) {
			mergingQ.offer(keys[i], 1);
		}
		int sum = 0;
		do {
			sum += mergingQ.poll();
		} while (!mergingQ.isEmpty());
		return sum;
	}

	@GenerateMicroBenchmark
	public int measureOffer() {
		int j = key();
		mergingQ.offer(j, 1);
		return j;
	}

	private int key() {
	    return keyIterator++ & 63;
    }

	@GenerateMicroBenchmark
	public int measureOffer1Poll1() {
		int j = key();
		mergingQ.offer(j, 1);
		return mergingQ.poll();
	}

	@GenerateMicroBenchmark
	public int measureOffer2Poll1() {
		int j = key();
		mergingQ.offer(j, 1);
		mergingQ.offer(j, 2);
		return mergingQ.poll();
	}

	@GenerateMicroBenchmark
	public int measureOffer2Poll2() {
		int j = key();
		mergingQ.offer(j, 1);
		j = key();
		mergingQ.offer(j, 2);
		return mergingQ.poll() + mergingQ.poll();
	}
}
