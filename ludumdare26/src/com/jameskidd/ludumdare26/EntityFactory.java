/**
 * 
 */
package com.jameskidd.ludumdare26;

import Enums.ZoneType;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import components.MapComponent;
import components.OnCursorComponent;
import components.PlayerComponent;
import components.SpriteComponent;
import components.UIButtonComponent;
import components.WorldPositionComponent;
import components.city.NPCComponent;
import components.city.PowerDeltaComponent;
import components.city.TrafficComponent;
import components.city.WorldComponent;
import components.city.ZoneComponent;

/**
 * @author James
 *
 */
public class EntityFactory {



	public static Entity createMap(World world, String name)
	{
		Entity entity = world.createEntity();
		entity.addComponent(new MapComponent(name));
		return entity;
	}
	public static Entity createButton(World world, String label,Vector2 loc, ChangeListener handler)
	{
		Entity entity = world.createEntity();
		entity.addComponent(new UIButtonComponent(label,loc,handler));
		return entity;
	}
	/*public static Entity createObject(World world, String name)
	{
		Entity entity = world.createEntity();
		entity.addComponent(new WorldPositionComponent(5*64,5*64));
		entity.addComponent(new SpriteComponent(5));
		world.getManager(GroupManager.class).add(entity, "persist");
		return entity;
	}*/
	public static Entity createPlayer(World world, String name)
	{
		Entity entity = world.createEntity();
		//entity.addComponent(new WorldPositionComponent(5*64,5*64));
		//entity.addComponent(new VelocityComponent());
		entity.addComponent(new PlayerComponent(0,0,10000));
		//entity.addComponent(new SpriteComponent(5));
		world.getManager(GroupManager.class).add(entity, "persist");
		world.getManager(GroupManager.class).add(entity, "Player");
		return entity;
	}
	/**
	 * @param _world
	 * @return
	 */

	public static Entity createResidential(World world) {
		Entity entity = world.createEntity();
		//entity.addComponent(new WorldPositionComponent(5*64,5*64));
		//entity.addComponent(new VelocityComponent());
		entity.addComponent(new WorldPositionComponent());
		entity.addComponent(new OnCursorComponent());
		entity.addComponent(new SpriteComponent("Residential"));
		entity.addComponent(new ZoneComponent(ZoneType.Residential));
		entity.addComponent(new PowerDeltaComponent());
		//world.getManager(GroupManager.class).add(entity, "persist");
		world.getManager(GroupManager.class).add(entity, "Residential");
		return entity;
	}

	public static Entity createComercial(World world) {
		Entity entity = world.createEntity();
		entity.addComponent(new WorldPositionComponent());
		entity.addComponent(new OnCursorComponent());
		entity.addComponent(new SpriteComponent("Commercial"));
		entity.addComponent(new ZoneComponent(ZoneType.Commercial));
		entity.addComponent(new PowerDeltaComponent());
		//world.getManager(GroupManager.class).add(entity, "persist");
		world.getManager(GroupManager.class).add(entity, "Commercial");
		return entity;
	}
	public static Entity createIndustrial(World world) {
		Entity entity = world.createEntity();
		entity.addComponent(new WorldPositionComponent());
		entity.addComponent(new OnCursorComponent());
		entity.addComponent(new SpriteComponent("Industrial"));
		entity.addComponent(new ZoneComponent(ZoneType.Industrial));
		entity.addComponent(new PowerDeltaComponent());
		//world.getManager(GroupManager.class).add(entity, "persist");
		world.getManager(GroupManager.class).add(entity, "Industrial");
		return entity;
	}
	public static Entity createPowerPlant(World world) {
		Entity entity = world.createEntity();
		entity.addComponent(new WorldPositionComponent());
		entity.addComponent(new OnCursorComponent());
		entity.addComponent(new SpriteComponent("Power Plant"));
		//entity.addComponent(new IndustrialComponent());
		entity.addComponent(new PowerDeltaComponent());
		//world.getManager(GroupManager.class).add(entity, "persist");
		world.getManager(GroupManager.class).add(entity, "Power");
		entity.addComponent(new ZoneComponent(ZoneType.PowerPlant));
		return entity;
	}
	/**
	 * @param world
	 * @return
	 */
	public static Entity createRoad(World world) {
		Entity entity = world.createEntity();
		entity.addComponent(new WorldPositionComponent());
		entity.addComponent(new OnCursorComponent());
		entity.addComponent(new SpriteComponent("Road"));
		entity.addComponent(new TrafficComponent());
		entity.addComponent(new ZoneComponent(ZoneType.Road));
		//world.getManager(GroupManager.class).add(entity, "persist");
		world.getManager(GroupManager.class).add(entity, "Road");
		return entity;
	}
	/**
	 * @param world
	 * @return
	 */
	public static Entity createWorld(World world) {
		Entity entity = world.createEntity();
		entity.addComponent(new WorldComponent());
		world.getManager(GroupManager.class).add(entity, "World");
		return entity;
	}
	/**
	 * @return
	 */
	public static Entity createNPC(World world) {
		Entity entity = world.createEntity();
		entity.addComponent(new NPCComponent());
		world.getManager(GroupManager.class).add(entity, "NPC");
		return entity;
	}

}
