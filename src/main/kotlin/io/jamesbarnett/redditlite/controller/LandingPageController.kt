package io.jamesbarnett.redditlite.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class LandingPageController {
  @GetMapping("/")
  fun renderLandingPage() : ModelAndView {
    return ModelAndView("landing")
  }
}