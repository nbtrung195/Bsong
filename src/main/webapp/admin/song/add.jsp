<%@page import="model.bean.categories"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Thêm bài hát</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <%
        @SuppressWarnings("unchecked")
        ArrayList<categories> Categories = (ArrayList<categories>)request.getAttribute("Categories");
        String name = request.getParameter("name");
        String catId = request.getParameter("category");
        String preview = request.getParameter("preview");
        String detail = request.getParameter("detail");
        String youtube = request.getParameter("youtube");
        String err = request.getParameter("err");
        if("1".equals(err)){
        	out.print("<p style = \"color:red\">Có lỗi khi thêm</P>");
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
                                <form action="" role="form" method="post" enctype="multipart/form-data" id="form">
                                    <div class="form-group">
                                        <label for="name">Tên bài hát</label>
                                        <input type="text" id="name" value="<%if(name != null){out.print(name);}%>" name="name" class="form-control"  required="required"/>
                                    </div>
                                    <div class="form-group">
                                        <label for="category">Danh mục bài hát</label>
                                        <select id="category" name="category" class="form-control" >
                                        <% 
                                        if(Categories!=null && Categories.size()>0){
                                        	for( categories obj : Categories){
                                        %>
	                                        <option<%if(catId!=null && catId.equals(String.valueOf(obj.getIdCat()))){out.print(" selected");}%> value="<%=obj.getIdCat()%>"><%=obj.getNameCat()%></option>
											<%} }%>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="picture">Hình ảnh</label>
                                        <input type="file" name="picture" />
                                    </div>
                                    <div class="form-group">
                                        <label for="youtube">youtube</label>
                                        <textarea id="youtube" class="form-control" rows="3" name="youtube" required="required"><%if(youtube != null){out.print(youtube);}%></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label for="preview">Mô tả</label>
                                        <textarea id="preview" class="form-control" rows="3" name="preview" required="required"><%if(preview != null){out.print(preview);}%></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label for="detail">Chi tiết</label>
                                        <textarea id="detail" class="form-control" id="detail" rows="5" name="detail"><%if(detail != null){out.print(detail);}%></textarea>
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Thêm</button>
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
	$().ready(function(){
		var validator = $("#form").validate({
			errorPlacement : function(error,element){
				$(element).closest("form").find("label[for='" + element.attr("id") +"']").append(error);
			},
			messages:{
				name : {
					required : " (Tên bài hát không được để trống)"
				},
				preview : {
					required : " (Mô tả không được để trống)"
				},
				youtube: {
					required : " (Embedded không được để trống)"
				}
			}
		});
	});
</script>
<style>
	.error{color : red;}
</style>
<script type="text/javascript">
	var ckeditor = CKEDITOR.replace('detail');
</script>
<script>
    document.getElementById("song").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>