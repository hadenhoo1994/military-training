//获取营
function getBattalion() {
    var resule;
    $.ajax({
        type: "POST",
        url: "/rest/getBattalion",
        dataType: "json",
        cache: false,
        async: false,
        success: function (result) {
            if (result.code == 200){
               //获取成功
                resule = result.results;
            }else{
                layer.msg(result.msg)
            }
        }
        , error: function (data) {
            alert(JSON.stringify(data));
        }
    })
    return resule;
}

//获取课程
function getProject() {
    var resule;
    $.ajax({
        type: "POST",
        url: "/rest/getProject",
        dataType: "json",
        cache: false,
        async: false,
        success: function (result) {
            if (result.code == 200){
                //获取成功
                resule = result.results;
            }else{
                layer.msg(result.msg)
            }
        }
        , error: function (data) {
            alert(JSON.stringify(data));
        }
    })
    return resule;
}
//获取连
function getCompany(id) {
    var resule;
    $.ajax({
        type: "POST",
        data:{battalionId:id},
        url: "/rest/getCompany",
        dataType: "json",
        cache: false,
        async: false,
        success: function (result) {
            if (result.code == 200){
                //获取成功
                resule = result.results;
            }else{
                layer.msg(result.msg)
            }
        }
        , error: function (data) {
            alert(JSON.stringify(data));
        }
    })
    return resule;
}
//获取排
function getPlatoon(id) {
    var resule;
    $.ajax({
        type: "POST",
        data:{companyId:id},
        url: "/rest/getPlatoon",
        dataType: "json",
        cache: false,
        async: false,
        success: function (result) {
            if (result.code == 200){
                //获取成功
                resule = result.results;
            }else{
                layer.msg(result.msg)
            }
        }
        , error: function (data) {
            alert(JSON.stringify(data));
        }
    })
    return resule;
}