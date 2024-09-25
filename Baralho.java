package ProjetoParadigmas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Baralho {
    private static final String[] ATRIBUTOS = {"fogo", "agua", "terra"};
    private static final Random RANDOM = new Random();

    // Método para criar mão de cartas para o jogador Verde
    public List<Carta> criarMaoVerde() {
        return criarMao("verde");
    }

    // Método para criar mão de cartas para o jogador Azul
    public List<Carta> criarMaoAzul() {
        return criarMao("azul");
    }

    // Método auxiliar para criar mão de cartas
    private List<Carta> criarMao(String jogador) {
        List<Carta> mao = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int cima = RANDOM.nextInt(10);
            int baixo = RANDOM.nextInt(10);
            int esquerda = RANDOM.nextInt(10);
            int direita = RANDOM.nextInt(10);
            String elemento = ATRIBUTOS[RANDOM.nextInt(ATRIBUTOS.length)];

            // Criar carta com o jogador definido
            mao.add(new Carta(cima, baixo, esquerda, direita, jogador, elemento));
        }
        return mao;  // Retorna a mão de cartas criada
    }
}
