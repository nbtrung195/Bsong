<%@page import="model.bean.contact"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Quản lý liên hệ</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <% 
        String err = request.getParameter("err");
        String msg = request.getParameter("msg");
        if("1".equals(err)){
        	out.print("<p style=\"color:red\">ID không tồn tại</p>");
        }
        if("2".equals(msg)){
        	out.print("<p style=\"color:red\">Xóa thất bại</p>");
        }
        if("1".equals(msg)){
        	out.print("<p style=\"color:red\">Xóa thành công</p>");
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
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="post" action="<%=request.getContextPath()%>/admin/contact/search">
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
                                        <th>Tên </th>
                                        <th>Email</th>
                                        <th>Website</th>
                                        <th>message</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                               @ SuppressWarnings("unchecked")
                                ArrayList<contact> contacts = (ArrayList<contact>)request.getAttribute("contacts");
                                if(contacts!=null && contacts.size()>0){
                                	for(contact obj : contacts){
                                %>
                                    <tr>
                                        <td><%=obj.getId()%></td>
                                        <td class="center"><%=obj.getName()%></td>
                                        <td class="center"><%=obj.getEmail()%></td>
                                        <td class="center"><%=obj.getWebsite()%></td>
                                        <td class="center"><%=obj.getMessage()%></td>
                                        <td class="center">
                                        <%
                                        users Userr = (users)session.getAttribute("userLogin");
                                        if("admin".equals(Userr.getUsername())){
                                        %>
                                            <a href="<%=request.getContextPath()%>/admin/contact/del?id=<%=obj.getId()%>" title="" class="btn btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa');"><i class="fa fa-pencil" ></i> Xóa</a>
                                        <%}%>
                                        </td>
                                    </tr>
                                    <%}}%>
                                </tbody>
                            </table>
                            <div class="row">
                                <div class="col-sm-6">
                                <%
                                int NumberOfPages = (Integer)request.getAttribute("NumberOfPages");
                                int currentPage = (Integer)request.getAttribute("currentPage");
                                int offset = (Integer)request.getAttribute("offset");
                                int NumberOfItems = (Integer)request.getAttribute("NumberOfItems");
                                if(contacts!=null && contacts.size()>0){
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
                                            <li class="paginate_button previous" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/contact?page=1">Trang đầu</a></li>
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
                                            <li class="paginate_button <%=active%>" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/contact?page=<%=i%>"><%=i%></a></li>
											<%}%>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/contact?page=<%=NumberOfPages%>">Trang cuối</a></li>
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
    document.getElementById("contact").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>