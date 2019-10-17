$(function(){
	getNumbers()
})
window.setInterval(function(){
	getNumbers()
	},300000)

function getNumbers(){
	$.ajax({
        type : "post",    //请求类型
        url : "/home/page/qos/quailtydisplay/getOutPatientNumber",//请求的 URL地址
        success: function (rawData) {
        debugger 
        var patientNumberList = rawData.result.patientNumberList;
        var emergencyNumberList = rawData.result.emergencyNumberList;
        var todayNumberList = rawData.result.todayNumberList;
        var thisMonthNumberList = rawData.result.thisMonthNumberList;
		// 指定图表的配置项和数据
		var data1 = [];//门诊
		var data2 = [];//急诊
		var data3 = [];//当日
		var data4 = [];//当月
		var datacity = [] ;//单位
		patientNumberList && patientNumberList.forEach(function(unitPoint) {
			data1.push(unitPoint.count);
		})
		emergencyNumberList && emergencyNumberList.forEach(function(unitPoint) {
			data2.push(unitPoint.count);
		})
		todayNumberList && todayNumberList.forEach(function(unitPoint) {
			data3.push(unitPoint.count);
		})
		thisMonthNumberList && thisMonthNumberList.forEach(function(unitPoint) {
			data4.push(unitPoint.count);
		})
		patientNumberList && patientNumberList.forEach(function(unitPoint) {
			datacity.push(unitPoint.unitName);
		})
		


        //社区就诊开始
         var medicalAdviceOption={
                                     color: ['#388BFF', '#05C3FA', '#F6931C', '#FFD52E'],
                                     tooltip: {
                                         trigger: 'axis',
                                     },
                                     legend: {
                                         top: '8%',
                                          textStyle: {
                                              fontSize: 10,
                                               color: '#fff'
                                           },
                                         data: ['门诊人次', '急诊人次', '当日门急诊人次', '当月门急诊人次'],
                                     },
                                     grid: { //图表的位置
                                         top: '20%',
                                         left: '3%',
                                         right: '2%',
                                         bottom: '5%',
                                         containLabel: true
                                     },
                                     yAxis: [{
                                         type: 'value',
                                         axisLabel: {
                                             show: true,
                                             interval: 'auto',
                                             formatter: '{value} '
                                         },
                                         splitLine: {
                                             show: true,
                                             lineStyle: {
                                                 type: 'dashed'
                                             }
                                         },
                                         splitLine:{
                                            show:false
                                         },
                                         show: true,
                                         axisLabel: {
                                             interval: 0,
                                             show: true,
                                             splitNumber: 15,
                                             textStyle: {
                                                fontSize: 10,
                                                color: '#fff'
                                             },
                                            },


                                     }],
                                     xAxis: [{
                                         type: 'category',
                                         axisLabel: {
                                             interval: 0,
                                             show: true,
                                             splitNumber: 15,
                                             textStyle: {
                                                 fontSize: 10,
                                                 color: '#fff'
                                             },
                                            interval:0,
                                            rotate:40,
                                            formatter: function(value) {
                                                var reg = new RegExp('社区卫生服务中心', "g");
                                                 	 return value.replace(reg, '');
                                              }
                                            },
                                         data: datacity,
                                         splitLine:{
                                            show:false
                                         }
                                     }],
                                     series: [{
                                             name: '门诊人次',
                                             type: 'bar',
                                             stack: 'sum',
                                             barWidth: '5px',
                                             data: data1


                                         },
                                         {
                                             name: '急诊人次',
                                             type: 'bar',
                                             barWidth: '5px',
                                             stack: 'sum',
                                             data: data2,

                                         },
                                         {
                                             name: '当日门急诊人次',
                                             type: 'bar',
                                             color: '#F6931C',
                                             stack: 'sum1',
                                             barWidth: '5px',
                                             data: data3

                                         },
                                         {
                                             name: '当月门急诊人次',
                                             type: 'bar',
                                             color: '#FFD52E',
                                             stack: 'sum1',
                                             barWidth: '5px',
                                             data: data4

                                         },
                                     ]
                                 };


                //社区就诊结束


               //初始化echarts实例
              var medicalAdvice_id = echarts.init(document.getElementById('medicalAdvice'));
                  medicalAdvice_id.setOption(medicalAdviceOption);
                   window.addEventListener("resize", function () {
                        medicalAdvice_id.resize();
                   });

        }
   });
}