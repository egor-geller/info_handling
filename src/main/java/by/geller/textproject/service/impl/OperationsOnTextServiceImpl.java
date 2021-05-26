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

    private static final String ERROR_MESSAGE = "There is no composite == null";
    private static final String DOT_REGEX = "\\.";
    private static final String NOT_A_WORD_REGEX = "\\W+";
    private static final String VOWELS_REGEX = "aeiouауоиЭы";
    private static final String CONSONANTS_REGEX = "BCDFGHJKLMNPQRSTVWXYZ";

    @Override
    public List<TextComponent> paragraphSort(TextComposite textComposite) throws TextException {
        if (!textComposite.getType().equals(ComponentType.TEXT)) {
            throw new TextException("Expected component of text type: " + textComposite.getType());
        }

        List<TextComponent> sortedParagraph = textComposite.getListOfChildren()
                .stream()
                .filter(textComponent -> matchesType(textComponent, ComponentType.PARAGRAPH))
                .sorted(new ParagraphComparator())
                .collect(Collectors.toList());
        return sortedParagraph;
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
                if (longWord < word.length()) {
                    longWord = word.length();
                    position = i;
                }
            }
        }
        return text[position];
    }

    @Override
    public List<TextComponent> removeSentenceUnderSomeWords(TextComposite textComposite, int amountOfCharacters) throws TextException {
        if (textComposite == null) {
            logger.error("Composite is null");
            throw new TextException("Composite is null");
        } else if (!textComposite.getType().equals(ComponentType.PARAGRAPH)) {
            logger.error("Search must be in paragraph {}", textComposite.getType());
            throw new TextException("Search must be in paragraph " + textComposite.getType());
        }
        List<TextComponent> paragraphs = textComposite.getListOfChildren();
        List<TextComponent> clearedSentences = new ArrayList<>();

        for (TextComponent component : paragraphs) {
            List<TextComponent> sentences = component.getListOfChildren();
            clearedSentences.addAll(sentences);
        }
        for (TextComponent sentence : clearedSentences) {
            List<TextComponent> lexemes = sentence.getListOfChildren();
            for (TextComponent lexeme : lexemes) {
                List<TextComponent> words = lexeme.getListOfChildren();
                for (TextComponent word : words) {
                    if (word.getType().equals(ComponentType.SYMBOL) && word.getSizeOfComponent() < amountOfCharacters) {
                        clearedSentences.remove(sentence);
                    }
                }
            }
        }
        logger.info("Text without words with a length of {} : {}", amountOfCharacters, clearedSentences);
        return clearedSentences;
    }

    @Override
    public Map<String, Long> countSameWords(TextComposite textComposite) {
        return Arrays.stream(textComposite.toString().split(NOT_A_WORD_REGEX))
                .collect(Collectors.groupingBy(Function.identity(), HashMap::new, counting()));
    }

    @Override
    public int countVowels(TextComposite textComposite) {
        var count = 0;
        String[] lines = textComposite.toString().split(NOT_A_WORD_REGEX);
        for (String line : lines) {
            for (var j = 0; j < line.length(); j++) {
                if (Character.isLetter(line.charAt(j))){
                    for (var i = 0; i < VOWELS_REGEX.length(); i++) {
                        if (line.charAt(j) == VOWELS_REGEX.toLowerCase().charAt(i)){
                            count++;
                        }
                    }
                }
            }
        }
        logger.info("{} Vowels counted", count);
        return count;
    }

    @Override
    public int countConsonants(TextComposite textComposite) {
        var count = 0;
        String[] lines = textComposite.toString().split(NOT_A_WORD_REGEX);
        for (String line : lines) {
            for (var j = 0; j < line.length(); j++) {
                if (Character.isLetter(line.charAt(j))){
                    for (var i = 0; i < CONSONANTS_REGEX.length(); i++) {
                        if (line.charAt(j) == CONSONANTS_REGEX.toLowerCase().charAt(i)){
                            count++;
                        }
                    }
                }
            }
        }
        logger.info("{} Consonants counted", count);
        return count;
    }

    private boolean matchesType(TextComponent component, ComponentType type) {
        return component.getType().equals(type);
    }
}
