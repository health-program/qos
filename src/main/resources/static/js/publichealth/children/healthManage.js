         var arr13203Month=[];
         var arr13203MonthY=[];
         var ev13209DataArray=[];
         
         String.prototype.endWith = function(str) {  
        	    var reg = new RegExp(str + "$");  
        	    return reg.test(this);  
         }

$(function(){
    //支付方式对比
         var arr = {
         //  儿童体检数，儿童建卡数
              eventIds:'13209,13203',
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
         url : "/qos/analysis/data/get/unit",//请求的 URL地址
         data:arr,
         success: function (rawData) {
         console.log(111);
         
        
         for(var i=0;i<rawData.result['13203'].length;i++){
        	 
        	 if(rawData.result['13203'][i].unitName.endWith('服务中心')){
        		 
        		 arr13203Month.push(rawData.result['13203'][i].unitName)
                 arr13203MonthY.push(rawData.result['13203'][i].count)
        	 }
          }
         
         for(var i=0;i<rawData.result['13209'].length;i++){
        	 
                if(rawData.result['13209'][i].unitName.endWith('服务中心')){
                	ev13209DataArray.push(rawData.result['13209'][i].count)
        	 }
          }
         
         
         
         
         let a=arr13203MonthY, b=ev13209DataArray;
         let newarr13002MonthY = a.map(function(v, i) {
        	 if(b[i]==0){
        		 return '0';
        	 }else{
        		 return (v*100/b[i]).toFixed(2);
        	 }
             
         });
         



        var medicaladviceoption2children = {

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
      		        data: arr13203Month,
      		                    		        axisLabel: {
                                                 interval: 0,
                                                 rotate: 0, //角度顺时针计算的
                                                 formatter: function(value) {
                                                      var reg = new RegExp('社区卫生服务中心', "g");
                                                        return value.replace(reg, '');
                                                 }
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
      		          barWidth : 30,
      		        name: '儿童健康管理率',
      		        data: newarr13002MonthY,
      		        type: 'bar'
      		    }]
      		};



        

                //初始化echarts实例
                    var medicalAdvice_id_children = echarts.init(document.getElementById('emergency1'));
                    medicalAdvice_id_children.setOption(medicaladviceoption2children);

         }
       
    });

})