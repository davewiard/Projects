/*
 * Problem:
 *
 * How many movies are in both the top ten highest rated movies according
 * to the imdb.rating and the metacritic fields? We should get these results
 * with exactly one access to the database.
 *
 * Hint: What is the intersection?
 *
 * Answer:
 *   1
 */
var pipeline = [
	{
		$match: {
			'imdb.rating' : { $gt : 0 },
			'metacritic' : { $gt : 0 }
		}
	},
	{
		$facet: {
			'topTenImdb': [
				{
					$sort: { 'imdb.rating' : -1 }
				},
				{
					$limit: 10
				}
			],
			'topTenMetacritic': [
				{
					$sort: {
						'metacritic': -1
					}
				},
				{
					$limit: 10
				}
			]
		}
	},
	{
		$project: {
			'commonTopFilms': {
				$size: {
					$setIntersection: [
						'$topTenImdb',
						'$topTenMetacritic'
					]
				}
			}	
		}
	}
];

// Prints the result.
var result = db.movies.aggregate(pipeline);
print('Result: ');
while (result.hasNext()) {
	printjson(result.next());
}
