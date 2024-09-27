package src.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Baralho {
    private static final String[] ATRIBUTOS = { "fogo", "agua", "terra" };
    private static final Random RANDOM = new Random();
    private static final int NUM_CARTAS = 5; 

    public List<Carta> criarMao(String jogador) {
        List<Carta> mao = new ArrayList<>();
        for (int i = 0; i < NUM_CARTAS; i++) {
            mao.add(criarCarta(jogador));
        }
        return mao; 
    }

    private Carta criarCarta(String jogador) {
        int cima = RANDOM.nextInt(10);
        int baixo = RANDOM.nextInt(10);
        int esquerda = RANDOM.nextInt(10);
        int direita = RANDOM.nextInt(10);
        String elemento = ATRIBUTOS[RANDOM.nextInt(ATRIBUTOS.length)];
        
        return new Carta(cima, baixo, esquerda, direita, jogador, elemento);
    }

    public List<Carta> criarMaoVerde() {
        return criarMao("verde");
    }

    public List<Carta> criarMaoAzul() {
        return criarMao("azul");
    }
}
