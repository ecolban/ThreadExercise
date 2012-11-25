package com.drawmetry.erik.ringbuffer;

/**
 * This class combines two classical examples: the ring buffer and the
 * producer-consumer pattern.
 * 
 * @author ecolban
 * 
 */
public class Buffer implements BufferInterface {

	private final int[] buffer;
	private final int capacity;
	private int first;
	private int last;
	private boolean empty = true;
	private boolean full = false;

	/**
	 * Constructs an instance.
	 * 
	 * @param capacity
	 *            the capacity of the buffer
	 */
	public Buffer(int capacity) {
		this.capacity = capacity;
		buffer = new int[capacity];
		first = last = 0;
	}

	/**
	 * Removes the first element from the buffer
	 * 
	 * @return the number that was removed
	 * 
	 * @throws InterruptedException
	 *             if interrupted
	 */
	public synchronized int remove() throws InterruptedException {
		while (empty) {
			// System.out.println(Thread.currentThread().getName() +
			// " is waiting.");
			wait();
		}
		first = (first + 1) % capacity;
		empty = first == last;
		full = false;
		System.out.println(this + "==>" + buffer[first]);
		notifyAll();
		return buffer[first];
	}

	/**
	 * Adds a number to the end of the buffer.
	 * 
	 * @param n
	 *            the number added
	 * 
	 * @throws InterruptedException
	 *             if interrupted
	 */
	public synchronized void add(int n) throws InterruptedException {
		while (full) {
			// System.out.println(Thread.currentThread().getName() +
			// " is waiting.");
			wait();
		}
		last = (last + 1) % capacity;
		buffer[last] = n;
		empty = false;
		full = first == last;
		System.out.println(this + "<==" + n);
		notifyAll();
	}

	/**
	 * Gets the count of elements in the buffer
	 * 
	 * @return
	 */
	public int getCount() {
		if (empty) {
			return 0;
		} else if (first < last) {
			return last - first;
		} else {
			return last - first + capacity;
		}
	}

	@Override
	public synchronized String toString() {
		if (empty)
			return "[] ";
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		int i = (first + 1) % capacity;
		while (i != last) {
			sb.append(buffer[i]);
			sb.append(", ");
			i = (i + 1) % capacity;
		}
		sb.append(buffer[i]);
		sb.append("] ");
		return sb.toString();
	}

	@Override
	public boolean isEmpty() {
		return empty;
	}

	@Override
	public boolean isFull() {
		return full;
	}
}
