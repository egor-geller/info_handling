package by.geller.textproject.parser.impl;

import by.geller.textproject.entity.ComponentType;
import by.geller.textproject.entity.TextComponent;
import by.geller.textproject.entity.impl.CharacterLeaf;
import by.geller.textproject.entity.impl.TextComposite;
import by.geller.textproject.parser.TextParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser implements TextParser {
    private static final String WORD_REGEX = "\\w+";
    private static final String LEXEME_SPLIT_REGEX = "\\s+";
    private static final String PUNCTUATION_REGEX = "[?!.,'-]+";
    private static final String EXPRESSION_REGEX = "[()<>~|&^]+";
    private TextParser nextParse;

    public LexemeParser() {
    }

    public LexemeParser(TextParser nextParser) {
        this.nextParse = nextParser;
    }

    @Override
    public void parseText(TextComposite textComposite, String dataToParse) {
        String[] lexemes = dataToParse.split(LEXEME_SPLIT_REGEX);
        var wordPattern = Pattern.compile(WORD_REGEX);
        var punctuationPattern = Pattern.compile(PUNCTUATION_REGEX);
        var expressionPattern = Pattern.compile(EXPRESSION_REGEX);
        var componentInText = new TextComposite(ComponentType.LEXEME);

        for (String lexeme : lexemes) {
            Matcher wordMatcher = wordPattern.matcher(lexeme);
            Matcher punctuationMatcher = punctuationPattern.matcher(lexeme);
            Matcher expressionMatcher = expressionPattern.matcher(lexeme);

            TextComposite currentLexemeComponent;

            if (nextParse != null && wordMatcher.find()) {
                currentLexemeComponent = new TextComposite(ComponentType.WORD);
                nextParse.parseText(currentLexemeComponent, lexeme);
            }else{
                //TODO: how to do lexeme?????
            }
        }

    }
}
