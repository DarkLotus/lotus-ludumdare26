/**
 * 
 */
package Systems.city;

import helpers.VoidIntervalEntitySystem;

import java.util.Random;


import Enums.ZoneDensity;
import Managers.ComponentHelper;
import World.World;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.IntervalEntityProcessingSystem;
import com.artemis.systems.VoidEntitySystem;
import com.jameskidd.ludumdare26.EntityFactory;
import com.jameskidd.ludumdare26.Logger;


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
	
	@Mapper
	ComponentMapper<OnCursorComponent> oc;
	/**
	 * @param interval
	 */
	public ZoneSystem(float interval) {
		super(Aspect.getAspectForAll(ZoneComponent.class), interval);
		// TODO Auto-generated constructor stub
	}

	Random rand = new Random();
	
	//temp vars used to calculate totals
	int Population,AvailHousing,AvailJobs;

	@Override
	protected void begin() {
		Population = 0;
		AvailHousing = 0;
		AvailJobs = 0;
	}



	@Override
	protected void process(Entity e) {
		if(oc.has(e))
			return;
		ZoneComponent zone = zc.get(e);
		WorldComponent worldComponent = ComponentHelper.getWorldComponent();
		switch(zone.zoneType){
			case Residential:
				Population += zone.Population;
				AvailHousing += zone.MaxPop() - zone.Population;
				if(zone.Population == zone.MaxPop()){
					if(zone.Density == ZoneDensity.Low && zone.Happiness > 50){
						zone.Density = ZoneDensity.Medium;
						Logger.Log("Residential Evolved to Medium");
					}
				}
				break;
			case Commercial:
				AvailJobs += zone.MaxEmployees() - zone.Employeed;
				if(zone.Employeed == zone.MaxEmployees()){
					if(zone.Density == ZoneDensity.Low && zone.Happiness > 50){
						zone.Density = ZoneDensity.Medium;
						Logger.Log("Commercial Evolved to Medium");	
					}
				}
				break;
			case Industrial:
				AvailJobs += zone.MaxEmployees() - zone.Employeed;
				if(zone.Employeed == zone.MaxEmployees()){
					if(zone.Density == ZoneDensity.Low && zone.Happiness > 50){
						zone.Density = ZoneDensity.Medium;
						Logger.Log("Industrial Evolved to Medium");	
					}
				}
				break;
				
				
		}
	
	}
	
	
	@Override
	protected void end() {
		WorldComponent worldComponent = ComponentHelper.getWorldComponent();
		worldComponent.Population = Population;
		worldComponent.AvailHousing = AvailHousing;
		worldComponent.AvailJobs = AvailJobs;
	}
	

}
