google.charts.load('current', {packages: ['corechart', 'bar']});

$("#DataForSalesReport").click(function () {
    $("#DataForSalesReport").hide();
    $.ajax({
        url: "SalesReport",
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
    var productNameArr = new Array();

    for(var i=0; i<parsedData.length; i++){
        var productName = parsedData[i]["productName"];
        if(!productNameArr.includes(productName)){
            productNameArr.push(productName);
        }
    }

    var headingArray = new Array(2);
    headingArray[0] = "Product Name";
    headingArray[1] = "total sales";
    data[0] = headingArray;

    for(var k=0;k<parsedData.length;k++){
        var dataArr = new Array(headingArray.length);
        dataArr[0] = parsedData[k]["productName"];
        dataArr[1] = parseInt(parsedData[k]["totalSale"]);
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
            title: 'All Sales Report Chart',
            subtitle: productNames,
        },
        bars: 'horizontal' // Required for Material Bar Charts.
    };

    var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
    chart.draw(chartData, options);
}