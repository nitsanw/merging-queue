package psy.lob.saw.mergeq;

import java.util.Random;

import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class MergingQueueBenchmark {

	private static final Integer ONE = 1;
	private final MergingQueue<Integer, Integer> merger;

	public MergingQueueBenchmark() {
		String type = System.getProperty("merge.impl");
		if (type == null || type.equals("array")) {
			merger = new ArrayMergingDeque<>();
		} else if (type.equals("linked")) {
			merger = new LinkedMergingQueue<>();
		} else if (type.equals("hppc")) {
			merger = new HppcMergingDeque<>();
		} else {
			throw new IllegalArgumentException();
		}
	}

	private int keyIterator = 0;
	private final Integer[] keys = new Integer[1000];

	@Setup
	public void prepare() {
		merger.clear();
		Random randy = new Random(0L);
		for (int i = 0; i < keys.length; i++) {
			keys[i] = randy.nextInt(64);
		}
	}

	@GenerateMicroBenchmark
	@Fork
	public int measureOffer1000PollUntilEmpty() {
		for (int i = 0; i < keys.length; i++) {
			merger.offer(keys[i], ONE);
		}
		int sum = 0;
		do {
			sum += merger.poll();
		} while (!merger.isEmpty());
		return sum;
	}

	@GenerateMicroBenchmark
	@Fork
	public int measureOffer() {
		int j = keyIterator++ & 63;
		merger.offer(j, ONE);
		return j;
	}

	@GenerateMicroBenchmark
	@Fork
	public int measureOffer1Poll1() {
		int j = keyIterator++ & 63;
		merger.offer(j, ONE);
		return merger.poll();
	}

	@GenerateMicroBenchmark
	@Fork
	public int measureOffer2Poll1() {
		int j = keyIterator++ & 63;
		merger.offer(j, ONE);
		merger.offer(j, ONE);
		return merger.poll();
	}

	@GenerateMicroBenchmark
	@Fork
	public int measureOffer2Poll2() {
		int j = keyIterator++ & 63;
		merger.offer(j, ONE);
		j = keyIterator++ & 63;
		merger.offer(j, ONE);
		return merger.poll() + merger.poll();
	}

}
