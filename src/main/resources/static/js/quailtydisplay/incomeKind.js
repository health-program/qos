var dataMap={}
    var  data31011;
    var  data31012;
    var  data31013;






 $(function(){

         //封装方法开始
              var paycomparsionOption=function(id){
                var eventId = $("#incomeSelect").val();
                  var data = dataMap[eventId];

                 //急诊人数开始
                    //指定图标的配置和数据
                var incomedsOptions={
                  tooltip : {
                      trigger: 'item',
                      formatter: "{a} <br/>{b} : {c} ({d}%)"
                  },
                  visualMap: {
                      show: false,
                       min: 800,
                       max: 50000,
                      inRange: {
                          color: ['#5569be']
                      }
                  },
                  series : [
                      {
                          name:'支付方式',
                          type:'pie',
                          radius : '70%',
                          center: ['50%', '50%'],
                          data:[
                              {value:data31011, name:'药品收入'},
                              {value:data31012, name:'其他收入'},
                              {value:data31013, name:'医疗收入'},
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
                  ]
              };
                    //初始化echarts实例
                 var incomeKind = echarts.init(document.getElementById('incomeKind'));
                    incomeKind.setOption(incomedsOptions);
                    //急诊人数结束
              }
        //封装方法结束

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
           startTime:'2018-12-10'
       };

          var incomeSelectValue='31009';
          if(incomeSelectValue=='31009'){
                         arr = {
                              eventIds:'31011,31012,31013',  // 伟华
                             startTime:'2018-12-10'
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
                   eventIds:'31011,31012,31013',  // 伟华
                    startTime:'2018-12-10'
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
                  eventIds:'15003,15004,15002',  // 周亚
                  startTime:'2018-12-10'
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





 })