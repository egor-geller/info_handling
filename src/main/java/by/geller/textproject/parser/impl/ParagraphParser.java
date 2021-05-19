package by.geller.textproject.parser.impl;

import by.geller.textproject.entity.ComponentType;
import by.geller.textproject.entity.impl.TextComposite;
import by.geller.textproject.parser.TextParser;

public class ParagraphParser implements TextParser {
    private static final String PARAGRAPH_SPLIT_PATTERN = "\\n+|\\t+|\\s{2,}";
    private final TextParser nextParse;

    public ParagraphParser(TextParser nextParse){
        this.nextParse = nextParse;
    }

    @Override
    public void parseText(TextComposite textComposite, String dataToParse) {
        String[] paragraphs = dataToParse.split(PARAGRAPH_SPLIT_PATTERN);
        for (String paragraph : paragraphs) {
            TextComposite paragraphContent = new TextComposite(ComponentType.PARAGRAPH);
            textComposite.add(paragraphContent);

            if (nextParse != null){
                nextParse.parseText(paragraphContent, paragraph);
            }
        }
    }
}
