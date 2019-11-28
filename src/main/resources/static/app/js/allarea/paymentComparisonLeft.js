$(function(){
    var color = ['#00ffff', '#0c81fe']
    var paymentComparisonLeftEchartsOption = {
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
            }],
            label: {
                normal: {
                    textStyle: {
                        color: '#ffffff',
                        fontSize: 14,
                    }
              },
            },
            itemStyle: {
                normal: {
                    borderWidth: 4,
                },
                emphasis: {
                    borderWidth: 0,
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }],
        color: color
    };



    var paymentComparisonLeftID = echarts.init(document.getElementById('paymentComparisonLeft'));
    paymentComparisonLeftID.setOption(paymentComparisonLeftEchartsOption);


    var index = 0; //播放所在下标
    var mTime = setInterval(function() {
        paymentComparisonLeftID.dispatchAction({
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
         paymentComparisonLeftID.resize();
    });
})