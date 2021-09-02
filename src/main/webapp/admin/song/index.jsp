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
        int NumberOfPages = (Integer)request.getAttribute("NumberOfPages");
        int currentPage = (Integer)request.getAttribute("currentPage");
        int offset = (Integer)request.getAttribute("offset");
        int NumberOfItems = (Integer)request.getAttribute("NumberOfItems");
        String err = request.getParameter("err");
        String msg = request.getParameter("msg");
        if("1".equals(err)){
        	out.print("<p style=\"color:red\">ID không tồn tại</P>");
        }
        if("2".equals(err)){
        	out.print("<p style=\"color:red\">Xóa thất bại</P>");
        }
        if("1".equals(msg)){
        	out.print("<p style=\"color:red\">Thêm bài hát thành công</P>");
        }
        if("2".equals(msg)){
        	out.print("<p style=\"color:red\">Sửa bài hát thành công</P>");
        }
        if("3".equals(msg)){
        	out.print("<p style=\"color:red\">Xóa bài hát thành công</P>");
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
                                    <a href="<%=request.getContextPath()%>/admin/song/add" class="btn btn-success btn-md">Thêm</a>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="post" action="<%=request.getContextPath()%>/admin/song/search">
                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search"  name="Search" class="form-control input-sm" placeholder="Nhập tên bài hát" style="float:right; width: 300px;" />
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
											<img width="200px"  src="<%=request.getContextPath()%>/files/<%=obj.getPicture()%>" alt="<%=obj.getName()%>"/>
                                        <%}%>
                                        </td>
                                        <td class="center">
                                            <a href="<%=request.getContextPath()%>/admin/song/edit?id=<%=obj.getId()%>&page=<%=currentPage%>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <a href="<%=request.getContextPath()%>/admin/song/del?id=<%=obj.getId()%>" onclick="return confirm('Bạn có chắc muốn xóa')" title="" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
                                	<%
                                }
                                }else{
                                	%>
                                	<tr><td colspan="6" align="center">Không có bảng nào được chọn<td></tr>
                                	<%
                                }
                                %>
                                </tbody>
                            </table>
                            <%
                            if(Song!=null && Song.size()>0){
                            %>
                            <div class="row">
                                <div class="col-sm-6">
                                <%if(currentPage != NumberOfPages){%>
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ <%=offset+1%> đến <%=offset+4%> của <%=NumberOfItems%> truyện</div>
                                <%}else{%>
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ <%=offset+1%> đến <%=NumberOfItems%> của <%=NumberOfItems%> truyện</div>
                                <%}%>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                            <li class="paginate_button previous " aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/song?page=<%=1%>">Trang đầu</a></li>
                                            <%
                                            String active = "";
                                            for(int i = 1 ; i <= NumberOfPages ; i++){
                                            	if(currentPage == i){
                                            		active = "active";
                                            	}
                                            	else{
                                            		active = "";
                                            	}
                                            %>
                                            <li class="paginate_button <%=active%>" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/song?page=<%=i%>"><%=i%></a></li>
											<%}}%>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/song?page=<%=NumberOfPages%>">Trang cuối</a></li>
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
    document.getElementById("song").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>