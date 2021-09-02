<%@page import="util.StringUtil"%>
<%@page import="model.dao.catDAO"%>
<%@page import="model.bean.categories"%>
<%@page import="model.bean.song"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.songDAO"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<div class="searchform">
<%
String Search_song = request.getParameter("Search_song");
%>
  <form id="formsearch" name="formsearch" method="post" action="<%=request.getContextPath()%>/search">
    <span>
    <input name="Search_song"  value="<%if(Search_song != null){out.print(Search_song);}%>" class="editbox_search" id="editbox_search" maxlength="80" placeholder="Tìm kiếm bài hát" type="search" />
    </span>
    <input name="button_search" src="<%=request.getContextPath()%>/templates/public/images/search.jpg" class="button_search" type="image" />
  </form>
</div>
<div class="clr"></div>
<div class="gadget">
  <h2 class="star">Danh mục bài hát</h2>
  <div class="clr"></div>
  <ul class="sb_menu">
  <%
  catDAO CatDAO = new catDAO();
  ArrayList<categories> items = CatDAO.getCategories();
  if(items.size()>0){
	  for(categories obj : items){
  %>
    <li><a href="<%=request.getContextPath()%>/<%=StringUtil.makeSlug(obj.getNameCat())%>-<%=obj.getIdCat()%>"><%=obj.getNameCat()%></a></li>
    
    <%}}%>
  </ul>
</div>

<div class="gadget">
  <h2 class="star"><span>Bài hát mới</span></h2>
  <div class="clr"></div>
  <ul class="ex_menu">
  <%
  songDAO SongDAO = new songDAO();
  ArrayList<song> recentSong = SongDAO.getItems(6);
  if(items.size()>0){
	  for(song obj : recentSong){
  %>
    <li><a href="<%=request.getContextPath()%>/<%=StringUtil.makeSlug(obj.getCategory().getNameCat())%>/<%=StringUtil.makeSlug(obj.getName())%>-<%=obj.getId()%>"><%=obj.getName()%></a><br />
      <%if(obj.getPreviewText().length()>60){out.print(obj.getPreviewText().substring(0,60)+"...");}else{out.print(obj.getPreviewText());}%></li>
    
      <%}}%>
  </ul>
</div>