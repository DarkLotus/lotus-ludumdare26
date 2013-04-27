/**
 * 
 */
package Systems.city;

import java.util.Random;


import Managers.ComponentHelper;
import World.World;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.IntervalEntityProcessingSystem;
import com.jameskidd.ludumdare26.Logger;


import components.LabelComponent;
import components.OnCursorComponent;
import components.city.NPCComponent;
import components.city.ZoneComponent;

/**
 * @author James
 *
 */
public class UIUpdateSystem extends IntervalEntityProcessingSystem {
	@Mapper
	ComponentMapper<LabelComponent> lc;

	
	Random _rand = new Random();
	/**
	 * @param aspect
	 * @param interval
	 */
	public UIUpdateSystem(float interval) {
		super(Aspect.getAspectForAll(LabelComponent.class), interval);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void begin() {

	}

	
	@Override
	protected void process(Entity e) {
		LabelComponent l = lc.get(e);
		
		switch(l.ElementName){
			case Money:
				l.LabelString =  "$" + ComponentHelper.GetPlayerComponent().Money;
				break;
			case Date:
				l.LabelString = "Date: " + ComponentHelper.getWorldComponent().Date();
				break;
			case Population:
				l.LabelString = "Pop: " + ComponentHelper.getWorldComponent().TotalPopulation();
			default:
				break;
		}
		//Logger.Log(""+e.getId() + l.ElementName + " " + l.LabelString);
	
		
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
		// TODO Auto-generated method stub
		
	}

}
