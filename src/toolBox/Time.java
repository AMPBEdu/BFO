package toolBox;

import org.lwjgl.Sys;

public class Time {
	private static long lastFrameTime = getCurrentTime();
	private static float delta;
	
	public static void Update(){
		long currentFrameTime = getCurrentTime();
		delta = (currentFrameTime - lastFrameTime)/1000f;
		lastFrameTime = currentFrameTime;
	}
	private static long getCurrentTime(){
		return Sys.getTime()*1000/Sys.getTimerResolution();
	}
	public static float getDelta(){
		return delta;
	}
}
