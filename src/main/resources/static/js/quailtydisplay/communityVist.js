$(function(){
    //支付方式对比
         var arr = {
           eventIds:'13001,13002,13003',
           ignoreUnitIds:'320583810343'
         }


         //从大到小排序
         function compareBigToSmall(property){
             return function(a,b){
                 var value1 = a[property];
                 var value2 = b[property];
                 return  value2 - value1;
             }
         };



        var ri = {
             eventIds:'13003',
             ignoreUnitIds:'320583810343'
        }



       $.ajax({
         type : "post",    //请求类型
        url : "/qos/analysis/data/get/unit",//请求的 URL地址
        data:arr,
        success: function (rawData) {

       var  unit13001Data=convertUnitChartData(rawData.result, '13001', false); //
       var  unit13002Data=convertUnitChartData(rawData.result, '13002', false); //
       var  unit13003Data=convertUnitChartData(rawData.result, '13003', false); //






        var  unit13003Data13003=rawData.result['13003'].sort(compareBigToSmall('count'));
        var unit13003DataArray= unit13003Data13003
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

 var newast13001 = {}
    newast13001['13001']=unit13001DatanewArray
 var  unit13001DatanewArrayunit13001DatanewArray=convertUnitChartData(newast13001, '13001', false);


 var newast13002 = {}
     newast13002['13002']=unit13002DatanewArray
  var  unit13002DatanewArrayunit13002DatanewArray=convertUnitChartData(newast13002, '13002', false);


// 指定图表的配置项和数据
var data1 = [20, 30, 20, 30, 20, 30, 20, 30, 20, 30];
var data2 = [9, 30, 9, 60, 70, 20, 59, 20, 49, 20];
var data3 = [20, 30, 20, 30, 20, 30, 20, 30, 20, 30];
var data4 = [9, 30, 9, 60, 70, 20, 59, 20, 49, 20];
var datacity =unit13002DatanewArrayunit13002DatanewArray.unit;






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
                                          data: ['存量', '新增', '拆除', '整改'],
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
                                          data: datacity
                                      }],
                                      series: [{
                                              name: '存量',
                                              type: 'bar',
                                              stack: 'sum',
                                              barWidth: '5px',
                                              data: data1


                                          },
                                          {
                                              name: '新增',
                                              type: 'bar',
                                              barWidth: '5px',
                                              stack: 'sum',
                                              data: data2,

                                          },
                                          {
                                              name: '拆除',
                                              type: 'bar',
                                              color: '#F6931C',
                                              stack: 'sum1',
                                              barWidth: '5px',
                                              data: data3

                                          },
                                          {
                                              name: '整改',
                                              type: 'bar',
                                              color: '#FFD52E',
                                              stack: 'sum1',
                                              barWidth: '5px',
                                              data: data3

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




})