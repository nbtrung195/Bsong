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
		String name = request.getParameter("Search");
		int NumberOfItems = (Integer)request.getAttribute("NumberOfItems");
		if(NumberOfItems>0){
		%>
		<p style = color:green>Có <%=NumberOfItems%> kết quả được tìm thấy</p>
		<%}else{%>
		<p style = color:red>Không có kết quả nào được tìm thấy, vui lòng kiểm tra lại tên danh mục</p>
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
									<a href="<%=request.getContextPath()%>/admin/cat/add"
										class="btn btn-success btn-md">Thêm</a>
								</div>
								<div class="col-sm-6" style="text-align: right;">
									<form method="post" action="">
										<input type="submit" name="search" value="Tìm kiếm"
											class="btn btn-warning btn-sm" style="float: right" /> <input
											type="search" class="form-control input-sm" name = "Search"
											placeholder="Nhập tên bài hát" value="<%if (name != null) {out.print(name);}%>"
											style="float: right; width: 300px;" />
										<div style="clear: both"></div>
									</form>
									<br />
								</div>
							</div>
							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<thead>
									<tr>
										<th>ID</th>
										<th>Tên danh mục</th>
										<th width="160px">Chức năng</th>
									</tr>
								</thead>
								<tbody>
									<%
									@SuppressWarnings("unchecked")
									ArrayList<categories> ListCat = (ArrayList<categories>) request.getAttribute("cat");
									if (ListCat != null && ListCat.size()>0) {
									for (categories obj : ListCat) {
									%>
									<tr>
										<td><%=obj.getIdCat()%></td>
										<td class="center"><%=obj.getNameCat()%></td>
										<td class="center"><a
											href="<%=request.getContextPath()%>/admin/cat/edit?id=<%=obj.getIdCat()%>"
											title="" class="btn btn-primary"><i class="fa fa-edit "></i>
												Sửa</a> <a
											href="<%=request.getContextPath()%>/admin/cat/del?id=<%=obj.getIdCat()%> "
											onclick="return confirm('Bạn có chắc chắn muốn xóa')"
											title="" class="btn btn-danger"><i class="fa fa-pencil"></i>
												Xóa</a></td>
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
	document.getElementById("category").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp"%>