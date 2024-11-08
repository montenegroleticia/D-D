import java.util.List;
import java.util.Scanner;

import entities.Baralho;
import entities.Carta;
import entities.Tabuleiro;

import utils.JogoUtils;

public class Main {
    private static final int TAMANHO_TABULEIRO = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Baralho baralho = new Baralho();

        List<Carta> maoVerde = baralho.getMao("verde");
        List<Carta> maoAzul = baralho.getMao("azul");

        Tabuleiro tabuleiro = new Tabuleiro();

        while (!tabuleiro.jogoTerminado()) {
            exibirMaos(maoVerde, maoAzul);
            realizarTurno("Verde", maoVerde, tabuleiro, baralho, scanner);

            if (tabuleiro.jogoTerminado()) {
                break;
            }

            exibirMaos(maoVerde, maoAzul);
            realizarTurno("Azul", maoAzul, tabuleiro, baralho, scanner);
        }

        System.out.println("\nO jogo terminou!");
        tabuleiro.mostrarTabuleiro();
        scanner.close();
    }

    private static void exibirMaos(List<Carta> maoVerde, List<Carta> maoAzul) {
        System.out.println("\nMão do jogador Verde:");
        for (int i = 0; i < maoVerde.size(); i++) {
            System.out.println(i + ": " + maoVerde.get(i));
        }

        System.out.println("\nMão do jogador Azul:");
        for (int i = 0; i < maoAzul.size(); i++) {
            System.out.println(i + ": " + maoAzul.get(i));
        }
    }

    private static void realizarTurno(String jogador, List<Carta> mao, Tabuleiro tabuleiro, Baralho baralho, Scanner scanner) {
        System.out.println("\nTurno do jogador " + jogador + ".");
        int escolhaCarta = JogoUtils.escolherCarta(mao, scanner);

        Carta cartaEscolhida = mao.get(escolhaCarta);
        int[] posicao = JogoUtils.escolherPosicao(scanner);

        while (!JogoUtils.posicaoValida(posicao, tabuleiro)) {
            System.out.println("Posição inválida ou ocupada. Tente novamente.");
            posicao = JogoUtils.escolherPosicao(scanner);
        }

        tabuleiro.inserirCarta(posicao[0], posicao[1], cartaEscolhida);
        baralho.removerCarta(jogador.toLowerCase(), cartaEscolhida); // Remove a carta da mão
    }
}
