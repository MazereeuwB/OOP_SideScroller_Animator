package sidescroller.animator;



import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sidescroller.entity.FpsCounter;
import sidescroller.entity.Grid;
import sidescroller.scene.MapSceneInterface;
import utility.Tuple;

public abstract class AbstractAnimator extends AnimationTimer implements AnimatorInterface{

	protected MapSceneInterface map;
	protected Tuple mouse;
	private Canvas canvas;
	private FpsCounter fps;
	private Grid grid;
	private GraphicsContext gc;
	
	public AbstractAnimator() {
		mouse = new Tuple();
		fps = new FpsCounter(10,25);
		fps.getDrawable().setFill(Color.BLACK).setStroke(Color.WHITE).setWidth(1.0);
		
	}
	@Override
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
		
	}

	@Override
	public void setMapScene(MapSceneInterface map) {
		this.map = map;
		
	}

	@Override
	public void clearAndFill(GraphicsContext gc, Color background) {
		gc.setFill(background);
		gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
	}

	
	public abstract void handle(GraphicsContext gc, long now);


	@Override
	public void handle(long now) {
		gc = canvas.getGraphicsContext2D();
		if(map.getDrawFPS()) fps.calculateFPS(now);
		handle(gc, now);
		if(map.getDrawGrid()) {
			if(grid==null) {
				grid = new Grid(map.getGridCount(), canvas.getWidth(),canvas.getHeight());
				grid.getDrawable().setStroke(Color.BLACK).setWidth(1.0).setScale(map.getScale()).setTileSize(map.getGridSize());
			}
			grid.getDrawable().draw(gc);
		}
		if(map.getDrawFPS()) fps.getDrawable().draw(gc);
		
	}
	
}
