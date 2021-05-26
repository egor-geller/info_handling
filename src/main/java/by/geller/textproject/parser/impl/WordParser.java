package by.geller.textproject.parser.impl;

import by.geller.textproject.entity.ComponentType;
import by.geller.textproject.entity.impl.TextComposite;
import by.geller.textproject.parser.AbstractTextParser;

public class WordParser extends AbstractTextParser {
    private static final String WORD_SPLIT_REGEX = "(\\W+)";

    @Override
    public TextComposite parse(String data) {
        String[] words = data.split(WORD_SPLIT_REGEX);
        var composite = new TextComposite(ComponentType.WORD);
        for (String word : words) {
            TextComposite nextComposite = nextParser.parse(word);
            composite.add(nextComposite);
        }
        return composite;
    }
}
