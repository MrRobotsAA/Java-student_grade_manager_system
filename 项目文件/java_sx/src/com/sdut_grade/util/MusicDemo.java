package com.sdut_grade.util;
import java.io.InputStream;


import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/*
 * @ͨ�ñ������ֲ���ģ��
 * @author Logic
 * @version 1.0
 * @data 03/24/2010
 */
public class MusicDemo {
	public MusicDemo(){
		init();
		
	}
	public void init() {
		/*
		 * ����һ��ʹ��jmf��
		 */

		/*
		 * Player player =null; File musicFile=new File(".\\src\\logic.mp3");
		 * MediaLocator locator=new
		 * MediaLocator("file:"+musicFile.getAbsolutePath()); try { player =
		 * Manager.createRealizedPlayer(locator); player.prefetch();//Ԥ���ļ�
		 * player.start(); } catch (NoPlayerException e) { e.getMessage();
		 * e.printStackTrace(); } catch (CannotRealizeException e) {
		 * e.getMessage(); } catch (IOException e) { e.getMessage();
		 * e.printStackTrace(); }
		 */

		//������
		/*URL musicUrl = null;
		try {
			musicUrl = new URL(".\\src\\logic.mp3");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		AudioClip ac = Applet.newAudioClip(musicUrl);
		ac.play();
		*/

		
		//������
		InputStream in = null;
		
		try {
			/*
			 * ��ȡ�ļ���һ��Ҫ�� getResourceAsStream ��Ҫ��Ȼ������޷���ȡ
			 */
        	in=this.getClass().getResourceAsStream("/com/sdut_grade/sounds/logic.mid"); 
		
			//String path = getClass().getResource("../sounds/logic.mid").getPath();
			//in = new FileInputStream(path);
			AudioStream as = new AudioStream(in);
			AudioPlayer.player.start(as);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
