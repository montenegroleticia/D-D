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

        // Criando mãos para os jogadores
        List<Carta> maoVerde = baralho.criarMaoVerde();
        List<Carta> maoAzul = baralho.criarMaoAzul();

        // Criando o tabuleiro
        Tabuleiro tabuleiro = new Tabuleiro();

        // Exibir mãos iniciais
        exibirMaos(maoVerde, maoAzul);

        // Lógica do jogo
        while (!tabuleiro.jogoTerminado()) {
            // Turno do jogador Verde
            realizarTurno("Verde", maoVerde, tabuleiro, scanner);

            if (tabuleiro.jogoTerminado()) {
                break;
            }

            // Turno do jogador Azul
            realizarTurno("Azul", maoAzul, tabuleiro, scanner);
        }

        // Exibição final do tabuleiro
        System.out.println("\nO jogo terminou!");
        tabuleiro.mostrarTabuleiro();
        scanner.close(); // Fecha o scanner
    }

    // Exibe as mãos dos jogadores
    private static void exibirMaos(List<Carta> maoVerde, List<Carta> maoAzul) {
        System.out.println("Mão do jogador Verde: " + maoVerde);
        System.out.println("Mão do jogador Azul: " + maoAzul);
    }

    // Realiza o turno de um jogador
    private static void realizarTurno(String jogador, List<Carta> mao, Tabuleiro tabuleiro, Scanner scanner) {
        System.out.println("Turno do jogador " + jogador + ".");
        int escolhaCarta = JogoUtils.escolherCarta(mao, scanner);
        int[] posicao = JogoUtils.escolherPosicao(scanner);

        while (!JogoUtils.posicaoValida(posicao, tabuleiro)) {
            System.out.println("Posição inválida ou ocupada. Tente novamente.");
            posicao = JogoUtils.escolherPosicao(scanner);
        }

        tabuleiro.inserirCarta(posicao[0], posicao[1], mao.get(escolhaCarta));
    }
}
