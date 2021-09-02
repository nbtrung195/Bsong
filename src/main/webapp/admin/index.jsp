<%@page import="model.dao.usersDAO"%>
<%@page import="model.bean.song"%>
<%@page import="model.dao.songDAO"%>
<%@page import="model.bean.categories"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.catDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>TRANG QUẢN TRỊ VIÊN</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <hr />
        <div class="row">
            <div class="col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-back noti-box">
                    <span class="icon-box bg-color-green set-icon">
                    <i class="fa fa-bars"></i>
                </span>
                    <div class="text-box">
                        <p class="main-text"><a href="<%=request.getContextPath()%>/admin/cat" title="">Quản lý danh mục</a></p>
                        <%
                        catDAO CatDAO = new catDAO();
                		ArrayList<categories> cat = CatDAO.getCategories();
                        %>
                        <p class="text-muted">Có <%=cat.size()%> danh mục</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-back noti-box">
                    <span class="icon-box bg-color-blue set-icon">
                    <i class="fa fa-bell-o"></i>
                </span>
                    <div class="text-box">
                        <p class="main-text"><a href="<%=request.getContextPath()%>/admin/song" title="">Quản lý bài hát</a></p>
                        <%
                        songDAO SongDAO = new songDAO();
                        ArrayList<song> song = SongDAO.getItems();
                        %>
                        <p class="text-muted">Có <%=song.size()%> bài hát</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-back noti-box">
                    <span class="icon-box bg-color-brown set-icon">
                    <i class="fa fa-rocket"></i>
                </span>
                    <div class="text-box">
                        <p class="main-text"><a href="<%=request.getContextPath()%>/admin/user" title="">Quản lý người dùng</a></p>
                        <%
                        usersDAO UserDAO = new usersDAO();
                        ArrayList<users> users = UserDAO.getItems();
                        %>
                        <p class="text-muted">Có <%=users.size()%> người dùng</p>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
<script>
    document.getElementById("index").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>