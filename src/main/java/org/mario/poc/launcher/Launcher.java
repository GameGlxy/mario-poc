/**
 * 
 */
package org.mario.poc.launcher;

import org.mario.poc.funky.Window;

/**
 * @author arixion
 *
 */
public class Launcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Window window = Window.getInstance();
		window.run();
	}

}
