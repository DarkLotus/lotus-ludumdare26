/**
 * 
 */
package Systems.city;

import java.util.Random;


import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.IntervalEntityProcessingSystem;
import com.jameskidd.ludumdare26.Logger;


import components.OnCursorComponent;
import components.city.ZoneComponent;

/**
 * @author James
 *
 */
public class PowerSystem extends IntervalEntityProcessingSystem {
	@Mapper
	ComponentMapper<ZoneComponent> rc;

	@Mapper
	ComponentMapper<OnCursorComponent> oc;
	Random rand = new Random();
	/**
	 * @param aspect
	 * @param interval
	 */
	public PowerSystem(float interval) {
		super(Aspect.getAspectForAll(ZoneComponent.class), interval);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void begin() {

	}



	/* (non-Javadoc)
	 * @see com.artemis.systems.IntervalEntityProcessingSystem#process(com.artemis.Entity)
	 */
	@Override
	protected void process(Entity e) {
	
	}

}
