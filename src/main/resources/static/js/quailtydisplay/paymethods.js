 var dataMap={}
   var  data31009;
   var  data31010;


     var selectIDValue='31009';
//封装的方法开始
       // 15005,15006
var paycomparsionOptions=function(id){
    var eventId = $("#selectID").val();
    var data = dataMap[eventId];
    var paycomparsionOptions={
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
            radius : '80%',
            center: ['50%', '50%'],
            data:[
                {value:data31009, name:'医保'},
                {value:data31010, name:'自费'}
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
    var paycomparsion_id = echarts.init(document.getElementById('paycomparsion'));
      paycomparsion_id.setOption(paycomparsionOptions);
 }
//封装的方法结束

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
          eventIds:'31009,31010'  // 伟华
      };

         if(selectIDValue=='31009'){
                        arr = {
                            eventIds:'31009,31010'  // 伟华
                        }

                       $.ajax({
                            type : "post",    //请求类型
                            url : "/qos/analysis/data/get/total",//请求的 URL地址
                            data:arr,
                            success: function (rawData) {
                            data31009 = rawData.result['31009']
                            data31010 = rawData.result['31010']
                             paycomparsionOptions()
                           }
                        });
              }
         $("#selectID").change(function(data){
         var selectIDValue= $('#selectID option:selected') .val();
         if(selectIDValue=='31009'){
               arr = {
                   eventIds:'31009,31010' // 伟华

               }
                  $.ajax({
                   type : "post",    //请求类型
                   url : "/qos/analysis/data/get/total",//请求的 URL地址
                   data:arr,
                   success: function (rawData) {
                   data31009 = rawData.result['31009']
                   data31010 = rawData.result['31010']
                   paycomparsionOptions()
                  }
               });
     }
         if(selectIDValue=='31010'){
         arr={
                 eventIds:'15005,15006',  // 伟华
              }

         $.ajax({
              type : "post",    //请求类型
              url : "/qos/analysis/data/get/total",//请求的 URL地址
              data:arr,
              success: function (rawData) {
              data31009 = rawData.result['15005']
              data31010 = rawData.result['15006']
              paycomparsionOptions()
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
          //指定图标的配置和数据
          // 21001 综合健康管理服务包签约率（收费）




$("#selectID").val('31009')
// 触发事件
$("#selectID").trigger("change")




})