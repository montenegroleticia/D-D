package src.utils;

import java.util.List;
import java.util.Scanner;

import src.entities.Carta;
import src.entities.Tabuleiro;

public class JogoUtils {
    private static final int TAMANHO_TABULEIRO = 3;

    public static int escolherCarta(List<Carta> mao, Scanner scanner) {
        System.out.println("Escolha uma carta (0 a " + (mao.size() - 1) + "): ");
        int escolha = scanner.nextInt();
        while (escolha < 0 || escolha >= mao.size()) {
            System.out.println("Escolha inválida. Tente novamente.");
            escolha = scanner.nextInt();
        }
        return escolha;
    }

    public static int[] escolherPosicao(Scanner scanner) {
        System.out.println("Escolha a posição (linha e coluna de 0 a " + (TAMANHO_TABULEIRO - 1) + "): ");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        return new int[]{x, y};
    }

    public static boolean posicaoValida(int[] posicao, Tabuleiro tabuleiro) {
        int x = posicao[0];
        int y = posicao[1];
        return x >= 0 && x < TAMANHO_TABULEIRO && y >= 0 && y < TAMANHO_TABULEIRO && tabuleiro.posicaoLivre(x, y);
    }
}
