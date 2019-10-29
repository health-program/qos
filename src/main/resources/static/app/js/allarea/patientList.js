  $.ajax({
        type : "post",    //请求类型
        url : "http://10.9.1.41:18081/home/page/qos/quailtydisplay/getRegisterList",//请求的 URL地址
        success: function (res) {
        //  序号  就诊机构  就诊科室 患者姓名  患者性别 就诊日期  结算类别
       var data = res.result;
       var html='';
        for(var i in data){
			html+='<div class="familySocilty">';
			html+='<li class="fz14 text-c1f3ff ellipsis47 textindext1">'+data[i].orgname+'</li>';
            html+='<li class="fz14 text-c1f3ff ellipsis47">'+data[i].deptname+'</li>';
            html+='<li class="fz14 text-c1f3ff ellipsis47">'+data[i].patientname+'</li>';
            html+='<li class="fz14 text-c1f3ff ellipsis47">'+data[i].sexcode+'</li>';
            html+='<li class="fz14 text-c1f3ff ellipsis47">'+data[i].seedate+'</li>';
            html+='<li class="fz14 text-c1f3ff ellipsis47">'+data[i].paykindname+'</li>';
            html+='</div>';
	 }
         $("#familyTableUl").append(html);
    }
 })



window.setInterval(function(){
	   $.ajax({
	        type : "post",    //请求类型
	        url : "http://10.9.1.41:18081/home/page/qos/quailtydisplay/getRegisterList",//请求的 URL地址
	        success: function (res) {
	        //  序号  就诊机构  就诊科室 患者姓名  患者性别 就诊日期  结算类别
	       var data = res.result;
	       var html='';
	        for(var i in data){
                html+='<div class="familySocilty">';
                html+='<li class="fz14 text-c1f3ff ellipsis47 textindext1">'+data[i].orgname+'</li>';
                html+='<li class="fz14 text-c1f3ff ellipsis47">'+data[i].deptname+'</li>';
                html+='<li class="fz14 text-c1f3ff ellipsis47">'+data[i].patientname+'</li>';
                html+='<li class="fz14 text-c1f3ff ellipsis47">'+data[i].sexcode+'</li>';
                html+='<li class="fz14 text-c1f3ff ellipsis47">'+data[i].seedate+'</li>';
                html+='<li class="fz14 text-c1f3ff ellipsis47">'+data[i].paykindname+'</li>';
                html+='</div>';

	       }
	       
	          $("#familyTableUl").append(html);

	             }
	      })
	},300000)