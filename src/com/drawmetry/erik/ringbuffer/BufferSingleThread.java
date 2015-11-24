package com.drawmetry.erik.ringbuffer;

/**
 * This class is a non-thread safe implementation of a BufferInterface
 * 
 * @author ecolban
 * 
 */
public class BufferSingleThread implements BufferInterface {

	private final int[] buffer;
	private int first;
	private int last;
	private boolean empty;
	private boolean full;

	
	/**
	 * Constructs an instance.
	 * 
	 * @param capacity
	 *            the capacity of the buffer
	 */
	public BufferSingleThread(int capacity) {
		buffer = new int[capacity];
		first = last = 0;
		empty = true;
		full = false;
	}

	/**
	 * Removes the first element from the buffer
	 * 
	 * @return the number that was removed
	 * 
	 */
	public int remove() {
		if (empty) {
			throw new IllegalStateException("Buffer is empty.");
		}
		first = (first + 1) % buffer.length;
		empty = first == last;
		full = false;
		return buffer[first];
	}

	/**
	 * Adds a number to the end of the buffer.
	 * 
	 * @param x
	 *            the number added
	 * 
	 */
	public void add(int x) {
		if (full) {
			throw new IllegalStateException("Buffer is full.");
		}
		last = (last + 1) % buffer.length;
		buffer[last] = x;
		empty = false;
		full = first == last;
	}
	
	public synchronized int peek() {
		if(empty) {
			throw new IllegalStateException("Buffer is empty.");
		}
		return buffer[(first + 1) % buffer.length];
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
			return buffer.length + last - first;
		}
	}

	@Override
	public String toString() {
		if (empty)
			return getCount() + ":[] ";
		StringBuilder sb = new StringBuilder();
		sb.append(getCount());
		sb.append(":");
		sb.append("[");
		int i = (first + 1) % buffer.length;
		while (i != last) {
			sb.append(buffer[i]);
			sb.append(", ");
			i = (i + 1) % buffer.length;
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
