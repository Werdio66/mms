<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>部门管理</title>
    <jsp:include page="/common/backend_common.jsp"/>
    <jsp:include page="/common/page.jsp"/>
</head>
<body class="no-skin" youdao="bind" style="background: white">
<input id="gritter-light" checked="" type="checkbox" class="ace ace-switch ace-switch-5"/>

<div class="page-header">
    <h1>
        用户管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            维护部门与用户关系
        </small>
    </h1>
</div>
<div class="main-content-inner">
    <div class="col-sm-3">
        <div class="table-header">
            部门列表&nbsp;&nbsp;
            <a class="green" onclick="">
                <i class="ace-icon fa fa-plus-circle orange bigger-130 dept-add"></i>
            </a>
        </div>
        <div id="deptList">

        </div>
    </div>
    <div class="col-sm-9">
        <div class="col-xs-12">
            <div class="table-header">
                用户列表&nbsp;&nbsp;
                <a class="green" href="#">
                    <i class="ace-icon fa fa-plus-circle orange bigger-130 user-add"></i>
                </a>
            </div>
            <div>
                <div id="dynamic-table_wrapper" class="dataTables_wrapper form-inline no-footer">
                    <div class="row">
                        <div class="col-xs-6">
                            <div class="dataTables_length" id="dynamic-table_length"><label>
                                展示
                                <select id="pageSize" name="dynamic-table_length" aria-controls="dynamic-table" class="form-control input-sm">
                                    <option value="5">5</option>
                                    <option value="10">10</option>
                                    <option value="25">25</option>
                                    <option value="50">50</option>
                                    <option value="100">100</option>
                                </select> 条记录 </label>
                            </div>
                        </div>
                    </div>
                    <table id="dynamic-table" class="table table-striped table-bordered table-hover dataTable no-footer" role="grid"
                           aria-describedby="dynamic-table_info" style="font-size:14px">
                        <thead>
                        <tr role="row">
                            <th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1">
                                姓名
                            </th>
                            <th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1">
                                所属部门
                            </th>
                            <th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1">
                                邮箱
                            </th>
                            <th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1">
                                电话
                            </th>
                            <th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1">
                                状态
                            </th>
                            <th class="sorting_disabled" rowspan="1" colspan="1" aria-label=""></th>
                        </tr>
                        </thead>
                        <tbody id="userList"></tbody>
                    </table>
                    <div class="row" id="userPage">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="dialog-dept-form" style="display: none;">
    <form id="deptForm">
        <table class="table table-striped table-bordered table-hover dataTable no-footer" role="grid">
<%--            <tr>--%>
<%--                <td style="width: 80px;"><label for="parentId">上级部门</label></td>--%>
<%--                <td>--%>
<%--                    <select id="parentId" name="parentId" data-placeholder="选择部门" style="width: 200px;"></select>--%>
<%--                    <input type="hidden" name="id" id="deptId"/>--%>
<%--                </td>--%>
<%--            </tr>--%>
            <tr>
                <td><label for="deptName">名称</label></td>
                <td><input type="text" name="name" id="deptName" value="" class="text ui-widget-content ui-corner-all"></td>
            </tr>
            <tr>
                <td><label for="deptSeq">顺序</label></td>
                <td><input type="text" name="seq" id="deptSeq" value="1" class="text ui-widget-content ui-corner-all"></td>
            </tr>
            <tr>
                <td><label for="deptRemark">备注</label></td>
                <td><textarea name="remark" id="deptRemark" class="text ui-widget-content ui-corner-all" rows="3" cols="25"></textarea></td>
            </tr>
        </table>
    </form>
</div>
<div id="dialog-user-form" style="display: none;">
    <form id="userForm">
        <table class="table table-striped table-bordered table-hover dataTable no-footer" role="grid">
            <tr>
                <td style="width: 80px;"><label for="parentId">所在部门</label></td>
                <td>
                    <select id="deptSelectId" name="deptId" data-placeholder="选择部门" style="width: 200px;"></select>
                </td>
            </tr>
            <tr>
                <td><label for="userName">名称</label></td>
                <input type="hidden" name="id" id="userId"/>
                <td><input type="text" name="username" id="userName" value="" class="text ui-widget-content ui-corner-all"></td>
            </tr>
            <tr>
                <td><label for="userMail">邮箱</label></td>
                <td><input type="text" name="mail" id="userMail" value="" class="text ui-widget-content ui-corner-all"></td>
            </tr>
            <tr>
                <td><label for="userTelephone">电话</label></td>
                <td><input type="text" name="telephone" id="userTelephone" value="" class="text ui-widget-content ui-corner-all"></td>
            </tr>
            <tr>
                <td><label for="userStatus">状态</label></td>
                <td>
                    <select id="userStatus" name="status" data-placeholder="选择状态" style="width: 150px;">
                        <option value="1">有效</option>
                        <option value="0">无效</option>
                        <option value="2">删除</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label for="userRemark">备注</label></td>
                <td><textarea name="remark" id="userRemark" class="text ui-widget-content ui-corner-all" rows="3" cols="25"></textarea></td>
            </tr>
        </table>
    </form>
