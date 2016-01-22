package Netflix;

import java.util.Iterator;
//import java.lang.Iterable;
import java.util.NoSuchElementException;
/**
 * This is a VectorIterator class
 * @author Anish
 * @version 10.26.15
 * @param <E>
 */
public class VectorIterator<E> implements Iterator<E>
{
	private Vector<E> vector;
	private int curr;
	public VectorIterator(Vector<E> v)
	{
		vector = v;
		curr = 0;
	}
	public boolean hasNext()
	{
		if(curr < vector.size())
			return true;
		return false;
	}
	public E next()
	{
		if(hasNext() == false)
		{
			throw new NoSuchElementException();
		}
		return (E) vector.get(curr++);
	}
	public void remove()
	{
		throw new UnsupportedOperationException();
	}
}