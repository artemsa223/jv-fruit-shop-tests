package core.basesyntax.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.WriterService;
import service.impl.WriterServiceImpl;

public class WriterServiceImplTest {
    private static final String VALID_OUTPUT_FILE_PATH = "src/test/java/resources/output.csv";
    private static final String PATH_TO_NEW_FILE = "src/test/java/resources/new.csv";
    private static final String INVALID_OUTPUT_FILE_PATH =
    private static final String Report = "apple,10";
    private WriterService writerService = new WriterServiceImpl();

    @Test
    void writeToInvalidFilePath() {
        Assertions.assertThrows(RuntimeException.class, ()
                -> writerService.writeReport(INVALID_OUTPUT_FILE_PATH, Report));
    }

    @Test
    void write_Ok() {
        writerService.writeReport(VALID_OUTPUT_FILE_PATH, Report);
        try (var reader = Files.newBufferedReader(Paths.get(VALID_OUTPUT_FILE_PATH),
                StandardCharsets.UTF_8)) {
            String actualContent = reader.readLine();
            Assertions.assertEquals(Report, actualContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void reportIsNull() {
        Assertions.assertThrows(RuntimeException.class,
                () -> writerService.writeReport(VALID_OUTPUT_FILE_PATH, null));
    }

    @Test
    void pathIsNull() {
        Assertions.assertThrows(RuntimeException.class,
                () -> writerService.writeReport(null, Report));
    }

    @Test
    void createNewFileAndWrite_Ok() {
        File file = new File(PATH_TO_NEW_FILE);
        writerService.writeReport(PATH_TO_NEW_FILE, Report);
        Assertions.assertTrue(file.exists());
    }

}
