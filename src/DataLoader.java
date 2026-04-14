import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class DataLoader {

    public static Map<String, List<String>> loadData(String basePath) throws IOException {

        Map<String, List<String>> data = new HashMap<>();

        Path baseDir = Paths.get(basePath);

        try (DirectoryStream<Path> languageDirs = Files.newDirectoryStream(baseDir)) {

            for (Path languageDir : languageDirs) {

                if (!Files.isDirectory(languageDir)) continue;

                String language = languageDir.getFileName().toString();
                List<String> texts = new ArrayList<>();


                try (DirectoryStream<Path> files = Files.newDirectoryStream(languageDir, "*.txt")) {

                    for (Path file : files) {
                        String content = Files.readString(file);
                        texts.add(content);
                    }
                }

                data.put(language, texts);
            }
        }

        return data;
    }
}