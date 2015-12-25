package com.drawmetry.erik.ringbuffer;

public class Main {

	/**
	 * This method is used to set up a running example.
	 * 
	 * @param args
	 *            - ignored
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		new Main().exampleRun1();
	}

	private void exampleRun1() throws InterruptedException {

		BufferInterface buffer = new Buffer(20);
		Consumer[] consumers = new Consumer[10];
		Producer[] producers = new Producer[2];
		Thread[] producerThreads = new Thread[2];
		for (int i = 0; i < producers.length; i++) {
			producers[i] = new Producer(buffer, true);
			producerThreads[i] = new Thread(producers[i]);
			producerThreads[i].setName("Producer-" + i);
			producerThreads[i].start();
		}
		for (int i = 0; i < consumers.length; i++) {
			consumers[i] = new Consumer(buffer, true);
			consumers[i].start();
		}
		// Let the producers and consumers run for a while...
		Thread.sleep(1000L);
		// A while later...
		System.out.println("Initiating shutdown...");
		for (Producer p : producers) {
			p.stop();
		}
		for (Thread t : producerThreads) {
			t.join();
		}
		buffer.add(Consumer.POISON_PILL);
		System.out.println(buffer + "<==" + Consumer.POISON_PILL);
		for (Consumer c : consumers) {
			c.join();
		}
		int item = buffer.remove();
		System.out.println(buffer + "==>" + item);
		System.out.println("buffer.isEmpty() == " + buffer.isEmpty());
	}

}
