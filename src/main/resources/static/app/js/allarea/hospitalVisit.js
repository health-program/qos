$(function(){
    gethospitelNumbers()
})
window.setInterval(function(){
    gethospitelNumbers()
},300000)
var hospitalLen;
function gethospitelNumbers(){
    $.ajax({
        type : "post",    //请求类型
        url : URLPATH+"/home/page/qos/hospital/getOutPatientNumber",//请求的 URL地址
        success: function (rawData) {
            var patientNumberList = rawData.result.patientHospitalNumberList;
            var emergencyNumberList = rawData.result.emergencyHospitalNumberList;
            var todayNumberList = rawData.result.todayNumberHospitalList;
            var thisMonthNumberList = rawData.result.thisMonthNumberHospitalList;

            hospitalLen= rawData.result.patientHospitalNumberList;
            // 指定图表的配置项和数据
            var data1 = [];//当日门诊
            var data2 = [];//当日急诊
            var data3 = [];//当月门诊
            var data4 = [];//当月急诊
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



            //社区就诊开始
            var hospitalVisitOption={
                color: ['#388BFF', '#05C3FA', '#F6931C', '#FFD52E'],
                tooltip: {
                    trigger: 'axis',
                },
                legend: {
                    top: '8%',
                    textStyle: {
                        fontSize: 10,
                        color: '#FFFFFF'
                    },
                    //data: ['当日门诊人次', '当日急诊人次', '当月门诊人次', '当月急诊人次'],
                    data: ['当月门诊人次', '当月急诊人次', '当日门诊人次', '当日急诊人次'],
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
                    },
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
                    name: '当日门诊人次',
                    type: 'bar',
                    stack: 'sum',
                    barWidth: '5px',
                    data: data1
                },
                    {
                        name: '当日急诊人次',
                        type: 'bar',
                        barWidth: '5px',
                        stack: 'sum',
                        data: data2,

                    }

                ]
            };




            //初始化echarts实例
            var hospitalVisitID = echarts.init(document.getElementById('hospitalVisit'));
            hospitalVisitID.setOption(hospitalVisitOption);
            var index = 0; //播放所在下标
            var mTime = setInterval(function() {
                hospitalVisitID.dispatchAction({
                    type: 'showTip',
                    seriesIndex: 0,
                    dataIndex: index
                });
                index++;
                if(index > hospitalLen.length) {
                    index = 0;
                }
            }, 5000)
            window.addEventListener("resize", function () {
                hospitalVisitID.resize();
            });

        }
    });
}