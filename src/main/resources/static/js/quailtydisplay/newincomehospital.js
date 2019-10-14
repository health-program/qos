var dataMap={}
    var  data31011;
    var  data31012;
    var  data31013;

     var incomeSelectValues='31010';
     //封装方法开始


     var now = new Date();
                 var year = now.getFullYear(); //得到年份
                 var month = now.getMonth()+1;//得到月份
                 var date = now.getDate();//得到日期
                var today=year + "-" + month + "-" + date;

             //封装方法结束

 $(function(){


$("#incomeSelect").val('30010')
// 触发事件
$("#incomeSelect").trigger("change")


       var arr={
           eventIds:'15003,15004,15002',  // 周亚
            startTime:today
       };

      if(incomeSelectValues=='31010'){
                         arrs = {
                              eventIds:'15003,15004,15002',  // 周亚
                             startTime:today
                         }
                        $.ajax({
                             type : "post",    //请求类型
                             url : "/qos/analysis/data/get/total",//请求的 URL地址
                             data:arrs,
                             success: function (rawData) {
                                 data31011 = rawData.result['15002']
                                            data31012 = rawData.result['15004']
                                            data31013 = rawData.result['15003']

                                               var newincomehosiptalOptions={
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
                                                                   ]
                                                               };
                                                                     //初始化echarts实例
                                                                  var newincomehosiptalID = echarts.init(document.getElementById('newincomehosiptal'));
                                                                     newincomehosiptalID.setOption(newincomehosiptalOptions);

                            }
                         });
               }








 })