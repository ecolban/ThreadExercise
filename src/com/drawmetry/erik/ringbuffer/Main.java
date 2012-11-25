package com.drawmetry.erik.ringbuffer;

public class Main {

	/**
	 * This method is used to set up a running example.
	 * 
	 * @param args
	 *            - ignored
	 */
	public static void main(String[] args) {
		BufferInterface buffer = new Buffer(10);
		Consumer[] consumers = new Consumer[20];
		for (int i = 0; i < consumers.length; i++) {
			consumers[i] = new Consumer(buffer);
			consumers[i].start();
		}
		Producer p1 = new Producer(buffer);
		p1.setName("The Producer");
		p1.start();
		try {
			Thread.sleep(1000L);
			p1.interrupt();
			for(Consumer c: consumers) {
				c.interrupt();
			}
			
		} catch (InterruptedException e) {
		}
	}

}
