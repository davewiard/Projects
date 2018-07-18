//
// Problem:
//
// Help MongoDB pick a movie our next movie night! Based on employee polling,
// we've decided that potential movies must meet the following criteria.
// 
//   * imdb.rating is at least 7
//   * genres does not contain "Crime" or "Horror"
//   * rated is either "PG" or "G"
//   * languages contains "English" and "Japanese"
//
// Assign the aggregation to a variable named pipeline
//
// Answer:
//
//     15
//
var pipeline = [
	{
		$match: {
			'imdb.rating': {
				$gte: 7
			},
			'genres': {
				$not: {
					$in: ['Crime', 'Horror']
				}
			},
			'rated': {
				$in: ['G', 'PG']
			},
			'languages': {
				$all: ['English', 'Japanese']
			}
		}
	}
];
