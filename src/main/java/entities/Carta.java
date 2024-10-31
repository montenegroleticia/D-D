package entities;

public class Carta {
    private int cima;
    private int baixo;
    private int esquerda;
    private int direita;
    private String jogador; 
    private String elemento; 

    public Carta(int cima, int baixo, int esquerda, int direita, String jogador, String elemento) {
        this.cima = cima;
        this.baixo = baixo;
        this.esquerda = esquerda;
        this.direita = direita;
        this.jogador = jogador;
        this.elemento = elemento;
    }

    @Override
    public String toString() {
        return String.format("[%s:C-%d,E-%d,D-%d,I-%d,%s]", 
                              jogador.charAt(0), cima, esquerda, baixo, direita, elemento);
    }

    public void aumentarValores() {
        cima++;
        baixo++;
        esquerda++;
        direita++;
    }

    public int getCima() {
        return cima;
    }

    public int getBaixo() {
        return baixo;
    }

    public int getEsquerda() {
        return esquerda;
    }

    public int getDireita() {
        return direita;
    }

    public String getJogador() {
        return jogador;
    }

    public String getElemento() {
        return elemento;
    }

    public void setJogador(String jogador) {
        this.jogador = jogador;
    }
}
