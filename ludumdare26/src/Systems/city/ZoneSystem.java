/**
 * 
 */
package Systems.city;

import helpers.VoidIntervalEntitySystem;

import java.util.Random;


import Managers.ComponentHelper;
import World.World;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.IntervalEntityProcessingSystem;
import com.artemis.systems.VoidEntitySystem;
import com.jameskidd.ludumdare26.EntityFactory;


import components.OnCursorComponent;
import components.SpriteComponent;
import components.city.WorldComponent;
import components.city.ZoneComponent;


/**
 * @author James
 * Responsible for spawning new actors etc
 */
public class ZoneSystem extends IntervalEntityProcessingSystem {
	
	@Mapper
	ComponentMapper<ZoneComponent> zc;
	/**
	 * @param interval
	 */
	public ZoneSystem(float interval) {
		super(Aspect.getAspectForAll(ZoneComponent.class), interval);
		// TODO Auto-generated constructor stub
	}

	Random rand = new Random();
	

	@Override
	protected void begin() {
		Population = 0;
		AvailHousing = 0;
		AvailJobs = 0;
	}

	World world;
	/* (non-Javadoc)
	 * @see com.artemis.systems.IntervalEntityProcessingSystem#process(com.artemis.Entity)
	 */
	@Override
	protected void process(Entity e) {
		if(world == null )world = (World) e.getWorld();
		ZoneComponent zone = zc.get(e);
		WorldComponent worldComponent = ComponentHelper.getWorldComponent();
		switch(zone.zoneType){
			case Residential:
				Population += zone.Population;
				AvailHousing += zone.MaxPop() - zone.Population;
				break;
			case Commercial:
				AvailJobs += zone.MaxEmployees() - zone.Employeed;
				
		}
	
	}
	int Population,AvailHousing,AvailJobs;
	
	@Override
	protected void end() {
		WorldComponent worldComponent = ComponentHelper.getWorldComponent();
		worldComponent.Population = Population;
		worldComponent.AvailHousing = AvailHousing;
		worldComponent.AvailJobs = AvailJobs;
	}
	

}
