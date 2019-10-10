$(function(){
     $.ajax({
       type : "post",    //请求类型
        url : "/qos/family/doctor/team/data/count",//请求的 URL地址
        success: function (res) {
             $("#home_family_doctor").text(res.result)

         }
    });

})