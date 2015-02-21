package entities;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import toolBox.Input;

public class Camera {
	private Vector3f position;
	private float pitch;
	private float yaw;
	private float roll;
	
	float dx = 0.0f;
	float dy = 0.0f;
	float dt = 0.0f;
	float lastTime = 0.0f;
	float time = 0.0f;

	float mouseSensitivity = 0.08f;
	float movementSpeed = 10.0f; //move 10 units per second
	
	public Camera(Vector3f position){
		this.position = position;
	}
	
	// Camera Movement
	public void updatePosition(float speed, boolean enableFPS){
		if(enableFPS){
			dx = Mouse.getDX();
	        dy = Mouse.getDY();
	        yaw(dx * mouseSensitivity);
	        pitch(-dy * mouseSensitivity);
		}
		//Keyboard
		if(Input.GetKey(Input.KEY_W)){
			moveForward(speed);
		}
		if(Input.GetKey(Input.KEY_S)){
			moveBackward(speed);
		}
		if(Input.GetKey(Input.KEY_D)){
			moveRight(speed);
		}
		if(Input.GetKey(Input.KEY_A)){
			moveLeft(speed);
		}
		if(Input.GetKey(Input.KEY_Q)){
			position.y-=speed;
		}
		if(Input.GetKey(Input.KEY_E)){
			position.y+=speed;
		}
	}
	public void yaw(float amount){
	yaw += amount;
	}
	public void pitch(float amount){
	pitch += amount;
	}
	//Movement
	public void moveForward(float distance)
	{
	    position.x += distance * (float)Math.sin(Math.toRadians(yaw));
	    position.z -= distance * (float)Math.cos(Math.toRadians(yaw));
	}
	public void moveBackward(float distance)
	{
	    position.x -= distance * (float)Math.sin(Math.toRadians(yaw));
	    position.z += distance * (float)Math.cos(Math.toRadians(yaw));
	}
	public void moveLeft(float distance)
	{
	    position.x += distance * (float)Math.sin(Math.toRadians(yaw-90));
	    position.z -= distance * (float)Math.cos(Math.toRadians(yaw-90));
	}
	public void moveRight(float distance)
	{
	    position.x += distance * (float)Math.sin(Math.toRadians(yaw+90));
	    position.z -= distance * (float)Math.cos(Math.toRadians(yaw+90));
	}
	
	// Getters and Setters
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
