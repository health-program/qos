$(function(){
    var color = ['#00ffff', '#0c81fe','#ff3a79']
    var incomeClassificationLeftEchartsOption =  {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        series: [{
            name: '库存情况',
            type: 'pie',
            radius: '68%',
            center: ['50%', '50%'],
            clockwise: false,
            data: [{
                value: 45,
                name: 'CARD'
            }, {
                value: 25,
                name: 'SSD'
            }, {
                value: 15,
                name: 'U盘'
            }],
            label: {
                normal: {
                    textStyle: {
                        color: '#999',
                        fontSize: 14,
                    }
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            itemStyle: {
                normal: {
                    borderWidth: 0,
                    borderColor: '#ffffff',
                },
                emphasis: {
                    borderWidth: 0,
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }],
        color:color,
    };



    var incomeClassificationLeftID = echarts.init(document.getElementById('incomeClassificationLeft'));
    incomeClassificationLeftID.setOption(incomeClassificationLeftEchartsOption);


    var index = 0; //播放所在下标
    var mTime = setInterval(function() {
        incomeClassificationLeftID.dispatchAction({
            type: 'showTip',
            seriesIndex: 0,
            dataIndex: index
        });
        index++;
        if(index > color.length) {
            index = 0;
        }
    }, 5000)
     window.addEventListener("resize", function () {
         incomeClassificationLeftID.resize();
    });
})