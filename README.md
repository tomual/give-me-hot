# give-me-hot
A dashboard of trending articles from various sites related to website development/programming and video games. Made with Java using Spring Boot.

![Screenshot](http://tomual.com/images/blog/ss%20(2018-02-16%20at%2008.25.03).png)

[View app](https://give-me-hot.herokuapp.com/) - Hosted on Heroku; will take 20 seconds to boot

Displays the following:
* 13 trending posts from [Reddit](https://www.reddit.com/r/all/)
* 13 top questions from [StackOverflow](https://stackoverflow.com/)
* 5 recent items from [The Gamer's Post](https://thegamerspost.com/)
* 5 recent items from [Slashdot](https://slashdot.org/)
* 3 top streams from [Twitch](https://www.twitch.tv/directory/all)
* Latest issue of [Codrops Collective](https://tympanus.net/codrops/collective/)
* Latest news issue of [Webdesigner Depot](https://www.webdesignerdepot.com/category/news/)

## Installation

This project uses Maven for package management.

The application requires three environment variables to be set for the Twitch API:
* TWITCH_CLIEND_ID - Twitch API client ID
* TWITCH_CLIENT_SECRET - Twitch API client secret
* TWITCH_TOKEN - Get one [here](https://twitchapps.com/tmi/)

After the Maven packages are imported and environment variables are set, run Spring Boot to start the application.
