package by.geller.textproject.service.impl;

import by.geller.textproject.comparator.ParagraphComparator;
import by.geller.textproject.entity.ComponentType;
import by.geller.textproject.entity.TextComponent;
import by.geller.textproject.entity.impl.TextComposite;
import by.geller.textproject.exception.TextException;
import by.geller.textproject.service.OperationsOnTextService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;

public class OperationsOnTextServiceImpl implements OperationsOnTextService {
    private static final Logger logger = LogManager.getLogger();

    private String ERROR_MESSAGE = "There is no composite == null";
    private String DOT_REGEX = "\\.";
    private String NOT_A_WORD_REGEX = "\\W+";
    private String VOWELS_REGEX = "(?i)[aeiou]+";
    private String CONSONANTS_REGEX = "(?i)[^aeiou]+";
    private String NEW_LINE_REGEX = "\\n";

    @Override
    public List<TextComponent> paragraphSort(TextComposite textComposite) throws TextException {
        if (!textComposite.getType().equals(ComponentType.TEXT)) {
            throw new TextException("Expected component of text type: " + textComposite.getType());
        }

        List<TextComponent> a = textComposite.getListOfChildren()
                .stream()
                .filter(textComponent -> matchesType(textComponent, ComponentType.PARAGRAPH))
                .sorted(new ParagraphComparator())
                .collect(Collectors.toList());
        return a;
    }

    @Override
    public String sentenceWithTheLongestWord(TextComposite textComposite) throws TextException {
        if (textComposite == null) {
            logger.error(ERROR_MESSAGE);
            throw new TextException(ERROR_MESSAGE);
        }

        String[] text = textComposite.toString().split(DOT_REGEX);
        var longWord = 0;
        var position = 0;
        for (var i = 0; i < text.length; i++) {
            for (String word : text[i].split(NOT_A_WORD_REGEX)) {
                if(longWord < word.length()){
                    longWord = word.length();
                    position = i;
                }
            }
        }
        String sentence = text[position];
        return sentence;
    }

    //TODO
    @Override
    public List<String> removeSentenceUnderSomeWords(TextComposite textComposite, int amountOfWords) {
        return null;
    }

    @Override
    public Map<String, Long> countSameWords(TextComposite textComposite) {
        return Arrays.stream(textComposite.toString().split(NOT_A_WORD_REGEX))
                .collect(Collectors.groupingBy(Function.identity(), HashMap::new, counting()));
    }

    @Override
    public int countVowels(TextComposite textComposite) {
        var count = 0;
        for (String line : textComposite.toString().split(NOT_A_WORD_REGEX)) {
            if (line.contains(VOWELS_REGEX)){
                count++;
            }
        }
        return count;
    }

    @Override
    public int countConsonants(TextComposite textComposite) {
        var count = 0;
        for (String line : textComposite.toString().split(NOT_A_WORD_REGEX)) {
            if (line.contains(CONSONANTS_REGEX)){
                count++;
            }
        }
        return count;
    }

    private boolean matchesType(TextComponent component, ComponentType type) {
        return component.getType().equals(type);
    }
}
