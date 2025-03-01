import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    //1. Создание пути установки
    private static String basePath = "C:\\Games";
    //2. Создание структуры директорий
    private static List<String> basePaths = Arrays.asList(
            "\\src",
            "\\res",
            "\\savegames",
            "\\temp",
            "\\src\\main",
            "\\src\\test",
            "\\res\\drawables",
            "\\res\\vectors",
            "\\res\\icons");
    //3. Создание структуры файлов
    private static List<String> baseFiles = Arrays.asList(
            "\\src\\main\\Main.java",
            "\\src\\main\\Utils.java");


    public static void main(String[] args) {
        //1. Создание лога
        StringBuilder log = new StringBuilder();

        //2. Создание пути установки
        installGame(basePath, log);

        //3.Создание директорий
        for (String name : basePaths) {
            createDirectory(log, new File(basePath + name), name);
        }

        //4. Создание файлов
        for (String name : baseFiles) {
            createNewFile(log, new File(basePath + name), name);
        }

        //5. Запись логов
        writeLogToFile(new File(basePath + "\\temp\\temp.txt"), log.toString());

    }

    //1. Создание установочной директории
    private static void installGame(String basePath, StringBuilder log) {
        File gamesDir = new File(basePath);
        if (!gamesDir.exists()) {
            boolean created = gamesDir.mkdir();
            log.append(getTimestamp()).append("Создание папки 'Games': ").append(created ? "успех" : "неудача").append("\n");
        } else {
            log.append("Папка 'Games' уже существует.\n");
        }
    }

    //2. Получение времени создания строки
    private static String getTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm:ss");
        return "[" + now.format(formatter) + "] ";
    }

    //3. Создане фала с логами.
    private static void writeLogToFile(File logFile, String log) {
        try (FileWriter writer = new FileWriter(logFile)) {
            writer.write(log);
        } catch (IOException e) {
            System.out.println("Ошибка записи лога в файл: " + e.getMessage());
        }
    }

    //4. Создание директории
    private static void createDirectory(StringBuilder log, File dir, String name) {
        if (!dir.exists()) {
            boolean created = dir.mkdir();
            log.append(getTimestamp()).append("Создана папка '").append(name).append("': ").append(created ? "успех" : "неудача").append("\n");
        } else {
            log.append(getTimestamp()).append("Папка '").append(name).append("' уже существует.\n");
        }
    }

    private static void createNewFile(StringBuilder log, File file, String name) {
        if (!file.exists()) {
            try {
                boolean created = file.createNewFile();
                log.append(getTimestamp()).append("Создан файл '").append(name).append("': ").append(created ? "успех" : "неудача").append("\n");
            } catch (IOException e) {
                log.append(getTimestamp()).append("Ошибка создания файла '").append(name).append("': ").append(e.getMessage()).append("\n");
            }
        } else {
            log.append(getTimestamp()).append("Файл '").append(name).append("' уже существует.\n");
        }
    }
}