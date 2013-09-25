package boundary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeSet;

import jogo.CruzadaDLO;
import jogo.PalavraDoJogo;

public class MatrizCruzada {

	private Quadrado quad[][];
	private static int tamanhoCruzada = Motor.TAMANHO_CRUZADA;
	private static int tamanhoPixel = Quadrado.TAMANHO + 1;
	private Motor motor;

	private void preencherBlocosVazios() {
		int totalIteracao = (int) (tamanhoCruzada * tamanhoCruzada * 0.25 / 2);

		for (int i = 0; i < totalIteracao; i++) {
			Random r = new Random(System.currentTimeMillis());
			int x = Math.abs(r.nextInt()) % tamanhoCruzada, y = Math.abs(r
					.nextInt()) % tamanhoCruzada;
			quad[y][x] = motor.criaQuadradoVazio(x, y, tamanhoPixel);
			quad[tamanhoCruzada - 1 - y][tamanhoCruzada - 1 - x] = motor
					.criaQuadradoVazio(tamanhoCruzada - 1 - x, tamanhoCruzada
							- 1 - y, tamanhoPixel);
		}
	}

	public MatrizCruzada(int maxPalavras,
			HashMap<Integer, ArrayList<PalavraDoJogo>> palavras) {
		motor = new Motor();
		quad = new Quadrado[tamanhoCruzada][tamanhoCruzada];
		int x = 0, y = 0;
		TreeSet<String> palavrasNoJogo = new TreeSet<String>();

		preencherBlocosVazios();

		while (!matrizCheia()) {
			if (quad[y][x] == null) {
				if (palavrasNoJogo.size() < maxPalavras) {
					QuadradoTip.Direcao dir = null;
					String palavraDireita = verificaPossibilidade(
							QuadradoTip.Direcao.DIREITA, x, y), palavraBaixo = verificaPossibilidade(
							QuadradoTip.Direcao.BAIXO, x, y);

					boolean completoDireita = !palavraDireita
							.matches(".*\\..*");
					boolean completoBaixo = !palavraBaixo.matches(".*\\..*");

					String regexPalavraAdjacente = null;

					if (!completoBaixo || !completoDireita) {
						PalavraDoJogo palavraSorteada = null;
						if (!completoBaixo && !completoDireita) {

							if ((new Random(System.currentTimeMillis()))
									.nextInt() % 2 == 0) {
								dir = QuadradoTip.Direcao.DIREITA;
								regexPalavraAdjacente = palavraDireita;
							} else {
								dir = QuadradoTip.Direcao.BAIXO;
								regexPalavraAdjacente = palavraBaixo;
							}
						} else if (!completoDireita) {
							dir = QuadradoTip.Direcao.DIREITA;
							regexPalavraAdjacente = palavraDireita;
						} else if (!completoBaixo) {
							dir = QuadradoTip.Direcao.BAIXO;
							regexPalavraAdjacente = palavraBaixo;
						}
						
						if (regexPalavraAdjacente != null) {
							palavraSorteada = CruzadaDLO
									.escolherPalavraAdequada(
											palavras.get(regexPalavraAdjacente
													.length()),
											regexPalavraAdjacente,
											palavrasNoJogo);

							if (palavraSorteada != null) {
								Quadrado tip = quad[y][x] = motor
										.criaQuadradoTip(x, y, tamanhoPixel,
												palavraSorteada, dir);

								criaQuadradosJogoPalavra(dir, x, y,
										palavraSorteada, (QuadradoTip) tip);

								if (dir == QuadradoTip.Direcao.BAIXO) {
									y += palavraSorteada.palavraDoJogo.length();
								} else {
									x += palavraSorteada.palavraDoJogo.length();
								}
							} else {
								quad[y][x] = motor.criaQuadradoVazio(x, y,
										tamanhoPixel);
							}
						}

					} else {
						quad[y][x] = motor
								.criaQuadradoVazio(x, y, tamanhoPixel);
					}
				} else {
					quad[y][x] = motor.criaQuadradoVazio(x, y, tamanhoPixel);
				}
			}
			if (++x == tamanhoCruzada) {
				x = 0;
				if (++y == tamanhoCruzada) {
					y = 0;
					x = 0;
				}
			}
		}
		
		motor.getFramePrincipal().repaint();
	}

	public int countTip() {
		int count = 0;
		for(int i = 0; i < quad.length; i++) {
			for(int j = 0; j < quad.length; j++) {
				if(quad[i][j] instanceof QuadradoTip) {
					count++;
				}
			}
		}
		return count;
	}
	
	public boolean matrizCheia() {
		for (int i = 0; i < quad.length; i++) {
			for (int j = 0; j < quad[i].length; j++) {
				if (quad[i][j] == null) {
					return false;
				}
			}
		}
		return true;
	}

	public final String verificaPossibilidade(QuadradoTip.Direcao dir, int x,
			int y) {

		int icrX = 0, icrY = 0;
		String palavra = "";

		switch (dir) {
		case BAIXO:
			icrX = 0;
			icrY = 1;
			break;
		case CIMA:
			icrX = 0;
			icrY = -1;
			break;
		case DIREITA:
			icrX = 1;
			icrY = 0;
			break;
		case ESQUERDA:
			icrX = -1;
			icrY = 0;
		}

		x += icrX;
		y += icrY;
		
		for (; x < tamanhoCruzada && x >= 0 && y < tamanhoCruzada && y >= 0; x = icrX
				+ x, y = icrY + y) {
			if (quad[y][x] == null) {
				palavra += ".";
			} else if (quad[y][x] instanceof QuadradoJogo) {
				palavra += ((QuadradoJogo) quad[y][x]).getLetra();
			} else {
				break;
			}
		}

		return palavra;
	}

	public final void criaQuadradosJogoPalavra(QuadradoTip.Direcao dir, int x,
			int y, PalavraDoJogo p, QuadradoTip tip) {
		int icrX = 0, icrY = 0;

		switch (dir) {
		case BAIXO:
			icrX = 0;
			icrY = 1;
			break;
		case CIMA:
			icrX = 0;
			icrY = -1;
			break;
		case DIREITA:
			icrX = 1;
			icrY = 0;
			break;
		case ESQUERDA:
			icrX = -1;
			icrY = 0;
		}

		int refX = icrX, refY = icrY;

		for (int i = 1; i <= p.palavraDoJogo.length(); i++, refX += icrX, refY += icrY) {
			if (quad[refY + y][refX + x] == null) {
				quad[refY + y][refX + x] = motor.criaQuadradoJogo((refX + x),
						(refY + y), tamanhoPixel,
						p.palavraDoJogo.charAt(i - 1), tip);
			} else if (quad[refY + y][refX + x] instanceof QuadradoJogo) {
				// ADICIONA TIP PAI A ESTE QUADRADO JOGO, POIS ELE ESTARA
				// CRUZADO
			}
		}
	}

}