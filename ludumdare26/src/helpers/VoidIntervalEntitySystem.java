package helpers;



import com.artemis.Aspect;
import com.artemis.EntitySystem;
import com.artemis.systems.VoidEntitySystem;


/**
 * A system that processes entities at a interval in milliseconds.
 * A typical usage would be a collision system or physics system.
 * 
 * @author Arni Arent
 *
 */
public abstract class VoidIntervalEntitySystem extends VoidEntitySystem {
	private float acc;
	private float interval;

	public VoidIntervalEntitySystem(float interval) {
		super();
		this.interval = interval;
	}

	@Override
	protected boolean checkProcessing() {
		acc += world.getDelta();
		if(acc >= interval) {
			acc -= interval;
			return true;
		}
		return false;
	}

}
