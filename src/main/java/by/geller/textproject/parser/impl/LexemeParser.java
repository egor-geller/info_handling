package by.geller.textproject.parser.impl;

import by.geller.textproject.entity.ComponentType;
import by.geller.textproject.entity.impl.CharacterLeaf;
import by.geller.textproject.entity.impl.TextComposite;
import by.geller.textproject.parser.AbstractTextParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser extends AbstractTextParser {
    private static final String WORD_REGEX = "[\\w&&[^\\d]]+|[аБбВвГгДдЕеёЖжЗзИиЙйКкЛлМмнОоПпРрСТтУуФфХхЦцЧчШшЩщЪъЫыЬьЭЮюЯ]+";
    private static final String NUMBER_REGEX = "\\d+";
    private static final String LEXEME_SPLIT_REGEX = "\\s+";
    private static final String PUNCTUATION_REGEX = "[?!.,'-]+";
    private static final String EXPRESSION_REGEX = "[()<>~|&^]+";

    @Override
    public TextComposite parse(String data) {
        var composite = new TextComposite(ComponentType.LEXEME);
        String[] lexemes = data.split(LEXEME_SPLIT_REGEX);

        var numberPattern = Pattern.compile(NUMBER_REGEX);
        var wordPattern = Pattern.compile(WORD_REGEX);
        var punctuationPattern = Pattern.compile(PUNCTUATION_REGEX);
        var expressionPattern = Pattern.compile(EXPRESSION_REGEX);

        for (String lexeme : lexemes) {
            var wordMatcher = wordPattern.matcher(lexeme);
            var punctuationMatcher = punctuationPattern.matcher(lexeme);
            var expressionMatcher = expressionPattern.matcher(lexeme);
            var numberMatcher = numberPattern.matcher(lexeme);

            lexemeMatcher(composite, wordMatcher);
            lexemeMatcher(composite, punctuationMatcher);
            lexemeMatcher(composite, expressionMatcher);
            lexemeMatcher(composite, numberMatcher);
        }
        return composite;
    }

    private void lexemeMatcher(TextComposite composite, Matcher matcher) {
        while (matcher.find()) {
            var matcherGroup = matcher.group();
            var groupLeaf = new CharacterLeaf(matcherGroup);
            composite.add(groupLeaf);
        }
    }
}
