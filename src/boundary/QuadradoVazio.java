package boundary;

import java.awt.Color;

class QuadradoVazio extends Quadrado {
	public QuadradoVazio(int x, int y) {
		super(x, y);
		this.cor = Color.black;
		this.removeMouseListener(this);
		this.removeKeyListener(this);
	}
}