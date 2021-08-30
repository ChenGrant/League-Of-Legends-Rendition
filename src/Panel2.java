import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Panel2 extends JPanel{
	Image background = new ImageIcon("src\\Images\\Panel 2\\background.png").getImage();
	Image instructions  = new ImageIcon("src\\Images\\Panel 2\\instructions.png").getImage();
	int instructionsW = 600;
	int instructionsH = 100;
	int maxW, maxH;
	
	String text = "";
	JTextArea ta;
	JScrollPane scroll;
	
	
	Panel2(int W, int H){
		maxW = W;
		maxH = H;
		this.setLayout(null);
		this.setSize(maxW, maxH);
		setUpInstructions();
	}
	
	public void readFile(String filePath) {
		File f = new File (filePath);
		Scanner sc;
		try {
			sc = new Scanner(f);
			while(sc.hasNextLine()) {
				text+=(sc.nextLine()+"\n");
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void setUpInstructions() {
		readFile("src\\Images\\Panel 2\\Instructions.txt");
		int w = 900;
		int h = 250;
		ta = new JTextArea(text, w,h);
		ta.setPreferredSize(new Dimension(w,h));
		ta.setLineWrap(true);
		scroll = new JScrollPane(ta);
		scroll.setPreferredSize(new Dimension(w,h));
		scroll.setBounds((maxW - w) / 2,300,w,h);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setVisible(true);
		this.add(scroll);
	}
	public void paint(Graphics g) {
		super.paint(g);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(background, 0,0,maxW, maxH, this);
		g.drawImage(instructions, (maxW - instructionsW) / 2,120,instructionsW, instructionsH, this);
	}
	
}
