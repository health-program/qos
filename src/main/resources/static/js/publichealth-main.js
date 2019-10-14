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





	$('.nav li').on('click',function(){
			currentIdx = $(this).index();

			//手动触发click事件时防止过快刷新到下一个页面，重置定时器
			clearInterval(timer);
			timer = setInterval("loop()",3000)
		});
		var timer = null;//定时器
		var currentIdx = 0;
		function loop(){
			(currentIdx<$('.nav li').length-1)? currentIdx++ : currentIdx = 0;
				$('.nav li').eq(currentIdx).trigger('click');
		}
		timer = setInterval("loop()",500)



});	