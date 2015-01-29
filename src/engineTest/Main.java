package engineTest;

import org.lwjgl.opengl.Display;

import Models.RawModel;
import Shaders.StaticShader;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.Renderer;

public class Main {
	
	public static void main(String[] args){
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		
		StaticShader shader = new StaticShader();
		
		float[] vertices = {
			// Bottom Left
				-0.5f,  0.5f, 0f,
				-0.5f, -0.5f, 0f,
				 0.5f, -0.5f, 0f,
			 //Top Right
				 0.5f, -0.5f, 0f,
				 0.5f, 0.5f,  0f,
				-0.5f, 0.5f, 0f
		};
		
		RawModel model = loader.loadToVAO(vertices);
		
		while(!Display.isCloseRequested()) {
			renderer.prepare();
			//game logic
			//render
			renderer.prepare();
			shader.start();
			renderer.render(model);
			shader.stop();
			DisplayManager.updateDisplay();
		}
		
		shader.cleanUp();
		loader.cleanUP();
		DisplayManager.closeDisplay();
	}
	
}
