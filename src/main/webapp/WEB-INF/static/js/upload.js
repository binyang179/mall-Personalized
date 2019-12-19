var formData = new FormData();//使用formData来上传数据
                              //content-Type是form-data类型
formData.append("appId", $("#appID").val());
formData.append("file", $("#appLogo")[0].files[0]);//取file类型input中的文件

$.ajax({
    method : "POST",
    url : "/apps/updateApp",
    timeout : 10000, //超时时间设置单位毫秒
    crossDomain: true,
    async: false,
    headers: {
        "client-type":"platform"
    },
    dataType:"json",
    data: formData,
    contentType:false,//
    processData:false,//数据不做预处理
    success : function(response) {
        alert(response.msg);
        return;
    },
    error : function(e) {
        alert(response.msg);
        return;
    }
});