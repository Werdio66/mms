<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>权限</title>
    <jsp:include page="/common/backend_common.jsp"/>
    <jsp:include page="/common/page.jsp"/>
</head>
<body class="no-skin" youdao="bind" style="background: white">
<input id="gritter-light" checked="" type="checkbox" class="ace ace-switch ace-switch-5"/>

<div class="page-header">
    <h1>
        权限模块管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            维护权限模块和权限点关系
        </small>
    </h1>
</div>
<div class="main-content-inner">
    <div class="col-sm-3">
        <div class="table-header">
            权限模块列表&nbsp;&nbsp;
            <a class="green" href="#">
                <i class="ace-icon fa fa-plus-circle orange bigger-130 aclModule-add"></i>
            </a>
        </div>
        <div id="aclModuleList">
        </div>
    </div>
    <div class="col-sm-9">
        <div class="col-xs-12">
            <div class="table-header">
                权限点列表&nbsp;&nbsp;
                <a class="green" href="#">
                    <i class="ace-icon fa fa-plus-circle orange bigger-130 acl-add"></i>
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
                                权限名称
                            </th>
                            <th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1">
                                权限模块
                            </th>
                            <th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1">
                                类型
                            </th>
                            <th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1">
                                URL
                            </th>
                            <th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1">
                                状态
                            </th>
                            <th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1">
                                顺序
                            </th>
                            <th class="sorting_disabled" rowspan="1" colspan="1" aria-label=""></th>
                        </tr>
                        </thead>
                        <tbody id="aclList"></tbody>
                    </table>
                    <div class="row" id="aclPage">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="dialog-aclModule-form" style="display: none;">
    <form id="aclModuleForm">
        <table class="table table-striped table-bordered table-hover dataTable no-footer" role="grid">
          <%--  <tr>
                <td style="width: 80px;"><label for="parentId">上级模块</label></td>
                <td>
                    <select id="parentId" name="parentId" data-placeholder="选择模块" style="width: 200px;"></select>
                    <input type="hidden" name="id" id="aclModuleId"/>
                </td>
            </tr>--%>
            <tr>
                <td><label for="aclModuleName">名称</label></td>
                <td><input type="text" name="name" id="aclModuleName" value="" class="text ui-widget-content ui-corner-all"></td>
            </tr>
            <tr>
                <td><label for="aclModuleSeq">顺序</label></td>
                <td><input type="text" name="seq" id="aclModuleSeq" value="1" class="text ui-widget-content ui-corner-all"></td>
            </tr>
            <tr>
                <td><label for="aclModuleStatus">状态</label></td>
                <td>
                    <select id="aclModuleStatus" name="status" data-placeholder="选择状态" style="width: 150px;">
                        <option value="1">有效</option>
                        <option value="0">无效</option>
                        <option value="2">删除</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label for="aclModuleRemark">备注</label></td>
                <td><textarea name="remark" id="aclModuleRemark" class="text ui-widget-content ui-corner-all" rows="3" cols="25"></textarea></td>
            </tr>
        </table>
    </form>
</div>
<div id="dialog-acl-form" style="display: none;">
    <form id="aclForm">
        <table class="table table-striped table-bordered table-hover dataTable no-footer" role="grid">
            <tr>
                <td style="width: 80px;"><label>所属权限模块</label></td>
                <td>
                    <select id="aclModuleSelectId" name="aclModuleId" data-placeholder="选择权限模块" style="width: 200px;"></select>
                </td>
            </tr>
            <tr>
                <td><label for="aclName">名称</label></td>
                <input type="hidden" name="id" id="aclId"/>
                <td><input type="text" name="name" id="aclName" value="" class="text ui-widget-content ui-corner-all"></td>
            </tr>
            <tr>
                <td><label for="aclType">类型</label></td>
                <td>
                    <select id="aclType" name="type" data-placeholder="类型" style="width: 150px;">
                        <option value="1">菜单</option>
                        <option value="2">按钮</option>
                        <option value="3">其他</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label for="aclUrl">URL</label></td>
                <td><input type="text" name="url" id="aclUrl" value="1" class="text ui-widget-content ui-corner-all"></td>
            </tr>
            <tr>
                <td><label for="aclStatus">状态</label></td>
                <td>
                    <select id="aclStatus" name="status" data-placeholder="选择状态" style="width: 150px;">
                        <option value="1">有效</option>
                        <option value="0">无效</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label for="aclSeq">顺序</label></td>
                <td><input type="text" name="seq" id="aclSeq" value="" class="text ui-widget-content ui-corner-all"></td>
            </tr>
            <tr>
                <td><label for="aclRemark">备注</label></td>
                <td><textarea name="remark" id="aclRemark" class="text ui-widget-content ui-corner-all" rows="3" cols="25"></textarea></td>
            </tr>
        </table>
    </form>
