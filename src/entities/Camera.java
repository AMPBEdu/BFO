package entities;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import toolBox.KeyIn;
import toolBox.Time;

public class Camera {
	
	private static Vector3f position = new Vector3f(100,5,-150);
	private float pitch;
	private static float yaw;
	private float roll;
	private static final float walkSpeed = 15;
	private static final float sprintSpeed = 25; 
	private static float movement;
	
	float dx = 0.0f;
	float dy = 0.0f;
	float dt = 0.0f;
	float lastTime = 0.0f;
	float time = 0.0f;

	float mouseSensitivity = 0.08f;
	
	public Camera(){}
	
	// Camera Movement
	public void updatePosition(){
		if(!KeyIn.isEscMenu()){
			dx = Mouse.getDX();
	        dy = Mouse.getDY();
	        yaw(dx * mouseSensitivity);
	        pitch(-dy * mouseSensitivity);
		}
		if(KeyIn.isSprinting()){
			movement = sprintSpeed * Time.getDelta();
		}else{
			movement = walkSpeed * Time.getDelta();
		}
	}
	public void yaw(float amount){
	yaw += amount;
	}
	public void pitch(float amount){
	pitch += amount;
	}
	//Movement
	public static void moveForward()
	{
	    position.x += movement * (float)Math.sin(Math.toRadians(yaw));
	    position.z -= movement * (float)Math.cos(Math.toRadians(yaw));
	}
	public static void moveBackward()
	{
	    position.x -= movement * (float)Math.sin(Math.toRadians(yaw));
	    position.z += movement * (float)Math.cos(Math.toRadians(yaw));
	}
	public static void moveLeft()
	{
	    position.x += movement * (float)Math.sin(Math.toRadians(yaw-90));
	    position.z -= movement * (float)Math.cos(Math.toRadians(yaw-90));
	}
	public static void moveRight()
	{
	    position.x += movement * (float)Math.sin(Math.toRadians(yaw+90));
	    position.z -= movement * (float)Math.cos(Math.toRadians(yaw+90));
	}
	public static void moveUp(){
		position.y += movement;
	}
	public static void moveDown(){
		position.y -= movement;
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
