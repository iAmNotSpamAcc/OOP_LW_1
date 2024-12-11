import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class WikiAPI implements InterfaceAPI {
    @Override
    public String getRequest() {
        System.out.print("Ожидается ввод данных: ");
        Scanner data = new Scanner(System.in);
        return data.nextLine();
    }

    @Override
    public String getResponse(String request) {
        String encodedRequest = percentEncode(request);
        return "https://ru.wikipedia.org/w/api.php?action=query&list=search&utf8=&format=json&srsearch=" +
                encodedRequest + "\"";
    }

    @Override
    public String fetchApiResponse(String urlString) throws IOException {
        HttpURLConnection connection = getConnect(urlString);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        } finally {
            connection.disconnect();
        }
    }

    private String percentEncode(String toEncode){
        return URLEncoder.encode(toEncode, StandardCharsets.UTF_8);
    }

    private HttpURLConnection getConnect(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        return connection;
    }
}
