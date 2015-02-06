package engineTest;

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

public class Main {
	
	public static void main(String[] args){
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		
		RawModel model = OBJLoader.loadObjModel("dragon", loader);
		TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("goldTexture")));
		Entity entity = new Entity(staticModel, new Vector3f(0,0,-50),0,0,0,1);
		Camera camera = new Camera();
		
		while(!Display.isCloseRequested()) {
			// Game logic
			//entity.increasePosition(0, 0, -0.05f);
			camera.move(0.1f);
			entity.increaseRotation(0, 1, 0);
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
