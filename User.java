package Netflix;
import java.io.*;
import java.util.Scanner;

public class Netflix
{
	private Vector<Vector<Integer>> users = new Vector<Vector<Integer>>();
	private Vector<String> shows = new Vector<String>();
	
	public static void main(String[] args)
	{
		String s = ReadCSV();
		initVector(s);
		System.out.println(compareScore(20));
	}
	public User(Vector<Integer> user)
	{
		users = new Vector<Vector<Integer>>(user.size());
		Vector<Integer> hold = new Vector<Integer> (1);
		for(int i = 0; i< user.size(); i++)
		{
			hold = users.get(i);
			users.set(i, hold);
		}
		similar = Vector<Integer>(users.size()-1);
	}
	public String ReadCSV()	
	{	
		String pathname = "Sheet1.csv";
		File file = new File(pathname);	
		Scanner input = null;
		String s = "";
		try
		{
			input = new Scanner(file);
		}
		catch (FileNotFoundException ex)
		{
			System.out.println(" Cannot open " + pathname );
			System.exit(1);
		}
		
		while( input.hasNextLine() )
		{
			s += input.nextLine() + "\n";
		}
		return s;
	}
	public void initVector(String s)
	{
		int i = 0;
		while(s.charAt(i) != '\n')
		{
			String x = "";
			while((s.charAt(i) != ',') && (s.charAt(i) != '\n'))
			{
				x += s.charAt(i);
				i++;
			}
			shows.add(x);
			if(s.charAt(i) == '\n')
				i--;
			i++;
		}
		while(i < s.length)
		{
			while(s.charAt(i) != '\n')
			{
				Vector<Integer> line = new Vector<Integer>();
				while(s.charAt(i) != '\n')
				{
					if(s.charAt(i) != '\n')
						line.add((int)s.charAt(i) - 48); //Finds ASCII values for integers
					i++;
				}
				ratings.add(line);
				if(s.charAt(i) == '\n')
					i--;
				i++;
			}
			i++;
		}
	}
	public int scale(int rating)
	{
		if(rating == 0)
			return 0;
		if(rating == 1)
			return -5;
		if(rating == 2)
			return -3;
		if(rating == 3)
			return 1;
		if(rating == 4)
			return 3;
		if(rating == 5)
			return 5;
	}
	public int getSimilarityScore(Vector<Integer> user1, Vector<Integer> user2)
	{
		for(int i = 0; i < shows.size(); i++)
		{
			similarity += scale.get(user1.get(i)) * scale.get(user2.get(i));
		}
		return similarity;
	}
	public String CompareScores()
	{
		
	}
}