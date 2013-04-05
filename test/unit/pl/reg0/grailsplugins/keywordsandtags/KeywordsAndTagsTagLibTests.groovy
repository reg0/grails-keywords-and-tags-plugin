package pl.reg0.grailsplugins.keywordsandtags

import grails.test.mixin.TestFor
import org.junit.Test;

@TestFor(KeywordsAndTagsTagLib)
class KeywordsAndTagsTagLibTests {

    @Test
    void wrapWordsWithTag() {
        String htmlCode = '<kat:wrapWordsWithTag words="${words}" tagName="em"><p>This is my paragraph of text with emphasized tag, a keyword and other words</p></kat:wrapWordsWithTag>'

        assert applyTemplate(htmlCode, [words: [" tag ", "   keyword"]]) == '<p>This is my paragraph of text with emphasized <em>tag</em>, a <em>keyword</em> and other words</p>'
        assert applyTemplate(htmlCode, [words: [" PARAGRAPH ", "   keyword"]]) == '<p>This is my <em>paragraph</em> of text with emphasized tag, a <em>keyword</em> and other words</p>'
    }

    @Test
    void wrapWordsWithTagWithClassName() {
        String htmlCode = '<kat:wrapWordsWithTag words="${words}" tagName="em" className="italic"><p>This is my paragraph of text with emphasized tag, a keyword and other words</p></kat:wrapWordsWithTag>'

        assert applyTemplate(htmlCode, [words: [" tag ", "   keyword"]]) == "<p>This is my paragraph of text with emphasized <em class='italic'>tag</em>, a <em class='italic'>keyword</em> and other words</p>"
        assert applyTemplate(htmlCode, [words: [" PARAGRAPH ", "   keyword"]]) == "<p>This is my <em class='italic'>paragraph</em> of text with emphasized tag, a <em class='italic'>keyword</em> and other words</p>"
    }

    @Test
    void wrapWordsWithTagWithNoTagNameShouldUseStrongTagByDefault() {
        String htmlCode = '<kat:wrapWordsWithTag words="${words}"><p>This is my paragraph of text with emphasized tag, a keyword and other words</p></kat:wrapWordsWithTag>'

        assert applyTemplate(htmlCode, [words: [" tag ", "   keyword"]]) == '<p>This is my paragraph of text with emphasized <strong>tag</strong>, a <strong>keyword</strong> and other words</p>'
        assert applyTemplate(htmlCode, [words: [" PARAGRAPH ", "   keyword"]]) == '<p>This is my <strong>paragraph</strong> of text with emphasized tag, a <strong>keyword</strong> and other words</p>'
    }
}
