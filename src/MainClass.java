import java.awt.Color;
import java.awt.Graphics2D;

import input.Input;

import shape.Polygon2D;
import sound.Sound;

import camera.Camera;

import entity.Entity;
import frame.GameFrame;
import game.Game;


public class MainClass {
	static GameFrame frame;
	static Game game;
	public static void main(String args[]) {
		Entity e = new Entity(){
			public void updateBoundingBox() {
				Polygon2D p = new Polygon2D();
				int size = 10;
				p.addPoint((int)(xPos-size),(int)(yPos-size));
				p.addPoint((int)(xPos-size),(int)(yPos+size));
				p.addPoint((int)(xPos+size),(int)(yPos+size));
				p.addPoint((int)(xPos+size),(int)(yPos-size));
				boundingBox = p;
			}
		};
		e.addToList();
		
		Entity player = new Entity() {
			double angle = 0;
			float accel = .0004f;
			float turnSpeed = .01f;
			float friction = 1 - .001f;
			
			public void tick(int delta) {
				if(Input.UP || Input.W) {
					xVel += accel*delta*Math.cos(angle);
					yVel += accel*delta*Math.sin(angle);
				}
				if(Input.DOWN || Input.S) {
					xVel -= accel*delta*Math.cos(angle);
					yVel -= accel*delta*Math.sin(angle);
				}
				if(Input.LEFT || Input.A){
					xVel += accel*delta*Math.cos(angle + Math.PI/2)/2;
					yVel += accel*delta*Math.sin(angle + Math.PI/2)/2;
				}
				if(Input.RIGHT || Input.D){
					xVel += accel*delta*Math.cos(angle - Math.PI/2)/2;
					yVel += accel*delta*Math.sin(angle - Math.PI/2)/2;
				}		
				
				if(!Input.MOUSE_RIGHT)
					updateAngle(delta);
				
				xPos += xVel*delta;
				yPos += yVel*delta;
				
				for(int i = 0; i < delta; i++) {
					xVel *= friction;
					yVel *= friction;
				}
				
				if(Input.NUMPAD1)
					Camera.focus.setFocus(Entity.entities.get(0));
				if(Input.NUMPAD2)
					Camera.focus.setFocus(Entity.entities.get(1));
				
				if(Input.Q)
					Camera.scale /= Math.pow(.999f, delta);
				if(Input.Z)
					Camera.scale *= Math.pow(.999f, delta);
				
				updateBoundingBox();
			}
			void updateAngle(int delta) {
				double mouseAngle = Math.atan2(Camera.getMouseYPos()-yPos,Camera.getMouseXPos()-xPos);
				double differenceAngle = angle - mouseAngle;
				while( differenceAngle < -Math.PI) differenceAngle += 2*Math.PI;
				while( differenceAngle >  Math.PI) differenceAngle -= 2*Math.PI;
				
				if(Math.abs(differenceAngle) <= turnSpeed*delta)
					angle = mouseAngle;
				else {
					int turnDir = (int)Math.signum(differenceAngle);
					angle -= turnSpeed*turnDir*delta;
				}
			}
			public void updateBoundingBox() {
				Polygon2D poly = new Polygon2D();
				int size = 10;
				poly.addPoint(xPos + (float)(size*1.5*Math.cos(angle)),yPos + (float)(size*1.5*Math.sin(angle)));
				poly.addPoint(xPos + (float)(size*Math.cos(angle + Math.PI*2/3)),yPos + (float)(size*Math.sin(angle + Math.PI*2/3)));
				poly.addPoint(xPos + (float)(size*Math.cos(angle - Math.PI*2/3)),yPos + (float)(size*Math.sin(angle - Math.PI*2/3)));
				boundingBox = poly;
			}
			public void draw(Graphics2D g) {
				if(Input.MOUSE_LEFT) {
					g.setColor(Color.red);
					Camera.drawLine(xPos,yPos,Camera.getMouseXPos(),Camera.getMouseYPos(),g);
					Camera.drawCenteredOval(Camera.getMouseXPos(),Camera.getMouseYPos(),10,10,g);
				}
				super.draw(g);
			}
			Sound collide = new Sound("assets\\hit.wav");
			public void onCollide(Entity other) {
				collide.playOnce();
			}
		};
		player.addToList();
		
		Entity cameraShake = new Entity() {
			public void tick(int delta) {
				if(Input.SPACE) {
					Camera.focus.xShake = (float)(Math.random()*40-20);
					Camera.focus.yShake = (float)(Math.random()*40-20);
				}
			}
		};
		cameraShake.addToList();
		
		Camera.focus.setFocus(player);
		Camera.focus.addToList();
		
		game = new Game();
		frame = new GameFrame("Test",game);
	}
}
