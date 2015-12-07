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
		Thread[] producerThreads = new Thread[2];
		for (int i = 0; i < producers.length; i++) {
			producers[i] = new Producer(buffer);
			producerThreads[i] = new Thread(producers[i]);
			producerThreads[i].setName("Producer-" + i);
			producerThreads[i].start();
		}
		try {
			Thread.sleep(1000L);
			System.out.println("Stopping production...");
			for (Producer p : producers) {
				p.stopProducing();
			}
			for (Thread t: producerThreads){
				t.join();
			}
			System.out.println("The buffer is: " + buffer);
			buffer.add(Consumer.POISON_PILL);
		} catch (InterruptedException e) {
		}
	}

}
