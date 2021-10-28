package sample;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TextToSpeechController {

    public static void tts(String s) {
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");

        if (voice != null) {
            voice.allocate();
        }
        try {
            voice.setRate(140);
            voice.setPitch(95);
            voice.setVolume(10);
            voice.speak(s);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}  