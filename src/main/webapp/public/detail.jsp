<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
  <%
  song Song = (song)request.getAttribute("Song");
  if(Song != null){
  %>
    <div class="article">
      <h1><%=Song.getName()%></h1>
      <div class="clr"></div>
      <div><%=Song.getYoutube() %></div>
      <p>Ngày đăng: <%=Song.getDateCreate()%>. Lượt xem: <%=Song.getCounter()%></p>
      <div class="vnecontent">
          <%=Song.getDetailText()%>
      </div>
    </div>
    <%}%>
    <div class="article">
      <h2>Bài viết liên quan</h2>
       <%
       @SuppressWarnings("unchecked")
  		ArrayList<song> relatedSongs = (ArrayList<song>)request.getAttribute("relatedSongs");
 		if(relatedSongs != null){
 			for(song obj : relatedSongs){
  %>
      <div class="clr"></div>
      <div class="comment"> <a href="<%=request.getContextPath()%>/<%=StringUtil.makeSlug(obj.getCategory().getNameCat())%>/<%=StringUtil.makeSlug(obj.getName())%>-<%=obj.getId()%>"><img src="<%=request.getContextPath()%>/files/<%=obj.getPicture()%>" width="40" height="40" alt="<%=obj.getName()%>" class="userpic" /></a>
        <h2><a href="<%=request.getContextPath()%>/<%=StringUtil.makeSlug(obj.getCategory().getNameCat())%>/<%=StringUtil.makeSlug(obj.getName())%>-<%=obj.getId()%>"><%=obj.getName()%></a></h2>
        <p><%=obj.getPreviewText()%></p>
      </div>
      <%}}%>
    </div>
  </div>
  <div class="sidebar">
  <%@ include file="/templates/public/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<script>
    document.getElementById("home_action").classList.add('active');
</script>
<%@ include file="/templates/public/inc/footer.jsp" %>
