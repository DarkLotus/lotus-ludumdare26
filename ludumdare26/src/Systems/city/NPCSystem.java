/**
 * 
 */
package Systems.city;

import java.util.Random;


import Enums.ZoneType;
import Managers.ComponentHelper;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.IntervalEntityProcessingSystem;
import com.artemis.utils.ImmutableBag;
import com.jameskidd.ludumdare26.Logger;


import components.OnCursorComponent;
import components.city.NPCComponent;

import components.city.ZoneComponent;

/**
 * @author James
 *
 */
public class NPCSystem extends IntervalEntityProcessingSystem {
	@Mapper
	ComponentMapper<NPCComponent> npcC;


	
	@Mapper
	ComponentMapper<ZoneComponent> zc;
	
	Random _rand = new Random();
	/**
	 * @param aspect
	 * @param interval
	 */
	public NPCSystem(float interval) {
		super(Aspect.getAspectForAll(NPCComponent.class), interval);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void begin() {

	}

	
	@Override
	protected void process(Entity e) {
		NPCComponent npc = npcC.get(e);
		if(npc.ResidenceID == 0 || !zc.has(e.getWorld().getEntity(npc.ResidenceID) )){
			// if no residentail entity with that id, assign one.
			
			assignResidence(e,npc);
		}
		if(npc.EmployerID == 0 || !zc.has(e.getWorld().getEntity(npc.EmployerID) )){
			// if no residentail entity with that id, assign one.
			//Logger.Log("Assigning Job to #" + e.getId());
			assignJob(e,npc);
		}
			
		
	
		
	}

	/**
	 * @param e
	 * @param npc
	 */
	private void assignJob(Entity e, NPCComponent npc) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param e
	 * @param npc
	 */
	private void assignResidence(Entity e, NPCComponent npc) {
		ImmutableBag<Entity> entities = ComponentHelper.GetZonesInRange(e, ZoneType.Residential, 0);
		for (int i = 0; i < entities.size(); i++) {
			Entity resEntity = entities.get(i);
			ZoneComponent zoneComponent = zc.get(resEntity);
			if(zoneComponent.Population < zoneComponent.MaxPop()){
				npc.ResidenceID = resEntity.getId();
				zoneComponent.Population++;
				Logger.Log("Assigning Residence:" + resEntity.getId()+ " to #" + e.getId());
				return;
			}
			
		}
	}

}
