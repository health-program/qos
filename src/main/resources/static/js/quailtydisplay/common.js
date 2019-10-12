function getRateNum(item, fixed) {
        var a = item.eventNum,
            b = item.totalNum,
            c = 0;
        if (a && b) {
            c = a / b * 100;
        }
        return c.toFixed(fixed || 2);
    }





function getRateNum(item, fixed) {
        var a = item.eventNum,
            b = item.totalNum,
            c = 0;
        if (a && b) {
            c = a / b * 100;
        }
        return c.toFixed(fixed || 2);
    }
 function convertMonthChartData(data, eventId, isRate) {
        var eventData = data[eventId],
            unitPoints = eventData.unitPoints,
            month = [],
            valuesMap = {},
            totalValues = [];

        var first = true;
        unitPoints && unitPoints.forEach(function(unitPoint) {
            var points = unitPoint.points,
                unitId = unitPoint.unitId;

            var values = valuesMap[unitId];
            if (!values) {
                values = [];
                valuesMap[unitId] = values;
            }

            for (var i = 0; i < points.length; i++) {
                var point = points[i];
                if (first) {
                    month.push(point.year + '-' + point.month);
                }

                values.push(isRate ? getRateNum(point) : point.totalNum);

                var totalVal = totalValues[i];
                if (!totalVal) {
                    totalVal = {
                        totalNum: point.totalNum,
                        eventNum: point.eventNum
                    }
                    totalValues[i] = totalVal;
                } else {
                    totalVal.totalNum += point.totalNum;
                    totalVal.eventNum += point.eventNum;
                }
            }

            first = false;
        });

        var totalVals = [];
        for (var j = 0; j < totalValues.length; j++) {
            var tv = totalValues[j];
            totalVals.push(isRate ? getRateNum(tv) : tv.totalNum);
        }

        valuesMap.total = totalVals;
        return {
            month: month,
            valuesMap: valuesMap
        }
    }





// -----------------------
    // 机构单位报表示例
    // -----------------------

    var unitChart;

    function showUnitChart(rawData) {
        if (!rawData) {
            $.postAjax('/qos/analysis/data/get/unit', requestParam, showUnitChart);
            return;
        }

        var unitDataMap = {};

        selectEvents.forEach(function(event) {
            unitDataMap[event.id] = convertUnitChartData(rawData, event.id, event.eventType == 1);
        });


        if (!unitChart) {
            unitChart = echarts.init(document.getElementById('unitChartGrid'));
        }

        var legendData = [];
        var xAxisData = [];
        var series = [];
        selectEvents.forEach(function(event) {
            legendData.push(event.name);
            xAxisData = unitDataMap[event.id].unit;
            series.push({
                type: 'bar',
                barWidth: 5,
                itemStyle: {
                    normal: {
                        barBorderRadius: 3,
                    }
                },
                name: event.name,
                data: unitDataMap[event.id].values
            });
        });
       unitChart.setOption(option, true);
    }

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

    // -----------------------
    // 机构单位报表示例
    // -----------------------