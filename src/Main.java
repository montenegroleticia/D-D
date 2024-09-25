package src;

import java.util.List;
import java.util.Scanner;

import src.entities.Baralho;
import src.entities.Carta;
import src.entities.Tabuleiro;

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
        int escolhaCarta = escolherCarta(mao, scanner);
        int[] posicao = escolherPosicao(scanner);

        while (!posicaoValida(posicao, tabuleiro)) {
            System.out.println("Posição inválida ou ocupada. Tente novamente.");
            posicao = escolherPosicao(scanner);
        }

        tabuleiro.inserirCarta(posicao[0], posicao[1], mao.get(escolhaCarta));
    }

    // Escolhe uma carta da mão do jogador
    private static int escolherCarta(List<Carta> mao, Scanner scanner) {
        System.out.println("Escolha uma carta (0 a " + (mao.size() - 1) + "): ");
        int escolha = scanner.nextInt();
        while (escolha < 0 || escolha >= mao.size()) {
            System.out.println("Escolha inválida. Tente novamente.");
            escolha = scanner.nextInt();
        }
        return escolha;
    }

    // Escolhe a posição no tabuleiro
    private static int[] escolherPosicao(Scanner scanner) {
        System.out.println("Escolha a posição (linha e coluna de 0 a " + (TAMANHO_TABULEIRO - 1) + "): ");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        return new int[] { x, y };
    }

    // Verifica se a posição é válida e se está livre
    private static boolean posicaoValida(int[] posicao, Tabuleiro tabuleiro) {
        int x = posicao[0];
        int y = posicao[1];
        return x >= 0 && x < TAMANHO_TABULEIRO && y >= 0 && y < TAMANHO_TABULEIRO && tabuleiro.posicaoLivre(x, y);
    }
}
