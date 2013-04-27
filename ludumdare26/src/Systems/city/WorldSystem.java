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


import components.OnCursorComponent;


/**
 * @author James
 * Responsible for spawning new actors etc
 */
public class WorldSystem extends VoidIntervalEntitySystem {
	
	/**
	 * @param interval
	 */
	public WorldSystem(float interval) {
		super(interval);
		// TODO Auto-generated constructor stub
	}

	Random rand = new Random();
	

	@Override
	protected void begin() {

	}
	
	@Override
	protected void processSystem() {
		// TODO Auto-generated method stub
		
	}

}
