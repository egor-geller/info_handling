package by.geller.textproject.parser.impl;

import by.geller.textproject.entity.ComponentType;
import by.geller.textproject.entity.impl.TextComposite;
import by.geller.textproject.parser.TextParser;

public class SentenceParser implements TextParser {
    private static final String SENTENCE_SPLIT_REGEX = "(.+\\.+|.+!+|.+\\?)";
    private TextParser nextParse;

    public SentenceParser(TextParser nextParse){
        this.nextParse = nextParse;
    }

    @Override
    public void parseText(TextComposite textComposite, String dataToParse) {
        String[] sentences = dataToParse.split(SENTENCE_SPLIT_REGEX);

        for (String sentence : sentences) {
            TextComposite sentenceComponent = new TextComposite(ComponentType.SENTENCE);
            textComposite.add(sentenceComponent);

            if (nextParse != null) {
                nextParse.parseText(sentenceComponent, sentence);
            }
        }
    }
}
