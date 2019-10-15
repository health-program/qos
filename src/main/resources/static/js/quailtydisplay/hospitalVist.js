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




var data1 = [20, 30, 20, 30, 20, 30, 20, 30, 20, 30];
var data2 = [9, 30, 9, 60, 70, 20, 59, 20, 49, 20];
var data3 = [20, 30, 20, 30, 20, 30, 20, 30, 20, 30];
var data4 = [9, 30, 9, 60, 70, 20, 59, 20, 49, 20];
var datacity = ['济南市', '青岛市', '淄博市', '枣庄', '东营', '烟台市', '潍坊市', '济宁市', '威海市', '泰安市'];




         //社区就诊开始
          var medicalAdviceOption= {
                                       color: ['#388BFF', '#05C3FA', '#F6931C', '#FFD52E'],
                                       tooltip: {
                                           trigger: 'axis',
                                       },
                                       legend: {

                                           top: '8%',
                                           data: ['存量', '新增', '拆除', '整改'],
                                       },
                                       grid: { //图表的位置
                                           top: '20%',
                                           left: '3%',
                                           right: '4%',
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
                                                   type: 'dashed',
                                                   color:'#ffffff'
                                               }
                                           },
                                           show: true

                                       }],
                                       xAxis: [{
                                           type: 'category',
                                           axisLabel: {
                                               interval: 0,
                                               show: true,
                                               splitNumber: 15,
                                               textStyle: {
                                                   fontSize: 10,
                                                   color: '#000'
                                               },

                                           },
                                           data: datacity,
                                           axisLine:{
                                                 lineStyle:{
                                                      color:'#ffffff',
                                                      width:2,//这里是为了突出显示加上的
                                                }
                                             }
                                       }],
                                       series: [{
                                               name: '存量',
                                               type: 'bar',
                                               stack: 'sum',
                                               barWidth: '10px',
                                               data: data1,
                                                 axisLine:{
                                                                                                lineStyle:{
                                                                                                     color:'#ffffff',
                                                                                                     width:2,//这里是为了突出显示加上的
                                                                                               }
                                                                                            }
                                              },
                                           {
                                               name: '新增',
                                               type: 'bar',
                                               barWidth: '10px',
                                               stack: 'sum',
                                               data: data2,

                                           },
                                           {
                                               name: '拆除',
                                               type: 'bar',
                                               color: '#F6931C',
                                               stack: 'sum1',
                                               barWidth: '10px',
                                               data: data3

                                           },
                                           {
                                               name: '整改',
                                               type: 'bar',
                                               color: '#FFD52E',
                                               stack: 'sum1',
                                               barWidth: '20px',
                                               data: data3

                                           },
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