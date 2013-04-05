package pl.reg0.grailsplugins.keywordsandtags

class KeywordsAndTagsTagLib {
    static namespace = 'kat'

    def wrapWordsWithTag = { attrs, body ->
        List<String> words = attrs.words

        if(!words) words = []

        out << KeywordsUtils.wrapWordsWithTag(words, body().toString(), attrs.tagName ?: 'strong', attrs.className ?: '')
    }

}