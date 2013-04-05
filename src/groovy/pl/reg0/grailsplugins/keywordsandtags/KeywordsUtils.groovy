package pl.reg0.grailsplugins.keywordsandtags

import java.util.regex.Pattern
import java.util.regex.Matcher

class KeywordsUtils {

    private static String joinWordsForRegex(List<String> words) {
        words ? words*.trim().findAll { it }.join('|') : ''
    }

    private static String getOpeningTag(String tagName, String className = '') {
        return "<$tagName" + (className ? " class='$className'>" : '>')
    }

    private static String getClosingTag(String tagName) {
        return "</$tagName>"
    }

    static wrapWordsWithTag(List<String> words, String text, String tagName, String className = '') {
        if (!words) {
            return text
        }
        String joinedWords = joinWordsForRegex(words)
        if (!joinedWords) {
            return text
        }

        Pattern p = Pattern.compile('(?i)(^|[^a-z\\d]){1}('+joinedWords+')($|[^a-z\\d]){1}')
        Matcher m = p.matcher(text)

        String openingTag = getOpeningTag(tagName, className)
        String closingTag = getClosingTag(tagName)

        StringBuffer s = new StringBuffer()
        while (m.find()) {
            m.appendReplacement(s, (m.group(1)?:'')+"$openingTag"+(m.group(2)?:'')+"$closingTag"+(m.group(3)?:''))
        }
        m.appendTail(s)
        return s.toString()
    }
}
