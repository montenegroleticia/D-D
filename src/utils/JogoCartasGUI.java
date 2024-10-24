package src.utils;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

import src.entities.Baralho;
import src.entities.Carta;
import src.entities.Tabuleiro;

public class JogoCartasGUI extends JFrame {
    private static final int TAMANHO_TABULEIRO = 3;
    private Tabuleiro tabuleiro;
    private List<Carta> maoVerde;
    private List<Carta> maoAzul;
    private JButton[][] botoesTabuleiro;
    private JComboBox<String> cartasVerdeComboBox;
    private JComboBox<String> cartasAzulComboBox;
    private JLabel jogadorAtualLabel;

    private String jogadorAtual = "Verde";

    public JogoCartasGUI() {
        setTitle("Jogo de Cartas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(new BorderLayout());

        // Inicializa componentes do jogo
        Baralho baralho = new Baralho();
        maoVerde = baralho.criarMaoVerde();
        maoAzul = baralho.criarMaoAzul();
        tabuleiro = new Tabuleiro();
        botoesTabuleiro = new JButton[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];

        // Painel do tabuleiro
        JPanel tabuleiroPanel = new JPanel();
        tabuleiroPanel.setLayout(new GridLayout(TAMANHO_TABULEIRO, TAMANHO_TABULEIRO));

        // Adiciona botões do tabuleiro
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                botoesTabuleiro[i][j] = new JButton("");
                final int linha = i;
                final int coluna = j;
                botoesTabuleiro[i][j].addActionListener(e -> jogarCarta(linha, coluna));
                tabuleiroPanel.add(botoesTabuleiro[i][j]);
            }
        }

        // Painel de informações dos jogadores
        JPanel jogadorPanel = new JPanel();
        jogadorPanel.setLayout(new GridLayout(3, 2));

        jogadorAtualLabel = new JLabel("Jogador Atual: " + jogadorAtual);
        jogadorPanel.add(jogadorAtualLabel);

        // Exibir mão do jogador Verde
        JLabel maoVerdeLabel = new JLabel("Mão do Verde:");
        jogadorPanel.add(maoVerdeLabel);
        cartasVerdeComboBox = new JComboBox<>();
        for (Carta carta : maoVerde) {
            cartasVerdeComboBox.addItem(carta.toString());
        }
        jogadorPanel.add(cartasVerdeComboBox);

        // Exibir mão do jogador Azul
        JLabel maoAzulLabel = new JLabel("Mão do Azul:");
        jogadorPanel.add(maoAzulLabel);
        cartasAzulComboBox = new JComboBox<>();
        for (Carta carta : maoAzul) {
            cartasAzulComboBox.addItem(carta.toString());
        }
        jogadorPanel.add(cartasAzulComboBox);

        // Adiciona painéis ao frame
        add(tabuleiroPanel, BorderLayout.CENTER);
        add(jogadorPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Método para realizar a jogada de acordo com o jogador atual
    private void jogarCarta(int linha, int coluna) {
        if (tabuleiro.jogoTerminado()) {
            JOptionPane.showMessageDialog(this, "O jogo terminou!");
            return;
        }

        if (!JogoUtils.posicaoValida(new int[] { linha, coluna }, tabuleiro)) {
            JOptionPane.showMessageDialog(this, "Posição inválida ou ocupada. Tente novamente.");
            return;
        }

        Carta cartaEscolhida;
        if (jogadorAtual.equals("Verde")) {
            int escolhaCarta = cartasVerdeComboBox.getSelectedIndex();
            cartaEscolhida = maoVerde.get(escolhaCarta);
        } else {
            int escolhaCarta = cartasAzulComboBox.getSelectedIndex();
            cartaEscolhida = maoAzul.get(escolhaCarta);
        }

        tabuleiro.inserirCarta(linha, coluna, cartaEscolhida);
        botoesTabuleiro[linha][coluna].setText(cartaEscolhida.toString());

        alternarJogador();
    }

    // Alterna entre os jogadores Verde e Azul
    private void alternarJogador() {
        if (jogadorAtual.equals("Verde")) {
            jogadorAtual = "Azul";
        } else {
            jogadorAtual = "Verde";
        }
        jogadorAtualLabel.setText("Jogador Atual: " + jogadorAtual);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JogoCartasGUI());
    }
}
