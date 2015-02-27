package guiComponents;


import org.lwjgl.util.vector.Vector2f;

public class TextButton extends Button {
	public TextButton(String text, Vector2f position) {
		super(position, "text");
	}
	
	public void render(){
		super.render();
		//Render Text
	}
}