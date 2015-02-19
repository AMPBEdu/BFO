package engineTest;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import Entities.Camera;
import Entities.Entity;
import Loaders.Loader;
import Loaders.OBJLoader;
import Models.RawModel;
import Models.TexturedModel;
import Shaders.StaticShader;
import Textures.ModelTexture;
import renderEngine.DisplayManager;
import renderEngine.Renderer;
import toolBox.Input;

public class Main {
	
	public static void main(String[] args){
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		
		RawModel model = OBJLoader.loadObjModel("oSword", loader);
		TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("gray")));
		Entity dragon = new Entity(staticModel, new Vector3f(0,-3.5f,-14),0,0,0,0.75f);
		Camera camera = new Camera();
		
		while(!Display.isCloseRequested() && !Input.GetKeyUp(Input.KEY_F4)) {
			Input.Update();
			//entity.increasePosition(0, 0, -0.05f);
			camera.updatePosition(0.1f);
			//dragon.increaseRotation(0, 1, 0);
			//Render
			renderer.prepare();
			shader.start();
			shader.loadViewMatrix(camera);
			renderer.render(dragon,shader);
			shader.stop();
			DisplayManager.updateDisplay();
		}
		
		shader.cleanUp();
		loader.cleanUP();
		DisplayManager.closeDisplay();
	}
	
}
