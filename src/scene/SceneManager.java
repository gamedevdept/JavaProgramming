package scene;

import javax.swing.*;

public class SceneManager{
	
	public static JFrame sm;
	
	public SceneManager() {
		sm = new JFrame();
		
		sm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sm.setTitle("Game");
		sm.setSize(800, 600);
		sm.setLocationRelativeTo(null);
		sm.setResizable(false);
		sm.setVisible(true);

		loadScene(new Title());
	}
	
	public static void loadScene(JPanel panel) {
		sm.getContentPane().removeAll();
		sm.getContentPane().add(panel);
		sm.validate();
		sm.repaint();
	}
	
}
