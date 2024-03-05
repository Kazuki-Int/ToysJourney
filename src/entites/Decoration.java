package entites;

import java.awt.Point;

public class Decoration extends Entity{
	private Decorations decoType;
	private Point pos;
	float x, y;

	public Decoration (Point pos, Decorations decoType) {
		super(pos.x, pos.y, decoType.width, decoType.height);
        this.pos = pos;
		this.decoType = decoType;
	}
	
	public Decorations getDecoType() {
		return decoType;
	}
	
	public Point getPos() {
        return pos;
    }

}
