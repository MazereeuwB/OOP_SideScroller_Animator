package sidescroller.scene;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import javafx.scene.canvas.Canvas;
import sidescroller.entity.GenericEntity;
import sidescroller.entity.property.Entity;
import sidescroller.entity.property.HitBox;
import sidescroller.entity.sprite.BackgroundSprite;
import sidescroller.entity.sprite.LandSprite;
import sidescroller.entity.sprite.PlatformSprite;
import sidescroller.entity.sprite.SpriteFactory;
import sidescroller.entity.sprite.TreeSprite;
import sidescroller.entity.sprite.tile.Tile;
import utility.Tuple;

public class MapBuilder implements MapBuilderInterface{
	private Tuple rowColCount;
	private Tuple dimension;
	private Double scale;
	private Canvas canvas;
	private Entity background;
	private List<Entity> landMass = new ArrayList<>();
	private List<Entity> other = new ArrayList<>();
	private static MapBuilder BUILDER = new MapBuilder();
	BackgroundSprite backgroundSprite;
	TreeSprite treeSprite;
	LandSprite landSprite;
	PlatformSprite platformSprite;
	
	protected MapBuilder() {
		landMass = new ArrayList<>();
		other = new ArrayList<>();
		backgroundSprite = new BackgroundSprite();
		treeSprite = new TreeSprite();
		landSprite = new LandSprite();
		platformSprite = new PlatformSprite();
		background = new GenericEntity();
	}
	public static MapBuilder createBuilder() {
		return BUILDER;
	}
	
	@Override
	public MapBuilderInterface setCanvas(Canvas canvas) {
		this.canvas = canvas;
		return this;
	}

	@Override
	public MapBuilderInterface setGrid(Tuple rowColCount, Tuple dimension) {
		this.rowColCount = rowColCount;
		this.dimension = dimension;
		return this;
	}

	@Override
	public MapBuilderInterface setGridScale(double scale) {
		this.scale = scale;
		return this;
	}

	@Override
	public MapBuilderInterface buildBackground(BiFunction<Integer, Integer, Tile> callback) {
		backgroundSprite = SpriteFactory.get("Background");
		backgroundSprite.init(scale, dimension, Tuple.pair(0, 0));
		backgroundSprite.createSnapshot(canvas, rowColCount, callback);
		this.background =  new GenericEntity(backgroundSprite, HitBox.build(0, 0, scale*dimension.x()*rowColCount.y(),
															scale*dimension.y()*rowColCount.x()));
		return this;
	}

	@Override
	public MapBuilderInterface buildLandMass(int rowPos, int colPos, int rowCount, int colCount) {
		landSprite = SpriteFactory.get("Land");
		landSprite.init(scale, dimension, Tuple.pair(colPos, rowPos));
		landSprite.createSnapshot(canvas, rowCount, colCount);
		landMass.add(new GenericEntity(landSprite, HitBox.build(colPos*dimension.x()*scale,
													rowPos*dimension.y()*scale, scale*dimension.x()*colCount,
													scale*dimension.y()*rowCount)));
		return this;
	}

	@Override
	public MapBuilderInterface buildTree(int rowPos, int colPos, Tile tile) {
		treeSprite = SpriteFactory.get("Tree");
		treeSprite.init(scale, dimension, Tuple.pair(colPos, rowPos));
		treeSprite.createSnapshot(canvas, tile);
		other.add(new GenericEntity(treeSprite, null));
		return this;
	}

	@Override
	public MapBuilderInterface buildPlatform(int rowPos, int colPos, int length, Tile tile) {
		platformSprite = SpriteFactory.get("Platform");
		platformSprite.init(scale, dimension, Tuple.pair(colPos, rowPos));
		platformSprite.createSnapshot(canvas, tile, length);
		other.add(new GenericEntity(platformSprite, HitBox.build((colPos +.5)*dimension.x() * scale,
													rowPos * dimension.y() * scale, scale * dimension.x()
													* (length - 1), scale * dimension.y() / 2)));
		return this;
	}

	@Override
	public Entity getBackground() {
		return background;
	}

	@Override
	public List<Entity> getEntities(List<Entity> list) {
	
		if(list == null) throw new NullPointerException("null list");
		list.addAll(landMass);
		list.addAll(other);
		
		return list;
	}

}
