let jogadorAtual = { color: "green-hand", cartas: [] };
const boardDiv = document.getElementById("board");
const messageDiv = document.getElementById("message");
const statusDiv = document.getElementById("status");
const pontosVerdeDiv = document.getElementById("pontosVerde");
const pontosAzulDiv = document.getElementById("pontosAzul");

// Função para obter as cartas do jogador
function carregarCartas() {
  fetch("/mao/verde")
    .then((response) => response.json())
    .then((cartas) => {
      jogadorAtual.cartas = cartas;
      renderizarCartas("green-hand", cartas);
    });

  fetch("/mao/azul")
    .then((response) => response.json())
    .then((cartas) => {
      renderizarCartas("blue-hand", cartas);
    });
}

// Função para renderizar as cartas na mão do jogador
function renderizarCartas(playerHandId, cartas) {
  const handDiv = document.getElementById(playerHandId);
  handDiv.innerHTML = "";
  cartas.forEach((carta, index) => {
    const cardDiv = document.createElement("div");
    cardDiv.classList.add("card");
    cardDiv.innerHTML = `
                    <div class="carta-titulo">Jogador: ${carta.jogador}</div>
                        <img src="${carta.imagem}" alt="${carta.elemento} Carta ${index + 1}"">
                    </div>
                    <div class="carta-atributos">
                        Cima: ${carta.cima}, Baixo: ${carta.baixo}, Esquerda: ${carta.esquerda}, Direita: ${carta.direita}
                    </div>
                `;
    if (playerHandId.includes(jogadorAtual.color)) {
      cardDiv.classList.remove("disabled");
      cardDiv.onclick = () => jogarCarta(carta, playerHandId);
    } else {
      cardDiv.classList.add("disabled"); // Adiciona uma classe para desabilitar a carta
    }
    handDiv.appendChild(cardDiv);
  });
}

// Função para atualizar o placar
function atualizarPlacar(data) {
    pontosVerdeDiv.textContent = `Verde: ${data.pontosVerde}`;
    pontosAzulDiv.textContent = `Azul: ${data.pontosAzul}`;
}

// Função para jogar a carta no tabuleiro
function jogarCarta(carta, playerHandId) {
  const x = prompt("Informe a posição X (0-2):");
  const y = prompt("Informe a posição Y (0-2):");

  if (x !== null && y !== null) {
    fetch(`/inserirCarta?jogador=${jogadorAtual.color}&x=${x}&y=${y}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(carta), // Envia a carta que foi jogada
    })
      .then((response) => {
        atualizarTabuleiro();
        jogadorAtual.color = jogadorAtual.color === "green-hand" ? "blue-hand" : "green-hand";
        carregarCartas();
      })
      .catch((error) => {
        messageDiv.textContent = error.message;
      });
  }
}

// Função para atualizar o tabuleiro
function atualizarTabuleiro() {
  fetch("/tabuleiro")
    .then((response) => response.json())
    .then((data) => {
      console.log("Resposta do servidor:", data);
      const tabuleiro = data.tabuleiro;
      const boardElement = document.getElementById("board");
      boardElement.innerHTML = ""; // Limpa o tabuleiro antes de adicionar as novas cartas

      tabuleiro.forEach((linha, linhaIndex) => {
        linha.forEach((carta, colunaIndex) => {
          const cartaElement = document.createElement("div");

          if (carta) {
            // Defina as propriedades da carta, por exemplo:
            cartaElement.classList.add("card");
            cartaElement.innerHTML = `
                            <div class="carta-titulo">Jogador: ${carta.jogador}</div>
                                <img src="${carta.imagem}" alt="${carta.elemento}">
                            </div>
                            <div class="carta-atributos">
                                Cima: ${carta.cima}, Baixo: ${carta.baixo}, Esquerda: ${carta.esquerda}, Direita: ${carta.direita}
                            </div>
                        `;
          } else {
            cartaElement.innerHTML = `<div class="carta-vazia"></div>`;
          }

          // Adiciona a carta ao tabuleiro
          boardElement.appendChild(cartaElement);
          // Atualiza o placar
          atualizarPlacar(data);
        });
      });
    })
    .catch((error) => console.error("Erro ao atualizar o tabuleiro:", error));
}

// Função para obter o status do jogo e atualizar a mensagem
function atualizarStatus() {
  fetch("/status")
    .then((response) => response.json())
    .then((status) => {
      statusDiv.textContent = status;
    })
    .catch((error) => {
      console.error("Erro ao obter o status do jogo:", error);
      statusDiv.textContent = "Erro ao obter o status do jogo";
    });
}

// Carrega as cartas ao iniciar o jogo
(window.onload = carregarCartas()),
  atualizarTabuleiro(),
  carregarCartas(),
  atualizarStatus();
  atualizarPlacar();
