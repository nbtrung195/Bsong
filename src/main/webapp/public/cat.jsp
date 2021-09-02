<%@page import="util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
    <div class="article">
    <%
    categories Categories = (categories)request.getAttribute("Categories");
    if(Categories != null){
    %>
		<h1><%=Categories.getNameCat()%></h1>
	<%} %>	
    </div>
    <%
    @SuppressWarnings("unchecked")
    ArrayList<song> Songs = (ArrayList<song>)request.getAttribute("Songs");
    if(Songs != null && Songs.size()>0){
    	int i =0;
    	for(song obj : Songs){
    		i++;
    %>
    <div class="article">
      <h2><a href="<%=request.getContextPath()%>/<%=StringUtil.makeSlug(obj.getCategory().getNameCat())%>/<%=StringUtil.makeSlug(obj.getName())%>-<%=obj.getId()%>" title="<%=obj.getName()%>"><%=obj.getName()%></a></h2>
      <p class="infopost">Ngày đăng: <%=obj.getDateCreate()%>. Lượt xem: <%=obj.getCounter()%> <a href="#" class="com"><span><%=i%></span></a></p>
      <div class="clr"></div>
      <div class="img"><img src="<%=request.getContextPath()%>/files/<%=obj.getPicture()%>" width="177" alt="<%=obj.getName()%>" class="fl" /></div>
      <div class="post_content">
        <p>“<%=obj.getPreviewText()%>.</p>
        <p class="spec"><a href="<%=request.getContextPath()%>/<%=StringUtil.makeSlug(obj.getCategory().getNameCat())%>/<%=StringUtil.makeSlug(obj.getName())%>-<%=obj.getId()%>" class="rm">Chi tiết &raquo;</a></p>
      </div>
      <div class="clr"></div>
    </div>
    <%}}else{out.print("Không có bài hát nào");}%>
    <% 
    int currentPage = (Integer)request.getAttribute("currentPage");
    int NumberOfPages = (Integer)request.getAttribute("NumberOfPages");
    if(Songs!=null && Songs.size()>0){
    %>
    <p class="pages"><small>Trang <%=currentPage%> của <%=NumberOfPages%></small>
    <%
    for(int i = 1 ; i <= NumberOfPages ; i++){
    	if(currentPage == i){
    %>
    <span><%=i%></span>
    <%}else{%>
    <a href="<%=request.getContextPath()%>/<%=StringUtil.makeSlug(Categories.getNameCat())%>-<%=Categories.getIdCat()%>/page/<%=i%>"><%=i%></a>
    <%}}%>
    <a href="<%=request.getContextPath()%>/<%=StringUtil.makeSlug(Categories.getNameCat())%>-<%=Categories.getIdCat()%>/page/<%=currentPage+1%>">&raquo;</a></p>
    <%}%>
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