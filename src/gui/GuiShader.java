package gui;

import org.lwjgl.util.vector.Matrix4f;

import shaders.ShaderProgram;

public class GuiShader extends ShaderProgram{
	
	private static final String VERTEX_FILE = "src/gui/guiVertexShader.vp";
	private static final String FRAGMENT_FILE = "src/gui/guiFragmentShader.fp";
	
	private int location_transformationMatrix;
	private int location_alpha;
	
	public GuiShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}
	
	public void loadTransformation(Matrix4f matrix){
		super.loadMatrix(location_transformationMatrix, matrix);
	}
	
	public void loadAlpha(float alpha){
		super.loadFloat(location_alpha, alpha);
	}

	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_alpha = super.getUniformLocation("alpha");
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}

}
