var dataMap={}
    var  data31011;
    var  data31012;
    var  data31013;




     var incomeSelectValue='31009';
     //封装方法开始
     var paycomparsionOption=function(id){

                     var eventId = $("#incomeSelect").val();
                       var data = dataMap[eventId];
                      //急诊人数开始
                         //指定图标的配置和数据
                     var incomedsOptions={
                         color: ['#ffffff', '#4aa4d4','#19D1FF'], //环形图每块的颜色
                       tooltip : {
                           trigger: 'item',
                           formatter: "{a} <br/>{b}(元) : {c} ({d}%)"
                       },

                       series : [

                       {
                                       name: '访问来源',
                                       type: 'pie',
                                       radius : '75%',
                                       center: ['52%', '60%'],
                                        data:[
                                                        {value:data31011, name:'药品'},
                                                                                         {value:data31012, name:'其他'},
                                                                                         {value:data31013, name:'医疗'},
                                                  ].sort(function (a, b) { return a.value - b.value; }),
                                       itemStyle: {
                                           emphasis: {
                                               shadowBlur: 10,
                                               shadowOffsetX: 0,
                                               shadowColor: 'rgba(0, 0, 0, 0.5)'
                                           }
                                       }
                                   }


                       ]
                   };
                         //初始化echarts实例
                      var newincomeshequID = echarts.init(document.getElementById('newincomeshequ'));
                         newincomeshequID.setOption(incomedsOptions);
                         //急诊人数结束
                   }
             //封装方法结束



                  var now = new Date();
                              var year = now.getFullYear(); //得到年份
                              var month = now.getMonth()+1;//得到月份
                              var date = now.getDate();//得到日期
                             var today=year + "-" + month + "-" + date;



 $(function(){





    function getRateNum(item, fixed) {
         var a = item.eventNum,
             b = item.totalNum,
             c = 0;
         if (a && b) {
             c = a / b * 100;
         }
         return c.toFixed(fixed || 2);
     }
     var arr={
           eventIds:'31011,31012,31013',  // 伟华
           startTime:today
       };


          if(incomeSelectValue=='31009'){
                         arr = {
                              eventIds:'31011,31012,31013',  // 伟华
                             startTime:today
                         }
                        $.ajax({
                             type : "post",    //请求类型
                             url : "/qos/analysis/data/get/total",//请求的 URL地址
                             data:arr,
                             success: function (rawData) {
                             data31011 = rawData.result['31001']
                             data31012 = rawData.result['31002']
                             data31013 = rawData.result['31003']
                                paycomparsionOption()
                            }
                         });
               }



      $("#incomeSelect").change(function(data){
      var incomeSelectValue= $('#incomeSelect option:selected') .val();
      if(incomeSelectValue=='31009'){
                arr = {
                   eventIds:'31011,31012,31013'// 伟华
                }

               $.ajax({
                    type : "post",    //请求类型
                    url : "/qos/analysis/data/get/total",//请求的 URL地址
                    data:arr,
                    success: function (rawData) {
                    data31011 = rawData.result['31011']
                     data31012 = rawData.result['31012']
                     data31013 = rawData.result['31013']
                    paycomparsionOption()
                   }
                });
      }

      if(incomeSelectValue=='31010'){
             arr={
                  eventIds:'15003,15004,15002' // 周亚
               }

          $.ajax({
               type : "post",    //请求类型
               url : "/qos/analysis/data/get/total",//请求的 URL地址
               data:arr,
               success: function (rawData) {
               data31011 = rawData.result['15002']
               data31012 = rawData.result['15004']
               data31013 = rawData.result['15003']
               paycomparsionOption()
              }
           });

      }
    })

     function convertMonthChartData(data, eventId, isRate) {
         var edata = data[eventId],
             max = 0,
             unit = [],
             values = [];
          edata && edata.forEach(function(item) {
             var r = isRate ? getRateNum(item) : item.count;
             max = Math.max(r, max);
             values.push(r);
             unit.push(item.unitName);
         });
         return {
             max: max,
             unit: unit,
             values: values
         }
     }


$("#incomeSelect").val('31009')
// 触发事件
$("#incomeSelect").trigger("change")

 })


 /*

                           {
                               name:'收入分类',
                               type:'pie',
                               radius : '70%',
                               center: ['50%', '50%'],
                               data:[
                                   {value:data31011, name:'药品'},
                                   {value:data31012, name:'其他'},
                                   {value:data31013, name:'医疗'},
                               ].sort(function (a, b) { return a.value - b.value; }),
                               roseType: 'radius',
                               label: {
                                   normal: {
                                       textStyle: {
                                           color: '#2cffff'
                                       }
                                   }
                               },
                               labelLine: {
                                   normal: {
                                       lineStyle: {
                                           color: '#2cffff'
                                       },
                                       smooth: 0.2,
                                       length: 10,
                                       length2: 20
                                   }
                               },
                               itemStyle: {
                                   normal: {
                                       color: '#2cffff',
                                       shadowBlur: 200,
                                       shadowColor: 'rgba(0, 0, 0, 0.5)'
                                   }
                               }

                           }

 */