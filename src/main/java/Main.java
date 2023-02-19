import com.akmal.searcher.CSVSearcher;
import com.akmal.ui.Menu;
import com.akmal.utils.FileReader;

import java.io.File;
import java.io.IOException;

public class Main {
    private static final String PATH = "src\\main\\resources\\airports.csv";

    public static void main(String[] args) throws IOException {
        int columnNumber = -1;

        try {
            if (args.length > 0) columnNumber = Integer.parseInt(args[0]);

            if (columnNumber < 1) {
                System.out.println("Параметр должен быть положительным целочисленным значением.");
                return;
            }

            CSVSearcher csvSearcher = new CSVSearcher(PATH, columnNumber);
            FileReader fileReader = new FileReader(new File(PATH));
            Menu menu = new Menu(csvSearcher, fileReader);
            menu.start();

        } catch (NumberFormatException e) {
            System.out.println("Параметр должен быть положительным целочисленным значением.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
