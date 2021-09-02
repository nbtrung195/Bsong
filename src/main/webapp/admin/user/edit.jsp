<%@page import="model.bean.users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Sửa người dùng</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <%
        String username = request.getParameter("username");
        String fullname = request.getParameter("fullname");
        String err = request.getParameter("err");
        users Users = (users)request.getAttribute("User");
        if(Users != null){
        	username = Users.getUsername();
        	fullname = Users.getFullname();
        }
        if("1".equals(err)){
        	out.print("<p style = \"color:red\">sửa người dùng thất bại</p>");
        }
        if("2".equals(err)){
        	out.print("<p style = \"color:red\">Tên đăng nhập đã tồn tại</p>");
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
                                        <label for="username">Tên đăng nhập</label>
                                        <input type="text" id="username" value="<%if(username != null){out.print(username);}%>" readonly="readonly" name="username" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="password">Mật khẩu</label>
                                        <input type="password" id="password" value="" name="password" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="checkpassword">Nhập lại mật khẩu</label>
                                        <input type="password" id="checkpassword" value="" name="checkpassword" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="fullname">Họ tên</label>
                                        <input type="text" id="fullname" value="<%if(fullname != null){out.print(fullname);}%>" name="fullname" class="form-control" />
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Sửa</button>
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
			rules:{
				fullname : {
					required : true
				},
				password : {
					required : true,
					minlength : 6,
					maxlength : 15
				},
				checkpassword : {
					required : true,
					equalTo : "#password",
				}
			},
			messages:{
				fullname : {
					required : " (Họ tên không được để trống)"
				},
				password : {
					required : " (mật khẩu không được để trống)",
					minlength : " Mật khẩu quá ngắn",
					maxlength : " Mât khẩu quá dài"
				},
				checkpassword : {
					required : " (Nhập lại mật khẩu)",
					equalTo : " Mật khẩu không khớp"
				}
			}
		});
	});
</script>
<style>
	.error{color : red;}
</style>
<script>
    document.getElementById("user").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>