<%@page import="model.dao.songDAO"%>
<%@page import="model.bean.categories"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/templates/admin/inc/header.jsp"%>
<%@ include file="/templates/admin/inc/leftbar.jsp"%>
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2>Quản lý danh mục</h2>
			</div>
		</div>
		<!-- /. ROW  -->
		<%
		songDAO SongDAO = new songDAO();
		int NumberOfPages = (Integer)request.getAttribute("NumberOfPages");
        int currentPage = (Integer)request.getAttribute("currentPage");
        int offset = (Integer)request.getAttribute("offset");
        int NumberOfItems = (Integer)request.getAttribute("NumberOfItems");
		String msg = request.getParameter("msg");
		String err = request.getParameter("err");
		if ("1".equals(msg)) {
			out.print("<p style= \"color:red\">Thêm danh mục thành công</p>");
		}
		if ("2".equals(msg)) {
			out.print("<p style= \"color:red\">Sửa danh mục thành công</p>");
		}
		if ("3".equals(msg)) {
			out.print("<p style= \"color:red\">Xóa danh mục thành công</p>");
		}
		if ("1".equals(err)) {
			out.print("<p style= \"color:red\">ID không tồn tại</p>");
		}
		if ("2".equals(err)) {
			out.print("<p style= \"color:red\">Xóa thất bại</p>");
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
									<a href="<%=request.getContextPath()%>/admin/cat/add"
										class="btn btn-success btn-md">Thêm</a>
								</div>
								<div class="col-sm-6" style="text-align: right;">
									<form method="post" action="<%=request.getContextPath()%>/admin/cat/search">
										<input type="submit" name="search" value="Tìm kiếm"
											class="btn btn-warning btn-sm" style="float: right" /> <input
											type="search" class="form-control input-sm" name = "Search"
											placeholder="Nhập tên bài hát"
											style="float: right; width: 300px;" />
										<div style="clear: both"></div>
									</form>
									<br />
								</div>
							</div>

							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<thead>
									<%
									@SuppressWarnings("unchecked")
									ArrayList<categories> ListCat = (ArrayList<categories>) request.getAttribute("cat");
									if (ListCat != null) {
									%>
									<tr>
										<th>ID</th>
										<th>Tên danh mục</th>
										<th>Số lượng bài hát</th>
										<th width="160px">Chức năng</th>
									</tr>
								</thead>
								<tbody>
									<%
									for (categories obj : ListCat) {
									%>
									<tr>
										<td><%=obj.getIdCat()%></td>
										<td class="center"><%=obj.getNameCat()%></td>
										<td class="center"><%=SongDAO.NumberOfItems(obj.getIdCat())%></td>
										<td class="center"><a
											href="<%=request.getContextPath()%>/admin/cat/edit?id=<%=obj.getIdCat()%>&page=<%=currentPage%>"
											title="" class="btn btn-primary"><i class="fa fa-edit "></i>
												Sửa</a> <a
											href="<%=request.getContextPath()%>/admin/cat/del?id=<%=obj.getIdCat()%> "
											onclick="return confirm('Bạn có chắc chắn muốn xóa')"
											title="" class="btn btn-danger"><i class="fa fa-pencil"></i>
												Xóa</a></td>
									</tr>
									<%
									}
									}
									%>
								</tbody>
							</table>
							<%
                            if(ListCat!=null && ListCat.size()>0){
                            %>
							<div class="row">
								<div class="col-sm-6">
									
									<%if(currentPage != NumberOfPages){%>
									<div class="dataTables_info" id="dataTables-example_info"
										style="margin-top: 27px">Hiển thị từ <%=offset+1%> đến <%=offset+4%> của <%=NumberOfItems%>
										truyện</div>
										<%}else{%>
										<div class="dataTables_info" id="dataTables-example_info"
										style="margin-top: 27px">Hiển thị từ <%=offset+1%> đến <%=NumberOfItems%> của <%=NumberOfItems%>
										truyện</div>
										<%}%>
								</div>
								<div class="col-sm-6" style="text-align: right;">
									<div class="dataTables_paginate paging_simple_numbers"
										id="dataTables-example_paginate">
										<ul class="pagination">
											<li class="paginate_button previous"
												aria-controls="dataTables-example" tabindex="0"
												id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/cat?page=1">
												Trang đầu</a></li>
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
											<li class="paginate_button <%=active%>"
												aria-controls="dataTables-example" tabindex="0"><a
												href="<%=request.getContextPath()%>/admin/cat?page=<%=i%>"><%=i%></a></li>
												<%}}%>
											<li class="paginate_button next"
												aria-controls="dataTables-example" tabindex="0"
												id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/cat?page=<%=NumberOfPages%>">Trang cuối</a></li>
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
	document.getElementById("category").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp"%>