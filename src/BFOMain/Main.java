package BFOMain;

import loaders.Loader;
import loaders.OBJLoader;
import loaders.SpriteLoader;
import loaders.SpriteSheet;
import models.RawModel;
import models.TexturedModel;

import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
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
import guiComponents.ButtonMode;

public class Main {
	private static DisplayManager gameDisplay = new DisplayManager("Battle for Osengaurd", 1080, 620, 60);
	private static Loader loader = new Loader();
	private static final SpriteSheet textures = new SpriteSheet("allTextures", "res/Textures/textures.xml");
	private static SpriteLoader textureLoader = new SpriteLoader(textures);
	public static MasterRenderer renderer = new MasterRenderer(loader);
	//Terrain
	private static TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("grassTexture"));
	private static TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("mud"));
	private static TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("pinkFlowers"));
	private static TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("path"));
	private static TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
	private static TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));
	//End of Terrain
	//Entity Models
	private static RawModel dragon = OBJLoader.loadObjModel("dragon", loader);
	private static RawModel sphere = OBJLoader.loadObjModel("sphere", loader);
	private static TexturedModel greenDragModel = new TexturedModel(dragon, new ModelTexture(loader.loadTexture("green")));
	private static TexturedModel redDragModel   = new TexturedModel(dragon, new ModelTexture(loader.loadTexture("red")));
	private static TexturedModel sphereModel  = new TexturedModel(sphere,  new ModelTexture(loader.loadTexture("yellow")));
	private static ModelTexture greenDragTexture = greenDragModel.getTexture();
	private static ModelTexture redDragTexture = redDragModel.getTexture();
	private static ModelTexture sphereTexture = sphereModel.getTexture();
	//End of Entity Models
	//Entities
	private static Terrain terrain = new Terrain(0,-1,loader, texturePack, blendMap);
	private static Light light = new Light(new Vector3f(100,500,-150), new Vector3f(1,1,1));
	private static Camera camera = new Camera();
	private static Entity greenDragon = new Entity(greenDragModel, new Vector3f(98,0,-170),0,85,0,1.5f);
	private static Entity redDragon   = new Entity(redDragModel, new Vector3f(108,0,-170),0,70,0,1.5f);	
	private static Entity sphereEntity = new Entity(sphereModel, light.getPosition(),0,0,0,0.75f);
	//End of Entities
	private static int frames = 0;
	private static double frameCounter = 0;
	
	public static void main(String[] args) {
		try {
			init();
			run();
			} catch (Exception e) {
				e.printStackTrace(System.err);
				Sys.alert(DisplayManager.getGameTitle(), "An error occured and the game will exit.");
				} finally {
					cleanUp();
					}
		}
      
	private static void init() throws Exception {
		setShineValues();
		Mouse.setGrabbed(true);
		}
      
      /**
       * Runs the game (the "main loop")
       */
	private static void run() {
		
		while (!KeyIn.isFinished()) {
			startFrameUpdate();
			if (Display.isCloseRequested()) {
				KeyIn.setFinished(true);
				} else if (Display.isActive()) {
					logic();
					render();
					} else {
						// The window is not in the foreground, so we can allow other stuff to run and infrequently update.
						try {
							Thread.sleep(100);
							} catch (InterruptedException e) {}
						logic();
						if (Display.isVisible() || Display.isDirty()) {
							// Only bother rendering if the window is visible or dirty
							render();
							}
						}
			endFrameUpdate();
			}
		}
	
	private static void cleanUp() {
		// TODO: save anything you want to disk here
		AL.destroy();
		renderer.cleanUp();
		loader.cleanUp();
		SpriteLoader.cleanUp(false);
		gameDisplay.closeDisplay();
		}
	
	private static void logic() {
		KeyIn.updateInput();
		camera.updatePosition();
		
		frameCounter += Time.getDelta();
		frames++;
		if(frameCounter >= 1.0){
			System.out.println(frames);
			frames = 0;
			frameCounter = 0;
			}
		}
	
	private static void render() {
		if(KeyIn.isEscMenu()){
			//TODO: Render Escape Menu
		}
		renderer.processTerrain(terrain);
		renderer.processEntity(greenDragon);
		renderer.processEntity(redDragon);
		renderer.processEntity(sphereEntity);
		renderer.render(light, camera);
		}
	
	private static void startFrameUpdate(){
		Time.Update();
		}
	private static void endFrameUpdate(){
		ButtonMode.Update();
		DisplayManager.updateDisplay();
		Input.Update();
		}
	private static void setShineValues(){
		greenDragTexture.setShineDamper(5);
		greenDragTexture.setReflectivity(0);
		redDragTexture.setShineDamper(10);
		redDragTexture.setReflectivity(2);
		sphereTexture.setShineDamper(10);
		sphereTexture.setReflectivity(2);
	}
	}
