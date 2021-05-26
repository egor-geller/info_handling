package by.geller.textproject.parser.impl;

import by.geller.textproject.entity.ComponentType;
import by.geller.textproject.entity.impl.CharacterLeaf;
import by.geller.textproject.entity.impl.TextComposite;
import by.geller.textproject.parser.AbstractTextParser;

public class SymbolParser extends AbstractTextParser {
    @Override
    public TextComposite parse(String data) {
        char[] letter = data.toCharArray();
        var composite = new TextComposite(ComponentType.SYMBOL);
        for (char letterValue : letter) {
            var characterLeaf = new CharacterLeaf(Character.toString(letterValue));
            composite.add(characterLeaf);
        }
        return composite;
    }
}
