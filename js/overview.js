/*
 * reset the form
 */
function ResetForm() {
  $('#newTradeDate').val('');
  $('#newTradeSymbolInput').val('');
  $('#newTradeSectorInput').val('');
  $('#newTradeBrokerInput').val('Robinhood');
  $('#newTradePortfolioInput').val('Robinhood');
  $('#newTradeShares').val('');
  $('#newTradePricePerShare').val('');
  $('#newTradePreTax').attr('checked', false);
}

$('#newTradeCancel').click(function(e) {
  e.preventDefault();     // don't scroll to the top of the page
  ResetForm();
});


/*
 * Add a class to the given object. If the given value is > 0.00
 * the color will be green, otherwise it will be red.
 */
function setCurrencyTextColor(value, object, colorClass) {
  if (parseFloat(value) > parseFloat(0.00)) {
    object.addClass(colorClass[0]);
  } else {
    object.addClass(colorClass[1]);
  }
} /* setCurrencyTextColor */


let brokers = [];
let portfolios = [];
let sectors = [];
let symbols = [];
let trades = [];

let tradeData = [];

let allTodaysReturn = 0.00;
let allTotalReturn = 0.00;
let allEquityValue = 0.00;

// get broker data
$.ajax({
  url: window.location.protocol + '//' + window.location.host + '/demo/msp-htmlcss/brokers.php',
  async: false
}).then(function(data) {
  brokers = JSON.parse(data);
});

// get portfolio data
$.ajax({
  url: window.location.protocol + '//' + window.location.host + '/demo/msp-htmlcss/portfolios.php',
  async: false
}).then(function(data) {
  portfolios = JSON.parse(data);
});

// get sector data
$.ajax({
  url: window.location.protocol + '//' + window.location.host + '/demo/msp-htmlcss/sectors.php',
  async: false
}).then(function(data) {
  sectors = JSON.parse(data);
});

// get symbol data
$.ajax({
  url: window.location.protocol + '//' + window.location.host + '/demo/msp-htmlcss/symbols.php',
  async: false
}).then(function(data) {
  symbols = JSON.parse(data);
});

// get trade data
$.ajax({
  url: window.location.protocol + '//' + window.location.host + '/demo/msp-htmlcss/trades.php',
  async: false
}).then(function(data) {
  trades = JSON.parse(data);
});

//console.log(brokers);
//console.log(portfolios);
//console.log(sectors);
//console.log(symbols);
//console.log(trades);




// loop over all trades, reshape data to make it easier to merge records into single cards
$.each(trades, function(index, trade) {
  let obj = {};

  // determine if the current trade's symbol matches an existing one in tradeData
  for (let i = 0; i < tradeData.length; i++) {
    if (trade.symbol_id === tradeData[i].symbol_id) {
      obj = tradeData[i];
      break;
    }
  }

  if (!obj.hasOwnProperty('symbol')) {
    // match symbol_id in trades to id in symbols
    let symbol = symbols.filter(s => s.id === trade.symbol_id)[0];
    obj.symbol_id = symbol.id;
    obj.symbol = symbol.symbol;
    obj.symbol_name = symbol.name;

    // match portfolio_id in trades to id in portfolios
    let portfolio = portfolios.filter(p => p.id === trade.portfolio_id)[0];
    obj.portfolio = portfolio.name;

    // match sector_id in trades to id in sectors
    let sector = sectors.filter(s => s.id === trade.sector_id)[0];
    obj.sector = sector.name;
  }

  // match broker_id in trades to id in brokers
  let broker = brokers.filter(b => b.id === trade.broker_id)[0];

  // initialize and poopulate the transaction array if it hasn't been done previously
  if (!obj.hasOwnProperty('transaction')) {
    obj.transaction = [];
    tradeData.push(obj);
  }

  // create new object specific to a single transaction/trade
  // this will get appended to an array of transactions/trades per symbol
  let trans = {
    broker: broker.name,
    pretax: (trade.pretax === 't') ? true : false,
    pricePerShare: trade.price_per_share,
    shares: trade.shares,
    tradeDate: trade.trade_date
  };

  tradeData[tradeData.length - 1].transaction.push(trans);
});

// sort the tradeData array by the symbol
tradeData = tradeData.sort(function(a, b) {
  let x = a.symbol.toUpperCase();
  let y = b.symbol.toUpperCase();

  if (x < y) {
    return -1
  } else if (x > y) {
    return 1;
  }

  return 0;
});

