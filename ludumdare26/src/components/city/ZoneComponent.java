/**
 * 
 */
package components.city;

import Enums.ZoneDensity;
import Enums.ZoneType;

import com.artemis.Component;

/**
 * @author James
 * Stores player data, Score,Lives,Money etc
 */
public class ZoneComponent extends Component {
	public ZoneType zoneType;
	
	public ZoneDensity Density = ZoneDensity.Low;
	public int Population = 0;
	
	//Max pop is 100 * the zones density
	public int MaxPop(){
		return 100*(Density.ordinal()+1);
	}
	
	public int Employeed = 0;
	public int MaxEmployees(){
		return 25*(Density.ordinal()+1*2);
	}

	//Happiness based off comercial districts in range? - industrial zones + parks?
	public int Happiness = 50;

	//public int Employeed = 0;


	public ZoneComponent(ZoneType type)
	{
		zoneType = type;
	}
	
	@Override
	public String toString(){
		switch(zoneType){
			case Residential:
				return "P:" + Population + " H:" + Happiness+ " D:" + Density;
			case Commercial:
				return "E:" + Employeed + " D:" + Density;
			case Industrial:
				return "E:" + Employeed + " D:" + Density;
				default:
					return "";
		}
		
	}
}
