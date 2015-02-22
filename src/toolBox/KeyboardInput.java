package toolBox;

import org.lwjgl.input.Mouse;

import entities.Camera;

public class KeyboardInput {
	public static boolean escMenu   = false;
	public static boolean sprinting = false;
	public void updateInput(){
		menuEvent();
		if(!escMenu){
			sprintEvent();
			moveCamEvent();
		}
	}
	private void menuEvent(){
		if(Input.getKeyDown(Input.KEY_ESCAPE)){
			escMenu = !escMenu;
			Mouse.setGrabbed(!escMenu);
		}
	}
	private void moveCamEvent(){
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
	private void sprintEvent(){
		if(Input.getKeyDown(Input.KEY_LSHIFT)){
			sprinting = true;
			Camera.setSpeed(0.4f);
		}else if(Input.getKeyUp(Input.KEY_LSHIFT)){
			sprinting = false;
			Camera.setSpeed(0.2f);
		}
	}
}
