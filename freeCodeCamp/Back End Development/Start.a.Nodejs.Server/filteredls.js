var fs = require('fs');
var path = require('path');	// for use with extracting extension from filename in extension function

var pathGiven = process.argv[2]
var extFilter = process.argv[3];

function extension(element) {
	var extName = path.extname(element);	
	return extName === '.' + extFilter;
};

fs.readdir(pathGiven, function(err, list) {
	list.filter(extension).forEach(function(value) {
		console.log(value);
	});
});

