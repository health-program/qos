
$(function(){



    $("#selectID").change(function(){
       echartses()
    })

    function convertUnitChartData(data, eventId, isRate) {
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
           eventIds:'21021,21022,21023,21024,21025,21026,21027,21028,21029'
       }


      $.ajax({
       type : "post",    //请求类型
        url : "/qos/analysis/data/get/unit",//请求的 URL地址
        data:arr,
        success: function (rawData) {
           rawData = rawData.result;
           dataMap['21021']=convertUnitChartData(rawData, '21021', true)
           dataMap['21022']=convertUnitChartData(rawData, '21022', true)
           dataMap['21023']=convertUnitChartData(rawData, '21023', true)
           dataMap['21024']=convertUnitChartData(rawData, '21024', true)
           dataMap['21025']=convertUnitChartData(rawData, '21025', true)
           dataMap['21026']=convertUnitChartData(rawData, '21026', true)
           dataMap['21027']=convertUnitChartData(rawData, '21027', true)
           dataMap['21028']=convertUnitChartData(rawData, '21028', true)
           dataMap['21029']=convertUnitChartData(rawData, '21029', true)
           echartses()

         }
    });

})