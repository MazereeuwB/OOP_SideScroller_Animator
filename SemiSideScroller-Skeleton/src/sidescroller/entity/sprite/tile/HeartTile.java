package sidescroller.entity.sprite.tile;

import utility.Tuple;

public enum HeartTile implements Tile{

	FULL_HEART(1), EMPTY_HEART(1), HALF_HEART(1), HEARTS(3);
	
	private Tuple coord;
	private Tuple size;
	private Tuple count;
	
	private HeartTile(int count) {
		this.coord = Tuple.pair(0, 0);
		this.size = Tuple.pair(count, 1);
		this.count = Tuple.pair(20, 20);
	}
	
	@Override
	public Tuple coord() {
		return coord;
	}

	@Override
	public Tuple size() {
		return size;
	}

	@Override
	public Tuple count() {
		return count;
	}

}
