/*
 * Problem:
 * Which alliance from air_alliances flies the most routes with either a
 * Boeing 747 or an Airbus A380 (abbreviated 747 and 380 in air_routes)?
 */

var pipeline = [
	{
		$unwind: '$airlines'
	},
	{
		$lookup: {
			from: 'air_routes',
			localField: 'airlines',
			foreignField: 'airline.name',
			as: 'routes'
		}
	},
	{
		$unwind: '$routes'
	},
	{
		$match: {
			'routes.airplane': { $in: [ '747', '380' ] }
		}
	},
	{
		$group: {
			'_id': '$name',
			'route_count': { $sum: 1 }
		}
	},
	{
		$sort: { 'route_count': -1 }
	}
];

print('Result: ')
printjson(db.air_alliances.aggregate(pipeline).next());

