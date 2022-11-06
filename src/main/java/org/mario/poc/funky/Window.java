/**
 * 
 */
package org.mario.poc.funky;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

/**
 * @author arixion
 *
 */
public class Window {

	private static Window INSTANCE = null;

	private final int width, height;
	private final String title;

	private long glfwWindow;

	/**
	 * 
	 */
	private Window() {
		this.height = 1080;
		this.width = 1920;
		this.title = "Funky Mario Engine";
	}

	/**
	 * 
	 * @return
	 */
	// TODO - Check if a thread safety is required.
	public static Window getInstance() {
		if (null == INSTANCE) {
			synchronized (Window.class) {
				if (null == INSTANCE) {
					INSTANCE = new Window();
				}
			}
		}
		return INSTANCE;
	}

	/**
	 * 
	 */
	public void run() {
		System.out.println("Hello From LWJGL " + Version.getVersion() + " !");

		init();
		loop();

		glfwFreeCallbacks(glfwWindow);
		glfwDestroyWindow(glfwWindow);

		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}

	/**
	 * 
	 */
	public void init() {
		GLFWErrorCallback.createPrint(System.err).set();

		// Initialize GLFW
		if (!glfwInit()) {
			throw new IllegalStateException("Unable to initialize GLFW !");
		}

		glfwDefaultWindowHints();
		glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
		glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);
		glfwWindowHint(GLFW.GLFW_MAXIMIZED, GLFW.GLFW_TRUE);

		glfwWindow = glfwCreateWindow(this.width, this.height, this.title, MemoryUtil.NULL, MemoryUtil.NULL);

		if (glfwWindow == MemoryUtil.NULL) {
			throw new IllegalStateException("Unable to initialize the GLFW Window");
		}

		glfwMakeContextCurrent(glfwWindow);

		// Enable v-sync
		glfwSwapInterval(1);

		glfwShowWindow(glfwWindow);

		GL.createCapabilities();
	}

	/**
	 * 
	 */
	public void loop() {
		while (!glfwWindowShouldClose(glfwWindow)) {
			glfwPollEvents();
			glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
			glClear(GL11.GL_COLOR_BUFFER_BIT);
			glfwSwapBuffers(glfwWindow);
		}
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
}
