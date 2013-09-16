package camera;

import input.Input;

import java.awt.Graphics2D;
import java.awt.Polygon;

import shape.Polygon2D;

import entity.CameraFocusEntity;
import entity.Entity;

public class Camera {
	public static float xPos=0,yPos=0,scale=1;
	
	public static int height,width;
	
	public static CameraFocusEntity focus = new CameraFocusEntity();
	
	public static void reset() {
		xPos = yPos = 0;
		scale = 1;
	}
	
	public static void drawLine(float x1, float y1, float x2, float y2, Graphics2D g) {
		g.drawLine(applyTranslation(x1,xPos,width),height - applyTranslation(y1,yPos,height),applyTranslation(x2,xPos,width),height - applyTranslation(y2,yPos,height));
	}
	
	public static void fillOval(float x, float y, float w, float h, Graphics2D g) {
		g.fillOval(applyTranslation(x,xPos,width),height - applyTranslation(y,yPos,height),(int)(w*scale),(int)(h*scale));
	}
	
	public static void fillCenteredOval(float x, float y, float w, float h, Graphics2D g) {
		g.fillOval(applyTranslation(x-w/2,xPos,width),height - applyTranslation(y+h/2,yPos,height),(int)(w*scale),(int)(h*scale));
	}
	
	public static void drawOval(float x, float y, float w, float h, Graphics2D g) {
		g.drawOval(applyTranslation(x,xPos,width),height - applyTranslation(y,yPos,height),(int)(w*scale),(int)(h*scale));
	}
	
	public static void drawCenteredOval(float x, float y, float w, float h, Graphics2D g) {
		g.drawOval(applyTranslation(x-w/2,xPos,width),height - applyTranslation(y+h/2,yPos,height),(int)(w*scale),(int)(h*scale));
	}
	
	public static void fillRect(float x, float y, float w, float h, Graphics2D g) {
		g.fillRect(applyTranslation(x,xPos,width),height - applyTranslation(y,yPos,height),(int)(w*scale),(int)(h*scale));
	}
	
	public static void drawRect(float x, float y, float w, float h, Graphics2D g) {
		g.drawRect(applyTranslation(x,xPos,width),height - applyTranslation(y,yPos,height),(int)(w*scale),(int)(h*scale));
	}
	
	public static void fillPolygon2D(Polygon2D p, Graphics2D g) {
		Polygon np = new Polygon();
		for(int i = 0; i < p.npoints; i++)
			np.addPoint(applyTranslation(p.xpoints[i],xPos,width), height - applyTranslation(p.ypoints[i],yPos,height));		
		g.fill(np);
	}
	
	public static void drawPolygon2D(Polygon2D p, Graphics2D g) {
		Polygon np = new Polygon();
		for(int i = 0; i < p.npoints; i++)
			np.addPoint(applyTranslation(p.xpoints[i],xPos,width), height - applyTranslation(p.ypoints[i],yPos,height));		
		g.draw(np);
	}
	
	static int applyTranslation(float z, float dz, int depth) {
		return (int)((z - dz)*scale) + depth/2;
	}
	
	public static float getMouseXPos() {
		return (xPos + (Input.MOUSE_X - width/2)/scale);
	}
	
	public static float getMouseYPos() {
		return (yPos + (height/2 - Input.MOUSE_Y)/scale);
	}
	
	public static boolean isOnScreen(Entity e) {
		Polygon2D poly = e.getBoundingBox();
		if(poly == null)
			return true;
		Polygon2D screen = new Polygon2D();
		
		screen.addPoint((xPos + (0 - width/2)/scale),(yPos + (height/2 - 0)/scale));
		screen.addPoint((xPos + (width - width/2)/scale),(yPos + (height/2 - 0)/scale));
		screen.addPoint((xPos + (width - width/2)/scale),(yPos + (height/2 - height)/scale));
		screen.addPoint((xPos + (0 - width/2)/scale),(yPos + (height/2 - height)/scale));
		
		return poly.intersects(screen);
	}
}