</div>

<script id="aclModuleListTemplate" type="x-tmpl-mustache">
<ol class="dd-list ">
    {{#aclModuleList}}
        <li class="dd-item dd2-item aclModule-name {{displayClass}}" id="aclModule_{{id}}" href="javascript:void(0)" data-id="{{id}}">
            <div class="dd2-content" style="cursor:pointer;">
            {{name}}
            &nbsp;
            <a class="green {{#showDownAngle}}{{/showDownAngle}}" href="#" data-id="{{id}}" >
                <i class="ace-icon fa fa-angle-double-down bigger-120 sub-aclModule"></i>
            </a>
            <span style="float:right;">
                <a class="yellow aclModule-add" href="#" data-id="{{id}}" >
                    <i class="ace-icon fa fa-plus-circle bigger-100"></i>
                </a>
                &nbsp;
                <a class="green aclModule-edit" href="#" data-id="{{id}}" >
                    <i class="ace-icon fa fa-pencil bigger-100"></i>
                </a>
                &nbsp;
                <a class="red aclModule-delete" href="#" data-id="{{id}}" data-name="{{name}}">
                    <i class="ace-icon fa fa-trash-o bigger-100"></i>
                </a>
            </span>
            </div>
        </li>
    {{/aclModuleList}}
</ol>
</script>

<script id="aclListTemplate" type="x-tmpl-mustache">
{{#aclList}}
<tr role="row" class="acl-name odd" data-id="{{id}}"><!--even -->
    <td><a href="#" class="acl-edit" data-id="{{id}}">{{name}}</a></td>
    <td>{{showAclModuleName}}</td>
    <td>{{showType}}</td>
    <td>{{url}}</td>
    <td>{{#bold}}{{showStatus}}{{/bold}}</td>
    <td>{{seq}}</td>
    <td>
        <div class="hidden-sm hidden-xs action-buttons">
            <a class="green acl-edit" href="#" data-id="{{id}}">
                <i class="ace-icon fa fa-pencil bigger-100"></i>
            </a>

            <a class="red acl-role" href="#" data-id="{{id}}">
                <i class="ace-icon fa fa-flag bigger-100"></i>
            </a>

             <a class="red acl-delete" href="#" data-id="{{id}}">
                <i class="ace-icon fa fa-trash-o bigger-100"></i>
            </a>
        </div>
    </td>
</tr>
{{/aclList}}
</script>

<script type="application/javascript">

    // ===================================== 权限信息 ==============================
   
    // 存储树形权限列表
    var aclModuleList;
    // 存储map格式的权限信息
    var aclModuleMap = {};

    // 取出权限树的 HTML，使用 mustache 渲染
    var aclModuleListTemplate = $('#aclModuleListTemplate').html();
    Mustache.parse(aclModuleListTemplate);

    loadAclModuleTree();

    function loadAclModuleTree() {
        console.log('加载权限树：');
        $.ajax({
            url : '/sys/aclModule/tree.json',
            method : 'get',
            data : {},
            success : function (result) {
                console.log("返回的对象：", result);
                if (result.rec){
                    aclModuleList = result.data;
                    // 渲染列表
                    var rendered = Mustache.render(aclModuleListTemplate, {
                        aclModuleList: result.data,
                        "showDownAngle": function () {
                            return function (text, render) {
                                return (this.aclModuleList && this.aclModuleList.length > 0) ? "" : "hidden";
                            }
                        },
                        "displayClass": function () {
                            return "";
                        }
                    });
                    // console.log(rendered);
                    $("#aclModuleList").html(rendered);
                    // 递归处理权限树
                    recursiveRenderAclModule(aclModuleList);
                    console.log("所有的权限信息：", aclModuleMap);
                    // 绑定权限点击的事件
                    bindaclModuleClick();
                }else {
                    showMessage("加载权限树", result.msg, false);
                }
            }
        });
    }

    function recursiveRenderAclModule(aclModuleList) {
        if (aclModuleList && aclModuleList.length > 0) {
            $(aclModuleList).each(function (i, aclModule) {
                aclModuleMap[aclModule.id] = aclModule;
                if (aclModule.aclModuleList && aclModule.aclModuleList.length > 0) {
                    var rendered = Mustache.render(aclModuleListTemplate, {
                        aclModuleList: aclModule.aclModuleList,
                        "showDownAngle": function () {
                            return function (text, render) {
                                return (this.aclModuleList && this.aclModuleList.length > 0) ? "" : "hidden";
                            }
                        },
                        "displayClass": function () {
                            return "hidden";
                        }
                    });
                    $("#aclModule_" + aclModule.id).append(rendered);
                    recursiveRenderAclModule(aclModule.aclModuleList);
                }
            })
        }
    }

    // 绑定权限点击的事件
    function bindaclModuleClick() {

        $(".sub-aclModule").click(function (e) {
            e.preventDefault();
            e.stopPropagation();
            $(this).parent().parent().parent().children().children(".aclModule-name").toggleClass("hidden");
            if($(this).is(".fa-angle-double-down")) {
                $(this).removeClass("fa-angle-double-down").addClass("fa-angle-double-up");
            } else{
                $(this).removeClass("fa-angle-double-up").addClass("fa-angle-double-down");
            }
        });

        // 点击权限模块名称，显示当前模块所有的权限信息
        $(".aclModule-name").click(function (e) {
            console.log("点击权限模块名称：");
            // 不递归
            e.preventDefault();
            e.stopPropagation();
            var aclModuleId = $(this).data("id");
            console.log("权限模块 id = ", aclModuleId);
            loadAclList(aclModuleId);
        });

        // 新增权限
        $('.aclModule-add').click(function () {
            console.log("新增权限：");
            var id = $(this).data("id") || 0;
            console.log("新增权限的父 id = ", id);
            $('#dialog-aclModule-form').dialog({
                model : true,
                title : '新增权限',
                buttons : {
                    '取消' : function () {
                        // 关闭模态框
                        $("#dialog-aclModule-form").dialog("close");
                    },
                     '保存' : function () {
                        var data = $("#aclModuleForm").serializeArray();
                        data.push({'name' : 'parentId', 'value' : id});
                        console.log("添加权限的参数：", data);
                        // 发送到后台保存权限模块
                        saveAclModule(data);
                    }
                }
            });

            function saveAclModule(data){
                console.log("保存权限：");
                $.ajax({
                    url : '/sys/aclModule/save.json',
                    method : 'post',
                    data : data,
                    success : function (result) {
                        console.log('返回结果：', result);
                        if (result.rec){
                            showMessage('新增权限', '操作成功', true);
                            // 新增权限后，重新刷新权限树
                            loadAclModuleTree();
                            // 关闭模态框
                            $("#dialog-aclModule-form").dialog("close");
                            // 将模态框中的数据清除
                            clearData();
                            showMessage("新增权限", '操作成功', true);
                        }else {
                            showMessage("新增权限", result.msg, false);
                        }
                    }
                });
            }

        });

        // 更新权限
        $('.aclModule-edit').click(function () {
            console.log('修改权限信息：');
            // 获取当前修改的权限信息
            var id = $(this).data("id");
            var aclModule = aclModuleMap[id];
            console.log('修改前的对象信息：', aclModule);
            $("#dialog-aclModule-form").dialog({
                model : true,
                title : '修改权限',
                open : function(event, ui){
                    $("#aclModuleStatus").val(aclModule.status);
                    $("#aclModuleName").val(aclModule.name);
                    $("#aclModuleSeq").val(aclModule.seq);
                    $("#aclModuleRemark").val(aclModule.remark);
                },
                buttons : {
                    '取消' : function () {
                        // 关闭模态框
                        $("#dialog-aclModule-form").dialog("close");
                    },
                    '修改' : function () {
                        var data = $("#aclModuleForm").serializeArray();
                        data.push({'name' : 'id', 'value' : id});
                        data.push({'name' : 'parentId', 'value' : aclModule.parentId});
                        console.log("修改后的权限模块信息：", data);
                        // 发送到后台保存权限模块
                        updateAclModule(data);
                    }
                }
            });


            function updateAclModule(data) {
                console.log('修改权限信息：');
                var oldName;
                var oldSeq;
                var oldRemark;
                var oldStatus;
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
                     if (data[i].name === 'status'){
                        oldStatus = data[i].value;
                    }
                });
                console.log('name = ', aclModule.name , '   ', oldName);
                console.log('seq = ', aclModule.seq , '   ', oldSeq);
                console.log('remark = ', aclModule.remark , '   ', oldRemark);
                console.log('status = ', aclModule.status , '   ', oldStatus);
                $.ajax({
                    url : '/sys/aclModule/update.json',
                    method : 'post',
                    data : data,
                    beforeSend : function () {
                        if (aclModule.status == oldStatus && aclModule.name == oldName && aclModule.seq == oldSeq && aclModule.remark == oldRemark) {
                            showMessage('更新权限信息', '请确认权限信息是否修改', false);
                            console.log('权限信息未修改');
                            return false;
                        } else {
                            console.log('权限信息已修改');
                            return true;
                        }
                    },
                    success : function (result) {
                        console.log(result);
                        if (result.rec){
                            console.log('修改成功');
                            // 关闭模态框
                            $("#dialog-aclModule-form").dialog("close");
                            loadAclModuleTree();
                            clearData();
                            showMessage('修改权限', '操作成功', true);
                        }else {
                            showMessage('修改权限', result.msg, false);
                        }
                    }
                })
            }
        });

        // 删除权限
        $(".aclModule-delete").click(function () {
            console.log('删除权限');
            var id = $(this).data("id");
            var aclModule = aclModuleMap[id];
            console.log('删除的权限对象：', aclModule);

            if (aclModule.aclModuleList.length > 0){
                console.log("当前权限还有子权限");
                showMessage('删除权限', '当前权限还有子权限，不能删除');
                return false;
            }

            if (confirm('确定删除权限[' + aclModule.name + ']吗?')) {
                $.ajax({
                    url : '/sys/aclModule/delete.json',
                    method : 'get',
                    data : {
                        id : id
                    },
                    success : function (result) {
                        console.log(result);
                        if (result.rec){
                            loadAclModuleTree();
                            showMessage('删除权限', '操作成功', true);
                        }else {
                            showMessage('删除权限', '操作失败 ', result.msg, false);
                        }
                    }
                })
            }
        });

        function clearData() {
            $("#aclModuleName").val('');
            $("#aclModuleSeq").val('');
            $("#aclModuleRemark").val('');
            $("#aclModuleStatus").val('');
        }

    }



    // ==============================权限点信息==================================
    // 存储权限点信息
    var aclList;
    var aclMap = {};
    var aclListTemplate = $('#aclListTemplate').html();
    Mustache.parse(aclListTemplate);

    function loadAclList(aclModuleId) {
        console.log('加载权限点信息：');
        var pageSize = $("#pageSize").val();
        console.log("每页数量：", pageSize);
        var pageNo = $("#aclPage .pageNo").val() || 1;
        console.log("当前页：", pageNo);
        var json = {
          pageNum : pageNo,
          pageSize : pageSize
        };
        var url = '/sys/acl/queryByAclModuleId.json?aclModuleId=' + aclModuleId;
        $.ajax({
            url : url,
            method : 'get',
            data : json,
            success : function (result) {
                console.log('返回的结果：', result);
                renderAclListAndPage(result, url, aclModuleId);
            }
        })
    }


    function renderAclListAndPage(result, url, aclModuleId) {
        if (result.rec) {
            if (result.data.total > 0){
                console.log("所有的权限点：", result.data.list);
                var rendered = Mustache.render(aclListTemplate, {
                    aclList: result.data.list,
                    "showAclModuleName" : function () {
                        console.log("当前权限点的模块名称：", aclModuleMap[this.aclModuleId].name);
                        return aclModuleMap[this.aclModuleId].name;
                    },
                    "showStatus" : function() {
                        return this.status == 1 ? "有效": "无效";
                    },
                    "showType" : function() {
                        return this.type == 1 ? "菜单" : (this.type == 2 ? "按钮" : "其他");
                    },
                    "bold" : function() {
                        return function (text, render) {
                            var status = render(text);
                            if (status == '有效') {
                                return "<span class='label label-sm label-success'>有效</span>";
                            } else if (status == '无效') {
                                return "<span class='label label-sm label-warning'>无效</span>";
                            } else {
                                return "<span class='label'>删除</span>";
                            }
                        }
                    }
                });
                $("#aclList").html(rendered);
                // 绑定权限点的点击事件
                bindAclClick(aclModuleId);
                $.each(result.data.list, function(i, acl) {
                    aclMap[acl.id] = acl;
                });
                console.log("权限点 map ：", aclMap);
            } else {
                $("#aclList").html('');
            }
            var pageSize = $("#pageSize").val();
            var pageNo = $("#aclPage .pageNo").val() || 1;
            console.log("分页时的权限点 id ：", aclModuleId);

            renderPage(url, result.data.total, pageNo, pageSize, result.data.total > 0 ? result.data.list.length : 0, "aclPage", renderAclListAndPage);
        } else {
            showMessage("获取权限模块下权限列表", result.msg, false);
        }

        // 权限点的点击事件
        function bindAclClick(aclModuleId) {

            // 新增权限点信息
            $(".acl-add").click(function () {
                console.log("新增权限点信息：");
                $('#dialog-acl-form').dialog({
                    model : true,
                    title : '新增权限点信息',
                    open : function () {
                        optionStr = "";
                        // 生成权限模块下拉列表
                        recursiveRenderAclModuleSelect(aclModuleList, 1);
                        $("#aclForm")[0].reset();
                        $("#aclModuleSelectId").html(optionStr);
                        $("#aclModuleSelectId").val(aclModuleId);
                    },
                    buttons : {
                        '取消' : function () {
                            // 关闭模态框
                            $("#dialog-acl-form").dialog("close");
                        },
                        '保存' : function () {
                            var data = $("#aclForm").serializeArray();
                            var aclModuleId = $("#aclModuleSelectId").val();
                            console.log("添加权限点的权限模块 id = ", aclModuleId);
                            console.log("添加权限点的参数：", data);
                            // 发送到后台保存权限点
                            saveAcl(data, aclModuleId);
                        }
                    }
                });

            });

            function saveAcl(data, aclModuleId){

                console.log("保存权限点：");
                $.ajax({
                    url : '/sys/acl/save.json',
                    method : 'post',
                    data : data,
                    success : function (result) {
                        console.log("返回结果：", result);
                        if (result.rec){
                            console.log("保存权限点成功!");
                            showMessage('保存权限点', "操作成功", true);
                            // 加载权限点列表
                            loadAclList(aclModuleId);
                            // 清除表单中的数据
                            clearAclForm();
                            // 关闭模态框
                            $("#dialog-acl-form").dialog("close");
                        }else {
                            showMessage("保存权限点", result.msg, false);
                        }
                    }
                })
            }

            // 修改权限点信息
            $(".acl-edit").click(function () {
                console.log("修改权限点信息：");

                var aclId = $(this).data("id");
                console.log("修改权限点 id = ", aclId);
                // 权限模块 id
                var aclModuleId;
                var acl = aclMap[aclId];
                console.log("修改前的权限点信息：", acl);

                $('#dialog-acl-form').dialog({
                    model : true,
                    title : '修改权限点信息',
                    open : function () {
                        optionStr = "";
                        // 生成权限模块下拉列表
                        recursiveRenderAclModuleSelect(aclModuleList, 1);
                        $("#aclForm")[0].reset();
                        $("#aclModuleSelectId").html(optionStr);
                        // 回写
                        showAclForm(acl);
                    },
                    buttons : {
                        '取消' : function () {
                            // 关闭模态框
                            $("#dialog-acl-form").dialog("close");
                        },
                        '保存' : function () {
                            var data = $("#aclForm").serializeArray();
                            console.log("修改后的权限点信息：", data);

                            if(checkIsChange(acl, data)){
                                // 发送到后台保存权限点
                                updateAcl(data, aclModuleId);
                            }

                        }
                    }
                });
                // 修改权限点信息
                function updateAcl(data, aclModuleId) {
                    console.log("修改权限点：");
                    $.ajax({
                        url : '/sys/acl/update.json',
                        method : 'post',
                        data : data,
                        success : function (result) {
                            console.log('修改后，后端返回的结果 : ', result);
                            if (result.rec){
                                console.log("修改成功");
                                showMessage('修改权限点', "操作成功", true);
                                // 加载权限点列表
                                loadAclList(aclModuleId);
                                // 清除表单中的数据
                                clearAclForm();
                                // 关闭模态框
                                $("#dialog-acl-form").dialog("close");
                            }else {
                                showMessage('修改权限点', result.msg, false);
                            }

                        }
                    })
                }

                function checkIsChange(oldAcl, newAcl) {
                    var aclModuleSelectId;
                    var aclName;
                    var aclType;
                    var aclUrl;
                    var aclSeq;
                    var aclStatus;
                    var aclRemark;

                    $.each(newAcl, function (i) {
                        var temp = newAcl[i];
                        if (temp.name === 'name'){
                            aclName = temp.value;
                        }
                        if (temp.name === 'aclModuleId'){
                            aclModuleSelectId = temp.value;
                        }
                        if (temp.name === 'type'){
                            aclType = temp.value;
                        }
                        if (temp.name === 'url'){
                            aclUrl = temp.value;
                        }
                        if (temp.name === 'status'){
                            aclStatus = temp.value;
                        }
                        if (temp.name === 'remark'){
                            aclRemark = temp.value;
                        } 
                        if (temp.name === 'seq'){
                            aclSeq = temp.value;
                        }
                    });

                    aclModuleId = aclModuleSelectId;
                    if (oldAcl.aclModuleId == aclModuleSelectId && oldAcl.name == aclName && oldAcl.type == aclType
                    && oldAcl.url == aclUrl && oldAcl.status == aclStatus && oldAcl.remark == aclRemark
                    && oldAcl.seq == aclSeq){
                        showMessage('修改权限点', '请确认权限点信息是否已经修改', false);
                        console.log("权限点信息没有修改");
                        return false;
                    }else {
                        console.log("权限点信息已经修改");
                        return true;
                    }
                }

            });
            
            // 删除权限点
            $(".acl-delete").click(function () {
                var aclId = $(this).data("id");
                var acl = aclMap[aclId];
                console.log("删除权限点：", acl);
                console.log('删除的权限点对象 id：', aclId);
                var aclModuleId = acl.aclModuleId;
                if (confirm('确定删除权限点[' + acl.name + ']吗?')) {
                    $.ajax({
                        url : '/sys/acl/delete.json',
                        method : 'get',
                        data : {
                            id : aclId
                        },
                        success : function (result) {
                            console.log(result);
                            if (result.rec){
                                console.log("删除成功");
                                loadAclList(aclModuleId);
                                showMessage('删除权限点', '操作成功', true);
                            }else {
                                showMessage('删除权限点', '操作失败', false);
                            }
                        }
                    })
                }
            });

            function showAclForm(acl) {
                $("#aclModuleSelectId").val(acl.aclModuleId);
                $("#aclName").val(acl.name);
                $("#aclId").val(acl.id);
                $("#aclStatus").val(acl.status);
                $("#aclType").val(acl.type);
                $("#aclUrl").val(acl.url);
                $("#aclSeq").val(acl.seq);
                $("#aclRemark").val(acl.remark);
            }
            function clearAclForm() {
                $("#aclModuleSelectId").val('');
                $("#aclName").val('');
                $("#aclId").val('');
                $("#aclStatus").val('');
                $("#aclType").val('');
                $("#aclUrl").val('');
                $("#aclSeq").val('');
                $("#aclRemark").val('');
            }

            // 展示模块名称下拉列表
            function recursiveRenderAclModuleSelect(aclModuleList, level) {
                level = level | 0;
                if (aclModuleList && aclModuleList.length > 0) {
                    $(aclModuleList).each(function (i, aclModule) {
                        aclModuleMap[aclModule.id] = aclModule;
                        var blank = "";
                        if (level > 1) {
                            for(var j = 3; j <= level; j++) {
                                blank += "..";
                            }
                            blank += "∟";
                        }
                        optionStr += Mustache.render("<option value='{{id}}'>{{name}}</option>", {id: aclModule.id, name: blank + aclModule.name});
                        if (aclModule.aclModuleList && aclModule.aclModuleList.length > 0) {
                            recursiveRenderAclModuleSelect(aclModule.aclModuleList, level + 1);
                        }
                    });
                }
            }

        }
    }


</script>

</body>
</html>
