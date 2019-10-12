$(function(){
    //支付方式对比
         var arr = {
         //  eventIds:'31007,31008,31001'
              eventIds:'31007,31008,31001'

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
       var  unit31007Data=convertUnitChartData(rawData.result, '31007', false); //
       var  unit31008Data=convertUnitChartData(rawData.result, '31008', false); //
       var  unit31001Data=convertUnitChartData(rawData.result, '31001', false); //


        var  unit31001Data31001=rawData.result['31001'].sort(compareBigToSmall('count'));
        var unit31001DataArray= unit31001Data31001.slice(0,5)

         // 排名最高的已经有了
        var unit31007DatanewArray=[]
       for(var i=0;i<unit31001DataArray.length;i++){
            for(var j=0;j<rawData.result['31007'].length;j++){
                if(unit31001Data31001[i].unitId ==rawData.result['31007'][j].unitId){
                    unit31007DatanewArray.push(rawData.result['31007'][j])
                }
            }
       }

       var unit31008DatanewArray=[]
              for(var i=0;i<unit31001DataArray.length;i++){
                   for(var j=0;j<rawData.result['31008'].length;j++){
                        if(unit31001DataArray[i].unitId ==rawData.result['31008'][j].unitId){
                           unit31008DatanewArray.push(rawData.result['31008'][j])
                       }
                   }
              }

 var newast31007 = {}
    newast31007['31007']=unit31007DatanewArray
 var  unit31007DatanewArrayunit31007DatanewArray=convertUnitChartData(newast31007, '31007', false);


 var newast31008 = {}
     newast31008['31008']=unit31008DatanewArray
  var  unit31008DatanewArrayunit31008DatanewArray=convertUnitChartData(newast31008, '31008', false);


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
                              interval:0,
                                 rotate:40
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
                       data:unit31008DatanewArrayunit31008DatanewArray.unit,
                        axisLabel: {
                           show: true,
                           color: '#fff',
                            fontSize: 14,
                            formatter: function(value) {
                            var reg = new RegExp('昆山市', "g");
                               if (value.length > 3) {
                                 return value.replace(reg, '').substring(0, 3) + ".";
                               } else {
                                 return value;
                               }
                              },
                         }
                   }, ],
                   series: [
                          {
                            name: '门诊人次',
                            position: 'top',
                            type: 'bar',
                            stack: '费用',
                            data: unit31007DatanewArrayunit31007DatanewArray.values,
                           //data: [2.5, 2, 1.5, 2.5, 4],
                           barWidth: '80%',
                           itemStyle: {
                               color: '#567db8',
                           },
                        },
                        {
                         name: '急诊人次',
                           position: 'top',
                           type: 'bar',
                            stack: '费用',
                           data: unit31008DatanewArrayunit31008DatanewArray.values,
                           // data: [2, 3, 2, 2, 2.5],
                           barWidth: '80%',
                           itemStyle: {
                                color: '#4aa4d4',
                           },
                         }
                     ]
               };



                 //社区就诊结束


                //初始化echarts实例
               var medicalAdvice_id = echarts.init(document.getElementById('middle_hospitel'));
                   medicalAdvice_id.setOption(medicalAdviceOption);
                    window.addEventListener("resize", function () {
                         medicalAdvice_id.resize();
                    });

         }
    });




})