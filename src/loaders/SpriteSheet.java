package loaders;

public class SpriteSheet {
	public String sheetLocation;
	public String xmlLocation;
	public SpriteSheet(String sheetLocation, String xmlLocation) {
		this.sheetLocation = sheetLocation;
		this.xmlLocation = xmlLocation;
	}
	public String getSheetLocation() {
		return sheetLocation;
	}
	public void setSheetLocation(String sheetLocation) {
		this.sheetLocation = sheetLocation;
	}
	public String getXmlLocation() {
		return xmlLocation;
	}
	public void setXmlLocation(String xmlLocation) {
		this.xmlLocation = xmlLocation;
	}
	
}
