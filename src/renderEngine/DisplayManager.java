package renderEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

import toolBox.Time;

public class DisplayManager {
	private static String windowTitle = "Battle for Osengaurd!";
	private static int WIDTH = 1080;
	private static int HEIGHT = 620;
	private static int FPS_CAP = 60;
	
	public DisplayManager(String title, int width, int height, int fps){
		windowTitle = title;
		WIDTH = width;
		HEIGHT = height;
		FPS_CAP = fps;
		createDisplay();
	}
	
	public static void createDisplay(){
		
		ContextAttribs attribs = new ContextAttribs(3,2)
		.withForwardCompatible(true)
		.withProfileCore(true);
		
		try {
		Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
		Display.create(new PixelFormat(), attribs);
		Display.setTitle(windowTitle);
		
		} catch (LWJGLException e){
			e.printStackTrace();
		}
		
		GL11.glViewport(0,0, WIDTH, HEIGHT);
		
	}
	
	public static void updateDisplay(){
		Display.sync(FPS_CAP);
		Display.update();
	}
	
	public static void closeDisplay(){
		Display.destroy();
	}

	public static int getWidth() {
		return WIDTH;
	}
	
	public static int getHeight() {
		return HEIGHT;
	}

	public static String getGameTitle() {
		return windowTitle;
	}
}
