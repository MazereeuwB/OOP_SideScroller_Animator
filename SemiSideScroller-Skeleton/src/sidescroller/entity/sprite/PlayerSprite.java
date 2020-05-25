package sidescroller.entity.sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sidescroller.entity.property.Sprite;
import sidescroller.entity.sprite.tile.Samurai;

public class PlayerSprite extends Sprite{

	private static final Image IDE = new Image( "file:assets\\samurai\\Idle_strip4.png");
	private static final Image RUN = new Image( "file:assets\\samurai\\Run_strip4.png");
	private static final Image JUMP = new Image( "file:assets\\samurai\\Jump_strip2.png");
	private static final Image SWORD = new Image( "file:assets\\samurai\\Sword_strip6.png");
	private static final Image DOWN  = new Image( "file:assets\\samurai\\Down.png");
	private static final Image BOW_UP  = new Image( "file:assets\\samurai\\BowUp_strip6.png");
	private static final Image BOW_DOWN  = new Image( "file:assets\\samurai\\BowDown_strip6.png");
	private static final Image BOW  = new Image( "file:assets\\samurai\\Bow_strip6.png");
	private static final Image BOW_UP_RIGHT  = new Image( "file:assets\\samurai\\BowUpRight_strip6.png");
	private static final Image BOW_DOWN_RIGHT  = new Image( "file:assets\\samurai\\BowDownRight_strip6.png");
	private static final Image OW  = new Image( "file:assets\\samurai\\Ow_strip4.png");
	
	

	private double playerFrame = 0;
	private Image activeImage;
	private Samurai tile;
	private boolean left;

	@Override
	public void draw( GraphicsContext gc){
		playerFrame += .1;
		if( playerFrame > tile.count().x()){
			playerFrame = 0;
		}
		if(left){
			gc.drawImage( activeImage, ((int) playerFrame) * 20.0, 0, 20, 20, coord.x()+(20 * scale)+8, coord.y(), -20 * scale, 20 * scale);
		}else{
			gc.drawImage( activeImage, ((int) playerFrame) * 20.0, 0, 20, 20, coord.x(), coord.y(), 20 * scale, 20 * scale);
		}
	}
	
	/**
	 * determine if player animation should be facing left or right.
	 * @param isLeft - true if left, false if right.
	 */
	public void setLeft( boolean isLeft){
		left = isLeft;
	}

	/**
	 * Using Samurai enum determine what animation to use for player sprite.
	 * @param tile - {@link Samurai} object.
	 */
	public void setTile( Samurai tile){
		if(tile==this.tile)
			return;
		switch (tile) {
			case IDLE:
				activeImage = IDE;
				break;
			case JUMP:
				activeImage = JUMP;
				break;
			case RUN:
				activeImage = RUN;
				break;
			case SWORD:
				activeImage = SWORD;
				break;
			case OW:
				activeImage = OW;
				break;
			case BOW_UP:
				activeImage = BOW_UP;
				break;
			case BOW_DOWN:
				activeImage = BOW_DOWN;
				break;
			case BOW_UP_RIGHT:
				activeImage = BOW_UP_RIGHT;
				break;
			case BOW_DOWN_RIGHT:
				activeImage = BOW_DOWN_RIGHT;
				break;
			case BOW:
				activeImage = BOW;
				break;
			case DOWN:
				activeImage = DOWN;
				break;
			default:
				throw new IllegalArgumentException( "Tile \"" + tile + "\" is not supported");
		}
		this.tile = tile;
		playerFrame = 0;
	}
}
