package loaders;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.lwjgl.opengl.GL11.*;

public class SpriteLoader {
	private static Loader loader = new Loader();

	private static int spriteSheet;
	private static Map<String, Integer> spriteSheetMap = new HashMap<String, Integer>();
	private static Set<String> keys;
	private static final Map<String, Sprite> spriteMap = new HashMap<String, Sprite>();
	private static String SPRITESHEET_IMAGE_LOCATION;
	private static String SPRITESHEET_XML_LOCATION;

	public SpriteLoader(String imgLocation, String xmlLocation) {
		SPRITESHEET_IMAGE_LOCATION = imgLocation;
		SPRITESHEET_XML_LOCATION = xmlLocation;
		setUpSpriteSheets();
		spriteSheetMap.put(SPRITESHEET_IMAGE_LOCATION, spriteSheet);
	}

	private static void setUpSpriteSheets() {
		spriteSheet = loader.loadTexture(SPRITESHEET_IMAGE_LOCATION);
		SAXBuilder builder = new SAXBuilder();
		try {
			Document document = builder
					.build(new File(SPRITESHEET_XML_LOCATION));
			Element root = document.getRootElement();
			for (Object spriteObject : root.getChildren()) {
				Element spriteElement = (Element) spriteObject;
				String name = spriteElement.getAttributeValue("n");
				int x = spriteElement.getAttribute("x").getIntValue();
				int y = spriteElement.getAttribute("y").getIntValue();
				int w = spriteElement.getAttribute("w").getIntValue();
				int h = spriteElement.getAttribute("h").getIntValue();
				Sprite sprite = new Sprite(name, x, y, w, h);
				spriteMap.put(sprite.getName(), sprite);
			}
		} catch (JDOMException e) {
			e.printStackTrace();
			cleanUp(true);
		} catch (IOException e) {
			e.printStackTrace();
			cleanUp(true);
		}
	}
	
	public static void cleanUp(boolean asCrash) {
		keys = spriteSheetMap.keySet();
		for (String key : keys){
			glDeleteTextures(spriteSheetMap.get(key));
		}
		System.exit(asCrash ? 1 : 0);
	}
	
	public int getSpriteSheet(){
		return spriteSheet;
	}
	
	public Sprite getSprite(String spriteName){
		return spriteMap.get(spriteName);
	}
}
