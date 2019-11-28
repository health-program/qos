
 //返回 01-12 的月份值     
 function getMonth(date) {
     var month = "";
     month = date.getMonth() + 1; //getMonth()得到的月份是0-11    
     if (month < 10) {
         month = "0" + month;
     }
     return month;
 }
 //返回01-30的日期    
 function getDay(date) {
     var day = "";
     day = date.getDate();
     if (day < 10) {
         day = "0" + day;
     }
     return day;
 }
 //小时  
 function getHours(date) {
     var hours = "";
     hours = date.getHours();
     if (hours < 10) {
         hours = "0" + hours;
     }
     return hours;
 }
 //分  
 function getMinutes(date) {
     var minute = "";
     minute = date.getMinutes();
     if (minute < 10) {
         minute = "0" + minute;
     }
     return minute;
 }
 //秒  
 function getSeconds(date) {
     var second = "";
     second = date.getSeconds();
     if (second < 10) {
         second = "0" + second;
     }
     return second;
 }

 function hideIdentification(value) {
     if (value) {
         var l = value.length;
         if (l > 7) {
             var s = value.substr(0, 3);
             var a = value.length - 7;
             for (; a > 0; a--) s += "*";
             s += value.substr(value.length - 4, 4);
             return s;
         } else {
             return value;
         }
     }
     return "";
 }

 function omitString(str, length) {
     if (str && str.length > length) {
         return str.substring(0, length) + "...";
     }
     return str;
 }


 // -----------------------
 // 月报表示例
 // -----------------------

 var monthChart;

 function showMonthChart(rawData, selectUnit) {
     if (!rawData) {
         $.postAjax('/qos/analysis/data/get/month/instalments', requestParam, showMonthChart);
         return;
     }

     var monthDataMap = {};

     selectEvents.forEach(function(event) {
         monthDataMap[event.id] = convertMonthChartData(rawData, event.id, event.eventType == 1);
     });

     var selectUnit = selectUnit || 'total';

     if (!monthChart) {
         monthChart = echarts.init(document.getElementById('monthChartGrid'));
     }

     var legendData = [];
     var xAxisData = [];
     var series = [];

     selectEvents.forEach(function(event) {
         legendData.push(event.name);
         xAxisData = monthDataMap[event.id].month;
         series.push({
             type: 'line',
             name: event.name,
             data: monthDataMap[event.id].valuesMap[selectUnit]
         });
     });

     var option = {
         //加载数据图表
         title: {
             text: '按月数据图'
         },
         tooltip: {
             trigger: 'axis',
             axisPointer: {
                 type: 'shadow'
             }
         },
         legend: {
             type: "scroll",
             data: legendData,
             bottom: 0
         },
         xAxis: {
             type: 'category',
             boundaryGap: false,
             axisLabel: {
                 interval: 0,
                 rotate: 40 //角度顺时针计算的
             },
             data: xAxisData
         },
         grid: {
             left: 45,
             right: 20,
             bottom: 40,
             containLabel: true
         },
         yAxis: {
             type: 'value'
         },
         series: series
     }

     monthChart.setOption(option, true);
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


 function readRequestParam() {
     requestParam = {};

     var eventIds = $("#eventIds").val();
     var startTime = $("#startTime").val();
     var endTime = $("#endTime").val();

     if (startTime) {
         requestParam.startTime = startTime;
     }

     if (endTime) {
         requestParam.endTime = endTime;
     }

     requestParam.eventIds = eventIds;

     selectEvents = [];

     eventIds.split(',').forEach(function(id) {
         selectEvents.push(eventMap[id]);
     });
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