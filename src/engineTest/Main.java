package engineTest;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import Entities.Camera;
import Entities.Entity;
import Models.RawModel;
import Models.TexturedModel;
import Shaders.StaticShader;
import Textures.ModelTexture;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.Renderer;

public class Main {
	
	public static void main(String[] args){
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		
		float[] vertices = {
				-0.5f,0.5f,-0.5f,	
				-0.5f,-0.5f,-0.5f,	
				 0.5f,-0.5f,-0.5f,	
				 0.5f,0.5f,-0.5f,		
				
				-0.5f,0.5f,0.5f,	
				-0.5f,-0.5f,0.5f,	
				 0.5f,-0.5f,0.5f,	
				 0.5f,0.5f,0.5f,
				
				 0.5f,0.5f,-0.5f,	
				 0.5f,-0.5f,-0.5f,	
				 0.5f,-0.5f,0.5f,	
				 0.5f,0.5f,0.5f,
				
				-0.5f,0.5f,-0.5f,	
				-0.5f,-0.5f,-0.5f,	
				-0.5f,-0.5f,0.5f,	
				-0.5f,0.5f,0.5f,
				
				-0.5f,0.5f,0.5f,
				-0.5f,0.5f,-0.5f,
				 0.5f,0.5f,-0.5f,
				 0.5f,0.5f,0.5f,
				
				-0.5f,-0.5f,0.5f,
				-0.5f,-0.5f,-0.5f,
				 0.5f,-0.5f,-0.5f,
			   	 0.5f,-0.5f,0.5f
		};
		
		int[] indices = {
				0,1,3,	
				3,1,2,	
				4,5,7,
				7,5,6,
				8,9,11,
				11,9,10,
				12,13,15,
				15,13,14,	
				16,17,19,
				19,17,18,
				20,21,23,
				23,21,22
		};
		
		float[] textureCoords = {
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0
		};
		
		RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
		TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("AnonTexture")));
		Entity entity = new Entity(staticModel, new Vector3f(0,0,-5),0,0,0,1);
		Camera camera = new Camera();
		
		while(!Display.isCloseRequested()) {
			// Game logic
			//entity.increasePosition(0, 0, -0.05f);
			camera.move();
			//entity.increaseRotation(0, 1, 0);
			//Render
			renderer.prepare();
			shader.start();
			shader.loadViewMatrix(camera);
			renderer.render(entity,shader);
			shader.stop();
			DisplayManager.updateDisplay();
		}
		
		shader.cleanUp();
		loader.cleanUP();
		DisplayManager.closeDisplay();
	}
	
}
