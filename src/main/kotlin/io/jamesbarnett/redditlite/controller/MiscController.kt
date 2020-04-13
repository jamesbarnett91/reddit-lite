package io.jamesbarnett.redditlite.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.ModelAndView

@Controller
class MiscController {
  @GetMapping("/")
  fun renderLandingPage() : ModelAndView {
    return ModelAndView("landing")
  }

  @GetMapping("/healthcheck")
  @ResponseBody
  fun healthCheck() : String {
    return "ok"
  }

  @GetMapping("/robots.txt")
  @ResponseBody
  fun robotsTxt() : String {
    return """User-agent: *
Disallow: /
"""
  }

}