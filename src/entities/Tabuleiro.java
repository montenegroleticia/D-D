package src.entities;

public class Tabuleiro {
    private Carta[][] tabuleiro = new Carta[3][3];
    private int pontosVerde = 5;
    private int pontosAzul = 5;

    public void inserirCarta(int x, int y, Carta novaCarta) {
        if (tabuleiro[x][y] == null) {
            // Incrementa valores se houver cartas do mesmo elemento
            for (Carta[] linha : tabuleiro) {
                for (Carta carta : linha) {
                    if (carta != null && carta.getElemento().equals(novaCarta.getElemento())) {
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
            if (!cartaAdjacente.getJogador().equals(novaCarta.getJogador())) {
                // Captura se o valor for maior
                if (novaCarta.getBaixo() > cartaAdjacente.getCima()) {
                    capturarCarta(x, y, novaCarta.getJogador());
                } else if (novaCarta.getDireita() > cartaAdjacente.getEsquerda()) {
                    capturarCarta(x, y, novaCarta.getJogador());
                } else if (cartaAdjacente.getCima() > novaCarta.getBaixo()) {
                    capturarCarta(x, y, cartaAdjacente.getJogador());
                } else if (cartaAdjacente.getEsquerda() > novaCarta.getDireita()) {
                    capturarCarta(x, y, cartaAdjacente.getJogador());
                }
            }
        }
    }

    private void capturarCarta(int x, int y, String jogadorNovo) {
        String jogadorAntigo = tabuleiro[x][y].getJogador();
        tabuleiro[x][y].setJogador(jogadorNovo);

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
