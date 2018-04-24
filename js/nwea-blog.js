//
// json2html template for building the blog post cards consisting of only
// a title and body text
//
let template = {
  '<>':'div', 'class':'col-md-4', 'html':[
    {'<>':'div', 'class':'card', 'id': 'post_id-${id}', 'html': [
      {'<>': 'div', 'class': 'card-header bg-secondary text-white', 'html': '${title}'},
      {'<>': 'div', 'class': 'card-body', 'html': '${body}'}
    ]}
  ]
};


//
// Submits the current blog post entry, clears the form, and reloads the blog posts
//
function submitPost() {
  let title = $('input[name=newBlogTitle]').val();
  let body = $('textarea[name=newBlogBody]').val();

  let saveData = $.ajax({
        method: "POST",
        url: window.location.protocol + '//' + window.location.host + '/post/',
        data: { "title": title, "body": body }
  }).done(function(response) {
    let data = { "title": title, "body": body };
    let html = json2html.transform(data, template);
    $('#allBlogEntries > .row').append(html);

    $('input[name=newBlogTitle]').val('');
    $('textarea[name=newBlogBody]').val('');

    $('#snackbar').text('Successfully added blog post!')

    // Show the snackbar then, after 3 seconds, remove the show class from DIV
    $('#snackbar').addClass('show');
    setTimeout(function(){
      $('#snackbar').removeClass('show');
    }, 3000);
  });

  // TODO
  // add a .fail() condition on the ajax call above
}


//
// retrieve all posts from the database and populate cards
//
function getPosts() {
  // clear the current set of blog posts
  $('#allBlogEntries > .row').empty();

  // create the promise for retreiving stock and chart data and execute
  // on the results
  $.ajax({
    url: window.location.protocol + '//' + window.location.host + '/posts/'
  }).then(function(data) {
    $.each(JSON.parse(data), function(index, d) {
      let html = json2html.transform(d, template);
      $('#allBlogEntries > .row').append(html);
    });
  })
}


//
// This loads all the blog posts when the page loads
//
$(document).ready(function() {
  // register onClick event for the submit button
  $('#newBlogSubmit').click(submitPost);

  // load the current set of blog posts
  getPosts()
});
