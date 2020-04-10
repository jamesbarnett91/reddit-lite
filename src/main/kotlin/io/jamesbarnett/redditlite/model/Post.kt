package io.jamesbarnett.redditlite.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.github.marlonlom.utilities.timeago.TimeAgo
import java.time.Instant

@JsonIgnoreProperties(ignoreUnknown = true)
data class Post (
  val id: String,
  val name: String,
  val title: String,
  val domain: String,
  val score: Int,
  val author: String,
  private val thumbnail: String?,
  val ups: Int,
  @JsonProperty("num_comments")
  val commentCount: Int,
  val url: String,
  @JsonProperty("created_utc")
  val createdDate: Instant,
  @JsonProperty("is_self")
  val isSelfPost: Boolean,
  @JsonProperty("selftext_html")
  val selftextHtml: String?,
  val subreddit: String
) {
  val primaryLink: String get() {
    return if (isSelfPost) {
      "/r/${subreddit}/comments/${id}"
    } else {
      url
    }
  }
  val subredditPath get() = "/r/${subreddit}"
  val relativeCreatedDate: String get() = TimeAgo.using(createdDate.toEpochMilli())
  val thumbnailUrl get() = thumbnail?.let { if(thumbnail.startsWith("http")) thumbnail else null }
//val thumbnail get() = if(thumbnailRaw?.startsWith("http") == true) thumbnailRaw else null

}