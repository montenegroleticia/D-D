import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.port;
import static spark.Spark.staticFiles;
import com.google.gson.Gson;

import entities.Baralho;
import entities.Carta;
import entities.Tabuleiro;

public class JogoApi {
    private static Baralho baralho = new Baralho();
    private static Tabuleiro tabuleiro = new Tabuleiro();
    private static Gson gson = new Gson();

    public static void main(String[] args) {
        port(3000); // Define a porta para o servidor
        staticFiles.externalLocation("src/main/resources/web"); // Serve arquivos estáticos da pasta web

        // Endpoint para criar mão do jogador "verde"
        get("/mao/verde", (req, res) -> {
            res.type("application/json");
            return gson.toJson(baralho.criarMaoVerde());
        });

        // Endpoint para criar mão do jogador "azul"
        get("/mao/azul", (req, res) -> {
            res.type("application/json");
            return gson.toJson(baralho.criarMaoAzul());
        });

        // Endpoint para inserir uma carta no tabuleiro
        post("/inserirCarta", (req, res) -> {
            String jogador = req.queryParams("jogador");
            int x = Integer.parseInt(req.queryParams("x"));
            int y = Integer.parseInt(req.queryParams("y"));
            Carta carta = gson.fromJson(req.body(), Carta.class);

            if (tabuleiro.posicaoLivre(x, y)) {
                tabuleiro.inserirCarta(x, y, carta);
                return "Carta inserida com sucesso.";
            } else {
                res.status(400);
                return "Posição já ocupada!";
            }
        });

        // Endpoint para verificar o status do jogo
        get("/status", (req, res) -> {
            res.type("application/json");
            return gson.toJson(tabuleiro.jogoTerminado() ? "Jogo terminado!" : "Jogo em andamento");
        });

        // Endpoint para mostrar o tabuleiro
        get("/tabuleiro", (req, res) -> {
            res.type("application/json");
            return gson.toJson(tabuleiro);
        });
    }
}
