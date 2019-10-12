$(function(){
     $.ajax({
         type : "post",    //请求类型
        url : "/qos/family/doctor/personnel/find/all",//请求的 URL地址
        success: function (res) {
           /*
                age: 29
                id: "126ca9ba92bb4f51bc3d02523b21a300"
                isTeamCaptain: 1  //  是否团队  1是 0否
                jobRank: "执业助理医师"  职称
                name: "刘清秀"
                personnelCategory: "全科医生"  工作岗位  、、  人员类别
                sex: 0  1男 0女
                teamName: "北区团队"
                unitId: "320583107467170500"
                unitName: "周庄社区卫生服务中心"
         */
          var data = res.result;
          var html = "";
          $.each(data, function(i, item) { //这里的函数参数是键值对的形式，k代表键名，v代表值
              html += '<div class="header doctorheader">';
              html += '<span class="doctor_unitName">'+item.unitName+'</span>';
              html += '<span  class="doctor_teamName">'+item.teamName+'</span>';
              html += '<span  class="doctor_names">'+item.name+'</span>';
              html += '<span  class="doctor_ages">'+item.age+'</span>';
              html += '<span  class="doctor_jobRank">'+item.jobRank+'</span>';
              html += '<span  class="doctor_personnelCategory">'+item.personnelCategory+'</span>';
              html += '<span  class="doctor_isTeamCaptain">'+item.isTeamCaptain+'</span>';
             });
           $("#list1").append(html);


           $(".doctor_isTeamCaptain").each(function(index,data){
                var doctor_isTeamCaptain=$(".doctor_isTeamCaptain").eq(index).text();
                if(doctor_isTeamCaptain==0){
                      $(".doctor_isTeamCaptain").eq(index).text('女')
                }
                if(doctor_isTeamCaptain==1){
                      $(".doctor_isTeamCaptain").eq(index).text('男')
               }
            })

        }
    });
 })