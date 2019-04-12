<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户信息管理</title>
<link rel="stylesheet" type="text/css" href="../ui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../ui/themes/icon.css">
<script type="text/javascript" src="../ui/jquery.min.js"></script>
<script type="text/javascript" src="../ui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../ui/locale/easyui-lang-zh_CN.js"></script>

<script>
function submitForm(){
	// submit the form    
	$('#ff').submit();
}
		$(function(){
			
			$('#ff').form({   //使普通表单成为ajax提交方式的表单。
			    url:"${pageContext.request.contextPath}/UserAction_regist",    
			    onSubmit: function(){    
			        // do some check  //进行一些校验 
			        // return false to prevent submit; 返回false就驳回表单提交
			        return true; 
			    },    
			    success:function(data){    
			    	$('#w').window('close'); //提交成功后关闭窗口
			    	$('#test').datagrid('reload'); //页面刷新
			    	$('#ff').form('clear'); //清除表单信息
			    }    
			});    
			
			$('#w').window('close'); //页面加载完毕关闭弹出窗口
			$('#test').datagrid({
				singleSelect:true, //是否只能选择一行
				title:'用户列表', //标题
				iconCls:'icon-tip', //标题图标
				/* width:700, //宽度
				height:350, //高度 */
				nowrap: false, //如果为true,就在一行中显示数据，节省性能
				striped: true, //是否显示隔行换色
				collapsible: true, //是否显示下拉隐藏
				url:'${pageContext.request.contextPath}/UserAction_list', //请求的路径
				sortName: 'user_id', //排序的字段
				sortOrder: 'asc', // asc/desc  排序规则
				remoteSort: false, //是否在后台数据库中就开始排序
				idField:'user_id', //指明哪一个字段是id列(选中哪一行的时候返回哪个对应的id给服务端)
				frozenColumns:[[ //冻结字段，就是不能动的一列(固定的)
	                {field:'ck',checkbox:true}, //就是支持复选框勾选
	                {title:'用户id',field:'user_id',width:80,sortable:true} //
				]],
				columns:[[ //其他列
					{field:'user_name',title:'用户名',width:120},
					{field:'user_code',title:'登录名',width:220,rowspan:2,sortable:true,}
				]],
				pagination:true,
				rownumbers:true,
				toolbar:[{
					id:'btnadd',
					text:'添加用户',
					iconCls:'icon-add',
					handler:function(){
						$('#ff').form('clear'); //每次点击添加用户时清除表单信息
						$('#btnsave').linkbutton('enable');
						$('#w').window('open'); //打开弹出窗口
					}
				},{
					id:'btncut',
					text:'修改用户',
					iconCls:'icon-cut',
					handler:function(){
						$('#btnsave').linkbutton('enable');
						//获得被选中的id
						var user_id = getSelected();
						//判断id是否为空
						if(!user_id){
							alert("请先选择用户");
							return;
						}
						//根据id回显数据
						//读取记录填充到表单中。数据参数可以是一个字符串或一个对象类型
						//去后台加载数据，返回的是个json
						$('#ff').form('load','${pageContext.request.contextPath}/UserAction_toEdit?user_id='+user_id);
						//打开一个窗口然后将信息回显
						$('#w').window('open');
					}
				},'-',{
					id:'btnsave',
					text:'删除用户',
					disabled:false, //设置是否  不能被选中
					iconCls:'icon-cancel',
					handler:function(){
						$('#btnsave').linkbutton('enable'); //点击完还能不能被选中
						//获取id
						var user_id = getSelected();
						//判断id是否为空
						if(!user_id){
							alert("请先选择用户");
							return;
						}
						//用ajax异步去数据库中删除数据
						$.post(
							"${pageContext.request.contextPath}/UserAction_delete",
							{"user_id":user_id},
							function(data){
								//执行成功后刷新页面	
								$('#test').datagrid('reload'); //页面刷新
							}
						);
					}
				}]
			});
			var p = $('#test').datagrid('getPager');
			$(p).pagination({
				onBeforeRefresh:function(){
					alert('before refresh');
				}
			});
		});
		function resize(){
			$('#test').datagrid('resize', {
				width:700,
				height:400
			});
		}
		//获得选中那行的信息
		function getSelected(){
			var selected = $('#test').datagrid('getSelected');
			if (selected){
				return selected.user_id;
			}
		}
		function getSelections(){
			var ids = [];
			var rows = $('#test').datagrid('getSelections');
			for(var i=0;i<rows.length;i++){
				ids.push(rows[i].code);
			}
			alert(ids.join(':'));
		}
		function clearSelections(){
			$('#test').datagrid('clearSelections');
		}
		function selectRow(){
			$('#test').datagrid('selectRow',2);
		}
		function selectRecord(){
			$('#test').datagrid('selectRecord','002');
		}
		function unselectRow(){
			$('#test').datagrid('unselectRow',2);
		}
		function mergeCells(){
			$('#test').datagrid('mergeCells',{
				index:2,
				field:'addr',
				rowspan:2,
				colspan:2
			});
		}
	</script>
</head>
<body>
<table id="test"></table>
<div id="w" class="easyui-window" title="My Window" iconCls="icon-save" style="width:500px;height:200px;padding:5px;">
	<form id="ff" method="post" novalidate>
		<input type="hidden" name="user_id" />
	
	        <div>
	            <label for="name">登录名称:</label>
	            <input class="easyui-validatebox" type="text" name="user_code" required="true"></input>
	        </div>
	        <div>
	            <label for="name">登录密码:</label>
	            <input class="easyui-validatebox" type="password" name="user_password" required="true"></input>
	        </div>
	        <div>
	            <label for="name">用户昵称:</label>
	            <input class="easyui-validatebox" type="text" name="user_name" required="true"></input>
	        </div>
	       
	        <div>
	            <input type="button" value="提交" onclick="submitForm()">
	        </div>
	    </form>	
</div>
</body>
</html>
