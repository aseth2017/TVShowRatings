package Netflix;

import java.util.Iterator;
import java.lang.Iterable;
/**
* This is a class that creates a vector
* A vector is another data struture, similar to ArrayList
* Got some guidance from Kevin Chow
* @author Anish Seth
* @version 10.18.15
*/
public class Vector <E> implements Iterable<E>
{
	private Object[] data; //Array storing all the values in the vector
	private int capacity; //Number of spaces the vector can hold
	private int occupy; //Number of spaces occupied


	/**
	 * Default constructor, sets capacity to 10
	 */
	@SuppressWarnings("unchecked")
	public Vector()
	{
		data = (E[]) new Object[10];
		occupy = 0;
		capacity = 10;
	}

	/**
	 * Constructor that takes in a value for the capacity of the vector
	 * @param size Size of the vector
	 */
	@SuppressWarnings("unchecked")
	public Vector(int size)
	{
		data = (E[]) new Object[size];
		occupy = 0;
		capacity = size;
	}

	/**
	 * Constructor that copies a vector into this class
	 * Running into error when trying to typecast, suppressing warnings
	 * @param other vector to be copied
	 */
	@SuppressWarnings("unchecked")
	public Vector(Vector<E> other)
	{
		data = (E[]) new Object[other.size()];
		for(int i = 0; i < other.size(); i++)
		{
			data[i] = other.get(i);
			occupy++;
		}
	}

	/**
	 * Adds an object to the next available index in the vector
	 * @param x Object that will be added
	 */
	public void add(E x)
	{
		if(occupy + 1 < data.length)
			Grow();
		data[occupy++] = x;
	}

	/**
	 * Adds an object to a specific index in the vector
	 * @param x Object that will be added
	 * @param i	Specific index at which the object will be added
	 */
	public void add(E x, int i)
	{
		if(i<0 || i > capacity)
			throw new IndexOutOfBoundsException();
		int length = occupy-1;
		int hold = i;
		for(int index = hold-1; index < length; index++)
		{
    		data[length] = data[hold];
        	hold++;
    	}
    	data[i] = x;
		occupy++;
	}

	/**
	 * Creates a new vector with double the capacity
	 * @return New vector with double the size
	 */
	@SuppressWarnings("unchecked")
	private Object[] Grow()
	{
		Object [] x = new Object[data.length * 2];
		for(int i = 0; i< occupy; i++)
		{
			x[i] = (E) data[i];
		}
		return x;
	}

	/**
	 * Gets the value at the specified index of the vector
	 * @param i Specific index in the vector
	 * @return Returns the value of the vector at the given index 
	 */
	@SuppressWarnings("unchecked")
	public E get(int i)
	{
		if(i<0 || i > capacity)
			throw new IndexOutOfBoundsException();
		return (E) data[i];
	}
	/**
	 * Remove and return item at specified index
	 * Shift the other items down
	 * @param key index of object to be removed
	 * @return object removed from vector
	 */
	@SuppressWarnings("unchecked")
	public E remove(int key)
	{
		if(key<0 || key > capacity)
			throw new IndexOutOfBoundsException();
		int length = occupy-1;
		for(int i = length; i > key-1; i--)
		{
			data[length] = data[key];
		}
		E hold = (E) data[key];
		data[key] = null;
		occupy--;
		return hold;
	}

	/**
	 * Remove first instance of specified object
	 * @param obj Object to be removed from vector
	 * @return True if specifie object is stored in vector, else false
	 */
	public boolean remove(E obj)
	{
		int index = indexOf(obj);
		if(index >= 0)
		{
			remove(index);
			return true;
		}
		return false;
	}

	/**
	 * Places object at specified index in vector
	 * @param index Index of vector where object is placed
	 * @param obj Object to be place
	 * @return Object specified
	 */
	@SuppressWarnings("unchecked")
	public E set(int index, E obj)
	{
		if(index<0 || index > capacity)
			throw new IndexOutOfBoundsException();
		E hold = (E) data[index];
		data[index] = obj;
		return hold;
	}

	/**
	 * Return the number of items in the vector
	 * @return private class field occupy
	 */
	public int size()
	{
		return occupy;
	}

	/**
	 * Removes all items from the vector
	 */
	public void clear()
	{
		for(int i = 0; i < data.length; i++)
			data[i] = null;
		occupy = 0;
	}

	/**
	 * Returns whether or not the vector is empty
	 * @return boolean for if vector is empty
	 */
	public boolean isEmpty()
	{
		return occupy == 0;
	}

	/**
	 * Return whether or not the vector contains specified object
	 * @param obj
	 * @return boolean for if vector contains specified object
	 */
	public boolean contains(E obj)
	{
		return indexOf(obj) >= 0;
	}

	/**
	 * Return index of the first instance of specified object
	 * @param obj specified object whose index is to be returned
	 * @return Index of specified object
	 */
	@SuppressWarnings("unchecked")
	public int indexOf(E obj)
	{
		for(int i = 0; i < data.length; i++)
		{
			if((E) data[i] == obj)
				return i;
		}
		return -1;
	}

	/**
	 * to String method, creates a String representation of the vector
	 * @return String representation of vector
	 */
	public String toString()
	{
		String s = "";
		for(int i = 0; i < occupy; i++)
		{
			s+= data[i] + "\n";
		}
		return s;
	}
	/**
	 * @param 
	 * @return returns a boolean on whether or not the parameter is equal to the vector data
	 */
	public boolean equals(Vector<E> x)
	{
		if(x.size() != data.length)
			return false;
		for(int i = 0; i < data.length; i++)
		{
			if(data[i] != x.get(i)||(data[i] == null && x.get(i) == null))
				return false;
		}
		return true;
	}
	/**
	 * create and return an iterator
	 * @return Iterator<E>
	 */
	public Iterator<E> iterator()
	{
		return new VectorIterator<E>(this);
	}
}
