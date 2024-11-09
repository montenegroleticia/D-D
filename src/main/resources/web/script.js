let jogadorAtual = { color: "green-hand" };
const boardDiv = document.getElementById("board");
const messageDiv = document.getElementById("message");
const statusDiv = document.getElementById("status");
const pontosVerdeDiv = document.getElementById("pontosVerde");
const pontosAzulDiv = document.getElementById("pontosAzul");
const musicCard = new Audio(
  "music/TripleTriad_src_main_resources_card-placed.wav"
);
const musicGame = new Audio("music/theme-loop.wav");

const log = document.getElementById("game-log");
log.style.display = "none";
const main = document.getElementById("main");
main.style.display = "none";

// Iniciar o jogo
const startButton = document.getElementById("start-button");
startButton.onclick = () => {
  musicGame.play();
  startButton.style.display = "none";
  log.style.display = "flex";
  main.style.display = "flex";
  carregarCartas();
  atualizarTabuleiro();
  atualizarStatus();
  atualizarPlacar();
};

// Pausar/tocar a música
const pauseButton = document.getElementById("pause-button");
pauseButton.onclick = () => {
  if (musicGame.paused) {
    musicGame.play();
    pauseButton.textContent = "Pausar Música";
  } else {
    musicGame.pause();
    pauseButton.textContent = "Tocar Música";
  }
};

// Função fim de jogo
function fimDeJogo() {
  fetch("/fim-de-jogo")
    .then((response) => response.json())
    .then((data) => {
      statusDiv.textContent = "O Jogo acabou!";
      atualizarPlacar(data);
      if (data.pontosAzul > data.pontosVerde) {
        messageDiv.textContent = "O jogador Azul venceu!";
      } else if (data.pontosVerde > data.pontosAzul) {
        messageDiv.textContent = "O jogador Verde venceu!";
      }
    });
}

// Função para obter as cartas do jogador
function carregarCartas() {
  fetch("/mao/verde")
    .then((response) => response.json())
    .then((cartas) => {
      if (cartas.length === 0) {
        fimDeJogo();
      }
      renderizarCartas("green-hand", cartas);
    });

  fetch("/mao/azul")
    .then((response) => response.json())
    .then((cartas) => {
      if (cartas.length === 0) {
        fimDeJogo();
      }
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
                    <img src="${carta.img}" alt="${carta.elemento} Carta ${index + 1}">
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
    musicCard.play();
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
    fetch(`/inserirCarta?jogador=${carta.jogador}&x=${x}&y=${y}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(carta), // Envia a carta que foi jogada
    })
      .then((response) => {
        atualizarTabuleiro();
        jogador = playerHandId.includes("green") ? "Verde" : "Azul";
        messageDiv.textContent = `Carta jogada pelo jogador ${jogador} sucesso!`;
        jogadorAtual.color =
          jogadorAtual.color === "green-hand" ? "blue-hand" : "green-hand";
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
      const tabuleiro = data.tabuleiro;
      const boardElement = document.getElementById("board");
      boardElement.innerHTML = ""; // Limpa o tabuleiro antes de adicionar as novas cartas
      console.log(data);
      tabuleiro.forEach((linha, linhaIndex) => {
        linha.forEach((carta, colunaIndex) => {
          const cartaElement = document.createElement("div");

          if (carta) {
            // Defina as propriedades da carta
            cartaElement.classList.add("card");
            cartaElement.innerHTML = `
                            <img src="${carta.img}" alt="${carta.elemento}">
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
