import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class WebPageOpener {
    public static void open(String link) {
        if (Desktop.isDesktopSupported()) {
            Desktop desk = Desktop.getDesktop();
            try {
                desk.browse(new URI(link));
                System.out.println("URL успешно открыт: " + link);
            } catch (IOException e) {
                System.err.println("Ошибка при открытии URL: " + e.getMessage());
            } catch (URISyntaxException e) {
                System.err.println("Некорректный URL: " + e.getMessage());
            }
        } else {
            System.err.println("Desktop API не поддерживается в текущей среде.");
        }
    }
}
