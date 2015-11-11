package Netflix;
import java.io.*;
import java.util.Scanner;
import java.util.Vector;
/**
 * @author Anish Seth
 * @version 11-8-15
 * This class uses a Netflix recommendation algorithm to determine people who have similar TV shows preferences and recommends three shows best suited for you to watch
 */
public class Netflix
{
	private static Vector<Vector<Integer>> users = new Vector<Vector<Integer>>();
	private static Vector<String> shows = new Vector<String>();
	private static String s;
	
	public static void main(String[] args)
	{
		String s = ReadCSV();
		initVector(s);
		System.out.println(Recommendations(2));
	}
	/**
	 * Reads the CSV file
	 * @return Returns a string representation of the CSV
	 */
	public static String ReadCSV()
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
	/**
	 * initializes a vector of the shows and a vector of the ratings for each show per person from the String in the CSV file
	 * @param String obatined from CSV file
	 */
	public static void initVector(String s)
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
		while(i < s.length())
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
				users.add(line);
				if(s.charAt(i) == '\n')
					i--;
				i++;
			}
			i++;
		}
	}
	/**
	 * the user's rating of the show is taken and put on a scale based how much they liked it or if they have not seen it
	 * @param the user's rating of the show
	 * @param returns the scaled value of the user's rating
	 */
	public static int scale(int rating)
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
		return 9023401;
	}
	/**
	 * Gets the similarity score between two particular individuals
	 * @param the vector of two particular people
	 * @return returns the similarity score
	 */
	public static int getSimilarityScore(Vector<Integer> user1, Vector<Integer> user2)
	{
		int similarity = 0;
		for(int i = 0; i < shows.size(); i++)
		{
			similarity += scale(user1.get(i)) * scale(user2.get(i));
		}
		return similarity;
	}
	/**
	 * Compares the similiarity scores between all the users and determines which one is most similar
	 * Spits out three shows that are most highly recommended
	 * @param user whose recommendations you will be giving
	 * @return Vector of Strings with the recommendations
	 */
/*	
	public static Vector<String> CompareScores(int index)
	{
		Vector<String> s = new Vector<String> (3);
		String first, second, third;
		int hold = 0;
		int similar = getSimilarityScore(users.get(index), users.get(index+1));
		for(int i = 0; i < shows.size(); i++)
		{
			if(similar < getSimilarityScore(users.get(index), users.get(i)) && i != index)
			{
				similar = getSimilarityScore(users.get(index), users.get(i));
				hold = i;
			}
		}
		for(int x = 0; x < shows.size(); x++)
		{
			int fave = users.get(hold.get(0));
			int occupy = 0;
			while(occupy < 3)
			{
				if(users.get(hold.get(x)) != 0 && users.get(index.get(x)) == 0)
				{
					if(users.get(hold) >= fave)
						fave = users.get(hold);
						s.add(fave);
						occupy++;
				}
			}
		}
		return s;
	}
*/
	public static Vector<String> Recommendations(int index)
	{
		Vector<String> s = new Vector<String> (3);
		int hold = 0;
		int save = 0;
		int holder = 0;
		for(int i = 0; i < shows.size(); i++)
		{
			if(users.get(index).get(i) == 0)
			{
				for(int c = 0; c < shows.size(); c++)
				{
					hold += users.get(i).get(c);
				}
				if(holder > hold/50)
				{
					holder = hold/50;
					save = i;
					s.add(shows.get(i));
				}
			}
		}
		return s;
	}
}