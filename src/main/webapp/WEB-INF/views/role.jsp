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
    var selectedUsersTemplate = $('#selectedUsersTemplate').html();
    Mustache.parse(selectedUsersTemplate);
    var unSelectedUsersTemplate = $("#unSelectedUsersTemplate").html();
    Mustache.parse(unSelectedUsersTemplate);

    // zTree
    <!-- 树结构相关 开始 -->
    var zTreeObj = [];
    var modulePrefix = 'm_';
    var aclPrefix = 'a_';
    var nodeMap = {};
    var lastRoleId = -1;
    var selectFirstTab = true;
    var hasMultiSelect = false;

    var setting = {
        check: {
            enable: true,
            chkDisabledInherit: true,
            chkboxType: {"Y": "ps", "N": "ps"}, //auto check 父节点 子节点
            autoCheckTrigger: true
        },
        data: {
            simpleData: {
                enable: true,
                rootPId: 0
            }
        },
        callback: {
            onClick: onClickTreeNode
        }
    };

    function onClickTreeNode(e, treeId, treeNode) { // 绑定单击事件
        var zTree = $.fn.zTree.getZTreeObj("roleAclTree");
        zTree.expandNode(treeNode);
    }

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

    function loadRoleUser(roleId) {
        console.log("加载角色对应的用户信息：");
        console.log("角色 id = ", roleId);

        $.ajax({
            url : '/sys/user/userList.json',
            method : 'get',
            data : {roleId : roleId},
            success : function (result) {
                if (result.rec) {
                    var renderedSelect = Mustache.render(selectedUsersTemplate, {userList: result.data.selected});
                    var renderedUnSelect = Mustache.render(unSelectedUsersTemplate, {userList: result.data.unselected});
                    $("#roleUserList").html(renderedSelect + renderedUnSelect);

                    if (!hasMultiSelect) {
                        $('select[name="roleUserList"]').bootstrapDualListbox({
                            showFilterInputs: false,
                            moveOnSelect: false,
                            infoText: false
                        });
                        hasMultiSelect = true;
                    } else {
                        $('select[name="roleUserList"]').bootstrapDualListbox('refresh', true);
                    }

                }else {
                    showMessage("加载角色用户数据", result.msg, false);
                }
            }
        })
    }
    function handleRoleSelected(roleId) {
        console.log("查询角色已经有的权限：");
        console.log("角色 id = ", roleId);

        if (lastRoleId != -1) {
            var lastRole = $("#role_" + lastRoleId + " .dd2-content:first");
            lastRole.removeClass("btn-yellow");
            lastRole.removeClass("no-hover");
        }
        var currentRole = $("#role_" + roleId + " .dd2-content:first");
        currentRole.addClass("btn-yellow");
        currentRole.addClass("no-hover");
        lastRoleId = roleId;

        $('#roleTab a:first').trigger('click');
        if (selectFirstTab){
            loadRoleAcl(roleId);
        }
    }

    $("#roleTab a[data-toggle='tab']").on("shown.bs.tab", function(e) {
        if(lastRoleId == -1) {
            showMessage("加载角色关系","请先在左侧选择操作的角色", false);
            return;
        }
        if (e.target.getAttribute("href") == '#roleAclTab') {
            selectFirstTab = true;
            loadRoleAcl(lastRoleId);
        } else {
            selectFirstTab = false;
            loadRoleUser(lastRoleId);
        }
    });

    $(".saveRoleAcl").click(function (e) {
        e.preventDefault();
        if (lastRoleId == -1) {
            showMessage("保存角色与用户的关系", "请现在左侧选择需要操作的角色", false);
            return;
        }
        var ids = getTreeSelectedId();
        console.log("选中的权限点ids = ", ids);
        var json = {
            ids : ids,
            roleId : lastRoleId
        };
        console.log("保存角色权限信息：", json);
        $.ajax({
            url : '/sys/roleAcl/save.json',
            method : 'post',
            data : json,
            success : function (result) {
                if (result.rec){
                    console.log('授予角色权限成功');
                    showMessage("给角色分配权限", '操作成功', true);
                    loadRoleAcl(lastRoleId);
                }else {
                    showMessage("给角色分配权限", result.msg, false);
                }
            }
        })
    });

    $(".saveRoleUser").click(function (e) {
        e.preventDefault();
        if (lastRoleId == -1) {
            showMessage("保存角色与用户的关系", "请现在左侧选择需要操作的角色", false);
            return;
        }
        var ids = $("#roleUserList").val() ? $("#roleUserList").val().join(",") : '';
        console.log("选中的权限点ids = ", ids);
        var json = {
            ids : ids,
            roleId : lastRoleId
        };
        console.log("保存角色权限信息：", json);
        $.ajax({
            url : '/sys/roleUser/save.json',
            method : 'post',
            data : json,
            success : function (result) {
                if (result.rec){
                    console.log('保存角色与用户的关系成功');
                    showMessage("保存角色与用户的关系", '操作成功', true);
                    loadRoleUser(lastRoleId);
                }else {
                    showMessage("保存角色与用户的关系", result.msg, false);
                }
            }
        })
    });

    function getTreeSelectedId() {
        var treeObj = $.fn.zTree.getZTreeObj("roleAclTree");
        var nodes = treeObj.getCheckedNodes(true);
        var ids = "";
        for(var i = 0; i < nodes.length; i++) {
            if(nodes[i].id.startsWith(aclPrefix)) {
                if (i == nodes.length - 1){
                    ids += nodes[i].dataId;
                }else {
                    ids += nodes[i].dataId;
                    ids += ',';
                }

            }
        }
        return ids;
    }
    function loadRoleAcl(roleId) {

        $.ajax({
            url : '/sys/role/roleTree.json',
            method : 'get',
            data : {roleId : roleId},
            success : function (result) {
                if (result.rec){
                    console.log("查询角色权限树成功");
                    console.log(result.data);
                    renderRoleTree(result.data);
                }else {
                    showMessage('加载角色权限树', result.msg, false);
                }
            }
        })
    }


    function renderRoleTree(aclModuleList) {
        zTreeObj = [];
        recursivePrepareTreeData(aclModuleList);
        for(var key in nodeMap) {
            zTreeObj.push(nodeMap[key]);
        }
        $.fn.zTree.init($("#roleAclTree"), setting, zTreeObj);
    }

    function recursivePrepareTreeData(aclModuleList) {
        // prepare nodeMap
        if (aclModuleList && aclModuleList.length > 0) {
            $(aclModuleList).each(function(i, aclModule) {
                var hasChecked = false;
                if (aclModule.aclList && aclModule.aclList.length > 0) {
                    $(aclModule.aclList).each(function(i, acl) {
                        zTreeObj.push({
                            id: aclPrefix + acl.id,
                            pId: modulePrefix + acl.aclModuleId,
                            name: acl.name + ((acl.type == 1) ? '(菜单)' : ''),
                            chkDisabled: !acl.hasAcl,
                            checked: acl.checked,
                            dataId: acl.id
                        });
                        if(acl.checked) {
                            hasChecked = true;
                        }
                    });
                }
                if ((aclModule.aclModuleList && aclModule.aclModuleList.length > 0) ||
                    (aclModule.aclList && aclModule.aclList.length > 0)) {
                    nodeMap[modulePrefix + aclModule.id] = {
                        id : modulePrefix + aclModule.id,
                        pId: modulePrefix + aclModule.parentId,
                        name: aclModule.name,
                        open: hasChecked
                    };
                    var tempAclModule = nodeMap[modulePrefix + aclModule.id];
                    while(hasChecked && tempAclModule) {
                        if(tempAclModule) {
                            nodeMap[tempAclModule.id] = {
                                id: tempAclModule.id,
                                pId: tempAclModule.pId,
                                name: tempAclModule.name,
                                open: true
                            }
                        }
                        tempAclModule = nodeMap[tempAclModule.pId];
                    }
                }
                recursivePrepareTreeData(aclModule.aclModuleList);
            });
        }
    }
    // 绑定角色点击的事件
    function bindRoleClick() {

        $(".role-name").click(function (e) {
            e.preventDefault();
            e.stopPropagation();
            var roleId = $(this).data("id");
            handleRoleSelected(roleId);
        });

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

        // 点击名称显示角色对应的权限

    }


</script>
</body>
</html>
