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
