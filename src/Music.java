import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Music {
	boolean play = false;
	long time=0;
	File musicPath;
	AudioInputStream audioInput;
	Clip clip;
	FloatControl gainControl;
	double gain;  
	float dB;
	
	Music (String filePath, int volume){
		musicPath = new File(filePath);
		try {
			audioInput = AudioSystem.getAudioInputStream(musicPath);
			clip = AudioSystem.getClip();
			clip.open(audioInput);
			gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		gain = volume/100.0;
		dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
		changeVolume();
	}
	
	public void restartMusic() {
		play= true;
		gainControl.setValue(dB);
		time = 0;
		clip.setMicrosecondPosition(time);
		clip.start();
	}
	
	public void pauseMusic() {
		play= false;
		gainControl.setValue(dB);
		time = clip.getMicrosecondPosition();
		clip.stop();
	}
	
	public void resumeMusic() {
		play= true;
		gainControl.setValue(dB);
		clip.setMicrosecondPosition(time);
		clip.start();
	}
	
	public void changeVolume() {
		time = clip.getMicrosecondPosition();
		gainControl.setValue(dB);
		if (play)
			clip.start();
	}
}
