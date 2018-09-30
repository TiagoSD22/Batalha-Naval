package jogoBN;

import java.applet.AudioClip;
import java.awt.Toolkit;
import java.util.HashMap;

import javax.swing.JApplet;

public class Sons {
	
	AudioClip tiro,agua,acertou,explodiu;
	HashMap <String,AudioClip> mapa;
	
	public Sons(){
		tiro = null;
		agua = null;
		acertou = null;
		explodiu = null;
		mapa = new HashMap<String,AudioClip>(2);
		try{
			tiro = JApplet.newAudioClip(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/audio/atirou.wav"));
			agua = JApplet.newAudioClip(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/audio/agua.wav"));
			acertou = JApplet.newAudioClip(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/audio/acertou.wav"));
			explodiu = JApplet.newAudioClip(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/audio/explodiu.wav"));
		}
		catch(Exception e){
			e.printStackTrace();
			System.exit(-1);
		}
		mapa.put("agua",agua);
		mapa.put("tiro",tiro);
		mapa.put("acertou",acertou);
		mapa.put("explodiu",explodiu);
	}
	
	public void Tocar(String audio){
		mapa.get(audio).play();
	}

}
