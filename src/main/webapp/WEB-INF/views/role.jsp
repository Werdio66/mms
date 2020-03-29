<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>角色</title>
    <jsp:include page="/common/backend_common.jsp" />
    <link rel="stylesheet" href="/ztree/zTreeStyle.css" type="text/css">
    <link rel="stylesheet" href="/assets/css/bootstrap-duallistbox.min.css" type="text/css">
    <script type="text/javascript" src="/ztree/jquery.ztree.all.min.js"></script>
    <script type="text/javascript" src="/assets/js/jquery.bootstrap-duallistbox.min.js"></script>
    <style type="text/css">
        .bootstrap-duallistbox-container .moveall, .bootstrap-duallistbox-container .removeall {
            width: 50%;
        }
        .bootstrap-duallistbox-container .move, .bootstrap-duallistbox-container .remove {
            width: 49%;
        }
    </style>
</head>
<body class="no-skin" youdao="bind" style="background: white">
<input id="gritter-light" checked="" type="checkbox" class="ace ace-switch ace-switch-5"/>
<div class="page-header">
    <h1>
        角色管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            维护角色与用户, 角色与权限关系
        </small>
    </h1>
</div>
<div class="main-content-inner">
    <div class="col-sm-3">
        <div class="table-header">
            角色列表&nbsp;&nbsp;
            <a class="green" href="#">
                <i class="ace-icon fa fa-plus-circle orange bigger-130 role-add"></i>
            </a>
        </div>
        <div id="roleList"></div>
    </div>
    <div class="col-sm-9">
        <div class="tabbable" id="roleTab">
            <ul class="nav nav-tabs">
                <li class="active">
                    <a data-toggle="tab" href="#roleAclTab">
                        角色与权限
                    </a>
                </li>
                <li>
                    <a data-toggle="tab" href="#roleUserTab">
                        角色与用户
                    </a>
                </li>
            </ul>
            <div class="tab-content">
                <div id="roleAclTab" class="tab-pane fade in active">
                    <ul id="roleAclTree" class="ztree"></ul>
                    <button class="btn btn-info saveRoleAcl" type="button">
                        <i class="ace-icon fa fa-check bigger-110"></i>
                        保存
                    </button>
                </div>

                <div id="roleUserTab" class="tab-pane fade" >
                    <div class="row">
                        <div class="box1 col-md-6">待选用户列表</div>
                        <div class="box1 col-md-6">已选用户列表</div>
                    </div>
                    <select multiple="multiple" size="10" name="roleUserList" id="roleUserList" >
                    </select>
                    <div class="hr hr-16 hr-dotted"></div>
                    <button class="btn btn-info saveRoleUser" type="button">
                        <i class="ace-icon fa fa-check bigger-110"></i>
                        保存
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="dialog-role-form" style="display: none;">
    <form id="roleForm">
        <table class="table table-striped table-bordered table-hover dataTable no-footer" role="grid">
            <tr>
                <td><label for="roleName">名称</label></td>
                <td>
                    <input type="text" name="name" id="roleName" value="" class="text ui-widget-content ui-corner-all">
                    <input type="hidden" name="id" id="roleId"/>
                </td>
            </tr>
            <tr>
                <td><label for="roleStatus">状态</label></td>
                <td>
                    <select id="roleStatus" name="status" data-placeholder="状态" style="width: 150px;">
                        <option value="1">可用</option>
                        <option value="0">冻结</option>
                    </select>
                </td>
            </tr>
            <td><label for="roleRemark">备注</label></td>
            <td><textarea name="remark" id="roleRemark" class="text ui-widget-content ui-corner-all" rows="3" cols="25"></textarea></td>
            </tr>
        </table>
    </form>
