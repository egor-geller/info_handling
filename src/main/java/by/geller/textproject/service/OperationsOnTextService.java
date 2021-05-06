package by.geller.textproject.service;

import by.geller.textproject.entity.impl.Composite;
import by.geller.textproject.exception.TextException;

import java.util.List;
import java.util.Map;

public interface OperationsOnTextService {
    void paragraphSort(Composite composite);

    String SentenceWithTheLongestWord(Composite composite) throws TextException;

    List<String> removeSentenceUnderSomeWords(Composite composite, int amountOfWords);

    Map<String, Long> countSameWords(Composite composite);

    int countVowels(Composite composite);

    int countConsonants(Composite composite);
}
