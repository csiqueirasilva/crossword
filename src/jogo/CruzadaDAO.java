package jogo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CruzadaDAO {

	HashMap<Integer, ArrayList<PalavraDoJogo>> lerPalavrasDicionario()
			throws Exception {

		try {
			BufferedReader dicionario = new BufferedReader(new FileReader(
					"dicionario.txt"));
			String linha = dicionario.readLine();
			Pattern patternPalavra = Pattern.compile("(?U)([^\\s]+){1}"), patternDescricao = Pattern
					.compile("(?U)^(?:\\w+.: )?(.*)");
			HashMap<Integer, ArrayList<PalavraDoJogo>> palavras = new HashMap<Integer, ArrayList<PalavraDoJogo>>();

			while (linha != null) {
				Matcher m = patternPalavra.matcher(linha);
				m.find();
				String palavra = m.group(1);

				if (palavras.get(palavra.length()) == null) {
					palavras.put(palavra.length(),
							new ArrayList<PalavraDoJogo>());
				} else {
					ArrayList<PalavraDoJogo> list = palavras.get(palavra
							.length());
					m = patternDescricao.matcher(linha);
					m.find();
					String descricao = m.group(1);
					PalavraDoJogo pdj = new PalavraDoJogo();
					pdj.descricao = descricao;
					pdj.palavra = palavra;
					pdj.palavraDoJogo = Normalizer.normalize(palavra,
							Normalizer.Form.NFD)
							.replaceAll("[^\\p{ASCII}]", "");
					list.add(pdj);
				}
				linha = dicionario.readLine();
			}
			dicionario.close();

			return palavras;

		} catch (Exception e) {
			throw e;
		}

	}
}