package guiComponents;

import loaders.Loader;

import org.lwjgl.util.vector.Vector2f;

import textures.ButtonTexture;

public class MenuButton extends Button {
	
	public MenuButton(String text, Vector2f position) {
		super(position);
		fileName = "GUITextures/rpgui";
		size = new Vector2f(100,100);		
	}

}