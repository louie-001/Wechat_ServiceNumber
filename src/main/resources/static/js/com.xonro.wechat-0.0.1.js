/**
 * 微信公众平台相关js
 * Created by louie on 2017-11-16.
 */

function getOauthorToken() {
    var oauthorToken ;
    var userCode = getUrlParameter("code");
    $.ajax({
        url:"../wechat/snsToken/"+userCode,
        type: "post",
        dataType: "json",
        async: false,
        success:function (res) {
            oauthorToken = res;
        },
        error:function (res) {
            console.log(res);
        }
    });
    return oauthorToken;
}


