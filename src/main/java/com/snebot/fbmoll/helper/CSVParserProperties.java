package com.snebot.fbmoll.helper;

public class CSVParserProperties {
    private char separator = ',';
    private char lineSeparator = '\n';
    private char textSeparator = '\"';
    private boolean firstLineColumnName = false;
    private boolean lowerCaseTags = false;

    public char getSeparator() {
        return separator;
    }

    public void setSeparator(char separator) {
        this.separator = separator;
    }

    public char getLineSeparator() {
        return lineSeparator;
    }

    public void setLineSeparator(char lineSeparator) {
        this.lineSeparator = lineSeparator;
    }

    public char getTextSeparator() {
        return textSeparator;
    }

    public void setTextSeparator(char textSeparator) {
        this.textSeparator = textSeparator;
    }

    public boolean isFirstLineColumnName() {
        return firstLineColumnName;
    }

    public boolean isLowerCaseTags() {
        return lowerCaseTags;
    }

    public void setLowerCaseTags(boolean lowerCaseTags) {
        this.lowerCaseTags = lowerCaseTags;
    }

    public void setFirstLineColumnName(boolean firstLineColumnName) {
        this.firstLineColumnName = firstLineColumnName;
    }

    public CSVParserProperties() {
    }

    public CSVParserProperties(Character separator, Character lineSeparator, Character textSeparator, boolean firstLineColumnName, boolean lowerCaseTags) {
        if (separator != null) this.separator = separator;
        if (lineSeparator != null) this.lineSeparator = lineSeparator;
        if (textSeparator != null) this.textSeparator = textSeparator;
        this.firstLineColumnName = firstLineColumnName;
        this.lowerCaseTags = lowerCaseTags;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof CSVParserProperties)) return false;
        CSVParserProperties properties = (CSVParserProperties) obj;
        return this.separator == properties.separator &&
                this.lineSeparator == properties.lineSeparator &&
                this.textSeparator == properties.textSeparator &&
                this.firstLineColumnName == properties.firstLineColumnName &&
                this.lowerCaseTags == properties.lowerCaseTags;
    }
}
