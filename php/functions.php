<?php

/* required external PHP files */
require_once('TradeInfo.php');


/* global scope variables */
$db = null;


/*
 * Close the currently open database connection.
 * 
 * @param array $db database connection
 * 
 * @return null
 */
function closeDatabaseConnection($db)
{
    pg_close($db);
} /* closeDatabaseConnection */


/*
 * Retrieves all data from the "Dividend" database table.
 * 
 * @param array $db database connection
 * 
 * @return array Array of dividend data
 */
function getDividendData($db)
{
	// select all dividends
    $dividends = [];

    // perform the query
    $result = pg_query($db, "SELECT * FROM dividends ORDER BY trade_date");

    // fetch the resultant records in associative array
    while ($row = pg_fetch_array($result)) {
        array_push($brokers, $row);
    }

    return $dividends;
}


/*
 * Retrieves all data from the "Trade" database table.
 * 
 * @param array $db database connection
 * 
 * @return array Array of all recorded stock market trades
 */
function getTradeData($db)
{
    $trades = [];

	/*
	 * SELECT statement using multiple left joins to collate all the needed
	 * data into a single SELECT statement that will greatly reduce the
	 * overhead of data manipulation and client/server round-trips.
	 */
	 $stmt = "SELECT T.id, T.trade_date, T.shares, T.price_per_share, T.pretax," .
					" T.broker_id, B.name as broker_name," .
					" T.portfolio_id, P.name as portfolio_name," .
					" T.sector_id, Sec.name as sector_name," .
					" T.symbol_id, Sym.symbol as symbol_symbol, Sym.name as symbol_name" .
				" FROM trade T" .
				" LEFT JOIN broker B on T.broker_id = B.id" .
				" LEFT JOIN portfolio P on T.portfolio_id = P.id" .
				" LEFT JOIN sector Sec on T.sector_id = Sec.id" .
				" LEFT JOIN symbol Sym on T.symbol_id = Sym.id";
	 $result = pg_query($stmt);
 
    // fetch the resultant records in associative array
    while ($row = pg_fetch_array($result)) {
        array_push($trades, $row);
    }

	return $trades;
}


/*
 * Loops through an array of arrays and searches for the given item. If any
 * of the arrays contains the item, return true. If none of the arrays contains
 * the item, return false.
 * 
 * @param array $array_of_arrays Input arrays of arrays to search
 * @param string $item Item to search for
 * 
 * @return boolean Return true if any of the arrays contains the item
 */
function inArrayOfArrays($array_of_arrays, $item)
{
	foreach ($array_of_arrays as $a) {
		if (in_array($item, $a) === true) {
			return true;
		}
	}

	return false;
}


/*
 * Inserts a new broker into the "Broker" database table.
 * 
 * @param array $db database connection
 * @param string $name name of the broker being inserted
 * 
 * @return mixed Result of pg_insert
 */
function putBroker($db, $name)
{
	$values = array();
	$values['name'] = $name;
	
	$result = pg_insert($db, "broker", $values);
	return $result;
}


/*
 * Inserts a new portfolio into the "Portfolio" database table.
 * 
 * @param array $db database connection
 * @param string $name name of the portfolio being inserted
 * 
 * @return mixed Result of pg_insert
 */
function putPortfolio($db, $name)
{
	$values = array();
	$values['name'] = $name;
	
	$result = pg_insert($db, "portfolio", $values);
	return $result;
}


/*
 * Inserts a new sector into the "Sector" database table.
 * 
 * @param array $db database connection
 * @param string $name name of the sector being inserted
 * 
 * @return mixed Result of pg_insert
 */
function putSector($db, $name)
{
	$values = array();
	$values['name'] = $name;
	
	$result = pg_insert($db, "sector", $values);
	return $result;
}


/*
 * Inserts a new symbol into the "Symbol" database table.
 * 
 * @param array $db database connection
 * @param string $symbol symbol letters of the symbol being inserted
 * @param string $name name of the symbol being inserted
 * 
 * @return mixed Result of pg_insert
 */
function putSymbol($db, $symbol, $name)
{
	$values = [
		'symbol' => $symbol,
		'name' => $name,
	];

	$result = pg_insert($db, 'symbol', $values);
	return $result;
}


/**
 * 
 */
