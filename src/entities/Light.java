package entities;

import org.lwjgl.util.vector.Vector3f;

import toolBox.Input;

public class Light {
	
	private Vector3f position;
	private Vector3f colour;
	
	public Light(Vector3f position, Vector3f colour) {
		this.position = position;
		this.colour = colour;
	}
	public void updatePosition(double speed){
		if(Input.getKey(Input.KEY_I)){
			position.z-=speed;
		}
		if(Input.getKey(Input.KEY_K)){
			position.z+=speed;
		}
		if(Input.getKey(Input.KEY_L)){
			position.x+=speed;
		}
		if(Input.getKey(Input.KEY_J)){
			position.x-=speed;
		}
		if(Input.getKey(Input.KEY_U)){
			position.y-=speed;
		}
		if(Input.getKey(Input.KEY_O)){
			position.y+=speed;
		}
	}
	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Vector3f getColour() {
		return colour;
	}

	public void setColour(Vector3f colour) {
		this.colour = colour;
	}
	
}
