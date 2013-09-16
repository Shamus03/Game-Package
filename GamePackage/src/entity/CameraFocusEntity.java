package entity;

import camera.Camera;

public class CameraFocusEntity extends Entity {
	Entity focus;
	private boolean focusInitialized = false;
	public float xShake = 0, yShake = 0;
	float springForce = .00005f;
	
	public void tick(int delta) {
		if(focus != null) {
			float force = (float)Math.sqrt(Math.pow(xPos-focus.xPos,2)+Math.pow(yPos-focus.yPos,2)) * springForce;

			double angle = Math.atan2(yPos-focus.yPos,xPos-focus.xPos);

			xVel -= Math.cos(angle)*force*delta;
			yVel -= Math.sin(angle)*force*delta;

			xPos += xVel*delta;
			yPos += yVel*delta;
			
			xVel *= Math.pow(.99,delta);
			yVel *= Math.pow(.99,delta);

			Camera.xPos = xPos + xShake;
			Camera.yPos = yPos + yShake;
			xShake = yShake = 0;
		}
	}
	
	public void setFocus(Entity focus) {
		this.focus = focus;
		if(!focusInitialized) {
			focusInitialized = true;
			xPos = focus.xPos;
			yPos = focus.yPos;
		}
	}
	
	public Entity getFocus() {
		return focus;
	}
}
