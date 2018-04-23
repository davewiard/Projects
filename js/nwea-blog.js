let template = {
  '<>':'div', 'class':'col-md-4', 'html':[
    {'<>':'div', 'class':'card', 'id': 'post_id-${id}', 'html': [
      {'<>': 'div', 'class': 'card-header bg-secondary text-white', 'html': '${title}'},
      {'<>': 'div', 'class': 'card-body', 'html': '${body}'}
    ]}
  ]
};


//
// create the promise for retreiving stock and chart data and execute
// on the results
//
$.ajax({
  url: window.location.protocol + '//' + window.location.host + '/posts'
}).then(function(data) {
  $.each( JSON.parse(data), function(index, d) {
    let html = json2html.transform(d, template);
    $('#allBlogEntries > .row').append(html);
  });
});
