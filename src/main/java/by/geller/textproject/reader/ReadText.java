package by.geller.textproject.reader;

import by.geller.textproject.exception.TextException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadText {
    private static final Logger logger = LogManager.getLogger();
    private static final String ERROR_IN_READING = "Problem in reading from file";

    public StringBuilder readFromFile(String pathToFile) throws TextException {
        var someText = new StringBuilder();
        try {
            someText.append(Files.readString(Paths.get(pathToFile)));
        }catch (IOException e){
            logger.error(ERROR_IN_READING);
            throw new TextException(ERROR_IN_READING);
        }
        return someText;
    }
}
