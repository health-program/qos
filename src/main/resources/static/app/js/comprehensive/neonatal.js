var arr13321Month=[];
var arr13321MonthY=[];
var ev13203DataArray=[];
String.prototype.endWith = function(str) {
    var reg = new RegExp(str + "$");
    return reg.test(this);
}

$(function(){
    //支付方式对比
    var arr = {
        //  儿童访视数，儿童建卡数
        eventIds:'13321,13203',
        ignoreUnitIds:'320583810343'

    }
    //从大到小排序
    function compareBigToSmall(property){
        return function(a,b){
            var value1 = a[property];
            var value2 = b[property];
            return  value2 - value1;
        }
    };

    $.ajax({
        type : "post",    //请求类型
        url : URLPATH+"/home/page/qos/data/display/unit",//请求的 URL地址
        data:arr,
        success: function (rawData) {
            for(var i=0;i<rawData.result['13321'].length;i++){
                if(rawData.result['13321'][i].unitName.endWith('服务中心')){
                    arr13321Month.push(rawData.result['13321'][i].unitName)
                    arr13321MonthY.push(rawData.result['13321'][i].count)
                }
            }

            for(var i=0;i<rawData.result['13203'].length;i++){
                if(rawData.result['13203'][i].unitName.endWith('服务中心')){
                    ev13203DataArray.push(rawData.result['13203'][i].count)
                }
            }

            let a=arr13321MonthY, b=ev13203DataArray;
            let newarr13002MonthY = a.map(function(v, i) {
                if(b[i]==0){
                    return '0';
                }else{
                    return (v*100/b[i]).toFixed(2);
                }

            });

            var neontalOption = {
                  tooltip: {
                    trigger: 'axis',
                    formatter: function(params, ticket, callback) {
                        var html = params[0].axisValueLabel + '：';
                        for (var i = 0; i < params.length; i++) {
                            var param = params[i];
                            html += '<br/>' + param.marker + param.seriesName + '：' + param.value + '%';
                        }
                        return html;
                    },
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                grid: {
                    top: '5%',
                    right: '3%',
                    left: '10%',
                    bottom: '12%'
                },
                xAxis: {
                    type: 'category',
                    data: arr13321Month,
                    axisLabel: {
                        interval: 0,
                        rotate: 10, //角度顺时针计算的
                        formatter: function(value) {
                            var reg = new RegExp('社区卫生服务中心', "g");
                            return value.replace(reg, '');
                        },
                        color: '#e2e9ff',
                        textStyle: {
                            fontSize: 12
                        },
                    },
                    axisLine: {
                        lineStyle: {
                            color: 'rgba(255,255,255,0.12)'
                        }
                    },

                },
                yAxis: {
                    type: 'value',
                    splitLine:{
                        show:false
                    },
                    axisLine:{
                        lineStyle:{
                            color:'#19d1ff',
                            width:1,//这里是为了突出显示加上的
                        }
                    },
                    axisLabel: {
                        formatter: function(val) {
                            return val + '%';
                        }
                    }
                },
                series: [{
                    barWidth: '10px',
                    itemStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset: 0,
                                color: 'rgba(0,244,255,1)' // 0% 处的颜色
                            }, {
                                offset: 1,
                                color: 'rgba(0,77,167,1)' // 100% 处的颜色
                            }], false),
                            barBorderRadius: [30, 30, 30, 30],
                            shadowColor: 'rgba(0,160,221,1)',
                            shadowBlur: 4,
                        }
                    },
                    name: '新生儿访视率',
                    data: newarr13002MonthY,
                    type: 'bar'
                }]
            };





            //初始化echarts实例


            var neontalID = echarts.init(document.getElementById('neonatal'));
            neontalID.setOption(neontalOption);


            var index = 0; //播放所在下标
            var mTime = setInterval(function() {
                neontalID.dispatchAction({
                    type: 'showTip',
                    seriesIndex: 0,
                    dataIndex: index
                });
                index++;
                if(index > arr13321Month.length) {
                    index = 0;
                }
            }, 5000)
            window.addEventListener("resize", function () {
                neontalID.resize();
            });
       }

    });
})