$(function() {
        var day=new Date();
            day.setTime(day.getTime());
        var month = day.getFullYear()+"-" + (day.getMonth()+1);
        laydate.render({
            elem: '#dateInput',
            type: 'month',
            value:month
        });
        initTable();
    });
function initTable() {
        table = $.createTable("#dataGrid", {
            idField: "id",
            columns: [
                [
                    { title: "辖区", field: "unitName" },
                    { title: "高血压患病数", field: "ev22004total" },
                    { title: "高血压管理数", field: "ev22005event" },
                    { title: "管理人群血压<br>控制率", field: "ev22004"},
                    { title: "高血压患者规范<br>管理率", field: "ev22005" },
                    { title: "糖尿病患病数", field: "ev22006total"},
                    { title: "糖尿病管理数", field: "ev22007event"},
                    { title: "管理人群血糖<br>控制率", field: "ev22006"},
                    { title: "糖尿病患者规范<br>管理率", field: "ev22007"}
                ]
            ],
            url: '/qos/analysis/data/get/unit',
            pagination: false,
            searchbar: '#searchbar',
            clickToSelect: true,
            onCheck: function(row) {
                console.log("on check");
            },
            onUncheck: function(row) {
                console.log("on uncheck");
            },
            queryParams: function(params) {
                params.eventIds = '22004,22005,22006,22007';
                params.ignoreUnitIds = '320583810343';
                requestParams = params;
                return params;
            },
            responseHandler: function(response) {
                var data = {};
                convertTableData('22004',response, data);
                convertTableData('22005',response, data);
                convertTableData('22006',response, data);
                convertTableData('22007',response, data);
                sumData = []
                for (var o in data) {
                    sumData.push(data[o]);
                }

                result = []
                for (var o in data) {
                    result.push(data[o]);
                }
                
                return sumData;
            }
        });
    }

function convertTableData(eventId,originData, data) {
    var eventData = originData[eventId],
        fieldName = 'ev' + eventId,
        total = 0,
        event = 0;

    eventData && eventData.forEach(function(item) {
        var id = item.unitId;
        unitData = data[id];
        if (!unitData) {
            unitData = {
                unitId: id,
                unitName: item.unitName
            };
            data[id] = unitData;
        }
        unitData[fieldName] = getRate(item);
        unitData[fieldName+'total'] = item.totalNum;
        unitData[fieldName+'event'] = item.eventNum;
        total += item.totalNum;
        event += item.eventNum;
    });

    var totalData = data['total'];
    if (!totalData) {
        totalData = {
            unitId: 'total',
            unitName: '合计',
        }
        data['total'] = totalData;
    }
    totalData[fieldName] = getRate({
        totalNum: total,
        eventNum: event
    });
    totalData[fieldName+'total']=total;
    totalData[fieldName+'event']=event;
}

function getRate(item, fixed) {
    var a = item.eventNum,
        b = item.totalNum,
        c = 0;
    if (a && b) {
        c = a / b * 100;
    }
    return c.toFixed(fixed || 2) + '%';
}