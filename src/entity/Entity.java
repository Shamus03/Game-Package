package entity;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;

import shape.*;
import camera.*;

public class Entity {
	public static ArrayList<Entity> entities = new ArrayList<Entity>();
    static ArrayList<ArrayList<Entity>> entityLists = new ArrayList<ArrayList<Entity>>();
	
	protected float xPos,yPos;
	protected float xVel,yVel;
	
	protected Polygon2D boundingBox;
	
	protected Color color = Color.black;
	
	public Entity() {
		xPos = yPos = xVel = yVel = 0;
	}
	
	public void addToList() {
		entities.add(this);
	}

    public void removeFromList() {
        entities.remove(this);
    }
	
	public void tick(int delta) {
        xPos += xVel * delta;
        yPos += yVel * delta;
		updateBoundingBox();
		//movement
	}
	
	public void onCollide(Entity other) {
		//collision with other entity
	}
	
	public void updateBoundingBox() {
		//sets the bounding box
	}
	
	public void draw(Graphics2D g) {
		drawBoundingBox(g);
	}
	
	void drawBoundingBox(Graphics2D g) {
		if(boundingBox != null) {
			g.setColor(Color.red);
			Camera.drawPolygon2D(boundingBox,g);
		}
	}
	
	public boolean colliding(Entity other) {
		if(this.boundingBox != null && other.boundingBox != null)
			return polygonsIntersect(this.boundingBox,other.boundingBox);
		else
			return false;
	}
	
	public float getxPos() {return xPos;}
	public float getyPos() {return yPos;}
	public void setxPos(float xPos) {this.xPos = xPos;}
	public void setyPos(float yPos) {this.yPos = yPos;}
	public float getxVel() {return xVel;}
	public float getyVel() {return xVel;}
	public void setxVel(float xVel) {this.xVel = xVel;}
	public void setyVel(float yVel) {this.yVel = yVel;}
	public Polygon2D getBoundingBox() { return boundingBox; }
	
	public static void collideAllEntities() {
		for(int i = 0; i < entities.size() - 1; i++) {
			Entity e1 = entities.get(i);
			for(int ii = i + 1; ii < entities.size(); ii++) {
				Entity e2 = entities.get(ii);
				if(e1.colliding(e2)) {
					e1.onCollide(e2);
					e2.onCollide(e1);
				}
			}
		}
	}
	
	public static boolean polygonsIntersect(Polygon2D p1, Polygon2D p2) {
		Point2D.Float p;
		for(int i = 0; i < p2.npoints;i++)
		{
			p = new Point2D.Float(p2.xpoints[i],p2.ypoints[i]);
			if(p1.contains(p))
				return true;
		}
		for(int i = 0; i < p1.npoints;i++)
		{
			p = new Point2D.Float(p1.xpoints[i],p1.ypoints[i]);
			if(p2.contains(p))
				return true;
		}
		return false;
	}

    public static int addEntityList(ArrayList<Entity> list) {
        entityLists.add(list);
        return entityLists.indexOf(list);
    }

    public static void switchEntityList(int index) {
        entities = entityLists.get(index);
    }
}
