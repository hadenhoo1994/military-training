//房源自动搜索
function resultFormatResult(orgs) {       // 下拉选项名称      #}
    return '<div>' + orgs.name + '</div>';
}

function resultFormatSelection(orgs) {    // 选取后显示的名称     #}
    return orgs.name;
}
$("#selectHouse").on("change", function(e) {
    var name=$("#selectHouse").find("option:selected").text();  //获取Select选择的Text
    var id=$("#selectHouse").val();  //获取Select选择的Value
    //将name从","分割
    name=name.split(",")[0]
    $("#userName").val(name);
    $("#userId").val(id);
})
$("#selectProject").on("change", function(e) {
    var name=$("#selectProject").find("option:selected").text();  //获取Select选择的Text
    var id=$("#selectProject").val();  //获取Select选择的Value
    //将name从","分割
    name=name.split(",")[0]
    $("#projectName").val(name);
    $("#projectId").val(id);
})
$(document).ready(function () {
    $("#selectHouse").select2({
        placeholder: "根据学生名称或学号查询学生",
        minimumInputLength: 1,    // 最小查询参数    #}
        maximumInputLength: 20,    // 最大查询参数    #}
        multiple: false,          // 多选设置    #}
        ajax: {
            url: '/admin/mt/userInfo/getStudentByNameOrNumber',  // ajax后台函数路径 #}
            dataType: "JSON",　　　// 传输的数据类型，一般使用json或jsonp，jsonp比较复杂，需要APIKEY，暂时没进行研究 #}
            type: "POST",　　　　　　// GET即为前台JS向后台函数取数据，POST反之 #}
            quietMillis: 2000, 　　// 延时查询毫秒数 #}
            data: function (params) {
                return {
                    name: params.term, // term为前台输入的查询关键字，保存到sSearch对象，即后台保存关键字的对象 #}
                    page: 10   // 每次查询的结果数 #}
                };
            },
            results: function (data, page) {
                return {
                    results: data  // results结果集，data为后台返回的查询结果 #}
                };
            }
        },
        formatSelection: resultFormatSelection,  // 设定查询样式
        formatResult: resultFormatResult,　　　　// 设定查询结果样式
        dropdownCssClass: "bigdrop", 　　　　// 设定SELECT2下拉框样式，bigdrop样式并不在CSS里定义，暂时没深入研究
        escapeMarkup: function (m) {
            return m;
        },
        formatInputTooShort: '输入的文字需要多于1个',
    });

    $("#selectProject").select2({
        placeholder: "根据学生名称或学号查询学生",
        minimumInputLength: 1,    // 最小查询参数    #}
        maximumInputLength: 20,    // 最大查询参数    #}
        multiple: false,          // 多选设置    #}
        ajax: {
            url: '/admin/mt/project/getProjectByNameOrNumber',  // ajax后台函数路径 #}
            dataType: "JSON",　　　// 传输的数据类型，一般使用json或jsonp，jsonp比较复杂，需要APIKEY，暂时没进行研究 #}
            type: "POST",　　　　　　// GET即为前台JS向后台函数取数据，POST反之 #}
            quietMillis: 2000, 　　// 延时查询毫秒数 #}
            data: function (params) {
                return {
                    name: params.term, // term为前台输入的查询关键字，保存到sSearch对象，即后台保存关键字的对象 #}
                    page: 10   // 每次查询的结果数 #}
                };
            },
            results: function (data, page) {
                return {
                    results: data  // results结果集，data为后台返回的查询结果 #}
                };
            }
        },
        formatSelection: resultFormatSelection,  // 设定查询样式
        formatResult: resultFormatResult,　　　　// 设定查询结果样式
        dropdownCssClass: "bigdrop", 　　　　// 设定SELECT2下拉框样式，bigdrop样式并不在CSS里定义，暂时没深入研究
        escapeMarkup: function (m) {
            return m;
        },
        formatInputTooShort: '输入的文字需要多于1个',
    });
});
