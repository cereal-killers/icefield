package menu;

public class ScoreData {

	private String name;
	private int score;
	
	ScoreData(String n, int s)
	{
		name = n;
		score = s;
	}
	
	//Függvény a score érték módosítására
	public void SetScore(int newvalue)
	{
		score = newvalue; 
	}
	public int GetScore()
	{
		return score;
	}
	
	//Függvény a name módosítására
	public void SetName(String newvalue)
	{
		name = newvalue; 
	}
	public String GetName()
	{
		return name;
	}
}
