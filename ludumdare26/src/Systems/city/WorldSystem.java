/**
 * 
 */
package Systems.city;

import helpers.VoidIntervalEntitySystem;

import java.util.Random;


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


/**
 * @author James
 * Responsible for spawning new actors etc
 */
public class WorldSystem extends IntervalEntityProcessingSystem {
	
	@Mapper
	ComponentMapper<WorldComponent> wc;
	/**
	 * @param interval
	 */
	public WorldSystem(float interval) {
		super(Aspect.getAspectForAll(WorldComponent.class), interval);
		// TODO Auto-generated constructor stub
	}

	Random rand = new Random();
	

	@Override
	protected void begin() {

	}


	/* (non-Javadoc)
	 * @see com.artemis.systems.IntervalEntityProcessingSystem#process(com.artemis.Entity)
	 */
	@Override
	protected void process(Entity e) {
		WorldComponent worldComponent = wc.get(e);
		worldComponent.Day++;
		if(worldComponent.Day > 30) {
			worldComponent.Day = 1;
			worldComponent.Month++;
		}
		if(worldComponent.Month > 12){
			worldComponent.Month = 1;
			worldComponent.Year++;
		}
		if(worldComponent.Happiness >= 50 && worldComponent.AvailHousing > 0 && worldComponent.AvailJobs > 0)
			e.getWorld().addEntity(EntityFactory.createNPC(e.getWorld()));	
	}
	
	

}
