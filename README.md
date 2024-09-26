# Jogo de Cartas Elementares

Este é um jogo de cartas para dois jogadores, onde cada jogador assume o controle de um conjunto de cartas com atributos baseados em elementos: Fogo, Água e Terra. Os jogadores competem para capturar as cartas do adversário ao colocar suas cartas no tabuleiro e utilizar as habilidades dos elementos.

## Estrutura do Projeto

O projeto é dividido em várias classes, cada uma responsável por uma parte específica do jogo:

- **`Carta`**: Representa uma carta com atributos de posição (cima, baixo, esquerda, direita) e um elemento (fogo, água ou terra).
- **`Baralho`**: Gerencia a criação de cartas e mãos para os jogadores.
- **`Tabuleiro`**: Representa o tabuleiro de jogo e gerencia a lógica de inserção e captura de cartas.
- **`JogoUtils`**: Contém métodos utilitários para facilitar a interação do usuário, como escolha de cartas e posições no tabuleiro.
- **`Main`**: Contém o ponto de entrada do jogo e a lógica principal de execução.

## Regras do Jogo

1. **Preparação do Jogo**:
   - Cada jogador recebe uma mão de 5 cartas aleatórias, que são criadas com atributos de valores e um elemento.
   - O tabuleiro é inicializado vazio, com 3 linhas e 3 colunas.

2. **Turnos**:
   - Os jogadores se alternam para jogar. O jogador Verde começa.
   - Em cada turno, um jogador deve escolher uma carta da sua mão e uma posição no tabuleiro para colocá-la.

3. **Inserção de Cartas**:
   - A carta escolhida é inserida na posição especificada no tabuleiro.
   - Se a posição já estiver ocupada, o jogador deve escolher outra posição.

4. **Captura de Cartas**:
   - Após inserir uma carta, o jogador verifica se captura cartas adjacentes do adversário.
   - A captura ocorre se:
     - O valor inferior da nova carta for maior que o valor superior da carta adjacente.
     - O valor direito da nova carta for maior que o valor esquerdo da carta adjacente.
     - O valor superior da nova carta for maior que o valor inferior da carta adjacente.
     - O valor esquerdo da nova carta for maior que o valor direito da carta adjacente.

5. **Pontuação**:
   - Cada jogador começa com 5 pontos.
   - A pontuação é ajustada a cada captura:
     - O jogador que captura uma carta ganha 1 ponto, enquanto o jogador que perdeu a carta perde 1 ponto.

6. **Final do Jogo**:
   - O jogo termina quando um jogador atinge 9 pontos.
   - O jogador que atingir 9 pontos primeiro é declarado o vencedor.

## Como Jogar

1. Clone o repositório:
   ```bash
   git clone https://github.com/montenegroleticia/D-D.git

2. Execute:
    - Rode o arquivo Main.java
