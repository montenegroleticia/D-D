package src;

import java.util.List;
import java.util.Scanner;

import src.entities.Baralho;
import src.entities.Carta;
import src.entities.Tabuleiro;
import src.utils.JogoUtils;

public class Main {
    private static final int TAMANHO_TABULEIRO = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Baralho baralho = new Baralho();

        List<Carta> maoVerde = baralho.criarMaoVerde();
        List<Carta> maoAzul = baralho.criarMaoAzul();

        Tabuleiro tabuleiro = new Tabuleiro();

        exibirMaos(maoVerde, maoAzul);

        while (!tabuleiro.jogoTerminado()) {
            realizarTurno("Verde", maoVerde, tabuleiro, scanner);

            if (tabuleiro.jogoTerminado()) {
                break;
            }
            realizarTurno("Azul", maoAzul, tabuleiro, scanner);
        }

        System.out.println("\nO jogo terminou!");
        tabuleiro.mostrarTabuleiro();
        scanner.close(); 
    }

    private static void exibirMaos(List<Carta> maoVerde, List<Carta> maoAzul) {
        System.out.println("Mão do jogador Verde:");
        for (Carta carta : maoVerde) {
            System.out.println(carta.toString());
        }

        System.out.println("Mão do jogador Azul:");
        for (Carta carta : maoAzul) {
            System.out.println(carta.toString());
        }
    }
    private static void realizarTurno(String jogador, List<Carta> mao, Tabuleiro tabuleiro, Scanner scanner) {
        System.out.println("Turno do jogador " + jogador + ".");
        int escolhaCarta = JogoUtils.escolherCarta(mao, scanner);
        int[] posicao = JogoUtils.escolherPosicao(scanner);

        while (!JogoUtils.posicaoValida(posicao, tabuleiro)) {
            System.out.println("Posição inválida ou ocupada. Tente novamente.");
            posicao = JogoUtils.escolherPosicao(scanner);
        }

        Carta cartaEscolhida = mao.get(escolhaCarta);
        tabuleiro.inserirCarta(posicao[0], posicao[1], cartaEscolhida);
    }
}
