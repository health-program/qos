$(function(){
    //支付方式对比
         var arr = {
           eventIds:'13001,13002,13003'
         }


         //从大到小排序
         function compareBigToSmall(property){
             return function(a,b){
                 var value1 = a[property];
                 var value2 = b[property];
                 return  value2 - value1;
             }
         };






       $.ajax({
         type : "post",    //请求类型
        url : "/qos/analysis/data/get/unit",//请求的 URL地址
        data:arr,
        success: function (rawData) {

       var  unit13001Data=convertUnitChartData(rawData.result, '13001', false); //
       var  unit13002Data=convertUnitChartData(rawData.result, '13002', false); //
       var  unit13003Data=convertUnitChartData(rawData.result, '13003', false); //






        var  unit13003Data13003=rawData.result['13003'].sort(compareBigToSmall('count'));
        var unit13003DataArray= unit13003Data13003.slice(0,5)
        alert('qiand'+unit13003DataArray.length)
         // 排名最高的已经有了



       var unit13001DatanewArray=[]
       for(var i=0;i<unit13003DataArray.length;i++){
            for(var j=0;j<rawData.result['13001'].length;j++){
                if(unit13003Data13003[i].unitId ==rawData.result['13001'][j].unitId){
                    unit13001DatanewArray.push(rawData.result['13001'][j])
                }
            }
       }



        var unit13002DatanewArray=[]
              for(var i=0;i<unit13003DataArray.length;i++){
                   for(var j=0;j<rawData.result['13002'].length;j++){
                       if(unit13003DataArray[i].unitId ==rawData.result['13002'][j].unitId){
                           unit13002DatanewArray.push(rawData.result['13002'][j])
                       }
                   }
              }

alert(999)
var newast13001 = {}
    newast13001['13001']=unit13001DatanewArray
 var  unit13001DatanewArrayunit13001DatanewArray=convertUnitChartData(newast13001, '13001', false);


 var newast13002 = {}
     newast13002['13002']=unit13002DatanewArray
  var  unit13002DatanewArrayunit13002DatanewArray=convertUnitChartData(newast13002, '13002', false);


       debugger

         //社区就诊开始

                  var medicalAdviceOption={
                   legend: {
                       name: [],
                       orient: 'align',
                       align: 'left',
                       right: 15,
                       top: 'top',
                       textStyle: {
                           color: '#fff',
                           fontSize: 14,
                       },
                   },

                   tooltip: {
                       trigger: 'axis',
                       axisPointer: {
                           type: 'shadow'
                       }
                   },
                   xAxis: {
                          type: 'value',
                          position: 'bottom',
                          axisLabel: {
                              show: true,
                              color: '#fff',
                              fontSize: 14,
                          },
                          splitLine: {
                              show: false
                          },
                          axisLine: {
                              // show:false,
                              lineStyle: {
                                  color: '#FFFFFF'
                              }
                          },
                          axisTick: {
                              show: false
                          },

                      },
                   yAxis: [{
                       type: 'category',
                       inverse: true,
                       axisTick: {
                           show: false
                       },
                       axisLine: {
                           lineStyle: {
                               color: '#FFFFFF',
                           }
                       },
                       data:unit13002DatanewArrayunit13002DatanewArray.unit,
                        axisLabel: {
                           show: true,
                           color: '#fff',
                            fontSize: 14,
                         interval: 0,
                         formatter: function(value) {
                           if (value.length > 2) {
                             return value.substring(0, 2) + "...";
                           } else {
                             return value;
                           }
                         }
                       }
                   }, ],
                   series: [
                          {
                            name: '门诊人次',
                            position: 'bottom',
                            type: 'bar',
                            stack: '费用',
                            data: unit13001DatanewArrayunit13001DatanewArray.values,
                           //data: [2.5, 2, 1.5, 2.5, 4],
                           barWidth: '30%',
                           itemStyle: {
                               color: '#567db8',
                           },
                        },
                        {
                         name: '急诊人次',
                           position: 'bottom',
                           type: 'bar',
                            stack: '费用',
                           data: unit13002DatanewArrayunit13002DatanewArray.values,
                           // data: [2, 3, 2, 2, 2.5],
                           barWidth: '30%',
                           itemStyle: {
                                color: 'red',
                           },
                         }
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




})