$(function(){
	
	$(".nav li").on("click",function(){
        var i = $(this).index();
		if(i===0){//健康档案
			window.location.href = "/home/page/qos/publichealth/index";
		}
		if(i===3){//0-6岁儿童
			window.location.href = "/home/page/qos/publichealth/indexChildren";
		}
		if(i===4){//孕产妇
			window.location.href = "/home/page/qos/publichealth/indexPregnant";
		}
		if(i===5){//老年人
			window.location.href = "/home/page/qos/publichealth/indexOldPeople";
		}
		if(i===6){//慢病
			window.location.href = "/home/page/qos/publichealth/indexSlowDisease";
		}
		if(i===9){//中医药
			window.location.href = "/home/page/qos/publichealth/indexChineseMedicine";
		}
		if(i===12){
			window.location.href = "/home/page/qos/publichealth/indexBirthControl";
		}
	});








	function loop2(){
	   var liLen = $(".nav li").length;
	   console.log(liLen)
        for(var i=0;i< $(".nav li").length;i++){
        console.log(i)
            $(".nav li").eq(i).trigger("click");
        }

	}


//  0  3  4   5  6  9    12

 $(".nav li").eq(2).trigger("click");



/*
	function loop(){
        for(var i=0;i< $(".nav li").length;i++){
            (function(){
                for(var i=0;i< $(".nav li").length;i++){
                      $(".nav li").eq(i).trigger("click");
                      console.log(i)
                 }
              })(i)
        }
    }
  setInterval(loop(),8000);
*/





});	