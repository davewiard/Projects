# NWEA Blog 
Project

This is a project demonstrating the submission and retrieval of blog post
entries. Refer to the [https://github.com/nwea-techops/blogpostapi](blogpostapi)
repository on GitHub for deailts of the project.

## Development Timeline

### Thursday, April 12, 2018

#### 12:15 PM

I got a phone call and email from a recruiter asking if I'd be interested in a
6-month contract DevOps Engineer position with NWEA. After some discussion I
decided to go ahead with it. There were a few questions surrounding his ability
to represent me as I had recently been represented for a different NWEA position
by a different recruiter with a different firm. After that was all worked out
and after he spoke to the hiring manager with the client to determine if my
skillset really matched what they were looking for we got the representation
consent form filled out.

#### 1:45 PM

At about 1:45 PM I started looking into the project description and what it
would require. It is a somewhat trivial exercise but demonstrative of full-stack
development skills, nonetheless.

I read the entire project outline and started brainstorming ideas for how to
implement the project. I decided on the following:

* Bootstrap 4 for the presentation layer
* JavaScript/jQuery for the client-side data handling
* jQuery/AJAX promises for the data retrieval
* PHP back-end for handling all database interactions and data transformations

As my workstation is running Gentoo Linux with Apache already configured and
running it was an obvious choice.

I initially started by getting the base UI framework in place. I put together
the Bootstrap navbar, jumbotron, and input form first. Then I moved on to the
card-based blog entries. After I was happy with how the the card-based entries
looked I moved on to setting up some dummy JSON data that I could use as input
for the dynamically-rendered cards.

#### 2:30 PM

It was around to 2:30 PM when I decided to utilize a JavaScript template
framework instead of hard-wiring the HTML into my JavaScript. I picked json2html
and figured out the basics of rendering the HTML using my dummy input data.

#### 2:45 PM

I started working on the back-end PHP whipping up a simple database connection
using the built-in SQLite3 API but quickly realized that it didn't have the
capability of fetching all the records in a single result set in a format that
was easily converted to JSON. I would have to loop through each row and add it
to an array. I didn't like that so I decided to try the PDO interface. My
version of PHP didn't have PDO support compiled in so I added the "pdo" USE flag
and recompiled PHP. This took about 8 minutes. At the end of the build process
I had to restart Apache for the changes to take effect.

#### 3:30 PM

I now had a fully-functioning GET interface that could return the results to
the browser in JSON format but once the browser got the data it couldn't do
anything with it. I needed to adjust my templating code to now take place in the
AJAX .then() instead of stand-alone against the dummy data. I switched that code
easily enough and had a card being rendered with data pulled from the database.
I added a couple records to my copy of the blog.db manually so I could verify
that my code was indeed pulling all the records and displaying them properly in
the browser. I removed the hard-coded cards that I had originally set in place
for getting the UI behaving properly.

#### 4:00 PM

I re-read the project requirements and realized that I had not checked in any
code yet and that my endpoints were not pointing at the requested /post and
/posts paths.

So I set out to reconfigure Apache to redirect requests to my "php" directory
to the requested /post and /posts paths. I also renamed my original api.php to
index.php so it could be picked up automatically without the file name.

#### 4:30 PM

I began to document my timeline and thought process here, which I had planned to
do anyway, but to a lesser extent. After I had written up a lot of this document
I had to take a break. The rest of my family would be home and it would be time
for dinner.

#### 7:15 PM

I was able to break away for a few minutes to set up my GitHub repository initialize
my local repository.

#### 8:15 PM
I was able to step away for a minute or two again to add, commit, and push all my
files up to GitHub and update this timeline. I deliberately did not commit this
timeline as I felt it was incomplete and needed more content before I could
commit and push to GitHub.

#### 9:20 PM

I took a few minutes to adjust the content of this timeline and finally push the
first version of README.md to GitHub and call it a night. The POST handler and
some JavaScript/PHP cleanup are due in the morning.
