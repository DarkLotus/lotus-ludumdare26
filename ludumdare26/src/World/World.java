/**
 * 
 */
package World;

import Enums.UIElementName;
import Managers.ComponentHelper;
import Managers.PersistenceManager;

import Systems.BuildSystem;
import Systems.KeyboardCameraMoveSystem;
import Systems.MapRenderSystem;
import Systems.MouseZoomSystem;
import Systems.MovementSystem;
import Systems.SpriteRenderSystem;
import Systems.UIRenderSystem;

import Systems.city.NPCSystem;
import Systems.city.UIUpdateSystem;
import Systems.city.ZoneSystem;

import Systems.city.WorldSystem;

import com.artemis.Entity;
import com.artemis.managers.GroupManager;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.jameskidd.ludumdare26.EntityFactory;
import com.jameskidd.ludumdare26.Logger;
import components.LabelComponent;
import components.OnCursorComponent;
import components.SpriteComponent;
import components.WorldPositionComponent;
import components.city.TrafficComponent;


/**
 * @author James
 *
 */
public class World extends com.artemis.World {
	protected static final int ZONEPRICE = 100;
	protected static final int POWERPLANTPRICE = 5000;
	protected static final int ROADPRICE = 50;
	public static World Instance;
	OrthographicCamera _camera;
	OrthographicCamera _uiCamera;

	public World(){
		super();
		Instance = this;
		//_guiGameGUI = new InGameGUI(_world);
		this._camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this._uiCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		//move the ui camera so 0,0 is at the corner, not centre.
		this._uiCamera.position.add(Gdx.graphics.getWidth()/2, (Gdx.graphics.getHeight()/2), 0);

		//move ingame camera, should be set to centre on map TODO
		this._camera.position.add(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);

		setSystem(new MapRenderSystem(this._camera));
		setSystem(new SpriteRenderSystem(this._camera));
		setSystem(new MouseZoomSystem(this._camera));
		setSystem(new KeyboardCameraMoveSystem(this._camera));

		setSystem(new BuildSystem(this._camera));
		//setSystem(new KeyboardPlayerControllerInputSystem(_camera));//Platformer controller
		setSystem(new MovementSystem(0.0f)); //todo fix speed?

		//
		setSystem(new UIRenderSystem());
		//_world.setSystem(new AISystem(1));
		float tickRate = 0.016f;
		
		setSystem(new WorldSystem(tickRate*24));
		setSystem(new NPCSystem(tickRate));
		setSystem(new ZoneSystem(tickRate));
		setSystem(new UIUpdateSystem(tickRate*24));
		//setSystem(new ResidentialSystem(1f));
		setupSomeButtons();
		setUpsomeLabels();
		
		setManager(new GroupManager());
		initialize();


		addEntity(EntityFactory.createMap(this, "world.tmx"));
		addEntity(EntityFactory.createPlayer(this, "lotus"));
		addEntity(EntityFactory.createWorld(this));
		//_world.addEntity(EntityFactory.createObject(_world, ""));

		

	}

	/**
	 * 
	 */
	private void setUpsomeLabels() {
		Entity entity = createEntity();
		entity.addComponent(new LabelComponent("$", null, UIElementName.Money));
				
		addEntity(entity);
		
		Entity entity2 = createEntity();
		entity2.addComponent(new LabelComponent("", null, UIElementName.Date));
				
		addEntity(entity2);
		Entity entity3 = createEntity();
		entity3.addComponent(new LabelComponent("", null, UIElementName.Population));
				
		addEntity(entity3);
		
	}
	
	private void setupSomeButtons(){
		addEntity(EntityFactory.createButton(getWorld(), "Save", null, new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Logger.Log("clicked save");
				PersistenceManager.Save(getWorld());

			}}));

		addEntity(EntityFactory.createButton(getWorld(), "wipe", null, new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Logger.Log("clicked wipe");
				ImmutableBag<Entity> entities = getWorld().getManager(GroupManager.class).getEntities("persist");
				for (int i = entities.size()-1;i >=0;i--) {
					entities.get(i).deleteFromWorld();
				}
				//PersistenceManager.Load(_world, "save.xml");

			}}));
		addEntity(EntityFactory.createButton(getWorld(), "Load", null, new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Logger.Log("clicked load");

				PersistenceManager.Load(getWorld(), "save.bin");


			}}));

		addEntity(EntityFactory.createButton(getWorld(), "Residential", null, new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Logger.Log("clicked room");
				//TODO check for gold would be in this phase i guess.
				//getSystem(RoomBuildSystem.class).StartBuild(new RoomComponent());
				if(ComponentHelper.GetPlayerComponent().Money < ZONEPRICE)
					return;
				ComponentHelper.GetPlayerComponent().Money -= ZONEPRICE;
				addEntity(EntityFactory.createResidential(getWorld()));

			}}));
		addEntity(EntityFactory.createButton(getWorld(), "Comercial", null, new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Logger.Log("clicked room");
				if(ComponentHelper.GetPlayerComponent().Money < ZONEPRICE)
					return;
				ComponentHelper.GetPlayerComponent().Money -= ZONEPRICE;
				addEntity(EntityFactory.createComercial(getWorld()));

			}}));
		addEntity(EntityFactory.createButton(getWorld(), "Industrial", null, new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(ComponentHelper.GetPlayerComponent().Money < ZONEPRICE)
					return;
				ComponentHelper.GetPlayerComponent().Money -= ZONEPRICE;
				addEntity(EntityFactory.createIndustrial(getWorld()));

			}}));
		addEntity(EntityFactory.createButton(getWorld(), "Power Plant", null, new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(ComponentHelper.GetPlayerComponent().Money < POWERPLANTPRICE)
					return;
				ComponentHelper.GetPlayerComponent().Money -= POWERPLANTPRICE;
				addEntity(EntityFactory.createPowerPlant(getWorld()));

			}}));
		
		addEntity(EntityFactory.createButton(getWorld(), "Road", null, new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(ComponentHelper.GetPlayerComponent().Money < ROADPRICE)
					return;
				ComponentHelper.GetPlayerComponent().Money -= ROADPRICE;
				addEntity(EntityFactory.createRoad(getWorld()));

			}}));
	}

	/**
	 * @param delta
	 */
	public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		this._camera.update();
		//_uiCamera.update();

		setDelta(0.016f);
		process();
		//_guiGameGUI.render();
	}

	public World getWorld(){
		return this;
	}

}
