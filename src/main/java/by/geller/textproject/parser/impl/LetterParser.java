package by.geller.textproject.parser.impl;

import by.geller.textproject.entity.impl.CharacterLeaf;
import by.geller.textproject.entity.ComponentType;
import by.geller.textproject.entity.impl.TextComposite;
import by.geller.textproject.parser.TextParser;

public class LetterParser implements TextParser {
    @Override
    public void parseText(TextComposite textComposite, String dataToParse) {
        char[] letter = dataToParse.toCharArray();

        for (char letterValue : letter) {
            CharacterLeaf characterLeaf = new CharacterLeaf(letterValue, ComponentType.LETTER);
            textComposite.add(characterLeaf);
        }
    }
}
