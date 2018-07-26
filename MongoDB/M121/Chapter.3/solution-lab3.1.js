var pipeline = [
	{
		$match: {
			'awards': /^Won \d+ Oscar/i
		}
	},
	{
		$group: {
			'_id': null,
			'highest_rating': { $max: '$imdb.rating' },
			'lowest_rating': { $min: '$imdb.rating' },
			'average_rating': { $avg: '$imdb.rating' },
			'deviation': { $stdDevSamp: '$imdb.rating' }
		}
	}
];

print('Result: ');
printjson(db.movies.aggregate(pipeline).next());

