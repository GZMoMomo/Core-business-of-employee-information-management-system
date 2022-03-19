<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>员工列表</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<script type="text/javascript" src="${APP_PATH}/static/js/jquery.min.js"></script>
<link rel="stylesheet"
	href="${APP_PATH}/static/bootstrap-3.4.1-dist/css/bootstrap.min.css"
	integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu"
	crossorigin="anonymous">
<script
	src="${APP_PATH}/static/bootstrap-3.4.1-dist/js/bootstrap.min.js"
	integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd"
	crossorigin="anonymous"></script>
</head>
<body>
	<!-- 员工修改模态框-->
	<div class="modal fade" id="empUpateModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">员工修改</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="emp_form">
						<div class="form-group">
							<label for="empName_add_input" class="col-sm-2 control-label">empName</label>
							<div class="col-sm-10">
								<p type="text" name="empName" class="form-control-static"
									id="empName_update_static" placeholder="empName"></p>
								<span id="" class="help-block"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="email_add_input" class="col-sm-2 control-label">email</label>
							<div class="col-sm-10">
								<input type="text" name="email" class="form-control"
									id="email_update_input" placeholder="email@crud.com"> <span
									id="" class="help-block"></span>

							</div>
						</div>
						<div class="form-group">
							<label for="gender_add_input" class="col-sm-2 control-label">gender</label>
							<div class="col-sm-10">
								<label class="radio-inline"> <input type="radio"
									name="gender" id="gender_update_input1" value="M"
									checked="checked"> 男
								</label> <label class="radio-inline"> <input type="radio"
									name="gender" id="gender_update_input2" value="F"> 女
								</label>
							</div>
						</div>
						<div class="form-group">
							<label for="gender_add_input" class="col-sm-2 control-label">deptName</label>
							<div class="col-sm-4">
								<!-- 提交部门id -->
								<select class="form-control" name="dId" id="dept_update_select">

								</select>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
							<button type="button" class="btn btn-primary" id="emp_update_btn">Save
								changes</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>


	<!-- 员工添加模态框-->
	<div class="modal fade" id="empAddModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">员工添加</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="emp_form">
						<div class="form-group">
							<label for="empName_add_input" class="col-sm-2 control-label">empName</label>
							<div class="col-sm-10">
								<input type="text" name="empName" class="form-control"
									id="empName_add_input" placeholder="empName"> <span
									id="" class="help-block"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="email_add_input" class="col-sm-2 control-label">email</label>
							<div class="col-sm-10">
								<input type="text" name="email" class="form-control"
									id="email_add_input" placeholder="email@crud.com"> <span
									id="" class="help-block"></span>

							</div>
						</div>
						<div class="form-group">
							<label for="gender_add_input" class="col-sm-2 control-label">gender</label>
							<div class="col-sm-10">
								<label class="radio-inline"> <input type="radio"
									name="gender" id="gender_add_input1" value="M"
									checked="checked"> 男
								</label> <label class="radio-inline"> <input type="radio"
									name="gender" id="gender_add_input2" value="F"> 女
								</label>
							</div>
						</div>
						<div class="form-group">
							<label for="gender_add_input" class="col-sm-2 control-label">deptName</label>
							<div class="col-sm-4">
								<!-- 提交部门id -->
								<select class="form-control" name="dId" id="dept_add_select">

								</select>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
							<button type="button" class="btn btn-primary" id="emp_save_btn">Save
								changes</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>


	<!-- 搭建显示页面 -->
	<div class="container">
		<!-- 标题行 -->
		<div class="row">
			<div class="col-md-12">
				<h1>SSM CRUD</h1>
			</div>
		</div>

		<!-- 按钮行 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-primary" id="emp_add_modal_btn">新增</button>
				<button class="btn btn-danger" id="emp_delete_all_btn">批量删除</button>
			</div>
		</div>


		<!-- 表格行 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" id="emps_table">
					<thead>
						<tr>
						    <th><input type="checkbox" id="check_all"></th>
							<th>#</th>
							<th>empName</th>
							<th>gender</th>
							<th>email</th>
							<th>deptName</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>

					</tbody>
				</table>
			</div>
		</div>

		<!-- 分页信息行 -->
		<div class="row">
			<div class="col-md-6" id="page_info_area"></div>

			<!-- 分页信息 -->
			<div class="col-md-6" id="page_nav_area"></div>
		</div>
	</div>
	<script type="text/javascript">
		//1.页面加载完成以后，直接发送一个ajax请求，要到分页数据

		var totalRecord,currentPage;

		$(function() {
			to_page(1);
		});

		function to_page(pn) {
			$.ajax({
				url : "${APP_PATH}/emps",
				data : "pn=" + pn,
				type : "GET",
				success : function(result) {
					// console.log(result);
					build_emps_table(result);
					build_page_info(result);
					build_page_nav(result);

				}
			});
		}

		function build_emps_table(result) {
			//清空table
			$("#emps_table tbody").empty();

			var emps = result.extend.pageInfo.list;
			$.each(emps, function(index, item) {
				var ehckBoxTd=$("<td><input type='checkbox' class='check_item'/></td>");
				var empIdTd = $("<td></td>").append(item.empId);
				var empNameTd = $("<td></td>").append(item.empName);
				var genderTd = $("<td></td>").append(
						item.gender == 'M' ? '男' : '女');
				var emailTd = $("<td></td>").append(item.email);
				var deptNameTd = $("<td></td>")
						.append(item.department.deptName);

				var editBtn = $("<button></button>").addClass(
						"btn btn-primary btn-sm edit_btn").append(
						$("<spand></spand>").addClass(
								"glyphicon glyphicon-pencil")).append("编辑");
				//为编辑按钮添加一个自定义属性，表示员工id
				editBtn.attr("edit_id", item.empId);
				var delBtn = $("<button></button>").addClass(
						"btn btn-danger btn-sm delete_btn").append(
						$("<spand></spand>").addClass(
								"glyphicon glyphicon-trash")).append("删除");
				//为删除按钮添加一个自定义属性 表示员工id
				delBtn.attr("delete_id", item.empId);
				var btnTd = $("<td></td>").append(editBtn).append(" ").append(
						delBtn);
				$("<tr></tr>").append(ehckBoxTd).append(empIdTd).append(empNameTd).append(
						genderTd).append(emailTd).append(deptNameTd).append(
						btnTd).appendTo("#emps_table tbody");
			});
		}
		//显示分页信息
		function build_page_info(result) {
			$("#page_info_area").empty();
			$("#page_info_area").append(
					"当前" + result.extend.pageInfo.pageNum + "页,总"
							+ result.extend.pageInfo.pages + "页,总"
							+ result.extend.pageInfo.total + "条记录");
			totalRecord = result.extend.pageInfo.total;
		    currentPage=result.extend.pageInfo.pageNum;
		}

		//解析显示分页条
		function build_page_nav(result) {
			$("#page_nav_area").empty();
			var ul = $("<ul></ul>").addClass("pagination");

			var firstPageLi = $("<li></li>").append(
					$("<a></a>").append("首页").attr("href", "#"));
			var prePageLivar = $("<li></li>").append(
					$("<a></a>").append("&laquo;"));
			if (result.extend.pageInfo.hasPreviousPage == false) {
				firstPageLi.addClass("disabled");
				prePageLivar.addClass("disabled");
			} else {
				//添加点击翻页事件
				firstPageLi.click(function() {
					to_page(1);
				});
				prePageLivar.click(function() {
					to_page(result.extend.pageInfo.pageNum - 1);
				});
			}

			var nextPageLivar = $("<li></li>").append(
					$("<a></a>").append("&raquo;"));
			var lastPageLi = $("<li></li>").append(
					$("<a></a>").append("末页").attr("href", "#"));
			if (result.extend.pageInfo.hasNextPage == false) {
				nextPageLivar.addClass("disabled");
				lastPageLi.addClass("disabled");
			} else {
				//添加点击翻页事件
				nextPageLivar.click(function() {
					to_page(result.extend.pageInfo.pageNum + 1);
				});
				lastPageLi.click(function() {
					to_page(result.extend.pageInfo.pages);
				});
			}

			var emps = result.extend.pageInfo.navigatepageNums;
			ul.append(firstPageLi).append(prePageLivar);
			$.each(emps, function(index, item) {
				var numLi = $("<li></li>").append($("<a></a>").append(item));
				if (result.extend.pageInfo.pageNum == item) {
					numLi.addClass("active");
				}
				numLi.click(function() {
					to_page(item);
				});
				ul.append(numLi);
			});
			ul.append(nextPageLivar).append(lastPageLi);
			var navEle = $("<nav></nav>").append(ul);
			$("#page_nav_area").append(ul);
		}

		//清空检验状态
		function reset(ele) {
			$(ele)[0].reset();
			$(ele).find("*").removeClass("has-success has-error");
			$(ele).find(".help-block").text("");
		}

		//员工添加按钮
		$("#emp_add_modal_btn").click(function() {
			//发送ajax请求 查出部门信息 显示在下拉列表中
			reset("#empAddModal form");
			getDepts("#dept_add_select");
			//弹出模态框
			$("#empAddModal").modal({

			});
		});

		//查询所有的部门信息
		function getDepts(ele) {
			$(ele).empty();
			$.ajax({
				url : "${APP_PATH}/depts",
				type : "GET",
				success : function(result) {
					//console.log(result);
					$.each(result.extend.depts, function(index, item) {
						var optionEle = $("<option></option>").append(
								item.deptName).attr("value", item.deptId);
						optionEle.appendTo(ele);
					});
				}
			});
		}

		//校验表单数据
		//实时监听
		$("#empName_add_input").bind(
				"input propertychange",
				function() {
					//发送ajax请求校验用户名是否重复

					var empName = this.value;
					$.ajax({
						url : "${APP_PATH}/checkuser",
						data : "empName=" + empName,
						type : "POST",
						success : function(result) {
							if (result.code == 100) {
								show_validate_msg("#empName_add_input",
										"success", "用户名可用");
								$("#emp_save_btn").attr("ajax-va", "success");
							} else {
								show_validate_msg("#empName_add_input",
										"error", result.extend.va_msg);
								$("#emp_save_btn").attr("ajax-va", "error");
							}
						}
					});
				});

		$("#email_add_input").bind("input propertychange", function() {
			var email = $("#email_add_input").val();
			var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if (!regEmail.test(email)) {
				//alert("邮箱格式不正确");
				//先清空元素之前的样式
				show_validate_msg("#email_add_input", "error", "邮箱格式有误!")
				return false;
			} else {
				show_validate_msg("#email_add_input", "success", "邮箱格式正确")
			}
		});
		function validate_add_form() {
			/* var empName = $("#empName_add_input").val();
			var regName = /(^[a-zA-Z0-9_-]{3,16}$)|(^[\u2E80-\u9FFF])/;
			if (!regName.test(empName)) {
				//alert("用户名有误!")
				//先清空样式
				show_validate_msg("#empName_add_input","error","用户名有误!")
				return false;
			}else{
				show_validate_msg("#empName_add_input","success","用户名可用")
				
			} */

			var email = $("#email_add_input").val();
			var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if (!regEmail.test(email)) {
				//alert("邮箱格式不正确");
				//先清空元素之前的样式
				show_validate_msg("#email_add_input", "error", "邮箱格式有误!")
				return false;
			} else {
				show_validate_msg("#email_add_input", "success", "邮箱格式可用")
			}
			return true;
		}

		function show_validate_msg(ele, status, msg) {
			//清除元素状态
			$(ele).parent().removeClass("has-success has-error");
			$(ele).next("span").text("");
			if ("success" == status) {
				$(ele).parent().addClass("has-success");
				$(ele).next("span").text(msg);

			} else if ("error" == status) {
				$(ele).parent().addClass("has-error");
				$(ele).next("span").text(msg);

			}
		}

		/*  $("#empName_add_input").change(function(){
			//发送ajax请求校验用户名是否重复
			var empName=this.value;
			$.ajax({
				url:"${APP_PATH}/checkuser",
				data:"empName="+empName,
				type:"POST",
				success:function(result){
					if(result.code==100){
						  show_validate_msg("#empName_add_input","success","用户名可用");
					  }else{
						  show_validate_msg("#empName_add_input","error","用户名重复");
					  }
				}
			});
		});  */

		$("#emp_save_btn")
				.click(
						function() {
							//提交表单数据给服务器保存
							//检验要提交的数据
							if (!validate_add_form()) {
								return false;
							}
							if ($(this).attr("ajax-va") == "error") {
								//show_validate_msg("#empName_add_input","error","用户名小于三位或用户名重复");
								return false
							}
							//发送ajax请求
							$
									.ajax({
										url : "${APP_PATH}/emp",
										type : "POST",
										data : $("#empAddModal form")
												.serialize(),
										success : function(result) {

											if (result.code == 100) {
												alert(result.msg);
												$("#empAddModal").modal('hide');
												//显示最后一页
												to_page(totalRecord);
												//清空状态
												/*  $("#empName_add_input").parent().removeClass("has-success has-error");
												$("#empName_add_input").next("span").text("");
												$("#email_add_input").parent().removeClass("has-success has-error");
												$("#email_add_input").next("span").text("");
												$("#dept_add_select").empty();
												document.getElementById("emp_form").reset(); */
											} else {
												//显示失败信息
												//console.log(result);
												if (undefined != result.extend.errorFields.email) {
													//显示邮箱错误信息
													show_validate_msg(
															"#email_add_input",
															"error",
															result.extend.errorFields.email);
												}
												if (undefined != result.extend.errorFields.empName) {
													//显示员工错误信息
													show_validate_msg(
															"#empName_add_input",
															"error",
															result.extend.errorFields.empName);
												}
											}
										}
									});
						})

		//用.on绑定事件 绑定元素后代	
		//单个删除按钮
		$(document).on("click",".delete_btn",function(){
			var empName = $(this).parents("tr").find("td:eq(2)").text();
		    var empId = $(this).attr("delete_id");
			if(confirm("确认删除【"+empName+"】吗？")){
		    	//发送ajax请求删除
		    	$.ajax({
		    		url:"${APP_PATH}/emp/"+empId,
		    		type:"DELETE",
		    		success:function(result){
		    			alert(result.msg);
		    			to_page(currentPage);
		    		}
		    	});
		    }
		});
		
		//编辑按钮
		$(document).on("click", ".edit_btn", function() {
			//reset("#empUpateModal form");
			//查询部门信息;
			getDepts("#dept_update_select");
			//查询员工信息
			getEmp($(this).attr("edit_id"));
			//传递id给更新按钮
			$("#emp_update_btn").attr("edit_id",$(this).attr("edit_id"));
			//弹出模态框
			$("#empUpateModal").modal({

			});
		});

		//查询员工信息
		function getEmp(id) {
			$.ajax({
				url : "${APP_PATH}/emp/" + id,
				type : "GET",
				success : function(result) {
					var empEle = result.extend.emp;
					$("#empName_update_static").text(empEle.empName);
					$("#email_update_input").val(empEle.email);
					$("#empUpateModal input[name=gender]").val(
							[ empEle.gender ]);
					$("#empUpateModal select").val([ empEle.dId ]);
				}
			});
		}

		//点击更新
		//实时监听
		/* $("#email_update_input").bind(
				"input propertychange",function() {
					//验证邮箱是否合法
					var email = $("#email_update_input").val();
					var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
					if (!regEmail.test(email)) {
						//alert("邮箱格式不正确");
						//先清空元素之前的样式
						show_validate_msg("#email_update_input", "error", "邮箱格式有误!")
						return false;
					} else {
						show_validate_msg("#email_update_input", "success", "邮箱格式可用")
					}}); */
		
		$("#emp_update_btn").click(function() {
			//验证邮箱是否合法
			var email = $("#email_update_input").val();
			var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if (!regEmail.test(email)) {
				//alert("邮箱格式不正确");
				//先清空元素之前的样式
				show_validate_msg("#email_update_input", "error", "邮箱格式有误!")
				return false;
			} else {
				show_validate_msg("#email_update_input", "success", "邮箱格式可用")
			}
			$.ajax({
				url:"${APP_PATH}/emp/"+$(this).attr("edit_id"),
				type:"PUT",
				data:$("#empUpateModal form").serialize(),
				success:function(result){
					alert(result.msg);
					$("#empUpateModal").modal('hide');
					//显示最后一页
					to_page(currentPage);
				}
			});
		});
					
		//全选、全不选功能
		$("#check_all").click(function(){
			//用prop获取dom原生的属性，attr获取自定义属性
			
			$(".check_item").prop("checked",$(this).prop("checked"));
		});
		
		$(document).on("click",".check_item",function(){
			var flag = $(".check_item:checked").length==$(".check_item").length;
		    $("#check_all").prop("checked",flag);
		});
		
		//批量删除
		$("#emp_delete_all_btn").click(function(){
			var empNames="";
			var del_idstr="";
			$.each($(".check_item:checked"),function(){
				empNames+=$(this).parents("tr").find("td:eq(2)").text()+",";
				del_idstr+=$(this).parents("tr").find("td:eq(1)").text()+"-";
			});
			empNames=empNames.substring(0,empNames.length-1);
			del_idstr=del_idstr.substring(0,del_idstr.length-1);
			if(confirm("确认删除【"+empNames+"】吗？")){
				$.ajax({
					url:"${APP_PATH}/emp/"+del_idstr,
					type:"DELETE",
					success:function(result){
						alert(result.msg);
						to_page(currentPage);
					}
				});
			}
			
		});
	</script>
</body>
</html>