package by.geller.textproject.entity;

public enum ComponentType {
    TEXT("\n"),
    PARAGRAPH(" "),
    SENTENCE(" "),
    WORD(""),
    PUNCTUATION(""),
    LETTER(""),
    LEXEME("");

    private final String childrenDelimiter;

    ComponentType(String childrenDelimiter) {
        this.childrenDelimiter = childrenDelimiter;
    }

    public String getChildrenDelimiter() {
        return childrenDelimiter;
    }
}
