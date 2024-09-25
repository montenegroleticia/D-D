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

    // Método para verificar se a posição está livre
    public boolean posicaoLivre(int x, int y) {
        return tabuleiro[x][y] == null;
    }
}
