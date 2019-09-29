 var dataMap={}
   var  data15005;
   var  data15006;
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


    $("#incomeSelect").change(function(){
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
           eventIds:'15005,15006',  // 伟华
           startTime:'2018-12-10'
       }

      $.ajax({
        type : "post",    //请求类型
        url : "/qos/analysis/data/get/total",//请求的 URL地址
        data:arr,
        success: function (rawData) {
        data15005 = rawData.result['15005']
        data15006 = rawData.result['15006']
        paycomparsionOption()
       }
    });



var paycomparsionOption=function(id){
  var eventId = $("#incomeSelect").val();
    var data = dataMap[eventId];
   //急诊人数开始
      //指定图标的配置和数据
  var paycomparsionOption={
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
            name:'收入分类',
            type:'pie',
            radius : '140%',
            center: ['50%', '50%'],
            data:[
                {value:data15005, name:'医保'},
                {value:data15006, name:'自费'}
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


       var incomeKind_id = echarts.init(document.getElementById('incomeKind'));
      incomeKind_id.setOption(paycomparsionOption);
      //急诊人数结束
}







})