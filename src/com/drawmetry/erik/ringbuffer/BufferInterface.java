package com.drawmetry.erik.ringbuffer;

/**
 * 
 * @author ecolban
 * 
 */
public interface BufferInterface {

	/**
	 * Removes the first element from the buffer
	 * 
	 * @return the number that was removed
	 * 
	 * @throws InterruptedException
	 *             if interrupted
	 */
	public int remove();

	/**
	 * Adds a number to the end of the buffer.
	 * 
	 * @param x
	 *            the number added
	 */
	public void add(int x);

	/**
	 * Gets the count of elements in the buffer
	 * 
	 * @return the number of elements in the buffer
	 */
	public int getCount();

	/**
	 * 
	 * @return true if the buffer is empty, false otherwise
	 */
	public boolean isEmpty();

	/**
	 * 
	 * @return true if the buffer is full, false otherwise
	 */
	public boolean isFull();

}
