<%@page import="model.bean.song"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Quản lý bài hát</h2>
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
		<p style = color:red>Không có kết quả nào được tìm thấy, vui lòng kiểm tra lại tên bài hát</p>
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
                                    <a href="<%=request.getContextPath()%>/admin/song/add" class="btn btn-success btn-md">Thêm</a>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="post" action="">
                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search"  name="Search" value="<%if(name !=null){out.print(name);}%>" class="form-control input-sm" placeholder="Nhập tên bài hát" style="float:right; width: 300px;" />
                                        <div style="clear:both"></div>
                                    </form><br />
                                </div>
                            </div>

                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th >ID</th>
                                        <th >Tên bài hát</th>
                                        <th >Danh mục</th>
                                        <th >Lượt đọc</th>
                                        <th >Hình ảnh</th>
                                        <th width="160px" >Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <% 
                                @SuppressWarnings("unchecked")
                                ArrayList<song> Song = ( ArrayList<song>)request.getAttribute("songs");
                                if(Song!=null && Song.size()>0){
                                for(song obj : Song){
                                	%>
                                	<tr>
                                        <td ><%=obj.getId()%></td>
                                        <td class="center"><%=obj.getName()%></td>
                                        <td class="center"><%=obj.getCategory().getNameCat()%></td>
                                        <td class="center" ><%=obj.getCounter()%></td>
                                        <td class="center">
                                        <%
                                        if(obj.getPicture().isEmpty()){
                                        	out.print("<p>Chưa có hình ảnh</p>");
                                        }
                                        else{
                                        %>
											<img width="200px" height="200px" src="<%=request.getContextPath()%>/files/<%=obj.getPicture()%>" alt="<%=obj.getName()%>"/>
                                        <%}%>
                                        </td>
                                        <td class="center">
                                            <a href="<%=request.getContextPath()%>/admin/song/edit?id=<%=obj.getId()%>&category=<%=obj.getCategory().getIdCat()%>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <a href="<%=request.getContextPath()%>/admin/song/del?id=<%=obj.getId()%>" onclick="return confirm('Bạn có chắc muốn xóa')" title="" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
                                	<%}}%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <!--End Advanced Tables -->
            </div>
        </div>
    </div>
</div>
<script>
    document.getElementById("song").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>