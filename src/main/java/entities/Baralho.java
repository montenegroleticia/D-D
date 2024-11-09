package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Baralho {
    private static final String[] ATRIBUTOS = { "fogo", "agua", "terra" };
    private static final Random RANDOM = new Random();
    private static final int NUM_CARTAS = 5;

    private List<Carta> maoVerde; // Mão do jogador verde
    private List<Carta> maoAzul; // Mão do jogador azul

    public Baralho() {
        this.maoVerde = criarMao("verde");
        this.maoAzul = criarMao("azul");
    }

    private List<Carta> criarMao(String jogador) {
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
        String img = "";

        if (elemento.equals("fogo")) {
            img = "images/cartas/fogo.jpeg";
        } else if (elemento.equals("agua")) {
            img = "images/cartas/agua.jpeg";
        } else if (elemento.equals("terra")) {
            img = "images/cartas/terra.jpeg";
        }

        return new Carta(cima, baixo, esquerda, direita, jogador, elemento, img);
    }

    public List<Carta> getMao(String jogador) {
        return "verde".equalsIgnoreCase(jogador) ? maoVerde : maoAzul;
    }

    public void removerCarta(String jogador, Carta carta) {
        List<Carta> mao = "verde".equalsIgnoreCase(jogador) ? maoVerde : maoAzul;

        mao.removeIf(c -> c.getCima() == carta.getCima() &&
                c.getBaixo() == carta.getBaixo() &&
                c.getEsquerda() == carta.getEsquerda() &&
                c.getDireita() == carta.getDireita() &&
                c.getJogador().equalsIgnoreCase(carta.getJogador()) &&
                c.getElemento().equalsIgnoreCase(carta.getElemento()));
    }
}
