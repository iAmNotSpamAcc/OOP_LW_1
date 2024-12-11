import java.io.IOException;

public interface InterfaceAPI {
    String getRequest();
    String getResponse(String request);
    String fetchApiResponse(String urlString) throws IOException;
}