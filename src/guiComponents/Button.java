package guiComponents;

import loaders.Loader;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

import renderEngine.DisplayManager;
import textures.ButtonTexture;
import toolBox.Input;

public class Button {
	Loader loader = new Loader();
	private static Vector2f mousePos;
	public Vector2f renderPosition;
	public static Vector2f size;
	public final int displayHeight = DisplayManager.getHeight();
	public static boolean disabled = false;
	public static boolean clicked  = false;
	public static String fileName;
	ButtonTexture buttonTexture;
	
	public Button(Vector2f renderPosition){
		this.renderPosition  = renderPosition;
	}
	
	public void Render(){
		mousePos = Input.getMousePosition();
		if(!disabled){
			if(!mouseOnButton()){
				ButtonMode.setMode(ButtonMode.normalMode);
			}else if(mouseOnButton() && !Input.getMouse(0)){
				ButtonMode.setMode(ButtonMode.hoverMode);
			}else{
				ButtonMode.setMode(ButtonMode.clickMode);
			}
		}else{
			ButtonMode.setMode(ButtonMode.disabledMode);
		}
		if(mouseOnButton() && Input.getMouseUp(0)){
			clicked = true;
		}else if(clicked){
			clicked = false;
		}
		if(ButtonMode.hasChanged()){
			renderButton(buttonTexture.getTexture());
		}
	}
	
	public void renderButton(Texture button){
		//Render Button Code
	}
	
	public boolean mouseOnButton(){
		if(mousePos.x >= renderPosition.x &&
		   mousePos.x <= renderPosition.x + size.x &&
		   mousePos.y >= displayHeight-renderPosition.y &&
		   mousePos.y <= displayHeight-(renderPosition.y + size.y)){
			return true;
		}else{
		return false;
				}
	}
	public boolean getClicked(){
		return clicked;
	}
}
