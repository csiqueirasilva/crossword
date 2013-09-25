package boundary;

class QuadradoJogo extends Quadrado {
	public QuadradoTip quadTip;
	public char letraCerta;

	public char getLetra() {
		return letraCerta;
	}

	public QuadradoJogo(QuadradoTip tip, int x, int y, char letraCerta) {
		super(x, y);
		this.letraCerta = letraCerta;
	}

}
