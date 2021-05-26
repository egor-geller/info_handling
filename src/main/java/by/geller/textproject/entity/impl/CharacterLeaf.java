package by.geller.textproject.entity.impl;

import by.geller.textproject.entity.ComponentType;
import by.geller.textproject.entity.TextComponent;

import java.util.List;

public class CharacterLeaf implements TextComponent {
    private final char character;

    public CharacterLeaf(String character) {
        this.character = character.charAt(0);
    }

    @Override
    public void add(TextComponent textComponent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(TextComponent textComponent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ComponentType getType() {
        return ComponentType.SYMBOL;
    }

    @Override
    public int getSizeOfComponent() {
        return 1;
    }

    @Override
    public List<TextComponent> getListOfChildren() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return String.valueOf(character);
    }

    @Override
    public int hashCode() {
        var hash = 7;
        hash += hash * 31 + Character.hashCode(character);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacterLeaf that = (CharacterLeaf) o;
        return character == that.character;
    }
}
