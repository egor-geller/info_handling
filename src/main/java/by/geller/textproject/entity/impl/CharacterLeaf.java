package by.geller.textproject.entity.impl;

import by.geller.textproject.entity.Component;

import java.util.List;

public class CharacterLeaf implements Component {
    private char character;

    public CharacterLeaf(char character) {
        this.character = character;
    }

    @Override
    public void add(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Component getChild(int index) {
        //TODO
        return null;
    }

    @Override
    public List<Component> getList() {
        //TODO
        return null;
    }

    @Override
    public String toString() {
        return String.valueOf(character);
    }
}
