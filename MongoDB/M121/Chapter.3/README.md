# Problem 3.1

## $group and accumulators

In the last lab, we calculated a normalized rating that required us to know what the minimum and maximum values for imdb.votes were. These values were found using the $group stage!

For all films that won at least 1 Oscar, calculate the standard deviation, highest, lowest, and average imdb.rating. Use the sample standard deviation expression.

HINT - All movies in the collection that won an Oscar begin with a string resembling one of the following in their awards field

    Won 13 Oscars
    Won 1 Oscar
	
Select the correct answer from the choices below. Numbers are truncated to 4 decimal places.

Choose the best answer:
 - { "highest_rating" : 9.2, "lowest_rating" : 4.5, "average_rating" : 7.5270, "deviation" : 0.5984 }
 - { "highest_rating" : 9.5, "lowest_rating" : 5.9, "average_rating" : 7.5290, "deviation" : 0.5988 }
 - { "highest_rating" : 9.8, "lowest_rating" : 6.5, "average_rating" : 7.5270, "deviation" : 0.5988 }
 - { "highest_rating" : 9.2, "lowest_rating" : 4.5, "average_rating" : 7.5270, "deviation" : 0.5988 }

<details>
  <summary>Click here for the solution</summary>
    <ul>
      <li>{ "highest_rating" : 9.2, "lowest_rating" : 4.5, "average_rating" : 7.5270, "deviation" : 0.5988 }</li>
    </ul>
</details>


# Problem 3.2

## $unwind

Let's use our increasing knowledge of the Aggregation Framework to explore our movies collection in more detail. We'd like to calculate how many movies every cast member has been in and get an average imdb.rating for each cast member.

What is the name, number of movies, and average rating (truncated to one decimal) for the cast member that has been in the most number of movies with English as an available language?

Provide the input in the following order and format

    { "_id": "First Last", "numFilms": 1, "average": 1.1 }

<details>
  <summary>Click here for the solution</summary>
    <ul>
      <li>{ "_id" : "John Wayne", "numFilms" : 107, "average" : 6.4 }</li>
    </ul>
</details>


# Problem 3.3

## Using $lookup

Which alliance from air_alliances flies the most routes with either a Boeing 747 or an Airbus A380 (abbreviated 747 and 380 in air_routes)?

Choose the best answer:
 - "SkyTeam"
 - "Star Alliance"
 - "OneWorld"

<details>
  <summary>Click here for the solution</summary>
    <ul>
      <li>"SkyTeam"</li>
    </ul>
</details>


# Problem 3.4

## $graphLookup

Now that you have been introduced to $graphLookup, let's use it to solve an interesting need. You are working for a travel agency and would like to find routes for a client! For this exercise, we'll be using the air_airlines, air_alliances, and air_routes collections in the aggregations database.

 - The air_airlines collection will use the following schema:
 
        {
            "_id" : ObjectId("56e9b497732b6122f8790280"),
            "airline" : 4,
            "name" : "2 Sqn No 1 Elementary Flying Training School",
            "alias" : "",
            "iata" : "WYT",
            "icao" : "",
            "active" : "N",
            "country" : "United Kingdom",
            "base" : "HGH"
        }
	
 - The air_routes collection will use this schema:
 
        {
            "_id" : ObjectId("56e9b39b732b6122f877fa31"),
            "airline" : {
                    "id" : 410,
                    "name" : "Aerocondor",
                    "alias" : "2B",
                    "iata" : "ARD"
            },
            "src_airport" : "CEK",
            "dst_airport" : "KZN",
            "codeshare" : "",
            "stops" : 0,
            "airplane" : "CR2"
        }
	
 - Finally, the air_alliances collection will show the airlines that are in each alliance, with this schema:

        {
            "_id" : ObjectId("581288b9f374076da2e36fe5"),
            "name" : "Star Alliance",
            "airlines" : [
                    "Air Canada",
                    "Adria Airways",
                    "Avianca",
                    "Scandinavian Airlines",
                    "All Nippon Airways",
                    "Brussels Airlines",
                    "Shenzhen Airlines",
                    "Air China",
                    "Air New Zealand",
                    "Asiana Airlines",
                    "Brussels Airlines",
                    "Copa Airlines",
                    "Croatia Airlines",
                    "EgyptAir",
                    "TAP Portugal",
                    "United Airlines",
                    "Turkish Airlines",
                    "Swiss International Air Lines",
                    "Lufthansa",
                    "EVA Air",
                    "South African Airways",
                    "Singapore Airlines"
            ]
        }
	
Determine the approach that satisfies the following question in the most efficient manner:

