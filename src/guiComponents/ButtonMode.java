package guiComponents;

public class ButtonMode {
	public static final String normalMode   = "Normal";
	public static final String hoverMode    = "Hovered";
	public static final String clickMode    = "Clicked";
	public static final String disabledMode = "Disabled";
	private static String buttonMode = normalMode;
	private static String lastButtonMode = buttonMode;
	
	public ButtonMode(){}
	public static void Update(){
		lastButtonMode = buttonMode;
	}
	public void setMode(String mode){
		buttonMode = mode;
	}
	public String getMode(){
		return buttonMode;
	}
	public boolean hasChanged(){
		if(buttonMode != lastButtonMode){
			return true;
		}else{
			return false;
		}
	}
}
