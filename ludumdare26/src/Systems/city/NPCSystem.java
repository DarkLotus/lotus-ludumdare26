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
		ImmutableBag<Entity> entities = null;
		if(_rand.nextBoolean())
		entities = ComponentHelper.GetZonesInRange(e, ZoneType.Commercial, 0);
		else
		entities = ComponentHelper.GetZonesInRange(e, ZoneType.Industrial, 0);
		ZoneComponent bestComponent = null;
		Entity bestEntity = null;
		
		for (int i = 0; i < entities.size(); i++) {
			Entity resEntity = entities.get(i);
			ZoneComponent zoneComponent = zc.get(resEntity);
			if(zoneComponent.Employeed < zoneComponent.MaxEmployees()){
					if(bestComponent == null || bestComponent.Employeed > zoneComponent.Employeed){
					bestComponent = zoneComponent;
					bestEntity = resEntity;}
				}
			}
		if(bestComponent == null || bestEntity == null)
			return;
		npc.EmployerID = bestEntity.getId();
		bestComponent.Employeed++;
		Logger.Log("Assigning Job:" + bestEntity.getId()+ " to #" + e.getId());
		return;
	}

	/**
	 * @param e
	 * @param npc
	 */
	private void assignResidence(Entity e, NPCComponent npc) {
		ImmutableBag<Entity> entities = ComponentHelper.GetZonesInRange(e, ZoneType.Residential, 0);
		ZoneComponent bestComponent = null;
		Entity bestEntity = null;
		for (int i = 0; i < entities.size(); i++) {
			Entity resEntity = entities.get(i);
			ZoneComponent zoneComponent = zc.get(resEntity);
			if(zoneComponent.Population < zoneComponent.MaxPop()){
				if(bestComponent == null || bestComponent.Population > zoneComponent.Population){
				bestComponent = zoneComponent;
				bestEntity = resEntity;}
			}
			
		}
		if(bestComponent == null || bestEntity == null)
			return;
		npc.ResidenceID = bestEntity.getId();
		bestComponent.Population++;
		Logger.Log("Assigning Residence:" + bestEntity.getId()+ " to #" + e.getId());
		return;
	}

}
