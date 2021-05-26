package service;

import by.geller.textproject.entity.TextComponent;
import by.geller.textproject.entity.impl.TextComposite;
import by.geller.textproject.exception.TextException;
import by.geller.textproject.parser.impl.*;
import by.geller.textproject.reader.ReadText;
import by.geller.textproject.service.OperationsOnTextService;
import by.geller.textproject.service.impl.OperationsOnTextServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperationsOnTextServiceImplTest {
    static final Logger logger = LogManager.getLogger();

    private String readText(String pathToFile) {
        var textFromFile = new StringBuilder();
        var readText = new ReadText();
        try {
            textFromFile = readText.readFromFile(pathToFile);
        } catch (TextException e) {
            e.getStackTrace();
        }
        return textFromFile.toString();
    }

    private TextComposite parseText(String data) {
        ParagraphParser paragraphParser = new ParagraphParser();
        SentenceParser sentenceParser = new SentenceParser();
        LexemeParser lexemeParser = new LexemeParser();
        WordParser wordParser = new WordParser();
        SymbolParser symbolParser = new SymbolParser();
        paragraphParser.setNextParser(sentenceParser);
        sentenceParser.setNextParser(lexemeParser);
        lexemeParser.setNextParser(wordParser);
        wordParser.setNextParser(symbolParser);
        TextComposite composite = paragraphParser.parse(data);
        return composite;
    }

    @Test
    public void paragraphSortTest() throws TextException {
        logger.info("Paragraph sort:");
        var pathToSortedFile = "src/main/resources/file/file1.txt";
        var pathToNotSortedFile = "src/main/resources/file/file2.txt";
        TextComposite composite = parseText(readText(pathToNotSortedFile));
        OperationsOnTextService operations = new OperationsOnTextServiceImpl();
        List<TextComponent> sortedParagraph = operations.paragraphSort(composite);
        List<TextComponent> expectedComposite = parseText(readText(pathToSortedFile)).getListOfChildren();
        Assert.assertEquals(expectedComposite.toString(), sortedParagraph.toString());
    }

    @Test
    public void sentenceWithTheLongestWordTest() throws TextException {
        logger.info("Sentence with the longest word:");
        var pathToFile = "src/main/resources/file/file1.txt";
        TextComposite composite = parseText(readText(pathToFile));
        OperationsOnTextService operations = new OperationsOnTextServiceImpl();
        String longestWordInASentence = operations.sentenceWithTheLongestWord(composite);

        SentenceParser sentenceParser = new SentenceParser();
        LexemeParser lexemeParser = new LexemeParser();
        WordParser wordParser = new WordParser();
        SymbolParser symbolParser = new SymbolParser();

        sentenceParser.setNextParser(lexemeParser);
        lexemeParser.setNextParser(wordParser);
        wordParser.setNextParser(symbolParser);
        TextComposite expectedComposite = sentenceParser.parse("The point of using Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c), " +
                "as opposed to using (Content here), content here's, making it look like readable English?");
        List<TextComponent> textComponentList = expectedComposite.getListOfChildren();
        Assert.assertEquals(expectedComposite.toString(), textComponentList.toString());
    }

    @Test
    public void removeSentenceUnderSomeWordsTest() throws TextException {
        String text = readText("src\\test\\java\\resources\\file\\text1.txt");
        TextComposite composite = parseText(text);
        OperationsOnTextService textService = new OperationsOnTextServiceImpl();
        List<TextComponent> actualSentences = textService.removeSentenceUnderSomeWords(composite, 3);
        SentenceParser sentenceParser = new SentenceParser();
        LexemeParser lexemeParser = new LexemeParser();
        WordParser wordParser = new WordParser();
        SymbolParser symbolParser = new SymbolParser();
        sentenceParser.setNextParser(lexemeParser);
        lexemeParser.setNextParser(wordParser);
        wordParser.setNextParser(symbolParser);
        TextComposite expectedComposite = sentenceParser.parse("Advertising can educate, too.");
        List<TextComponent> expectedSentences = expectedComposite.getListOfChildren();
        Assert.assertEquals(expectedSentences.toString(), actualSentences.toString());
    }

    @Test
    public void countSameWordsTest() {
        String text = readText("src\\test\\java\\resources\\file\\text1.txt");
        TextComposite composite = parseText(text);
        OperationsOnTextService textService = new OperationsOnTextServiceImpl();
        Map<String, Long> actualWords = textService.countSameWords(composite);
        Map<String, Long> expectedWords = new HashMap<>();
        expectedWords.put("established", 2L);
        expectedWords.put("It", 4L);
        expectedWords.put("Bye", 1L);
        Assert.assertEquals(expectedWords, actualWords);
    }

    @Test
    public void countVowelsTest() {
        LexemeParser lexemeParser = new LexemeParser();
        WordParser wordParser = new WordParser();
        SymbolParser symbolParser = new SymbolParser();
        lexemeParser.setNextParser(wordParser);
        wordParser.setNextParser(symbolParser);
        TextComposite composite = lexemeParser.parse("It has survived - not only (five) centuries");
        OperationsOnTextService textService = new OperationsOnTextServiceImpl();
        int actualCount = textService.countVowels(composite);
        int expectedCount = 12;
        Assert.assertEquals(expectedCount, actualCount);
    }

    @Test
    public void countConsonantsTest() {
        LexemeParser lexemeParser = new LexemeParser();
        WordParser wordParser = new WordParser();
        SymbolParser symbolParser = new SymbolParser();
        lexemeParser.setNextParser(wordParser);
        wordParser.setNextParser(symbolParser);
        TextComposite composite = lexemeParser.parse("It has survived - not only (five) centuries");
        OperationsOnTextService textService = new OperationsOnTextServiceImpl();
        int actualCount = textService.countConsonants(composite);
        int expectedCount = 21;
        Assert.assertEquals(expectedCount, actualCount);
    }
}
