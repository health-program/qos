$(function(){
   $.ajax({
        type : "post",    //请求类型
        url : "/home/page/qos/quailtydisplay/getRegisterList",//请求的 URL地址
        success: function (res) {
        //  序号  就诊机构  就诊科室 患者姓名  患者性别 就诊日期  结算类别
       var data = res.result;
       var html='';
        for(var i in data){
                                           html += '<li class="header quailty_bottom_right_header_title"><span class="quailty_seek">'+data[i].orgname+'</span>';
                                           html += '<span class="quailty_department">'+data[i].deptname+'</span>';
                                           html += '<span class="quailty_name">'+data[i].patientname+'</span>';
                                           html += '<span class="quailty_sex">'+data[i].sexcode+'</span>';
                                           html += '<span class="quailty_date">'+data[i].seedate+'</span>';
                                           html += '<span class="quailty_sick">'+data[i].paykindname+'</span></li>';

       }
       
          $("#list1").append(html);

             }
      })
})


window.setInterval(function(){
	console.log(123);
	   $.ajax({
	        type : "post",    //请求类型
	        url : "/home/page/qos/quailtydisplay/getRegisterList",//请求的 URL地址
	        success: function (res) {
	        //  序号  就诊机构  就诊科室 患者姓名  患者性别 就诊日期  结算类别
	       var data = res.result;
	       var html='';
	        for(var i in data){
	                                           html += '<li class="header quailty_bottom_right_header_title"><span class="quailty_seek">'+data[i].orgname+'</span>';
	                                           html += '<span class="quailty_department">'+data[i].deptname+'</span>';
	                                           html += '<span class="quailty_name">'+data[i].patientname+'</span>';
	                                           html += '<span class="quailty_sex">'+data[i].sexcode+'</span>';
	                                           html += '<span class="quailty_date">'+data[i].seedate+'</span>';
	                                           html += '<span class="quailty_sick">'+data[i].paykindname+'</span></li>';

	       }
	       
	          $("#list1").append(html);

	             }
	      })
	},300000)