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