Find the list of all possible distinct destinations, with at most one layover, departing from the base airports of airlines that make part of the "OneWorld" alliance. The airlines should be national carriers from Germany, Spain or Canada only. Include both the destination and which airline services that location. As a small hint, you should find 158 destinations.

Select the correct pipeline from the following set of options:

Choose the best answer:
 - db.air_routes.aggregate(
      [
        {"$lookup": {
          "from": "air_alliances",
          "foreignField": "airlines",
          "localField": "airline.name",
          "as": "alliance"
        }},
        {"$match": {"alliance.name": "OneWorld"}},
        {"$lookup": {
          "from": "air_airlines",
          "foreignField": "name",
          "localField": "airline.name",
          "as": "airline"
        }},
        {"$graphLookup": {
          "startWith": "$airline.base",
          "from": "air_routes",
          "connectFromField": "dst_airport",
          "connectToField": "src_airport",
          "as": "connections",
          "maxDepth": 1
        }},
        {"$project":{ "connections.dst_airport": 1 }},
        {"$unwind": "$connections"},
        {"$group": { "_id": "$connections.dst_airport" }}
      ]
    ) 
 -  var airlines = [];
    db.air_alliances.find({"name": "OneWorld"}).forEach(function(doc){
      airlines = doc.airlines
    })
    var oneWorldAirlines = db.air_airlines.find({"name": {"$in": airlines}})
    
    oneWorldAirlines.forEach(function(airline){
      db.air_alliances.aggregate([
      {"$graphLookup": {
        "startWith": airline.base,
        "from": "air_routes",
        "connectFromField": "dst_airport",
        "connectToField": "src_airport",
        "as": "connections",
        "maxDepth": 1
      }}])
    })
 -  db.air_alliances.aggregate([{
      $match: { name: "OneWorld" }
    }, {
      $graphLookup: {
        startWith: "$airlines",
        from: "air_airlines",
        connectFromField: "name",
        connectToField: "name",
        as: "airlines",
        maxDepth: 0,
        restrictSearchWithMatch: {
          country: { $in: ["Germany", "Spain", "Canada"] }
        }
      }
    }, {
      $graphLookup: {
        startWith: "$airlines.base",
        from: "air_routes",
        connectFromField: "dst_airport",
        connectToField: "src_airport",
        as: "connections",
        maxDepth: 1
      }
    }, {
      $project: {
        validAirlines: "$airlines.name",
        "connections.dst_airport": 1,
        "connections.airline.name": 1
      }
    },
    { $unwind: "$connections" },
    {
      $project: {
        isValid: { $in: ["$connections.airline.name", "$validAirlines"] },
        "connections.dst_airport": 1
      }
    },
    { $match: { isValid: true } },
    { $group: { _id: "$connections.dst_airport" } }
    ])
	
 -  db.air_airlines.aggregate(
      [
        {"$match": {"country": {"$in": ["Spain", "Germany", "Canada"]}}},
        {"$lookup": {
          "from": "air_alliances",
          "foreignField": "airlines",
          "localField": "name",
          "as": "alliance"
        }},
        {"$match": {"alliance.name": "OneWorld"}},
        {"$graphLookup": {
          "startWith": "$base",
          "from": "air_routes",
          "connectFromField": "dst_airport",
          "connectToField": "src_airport",
          "as": "connections",
          "maxDepth": 1
        }},
        {"$project":{ "connections.dst_airport": 1 }},
        {"$unwind": "$connections"},
        {"$group": { "_id": "$connections.dst_airport" }}
      ]
    )

<details>
  <summary>Click here for the solution</summary>
    <ul>
      <li>db.air_alliances.aggregate([{
      $match: { name: "OneWorld" }
    }, {
      $graphLookup: {
        startWith: "$airlines",
        from: "air_airlines",
        connectFromField: "name",
        connectToField: "name",
        as: "airlines",
        maxDepth: 0,
        restrictSearchWithMatch: {
          country: { $in: ["Germany", "Spain", "Canada"] }
        }
      }
    }, {
      $graphLookup: {
        startWith: "$airlines.base",
        from: "air_routes",
        connectFromField: "dst_airport",
        connectToField: "src_airport",
        as: "connections",
        maxDepth: 1
      }
    }, {
      $project: {
        validAirlines: "$airlines.name",
        "connections.dst_airport": 1,
        "connections.airline.name": 1
      }
    },
    { $unwind: "$connections" },
    {
      $project: {
        isValid: { $in: ["$connections.airline.name", "$validAirlines"] },
        "connections.dst_airport": 1
      }
    },
    { $match: { isValid: true } },
    { $group: { _id: "$connections.dst_airport" } }
    ])</li>
    </ul>
</details>
