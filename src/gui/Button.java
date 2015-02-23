package gui;

import loaders.Loader;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

import renderEngine.DisplayManager;
import toolBox.Input;

public class Button {
	Loader loader = new Loader();
	public Texture normalButton;
	public Texture hoveredButton;
	public Texture clickedButton;
	public Texture disabledButton;
	public Vector2f mousePos;
	public Vector2f position;
	public static Vector2f size;
	public final int displayHeight = DisplayManager.getHeight();
	public static boolean disabled = false;
	public static boolean clicked  = false;
	
	public Button(Vector2f position, Vector2f size, Texture[] button){
		this.position  = position;
		this.size      = size;
		normalButton   = button[0];
		hoveredButton  = button[1];
		clickedButton  = button[2];
		disabledButton = button[3];
	}
	
	public void Update(){
		mousePos = Input.getMousePosition();
		if(!disabled){
			if(!mouseOnButton()){
				renderButton(normalButton);
			}else if(mouseOnButton() && !Input.getMouse(0)){
				renderButton(hoveredButton);
			}else{
				renderButton(clickedButton);
			}
		}else{
			renderButton(disabledButton);
		}
		if(mouseOnButton() && Input.getMouseUp(0)){
			clicked = true;
		}else if(clicked){
			clicked = false;
		}
	}
	
	public void renderButton(Texture button){
		//Render Button Code
	}
	
	public boolean mouseOnButton(){
		if(mousePos.x >= position.x &&
		   mousePos.x <= position.x + size.x &&
		   mousePos.y >= displayHeight-position.y &&
		   mousePos.y <= displayHeight-(position.y + size.y)){
			return true;
		}else{
		return false;
				}
	}
	public boolean getClicked(){
		return clicked;
	}
}
