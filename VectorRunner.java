import java.util.Iterator;
//import java.lang.Iterable;
import java.util.Vector;

public class VectorRunner
{
	public static void main(String[] args)
	{
		Vector<Object> v = new Vector<Object> ();
		v.add("Hi");
		v.add(0, "Hello");
		System.out.println(v);
		v.remove("Hello");
		System.out.println(v);
		v.iterator();
		System.out.println(v);
	}
}
