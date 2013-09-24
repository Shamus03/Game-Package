package game;

import input.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import camera.Camera;

import entity.Entity;
import frame.GameFrame;

@SuppressWarnings("serial")
public class Game extends JPanel {
	int width;
	int height;
	
	GameFrame frame;

    boolean showFPSInWindow;
	
	public Game() {
		super();

        showFPSInWindow = false;
		
		Camera.reset();
		
		setBackground(Color.white);
		setDoubleBuffered(true);
		
		setFocusable(true);
		requestFocus();
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				Input.keyChange(e, true);
			}

			public void keyReleased(KeyEvent e) {
				Input.keyChange(e, false);
			}
		});
		addMouseMotionListener(new MouseInputAdapter() {
			public void mouseMoved(MouseEvent e) 		{ Input.mouseUpdate(e.getX(), e.getY()); }
			public void mouseDragged(MouseEvent e)		{ mouseMoved(e); }
		});
		addMouseListener(new MouseInputAdapter() {
			public void mousePressed(MouseEvent e)		{ Input.mouseChange(e, true	); }
			public void mouseReleased(MouseEvent e)		{ Input.mouseChange(e, false); }
		});
	}
	
	long lastTime = System.currentTimeMillis();
	
	@Override
	public void paintComponent(Graphics gg) {		
		
		Graphics2D g = (Graphics2D)gg;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		super.paintComponent(gg);
		
		int delta = (int)(System.currentTimeMillis() - lastTime);
		lastTime = System.currentTimeMillis();	
		
		refreshSize();
		
		gameLoop(delta, g);
		
		updateFPS();

		repaint();
	}
	
	public void gameLoop(int delta, Graphics2D g) {
		gameTick(delta);
		drawGame(g);
	}
	
	int fps = 0;
	int lastFPS = 0;
	long lastFPSUpdate = System.currentTimeMillis();
	void updateFPS() {
		if(System.currentTimeMillis() - lastFPSUpdate >= 1000) {
			lastFPSUpdate = System.currentTimeMillis();
			lastFPS = fps;
			fps = 0;
            if(showFPSInWindow)
                frame.setTitle(frame.getTitle() + "     FPS:" + lastFPS);
		} else
			fps++;
	}

    public void showFPS(boolean value) {
        showFPSInWindow = value;
    }
	
	public void gameTick(int delta) {
		for(int i = 0; i < Entity.entities.size(); i++)
			Entity.entities.get(i).tick(delta);
		Entity.collideAllEntities();
	}
	
	public void drawGame(Graphics2D g) {
		for(int i = 0; i < Entity.entities.size(); i++)
			if(Camera.isOnScreen(Entity.entities.get(i)))
				Entity.entities.get(i).draw(g);
	}
	
	public void setFrame(GameFrame frame) {
		this.frame = frame;
	}
	
	public void refreshHeight(){height = Camera.height = getHeight();}
	public void refreshWidth(){width = Camera.width = getWidth();}
	public void refreshSize(){refreshWidth();refreshHeight();}
}
