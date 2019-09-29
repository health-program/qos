$(function(){
    //支付方式对比
         var arr = {
           eventIds:'13002,14001,13001',
           startTime:'2019-09-10',
           byUnit: 0
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
        url : "/qos/analysis/data/get/day/instalments",//请求的 URL地址
        data:arr,
        success: function (rawData) {

      var arr13001Month=[]
      var arr13001MonthY=[]
      var arr13002MonthY=[]
       var arr14001MonthY=[]
       for(var i=0;i<rawData.result['13001'].length;i++){
            arr13001Month.push(rawData.result['13001'][i].day)
            arr13001MonthY.push(rawData.result['13001'][i].totalNum)
       }


        for(var i=0;i<rawData.result['13002'].length;i++){
              arr13002MonthY.push(rawData.result['13002'][i].totalNum)
        }


         for(var i=0;i<rawData.result['14001'].length;i++){
              arr14001MonthY.push(rawData.result['14001'][i].totalNum)
        }





         //社区就诊开始
           medicaladviceoption ={
                                  tooltip : {
                                      trigger: 'axis'
                                  },
                                  legend: {
                                      data:['急诊人次','入院人次','普通门诊人次'],
                                       textStyle:{//图例文字的样式
                                                  color:'#ffffff',
                                                  fontSize:16
                                              }
                                  },
                                  toolbox: {

                                  },
                                  grid: {
                                      left: '3%',
                                      right: '4%',
                                      bottom: '3%',
                                      containLabel: true
                                  },
                                  xAxis : [
                                      {
                                          type : 'category',
                                          boundaryGap : false,
                                           axisLine:{
                                                lineStyle:{
                                                    color:'#ffffff',
                                                     width:1,//这里是为了突出显示加上的
                                                                                          }
                                              },
                                          data :arr13001Month
                                      }
                                  ],
                                  yAxis : [
                                      {
                                          type : 'value',
                                          axisLine:{
                                               lineStyle:{
                                                    color:'#ffffff',
                                                    width:1,//这里是为了突出显示加上的
                                                }
                                         }

                                      }
                                  ],
                                  series : [
                                      {
                                          name:'急诊人次',
                                          type:'line',
                                          stack: '总量',
                                          areaStyle: {normal: {
                                              color: new echarts.graphic.LinearGradient(0, 0, 0, 1,[{
                                                      offset: 0, color: '#74a5d4' // 0% 处的颜色
                                               }, {
                                               offset: 0.4, color: '#74a5d4' // 100% 处的颜色
                                                }, {
                                               offset: 1, color: '#74a5d4' // 100% 处的颜色
                                                }]
                                         ), //背景渐变色
                                          }},
                                          data: arr13002MonthY, // total 如果是单位，就改成该单位的id
                                      },
                                       {
                                          name:'入院人次',
                                          type:'line',
                                          stack: '总量',
                                          areaStyle: {normal: {}},
                                          data: arr14001MonthY
                                       },
                                       {
                                          name:'普通门诊人次',
                                         type:'line',
                                         stack: '总量',
                                         areaStyle: {normal: {
                                              color: new echarts.graphic.LinearGradient(0, 0, 0, 1,[{
                                             offset: 0, color: '#6e90ba' // 0% 处的颜色
                                         }, {
                                              offset: 0.4, color: '#6e90ba' // 100% 处的颜色
                                         }, {
                                              offset: 1, color: '#6e90ba' // 100% 处的颜色
                                        }]
                                        ), //背景渐变色
                                         }},
                                         data:arr13001MonthY
                                        }
                                  ]
                              };

           var myChartMedicaladvice = echarts.init(document.getElementById('medicaladvice'));
         	   myChartMedicaladvice.setOption(medicaladviceoption);


                    //初始化echarts实例
                    window.addEventListener("resize", function () {
                         myChartMedicaladvice.resize();
                    });

         }
    });




})