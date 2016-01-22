package Netflix;
import java.util.Vector;

public class Algorithm
{
	private Vector<Integer> scale;
	private Vector<Integer> user1;
	private Vector<Integer> user2;
	private int similariity;
	
	public Algorithm()
	{
		scale.add(0);
		scale.add(-5);
		scale.add(-3);
		scale.add(1);
		scale.add(3);
		scale.add(5);
	}
	public Algorithm(Vector<Integer> user1, Vector<Integer> user2)
	{
		super();
		this.user1 = user1;
		this.user2 = user2;
	}
	public int getSimilarityScore()
	{
		for(int i = 0; i < user1.size(); i++)
		{
			similarity += scale.get(user1.get(i)) * scale.get(user2.get(i));
		}
		return similarity;
	}
	public String Recommendations()
	{
		int fave = 0;
		String favorites = "";
		for(int i = 0; i < user1.size(); i++)
		{
			if(user1.get(i) != 0 && user2.get(i) == 0)
			{
				if(user2.get(i) > fave)
					fave = user2.get(i);
			}
	}
}