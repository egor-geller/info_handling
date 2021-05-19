package by.geller.textproject._main;


import by.geller.textproject.entity.ComponentType;

import by.geller.textproject.entity.impl.TextComposite;
import by.geller.textproject.exception.TextException;
import by.geller.textproject.parser.impl.*;
import by.geller.textproject.reader.ReadText;

public class Main {
    public static void main(String[] args) {
        //TODO: how to read to chain?
        var pathToFile = "src/main/resources/file/file1.txt";
        StringBuilder textFromFile = new StringBuilder();
        var readText = new ReadText();
        try {
            textFromFile = readText.readFromFile(pathToFile);
        } catch (TextException e) {
            e.getStackTrace();
        }

        LetterParser letterParser = new LetterParser();
        WordParser wordParser = new WordParser(letterParser);
        LexemeParser lexemeParser = new LexemeParser(letterParser);
        SentenceParser sentenceParser = new SentenceParser(wordParser);
        ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);

        TextComposite component = new TextComposite(ComponentType.TEXT);
        paragraphParser.parseText(component, textFromFile.toString());
        System.out.println(paragraphParser);

    }
}
