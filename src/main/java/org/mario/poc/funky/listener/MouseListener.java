/**
 * 
 */
package org.mario.poc.funky.listener;

/**
 * @author arixion
 *
 */
public class MouseListener {

	private static MouseListener INSTANCE;

	/**
	 * 
	 */
	private MouseListener() {
	}

	/**
	 * 
	 * @return
	 */
	public static MouseListener getInstance() {
		if (null == INSTANCE) {
			synchronized (MouseListener.class) {
				if (null == INSTANCE) {
					INSTANCE = new MouseListener();
				}
			}
		}
		return INSTANCE;
	}

}
