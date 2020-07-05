<#include 'lib.ftlh'>

<@wrapper title="/r/${subreddit}">
  <@header subreddit/>

  <div class="post-summary">
    <@postSummary postDetail.post/>
  </div>

  <div class="post-selftext">
    ${(postDetail.selftextHtmlUnescaped?no_esc)!""}
  </div>

  <div class="comment-heading"><@pluralise postDetail.commentCount, "comment"/></div>
  <div class="comments">
    <#list postDetail.comments as comment>
      <@postComment comment/>
    </#list>
  </div>

</@wrapper>