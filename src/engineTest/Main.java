package engineTest;

import loaders.Loader;
import loaders.OBJLoader;
import models.RawModel;
import models.TexturedModel;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import renderEngine.DisplayManager;
import renderEngine.MasterRenderer;
import terrains.Terrain;
import textures.ModelTexture;
import textures.TerrainTexture;
import textures.TerrainTexturePack;
import toolBox.Input;
import toolBox.KeyboardInput;
import entities.Camera;
import entities.Entity;
import entities.Light;

public class Main {
	
	public static void main(String[] args){
		boolean escMenu = false;
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		KeyboardInput keyIn = new KeyboardInput();
		//******************Terrain Stuff*******************
				TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("grassTexture"));
				TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("mud"));
				TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("pinkFlowers"));
				TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("path"));
				TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
				TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));
		//*******************Model Stuff********************	
		RawModel model = OBJLoader.loadObjModel("dragon", loader);
		RawModel cube = OBJLoader.loadObjModel("sphere", loader);
		TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("green")));
		TexturedModel dragModel   = new TexturedModel(model, new ModelTexture(loader.loadTexture("red")));
		TexturedModel cubeTexture = new TexturedModel(cube, new ModelTexture(loader.loadTexture("yellow")));
		ModelTexture texture = staticModel.getTexture();
		ModelTexture redDrag = dragModel.getTexture();
		ModelTexture cubeText = cubeTexture.getTexture();
		
		texture.setShineDamper(5);
		texture.setReflectivity(2);
		redDrag.setShineDamper(10);
		redDrag.setReflectivity(2);
		cubeText.setShineDamper(10);
		cubeText.setReflectivity(2);
		
		Entity dragon = new Entity(staticModel, new Vector3f(98,0,-170),0,85,0,0.75f);
		Entity dragon2 = new Entity(dragModel, new Vector3f(103,0,-170),0,70,0,0.75f);
		
		Light light = new Light(new Vector3f(100,500,-150), new Vector3f(1,1,1));
		
		Entity sphereEntity = new Entity(cubeTexture, light.getPosition(),0,0,0,0.75f);
		
		Terrain terrain = new Terrain(0,-1,loader, texturePack, blendMap);
		
		Camera camera = new Camera();
		//Light playerLight = new Light(camera.getPosition(), new Vector3f(1,1,1));
		MasterRenderer renderer = new MasterRenderer();
		
		Mouse.setGrabbed(true);
		while(!Display.isCloseRequested() && !Input.getKeyUp(Input.KEY_F4)) {
			Input.Update();	
			camera.updatePosition();
			//playerLight.setPosition(camera.getPosition());
			light.updatePosition(0.1f);
			sphereEntity.setPosition(light.getPosition());
			//dragon.increaseRotation(0, 1, 0);
			//Render
			renderer.processTerrain(terrain);
			renderer.processEntity(dragon);
			renderer.processEntity(dragon2);
			renderer.processEntity(sphereEntity);
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
		}
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}
	
}
