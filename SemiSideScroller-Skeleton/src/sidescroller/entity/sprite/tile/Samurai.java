package sidescroller.entity.sprite.tile;

import utility.Tuple;

public enum Samurai implements Tile{

	RUN(4), IDLE(4), JUMP(2), SWORD(6), OW(4), DOWN(1), BOW(6), BOW_DOWN(6), BOW_DOWN_RIGHT(6), BOW_UP(6), BOW_UP_RIGHT(6);
	
	private Tuple coord;
	private Tuple count;
	private Tuple size;

	private Samurai( int count){
		this.coord = Tuple.pair( 0, 0);
		this.count = Tuple.pair( count, 1);
		this.size = Tuple.pair( 20, 20);
	}

	@Override
	public Tuple coord(){
		return coord;
	}

	@Override
	public Tuple size(){
		return size;
	}

	@Override
	public Tuple count(){
		return count;
	}
}
