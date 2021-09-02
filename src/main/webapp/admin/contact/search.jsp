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
        String name = request.getParameter("Search");
        int NumberOfItems = (Integer)request.getAttribute("NumberOfItems");
		if(NumberOfItems>0){
        %>
        <p style = color:green>Có <%=NumberOfItems%> kết quả được tìm thấy</p>
		<%}else{%>
		<p style = color:red>Không có kết quả nào được tìm thấy, vui lòng kiểm tra lại tên liên hệ</p>
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