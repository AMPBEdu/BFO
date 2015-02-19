package Entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	private Vector3f position = new Vector3f(0,0,0);
	private float pitch;
	private float yaw;
	private float roll;
	
	public Camera(){}
	
	// Camera Movement
	public void updatePosition(double speed){
		//Mouse
		
		//Keyboard
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			position.z-=speed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			position.z+=speed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			position.x+=speed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			position.x-=speed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_Q)){
			position.y-=speed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_E)){
			position.y+=speed;
		}
	}
	// Getters and Setters
	public void yaw(float amt){
		yaw+=amt;
	}
	
	public void pitch(float amt){
		pitch+=amt;
	}
	
	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}
	
	
}
