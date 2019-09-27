 var dataMap={}
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


    $("#selectID").change(function(){
       echartses()
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
       var arr = {
           eventIds:'31009,31010'  // 伟华
       }


      $.ajax({
       type : "post",    //请求类型
        url : "/qos/analysis/data/get/day/instalments",//请求的 URL地址
        data:arr,
        success: function (rawData) {

           var  month31009Data=convertMonthChartData(rawData.result, '31009', false); //
           var  month31010Data=convertMonthChartData(rawData.result, '31010', false); //




           echartses()

         }
    });







var echartses=function(id){
  var eventId = $("#selectID").val();
    var data = dataMap[eventId];
   //急诊人数开始
      //指定图标的配置和数据
      var emergencyoption = {
        tooltip: {
                      trigger: 'axis',
                      axisPointer: {
                          type: 'shadow'
                      }
                  },

         dataZoom:{
                   realtime:true, //拖动滚动条时是否动态的更新图表数据
                   height:25,//滚动条高度
                   start:40,//滚动条开始位置（共100等份）
                   end:65//结束位置（共100等份）
          },
         xAxis: {
              type: 'category',
              data: data.unit,

              axisLine: {
                  lineStyle: {
                      color: '#19d1ff',
                      width: 1, //这里是为了突出显示加上的
                  }
              },

               grid: {
                              left: 45,
                              right: 20,
                              bottom: 10,
                              containLabel: true
               }

          },
          yAxis: {
              type: 'value',
              splitLine: {
                  show: false
              },
              axisLine: {
                  lineStyle: {
                      color: '#19d1ff',
                      width: 1, //这里是为了突出显示加上的
                  }
              }
          },
          series: [{
              itemStyle: {
                  normal: {
                      // 随机显示
                      color: function(d) { return "#" + '439AFF'; }

                  },
              },
              barWidth: 15,
              data: data.values,
              type: 'bar'
          }]
      };
      //初始化echarts实例

      var myChartEmergency = echarts.init(document.getElementById('emergency'));
      myChartEmergency.setOption(emergencyoption);
      //急诊人数结束
}







})