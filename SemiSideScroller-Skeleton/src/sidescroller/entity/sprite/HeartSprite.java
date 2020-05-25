package sidescroller.entity.sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sidescroller.entity.property.Sprite;
import sidescroller.entity.sprite.tile.HeartTile;

public class HeartSprite extends Sprite{
	
	private static final Image EMPTY_HEART  = new Image( "file:assets\\heart\\empty_heart.png");
	private static final Image FULL_HEART  = new Image( "file:assets\\heart\\full_heart.png");
	private static final Image HALF_HEART  = new Image( "file:assets\\heart\\half_heart.png");
	//private static final Image HEARTS  = new Image( "file:assets\\heart\\hearts.png");

	private Image heartImage;
	private HeartTile tile;
	
	@Override
	public void draw( GraphicsContext gc){
		gc.drawImage( heartImage, coord.x() * tileSize.x() * scale, coord.y() * tileSize.y() * scale);
	}
	
	public void setTile( HeartTile tile){
		if(tile==this.tile)
			return;
		switch (tile) {
			case FULL_HEART:
				heartImage = FULL_HEART;
				break;
			case EMPTY_HEART:
				heartImage = EMPTY_HEART;
				break;
			case HALF_HEART:
				heartImage = HALF_HEART;
				break;
			default:
				throw new IllegalArgumentException( "Tile \"" + tile + "\" is not supported");
		}
		this.tile = tile;
	}

}
