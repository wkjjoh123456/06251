<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" content="ie=edge"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>角色分类表格</title>
    <link href="${basePath!}/static/jqgrid/css/ui.jqgrid.css" type="text/css" media="screen" rel="stylesheet"/>
    <link href="${basePath!}/static/jqgrid/css/jquery-ui.css" type="text/css" media="screen" rel="stylesheet"/>
    <link href="${basePath!}/static/css/global.css" type="text/css" media="screen" rel="stylesheet"/>
    <link href="${basePath!}/static/plugins/font-awesome/css/font-awesome.min.css" type="text/css" media="screen" rel="stylesheet"/>
    <link href="${basePath!}/static/layui/css/layui.css" type="text/css" media="screen" rel="stylesheet"/>
    <link href="${basePath!}/static/css/ztree/metroStyle/metroStyle.css" type="text/css" media="screen" rel="stylesheet"/>
    <link href="${basePath!}/static/css/ztree/demo.css" type="text/css" media="screen" rel="stylesheet"/>
    <link href="${basePath!}/static/jquery-easyui-1.3.3/themes/default/easyui.css" rel="stylesheet" type="text/css" />
    <link href="${basePath!}/static/jquery-easyui-1.3.3/themes/icon.css" rel="stylesheet" type="text/css"/>
    <script src="${basePath!}/static/js/jquery.min.js" type="text/javascript"></script>
    <script src="${basePath!}/static/jqgrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>
    <script src="${basePath!}/static/jqgrid/js/i18n/grid.locale-cn.js" type="text/javascript"></script>
    <script src="${basePath!}/static/jqgrid/js/jquery-ui.js" type="text/javascript"></script>
    <script src="${basePath!}/static/layui/layui.js" type="text/javascript"></script>
    <script src="${basePath!}/static/js/ztree/jquery.ztree.all.js" type="text/javascript"></script>
    <script src="${basePath!}/static/jquery-easyui-1.3.3/jquery.min.js" type="text/javascript"></script>
    <script src="${basePath!}/static/jquery-easyui-1.3.3/jquery.easyui.min.js" type="text/javascript"></script>
    <script src="${basePath!}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
    <base href="${basePath!}/">
</head>
<body>
<table id="dg" title="食品类别录入" class="easyui-datagrid" fitColumns="false"
       pagination="true" rownumbers="true"
       url="admin/food/foodkindlist" fit="true"
       toolbar="#tb" remoteSort="false",multiSort="true",collapsible="true">
    <thead>
    <tr>
        <th field="fpId" width="120" align="center">id</th>
        <th field="fpName" width="120" align="center">类别</th>
    </tr>
    </thead>
</table>

<div id="tb">
    <div>
    <#--没完成-->
        <a href="javascript:openFoodKindAddDialog()" class="easyui-linkbutton"
           iconCls="icon-add" plain="true">添加</a> <a
            href="javascript:openFoodKindModifyDialog()" class="easyui-linkbutton"
            iconCls="icon-edit" plain="true">修改</a> <a
            href="javascript:deleteFoodKind()" class="easyui-linkbutton"
            iconCls="icon-remove" plain="true">删除</a>
    </div>
<#--搜索条件-->
    <div>
        &nbsp;食品类别：&nbsp;
        <input type="text" id="fpName" size="10"  onkeydown="if(event.keyCode==13) searchFoodKind()"/>
        <a href="javascript:searchFoodKind()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
    </div>
</div>

<div id="dlg" class="easyui-dialog"
     style="width: 400px;height:400px;padding: 40px 20px" closed="true"
     buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table cellspacing="10px">
            <tr>
                <td>   <input type="text" id="_fpId" name="fpId" hidden="true"/></td>
            </tr>
            <tr>
                <td>食品类别：</td>
                <td><input type="text" id="_fpName" name="fpName"
                           class="easyui-validatebox" required="true"/>
                </td>
            </tr>

        </table>
    </form>
</div>
<div id="dlg-buttons">
    <table cellpadding="0" cellspacing="0" style="width:100%">
        <a href="javascript:saveFoodKind()" class="easyui-linkbutton"
           iconCls="icon-ok">保存</a>
        <a href="javascript:closeFoodKindDialog()" class="easyui-linkbutton"
           iconCls="icon-cancel">关闭</a>
    </table>
</div>
</body>
<script type="text/javascript">

    /*关闭*/
    function closeFoodKindDialog() {
        $("#dlg").dialog("close");
        resetValue();
    }
    /*重置*/
    function resetValue() {
        $("#_fpId").val("");
        $("#_fpName").val("");
    }
    /*搜索食物三个条件*/
    function searchFoodKind() {

        $("#dg").datagrid('load', {
            "fpName": $("#fpName").val()
        });
    }

    /*保存食物，返回map.result.success   true/false*/
    function saveFoodKind(){
        var id = $("#_fpId").val();
        var name = $("#_fpName").val();
        var data={"fpId":id,"fpName":name};
        $.ajax({
            type:method,
            datatype:"json",
            url:"admin/food/addFoodKind",
            data:JSON.stringify(data),
            contentType:"application/json;charset=utf-8",
            success:function(result){
                if(result.success) {
                    $.messager.alert("系统提示", "保存成功");
                    $("#dlg").dialog("close");
                    $("#dg").datagrid("reload");
                    resetValue;
                }else{  $.messager.alert("系统提示","保存失败");
                    $("#dlg").dialog("close");
                    resetValue();}
            },
            error:function(){
                $.messager.alert("系统提示","保存失败");
                $("#dlg").dialog("close");
                resetValue();
            }
        });
    }

    /*添加food*/
    function openFoodKindAddDialog(){
        $("#dlg").dialog("open").dialog("setTitle","添加食品类别");
        method = "POST";
    }

    /*删除*/
    function deleteFoodKind(){

        /*获取选择的行*/
        var selectedRows = $("#dg").datagrid('getSelections');
        /*var selectedRow = $("#dg").datagrid('getSelections');*/
        if (selectedRows.length == 0) {
            $.messager.alert("系统提示", "请选择要删除的数据！");
            return;
        }
        if (selectedRows.length >20) {
            $.messager.alert("系统提示", "请选择少于20条数据！");
            return;
        }
        var strIds = [];
        /*遍历选择的id*/
        for (var i = 0; i < selectedRows.length; i++) {
            strIds.push(selectedRows[i].fpId);

        }
        /*通过，分割*/
        var ids = strIds.join(",");
        $.messager.confirm("系统提示","您确认要删除<font color='=red'>"+selectedRows.length+
                "</font>条数据吗？",function(r){
            if(r){
                $.ajax({
                    type:"DELETE",
                    datatype:"json",
                    url:"admin/food/deleteFoodKind/"+ids,
                    data:{},
                    success:function(result) {
                        if(result.success) {
                            $.messager.alert("系统提示","数据已经成功删除！") ;
                            $("#dg").datagrid("reload");
                        }else
                        {
                            $.messager.alert("系统提示","fail");
                        };
                    },
                    error:function(){
                        $.messager.alert("error");
                    }
                });
            }
        });
    }
    /*修改*/
    function openFoodKindModifyDialog() {
        var selectedRows = $("#dg").datagrid('getSelections');
        if (selectedRows.length != 1) {
            $.messager.alert("系统提示", "请选择一条要编辑的数据！");
            return;
        }
        var row = selectedRows[0];
        $("#dlg").dialog("open").dialog("setTitle", "编辑食品类别信息");
        $('#fm').form('load', row);
        method = "PUT";
    }
</script>
</html>
