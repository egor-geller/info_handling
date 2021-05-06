package by.geller.textproject.service.impl;

import by.geller.textproject.entity.impl.Composite;
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
    public void paragraphSort(Composite composite) {
        //TODO: sort
        //StringBuilder paragraph = new StringBuilder();
        List<Integer> longestParagraph = new ArrayList<>();
        Map<Integer, String> unsortedParagraph = new HashMap<>();
        for (String paragraph : composite.toString().split("\\s{3,}")) {
            int count = 0;
            for (String lines : paragraph.split(DOT_REGEX)) {
                count += lines.length();
            }
            unsortedParagraph.put(count, paragraph);
        }
        //composite.remove(); ???
        Map<Integer, String> sortedParagraph = new TreeMap<>(unsortedParagraph);
        String newText = sortedParagraph.keySet().stream().map(sortedParagraph::get).collect(Collectors.joining(NEW_LINE_REGEX));
        //composite.add(); ???
    }

    @Override
    public String SentenceWithTheLongestWord(Composite composite) throws TextException {
        if (composite == null) {
            logger.error(ERROR_MESSAGE);
            throw new TextException(ERROR_MESSAGE);
        }

        String[] text = composite.toString().split(DOT_REGEX);
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

    @Override
    public List<String> removeSentenceUnderSomeWords(Composite composite, int amountOfWords) {
        return null;
    }

    @Override
    public Map<String, Long> countSameWords(Composite composite) {
        return Arrays.stream(composite.toString().split(NOT_A_WORD_REGEX))
                .collect(Collectors.groupingBy(Function.identity(), HashMap::new, counting()));
    }

    @Override
    public int countVowels(Composite composite) {
        var count = 0;
        for (String line : composite.toString().split(NOT_A_WORD_REGEX)) {
            if (line.contains(VOWELS_REGEX)){
                count++;
            }
        }
        return count;
    }

    @Override
    public int countConsonants(Composite composite) {
        var count = 0;
        for (String line : composite.toString().split(NOT_A_WORD_REGEX)) {
            if (line.contains(CONSONANTS_REGEX)){
                count++;
            }
        }
        return count;
    }
}
