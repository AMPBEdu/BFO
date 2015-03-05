package guiComponents;

import loaders.Loader;
import loaders.Sprite;
import loaders.SpriteLoader;
import loaders.SpriteSheet;

import org.lwjgl.util.vector.Vector2f;
import renderEngine.DisplayManager;
import toolBox.Input;

public class Button {
	Loader loader = new Loader();
	SpriteSheet buttonSheet = new SpriteSheet("GUITextures/GUIButtons","res/GUITextures/GUIButtons.xml");
	SpriteLoader buttons = new SpriteLoader(buttonSheet);
	Sprite buttonSprite;
	ButtonMode mode = new ButtonMode();
	private static Vector2f mousePos;
	public Vector2f renderPosition;
	public static Vector2f size;
	public final int displayHeight = DisplayManager.getHeight();
	public static boolean disabled = false;
	public static boolean clicked = false;
	public String buttonName;

	public Button(Vector2f renderPosition, String buttonName) {
		this.renderPosition = renderPosition;
		this.buttonName = buttonName;
		size = new Vector2f(buttons.getSprite(buttonName + mode.getMode())
				.getSize());
	}

	public void render() {
		mousePos = Input.getMousePosition();
		if (!disabled) {
			if (!mouseOnButton()) {
				mode.setMode(ButtonMode.normalMode);
			} else if (mouseOnButton() && !Input.getMouse(0)) {
				mode.setMode(ButtonMode.hoverMode);
			} else {
				mode.setMode(ButtonMode.clickMode);
			}
		} else {
			mode.setMode(ButtonMode.disabledMode);
		}
		if (mouseOnButton() && Input.getMouseUp(0)) {
			clicked = true;
		} else if (clicked) {
			clicked = false;
		}
		if (mode.hasChanged()) {
			size = new Vector2f(buttons.getSprite(buttonName + mode.getMode())
					.getSize());
			renderButton(buttons.getSprite(buttonName + mode.getMode()));
		}
	}

	private void renderButton(Sprite button) {
		
	}

	public boolean mouseOnButton() {
		if (mousePos.x >= renderPosition.x
				&& mousePos.x <= renderPosition.x + size.x
				&& mousePos.y >= displayHeight - renderPosition.y
				&& mousePos.y <= displayHeight - (renderPosition.y + size.y)) {
			return true;
		} else {
			return false;
		}
	}
	public boolean getClicked() {
		return clicked;
	}
	
	public Sprite getSprite(){
		return buttons.getSprite(buttonName + mode.getMode());
	}
}