// loop across all tradeData entries and create cards
$.each(tradeData, function(index, data) {
  let html = '<div class="card [ is-collapsed ]" id="' + data.symbol + '">';

  html += `
    <div class="card--upper [ js-expander ]">
      <div class="symbol">`;

  html += data.symbol;

  html += `</div>
      <div class="latest-price bold justify-right">$0.00</div>
      <div class="shares">`;

  let shares = 0;
  for (let i = 0; i < data.transaction.length; i++) {
    shares += parseFloat(data.transaction[i].shares);
  }

  html += shares.toFixed(4);

  html += ` Shares</div>
        <div class="todays-change bold justify-right">$0.00</div>
    </div> <!-- .card--upper -->
    <div class="card--expander [ js-collapser ]">
      <hr />
      <div class="card--expander--info--container">
        <span class="name">`;

  html += data.symbol_name;

  html += `</span>
            <span class="equity-value-label">Equity Value</span>
            <span class="equity-value justify-right">$0.00</span>
            <span class="total-return-label">Total Return</span>
            <span class="total-return justify-right">$0.00</span>
            <span class="todays-return-label">Today's Return</span>
            <span class="todays-return justify-right">$0.00</span>
            <div class="chart">`;

  html += '<canvas id="' + data.symbol + '-chart"></canvas>';

  html += `</div>
        </div>
    </div> <!-- .card--expanding -->
</div> <!-- .card -->`;

  let current = $('#card-container').html();
  current += html;
  $('#card-container').html(current);
});



//let today = new Date();
//let dd = today.getDate();
//let mm = today.getMonth() + 1; // January is 0!
//
//let yyyy = today.getFullYear();
//if (dd < 10) { dd = '0' + dd }
//if (mm < 10) { mm = '0' + mm }
//today = mm + '-' + dd + '-' + yyyy;
//
//$('#newTradeDate').val(today);



// get a list of all symbols for select list
$("#newTradeSymbolDataList").empty();
$.each(symbols, function(index, data) {
  let opt = $("<option>" + data.name + "</option>").attr('value', data.symbol);
  $("#newTradeSymbolDataList").append(opt);
});


// get a list of all sectors for select list
$("#newTradeSectorDataList").empty();
$.each(sectors, function(index, data) {
  let opt = $("<option>" + data.name + "</option>").attr('value', data.sector);
  $("#newTradeSectorDataList").append(opt);
});


// get a list of all portfolios for select list
$("#newTradePortfolioDataList").empty();
$.each(portfolios, function(index, data) {
  let opt = $("<option></option>").attr("value", data.name);
  $("#newTradePortfolioDataList").append(opt);
});
$('#newTradePortfolioInput').attr('value', 'Robinhood');


// get a list of all brokers for select list
$("#newTradeBrokerDataList").empty();
$.each(brokers, function(index, data) {
  let opt = $("<option></option>").attr('value', data.name);
  $("#newTradeBrokerDataList").append(opt);
});
$('#newTradeBrokerInput').attr('value', 'Robinhood');



/*
 * retrieve stock data from IEX API service
 */

let baseUrl = 'https://api.iextrading.com/1.0/stock/market/batch';
let baseParameters = '?displayPercent=true&types=quote,chart&range=1d';

// get a list of all symbols separated by commas
symbols = [];
$.each(tradeData, function(index, data) {
  symbols.push(data.symbol);
});
let symbolList = symbols.join(',');


