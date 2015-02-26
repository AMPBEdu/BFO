package guiComponents;

public class ButtonMode {
	public static final int normalMode   = 0;
	public static final int hoverMode    = 1;
	public static final int clickMode    = 2;
	public static final int disabledMode = 3;
	private static int buttonMode = 0;
	private static int lastButtonMode = buttonMode;
	
	public static void Update(){
		lastButtonMode = buttonMode;
	}
	public static void setMode(int mode){
		buttonMode = mode;
	}
	public static int getMode(){
		return buttonMode;
	}
	public static boolean hasChanged(){
		if(buttonMode != lastButtonMode){
			return true;
		}else{
			return false;
		}
	}
}
