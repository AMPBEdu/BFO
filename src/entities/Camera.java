package entities;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import toolBox.Input;
import toolBox.KeyboardInput;

public class Camera {
	KeyboardInput keyIn = new KeyboardInput();
	
	private static Vector3f position = new Vector3f(100,5,-150);
	private float pitch;
	private static float yaw;
	private float roll;
	private static float speed = 0.2f;
	
	float dx = 0.0f;
	float dy = 0.0f;
	float dt = 0.0f;
	float lastTime = 0.0f;
	float time = 0.0f;

	float mouseSensitivity = 0.08f;
	
	public Camera(){}
	
	// Camera Movement
	public void updatePosition(){
		if(!keyIn.escMenu){
			dx = Mouse.getDX();
	        dy = Mouse.getDY();
	        yaw(dx * mouseSensitivity);
	        pitch(-dy * mouseSensitivity);
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
	    position.x += speed * (float)Math.sin(Math.toRadians(yaw));
	    position.z -= speed * (float)Math.cos(Math.toRadians(yaw));
	}
	public static void moveBackward()
	{
	    position.x -= speed * (float)Math.sin(Math.toRadians(yaw));
	    position.z += speed * (float)Math.cos(Math.toRadians(yaw));
	}
	public static void moveLeft()
	{
	    position.x += speed * (float)Math.sin(Math.toRadians(yaw-90));
	    position.z -= speed * (float)Math.cos(Math.toRadians(yaw-90));
	}
	public static void moveRight()
	{
	    position.x += speed * (float)Math.sin(Math.toRadians(yaw+90));
	    position.z -= speed * (float)Math.cos(Math.toRadians(yaw+90));
	}
	public static void moveUp(){
		position.y += speed;
	}
	public static void moveDown(){
		position.y -= speed;
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
	
	public static void setSpeed(float moveSpeed) {
		speed = moveSpeed;
	}
}
