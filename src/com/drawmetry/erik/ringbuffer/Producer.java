package com.drawmetry.erik.ringbuffer;

import java.util.concurrent.ThreadLocalRandom;


/**
 * A Producer adds repeatedly random numbers to a buffer.
 * 
 * @author ecolban
 * 
 */
public class Producer implements Runnable {

	private final BufferInterface buffer;
	private volatile boolean running = true;
	private boolean verbose;

	/**
	 * Constructs an instance of a producer.
	 * 
	 * @param buffer
	 *            the buffer to which this producers adds numbers.
	 * @param verbose TODO
	 */
	public Producer(BufferInterface buffer, boolean verbose) {
		this.buffer = buffer;
		this.verbose = verbose;
	}

	/**
	 * Adds random numbers between 1 and 10 to a buffer until stopped
	 */
	public void run() {
		int item = 0;
		while (running && !Thread.currentThread().isInterrupted()) {
			item = produce();
			try {
				buffer.add(item);
				if(verbose){
					System.out.println(buffer + "<==" + item);
				}
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
		if(verbose){
			System.out.println(Thread.currentThread().getName() + " is done.");
		}
	}

	private int produce() {
		// produce..., produce...
		Util.stayBusy(1 + ThreadLocalRandom.current().nextInt(80));
		return ThreadLocalRandom.current().nextInt(10);
	}

	/**
	 * Stops the producer.
	 * <p>
	 * Use this method rather than an interrupt. An interrupt is ambiguous in
	 * that it is not clear whether the intention is to stop the producer or to
	 * interrupt the thread that it is running in. By using the stop() method,
	 * every item produced gets added to the buffer, whereas if an interrupt is
	 * used, the last item produced will likely not get added to the buffer and
	 * goes to waste.
	 */
	public void stop() {
		this.running = false;
	}

}
