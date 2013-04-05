package pl.reg0.grailsplugins.keywordsandtags;

import org.junit.Test;

class KeywordsUtilsTests {

    @Test
    void descriptionWithWordsHighlighted() {
        List words = [' met ', 'DOLO']
        String text = 'meta Lorem ,Dolo, dolo ipsum dolor amet met'
        assert 'meta Lorem ,<strong>Dolo</strong>, <strong>dolo</strong> ipsum dolor amet <strong>met</strong>' == KeywordsUtils.wrapWordsWithTag(words, text, 'strong')
        assert "meta Lorem ,<strong class='tag'>Dolo</strong>, <strong class='tag'>dolo</strong> ipsum dolor amet <strong class='tag'>met</strong>" == KeywordsUtils.wrapWordsWithTag(words, text, 'strong', 'tag')
    }

    @Test
    void descriptionWithWordsHighlightedNotFound() {
        List words = [' aaa']
        String text = 'meta Lorem ,Dolo, dolo ipsum dolor amet met'
        assert 'meta Lorem ,Dolo, dolo ipsum dolor amet met' == KeywordsUtils.wrapWordsWithTag(words, text, 'strong')
    }

    @Test
    void descriptionWithWordsHighlightedEmptyLit() {
        List words = []
        String text = 'meta Lorem ,Dolo, dolo ipsum dolor amet met'
        assert 'meta Lorem ,Dolo, dolo ipsum dolor amet met' == KeywordsUtils.wrapWordsWithTag(words, text, 'strong')
    }

    @Test
    void joinWordsForRegex() {
        List<String> words = ['word1', ' word2', 'word3 ', '\tword4 ']

        String result = KeywordsUtils.joinWordsForRegex(words)

        assert 'word1|word2|word3|word4' == result
    }

    @Test
    void joinWordsForRegexWithNullInList() {
        List<String> words = ['word1', null, 'word3 ', '\tword4 ']

        String result = KeywordsUtils.joinWordsForRegex(words)

        assert 'word1|word3|word4' == result
    }

    @Test
    void joinWordsForRegexWithBlankInList() {
        List<String> words = ['word1', ' \t ', 'word3 ', '\tword4 ']

        String result = KeywordsUtils.joinWordsForRegex(words)

        assert 'word1|word3|word4' == result
    }

    @Test
    void joinWordsForRegexWithNullList() {
        List<String> words = null

        String result = KeywordsUtils.joinWordsForRegex(words)

        assert '' == result
    }

    @Test
    void getOpeningTag() {
        assert '<strong>' == KeywordsUtils.getOpeningTag('strong')
        assert '<a>' == KeywordsUtils.getOpeningTag('a', '')
        assert '<em>' == KeywordsUtils.getOpeningTag('em', null)
        assert "<strong class='highlight'>" == KeywordsUtils.getOpeningTag('strong', 'highlight')
        assert "<em class='highlight tag'>" == KeywordsUtils.getOpeningTag('em', 'highlight tag')
    }

    @Test
    void getClosingTag() {
        assert '</strong>' == KeywordsUtils.getClosingTag('strong')
    }

}
