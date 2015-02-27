package loaders;

import org.lwjgl.util.vector.Vector2f;

public final class Sprite {
	private final String name;
	private final int spriteSheet;
	private final int x;
	private final int y;
	private final int w;
	private final int h;
	private final Vector2f size;

	public Sprite(String name, int spriteSheet, int x, int y, int w, int h) {
		this.name = name;
		this.spriteSheet = spriteSheet;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		size = new Vector2f(getWidth(), getHeight());
	}

	public void render() {
		// render code
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Sprite sprite = (Sprite) o;
		if (h != sprite.h) {
			return false;
		}
		if (w != sprite.w) {
			return false;
		}
		if (x != sprite.x) {
			return false;
		}
		if (y != sprite.y) {
			return false;
		}
		if (name != null ? !name.equals(sprite.name) : sprite.name != null) {
			return false;
		}
		return true;
	}
	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + x;
		result = 31 * result + y;
		result = 31 * result + w;
		result = 31 * result + h;
		return result;
	}
	@Override
	public String toString() {
		return "Sprite{" + "name='" + name + '\'' + ", x=" + x + ", y=" + y
				+ ", w=" + w + ", h=" + h + '}';
	}
	public String getName() {
		return name;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getWidth() {
		return w;
	}
	public int getHeight() {
		return h;
	}
	public Vector2f getSize() {
		return size;
	}
}
