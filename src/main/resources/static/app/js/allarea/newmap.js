$(function(){

    var areas = ['巴城镇', '高新区', '周市镇', '张浦镇', '锦溪镇', '周庄镇', '开发区', '陆家镇', '千灯镇', '花桥镇', '淀山湖'],
        currentArea = '高新区',
        showCurrentCompany = false,
        currentCompany = {
            name: '',
            charge: '',
            chargePhone: '',
            location: '',
            locationX: '',
            locationY: '',
            color: ''
        },
        timeOut = null,
        interval = null;

    function showArea(area) {
        //   $(".K106_areaClick").hide();
        $("#XMLID_" + area).show();
    }
    function randomShow() {
        let index = Math.floor(Math.random() * 11)
        showArea(areas[index])
    }
    randomShow()
    function compare(property) {
        return function (a, b) {
            var value1 = a[property];
            var value2 = b[property];
            return value1 - value2;
        }
    }
    $.ajax({
        url: URLPATH + '/home/page/qos/getAddressCount',
        type: 'post',
        success: function (res) {
            var resResult = res.result;
            var newArray = []
            for (var i in resResult) {
                newArray.push({
                    address: i,
                    value: resResult[i]
                })
            }
            var obj = newArray
            var objs = obj.sort(compare('value'))
            var objAddress1 = objs[0].address
            var objAddress2 = objs[1].address
            var objAddress3 = objs[2].address
            var objAddress4 = objs[3].address
            var objAddress5 = objs[4].address
            var objAddress6 = objs[5].address
            var objAddress7 = objs[6].address
            var objAddress8 = objs[7].address
            var objAddress9 = objs[8].address
            var objAddress10 = objs[9].address
            var objAddress11 = objs[10].address
            // 周市
            $(".cityAddress").each(function (index, data) {
                if (objAddress1 == $(".cityAddress").eq(index).text()) {
                    $(".cityAddressPath").eq(index).addClass("blue1")
                }
            })
            $(".cityAddress").each(function (index, data) {
                if (objAddress2 == $(".cityAddress").eq(index).text()) {
                    $(".cityAddressPath").eq(index).addClass("blue2")
                }
            })
            $(".cityAddress").each(function (index, data) {
                if (objAddress3 == $(".cityAddress").eq(index).text()) {
                    $(".cityAddressPath").eq(index).addClass("blue3")
                }
            })
            $(".cityAddress").each(function (index, data) {
                if (objAddress4 == $(".cityAddress").eq(index).text()) {
                    $(".cityAddressPath").eq(index).addClass("blue4")
                }
            })
            $(".cityAddress").each(function (index, data) {
                if (objAddress5 == $(".cityAddress").eq(index).text()) {
                    $(".cityAddressPath").eq(index).addClass("blue5")
                }
            })
            $(".cityAddress").each(function (index, data) {
                if (objAddress6 == $(".cityAddress").eq(index).text()) {
                    $(".cityAddressPath").eq(index).addClass("blue6")
                }
            })
            $(".cityAddress").each(function (index, data) {
                if (objAddress7 == $(".cityAddress").eq(index).text()) {
                    $(".cityAddressPath").eq(index).addClass("blue7")
                }
            })
            $(".cityAddress").each(function (index, data) {
                if (objAddress8 == $(".cityAddress").eq(index).text()) {
                    $(".cityAddressPath").eq(index).addClass("blue8")
                }
            })
            $(".cityAddress").each(function (index, data) {
                if (objAddress9 == $(".cityAddress").eq(index).text()) {
                    $(".cityAddressPath").eq(index).addClass("blue9")
                }
            })
            $(".cityAddress").each(function (index, data) {
                if (objAddress10 == $(".cityAddress").eq(index).text()) {
                    $(".cityAddressPath").eq(index).addClass("blue10")
                }
            })
            $(".cityAddress").each(function (index, data) {
                if (objAddress11 == $(".cityAddress").eq(index).text()) {
                    $(".cityAddressPath").eq(index).addClass("blue11")
                }
            })
        }
    })
    var newRes = [];
    var currentIndex = 0;

    function showPopup() {
        $.ajax({
            type: "post",    //请求类型
            url: URLPATH+"/home/page/address/search/all",//请求的 URL地址
            success: function (rawData) {
                newRes = rawData.result;
             }
        })

        if (newRes && newRes.length > 0) {
            if (currentIndex == newRes.length) {
                currentIndex = 0;
            }

            var currentRes = newRes[currentIndex++];
            if (currentRes.orgType == 1) {
                var content = document.getElementById("mapRight");
                content.innerHTML = getFeatrueInfo(currentRes);
                var InfoOrgNameText = $("#InfoOrgName").text()
                for (var i = 0; i < $(".samePthanimateText").length; i++) {
                    if (InfoOrgNameText == $(".samePthanimateText").eq(i).text()) {
                        $(".samePthanimate").removeClass("yellow")
                        $(".samePthanimate").eq(i).addClass("yellow");
                    }
                }

            }

        }
    }
    /**
     * 动态创建popup的具体内容
     */
    function getFeatrueInfo(info) {
        if (info.orgType == 1) {
            var unitId = info.unitId
            var arr = {
                eventIds: '41001,41002,41003,41004,41005',
                unitId: info.unitId
            }
            $.ajax({
                type: "post",    //请求类型
                url: URLPATH+"/home/page/qos/data/get/unit",//请求的 URL地址
                data: arr,
                success: function (rawData) {
                    var res41001 = rawData.result['41001'];
                    var account41001;
                    for (var i = 0; i < res41001.length; i++) {
                        if (unitId == res41001[i].unitId) {
                            account41001 = res41001[i].count
                        }
                    }

                    var res41002 = rawData.result['41002'];
                    var account41002;
                    for (var i = 0; i < res41002.length; i++) {
                        if (unitId == res41002[i].unitId) {
                            account41002 = res41002[i].count
                        }
                    }
                    var res41003 = rawData.result['41003'];
                    var account41003;
                    for (var i = 0; i < res41003.length; i++) {
                        if (unitId == res41003[i].unitId) {
                            account41003 = res41001[i].count
                        }
                    }
                    var res41004 = rawData.result['41004'];
                    var account41004;
                    for (var i = 0; i < res41004.length; i++) {
                        if (unitId == res41004[i].unitId) {
                            account41004 = res41004[i].count
                        }
                    }
                    var res41005 = rawData.result['41005'];
                    var account41005;
                    for (var i = 0; i < res41005.length; i++) {
                        if (unitId == res41005[i].unitId) {
                            if(res41005[i].eventNum==0){
                                account41005="0"
                            } else {
                                account41005 = (res41005[i].totalNum / res41005[i].eventNum).toFixed(2)
                            }

                        }
                    }

                    $("#appointmentPeople").text(account41001)
                    $("#patientsPeople").text(account41002)
                    $("#inspectorsPeople").text(account41003)
                    $("#newinspectorsPeople").text(account41004)
                    $("#averageHospitalStay").text(account41005)
                }
            })

        }
        //  console.log(info.orgType)
        if (info.orgType == 1) {
            return "<div class='mt1vh'>" +
                "<p>" +
                "<div class='newlh3'><img src='../img/alldisplay/representative.png'/><span class='text-00ffff' id='InfoOrgName'>" + info.orgName + "</span></div>" +
                // "<div><img src='../img/alldisplay/address.png' class='mapaddress'/><span class='text-00ffff'>" + info.orgAddress + "</span></div>" +
                "<p  class='newlh3'>" + "<span class='fz14 text-ffffff'>预约人次数：</span>" +
                "<span class='fz14 text-ffffff' id='appointmentPeople'></span></p>" +
                "<p class='fz14 text-ffffff newlh3'>" + "<span class='fz14 ffffff'>诊疗人次数：</span>" +
                "<span class='text-ffffff' id='patientsPeople'></span></p>" +
                "<p class='newlh3'>" + "<span class='fz14 text-ffffff'>检查人次数：</span>" +
                "<span class='fz14 text-ffffff' id='inspectorsPeople'></span></p>" +
                "<p class='newlh3'>" + "<span class='fz14 text-ffffff'>检验人次数：</span>" +
                "<span class='fz14 text-ffffff' id='newinspectorsPeople'></span></p>" +
                "<p>" + "<span class='fz14 text-ffffff'>医院平均住院日：</span>" +
                "<span class='fz14 text-ffffff' id='averageHospitalStay'></span></p>" +
                "</div>";
        }
    }


  /*
    var i=0;
    function show() {

        i++;
        if(i<13){
            setInterval(function(){ console.log(i);},1000);
        }
    }
    show();*/


    setInterval(function () {
          showPopup();
     }, 10000);

})