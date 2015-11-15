import java.util.Iterator;
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
	/**
	 * Returns true if element exists in next position, else false
	 * @return boolean does another element exist in the vector
	 */
	public boolean hasNext()
	{
		if(curr < vector.size())
			return true;
		return false;
	}
	/**
	 * Returns next item in vector
	 * @return Next element in vector
	 */
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