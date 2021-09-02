<%@page import="model.bean.users"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Quản lý người dùng</h2>
            </div>
        </div>
        <!-- /. ROW  -->
       <%
        String name = request.getParameter("Search");
        int NumberOfItems = (Integer)request.getAttribute("NumberOfItems");
		if(NumberOfItems>0){
        %>
        <p style = color:green>Có <%=NumberOfItems%> kết quả được tìm thấy</p>
		<%}else{%>
		<p style = color:red>Không có kết quả nào được tìm thấy, vui lòng kiểm tra lại tên người dùng</p>
		<%}%>
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Advanced Tables -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="table-responsive">
                            <div class="row">
                                <div class="col-sm-6">
                                <%
                                users Userr = (users)session.getAttribute("userLogin");
                                if("admin".equals(Userr.getUsername())){
                                %>
                                    <a href="<%=request.getContextPath()%>/admin/user/add" class="btn btn-success btn-md">Thêm</a>
                                <%}%>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="post" action="">
                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search" name="Search" value="<%if(name != null){out.print(name);}%>" class="form-control input-sm" placeholder="Nhập tên bài hát" style="float:right; width: 300px;" />
                                        <div style="clear:both"></div>
                                    </form><br />
                                </div>
                            </div>

                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên đăng nhập</th>
                                        <th>Họ tên</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                                @SuppressWarnings("unchecked")
                                ArrayList<users> Users = (ArrayList<users>)request.getAttribute("users");
                                if(Users!=null && Users.size()>0){
                                	for(users obj : Users){
                                %>
                                    <tr>
                                        <td><%=obj.getId()%></td>
                                        <td class="center"><%=obj.getUsername()%></td>
                                        <td class="center"><%=obj.getFullname()%></td>
                                        <td class="center">
                                        <% 
                                        if("admin".equals(Userr.getUsername())){
                                        	if(Userr.getUsername().equals(obj.getUsername())){
                                        %>
                                        	<a href="<%=request.getContextPath()%>/admin/user/edit?id=<%=obj.getId()%>"  title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                        	<%}else{%>
                                            <a href="<%=request.getContextPath()%>/admin/user/edit?id=<%=obj.getId()%>"  title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <a href="<%=request.getContextPath()%>/admin/user/del?id=<%=obj.getId()%>"  onclick="return confirm('Bạn có muốn xóa user không');" title="" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
                                       <%}}else{
                                       if(Userr.getUsername().equals(obj.getUsername())){
                                       %>
                                            <a href="<%=request.getContextPath()%>/admin/user/edit?id=<%=obj.getId()%>"  title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                        <%}}%>
                                        </td>
                                    </tr>
                                    <% }
                                }%>
                                </tbody>
                            </table>
                    </div>
                </div>
                <!--End Advanced Tables -->
            </div>
        </div>
    </div>
</div>
<script>
    document.getElementById("user").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>