package src.entities;

public class Tabuleiro {
    Carta[][] tabuleiro = new Carta[3][3];
    int pontosVerde = 5;
    int pontosAzul = 5;

    public void inserirCarta(int x, int y, Carta novaCarta) {
        if (tabuleiro[x][y] == null) {
            // Incrementa valores se houver cartas do mesmo elemento
            for (Carta[] linha : tabuleiro) {
                for (Carta carta : linha) {
                    if (carta != null && carta.elemento.equals(novaCarta.elemento)) {
                        carta.aumentarValores();
                    }
                }
            }

            tabuleiro[x][y] = novaCarta; // Insere a carta
            verificarCapturaAdjacente(x, y, novaCarta);
            mostrarTabuleiro();
        } else {
            System.out.println("Posição já ocupada!");
        }
    }

    private void verificarCapturaAdjacente(int x, int y, Carta novaCarta) {
        verificarCaptura(x - 1, y, novaCarta); // Cima
        verificarCaptura(x + 1, y, novaCarta); // Baixo
        verificarCaptura(x, y - 1, novaCarta); // Esquerda
        verificarCaptura(x, y + 1, novaCarta); // Direita
    }

    private void verificarCaptura(int x, int y, Carta novaCarta) {
        if (x >= 0 && x < 3 && y >= 0 && y < 3 && tabuleiro[x][y] != null) {
            Carta cartaAdjacente = tabuleiro[x][y];
            if (!cartaAdjacente.jogador.equals(novaCarta.jogador)) {
                // Captura se o valor for maior
                if (novaCarta.baixo > cartaAdjacente.cima) {
                    capturarCarta(x, y, novaCarta.jogador);
                } else if (novaCarta.direita > cartaAdjacente.esquerda) {
                    capturarCarta(x, y, novaCarta.jogador);
                } else if (cartaAdjacente.cima > novaCarta.baixo) {
                    capturarCarta(x, y, cartaAdjacente.jogador);
                } else if (cartaAdjacente.esquerda > novaCarta.direita) {
                    capturarCarta(x, y, cartaAdjacente.jogador);
                }
            }
        }
    }

    private void capturarCarta(int x, int y, String jogadorNovo) {
        String jogadorAntigo = tabuleiro[x][y].jogador;
        tabuleiro[x][y].jogador = jogadorNovo;

        // Ajustar a pontuação
        if (jogadorNovo.equals("verde")) {
            pontosVerde++;
            pontosAzul--;
        } else {
            pontosAzul++;
            pontosVerde--;
        }

        System.out.println("A carta na posição (" + x + ", " + y + ") foi capturada pelo jogador " + jogadorNovo + "!");
    }

    public void mostrarTabuleiro() {
        System.out.println("Tabuleiro atual:");
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                if (tabuleiro[i][j] != null) {
                    System.out.print("[" + tabuleiro[i][j].toString() + "] ");
                } else {
                    System.out.print("[ ] ");
                }
            }
            System.out.println();
        }
        System.out.println("Pontos - Verde: " + pontosVerde + ", Azul: " + pontosAzul);
    }

    public boolean jogoTerminado() {
        if (pontosVerde >= 9) {
            System.out.println("O jogador Verde ganhou!");
            return true;
        } else if (pontosAzul >= 9) {
            System.out.println("O jogador Azul ganhou!");
            return true;
        }
        return false;
    }
}

