package sidescroller.scene;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.canvas.Canvas;
import sidescroller.animator.AnimatorInterface;

import sidescroller.entity.property.Entity;
import sidescroller.entity.property.HitBox;
import sidescroller.entity.sprite.tile.BackgroundTile;
import sidescroller.entity.sprite.tile.FloraTile;
import sidescroller.entity.sprite.tile.PlatformTile;
import sidescroller.entity.sprite.tile.Tile;
import utility.Tuple;

public class MapScene implements MapSceneInterface {
	private Tuple count;
	private Tuple size;
	private double scale;
	private AnimatorInterface animator;
	private List<Entity> players;
	private List<Entity> staticShapes;
	private BooleanProperty drawBounds;
	private BooleanProperty drawFPS;
	private BooleanProperty drawGrid;
	private Entity background;

	public MapScene() {
		this.drawBounds = new SimpleBooleanProperty();
		this.drawFPS = new SimpleBooleanProperty();
		this.drawGrid = new SimpleBooleanProperty();
		this.players = new ArrayList<Entity>();
		this.staticShapes = new ArrayList<Entity>();
	}

	@Override
	public BooleanProperty drawFPSProperty() {
		return drawFPS;
	}

	@Override
	public boolean getDrawFPS() {
		return drawFPS.get();
	}

	@Override
	public BooleanProperty drawBoundsProperty() {
		return drawBounds;
	}

	@Override
	public boolean getDrawBounds() {
		return drawBounds.get();
	}

	@Override
	public BooleanProperty drawGridProperty() {
		return drawGrid;
	}

	@Override
	public boolean getDrawGrid() {
		return drawGrid.get();
	}

	/**
	 * save date in the correct variables.
	 * 
	 * @param count - number of rows and columns in the grid.
	 * @param size  - width and height of each cell in grid.
	 * @param scale - a double multiplier for width and height of each grid cell.
	 * @return current instance of this class.
	 */
	@Override
	public MapSceneInterface setRowAndCol(Tuple count, Tuple size, double scale) {
		this.scale = scale;
		this.count = count;
		this.size = size;
		return this;
	}

	@Override
	public Tuple getGridCount() {
		return count;
	}

	@Override
	public Tuple getGridSize() {
		return size;
	}

	@Override
	public double getScale() {
		return scale;
	}

	@Override
	public MapSceneInterface setAnimator(AnimatorInterface newAnimator) {
		if (this.animator != null) animator.stop();
		if (newAnimator == null) throw new NullPointerException("newAnimator is null");
		this.animator = newAnimator;
		return this;
	}

	@Override
	public Entity getBackground() {
		return this.background;
	}

	@Override
	public void start() {
		if (animator == null)
			throw new NullPointerException("null animator, cannot start");
		animator.start();

	}

	@Override
	public void stop() {
		if (animator == null)
			throw new NullPointerException("null animator, cannot stop");
		animator.stop();
	}

	@Override
	public List<Entity> staticShapes() {
		return staticShapes;
	}

	@Override
	public List<Entity> players() {
		return players;
	}

	@Override
	public MapSceneInterface createScene(Canvas canvas) {
		MapBuilder mb = MapBuilder.createBuilder();
		mb.setCanvas(canvas).setGrid(count, size).setGridScale(scale);
		mb.buildLandMass(12, 10, 2, 18);// row pos, col pos, # rows, # cols
		BiFunction<Integer, Integer, Tile> biFunction = (row, column) -> { return BackgroundTile.EVENING; }; 
		mb.buildBackground(biFunction);
		mb.buildTree(5, 14, FloraTile.TREE);
		mb.buildPlatform(10, 10, 6, PlatformTile.STONE);
		background = mb.getBackground();

		mb.getEntities(staticShapes);
		return this;

	}

	@Override
	public boolean inMap(HitBox hitbox) {
		return background.getHitBox().containsBounds(hitbox);

	}

}
