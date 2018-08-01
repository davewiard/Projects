# Problem 2.1

## Using Cursor-like Stages

MongoDB has another movie night scheduled. This time, we polled employees for their favorite actress or actor, and got these results

    favorites = [
      "Sandra Bullock",
      "Tom Hanks",
      "Julia Roberts",
      "Kevin Spacey",
      "George Clooney"]

For movies released in the USA with a tomatoes.viewer.rating greater than or equal to 3, calculate a new field called num_favs that represets how many favorites appear in the cast field of the movie.

Sort your results by num_favs, tomatoes.viewer.rating, and title, all in descending order.

What is the title of the 25th film in the aggregation result?

Choose the best answer:
 - Wrestling Ernest Hemingway
 - Recount
 - Erin Brockovich
 - The Heat

<details>
  <summary>Click here for the solution</summary>
    <ul>
      <li>The Heat</li>
    </ul>
</details>


# Problem 2.2

## Bringing it all together

Download Handouts:
- <a href="https://s3.amazonaws.com/edu-downloads.10gen.com/M121_2018_March/static/handouts/m121/chapter2/scaling.js">m121/chapter2/scaling.js</a>

Calculate an average rating for each movie in our collection where English is an available language, the minimum imdb.rating is at least 1, the minimum imdb.votes is at least 1, and it was released in 1990 or after. You'll be required to <a href="https://en.wikipedia.org/wiki/Feature_scaling">rescale (or normalize)</a> imdb.votes. The formula to rescale imdb.votes and calculate normalized_rating is included as a handout.

What film has the lowest normalized_rating?

Choose the best answer:
 - Avatar: The Last Airbender
 - Twilight
 - The Christmas Tree
 - DMZ

<details>
  <summary>Click here for the solution</summary>
    <ul>
      <li>The Christmas Tree</li>
    </ul>
</details>
