package menu;
// A toplista egy elemét megvalósító osztály
public class ScoreData implements java.io.Serializable {

	private String name; // játékos neve
	private int score; // játékos pontszáma => hány kör alatt teljesítette a pályát
	ScoreData(){}
	ScoreData(String n, int s)
	{
		name = n;
		score = s;
	}
	
	/**
	 * A score settere
	 * @param newvalue új érték
	 */
	public void setScore(int newvalue)
	{
		score = newvalue; 
	}
	/**
	 * A score gettere
	 * @return a score meglévő értéke
	 */
	public int getScore()
	{
		return score;
	}
	
	/**
	 * A name settere
	 * @param newvalue új érték
	 */
	public void setName(String newvalue)
	{
		name = newvalue; 
	}
	/**
	 * A name gettere
	 * @return a meglévő érték
	 */
	public String getName()
	{
		return name;
	}
}
