package gui;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

public class MenuButton extends Button {
	public MenuButton(String text, Vector2f position) {
		size = new Vector2f(100,100);
		button[0] = loader.loadTextureSheet("normalButton", "rpgui");
		super(position, size, button);
	}

}