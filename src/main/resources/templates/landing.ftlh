<#include 'lib.ftlh'>

<@wrapper title="Reddit-lite">
  <div class="header">
    <a class="subreddit-title">reddit-lite</a>
    <span class="header-links"> </span>
  </div>
  <div class="landing-desc">
    <p>
      A lightweight, minimal, readonly Reddit client, designed for mobile devices or slow connections. Source is available on <a href="https://github.com/jamesbarnett91/reddit-lite">GitHub</a>.
    </p>
    <div>
      Navigate to /r/&lt;subreddit> or try some of the examples below:
      <ul>
        <li>
          <a href="/r/popular">Reddit frontpage (popular subreddits)</a>
        </li>
        <li>
          <a href="/r/programming">/r/programming</a>
        </li>
        <li>
          <a href="/r/webdev">/r/webdev</a>
        </li>
        <li>
          <a href="/r/videos">/r/videos</a>
        </li>
        <li>
          <a href="/r/youtubehaiku">/r/youtubehaiku</a>
        </li>
        <li>
          <a href="/r/worldnews">/r/worldnews/</a>
        </li>
        <li>
          <a href="/r/food">/r/food</a>
        </li>
      </ul>
    </div>
    <p>You can also paste any Reddit link (post or comment) here to view the corresponding page in this client</p>
    <input id="reddit-link" placeholder="https://reddit.com/r/example/comments/1jke3nj2/some_example_post"> </input> <button id="view-link">view in reddit-lite</button>
    <p id="link-error" hidden >specified link does not contain "reddit.com"</p>
  </div>

  <script>
    document.addEventListener("DOMContentLoaded", function() {
      document.getElementById("view-link").onclick = function(e) {
        var link = document.getElementById("reddit-link").value;
        var linkTokens = link.split("reddit.com");
        if(linkTokens.length === 2) {
          window.location = linkTokens[1];
        }
        else {
          document.getElementById("link-error").removeAttribute('hidden');
        }
      }
    });
  </script>

</@wrapper>