/*
 * public class Tabuleiro {
 * Carta[][] tabuleiro = new Carta[3][3];
 * int pontosVerde = 5;
 * int pontosAzul = 5;
 * 
 * public void inserirCarta(int x, int y, Carta novaCarta) {
 * if (tabuleiro[x][y] == null) {
 * tabuleiro[x][y] = novaCarta; // Insere a carta
 * 
 * // Verifica captura nas posições adjacentes
 * verificarCapturaAdjacente(x, y, novaCarta);
 * mostrarTabuleiro();
 * } else {
 * System.out.println("Posição já ocupada!");
 * }
 * }
 * 
 * private void verificarCapturaAdjacente(int x, int y, Carta novaCarta) {
 * // Verifica carta acima
 * if (x > 0 && tabuleiro[x - 1][y] != null) {
 * Carta cartaCima = tabuleiro[x - 1][y];
 * if (!cartaCima.jogador.equals(novaCarta.jogador)) {
 * // Compare C (cima) com I (atual)
 * if (novaCarta.baixo > cartaCima.cima) {
 * capturarCarta(x - 1, y, novaCarta.jogador);
 * } else if (cartaCima.cima > novaCarta.baixo) {
 * capturarCarta(x, y, cartaCima.jogador);
 * }
 * }
 * }
 * 
 * // Verifica carta abaixo
 * if (x < 2 && tabuleiro[x + 1][y] != null) {
 * Carta cartaBaixo = tabuleiro[x + 1][y];
 * if (!cartaBaixo.jogador.equals(novaCarta.jogador)) {
 * // Compare I (atual) com C (abaixo)
 * if (cartaBaixo.cima > novaCarta.baixo) {
 * capturarCarta(x + 1, y, novaCarta.jogador);
 * } else if (novaCarta.baixo > cartaBaixo.cima) {
 * capturarCarta(x, y, novaCarta.jogador);
 * }
 * }
 * }
 * 
 * // Verifica carta à esquerda
 * if (y > 0 && tabuleiro[x][y - 1] != null) {
 * Carta cartaEsquerda = tabuleiro[x][y - 1];
 * if (!cartaEsquerda.jogador.equals(novaCarta.jogador)) {
 * // Compare E (esquerda) com D (atual)
 * if (novaCarta.direita > cartaEsquerda.esquerda) {
 * capturarCarta(x, y - 1, novaCarta.jogador);
 * } else if (cartaEsquerda.esquerda > novaCarta.direita) {
 * capturarCarta(x, y, cartaEsquerda.jogador);
 * }
 * }
 * }
 * 
 * // Verifica carta à direita
 * if (y < 2 && tabuleiro[x][y + 1] != null) {
 * Carta cartaDireita = tabuleiro[x][y + 1];
 * if (!cartaDireita.jogador.equals(novaCarta.jogador)) {
 * // Compare D (atual) com E (direita)
 * if (cartaDireita.esquerda > novaCarta.direita) {
 * capturarCarta(x, y + 1, novaCarta.jogador);
 * } else if (novaCarta.direita > cartaDireita.esquerda) {
 * capturarCarta(x, y, cartaDireita.jogador);
 * }
 * }
 * }
 * }
 * 
 * private void capturarCarta(int x, int y, String jogadorNovo) {
 * String jogadorAntigo = tabuleiro[x][y].jogador;
 * tabuleiro[x][y].jogador = jogadorNovo;
 * 
 * // Ajustar a pontuação
 * if (jogadorNovo.equals("verde")) {
 * pontosVerde++;
 * pontosAzul--;
 * } else {
 * pontosAzul++;
 * pontosVerde--;
 * }
 * 
 * System.out.println("A carta na posição (" + x + ", " + y +
 * ") foi capturada pelo jogador " + jogadorNovo + "!");
 * }
 * 
 * public void mostrarTabuleiro() {
 * System.out.println("Tabuleiro atual:");
 * for (int i = 0; i < tabuleiro.length; i++) {
 * for (int j = 0; j < tabuleiro[i].length; j++) {
 * if (tabuleiro[i][j] != null) {
 * System.out.print("[" + tabuleiro[i][j].toString() + "] ");
 * } else {
 * System.out.print("[ ] ");
 * }
 * }
 * System.out.println();
 * }
 * System.out.println("Pontos - Verde: " + pontosVerde + ", Azul: " +
 * pontosAzul);
 * }
 * 
 * public boolean jogoTerminado() {
 * // Verifica se o tabuleiro está cheio
 * boolean tabuleiroCheio = true;
 * for (int i = 0; i < tabuleiro.length; i++) {
 * for (int j = 0; j < tabuleiro[i].length; j++) {
 * if (tabuleiro[i][j] == null) {
 * tabuleiroCheio = false; // Se houver uma posição vazia, o tabuleiro não está
 * cheio
 * break;
 * }
 * }
 * }
 * 
 * if (tabuleiroCheio) {
 * // Exibe quem ganhou
 * if (pontosVerde > pontosAzul) {
 * System.out.println("O jogador Verde ganhou!");
 * } else if (pontosAzul > pontosVerde) {
 * System.out.println("O jogador Azul ganhou!");
 * } else {
 * System.out.println("O jogo terminou em empate!");
 * }
 * return true; // O jogo terminou
 * }
 * 
 * return false; // O jogo não terminou
 * }
 * 
 * }
 */
