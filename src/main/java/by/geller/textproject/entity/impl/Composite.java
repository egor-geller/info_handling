package by.geller.textproject.entity.impl;

import by.geller.textproject.entity.Component;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component {
    private ArrayList<Component> components = new ArrayList<>();

    @Override
    public void add(Component component) {
        components.add(component);
    }

    @Override
    public void remove(Component component) {
        components.remove(component);
    }

    @Override
    public Component getChild(int index) {
        return components.get(index);
    }

    @Override
    public List<Component> getList() {
        List<Component> componentsClone = new ArrayList<>(components);
        return componentsClone;
    }

    @Override
    public String toString() {
        var stringBuilder = new StringBuilder();
        for (Component component : components){
            stringBuilder.append(component);
        }
        return stringBuilder.toString();
    }
}
