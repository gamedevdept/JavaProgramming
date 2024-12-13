package scene;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Ending extends JPanel{

	ArrayList<String> textList;
	JLabel endingImageLabel;
	JLabel endingTextLabel;
	
	final int THICKNESS = 1;
	
	public Ending() {
		setLayout(null);
		setBackground(Color.black);
		
		textList = new ArrayList<>(Arrays.asList(new String[]{
			"<html>던전의 꼭대기에 도착했다.<br>"
			+ "<br>돌로 만들어진 제단 위에 두루마리가 놓여 있다.<br>"
			+ "<br>두루마리를 조심스럽게 펼치니, 고대 문자로 적힌 글이 있다.",
						
			"<html>그대의 도전 정신과 힘을 높게 사네.<br>"
			+ "<br>이 두루마리를 가지고 족장에게 오시게.<br>"
			+ "<br>그대는 이 아마존을 이끌어갈 족장이 될 것이오.",
						
			"<html>던전을 나왔다. 아마존은 족장이 없다.<br>"
			+ "<br>두루마리를 빤히 쳐다보다가 혼자 생각한다.<br>"
			+ "<br>'그래도 즐거웠잖아, 뭐 어때?'",
			
			"-THE END-"
		}));
		
		ImageIcon icon = new ImageIcon("images/엔딩그림.png");
	    Image image = icon.getImage();
	    double ratio = 0.6;
	    image = image.getScaledInstance((int)(icon.getIconWidth() * ratio), (int)(icon.getIconHeight() * ratio), Image.SCALE_SMOOTH);
	    icon = new ImageIcon(image);
		
		endingImageLabel = new JLabel(icon, JLabel.CENTER);
		endingImageLabel.setBorder(new LineBorder(Color.white, 3, false));
		endingImageLabel.setBounds(190, 50, 400, 300);
		add(endingImageLabel);
		
		endingTextLabel = new JLabel(textList.get(0));
		endingTextLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		endingTextLabel.setForeground(Color.white);
		endingTextLabel.setBounds(200, 380, 500, 150);
		add(endingTextLabel);
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if(textList.size() == 1) {
					SceneManager.loadScene(new Title());
				}
				else if(textList.size() == 2) {
					textList.remove(0);
					endingTextLabel.setText(textList.get(0));
					endingTextLabel.setHorizontalAlignment(JLabel.CENTER);
					endingTextLabel.setFont(new Font("맑은 고딕", Font.BOLD, 32));
					endingTextLabel.setLocation(140, 380);
				}
				else {
					textList.remove(0);
					endingTextLabel.setText(textList.get(0));
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	}

}
