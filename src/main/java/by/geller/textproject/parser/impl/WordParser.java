package by.geller.textproject.parser.impl;

import by.geller.textproject.entity.ComponentType;
import by.geller.textproject.entity.impl.TextComposite;
import by.geller.textproject.parser.TextParser;

public class WordParser implements TextParser {
    private static final String WORD_SPLIT_REGEX = "(\\w+)";
    private TextParser nextParse;

    public WordParser(TextParser nextParse){
        this.nextParse = nextParse;
    }

    @Override
    public void parseText(TextComposite textComposite, String dataToParse) {
        String[] sentences = dataToParse.split(WORD_SPLIT_REGEX);

        for (String sentence : sentences) {
            var sentenceComponent = new TextComposite(ComponentType.WORD);
            textComposite.add(sentenceComponent);

            if (nextParse != null) {
                nextParse.parseText(sentenceComponent, sentence);
            }
        }
    }
}
