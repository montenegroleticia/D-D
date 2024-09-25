package src;

import java.util.List;
import java.util.Scanner;

import src.entities.Baralho;
import src.entities.Carta;
import src.entities.Tabuleiro;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Baralho baralho = new Baralho();

        // Criando mãos para os jogadores
        List<Carta> maoVerde = baralho.criarMaoVerde();
        List<Carta> maoAzul = baralho.criarMaoAzul();

        // Criando o tabuleiro
        Tabuleiro tabuleiro = new Tabuleiro();

        // Exibir mãos iniciais
        System.out.println("Mão do jogador Verde: " + maoVerde);
        System.out.println("Mão do jogador Azul: " + maoAzul);

        // Lógica do jogo
        while (!tabuleiro.jogoTerminado()) {
            // Jogador Verde
            System.out.println("Turno do jogador Verde.");
            System.out.println("Escolha uma carta (0 a " + (maoVerde.size() - 1) + "): ");
            int escolhaVerde = scanner.nextInt();
            System.out.println("Escolha a posição (linha, coluna): ");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            tabuleiro.inserirCarta(x, y, maoVerde.get(escolhaVerde));

            // Verifica se o jogo terminou após o turno do jogador Verde
            if (tabuleiro.jogoTerminado()) {
                break;
            }

            // Jogador Azul
            System.out.println("Turno do jogador Azul.");
            System.out.println("Escolha uma carta (0 a " + (maoAzul.size() - 1) + "): ");
            int escolhaAzul = scanner.nextInt();
            System.out.println("Escolha a posição (linha, coluna): ");
            x = scanner.nextInt();
            y = scanner.nextInt();
            tabuleiro.inserirCarta(x, y, maoAzul.get(escolhaAzul));
        }

        // Exibição final do tabuleiro
        System.out.println("\nO jogo terminou!");
        tabuleiro.mostrarTabuleiro();
        scanner.close(); // Fecha o scanner
    }
}
