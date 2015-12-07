package com.drawmetry.erik.ringbuffer;

import java.util.concurrent.ThreadLocalRandom;


/**
 * Removes numbers from a buffer until interrupted.
 * 
 * @author ecolban
 * 
 */
public class Consumer extends Thread {

	private final BufferInterface buffer;
	public static final int POISON_PILL = -1;

	/**
	 * Constructs an instance of consumer.
	 * 
	 * @param buffer
	 *            the buffer this instances removes numbers from.
	 */
	public Consumer(BufferInterface buffer) {
		this.buffer = buffer;
		setName(getName().replaceFirst("Thread", "Consumer"));
	}

	/**
	 * Repeatedly removes a number from the buffer and processes it until the
	 * POISON_PILL is encountered.
	 */
	public void run() {
		boolean keepGoing = true;
		while (keepGoing) {
			try {
				int item;
				synchronized (buffer) {
					if ((item = buffer.peek()) == POISON_PILL) {
						keepGoing = false;
					} else {
						buffer.remove();
					}
				}
				if (item != POISON_PILL) {
					process(item);
				}
			} catch (InterruptedException e) {
				System.err.println("Can't interrupt me!");
			}
		}
		System.out.println(getName() + " is done.");
	}

	private void process(int item) {
		// process..., process...
		int t = 200 + ThreadLocalRandom.current().nextInt(300);
		long start = System.currentTimeMillis();
		while (System.currentTimeMillis() < start + t) {
			Thread.yield();
		}

	}
}
