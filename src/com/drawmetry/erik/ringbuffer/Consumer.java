package com.drawmetry.erik.ringbuffer;

import java.util.concurrent.ThreadLocalRandom;


/**
 * Removes numbers from a buffer until it encounters a POISON_PILL. To interrupt
 * this thread, add the POISON_PILL to the buffer.
 * 
 * @author ecolban
 * 
 */
public class Consumer extends Thread {

	private final BufferInterface buffer;
	private final boolean verbose;

	public static final int POISON_PILL = -1;

	/**
	 * Constructs an instance of consumer.
	 * 
	 * @param buffer
	 *            the buffer this instances removes numbers from.
	 * @param verbose
	 *            TODO
	 */
	public Consumer(BufferInterface buffer, boolean verbose) {
		this.buffer = buffer;
		this.verbose = verbose;
		setName(getName().replaceFirst("Thread", getClass().getSimpleName()));
	}

	/**
	 * Repeatedly removes a number from the buffer and processes it until the
	 * POISON_PILL is encountered.
	 */
	public void run() {
		boolean running = true;
		while (running) {
			try {
				int item = POISON_PILL;
				// POISON_PILL must *not* be removed from the buffer
				synchronized (buffer) {
					if (buffer.peek() != POISON_PILL) {
						item = buffer.remove();
						if(verbose) {
							System.out.println(buffer + "==>" + item);
						}
					}
				}
				if (item == POISON_PILL) {
					running = false;
				} else {
					process(item);
				}
			} catch (InterruptedException e) {
				if (verbose) {
					System.err.println(getName() + " was interrupted.");
				}
			}
		}
		if (verbose) {
			System.out.println(getName() + " is done.");
		}
	}

	private void process(int item) {
		// process..., process...
		Util.stayBusy(300 + ThreadLocalRandom.current().nextInt(101));

	}

}
