google.charts.load('current', {packages: ['corechart', 'bar']});

$("#DataForProducts").click(function () {
    $("#DataForProducts").hide();
    $.ajax({
        url: "Inventory",
        type: "POST",
        data: "{}",
        success: function (msg) {
            createDataTable(msg)
        },
        error: function(){
            console.log("error occurred while making ajax call;")
        }
    });
});

function createDataTable(jsonData) {
    var parsedData = $.parseJSON(jsonData);
    var data = new Array();
    var productTypeArr = new Array();
    var productNameArr = new Array();




    //Create an array of product name and an array of zipcodes
    for(var i=0; i<parsedData.length; i++) {
        var productName = parsedData[i]["productName"];
        var productType = parsedData[i]["productType"];
        if(!productTypeArr.includes(productType)) {
            productTypeArr.push(productType);
        }
        if(!productNameArr.includes(productName)){
            productNameArr.push(productName);
        }
    }

    //Create header array for google api
    // var headingArray = new Array(productNameArr.length+1);
    // headingArray[0] = "product Type";
    // for(var i=0;i<productNameArr.length;i++){
    //     headingArray[i+1] = productNameArr[i];
    // }
    // data[0] = headingArray;
    // var m =1;
    // for(var i=0; i<productTypeArr.length; i++) {
    //     var dataArr = new Array(headingArray.length);
    //     dataArr[0] = productTypeArr[i];
    //     for(var j=0; j<productNameArr.length; j++) {
    //         for (var k = 0; k < parsedData.length; k++) {
    //             if (parsedData[k]["productType"] == productTypeArr[i]&& parsedData[k]["productName"] === productNameArr[j]) {
    //                 dataArr[j+1] = parseInt(parsedData[k]["count"]);
    //             }
    //         }
    //      }
    //     for(var n=1; n<headingArray.length; n++) {
    //         if(!(dataArr[n] > 0)) {
    //             dataArr[n] = 0;
    //         }
    //     }
    //     data[m] = dataArr;
    //     m++;
    // }

    var headingArray = new Array(2);
    headingArray[0] = "product Name";
    headingArray[1] = "count";
    data[0] = headingArray;
    for (var k = 0; k < parsedData.length; k++){
        var dataArr = new Array(headingArray.length);
        dataArr[0] = parsedData[k]["productName"];
        dataArr[1] = parseInt(parsedData[k]["count"]);
        data[k+1] = dataArr;
    }

    drawChart(data,productNameArr);
}

function drawChart(data,productNameArr) {

    var productNames = "";
    for(var i=0; i<productNameArr.length; i++) {
        productNames += productNameArr[i] + ",";
    }

    //Invoke google's built in method to get data table object required by google.
    var chartData = google.visualization.arrayToDataTable(data);

    var options = {
        'width':600,
        'height':650,
        chart: {
            title: 'All Available Products Chart',
            subtitle: productNames,
        },
        bars: 'horizontal' // Required for Material Bar Charts.
    };

    var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
    chart.draw(chartData, options);
}