package io.jamesbarnett.redditlite.model

import org.apache.commons.text.StringEscapeUtils

data class PostDetail(val post: Post, val comments: List<Comment>) {
  val isSelfPost get() = post.isSelfPost
  val selftextHtmlUnescaped: String? get() = StringEscapeUtils.unescapeHtml4(post.selftextHtml)
  val commentCount get() = post.commentCount
}
