 var dataMap={}
   var  data31009;
   var  data31010;
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
       paycomparsionOption()
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
           eventIds:'31009,31010',  // 伟华
           startTime:'2018-12-10'
       }

      $.ajax({
        type : "post",    //请求类型
        url : "/qos/analysis/data/get/total",//请求的 URL地址
        data:arr,
        success: function (rawData) {
        data31009 = rawData.result['31009']
        data31010 = rawData.result['31010']
        paycomparsionOption()
       }
    });



var paycomparsionOption=function(id){
  var eventId = $("#selectID").val();
    var data = dataMap[eventId];
   //急诊人数开始
      //指定图标的配置和数据
      alert(788999)
  var paycomparsionOption={
   color: ['#4DFFE3','#4DE0FF','#4DFF8F','#ADFF4D'],
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    visualMap: {
        show: false,
        min: 80,
        max: 600,
        inRange: {
            colorLightness: [0, 1]
        }
    },
    series : [
        {
            name:'访问来源',
            type:'pie',
            radius : '55%',
            center: ['50%', '50%'],
            data:[
                {value:data31009, name:'医保'},
                {value:data31010, name:'自费'}
            ].sort(function (a, b) { return a.value - b.value; }),
            roseType: 'radius',
            label: {
                normal: {
                    textStyle: {
                        color: 'rgba(255, 255, 255, 0.3)'
                    }
                }
            },
            labelLine: {
                normal: {
                    lineStyle: {
                        color: 'rgba(255, 255, 255, 0.3)'
                    },
                    smooth: 0.2,
                    length: 10,
                    length2: 20
                }
            },
            itemStyle: {
                normal: {
                    color: '#c23531',
                    shadowBlur: 200,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }

        }
    ]
};
      //初始化echarts实例
   var paycomparsion_id = echarts.init(document.getElementById('paycomparsion'));
      paycomparsion_id.setOption(paycomparsionOption);
      //急诊人数结束
}







})