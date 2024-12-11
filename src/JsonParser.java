import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.Scanner;

public class JsonParser implements InterfaceParser {
    @Override
    public void parseAndOpenLink(String apiResponse) {
        JsonArray searchArray = parseApiResponse(apiResponse);
        int choice = getUserChoice(searchArray.size());
        linkOpener(searchArray, choice);
    }

    private JsonArray parseApiResponse(String apiResponse){
        Gson gson = new Gson();
        JsonObject rootObject = gson.fromJson(apiResponse, JsonObject.class);
        JsonObject queryObject = rootObject.getAsJsonObject("query");
        return  queryObject.getAsJsonArray("search");
    }

    private int getUserChoice(int searchSize){
        Scanner in = new Scanner(System.in);
        while(true){
            System.out.print("Выберите номер (от 1 до " + searchSize + "): ");
            int choice = in.nextInt() - 1;
            if(choice >= 0 && choice < searchSize){
                return choice;
            }else{
                System.out.println("Некорректный выбор. Попробуйте еще раз!");
            }
        }
    }

    private void linkOpener(JsonArray searchArray, int choice){
        JsonObject result = searchArray.get(choice).getAsJsonObject();
        String pageid = result.get("pageid").getAsString();
        String finalLink = "https://ru.wikipedia.org/w/index.php?curid=" + pageid;
        WebPageOpener.open(finalLink);
    }
}