//
// create the promise for retreiving stock and chart data and execute
// on the results
//
$.ajax({
  url: baseUrl + baseParameters + '&symbols=' + symbolList
}).then(function(data) {
  $.each(data, function(index, d) {
    let symbol = d.quote.symbol;
    let latestPrice = d.quote.latestPrice;
    let change = d.quote.change;
    let changePercent = d.quote.changePercent;
    let chartData = [];
    let labels = [];
    let chartInterval = 12;

//  console.log(symbol);
//  console.log(d.chart);

    // filter out chart data that has marketAverage property equal to 0
    let chartDataFiltered = d.chart.filter((el) => (el.marketAverage !== 0));
//    console.log(chartDataFiltered.length);

    // chartInterval determines the number of data points for the chart
    if (chartDataFiltered.length < 20) {
      chartInterval = 1;
    } else if (chartDataFiltered.length < 120) {
      chartInterval = parseInt(chartDataFiltered.length / 12);
    }

//    console.log(chartInterval);

    // keep only 5-minute interval chart data
    $.each(chartDataFiltered, function(index, d) {
      if (index === 0 || index === (chartDataFiltered.length - 1)) {
//        console.log(index);
        chartData.push(d.marketAverage);
        labels.push(d.minute);
      } else {
        if (index % chartInterval === 0) {
          // don't keep the data point if it isn't valid
          if (d.marketAverage != 0) {
            chartData.push(d.marketAverage);

            // keep a label with the minute the data point came from
            labels.push(d.minute);
          }
        }
      }
    });

//    console.log(chartData);
//    console.log(labels);

    // update latestPrice
    $('#' + symbol).find('div.latest-price').text('$' + latestPrice.toFixed(2));

    // update todays-change
    setCurrencyTextColor( Math.round(change * 100),
                          $('#' + symbol).find('div.todays-change'),
                          ["text-green", "text-red"] );
    $('#' + symbol).find('div.todays-change')
                   .text('$' + Math.abs(change).toFixed(2) + ' (' + Math.abs(changePercent).toFixed(2) + '%)');

    // update expanding data
    $.each(tradeData, function(index, trade) {
      if (trade.symbol === symbol) {
        let shares = 0;
        let purchasePrice = 0.00;
        for (let i = 0; i < trade.transaction.length; i++) {
          shares += parseFloat(trade.transaction[i].shares);
          purchasePrice += parseFloat(trade.transaction[i].pricePerShare) * parseFloat(trade.transaction[i].shares);
        }

        // update equity value
        let equityValue = (shares * parseFloat(latestPrice)).toFixed(2);
        allEquityValue += parseFloat(equityValue);
        $('#' + symbol).find('span.equity-value').text('$' + equityValue);

        // update total return
        let totalReturn = (equityValue - purchasePrice).toFixed(2);
        allTotalReturn += parseFloat(totalReturn);
        setCurrencyTextColor(totalReturn, $('#' + symbol).find('span.total-return'), ["text-green", "text-red"]);
        $('#' + symbol).find('span.total-return').text('$' + Math.abs(totalReturn).toFixed(2));

        // update today's return
        let todaysReturn = (shares * parseFloat(change)).toFixed(2);
        allTodaysReturn += parseFloat(todaysReturn);
        setCurrencyTextColor(todaysReturn, $('#' + symbol).find('span.todays-return'), ["text-green", "text-red"]);
        $('#' + symbol).find('span.todays-return').text('$' + Math.abs(todaysReturn).toFixed(2));
      }
    });

    // update the chart
    let ctx = $('#' + symbol + '-chart')[0].getContext('2d');
    let myChart = new Chart(ctx, {
      type: 'line',
      data: {
        labels: labels,
        datasets: [{
          label: 'Price Per Share',
          data: chartData,
          backgroundColor: [
            'rgba(79, 195, 247, 0.2)'   // light-blue-300
          ],
          borderColor: [
            'rgba(3, 169, 244, 1)'      // light-blue-500
          ],
          borderWidth: 1
        }]
      },
      options: {
        legend: {
          display: false
        },
        tooltips: {
          enabled: true
        },
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: false
            }
          }]
        }
      }
    });


    // update the card's background color to indicate it is "ready"
    $('#' + symbol).css("background-color", "#E0E0E0");
  });

  // update globals
  $('#allEquityValue').text('$' + allEquityValue.toFixed(2));

  setCurrencyTextColor(allTotalReturn, $('#allTotalReturn'), ["text-light-green", "text-light-red"]);
  $('#allTotalReturn').text('$' + Math.abs(allTotalReturn).toFixed(2));

  setCurrencyTextColor(allTodaysReturn, $('#allTodaysReturn'), ["text-light-green", "text-light-red"]);
  $('#allTodaysReturn').text('$' + Math.abs(allTodaysReturn).toFixed(2));

  if (parseFloat(allTodaysReturn) > parseFloat(0.00)) {
    $('#st-trigger-effects').addClass('bg-green');
  } else {
    $('#st-trigger-effects').addClass('bg-red');
  }


  // change the display of each card to indicate the data has
  // finished loading into the cards
  $('.progress').addClass("hide");
});
