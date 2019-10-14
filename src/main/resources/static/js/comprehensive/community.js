 var dataMap={}
$(function(){

    //  家庭医生团队数量
     $.ajax({
       type : "post",    //请求类型
        url : "/qos/family/doctor/team/data/count",//请求的 URL地址
        success: function (res) {
             $("#home_family_doctor").text(res.result)

         }
    });


    //统计的全部写在这
     var solicty = {
             eventIds:'22001,248683'
     }


     $.ajax({
             type : "post",    //请求类型
             url : "/qos/analysis/data/get/total",//请求的 URL地址
             data:solicty,
             success: function (rawData) {
               // $("#thismonth").text(rawData.result['13003'])
                $("#healthAll").text(rawData.result['22001'])
                $("#specialPeople").text(rawData.result['248683'])
               }
        });


  var solicty = {
             eventIds:'13003'
     }
     $.ajax({
             type : "post",    //请求类型
             url : "/qos/analysis/data/get/total",//请求的 URL地址
             data:solicty,
             success: function (rawData) {

                $("#thismonth").text(rawData.result['13003'])
              }
        });


window.onload=function(){
    console.log(4234234)
   publicBusi();
}
    function publicBusi(){
    // setTimeout(personBusi,1000*60*7);//这里的1000表示1秒有1000毫秒,1分钟有60秒,7表示总共7分钟
    setTimeout(personBusi,1000*60*5);//这里的1000表示1秒有1000毫秒,1分钟有60秒,7表示总共7分钟
      $.ajax({
                  type : "post",    //请求类型
                  url : "/qos/analysis/data/get/total",//请求的 URL地址
                  data:solicty,
                  success: function (rawData) {
                     $("#thismonth").text(rawData.result['13003'])
                   }
             });
    }


 /*    var arr = {
        eventIds:'13003'
     }
           var thismonth=''
       $.ajax({
         type : "post",    //请求类型
         url : "/qos/analysis/data/get/unit",//请求的 URL地址
         data:arr,
         success: function (rawData) {
          var  unit13003Data=convertUnitChartData(rawData.result, '13003', false); //
          for(var i=0;i<unit13003Data.length;i++){

          }
             debugger
          }
         });*/
})