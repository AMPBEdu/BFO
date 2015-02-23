package gui;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

public class Button {
	public Texture normalButton;
	public Texture hoveredButton;
	public Texture clickedButton;
	public Texture disabledButton;
	public Vector2f position;
	public static boolean clicked = false;
}
