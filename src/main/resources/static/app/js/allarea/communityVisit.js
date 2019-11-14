$(function(){
    getNumbers()
})
window.setInterval(function(){
    getNumbers()
},300000)

var communityVisitLen;
function getNumbers(){
    $.ajax({
        type : "post",    //请求类型
        url : URLPATH+"/home/page/qos/quailtydisplay/getOutPatientNumber",//请求的 URL地址
        success: function (rawData) {

            var patientNumberList = rawData.result.patientNumberList;
            var emergencyNumberList = rawData.result.emergencyNumberList;
            var todayNumberList = rawData.result.todayNumberList;
            var thisMonthNumberList = rawData.result.thisMonthNumberList;
            communityVisitLen= rawData.result.thisMonthNumberList

            // 指定图表的配置项和数据
            var data1 = [];//门诊
            var data2 = [];//急诊
            var data3 = [];//当日
            var data4 = [];//当月
            var datacity = [] ;//单位
            patientNumberList && patientNumberList.forEach(function(unitPoint) {
                data1.push(unitPoint.count);
            })
            emergencyNumberList && emergencyNumberList.forEach(function(unitPoint) {
                data2.push(unitPoint.count);
            })
            todayNumberList && todayNumberList.forEach(function(unitPoint) {
                data3.push(unitPoint.count);
            })
            thisMonthNumberList && thisMonthNumberList.forEach(function(unitPoint) {
                data4.push(unitPoint.count);
            })
            patientNumberList && patientNumberList.forEach(function(unitPoint) {
                datacity.push(unitPoint.unitName);
            })



            var communityVisitOption={
                color: ['#388BFF', '#05C3FA', '#F6931C', '#FFD52E'],
                tooltip: {
                    trigger: 'axis',
                },
                legend: {
                    top: '8%',
                    textStyle: {
                        fontSize: 10,
                        color: '#fff'
                    },
                   // data: ['当天门诊人次', '当天急诊人次', '当月门诊人次', '当月急诊人次'],
                    data: [ '当月门诊人次', '当月急诊人次','当天门诊人次', '当天急诊人次'],
                },
                grid: { //图表的位置
                    top: '20%',
                    left: '3%',
                    right: '2%',
                    bottom: '5%',
                    containLabel: true
                },
                yAxis: [{
                    type: 'value',
                    axisLabel: {
                        show: true,
                        interval: 'auto',
                        formatter: '{value} '
                    },
                    splitLine: {
                        show: true,
                        lineStyle: {
                            type: 'dashed'
                        }
                    },
                    show: true,
                    axisLabel: {
                        interval: 0,
                        show: true,
                        splitNumber: 15,
                        textStyle: {
                            fontSize: 10,
                            color: '#50A2C1'
                        },
                    },

                    splitLine: {
                        show: true,
                        lineStyle: {
                            type: 'solid',
                            color:'#113d5e'
                        }
                    },
                    axisLine:{
                        show:false
                    },
                    axisTick:{
                        show:false
                    },
                }],
                xAxis: [{
                    type: 'category',
                    splitLine:{
                        show:false
                    },
                    axisLabel: {
                        interval: 0,
                        show: true,
                        splitNumber: 15,
                        textStyle: {
                            fontSize: 10,
                            color: '#50A2C1'
                        },
                        interval:0,
                        rotate:40,
                        formatter: function(value) {
                            var reg = new RegExp('社区卫生服务中心', "g");
                            return value.replace(reg, '');
                        }
                    },
                    data: datacity,
                    axisLine:{
                        show:false
                    },
                    axisTick:{
                        show:false
                    }

                }],
                series: [
                    {
                        name: '当月门诊人次',
                        type: 'bar',
                        color: '#F6931C',
                        stack: 'sum1',
                        barWidth: '5px',
                        data: data3

                    },
                    {
                        name: '当月急诊人次',
                        type: 'bar',
                        color: '#FFD52E',
                        stack: 'sum1',
                        barWidth: '5px',
                        data: data4

                    },{
                    name: '当天门诊人次',
                    type: 'bar',
                    stack: 'sum',
                    barWidth: '5px',
                    data: data1
                 },
                    {
                        name: '当天急诊人次',
                        type: 'bar',
                        barWidth: '5px',
                        stack: 'sum',
                        data: data2,

                    }

                ]
            };


            var communityVisitID = echarts.init(document.getElementById('communityVisit'));
            communityVisitID.setOption(communityVisitOption);
            var index = 0; //播放所在下标
            setInterval(function() {
                 communityVisitID.dispatchAction({
                    type: 'showTip',
                    seriesIndex: 0,
                    dataIndex: index
                });
                index++;
                if(index > communityVisitLen.length) {
                    index = 0;
                }
            }, 5000)

            window.addEventListener("resize", function () {
                 communityVisitID.resize();
            });

        }
    });
}