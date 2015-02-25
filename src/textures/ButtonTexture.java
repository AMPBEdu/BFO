package textures;

import gui.ButtonMode;

import java.util.ArrayList;
import java.util.List;

import loaders.Loader;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

public class ButtonTexture {
	Loader loader = new Loader();
	private List<Vector2f> buttonPos = new ArrayList<Vector2f>(4);
	private Vector2f buttonSize;
	private String fileName;
	
	public ButtonTexture(List<Vector2f> buttonPos, Vector2f buttonSize, String fileName){
		this.buttonPos  = buttonPos;
		this.buttonSize = buttonSize;
		this.fileName   = fileName;
	}
	public Vector2f getSize() {
		return buttonSize;
	}
	public void setSize(Vector2f buttonSize) {
		this.buttonSize = buttonSize;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Texture getTexture(){
		Texture buttonTexture = loader.loadTextureSheet(buttonPos.get(ButtonMode.getMode()), buttonSize, fileName);
		return buttonTexture;
	}
}
