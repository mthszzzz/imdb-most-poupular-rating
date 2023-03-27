import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
public class App {
    public static void main(String[] args) throws Exception{
        String url =
                "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
        HttpClient cliente =
                HttpClient.newHttpClient();
        HttpRequest requisicao =
                HttpRequest.newBuilder(URI.create(url)).GET().build();
        HttpResponse<String> resposta =
                cliente.send(requisicao, BodyHandlers.ofString());

        String body = resposta.body();

        JsonParser parser = new JsonParser();
        List<Map<String, String>> listadeFilmes = parser.parse(body);
        System.out.println(listadeFilmes.size());

        for (Map<String,String> filme : listadeFilmes) {
            System.out.println("\u001b[1mTítulo:\u001b[m "+ filme.get("title"));
            System.out.println("\u001b[1mURL:\u001b[m "+ filme.get("image"));
            System.out.println("\u001b[1mRating:\u001b[m "+ filme.get("imDbRating"));
            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            int numeroEstrelas = (int) classificacao;
            for (int n = 1; n <= numeroEstrelas; n++) {
                if (numeroEstrelas > 6) {
                    System.out.print("⭐");
                }
                else {
                    System.out.print("🍅");
                }
            }
            System.out.println("\n");
        }



    }
}
