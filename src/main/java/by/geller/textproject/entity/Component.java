package by.geller.textproject.entity;

import java.util.List;

public interface Component {
    void add(Component component);

    void remove(Component component);

    Component getChild(int index);

    List<Component> getList();
}
