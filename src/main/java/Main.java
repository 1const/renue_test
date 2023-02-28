import com.akmal.searcher.CSVSearcher;
import com.akmal.ui.Menu;
import com.akmal.utils.FileReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        int columnNumber = -1;

        try {
            if (args.length > 0) columnNumber = Integer.parseInt(args[0]);

            if (columnNumber < 1) {
                System.out.println("Параметр должен быть положительным целочисленным значением.");
                return;
            }

            String path = inputFilePath();
            CSVSearcher csvSearcher = new CSVSearcher(path, columnNumber);
            FileReader fileReader = new FileReader(new File(path));
            Menu menu = new Menu(csvSearcher, fileReader);
            menu.start();

        } catch (NumberFormatException e) {
            System.out.println("Параметр должен быть положительным целочисленным значением.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static String inputFilePath() {
        String filePath = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Введите путь к файлу: ");
            filePath = scanner.next();
            if (Files.isRegularFile(Path.of(filePath))) break;
            System.out.println("Файла по введённому пути не существует! Повторите попытку!");
        }
        return filePath;
    }
}
