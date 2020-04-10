package io.jamesbarnett.redditlite.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.treeToValue
import io.jamesbarnett.redditlite.model.Comment
import io.jamesbarnett.redditlite.model.Post
import io.jamesbarnett.redditlite.model.PostDetail
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.time.Duration

const val REDDIT_API_ROOT_URL = "https://reddit.com/r/"

@Service
class SubredditService (restTemplateBuilder: RestTemplateBuilder) {

  val objectMapper: ObjectMapper = jacksonObjectMapper()
      .registerModule(JavaTimeModule())

  val restTemplate: RestTemplate = restTemplateBuilder
      .setConnectTimeout(Duration.ofSeconds(5))
      .setReadTimeout(Duration.ofSeconds(10))
      // Custom UA required to prevent rate limiting: https://github.com/reddit-archive/reddit/wiki/API#rules
      .defaultHeader("User-Agent", "reddit-lite")
      .build()

  fun getPosts(subreddit: String, postsAfterId: String?): List<Post> {
    val jsonResponse = restTemplate.getForObject("${REDDIT_API_ROOT_URL}${subreddit}/.json${if (postsAfterId != null) "?after=${postsAfterId}" else ""}", String::class.java)
    return objectMapper.readTree(jsonResponse)
      .path("data")
      .path("children")
      .findValues("data")
      .map { objectMapper.treeToValue(it, Post::class.java) }
  }

  fun getPostDetail(subreddit: String, postId: String): PostDetail {
    val jsonResponse = restTemplate.getForObject("${REDDIT_API_ROOT_URL}${subreddit}/comments/${postId}/.json", String::class.java)
    val rootNode = objectMapper.readTree(jsonResponse)

    val post = objectMapper.treeToValue(
      rootNode
        .path(0)
        .path("data")
        .path("children")
        .path(0)
        .path("data"),
      Post::class.java)

    val comments = rootNode
      .path(1)
      .path("data")
      .path("children")
      .findValues("data")
      .map { Comment(it, objectMapper) }

    return PostDetail(post, comments)
  }

}