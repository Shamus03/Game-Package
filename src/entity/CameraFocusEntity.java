package entity;

import camera.Camera;

public class CameraFocusEntity extends Entity {
	Entity focus;
	public float xShake = 0, yShake = 0;
	
	public void tick(int delta) {
		if(focus != null) {
			xPos = focus.xPos;
			yPos = focus.yPos;
		}

        Camera.xPos = xPos + xShake;
        Camera.yPos = yPos + yShake;
        xShake = yShake = 0;
	}
	
	public void setFocus(Entity focus) {
		this.focus = focus;
        xPos = focus.xPos;
        yPos = focus.yPos;
	}

	public Entity getFocus() {
		return focus;
	}
}
