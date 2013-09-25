package boundary;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import jogo.PalavraDoJogo;


class QuadradoTip extends Quadrado {
	public static enum Direcao {
		DIREITA, ESQUERDA, BAIXO, CIMA
	};

	public Direcao aponto;
	public PalavraDoJogo palavraDoJogo;

	public QuadradoTip(PalavraDoJogo pdj, Direcao aponto, int x, int y) {
		super(x, y);
		this.aponto = aponto;
		this.palavraDoJogo = pdj;
		this.cor = Color.black;
		this.labelLetra.setForeground(Color.white);
		if (aponto == Direcao.DIREITA) {
			this.labelLetra.setText(">");
		} else if (aponto == Direcao.BAIXO) {
			this.labelLetra.setText("\\/");
		}
	}

	public void mouseClicked(MouseEvent e) {
		JOptionPane.showMessageDialog(null, palavraDoJogo.descricao);
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

}