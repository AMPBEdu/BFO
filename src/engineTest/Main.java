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
import toolBox.KeyIn;
import toolBox.Time;
import entities.Camera;
import entities.Entity;
import entities.Light;
import gui.ButtonMode;

public class Main {
	
	public static void main(String[] args){
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		//******************Terrain Stuff*******************
				TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("grassTexture"));
				TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("mud"));
				TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("pinkFlowers"));
				TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("path"));
				TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
				TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));
		//*******************Model Stuff********************	
		RawModel dragon = OBJLoader.loadObjModel("dragon", loader);
		RawModel sphere = OBJLoader.loadObjModel("sphere", loader);
		TexturedModel greenDragModel = new TexturedModel(dragon, new ModelTexture(loader.loadTexture("green")));
		TexturedModel redDragModel   = new TexturedModel(dragon, new ModelTexture(loader.loadTexture("red")));
		TexturedModel sphereModel  = new TexturedModel(sphere,  new ModelTexture(loader.loadTexture("yellow")));
		ModelTexture greenDragTexture = greenDragModel.getTexture();
		ModelTexture redDragTexture = redDragModel.getTexture();
		ModelTexture sphereTexture = sphereModel.getTexture();
		
		greenDragTexture.setShineDamper(5);
		greenDragTexture.setReflectivity(0);
		redDragTexture.setShineDamper(10);
		redDragTexture.setReflectivity(2);
		sphereTexture.setShineDamper(10);
		sphereTexture.setReflectivity(2);
		
		Entity greenDragon = new Entity(greenDragModel, new Vector3f(98,0,-170),0,85,0,1.5f);
		Entity redDragon   = new Entity(redDragModel, new Vector3f(108,0,-170),0,70,0,1.5f);
		
		Light light = new Light(new Vector3f(100,500,-150), new Vector3f(1,1,1));
		
		Entity sphereEntity = new Entity(sphereModel, light.getPosition(),0,0,0,0.75f);
		
		Terrain terrain = new Terrain(0,-1,loader, texturePack, blendMap);
		
		Camera camera = new Camera();
		//Light playerLight = new Light(camera.getPosition(), new Vector3f(1,1,1));
		MasterRenderer renderer = new MasterRenderer();
		
		Mouse.setGrabbed(true);
		while(!Display.isCloseRequested() && !Input.getKeyUp(Input.KEY_F4)) {
			startFrameUpdate();
			camera.updatePosition();
			light.updatePosition(0.1f);
			sphereEntity.setPosition(light.getPosition());
			//dragon.increaseRotation(0, 1, 0);
			//Render
			renderer.processTerrain(terrain);
			renderer.processEntity(greenDragon);
			renderer.processEntity(redDragon);
			renderer.processEntity(sphereEntity);
			renderer.render(light, camera);
			endFrameUpdate();
		}
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}
	private static void init(){
		
	}
	private static void cleanup(){
		
	}
	private static void startFrameUpdate(){
		Time.Update();
		KeyIn.updateInput();
	}
	private static void endFrameUpdate(){
		ButtonMode.Update();
		DisplayManager.updateDisplay();
		Input.Update();
	}
	
}