</div>

<script id="deptListTemplate" type="x-tmpl-mustache">
<ol class="dd-list">
    {{#deptList}}
        <li class="dept-name dd-item dd2-item" href="javascript:void(0)" id="dept_{{id}}" data-id="{{id}}">
            <div  class="dd2-content" style="cursor:pointer;">
              {{name}}
            <span style="float:right;">
                <a class="yellow dept-add" onclick="getId({{id}});" data-id="{{id}}" >
                    <i class="ace-icon fa fa-plus-circle bigger-100"></i>
                </a>
                &nbsp;
                <a class="green dept-edit" onclick="getId({{id}});" data-id="{{id}}" >
                    <i class="ace-icon fa fa-pencil bigger-100"></i>
                </a>
                &nbsp;
                <a class="red dept-delete" onclick="getId({{id}});" data-id="{{id}}" data-name="{{name}}">
                    <i class="ace-icon fa fa-trash-o bigger-100"></i>
                </a>
            </span>
            </div>
        </li>
    {{/deptList}}
</ol>
</script>

<script id="userListTemplate" type="x-tmpl-mustache">
{{#userList}}
<tr role="row" class="user-name odd" data-id="{{id}}"><!--even -->
    <td><a href="#" class="user-edit" data-id="{{id}}">{{username}}</a></td>
    <td>{{showDeptName}}</td>
    <td>{{mall}}</td>
    <td>{{telephone}}</td>
    <td>{{#bold}}{{showStatus}}{{/bold}}</td> <!-- 此处套用函数对status做特殊处理 -->
    <td>
        <div class="hidden-sm hidden-xs action-buttons">

            <a class="green user-edit" href="#" data-id="{{id}}">
                <i class="ace-icon fa fa-pencil bigger-100"></i>
            </a>
            <a class="red user-delete" href="#" data-id="{{id}}">
                <i class="ace-icon fa fa-flag bigger-100"></i>
            </a>
        </div>
    </td>
</tr>
{{/userList}}
</script>

<script type="application/javascript">

    // ===================================== 部门信息 ==============================
    // 获取操作部门的 id
    var id = 0;
    function getId(data) {
        id = data;
        return data;
    }
    // 存储树形部门列表
    var deptList;
    // 存储map格式的部门信息
    var deptMap = {};

    // 取出部门树的 HTML，使用 mustache 渲染
    var deptListTemplate = $('#deptListTemplate').html();
    Mustache.parse(deptListTemplate);

    loadDeptTree();

    function loadDeptTree() {
        console.log('加载部门树：');
        $.ajax({
            url : '/sys/dept/tree.json',
            method : 'get',
            data : {},
            success : function (result) {
                console.log("返回的对象：", result);
                if (result.rec){
                    deptList = result.data;
                    // 渲染列表
                    var rendered = Mustache.render(deptListTemplate, {deptList: result.data});
                    // console.log(rendered);
                    $("#deptList").html(rendered);
                    // 递归处理部门树
                    recursiveRenderDept(deptList);
                    console.log("所有的部门信息：", deptMap);
                    // 绑定部门点击的事件
                    bindDeptClick();
                }else {
                    showMessage("加载部门列表树", result.msg, false);
                }
            }
        });
    }

    // 递归渲染部门树
    function recursiveRenderDept(deptList) {
        if(deptList && deptList.length > 0) {
            $(deptList).each(function (i, dept) {
                deptMap[dept.id] = dept;
                if (dept.deptList.length > 0) {
                    var rendered = Mustache.render(deptListTemplate, {deptList: dept.deptList});
                    $("#dept_" + dept.id).append(rendered);
                    recursiveRenderDept(dept.deptList);
                }
            })
        }
    }

    // 绑定部门点击的事件
    function bindDeptClick() {

        $(".dept-name").click(function (e) {
            console.log("点击部门名称：");
            // 不递归
            e.preventDefault();
            e.stopPropagation();
            var deptId = $(this).attr("data-id");
            console.log("部门 id = ", deptId);
            loadUserList(deptId);
        });

        // 新增部门
        $('.dept-add').click(function () {
            console.log("新增部门：");
            $('#dialog-dept-form').dialog({
                model : true,
                title : '新增部门',
                buttons : {
                    '取消' : function () {
                        // 关闭模态框
                        $("#dialog-dept-form").dialog("close");
                    },
                     '保存' : function () {
                        var data = $("#deptForm").serializeArray();
                        data.push({'name' : 'parentId', 'value' : id});
                        console.log("添加部门的参数：", data);
                        // 发送到后台保存部门
                        saveDept(data);
                    }
                }
            });

            function saveDept(data){
                console.log("保存部门：");
                $.ajax({
                    url : '/sys/dept/save.json',
                    method : 'post',
                    data : data,
                    success : function (result) {
                        console.log('返回结果：', result);
                        if (result.rec){
                            showMessage('新增部门', '操作成功', true);
                            // 新增部门后，重新刷新部门树
                            loadDeptTree();
                            // 关闭模态框
                            $("#dialog-dept-form").dialog("close");
                            // 将模态框中的数据清除
                            clearData();
                        }else {
                            showMessage("新增部门", result.msg, false);
                        }
                    }
                });
            }

        });

        // 更新部门
        $('.dept-edit').click(function () {
            console.log('修改部门信息：');
            // 获取当前修改的部门信息
            var dept = deptMap[id];
            console.log('修改前的对象信息：', dept);
            $("#dialog-dept-form").dialog({
                model : true,
                title : '修改部门',
                open : function(event, ui){
                    $("#deptName").val(dept.name);
                    $("#deptSeq").val(dept.seq);
                    $("#deptRemark").val(dept.remark);
                },
                buttons : {
                    '取消' : function () {
                        // 关闭模态框
                        $("#dialog-dept-form").dialog("close");
                    },
                    '修改' : function () {
                        var data = $("#deptForm").serializeArray();
                        data.push({'name' : 'id', 'value' : id});
                        data.push({'name' : 'parentId', 'value' : dept.parentId});
                        console.log("修改后的部门信息：", data);
                        // 发送到后台保存部门
                        updateDept(data);
                    }
                }
            });


            function updateDept(data) {
                console.log('修改部门信息：');
                var oldName;
                var oldSeq;
                var oldRemark;
                $.each(data, function (i) {
                    if (data[i].name === 'name'){
                        oldName = data[i].value;
                    }
                    if (data[i].name === 'seq'){
                        oldSeq = data[i].value;
                    }
                    if (data[i].name === 'remark'){
                        oldRemark = data[i].value;
                    }
                });
                console.log('name = ', dept.name , '   ', oldName);
                console.log('seq = ', dept.seq , '   ', oldSeq);
                console.log('remark = ', dept.remark , '   ', oldRemark);
                $.ajax({
                    url : '/sys/dept/update.json',
                    method : 'post',
                    data : data,
                    beforeSend : function () {
                        if (dept.name == oldName && dept.seq == oldSeq && dept.remark == oldRemark) {
                            console.log('部门信息未修改');
                            showMessage('更新部门', '请确认部门信息是否修改', false);
                            return false;
                        } else {
                            console.log('部门信息已修改');
                            return true;
                        }
                    },
                    success : function (result) {
                        console.log(result);
                        if (result.rec){
                            console.log('修改成功');
                            // 关闭模态框
                            $("#dialog-dept-form").dialog("close");
                            loadDeptTree();
                            clearData();
                            showMessage('修改部门', '操作成功', true);
                        }else {
                            showMessage('修改部门', result.msg, false);
                        }
                    }
                })
            }
        });

        // 删除部门
        $(".dept-delete").click(function () {
            console.log('删除部门');
            var dept = deptMap[id];
            console.log('删除的部门对象：', dept);

            if (dept.deptList.length > 0){
                console.log("当前部门还有子部门");
                showMessage('删除部门', '当前部门还有子部门，不能删除');
                return false;
            }
            if (confirm('确定删除部门[' + dept.name + ']吗?')) {
                $.ajax({
                    url : '/sys/dept/delete.json',
                    method : 'get',
                    data : {
                        id : id
                    },
                    success : function (result) {
                        console.log(result);
                        if (result.rec){
                            loadDeptTree();
                            showMessage('删除部门', '操作成功', true);
                        }else {
                            showMessage('删除部门', '操作失败', false);
                        }
                    }
                })
            }
        });
        function clearData() {
            $("#deptName").val('');
            $("#deptSeq").val('');
            $("#deptRemark").val('');
        }

    }



    // ==============================用户信息==================================
    // 存储用户信息
    var userList;
    var userMap = {};
    var userListTemplate = $('#userListTemplate').html();
    Mustache.parse(userListTemplate);

    function loadUserList(deptId) {
        console.log('加载用户信息：');
        var pageSize = $("#pageSize").val();
        console.log("每页数量：", pageSize);
        var pageNo = $("#userPage .pageNo").val() || 1;
        console.log("当前页：", pageNo);
        var json = {
          pageNum : pageNo,
          pageSize : pageSize
        };
        var url = '/sys/user/queryByDeptId.json?deptId=' + deptId;
        $.ajax({
            url : url,
            method : 'get',
            data : json,
            success : function (result) {
                console.log('返回的结果：', result);
                renderUserListAndPage(result, url, deptId);
            }
        })
    }


    function renderUserListAndPage(result, url, deptId) {
        if (result.rec) {
            if (result.data.total > 0){
                console.log("所有的用户：", result.data.list);
                var rendered = Mustache.render(userListTemplate, {
                    userList: result.data.list,
                    "showDeptName": function() {
                        // console.log("部门名称：", deptMap[this.deptId].name);
                        return deptMap[this.deptId].name;
                    },
                    "showStatus": function() {
                        // console.log("状态：", this.status);
                        return this.status == 1 ? '有效' : (this.status == 0 ? '无效' : '删除');
                    },
                    "bold": function() {
                        // console.log("blod 事件：");
                        return function(text, render) {
                            var status = render(text);
                            if (status == '有效') {
                                return "<span class='label label-sm label-success'>有效</span>";
                            } else if(status == '无效') {
                                return "<span class='label label-sm label-warning'>无效</span>";
                            } else {
                                return "<span class='label'>删除</span>";
                            }
                        }
                    }
                });
                $("#userList").html(rendered);
                // 绑定用户的点击事件
                bindUserClick();
                $.each(result.data.list, function(i, user) {
                    userMap[user.id] = user;
                })
                console.log("用户 map ：", userMap);
            } else {
                $("#userList").html('');
            }
            var pageSize = $("#pageSize").val();
            var pageNo = $("#userPage .pageNo").val() || 1;
            console.log("分页时的部门 id ：", deptId);

            renderPage(url, result.data.total, pageNo, pageSize, result.data.total > 0 ? result.data.list.length : 0, "userPage", renderUserListAndPage);
        } else {
            showMessage("获取部门下用户列表", result.msg, false);
        }

        // 用户的点击事件
        function bindUserClick() {

            // 新增用户信息
            $(".user-add").click(function () {
                console.log("新增用户信息：");
                $('#dialog-user-form').dialog({
                    model : true,
                    title : '新增用户信息',
                    open : function () {
                        optionStr = "";
                        // 生成部门下拉列表
                        recursiveRenderDeptSelect(deptList, 1);
                        $("#userForm")[0].reset();
                        $("#deptSelectId").html(optionStr);
                    },
                    buttons : {
                        '取消' : function () {
                            // 关闭模态框
                            $("#dialog-user-form").dialog("close");
                        },
                        '保存' : function () {
                            var data = $("#userForm").serializeArray();
                            var deptId = $("#deptSelectId").val();
                            console.log("添加用户的部门 id = ", deptId);
                            console.log("添加部门的参数：", data);
                            // 发送到后台保存部门
                            saveUser(data, deptId);
                        }
                    }
                });

            });

            function saveUser(data, deptId){

                console.log("保存用户：");
                $.ajax({
                    url : '/sys/user/save.json',
                    method : 'post',
                    data : data,
                    success : function (result) {
                        console.log("返回结果：", result);
                        if (result.rec){
                            console.log("保存用户成功!");
                            // 加载用户列表
                            loadUserList(deptId);
                            // 清除表单中的数据
                            clearUserForm();
                            // 关闭模态框
                            $("#dialog-user-form").dialog("close");
                            showMessage('保存用户', "操作成功", true);
                        }else {
                            showMessage("保存用户", result.msg, false);
                        }
                    }
                })
            }

            // 修改用户信息
            $(".user-edit").click(function () {
                console.log("修改用户信息：");

                var userId = $(this).data("id");
                console.log("修改用户 id = ", userId);
                // 部门 id
                var deptId;
                var user = userMap[userId];
                console.log("修改前的用户信息：", user);

                $('#dialog-user-form').dialog({
                    model : true,
                    title : '修改用户信息',
                    open : function () {
                         optionStr = "";
                        // 生成部门下拉列表
                        recursiveRenderDeptSelect(deptList, 1);
                        $("#userForm")[0].reset();
                        $("#deptSelectId").html(optionStr);
                        // 回写
                        showUserForm(user);
                    },
                    buttons : {
                        '取消' : function () {
                            // 关闭模态框
                            $("#dialog-user-form").dialog("close");
                        },
                        '保存' : function () {
                            var data = $("#userForm").serializeArray();
                            console.log("修改后的用户信息：", data);

                            if(checkIsChange(user, data)){
                                // 发送到后台保存用户
                                updateUser(data, deptId);
                            }

                        }
                    }
                });
                // 修改用户信息
                function updateUser(data, deptId) {
                    console.log("修改用户：");
                    $.ajax({
                        url : '/sys/user/update.json',
                        method : 'post',
                        data : data,
                        success : function (result) {
                            console.log('修改后，后端返回的结果 : ', result);
                            if (result.rec){
                                console.log("修改成功");
                                // 加载用户列表
                                loadUserList(deptId);
                                // 清除表单中的数据
                                clearUserForm();
                                // 关闭模态框
                                $("#dialog-user-form").dialog("close");
                                showMessage('修改用户', "操作成功", true);
                            }else {
                                showMessage('修改用户', result.msg, false);
                            }

                        }
                    })
                }
                function checkIsChange(oldUser, newUser) {
                    var deptSelectId;
                    var userName;
                    var userMail;
                    var userTelephone;
                    var userStatus;
                    var userRemark;

                    $.each(newUser, function (i) {
                        var temp = newUser[i];
                        if (temp.name === 'username'){
                            userName = temp.value;
                        }
                        if (temp.name === 'deptId'){
                            deptSelectId = temp.value;
                        }
                        if (temp.name === 'mail'){
                            userMail = temp.value;
                        }
                        if (temp.name === 'telephone'){
                            userTelephone = temp.value;
                        }
                        if (temp.name === 'status'){
                            userStatus = temp.value;
                        }
                        if (temp.name === 'remark'){
                            userRemark = temp.value;
                        }
                    });

                    deptId = deptSelectId;
                    if (oldUser.deptId == deptSelectId && oldUser.username == userName && oldUser.mall == userMail
                    && oldUser.telephone == userTelephone && oldUser.status == userStatus && oldUser.remark == userRemark){
                        console.log("用户信息没有修改");
                        showMessage('修改用户', '请确认用户信息是否已经修改', false);
                        return false;
                    }else {
                        console.log("用户信息已经修改");
                        return true;
                    }
                }

            });
            
            // 删除用户
            $(".user-delete").click(function () {
                var userId = $(this).data("id");
                var user = userMap[userId];
                console.log("删除用户：", user);
                console.log('删除的用户对象 id：', userId);
                var deptId = user.deptId;
                if (confirm('确定删除用户[' + user.username + ']吗?')) {
                    $.ajax({
                        url : '/sys/user/delete.json',
                        method : 'get',
                        data : {
                            id : userId
                        },
                        success : function (result) {
                            console.log(result);
                            if (result.rec){
                                console.log("删除成功");
                                loadUserList(deptId);
                                showMessage('删除用户', '操作成功', true);
                            }else {
                                showMessage('删除用户', '操作失败', false);
                            }
                        }
                    })
                }
            });

            function showUserForm(user) {
                $("#deptSelectId").val(user.deptId);
                $("#userName").val(user.username);
                $("#userMail").val(user.mall);
                $("#userTelephone").val(user.telephone);
                $("#userStatus").val(user.status);
                $("#userRemark").val(user.remark);
                $("#userId").val(user.id);
            }
            function clearUserForm() {
                $("#deptSelectId").val('');
                $("#userName").val('');
                $("#userMail").val('');
                $("#userTelephone").val('');
                $("#userStatus").val('');
                $("#userRemark").val('');
                $("#userId").val('');
            }

            // 生成部门下拉列表
            function recursiveRenderDeptSelect(deptList, level) {
                level = level | 0;
                if (deptList && deptList.length > 0) {
                    $(deptList).each(function (i, dept) {
                        deptMap[dept.id] = dept;
                        var blank = "";
                        if (level > 1) {
                            for(var j = 3; j <= level; j++) {
                                blank += "..";
                            }
                            blank += "∟";
                        }
                        optionStr += Mustache.render("<option value='{{id}}'>{{name}}</option>", {id: dept.id, name: blank + dept.name});
                        if (dept.deptList && dept.deptList.length > 0) {
                            recursiveRenderDeptSelect(dept.deptList, level + 1);
                        }
                    });
                }
            }

        }
    }


</script>

</body>
</html>
