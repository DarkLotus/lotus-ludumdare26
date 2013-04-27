/**
 * 
 */
package components.city;

import com.artemis.Component;

/**
 * @author James
 *
 */
public class WorldComponent extends Component {

	public int CitizensToArrive = 20;
	
	public int Year,Month,Day;
	
	public WorldComponent(){
		Year = 2000;
		Month = 0;
		Day = 0;
	}
	
	
	
	public String Date() {
		return Day + "/" + Month + "/" + Year;
	}
	
}
