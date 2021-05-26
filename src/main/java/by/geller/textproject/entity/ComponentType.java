package by.geller.textproject.entity;

public enum ComponentType {
    TEXT("\n", "\t"),
    PARAGRAPH(" ", ""),
    SENTENCE(" ", ""),
    WORD("", ""),
    SYMBOL("", ""),
    LEXEME("", "");

    private final String childrenDelimiter;
    private final String prefix;

    ComponentType(String childrenDelimiter, String prefix) {
        this.childrenDelimiter = childrenDelimiter;
        this.prefix = prefix;
    }

    public String getChildrenDelimiter() {
        return childrenDelimiter;
    }

    public String getPrefix() {
        return prefix;
    }
}
