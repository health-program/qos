  $.ajax({
        type : "post",    //请求类型
        url : URLPATH+"/home/page/qos/quailtydisplay/getRegisterList",//请求的 URL地址
        success: function (res) {
        //  序号  就诊机构  就诊科室 患者姓名  患者性别 就诊日期  结算类别
       var data = res.result;
       var html='';
        for(var i in data){
			html+='<div class="familySocilty">';
			html+='<li class="fz14 text-c1f3ff ellipsis47 textindext1">'+isNull(data[i].orgname)+'</li>';
            html+='<li class="fz14 text-c1f3ff ellipsis47">'+isNull(data[i].deptname)+'</li>';
            html+='<li class="fz14 text-c1f3ff ellipsis47">'+formatName(data[i].patientname)+'</li>';
            html+='<li class="fz14 text-c1f3ff ellipsis47">'+isNull(data[i].sexcode)+'</li>';
            html+='<li class="fz14 text-c1f3ff ellipsis47">'+intercePt(data[i].seedate)+'</li>';
            html+='<li class="fz14 text-c1f3ff ellipsis47">'+isNull(data[i].paykindname)+'</li>';
            html+='</div>';
	 }
         $("#familyTableUl").append(html);
    }
 })

window.setInterval(function(){
	   $.ajax({
	        type : "post",    //请求类型
	        url : URLPATH+"/home/page/qos/quailtydisplay/getRegisterList",//请求的 URL地址
	        success: function (res) {
	        //  序号  就诊机构  就诊科室 患者姓名  患者性别 就诊日期  结算类别
	       var data = res.result;
	       var html='';
	        for(var i in data){
                html+='<div class="familySocilty">';
                html+='<li class="fz14 text-c1f3ff ellipsis47 textindext1">'+isNull(data[i].orgname)+'</li>';
                html+='<li class="fz14 text-c1f3ff ellipsis47">'+isNull(data[i].deptname)+'</li>';
                html+='<li class="fz14 text-c1f3ff ellipsis47">'+formatName(data[i].patientname)+'</li>';
                html+='<li class="fz14 text-c1f3ff ellipsis47">'+isNull(data[i].sexcode)+'</li>';
                html+='<li class="fz14 text-c1f3ff ellipsis47">'+intercePt(data[i].seedate)+'</li>';
                html+='<li class="fz14 text-c1f3ff ellipsis47">'+isNull(data[i].paykindname)+'</li>';
                html+='</div>';

	       }
	       
	          $("#familyTableUl").append(html);

	             }
	      })
	},300000)



  function formatName(str) {
      return new Array(str.length).join('*') + str.substr(-1);
 }


  function isNull(a) {
      if(a == "" || a == null || a == undefined){
            return '未知'
       }
       return a;
 }

  function intercePt(time){
      return time.substring(0,10)
  }

