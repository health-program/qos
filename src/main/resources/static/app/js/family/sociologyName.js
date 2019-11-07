$(function(){
     $.ajax({
         type : "post",    //请求类型
        url : URLPATH+"/home/page/qos/team/find/all",//请求的 URL地址
        success: function (res) {

          var data = res.result;
          var html = "";
          $.each(data, function(i, item) { //这里的函数参数是键值对的形式，k代表键名，v代表值
              html += '<div class="sameDiv">';
              html += '<li class="doctor_unitName">'+isNull(item.unitName)+'</li>';
              html += '<li  class="doctor_teamName">'+isNull(item.teamName)+'</li>';
              html += '<li  class="doctor_names">'+isNull(item.name)+'</li>';
              html += '<li  class="doctor_ages">'+isNull(item.age)+'</li>';
              html += '<li  class="doctor_jobRank">'+isNull(item.jobRank)+'</li>';
              html += '<li  class="doctor_personnelCategory">'+isNull(item.personnelCategory)+'</li>';
              html += '<li  class="doctor_isTeamCaptain">'+item.isTeamCaptain+'</li>';
              html += '</div>';
             });
           $("#familyTableUl").append(html);


           $(".doctor_isTeamCaptain").each(function(index,data){
                var doctor_isTeamCaptain=$(".doctor_isTeamCaptain").eq(index).text();
                if(doctor_isTeamCaptain==0){
                      $(".doctor_isTeamCaptain").eq(index).text('否')
                }
                if(doctor_isTeamCaptain==1){
                      $(".doctor_isTeamCaptain").eq(index).text('是')
               }
            })

        }
    });
 })



function isNull(a) {
    if(a == "" || a == null || a == undefined){
        return '未知'
    }
    return a;
}
