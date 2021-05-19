package by.geller.textproject.entity.impl;

import by.geller.textproject.entity.ComponentType;
import by.geller.textproject.entity.TextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TextComposite implements TextComponent {
    private final ArrayList<TextComponent> textComponents = new ArrayList<>();
    private final ComponentType componentType;

    public TextComposite(ComponentType componentType){
        this.componentType = componentType;
    }

    @Override
    public void add(TextComponent textComponent) {
        textComponents.add(textComponent);
    }

    @Override
    public void remove(TextComponent textComponent) {
        textComponents.remove(textComponent);
    }

    @Override
    public ComponentType getType() {
        return componentType;
    }

    @Override
    public List<TextComponent> getListOfChildren() {
        List<TextComponent> componentsClone = new ArrayList<>(textComponents);
        return componentsClone;
    }

    @Override
    public int hashCode() {
        var hash = 7;
        hash += 31 * hash + Objects.hashCode(this.textComponents);
        hash += 31 * hash + Objects.hashCode(this.componentType);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextComposite that = (TextComposite) o;
        return textComponents.equals(that.textComponents) && componentType == that.componentType;
    }

    @Override
    public String toString() {
        var stringBuilder = new StringBuilder();
        for (TextComponent textComponent : textComponents){
            stringBuilder.append(textComponent);
        }
        return stringBuilder.toString();
    }
}
