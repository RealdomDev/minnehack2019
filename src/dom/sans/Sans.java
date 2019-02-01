package dom.sans;

import java.io.BufferedInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class Sans {
	public static void main(String[] args) {
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.OFF);
		logger.setUseParentHandlers(false);
		
		Clip clip1, clip2;
		try {
			GlobalScreen.registerNativeHook();
			
		    AudioInputStream inputStream1 = AudioSystem.getAudioInputStream(new BufferedInputStream(Sans.class.getResourceAsStream("/lol.wav")));
		    AudioInputStream inputStream2 = AudioSystem.getAudioInputStream(new BufferedInputStream(Sans.class.getResourceAsStream("/lol.wav")));
		    clip1 = AudioSystem.getClip();
		    clip2 = AudioSystem.getClip();
		    clip1.open(inputStream1);
		    clip2.open(inputStream2);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		GlobalScreen.addNativeKeyListener(new NativeKeyListener() {
			private boolean swap = false;
			
			public void nativeKeyPressed(NativeKeyEvent e) {}
			public void nativeKeyReleased(NativeKeyEvent e) {}

			public void nativeKeyTyped(NativeKeyEvent e) {
				clip1.stop();
				clip2.stop();
				if(swap) {
					clip1.setFramePosition(0);
					clip1.start();
				} else {
					clip2.setFramePosition(0);
					clip2.start();
				}
				
				swap = !swap;
			}
		});
	}
}

