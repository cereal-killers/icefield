/*package menu;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
// A zenelejátszót megvalósító osztály
public class AudioPlayer  {

	private MediaPlayer mediaPlayer;
	private boolean playing=false;

	    void Start(boolean menu) {
	    	 new javafx.embed.swing.JFXPanel();
	    	System.out.println("Audio is playing.");
	    	playing = true;
	    	String filename = menu? "menu":"main";
	    	String s = getClass().getResource(filename+".mp3").toString();
	    	System.out.println(s);
	    	Media hit = new Media(s);
	    	mediaPlayer = new MediaPlayer(hit);
	    	mediaPlayer.setOnEndOfMedia(new Runnable() {
	    	       public void run() {
	    	    	   mediaPlayer.seek(Duration.ZERO);
	    	       }
	    	   });
	    	mediaPlayer.play();
	         
	    }
	    public void Stop()
	    {
	    	mediaPlayer.stop();
	    	System.out.println("Audio stopped.");
	    	playing = false;
	    }
	    public void Reset(boolean music)
	    {
	    	if (!music && playing)
	    		Stop();
	    	else if(music && !playing)
	    		Start(true);
	    }
}
*/