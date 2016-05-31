package com.lpoo.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.math.Plane;
import com.lpoo.game.PlaneRacing;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = PlaneRacing.WIDTH;
		config.height = PlaneRacing.HEIGHT;
		config.title = PlaneRacing.TITLE;
		new LwjglApplication(new PlaneRacing(), config);
	}
}
