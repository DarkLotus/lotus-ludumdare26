/**
 * 
 */
package Systems;

import java.util.HashMap;
import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.jameskidd.ludumdare26.GameBase;



import components.LabelComponent;
import components.UIButtonComponent;
import components.WorldPositionComponent;


/**
 * @author James
 *
 */
public class UIRenderSystem extends EntityProcessingSystem {


	@Mapper
	ComponentMapper<UIButtonComponent> bc;
	@Mapper
	ComponentMapper<LabelComponent> lc;

	Stage _stage;
	Table _table;

	private Skin _skin;
	public UIRenderSystem() {
		super(Aspect.getAspectForOne(UIButtonComponent.class,LabelComponent.class));

		this._stage = new Stage();
		GameBase.addInputHandler(this._stage);
		this._uiElementsCache = new HashMap<Integer,Actor>();
		this._skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		this._skin.getAtlas().getTextures().iterator().next().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		this._table = new Table(this._skin);
		this._table.setFillParent(true);
		this._table.top();
		this._table.left();
		this._stage.addActor(this._table);
		// take a stage instead of a camera, handle objects as they are but add to a stage instead.
	}

	@Override
	protected void begin() {
		// batch.setProjectionMatrix(_camera.combined);
		// batch.begin();
	}


	HashMap<Integer, Actor> _uiElementsCache;
	/* (non-Javadoc)
	 * @see com.artemis.systems.EntityProcessingSystem#process(com.artemis.Entity)
	 */
	@Override
	protected void process(Entity e) {
		//Draw residential overlay info

		if(this.bc.has(e))
		{
			UIButtonComponent b = this.bc.getSafe(e);
			if(!this._uiElementsCache.containsKey(e.getId())){
				TextButton button = new TextButton(b.LabelString, this._skin);
				if(b.Location != null){
					button.setX(b.Location.x);
					button.setY(b.Location.y);
				}
				if(b.Listener != null) {
					button.addListener(b.Listener);
				}
				this._uiElementsCache.put(e.getId(), button);
				this._table.add(button);
			}
			//_uiElementsCache.get(e.getId()).draw(batch, 1f);
		}
		
		if(this.lc.has(e))
		{
			LabelComponent b = this.lc.getSafe(e);
			if(!this._uiElementsCache.containsKey(e.getId())){
				Label button = new Label(b.LabelString, _skin);
				if(b.Location != null){
					button.setX(b.Location.x);
					button.setY(b.Location.y);
				}
				
				
				this._uiElementsCache.put(e.getId(), button);
				this._table.add(button);
			}
			Label label = (Label) _uiElementsCache.get(e.getId());
			label.setText(b.LabelString);
		}
		

	}
	@Override
	protected void end() {
		this._stage.act();
		this._stage.draw();
	}



}
