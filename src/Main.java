import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        InterfaceAPI wikiApi = new WikiAPI();
        InterfaceParser jsonParse = new JsonParser();
        String request = wikiApi.getRequest();
        String urlResponse = wikiApi.getResponse(request);
        String apiResponse = wikiApi.fetchApiResponse(urlResponse);
        jsonParse.parseAndOpenLink(apiResponse);
    }
}