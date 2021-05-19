package by.geller.textproject.comparator;

import by.geller.textproject.entity.TextComponent;

import java.util.Comparator;

public class ParagraphComparator implements Comparator<TextComponent> {
    @Override
    public int compare(TextComponent o1, TextComponent o2) {
        long first = o1.getListOfChildren().size();
        long second = o2.getListOfChildren().size();

        return Long.compare(first, second);
    }
}
