package boundary;

import javax.swing.JFrame;
import javax.swing.JPanel;

import jogo.PalavraDoJogo;

class Motor {
	public final static int TAMANHO_CRUZADA = 15;
	private JFrame framePrincipal;

	public Motor() {
		framePrincipal = new JFrame();
		framePrincipal.setResizable(false);
		framePrincipal.setSize(
				(int) (Quadrado.TAMANHO * TAMANHO_CRUZADA * 1.1),
				(int) (Quadrado.TAMANHO * TAMANHO_CRUZADA * 1.15));
		framePrincipal.setVisible(true);
		framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePrincipal.setContentPane(new JPanel());
		framePrincipal.getContentPane().setLayout(null);
	}

	QuadradoJogo criaQuadradoJogo(int x, int y, int tamanho, char ch,
			QuadradoTip tip) {
		QuadradoJogo quad = new QuadradoJogo((QuadradoTip) tip, x * tamanho
				+ 10, y * tamanho + 10, ch);
		framePrincipal.add(quad);
		return quad;
	}

	QuadradoTip criaQuadradoTip(int x, int y, int tamanho,
			PalavraDoJogo palavra, QuadradoTip.Direcao dir) {
		QuadradoTip quad = new QuadradoTip(palavra, dir, x * tamanho + 10, y
				* tamanho + 10);
		framePrincipal.add(quad);
		return quad;
	}

	QuadradoVazio criaQuadradoVazio(int x, int y, int tamanho) {
		QuadradoVazio quad = new QuadradoVazio(x * tamanho + 10, y * tamanho
				+ 10);
		framePrincipal.add(quad);
		return quad;
	}

	public JFrame getFramePrincipal() {
		return framePrincipal;
	}

}