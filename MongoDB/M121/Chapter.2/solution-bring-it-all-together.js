//
// Problem:
//
// Calculate an average rating for each movie in our collection where
// English is an available language, the minimum imdb.rating is at
// least 1, the minimum imdb.votes is at least 1, and it was released
// in 1990 or after. You'll be required to rescale (or normalize)
// imdb.votes. The formula to rescale imdb.votes and calculate
// normalized_rating is included as a handout.
// 
// What film has the lowest normalized_rating?
//
// Answer:
//
//    The Christmas Tree
//

var x_max = 1521105
var x_min = 5
var min = 1
var max = 10    // never used, no idea why MongoDB University includes this

//
// 1. Filter the document set to only qualifying documents
// 2. Add new field called 'scaled_votes' using the formula from scaling.js
// 3. Project out only the relevant fields we want to see
// 4. Sort the results and show only the one document we're looking for
//
var pipeline = [
  {
    $match: {
      'imdb.votes': { $gte: 1 },
      'year': { $gte: 1990 },
      'languages': { $in: [ 'English' ] },
      'imdb.rating': { $gte: 1 },
    }
  },
  {
    $addFields: {
      scaled_votes: {
        $add: [
          1,
          {
            $multiply: [
              9,
              {
                $divide: [
                  {
                    $subtract: [ '$imdb.votes', x_min ]
                  },
                  {
                    $subtract: [ x_max, x_min ]
                  }
                ]
              },
            ]
          },
        ]
      }
    }
  },
  {
    $project: {
      'title': 1,
      'imdb.rating': 1,
      'imdb.votes': 1,
      'year': 1,
      'languages': 1,
      'normalized_rating': {
        $avg: [ '$scaled_votes', '$imdb.rating' ]
      },
      '_id': 0
    }
  },
  {
    $sort: { 'normalized_rating': 1 }
  },
  {
    $limit: 1
  }
];
