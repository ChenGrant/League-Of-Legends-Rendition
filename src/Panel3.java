import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;

public class Panel3 extends JPanel implements ActionListener, KeyListener {
	int maxW, maxH;
	Image dirt = new ImageIcon("src\\Images\\Panel 3\\dirt.png").getImage();
	
	CharacterSelect cs1 = new CharacterSelect(1, true);
	CharacterSelect cs2 = new CharacterSelect(2, false);
	Player p1 = new Player(1);
	Player p2 = new Player(2);
	Arena arena;
	Characters c1;
	Characters c2;
	ArrayList<Ability> a1 = new ArrayList<Ability>();
	ArrayList<Ability> a2 = new ArrayList<Ability>();
	boolean first = true;
	boolean first2 = true;
	
	Panel3(int W, int H) {
		maxW = W;
		maxH = H;
		arena = new Arena (maxW, maxH);
		this.setBackground(Color.green);
		this.setLayout(null);
		this.setSize(maxW, maxH);
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.addKeyListener(this);
		setUpCharacterSelect(cs1);
	}
	
	public void setUpCharacterSelect(CharacterSelect cs) {
		cs.lockIn.addActionListener(this);
		for (int i = 0; i < cs.champButtons.length; i++) {
			cs.champButtons[i].addActionListener(this);
			this.add(cs.champButtons[i]);
		}
	}
	
