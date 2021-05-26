package by.geller.textproject.entity;

import java.util.List;

public interface TextComponent {
    void add(TextComponent textComponent);

    void remove(TextComponent textComponent);

    ComponentType getType();

    int getSizeOfComponent();

    List<TextComponent> getListOfChildren();
}
