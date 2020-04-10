package io.jamesbarnett.redditlite.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.marlonlom.utilities.timeago.TimeAgo
import org.apache.commons.text.StringEscapeUtils
import java.time.Instant

@JsonIgnoreProperties(ignoreUnknown = true)
data class Comment(
  val author: String?,
  val score: Int,
  @JsonProperty("is_submitter")
  val isSubmitter: Boolean,
  @JsonProperty("created_utc")
  val createdDate: Instant?,
  @JsonProperty("author_flair_text")
  val flairText: String?,
  val body: String?,
  @JsonProperty("body_html")
  val bodyHtml: String?,
  val depth: Int,
  @JsonIgnore
  val replies: MutableList<Comment> = mutableListOf()
) {
  companion object {
    operator fun invoke(jsonNode: JsonNode, objectMapper: ObjectMapper): Comment {
      val topLevelComment = objectMapper.treeToValue(jsonNode, Comment::class.java)
      jsonNode
        .path("replies")
        .path("data")
        .path("children")
        .findValues("data")
        .forEach {topLevelComment.replies.add(invoke(it, objectMapper))}
      return topLevelComment
    }
  }
  val isChild get() = depth > 0
  val relativeCreatedDate: String? get() = createdDate?.let { TimeAgo.using(createdDate.toEpochMilli()) }
  val bodyHtmlUnescaped: String? get() = StringEscapeUtils.unescapeHtml4(bodyHtml)
}