function putTrade($db, $trade_date, $shares, $price_per_share, $pretax,
					$broker_id, $sector_id, $symbol_id, $portfolio_id)
{
	$values = [
		'trade_date' => $trade_date,
		'shares' => $shares,
		'price_per_share' => $price_per_share,
		'pretax' => $pretax,
		'broker_id' => $broker_id,
		'sector_id' => $sector_id,
		'symbol_id' => $symbol_id,
		'portfolio_id' => $portfolio_id,
	];
	
	$result = pg_insert($db, 'trade', $values);
	return $result;
}


/*
 * Open a new database connection based on the server, user name, password, and
 * database named found in the /msp_private.php file. This file is housed
 * outside of the range of files accessible directly from a browser to help
 * lock down this information from prying eyes.
 * 
 * @return array Returns the array object describing the opened database connection
 */
function openDatabaseConnection()
{
    /* private data stored outside the web server's visibility */
	require('/msp_private.php');

	global $db;

//	$db = new MysqliDb($MSP_ServerName, $MSP_UserName, $MSP_Password, $MSP_DatabaseName);
    
    $conn_string = "host=" . $MSP_ServerName;
    $conn_string .= " dbname=" . $MSP_DatabaseName;
    $conn_string .= " user=" . $MSP_UserName;
    $conn_string .= " password=" . $MSP_Password;
    $db = pg_connect($conn_string);

	return $db;
} /* openDatabaseConnection */


/*
 * parseTradeInfo
 * 
 * Parses the data returned from the database into a more manageable array of
 * class objects (instead of the array of associative arrays).
 * 
 * @param array $TradeDataFromDatabase The trade info pulled from database comes back in an array of associative arrays which takes more to muddle through than an array of class objects.
 * @param array $brokers
 * @param array $portfolios
 * @param array $sectors
 * @param array $symbols
 * 
 * @return TradeInfo[] Array of TradeInfo objects representing collated trade data, broker names, portfolio names, sector name, and symbol names/codes
 */
function parseTradeData($TradeDataFromDatabase, $brokers, $portfolios, $sectors, $symbols)
{
	$TradeInfo = [];
    $Index = 0;

    // loop through each trade and determine if it should be merged with
    // an existing TradeInfo object or if a new object needs to be created
	foreach ($TradeDataFromDatabase as $trade)
	{
        $Index = count($TradeInfo);
        $Merge = false;
        
        // determine the TradeInfo index of the object being operated on
        $TradeInfoIndex = 0;
        foreach ($TradeInfo as $ti) {
            if ($trade['symbol_id'] == $ti->getSymbolId()) {
                $Index = $TradeInfoIndex;
                $Merge = true;
                break;
            }

            ++$TradeInfoIndex;
        }

        if ($Merge == false) {
            $TradeInfo[$Index] = new TradeInfo();

            // get symbol and name of current 'trade'
            $TradeInfo[$Index]->setSymbolId($trade['symbol_id']);
            foreach ($symbols as $symbol)
            {
                if ($trade['symbol_id'] == $symbol['id'])
                {
                    $TradeInfo[$Index]->setSymbolSymbol($symbol['symbol']);
                    $TradeInfo[$Index]->setSymbolName($symbol['name']);
                    break;
                }
            }

            // get the sector name of the current trade
            $TradeInfo[$Index]->setSectorId($trade['sector_id']);
            foreach ($sectors as $sector)
            {
                if ($trade['sector_id'] == $sector['id'])
                {
                    $TradeInfo[$Index]->setSectorName($sector['name']);
                    break;
                }
            }
        }


        // get the broker name of the current trade
		foreach ($brokers as $broker)
		{
			if ($trade['broker_id'] == $broker['id'])
			{
				$broker = $broker['name'];
				break;
			}
		}


        // get the portfolio name of the current trade
		foreach ($portfolios as $portfolio)
		{
			if ($trade['portfolio_id'] == $portfolio['id'])
			{
				$portfolio = $portfolio['name'];
				break;
			}
		}

        $TradeInfo[$Index]->setTransaction(
            $trade['id'],
            $trade['trade_date'],
            $trade['shares'],
            $trade['price_per_share'],
            ($trade['pretax'] == 't') ? true : false,
            $broker,
            $portfolio
        );
	}

    // sort the data so it appears in alphabetical order by symbol
    usort($TradeInfo, function($a, $b) {
        return strcmp($a->getSymbolSymbol(), $b->getSymbolSymbol());
    });

    //echo "<pre>", var_dump($TradeInfo), "</pre>";
    return $TradeInfo;
} /* parseTradeData */


?>
