package io.jamesbarnett.redditlite.controller

import io.jamesbarnett.redditlite.service.SubredditService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/r")
class SubredditController (val subredditService: SubredditService) {

  @GetMapping("/{subreddit}")
  fun renderPosts(@PathVariable subreddit: String,
                  @RequestParam("after", required = false) postAfterId: String?,
                  @RequestParam("showThumbs", defaultValue = "true") showThumbs: Boolean
  ): ModelAndView {
    val posts = subredditService.getPosts(subreddit, postAfterId)
    return ModelAndView("posts")
        .addObject("showThumbs", showThumbs)
        .addObject("postAfterId", postAfterId)
        .addObject("subreddit", subreddit)
        .addObject("posts", posts)
        .addObject("nextPostId", posts.last().name)
  }

  @GetMapping("/{subreddit}/comments/{postId}")
  fun renderPostDetail(@PathVariable subreddit: String, @PathVariable postId: String): ModelAndView {
    val postDetail = subredditService.getPostDetail(subreddit, postId)
    return ModelAndView("postDetail")
        .addObject("subreddit", subreddit)
        .addObject("postDetail", postDetail)
  }
}

