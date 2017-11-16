/**
 * Created by haolh on 2017/10/14.
 */
// function getParameter(param) {
//     var query = window.location.search; //获取URL地址中？后的所有字符
//     var iLen = param.length; //获取你的参数名称长度
//     var iStart = query.indexOf(param); //获取你该参数名称的起始索引
//     if(iStart == -1) {//-1为没有该参数
//         return "";
//     }
//     iStart += iLen + 1;
//     var iEnd = query.indexOf("&", iStart); //获取下一个参数的起始索引
//     if(iEnd == -1) {
//         //没有下一参数
//         return query.substring(iStart); //获取该参数的参数值
//
//     }
//     return query.substring(iStart, iEnd); //获取该参数的参数值
// }
// 根据参数名称获取value
function getUrlParameter(paramKey) {
    var sURLVariables, i, sParameterName, sPageURL = window.location.search.substring(1);
    if (sPageURL) {
        sURLVariables = sPageURL.split("&");
        for (i = 0; i < sURLVariables.length; i++) {
            sParameterName = sURLVariables[i].split("=");
            if (sParameterName[0] === paramKey) return sParameterName[1]
        }
    }
}
//获取当前登录账户信息
function getUserContext() {
    var user = {};
    $.ajax({
        url: "../getUserContext",
        type: "post",
        dataType: "json",
        async: false,
        data: {
        },
        success: function (data) {
            user = data;
        },
        error : function (data) {
        }
    });
    return user;
}

function checkMobile(phone) {
    var mobileReg = /^(13|14|15|17|18)[0-9]{8}[0-9]$/;
    if (!mobileReg.test(phone)) {
        return false;
    }
    return true;
}