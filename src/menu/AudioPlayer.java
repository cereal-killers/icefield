package menu;
import java.io.File;
import java.io.FileOutputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

// A zenelejátszót megvalósító osztály
public class AudioPlayer  {

	private Clip audioClip;
	private boolean playing=false;

	    void Start(boolean menu) {
	    	System.out.println("Audio is playing.");
	    	playing = true;
	    	String filename = menu? "menu":"main";
	        File f = new File(System.getProperty("user.dir")+"\\menu\\"+filename+".wav");
	        AudioInputStream audioInputStream;
			try {
				audioInputStream = AudioSystem.getAudioInputStream(f);
		        audioClip = AudioSystem.getClip();
		        audioClip.open(audioInputStream);
		        audioClip.loop(audioClip.LOOP_CONTINUOUSLY);
			} catch (Exception e) {
				System.out.println("Audio error.");
			}
         
	    }
	    public void Stop()
	    {
	    	audioClip.stop();
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
