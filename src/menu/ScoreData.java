package menu;

public class ScoreData implements java.io.Serializable {

	private String name;
	private int score;
	ScoreData(){}
	ScoreData(String n, int s)
	{
		name = n;
		score = s;
	}
	
	//Függvény a score érték módosítására
	public void setScore(int newvalue)
	{
		score = newvalue; 
	}
	public int getScore()
	{
		return score;
	}
	
	//Függvény a name módosítására
	public void setName(String newvalue)
	{
		name = newvalue; 
	}
	public String getName()
	{
		return name;
	}
}
