package com.drawmetry.erik.ringbuffer;

public class Main {

	/**
	 * This method is used to set up a running example.
	 * 
	 * @param args
	 *            - ignored
	 */
	public static void main(String[] args) {
		BufferInterface buffer = new Buffer(20);
		Consumer[] consumers = new Consumer[10];
		for (int i = 0; i < consumers.length; i++) {
			consumers[i] = new Consumer(buffer);
			consumers[i].start();
		}
		Producer[] producers = new Producer[2];
		Thread[] threads = new Thread[2];
		for (int i = 0; i < producers.length; i++) {
			producers[i] = new Producer(buffer);
			threads[i] = new Thread(producers[i]);
			threads[i].setName("Producer_" + i);
			threads[i].start();
		}
		try {
			Thread.sleep(1000L);
			for (Producer p : producers) {
				p.stopProducing();
			}
			for (Thread t: threads){
				t.join();
			}
			System.out.println("The buffer is: " + buffer);
			buffer.add(-1);
		} catch (InterruptedException e) {
		}
	}

}
