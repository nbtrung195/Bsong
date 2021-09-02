<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
    <div class="article">
      <h2><span>Liên hệ</span></h2>
      <div class="clr"></div>
      <%
      String msg = request.getParameter("msg");
      String err = request.getParameter("err");
      if("1".equals(msg)){
    	  out.print("<h1 style=\"color:red\">Gửi thông tin thành công</h1>");      }
      if("1".equals(err)){
    	  out.print("<h1 style=\"color:red\">Gửi thông tin thất bại</h1>");
      }
      String name = request.getParameter("name");
	  String email = request.getParameter("email");
	  String website = request.getParameter("website");
	  String message = request.getParameter("message");
      %>
      <p>Khi bạn có thắc mắc, vui lòng gửi liên hệ, chúng tôi sẽ phản hồi trong thời gian sớm nhất.</p>
    </div>
    <div class="article">
      <h2>Gửi liên hệ đến chúng tôi</h2>
      <div class="clr"></div>
      <form action="" method="post" id="form">
        <ol>
          <li>
            <label for="name">Họ tên (required)</label>
            <input type="text" id="name" value="<%if(name != null){out.print(name);}%>" name="name" class="text" />
          </li>
          <li>
            <label for="email">Email (required)</label>
            <input type="text"  id="email" value="<%if(email != null){out.print(email);}%>" name="email" class="text" />
          </li>
          <li>
            <label for="website">Website</label>
            <input type="text"  id="website" value="<%if(website != null){out.print(website);}%>" name="website" class="text" />
          </li>
          <li>
            <label for="message">Nội dung</label>
            <textarea id="message" name="message" rows="8" cols="50"><%if(message != null){out.print(message);}%></textarea>
          </li>
          <li>
            <input type="image" name="imageField" id="imageField" src="<%=request.getContextPath()%>/templates/public/images/submit.gif" class="send" />
            <div class="clr"></div>
          </li>
        </ol>
      </form>
    </div>
  </div>
  <div class="sidebar">
  <%@ include file="/templates/public/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<script type="text/javascript">
	$().ready(function(){
		var validator = $("#form").validate({
			errorPlacement : function(error,element){
				$(element).closest("form").find("label[for='" + element.attr("id") +"']").append(error);
			},
			rules:{
				name:{
					required : true
				},
				email:{
					required : true,
					email : true
				},
			},
			messages:{
				name : {
					required : " (Họ tên không được để trống)"
				},
				email : {
					required : " (email không được để trống)",
					email : " (email không hợp lệ)"
				},
			}
		});
	});
</script>
<style>
	.error{color : red;}
</style>
<script type="text/javascript">
	var ckeditor = CKEDITOR.replace('message');
</script>
<script>
    document.getElementById("contact_action").classList.add('active');
</script>
<%@ include file="/templates/public/inc/footer.jsp" %>
