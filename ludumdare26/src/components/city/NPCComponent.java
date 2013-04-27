/**
 * 
 */
package components.city;

import Enums.NPCState;
import Enums.ZoneDensity;

import com.artemis.Component;
import com.jameskidd.ludumdare26.Logger;

/**
 * @author James
 * 
 */
public class NPCComponent extends Component {
	public int Age;
	public int ResidenceID;
	public int EmployerID;
	
	public int Happiness;
	public NPCState CurrentState;
	

	public NPCComponent()
	{
		Logger.Log("Creating NPC");
		
	}
	
	/*@Override
	public String toString(){
		//return "P:" + Population + "H:" + Happiness;
	}*/
}
