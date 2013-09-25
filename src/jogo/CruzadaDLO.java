package jogo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeSet;

public class CruzadaDLO {
	public final static int MAX_PALAVRAS = 60;
	private final static int MAX_TENTATIVAS_RANDOM = 500;

	public final static HashMap<Integer, ArrayList<PalavraDoJogo>> lerPalavras()
			throws Exception {
		return (new CruzadaDAO()).lerPalavrasDicionario();
	}

	public final static boolean testarPalavras(
			HashMap<Integer, ArrayList<PalavraDoJogo>> palavras) {
		Object[] k = palavras.keySet().toArray();
		for (int i = 0; i < k.length; i++) {
			for (int j = 0; j < palavras.get(k[i]).size(); j++) {
				if (palavras.get(k[i]).get(j).palavra.length() != palavras.get(
						k[i]).get(j).palavraDoJogo.length()) {
					return false;
				}
			}
		}
		return true;
	}

	public final static PalavraDoJogo escolherPalavraAdequada(
			ArrayList<PalavraDoJogo> palavras, String match,
			TreeSet<String> palavrasNoJogo) {
		Random generator = new Random(System.currentTimeMillis());
		PalavraDoJogo palavraSorteada = null;
		int count = 0;
		do {
			int random = Math.abs(generator.nextInt());
			random = random % palavras.size();
			random = random == 0 ? 1 : random;
			palavraSorteada = palavras.get(random);
			count++;
			if(count == MAX_TENTATIVAS_RANDOM) {
				break;
			}
		} while (!palavraSorteada.palavraDoJogo.matches(match)
				|| palavrasNoJogo.contains(palavraSorteada.palavraDoJogo));
		
		if ((!palavraSorteada.palavraDoJogo.matches(match)
				|| palavrasNoJogo.contains(palavraSorteada.palavraDoJogo))
				&& count == MAX_TENTATIVAS_RANDOM) {
			palavraSorteada = null;
		} else {
			palavrasNoJogo.add(palavraSorteada.palavraDoJogo);
		}
		
		return palavraSorteada;
	}
}