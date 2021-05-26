package by.geller.textproject.parser.impl;

import by.geller.textproject.entity.ComponentType;
import by.geller.textproject.entity.impl.TextComposite;
import by.geller.textproject.parser.AbstractTextParser;


public class SentenceParser extends AbstractTextParser {
    private static final String SENTENCE_SPLIT_REGEX = "[.?!]";

    @Override
    public TextComposite parse(String data) {
        String[] sentences = data.split(SENTENCE_SPLIT_REGEX);
        var composite = new TextComposite(ComponentType.SENTENCE);
        for (String sentence : sentences) {
            TextComposite nextComposite = nextParser.parse(sentence);
            composite.add(nextComposite);
        }
        return composite;
    }
}
