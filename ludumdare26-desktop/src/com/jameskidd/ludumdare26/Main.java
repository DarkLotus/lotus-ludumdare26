package com.jameskidd.ludumdare26;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "MinimaCity";
		cfg.useGL20 = true;
		cfg.width = 1024;
		cfg.height = 600;
		
		new LwjglApplication(new GameBase(), cfg);
	}
}
