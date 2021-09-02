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
		users Userr = (users)session.getAttribute("userLogin");
        int currentPage = (Integer)request.getAttribute("currentPage");
        String msg = request.getParameter("msg");
        String err = request.getParameter("err");
        if("1".equals(msg)){
        	out.print("<p style = \"color:red\">Thêm người dùng thành công</p>");
        }
        if("2".equals(msg)){
        	out.print("<p style = \"color:red\">Sửa người dùng thành công</p>");
        }
        if("3".equals(msg)){
        	out.print("<p style = \"color:red\">xóa người dùng thành công</p>");
        }
        if("1".equals(err)){
        	out.print("<p style = \"color:red\">ID không tồn tại</p>");
        }
        if("2".equals(err)){
        	out.print("<p style = \"color:red\">Xóa người dùng thất bại</p>");
        }
        if("3".equals(err)){
        	out.print("<p style = \"color:red\">Bạn không có quyền thêm người dùng</p>");
        }
        if("4".equals(err)){
        	out.print("<p style = \"color:red\">Bạn không có quyền sửa người dùng</p>");
        }
        if("5".equals(err)){
        	out.print("<p style = \"color:red\">Bạn không có quyền xóa người dùng</p>");
        }
        %>
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
                                if("admin".equals(Userr.getUsername())){
                                %>
                                    <a href="<%=request.getContextPath()%>/admin/user/add" class="btn btn-success btn-md">Thêm</a>
                                <%}%>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="post" action="<%=request.getContextPath()%>/admin/user/search">
                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search" name="Search" class="form-control input-sm" placeholder="Nhập tên bài hát" style="float:right; width: 300px;" />
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
                                        	<a href="<%=request.getContextPath()%>/admin/user/edit?id=<%=obj.getId()%>&page=<%=currentPage%>"  title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                        	<%}else{%>
                                            <a href="<%=request.getContextPath()%>/admin/user/edit?id=<%=obj.getId()%>&page=<%=currentPage%>"  title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <a href="<%=request.getContextPath()%>/admin/user/del?id=<%=obj.getId()%>"  onclick="return confirm('Bạn có muốn xóa user không');" title="" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
                                       <%}}else{
                                       if(Userr.getUsername().equals(obj.getUsername())){
                                       %>
                                            <a href="<%=request.getContextPath()%>/admin/user/edit?id=<%=obj.getId()%>&page=<%=currentPage%>"  title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                        <%}}%>
                                        </td>
                                    </tr>
                                    <% }
                                }%>
                                </tbody>
                            </table>
                            <div class="row">
                                <div class="col-sm-6">
                                <%
                                int NumberOfPages = (Integer)request.getAttribute("NumberOfPages");
                                int offset = (Integer)request.getAttribute("offset");
                                int NumberOfItems = (Integer)request.getAttribute("NumberOfItems");
                                if(Users!=null && Users.size()>0){
                                	if(currentPage != NumberOfPages){
                                %>
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ <%=offset+1%> đến <%=offset+4%> của <%=NumberOfItems%> truyện</div>
                                <%}else{%>
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ <%=offset+1%> đến <%=NumberOfItems%> của <%=NumberOfItems%> truyện</div>
                                <%}}%>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                            <li class="paginate_button previous" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/user?page=1">Trang đầu</a></li>
                                           <%
                                           String active = "";
                                           for(int i=1;i<=NumberOfPages;i++){
                                        	   if(currentPage == i){
                                        		   active = "active";
                                        	   }
                                        	   else{
                                        		   active = "";
                                        	   }
                                           %>
                                            <li class="paginate_button <%=active%>" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/user?page=<%=i%>"><%=i%></a></li>
											<%}%>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/user?page=<%=NumberOfPages%>">Trang cuối</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>

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