package by.geller.textproject.service;

import by.geller.textproject.entity.TextComponent;
import by.geller.textproject.entity.impl.TextComposite;
import by.geller.textproject.exception.TextException;

import java.util.List;
import java.util.Map;

public interface OperationsOnTextService {
    List<TextComponent> paragraphSort(TextComposite textComposite) throws TextException;

    String sentenceWithTheLongestWord(TextComposite textComposite) throws TextException;

    List<TextComponent> removeSentenceUnderSomeWords(TextComposite textComposite, int amountOfCharacters) throws TextException;

    Map<String, Long> countSameWords(TextComposite textComposite);

    int countVowels(TextComposite textComposite);

    int countConsonants(TextComposite textComposite);
}
