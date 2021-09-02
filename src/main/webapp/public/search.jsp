<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
  <%
  @SuppressWarnings("unchecked")
  ArrayList<song> Song = (ArrayList<song>)request.getAttribute("Song");
  if(Song!=null && Song.size()>0){
	  int i = 0;
	  for(song obj : Song){
  	  i++;
  %>
    <div class="article">
      <h2><a href="<%=request.getContextPath()%>/<%=StringUtil.makeSlug(obj.getCategory().getNameCat())%>/<%=StringUtil.makeSlug(obj.getName())%>-<%=obj.getId()%>" title="<%=obj.getName()%>"><%=obj.getName()%></a></h2>
      <p class="infopost">Ngày đăng: <%=obj.getDateCreate()%>. Lượt xem: <%=obj.getCounter()%> <a href="#" class="com"><span><%=i%></span></a></p>
      <div class="clr"></div>
      <div class="img"><img src="<%=request.getContextPath()%>/files/<%=obj.getPicture()%>" width="177" alt="<%=obj.getName()%>" class="fl" /></div>
      <div class="post_content">
        <p><%=obj.getPreviewText()%></p>
        <p class="spec"><a href="<%=request.getContextPath()%>/<%=StringUtil.makeSlug(obj.getCategory().getNameCat())%>/<%=StringUtil.makeSlug(obj.getName())%>-<%=obj.getId()%>" class="rm">Chi tiết &raquo;</a></p>
      </div>
      <div class="clr"></div>
    </div>
    <%} }else{out.print("<h2 style=\"color:red\">Không tìm thấy bài hát nào, vui lòng kiểm tra lại tên bài hát.</h2>");}%>
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
