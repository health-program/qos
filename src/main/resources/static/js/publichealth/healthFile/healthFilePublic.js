function convertUnitChartData(data, eventId, isRate) {
        var edata = data[eventId],
            max = 0,
            unit = [],
            values = [];
        edata && edata.forEach(function(item) {
            var r = isRate ? getPublicRate(item) : item.count;
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



function getPublicRate(row) {
    var e = row.publicArchivesNumber,
        t = row.activeArchivesNumber;
    if (t == 0) return 0;
    var x = e * 100 / t;
    if (x < 0.005) {
        return 0;
    }
    return x.toFixed(2) * 1;
}

$(function(){
	
	function compareBigToSmall(property){
        return function(a,b){
            var value1 = a[property];
            var value2 = b[property];
            return  value2 - value1;
        }
    };
	
	
	$.ajax({
		type : "post",    //请求类型
        url : "/qos/gongwei/archives/search/all",//请求的 URL地址
        async: false,
        success: function (rawData){
        	console.log(rawData);
        	var ev22002 = rawData.result;
        	var ev22002Sort = rawData.result.sort(compareBigToSmall('publicArchivesNumber'));
        	var ev22002DataArray = ev22002Sort.slice(0,5);
        	
        	var newast22002 = {}
            newast22002['22002']=ev22002DataArray
            var unit22002DatanewArrayunit22002DatanewArray=convertUnitChartData(newast22002, '22002', true);
        	
        	
        	var medicaladviceoption1 = {
        			
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
    	            
            		  xAxis: {
            		        type: 'category',
            		        data: unit22002DatanewArrayunit22002DatanewArray.unit,
            		        axisLabel: {
      	                    interval: 0,
      	                    rotate: 25 //角度顺时针计算的
      	                },
            		        axisLine:{
                                lineStyle:{
                                    color:'#19d1ff',
                                    width:1,//这里是为了突出显示加上的
                                }
                            }
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
            		    	  itemStyle: {
            		              normal: {
            		                  // 随机显示
            		                   color:function(d){return "#"+Math.floor(Math.random()*(256*256*256-1)).toString(16);}
            		                
            		              },
            		          },
            		          barWidth : 20,
            		          name: '健康档案公开率',
            		        data: unit22002DatanewArrayunit22002DatanewArray.values,
            		        type: 'bar'
            		    }]
            		};
        	
        	//初始化echarts实例
            var medicalAdvice_id = echarts.init(document.getElementById('emergency1'));
            medicalAdvice_id.setOption(medicaladviceoption1);
        }
	})
})

/*$(function(){
    //支付方式对比
         var arr = {
         //  建档率，公开率（使用率==建档率），
              eventIds:'22002,22002'

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
         url : "/qos/analysis/data/get/unit",//请求的 URL地址
         async: false,
         data:arr,
         success: function (rawData) {
         console.log(111);
         var  unit22002Data=convertUnitChartData(rawData.result, '22002', true); //
         var ev22002 = convertUnitChartData(rawData.result, '22002', true);
         var ev22002Sort = rawData.result['22002'].sort(compareBigToSmall('count'));
         var ev22002DataArray = ev22002Sort.slice(0,5);

        var newast22002 = {}
        newast22002['22002']=ev22002DataArray
        var unit22002DatanewArrayunit22002DatanewArray=convertUnitChartData(newast22002, '22002', true);




         //社区建档率
        var medicaladviceoption1 = {
      		  xAxis: {
      		        type: 'category',
      		        data: unit22002DatanewArrayunit22002DatanewArray.unit,
      		        axisLabel: {
	                    interval: 0,
	                    rotate: 20 //角度顺时针计算的
	                },
      		        axisLine:{
                          lineStyle:{
                              color:'#19d1ff',
                              width:1,//这里是为了突出显示加上的
                          }
                      }
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
                      }
      		    },
      		    series: [{
      		    	  itemStyle: {
      		              normal: {
      		                  // 随机显示
      		                   color:function(d){return "#"+Math.floor(Math.random()*(256*256*256-1)).toString(16);}
      		                
      		              },
      		          },
      		          barWidth : 30,
      		        data: [0.1,0.2,0.3,0.4,0.5],
      		        type: 'bar'
      		    }]
      		};



        

                //初始化echarts实例
                    var medicalAdvice_id = echarts.init(document.getElementById('emergency'));
                    medicalAdvice_id.setOption(medicaladviceoption1);
                   

         }
       
    });

})*/