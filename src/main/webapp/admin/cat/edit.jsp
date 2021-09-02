<%@page import="model.bean.categories"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp"%>
<%@ include file="/templates/admin/inc/leftbar.jsp"%>
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h2>Sửa danh mục</h2>
			</div>
		</div>
		<!-- /. ROW  -->
		<%
		String name = request.getParameter("name");
		String err = request.getParameter("err");
		categories Categories = (categories) request.getAttribute("category");
		if (Categories != null) {
			name = Categories.getNameCat();
		}

		if ("1".equals(err)) {
			out.print("<p style= \"color:red\">Sửa danh mục thất bại</p>");
		}
		%>
		<hr />
		<div class="row">
			<div class="col-md-12">
				<!-- Form Elements -->
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="row">
							<div class="col-md-12">
								<form action="" role="form" method="post" id="form">
									<div class="form-group">
										<label for="name">Tên danh mục</label> <input type="text"
											id="name" value="<%if (name != null) {out.print(name);}%>"
											name="name" class="form-control" required="required" />
									</div>
									<button type="submit" name="submit"
										class="btn btn-success btn-md">Sửa</button>
								</form>
							</div>
						</div>
					</div>
				</div>
				<!-- End Form Elements -->
			</div>
		</div>
		<!-- /. ROW  -->
	</div>
	<!-- /. PAGE INNER  -->
</div>
<script type="text/javascript">
	$()
			.ready(
					function() {
						var validator = $("#form")
								.validate(
										{
											errorPlacement : function(error,
													element) {
												$(element)
														.closest("form")
														.find(
																"label[for='"
																		+ element
																				.attr("id")
																		+ "']")
														.append(error);
											},
											messages : {
												name : {
													required : " (Tên danh mục không được để trống)"
												},
											}
										});
					});
</script>
<style>
.error {
	color: red;
}
</style>
<script>
	document.getElementById("category").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp"%>