	public void collision() {
		Rectangle r1 = c1.bounds();
		Rectangle r2 = c2.bounds();
		ArrayList<Rectangle> ra1 = new ArrayList<Rectangle>();
		ArrayList<Rectangle> ra2 = new ArrayList<Rectangle>();
		for (int i =0; i<a1.size(); i++ ) {
			ra1.add(a1.get(i).bounds());
		}
		for (int i =0; i<a2.size(); i++ ) {
			ra2.add(a2.get(i).bounds());
		}
		
		boolean break2 = false;
		for (int i =0; i<a1.size(); i++ ) {
			if (ra1.get(i).intersects(r2)&& a1.get(i).show) {
				
				if (a1.size()!=1) {
					a1.remove(i);
					c2.health=c2.health-20;
				}
				if (a1.size()==1 && first ) {
					first = false;
					c2.health=c2.health-20;
					
				}
				ra1.remove(i);
				
				if (c2.health<0)
					c2.health=0;
				if (c2.health==0) {
					c2.show = false;
				}
				break2 = true;
				break;
			}
			if (break2)
				break;
		}
		
		break2 = false;
		for (int i =0; i<a2.size(); i++ ) {
			if (ra2.get(i).intersects(r1)&& a2.get(i).show) {
				if (a2.size()!=1) {
					a2.remove(i);
					c1.health=c1.health-20;
				}
				if (a2.size()==1 && first2 ) {
					first2 = false;
					c1.health=c1.health-20;
					
				}

				ra2.remove(i);
				if (c1.health<0)
					c1.health=0;
				if (c1.health==0) {
					c1.show = false;
				}
				
				break2 = true;
				break;
			}
			if (break2)
				break;
		}
	}
	public void paint(Graphics g) {
		super.paint(g);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (cs1.show) {
			g.drawImage(cs1.background, 0,0, maxW,maxH, this);
			if (cs1.showLockIn) {
				this.add(cs1.lockIn);
			}

			repaint();
		}
		
		else if (cs2.show) {
			g.drawImage(cs2.background, 0,0, maxW,maxH, this);
			if (cs2.showLockIn) {
				this.add(cs2.lockIn);
			}

			repaint();
		}
		
		else if (arena.show){
			g.drawImage(dirt, 0,0, maxW, maxH, this);
			g.drawImage(arena.img, arena.leftX, arena.topY, arena.imgW, arena.imgH, this);
			g.drawLine(arena.midX, arena.topY, arena.midX, arena.bottomY);

			g.drawImage(c1.healthBack, arena.leftX, 60, c1.healthBackW, c1.healthBackH, this);
			g.drawImage(c1.healthImg, arena.leftX,60,(int)((c1.health/100.0)*c1.healthBackW),c1.healthBackH,this);
			g.drawImage(c1.healthLine, arena.leftX+(int)((c1.health/100.0)*c1.healthBackW),60, 2,c1.healthBackH,this );
			g.drawImage(c1.healthLine, arena.leftX,60, 2,c1.healthBackH,this );
			
			g.drawImage(c2.healthBack, arena.rightX-c2.healthBackW, 60, c2.healthBackW, c2.healthBackH, this);
			g.drawImage(c2.healthImg, arena.rightX-(int)((c2.health/100.0)*c2.healthBackW), 60, (int)((c2.health/100.0)*c2.healthBackW), c2.healthBackH, this);
			g.drawImage(c2.healthLine, arena.rightX+c2.healthBackW-2, 60, 2, c2.healthBackH, this);
			g.drawImage(c2.healthLine,arena.rightX-(int)((c2.health/100.0)*c2.healthBackW)-2, 60,2, c2.healthBackH, this );
			
			g.drawImage(new ImageIcon("src\\Images\\Panel 3\\Game\\player1.png").getImage(), arena.leftX, 5, 180, 50, this);
			g.drawImage(new ImageIcon("src\\Images\\Panel 3\\Game\\player2.png").getImage(), arena.rightX-180, 5, 180, 50, this);
			
			if (c1.show) {
				if (c1.left)
					c1.moveLeft();
				if (c1.right)
					c1.moveRight();
				if (c1.up)
					c1.moveUp();
				if (c1.down)
					c1.moveDown();
				g.drawImage(c1.icon, c1.x, c1.y, c1.iconW, c1.iconH, this);
				
			
			}

			
			if (c2.show) {
				if (c2.left)
					c2.moveLeft();
				if (c2.right)
					c2.moveRight();
				if (c2.up)
					c2.moveUp();
				if (c2.down)
					c2.moveDown();
				g.drawImage(c2.icon, c2.x, c2.y, c2.iconW, c2.iconH, this);
			}
			
			for (int i =0; i<a1.size(); i++) {
				c1.endTime = System.currentTimeMillis();
				if ((c1.endTime-c1.startTime)/1000.0>c1.CD) {
					c1.onCD = false;
					g.drawImage(c1.ready,arena.leftX,arena.bottomY+20,c1.readyW, c1.readyH, this);
				}
				else {
					int left = (int)(c1.CD-(c1.endTime-c1.startTime)/1000.0)+1;
					Image img = new ImageIcon("src\\Images\\Panel 3\\Characters\\"+left+".png").getImage();
					g.drawImage(c1.cdImg, arena.leftX, arena.bottomY+20, c1.cdW, c1.cdH, this);
					if (left!=1)
						g.drawImage(img, arena.leftX+c1.cdW+10,arena.bottomY+20, 20,c1.cdH , this);
					else
						g.drawImage(img, arena.leftX+c1.cdW+10,arena.bottomY+20, 5,c1.cdH , this);
				}
				
				if (a1.get(i).show) {
					a1.get(i).move();
					g.drawImage(a1.get(i).img, a1.get(i).x, a1.get(i).y, a1.get(i).imgW, a1.get(i).imgH, this);
				}
				
			}
			
			for (int i =0; i<a2.size(); i++) {
				c2.endTime = System.currentTimeMillis();
				if ((c2.endTime-c2.startTime)/1000.0>c2.CD) {
					c2.onCD = false;
					g.drawImage(c2.ready,arena.rightX-c2.readyW,arena.bottomY+20,c2.readyW, c2.readyH, this);
				}
				else {
					int left = (int)(c2.CD-(c2.endTime-c2.startTime)/1000.0)+1;
					Image img = new ImageIcon("src\\Images\\Panel 3\\Characters\\"+left+".png").getImage();
					g.drawImage(c2.cdImg, arena.rightX-150, arena.bottomY+20, c2.cdW, c2.cdH, this);
					if (left!=1)
						g.drawImage(img, arena.rightX-150+c2.cdW+20,arena.bottomY+20, 20,c2.cdH , this);
					else
						g.drawImage(img, arena.rightX-150+c2.cdW+20,arena.bottomY+20, 5,c2.cdH , this);
				}
				if (a2.get(i).show) {
					a2.get(i).move();
					g.drawImage(a2.get(i).img, a2.get(i).x, a2.get(i).y, a2.get(i).imgW, a2.get(i).imgH, this);
				}
			}
			if (c1.show && c2.show)
				collision();
			repaint();
		}
		
	}

	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < cs1.champButtons.length; i++) {
			if (e.getSource()==cs1.champButtons[i]) {
				p1.champID = i;
				cs1.showLockIn=true;
				break;
			}
		}
		
		if (e.getSource()==cs1.lockIn) {
			cs1.show = false;
			this.removeAll();
			cs2.show = true;
			setUpCharacterSelect(cs2);
		}
		
		for (int i = 0; i < cs2.champButtons.length; i++) {
			if (e.getSource()==cs2.champButtons[i]) {
				p2.champID = i;
				cs2.showLockIn=true;
				break;
			}
		}
		
		if (e.getSource()==cs2.lockIn) {
			cs2.show = false;
			this.removeAll();
			arena.show = true;
			c1 = new Characters(1, arena.leftX, arena.topY, arena.midX, arena.rightX, arena.bottomY, p1.champID, cs1.champButtons.length);
			c2 = new Characters(2, arena.leftX, arena.topY, arena.midX, arena.rightX, arena.bottomY, p2.champID, cs2.champButtons.length);
		}
		
	}

	public void keyTyped(KeyEvent e) {
		
	}
	
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();
		if (c1.show) {
			if (e.getKeyChar() == 'w') {
				c1.up = true;
			}
			if (e.getKeyChar() == 'a') {
				c1.left = true;
			}
			if (e.getKeyChar() == 's') {
				c1.down = true;		
			}
			if (e.getKeyChar() == 'd') {
				c1.right = true;
			}

			switch(key) {
				case KeyEvent.VK_SPACE:{
					if (!c1.onCD) {
						c1.onCD = true;
						c1.startTime = System.currentTimeMillis();
						Ability a = new Ability(p1.champID, c1.x, c1.y, c1.iconW, c1.iconH,cs1.champButtons.length, p1.playerNum, arena.leftX, arena.topY, arena.rightX, arena.bottomY);
						a1.add(a);
					}
				}
			}
		}
		if (c2.show) {
			switch (key) {
				case KeyEvent.VK_LEFT: {
					c2.left = true;
					break;
				}
				case KeyEvent.VK_RIGHT: {
					c2.right = true;
					break;
				}
				case KeyEvent.VK_DOWN: {
					c2.down = true;
					break;
				}
				case KeyEvent.VK_UP: {
					c2.up = true;
					break;
				}
			}

			if (e.getKeyChar()=='0' && !c2.onCD) {
				c2.onCD = true;
				c2.startTime = System.currentTimeMillis();
				Ability a = new Ability(p2.champID, c2.x, c2.y, c2.iconW, c2.iconH, cs2.champButtons.length, p2.playerNum, arena.leftX, arena.topY, arena.rightX, arena.bottomY);
				a2.add(a);
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (c1.show) {
			if (e.getKeyChar() == 'w') {
				c1.up = false;
			}
			if (e.getKeyChar() == 'a') {
				c1.left = false;
			}
			if (e.getKeyChar() == 's') {
				c1.down = false;	
			}
			if (e.getKeyChar() == 'd') {
				c1.right = false;
			}
		}
		if (c2.show) {
			switch (key) {
				case KeyEvent.VK_LEFT: {
					c2.left = false;
					break;
				}
				case KeyEvent.VK_RIGHT: {
					c2.right = false;
					break;
				}
				case KeyEvent.VK_DOWN: {
					c2.down = false;
					break;
				}
				case KeyEvent.VK_UP: {
					c2.up = false;
					break;
				}
			}
		}
	}
}
