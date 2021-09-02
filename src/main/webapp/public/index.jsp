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
    <%} }else{out.print("Chưa có bài hát nào");}%>
    <%
     int NumberOfPages = (Integer)request.getAttribute("NumberOfPages");
     int currentPage = (Integer)request.getAttribute("currentPage");
     int offset = (Integer)request.getAttribute("offset");
     int NumberOfItems = (Integer)request.getAttribute("NumberOfItems");
     if(Song!=null && Song.size()>0){
     %>
    <p class="pages"><small>Trang <%=currentPage%> của <%=NumberOfPages%></small>
    <%
    }for(int i=1 ; i<=NumberOfPages ; i++){
    	if(i == currentPage){
    %>
    <span><%=i%></span>
    <%}else{%>
    <a href="<%=request.getContextPath()%>/page/<%=StringUtil.makeSlug(String.valueOf(i))%>"><%=i%></a>
    <%}}%>
    
    <a href="<%=request.getContextPath()%>/page/<%=StringUtil.makeSlug(String.valueOf(currentPage+1))%>">&raquo;</a></p>
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
