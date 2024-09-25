package ProjetoParadigmas;


public class Carta {
    int cima;
    int baixo;
    int esquerda;
    int direita;
    String jogador; // "verde" ou "azul"
    String elemento; // "fogo", "agua", "terra"

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
        return "[" + jogador.charAt(0) + ":C-" + cima + ",E-" + esquerda + ",D-" + baixo + ",I-" + direita + ", " + elemento + "]";
    }

    public void aumentarValores() {
        cima++;
        baixo++;
        esquerda++;
        direita++;
    }
}

/*public class Carta {
    int cima;
    int baixo;
    int esquerda;
    int direita;
    String jogador; // "verde" ou "azul"
    String elemento; // "fogo", "agua", "terra"

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
        return "[" + jogador.charAt(0) + ":C-" + cima + ",E-" + direita + ",D-" + baixo + ",I-" + esquerda + ", " + elemento + "]";
    }

    public void aumentarValores() {
        cima++;
        baixo++;
        esquerda++;
        direita++;
    }
}*/
