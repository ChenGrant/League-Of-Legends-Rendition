import java.awt.*;
import javax.swing.*;

public class Panel1 extends JPanel {
	int maxW, maxH;
	Image background = new ImageIcon("src\\Images\\Panel 1\\background.png").getImage();
	Image logo = new ImageIcon("src\\Images\\Panel 1\\logo.png").getImage();
	int logoW = 800;
	int logoH = 200;
	
	JButton b[] = new JButton[2];
	int buttonDimensions[][] = new int[2][2];// first cell is to identify the button, 2nd cell is to identify w or h
	
	Panel1(int W, int H) {
		maxW = W;
		maxH = H;
		this.setLayout(null);
		setButtons();
		this.setSize(maxW, maxH);
	}

	public void setButtons() {
		Image img0 = new ImageIcon("src\\Images\\Panel 1\\settings.png").getImage();
		Image img1 = new ImageIcon("src\\Images\\Panel 1\\play.png").getImage();
		buttonDimensions[0][0] = 500;
		buttonDimensions[0][1] = 150;
		buttonDimensions[1][0] = 300;
		buttonDimensions[1][1] = 150;

		b[0] = new JButton();// settings button
		b[1] = new JButton();// play button
		b[0].setIcon(new ImageIcon(img0.getScaledInstance(buttonDimensions[0][0], buttonDimensions[0][1], java.awt.Image.SCALE_SMOOTH)));
		b[1].setIcon(new ImageIcon(img1.getScaledInstance(buttonDimensions[1][0], buttonDimensions[1][1], java.awt.Image.SCALE_SMOOTH)));
		b[0].setBounds(175+buttonDimensions[1][0]+50, 500, buttonDimensions[0][0], buttonDimensions[0][1]);
		b[1].setBounds(175, 500, buttonDimensions[1][0], buttonDimensions[1][1]);
		b[0].setMargin(new Insets(0, 0, 0, 0));
		b[1].setMargin(new Insets(0, 0, 0, 0));
		this.add(b[0]);
		this.add(b[1]);
	}

	public void paint(Graphics g) {
		super.paint(g);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, maxW, maxH, this);
		g.drawImage(logo, (maxW - logoW) / 2, maxH / 5, logoW, logoH, this);
	}
}