</div>
<script id="roleListTemplate" type="x-tmpl-mustache">
<ol class="dd-list ">
    {{#roleList}}
        <li class="dd-item dd2-item role-name" id="role_{{id}}" href="javascript:void(0)" data-id="{{id}}">
            <div class="dd2-content" style="cursor:pointer;">
            {{name}}
            <span style="float:right;">
                <a class="green role-edit" href="#" data-id="{{id}}" >
                    <i class="ace-icon fa fa-pencil bigger-100"></i>
                </a>
                &nbsp;
                <a class="red role-delete" href="#" data-id="{{id}}" data-name="{{name}}">
                    <i class="ace-icon fa fa-trash-o bigger-100"></i>
                </a>
            </span>
            </div>
        </li>
    {{/roleList}}
</ol>
</script>

<script id="selectedUsersTemplate" type="x-tmpl-mustache">
{{#userList}}
    <option value="{{id}}" selected="selected">{{username}}</option>
{{/userList}}
</script>

<script id="unSelectedUsersTemplate" type="x-tmpl-mustache">
{{#userList}}
    <option value="{{id}}">{{username}}</option>
{{/userList}}
</script>

<script type="application/x-javascript">

    // ===================================== 角色信息 ==============================

    // 存储角色列表
    var roleList;
    // 存储 map 格式的角色信息
    var roleMap = {};

    // 取出角色的 HTML，使用 mustache 渲染
    var roleListTemplate = $('#roleListTemplate').html();
    Mustache.parse(roleListTemplate);

    loadRoleList();

    function loadRoleList() {
        console.log('加载角色列表：');
        $.ajax({
            url : '/sys/role/queryAll.json',
            method : 'get',
            data : {},
            success : function (result) {
                console.log("返回的对象：", result);
                if (result.rec){
                    roleList = result.data;
                    // 渲染列表
                    var rendered = Mustache.render(roleListTemplate, {roleList: result.data});
                    // console.log(rendered);
                    $("#roleList").html(rendered);
                    $.each(roleList, function (i, role) {
                        roleMap[role.id] = role;
                    });
                    console.log("所有的角色信息：", roleMap);
                    // 绑定角色点击的事件
                    bindRoleClick();
                }else {
                    showMessage("加载角色列表", result.msg, false);
                }
            }
        });
    }


    // 绑定角色点击的事件
    function bindRoleClick() {

      /*  $(".role-name").click(function (e) {
            console.log("点击部门名称：");
            // 不递归
            e.preventDefault();
            e.stopPropagation();
            var roleId = $(this).attr("data-id");
            console.log("部门 id = ", roleId);
            loadUserList(roleId);
        });*/

        // 新增角色
        $('.role-add').click(function () {
            console.log("新增角色：");
            clearData();
            $('#dialog-role-form').dialog({
                model : true,
                title : '新增角色',
                buttons : {
                    '取消' : function () {
                        // 关闭模态框
                        $("#dialog-role-form").dialog("close");
                    },
                    '保存' : function () {
                        var data = $("#roleForm").serializeArray();
                        console.log("添加角色的参数：", data);
                        // 发送到后台保存角色
                        saveRole(data);
                    }
                }
            });

            function saveRole(data){
                console.log("保存部门：");
                $.ajax({
                    url : '/sys/role/save.json',
                    method : 'post',
                    data : data,
                    success : function (result) {
                        console.log('返回结果：', result);
                        if (result.rec){
                            // 将模态框中的数据清除
                            clearData();
                            showMessage('新增角色', '操作成功', true);
                            // 新增角色后，重新刷新角色列表
                            loadRoleList();
                            // 关闭模态框
                            $("#dialog-role-form").dialog("close");
                        }else {
                            showMessage("新增角色", result.msg, false);
                        }
                    }
                });
            }

        });

        // 更新角色
        $('.role-edit').click(function () {
            console.log('修改角色信息：');
            // 获取当前修改的角色信息
            var id = $(this).data("id");
            var role = roleMap[id];
            console.log('修改前的对象信息：', role);
            $("#dialog-role-form").dialog({
                model : true,
                title : '修改角色',
                open : function(event, ui){
                    $("#roleId").val(id);
                    $("#roleName").val(role.name);
                    $("#roleStatus").val(role.status);
                    $("#roleRemark").val(role.remark);
                },
                buttons : {
                    '取消' : function () {
                        // 关闭模态框
                        $("#dialog-role-form").dialog("close");
                    },
                    '修改' : function () {
                        var data = $("#roleForm").serializeArray();
                        data.push({'name' : 'id', 'value' : id});
                        console.log("修改后的角色信息：", data);
                        // 发送到后台保存角色
                        updateRole(data);
                    }
                }
            });


            function updateRole(data) {
                console.log('修改角色信息：');
                var oldName;
                var oldStatus;
                var oldRemark;
                $.each(data, function (i) {
                    if (data[i].name === 'name'){
                        oldName = data[i].value;
                    }
                    if (data[i].name === 'status'){
                        oldStatus = data[i].value;
                    }
                    if (data[i].name === 'remark'){
                        oldRemark = data[i].value;
                    }
                });
                console.log('name = ', role.name , '   ', oldName);
                console.log('status = ', role.status , '   ', oldStatus);
                console.log('remark = ', role.remark , '   ', oldRemark);
                $.ajax({
                    url : '/sys/role/update.json',
                    method : 'post',
                    data : data,
                    beforeSend : function () {
                        if (role.name == oldName && role.status == oldStatus && role.remark == oldRemark) {
                            console.log('角色信息未修改');
                            showMessage('更新角色', '请确认角色信息是否修改', false);
                            return false;
                        } else {
                            console.log('角色信息已修改');
                            return true;
                        }
                    },
                    success : function (result) {
                        console.log(result);
                        if (result.rec){
                            clearData();
                            console.log('修改成功');
                            // 关闭模态框
                            $("#dialog-role-form").dialog("close");
                            loadRoleList();
                            showMessage('修改角色', '操作成功', true);
                        }else {
                            showMessage('修改角色', result.msg, false);
                        }
                    }
                })
            }
        });

        // 删除角色
        $(".role-delete").click(function () {
            console.log('删除角色');
            var id = $(this).data("id");
            var role = roleMap[id];
            console.log('删除的角色对象：', role);

            if (confirm('确定删除角色 [' + role.name + '] 吗?')) {
                $.ajax({
                    url : '/sys/role/delete.json',
                    method : 'get',
                    data : {
                        id : id
                    },
                    success : function (result) {
                        console.log(result);
                        if (result.rec){
                            loadRoleList();
                            showMessage('删除角色', '操作成功', true);
                        }else {
                            showMessage('删除角色', '操作失败', false);
                        }
                    }
                })
            }
        });
        function clearData() {
            console.log("清除表单数据");
            $("#roleId").val('');
            $("#roleName").val('');
            $("#roleStatus").val('');
            $("#roleRemark").val('');
        }

    }

</script>
</body>
</html>
