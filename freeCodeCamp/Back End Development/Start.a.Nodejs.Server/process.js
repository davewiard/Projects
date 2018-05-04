var result = process.argv.slice(2).reduce(function(sum, n) {
	return sum + parseInt(n, 10)
}, 0);
console.log(result);
