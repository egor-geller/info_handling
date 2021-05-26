package by.geller.textproject.parser.impl;

import by.geller.textproject.entity.ComponentType;
import by.geller.textproject.entity.impl.TextComposite;
import by.geller.textproject.parser.AbstractTextParser;

public class ParagraphParser extends AbstractTextParser {
    private static final String PARAGRAPH_SPLIT_PATTERN = "\\s{2,}";

    @Override
    public TextComposite parse(String data) {
        String[] paragraphs = data.split(PARAGRAPH_SPLIT_PATTERN);
        var composite = new TextComposite(ComponentType.PARAGRAPH);
        for (String paragraph : paragraphs) {
            TextComposite nextComposite = nextParser.parse(paragraph);
            composite.add(nextComposite);
        }
        return composite;
    }
}
