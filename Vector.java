import java.util.Iterator;
import java.lang.Iterable;

/**
* This is a class that creates a vector
* A vector is another data struture, similar to ArrayList
* Got some guidance from Kevin Chow and Jack Proudfoot
* @author Anish Seth
* @version 10.18.15
*/
public class Vector <E> implements Iterable<E>
{
	private Object[] data; //Array storing all the values in the vector
	private int occupy; //Number of spaces occupied


	/**
	 * Default constructor, sets capacity to 10
	 */
	public Vector()
	{
		data = new Object[10];
		occupy = 0;
	}

	/**
	 * Constructor that takes in a value for the capacity of the vector
	 * @param size Size of the vector
	 */
	public Vector(int size)
	{
		data = new Object[size];
		occupy = 0;
	}

	/**
	 * Constructor that copies a vector into this class
	 * @param other vector to be copied
	 */
	public Vector(Vector<E> other)
	{
		data = new Object[other.size()];
		for(int i = 0; i < data.length; i++)
		{
			data[i] = other.get(i);
		}
		occupy = data.length;
	}

	/**
	 * Adds an object to the next available index in the vector
	 * @param x Object that will be added
	 */
	public void add(E x)
	{
		add(x, occupy);
	}

	/**
	 * Adds an object to a specific index in the vector, if vector is full, grows the vector 
	 * @param x Object that will be added
	 * @param i	Specific index at which the object will be added
	 */
	public void add(E x, int i)
	{
		if(i < 0 || i > occupy)
			throw new IndexOutOfBoundsException();
		if(occupy+1 >= data.length)
		{
			Grow();
			add(x, i);
		}
		else
		{
			occupy++;
			for(int index = occupy - 1; index > i; index--)
			{
				if(data[index-1] != null)
					data[index] = data[index-1];
				else
					data[index] = null;
			}
		}
		data[i] = (Object) x;
	}

	/**
	 * Creates a new vector with double the capacity
	 * Moves all the values in the old vector into the new vector
	 * @return New vector with double the size
	 */
	private void Grow()
	{
		Object [] Double = new Object[data.length * 2];
		for(int i = 0; i < occupy; i++)
		{
			if(data[i] != null)
				Double[i] = data[i];
		}
		data = Double;
	}

	/**
	 * Gets the value at the specified index of the vector
	 * @param i Specific index in the vector
	 * @return Returns the value of the vector at the given index 
	 */
	@SuppressWarnings("unchecked")
	public E get(int i)
	{
		if(i<0 || i > occupy)
			throw new IndexOutOfBoundsException();
		if(data[i] != null)
			return (E) data[i];
		return null;
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
		if(key<0 || key > occupy)
			throw new IndexOutOfBoundsException();
		if(key < occupy)
		{
			E removable = (E) data[key];
			for(int i = key; i < occupy; i++)
			{
				data[i] = data[i+1];
			}
			occupy--;
			return removable;
		}
		return null;
	}

	/**
	 * Remove first instance of specified object
	 * @param obj Object to be removed from vector
	 * @return True if specifie object is stored in vector, else false
	 */
	public boolean remove(E obj)
	{
		int index = indexOf(obj);
		if(index >= 0 && obj != null)
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
		if(index<0 || index > occupy)
			throw new IndexOutOfBoundsException();
		E hold = null;
		if(index < occupy)
		{
			if(data[index] != null)
				hold = (E) data[index];
			data[index] = obj;
		}
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
		for(int i = 0; i < occupy; i++)
			data[i] = null;
		occupy = 0;
	}

	/**
	 * Returns whether or not the vector is empty
	 * @return boolean for if vector is empty
	 */
	public boolean isEmpty()
	{
		return (occupy == 0);
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
	public int indexOf(E obj)
	{
		for(int i = 0; i < data.length; i++)
		{
			if(data[i].equals(obj))
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
		for (int i = 0; i < data.length; i++)
		{
			if (data[i] == null && x.get(i) == null)
				continue;
			else if (data[i].equals(x.get(i)))
				continue;
			else
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
