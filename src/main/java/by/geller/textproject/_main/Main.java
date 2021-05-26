package by.geller.textproject._main;

import by.geller.textproject.exception.TextException;
import by.geller.textproject.parser.impl.*;
import by.geller.textproject.reader.ReadText;
import by.geller.textproject.service.OperationsOnTextService;
import by.geller.textproject.service.impl.OperationsOnTextServiceImpl;
import org.apache.logging.log4j.LogManager;

public class Main {
    public static void main(String[] args) {
        final var logger = LogManager.getLogger();
        var pathToFile = "src/main/resources/file/file1.txt";
        var textFromFile = new StringBuilder();
        var readText = new ReadText();
        try {
            textFromFile = readText.readFromFile(pathToFile);
        } catch (TextException e) {
            e.getStackTrace();
        }

        var symbolParser = new SymbolParser();
        var wordParser = new WordParser();
        var lexemeParser = new LexemeParser();
        var sentenceParser = new SentenceParser();
        var paragraphParser = new ParagraphParser();

        paragraphParser.setNextParser(sentenceParser);
        sentenceParser.setNextParser(lexemeParser);
        lexemeParser.setNextParser(wordParser);
        wordParser.setNextParser(symbolParser);

        var composite = wordParser.parse(textFromFile.toString());

        logger.info(composite);
        System.out.println("Word ==============================================");
        System.out.println(composite);
        System.out.println("Operations ==============================================");

        OperationsOnTextService operations = new OperationsOnTextServiceImpl();

        System.out.println(operations.countVowels(composite));

    }
}
