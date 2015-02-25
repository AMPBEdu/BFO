package toolBox;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

import renderEngine.DisplayManager;
import entities.Camera;

public class KeyIn {
	private static boolean finished  = false;
	private static boolean escMenu   = false;
	private static boolean sprinting = false;
	public static void updateInput(){
		if(!finished){
			menuEvent();
			closeEvent();
		if(!escMenu){
			sprintEvent();
			moveCamEvent();
			}
		}
	}
	//Listen Events
	private static void closeEvent(){
		if(Input.getKey(Input.KEY_F4) && Input.getKey(Input.KEY_LALT)|| Input.getKey(Input.KEY_RALT)){
			finished = true;
		}
	}
	private static void menuEvent(){
		if(Input.getMouseDown(0)){
			escMenu = false;
			Mouse.setGrabbed(true);
		}
		if(Input.getKeyDown(Input.KEY_ESCAPE)){
			escMenu = !escMenu;
			Mouse.setGrabbed(!escMenu);
			Input.setMousePosition(new Vector2f(DisplayManager.getWidth()/2, DisplayManager.getHeight()/2));
		}
	}
	private static void moveCamEvent(){
		if(Input.getKey(Input.KEY_W)){
			Camera.moveForward();
		}
		if(Input.getKey(Input.KEY_S)){
			Camera.moveBackward();
		}
		if(Input.getKey(Input.KEY_D)){
			Camera.moveRight();
		}
		if(Input.getKey(Input.KEY_A)){
			Camera.moveLeft();
		}
		if(Input.getKey(Input.KEY_Q)){
			Camera.moveDown();
		}
		if(Input.getKey(Input.KEY_E)){
			Camera.moveUp();
		}
	}
	private static void sprintEvent(){
		if(Input.getKeyDown(Input.KEY_LSHIFT)){
			sprinting = true;
		}else if(Input.getKeyUp(Input.KEY_LSHIFT)){
			sprinting = false;
		}
	}
	
	//Getters
	public static boolean isFinished(){
		return finished;
	}
	public static boolean isEscMenu() {
		return escMenu;
	}
	public static boolean isSprinting() {
		return sprinting;
	}
	//Setters
	public static void setFinished(boolean done){
		finished = done;
	}
}
