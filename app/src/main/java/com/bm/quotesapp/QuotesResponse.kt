package com.bm.quotesapp

data class QuotesResponse(
    var _id: String? = null,
    var content: String? = null,
    var author: String? = null,
    var authorSlug: String? = null,
    var length: Number? = null,
    var tags: Array<String> = arrayOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as QuotesResponse

        if (_id != other._id) return false
        if (content != other.content) return false
        if (author != other.author) return false
        if (authorSlug != other.authorSlug) return false
        if (length != other.length) return false
        if (!tags.contentEquals(other.tags)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = _id.hashCode()
        result = 31 * result + content.hashCode()
        result = 31 * result + author.hashCode()
        result = 31 * result + authorSlug.hashCode()
        result = 31 * result + length.hashCode()
        result = 31 * result + tags.contentHashCode()
        return result
    }
}