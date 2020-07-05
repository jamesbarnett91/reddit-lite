<#macro wrapper title>
<html>
  <head>
    <title>${title}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="/stylesheets/style.css" media="screen" rel="stylesheet" type="text/css" />
  </head>
  <body>
    <#nested>
  </body>
</html>
</#macro>

<#macro header subreddit>
  <div class="header">
    <a class="subreddit-title" href="/r/${subreddit}">
      <#if subreddit?contains("+")>
        <#list subreddit?split("+") as multiRedditName>
          <#if multiRedditName?is_first>/r/<#else>+</#if>${multiRedditName}
        </#list>
      <#else>
        /r/${subreddit}
      </#if>
    </a>
    <span class="header-links">
      <#nested>
    </span>
  </div>
</#macro>

<#macro postSummary post>
  <div class="post-body">
    <div>
      <a class="post-title" href="${post.primaryLink}">${post.title}</a>
      <span class="post-domain">(${post.domain})</span>
    </div>
    <div class="post-info">
      <span class="nowrap"><@pluralise post.score "point"/></span>
      <span class="nowrap">by ${post.author}</span>
      <span class="nowrap"> in <a class="subreddit-link" href="${post.subredditPath}">${post.subredditPath}</a></span>
      <span class="nowrap">${post.relativeCreatedDate}</span> | <span class="nowrap"><a href="${post.subredditPath}/comments/${post.id}"><@pluralise post.commentCount, "comment"/></a></span>
    </div>
  </div>
</#macro>

<#macro postComment comment>
  <div class="comment<#if comment.isChild()> child</#if>">
    <#if comment.author?has_content>
      <details open>
        <summary>
          <span class="comment-details">${comment.author} <#if comment.flairText?has_content>${comment.flairText}</#if> | ${comment.relativeCreatedDate} | <@pluralise comment.score "point"/></span>
        </summary>
        <div class="comment-text">${comment.bodyHtmlUnescaped?no_esc}</div>
        <#list comment.replies as childComment>
          <@postComment childComment/>
        </#list>
      </details>
    <#else>
      <a href="#">TODO Load more comments...</a>
    </#if>
  </div>
</#macro>

<#macro pluralise count word>
  <#if count == 1>
    ${count} ${word}
  <#else>
    ${count} ${word}s
  </#if>
</#macro>