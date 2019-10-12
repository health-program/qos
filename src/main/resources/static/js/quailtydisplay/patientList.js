$(function(){
   $.ajax({
        type : "post",    //请求类型
        url : "/home/page/qos/quailtydisplay/getRegisterList",//请求的 URL地址
        success: function (res) {
        debugger;
//       var html = "";
//         $.each(data, function(i, item) { //这里的函数参数是键值对的形式，k代表键名，v代表值
//                               html += '<div class="header quailty_bottom_right_header_title">';
//                               html += '<span class="quailty_serial">'+i+'</span>';
//                               html += '<span class="quailty_seek">'+item.unitName+'</span>';
//                               html += '<span class="quailty_department">'+item.teamName+'</span>';
//                               html += '<span class="quailty_name">'+item.name+'</span>';
//                               html += '<span class="quailty_sex">'+item.sex+'</span>';
//                               html += '<span class="quailty_date">就诊日期</span>';
//                               html += '<span class="quailty_sick">诊断疾病</span>';
//
//                           });
//                           $("#list1").append(html);

        }
      })


 })