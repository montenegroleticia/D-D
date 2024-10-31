const jogadorAtual = { color: 'verde', cartas: [] };
const boardDiv = document.getElementById('board');
const messageDiv = document.getElementById('message');

// Função para obter as cartas do jogador
function carregarCartas() {
    fetch('/mao/verde')
        .then(response => response.json())
        .then(cartas => {
            jogadorAtual.cartas = cartas;
            renderizarCartas('green-hand', cartas);
        });

    fetch('/mao/azul')
        .then(response => response.json())
        .then(cartas => {
            renderizarCartas('blue-hand', cartas);
        });
}

// Função para renderizar as cartas na mão do jogador
function renderizarCartas(playerHandId, cartas) {
    const handDiv = document.getElementById(playerHandId);
    handDiv.innerHTML = '';
    cartas.forEach((carta, index) => {
        const cardDiv = document.createElement('div');
        cardDiv.classList.add('card');
        cardDiv.innerHTML = `<img src="${carta.imagem}" alt="Carta ${index + 1}">`; // Atributo imagem da classe Carta
        cardDiv.onclick = () => jogarCarta(carta, playerHandId);
        handDiv.appendChild(cardDiv);
    });
}

// Função para jogar a carta no tabuleiro
function jogarCarta(carta, playerHandId) {
    const x = prompt('Informe a posição X (0-5):');
    const y = prompt('Informe a posição Y (0-5):');

    if (x !== null && y !== null) {
        fetch(`/inserirCarta?jogador=${jogadorAtual.color}&x=${x}&y=${y}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(carta) // Envia a carta que foi jogada
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao inserir carta: ' + response.statusText);
            }
            return response.text();
        })
        .then(message => {
            messageDiv.textContent = message;
            atualizarTabuleiro();
        })
        .catch(error => {
            console.error(error);
            messageDiv.textContent = error.message;
        });
    }
}

// Função para atualizar o tabuleiro após uma jogada
function atualizarTabuleiro() {
    fetch('/tabuleiro')
        .then(response => response.json())
        .then(tabuleiro => {
            // Aqui você pode renderizar a lógica para mostrar as cartas no tabuleiro
            // Exemplo: Crie uma matriz de elementos para representar o tabuleiro
        });
}

// Carrega as cartas ao iniciar o jogo
window.onload = carregarCartas;
