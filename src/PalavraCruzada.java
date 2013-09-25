import jogo.CruzadaDLO;
import boundary.MatrizCruzada;

public class PalavraCruzada {

	public static void main(String args[]) {
		try {
			MatrizCruzada m = new MatrizCruzada(CruzadaDLO.MAX_PALAVRAS, CruzadaDLO.lerPalavras());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}