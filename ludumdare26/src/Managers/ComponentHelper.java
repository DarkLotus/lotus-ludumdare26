/**
 * 
 */
package Managers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.lang.reflect.Field;

import helpers.Persistant;
import helpers.Save;
import helpers.SaveObject;


import Enums.ZoneType;
import World.World;

import com.artemis.Component;
import com.artemis.Entity;

import com.artemis.managers.GroupManager;
import com.artemis.utils.Bag;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import components.PlayerComponent;
import components.city.WorldComponent;


/**
 * @author James
 *
 */
public class ComponentHelper  {

	/**
	 * @param e
	 * @param com
	 * @param Range 
	 * @return 
	 */
	public static ImmutableBag<Entity> GetZonesInRange(Entity e, ZoneType Ttype, int Range) {
		// TODO Auto-generated method stub
		switch(Ttype){
			case Residential:
				return e.getWorld().getManager(GroupManager.class).getEntities("Residential");
			case Commercial:
				return e.getWorld().getManager(GroupManager.class).getEntities("Commercial");
			case Industrial:
				return e.getWorld().getManager(GroupManager.class).getEntities("Industrial");
			default:
				return null;
		}
		
		//return new Entity[0];
	}

	/**
	 * @return
	 */
	public static PlayerComponent GetPlayerComponent() {
		return World.Instance.getManager(GroupManager.class).getEntities("Player").get(0).getComponent(PlayerComponent.class);
	}

	/**
	 * @param world
	 * @return
	 */
	public static WorldComponent getWorldComponent() {
		return World.Instance.getManager(GroupManager.class).getEntities("World").get(0).getComponent(WorldComponent.class);
	}
	
	
}

