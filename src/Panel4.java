import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Panel4 extends JPanel implements ActionListener{
	Image background = new ImageIcon("src\\Images\\Panel 4\\background.png").getImage();
	int maxW, maxH;
	
	JButton b[] = new JButton[2];
	int buttonDimensions[][] = new int[2][2];
	
	Panel4(int W, int H){
		maxW = W;
		maxH = H;
		this.setLayout(null);
		setButtons();
		this.setSize(maxW, maxH);
	}
	
	public void setButtons() {
		Image img0 = new ImageIcon("src\\Images\\Panel 4\\exit.png").getImage();
		Image img1 = new ImageIcon("src\\Images\\Panel 4\\home.png").getImage();
		buttonDimensions[0][0] = 300;
		buttonDimensions[0][1] = 150;
		buttonDimensions[1][0] = 300;
		buttonDimensions[1][1] = 150;
		
		
		b[0] = new JButton();// exit button
		b[1] = new JButton();// home button
		b[0].setIcon(new ImageIcon(img0.getScaledInstance(buttonDimensions[0][0], buttonDimensions[0][1], java.awt.Image.SCALE_SMOOTH)));
		b[1].setIcon(new ImageIcon(img1.getScaledInstance(buttonDimensions[1][0], buttonDimensions[1][1], java.awt.Image.SCALE_SMOOTH)));
		b[0].setBounds((maxW - buttonDimensions[0][0]) / 2, 100, buttonDimensions[0][0], buttonDimensions[0][1]);
		b[1].setBounds((maxW - buttonDimensions[1][0]) / 2, 100+buttonDimensions[0][1]+100, buttonDimensions[1][0], buttonDimensions[1][1]);
		b[0].setMargin(new Insets(0, 0, 0, 0));
		b[1].setMargin(new Insets(0, 0, 0, 0));
		b[0].addActionListener(this);
		this.add(b[0]);
		this.add(b[1]);	
	}
	
	public void paint(Graphics g) {
		super.paint(g);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0,0,maxW, maxH, this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==b[0])
			System.exit(1);
		
	}
}
