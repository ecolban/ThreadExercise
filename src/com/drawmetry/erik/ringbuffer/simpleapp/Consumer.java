package com.drawmetry.erik.ringbuffer.simpleapp;

import java.util.concurrent.ThreadLocalRandom;

import com.drawmetry.erik.ringbuffer.BufferInterface;
import com.drawmetry.erik.ringbuffer.Util;


/**
 * Removes numbers from a buffer until interrupted.
 * 
 * @author ecolban
 * 
 */
public class Consumer extends Thread {

	private final BufferInterface buffer;

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
	 * Repeatedly removes a number from the buffer and processes it until
	 * interrupted.
	 */
	public void run() {
		try {
			while (true) {
				int item = buffer.remove();
				process(item);
				System.out.println(buffer + "==>" + item);
			}
		} catch (InterruptedException e) {
			System.out.println(getName() + " was interrupted.");
		}
	}

	private void process(int item) {
		// process..., process...
		Util.stayBusy(200 + ThreadLocalRandom.current().nextInt(300));
	}
}
