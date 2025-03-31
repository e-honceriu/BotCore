# BotCore

# Table of Contents

# Introduction

**BotCore** is a backend framework designed to support the development and deployment of multiple Discord bots. It provides a scalable and secure environment by implementing a **Security Service** that manages API keys for each bot, ensuring controlled access to its functionalities.

The framework integrates multiple services to extend the capabilities of Discord bots:
- **Security Service**: Handles API key generation and authentication for bots.
- **Music Service**: Enables music-related features such as playlists, song metadata retrieval, and engagement tracking.
- **Game Service**: Provides League of Legends-related functionalities, including match management and player statistics.

**BotCore** is written in **Java** and is designed to be deployed using **Docker**, making it easy to manage and scale multiple Discord bots efficiently.

You can find an integration of this backend in a Discord bot written in Python here: [BotX](https://github.com/e-honceriu/BotX).

# **Instalation Guide**

Follow the steps below to setup and run the project using Docker.

## Prerequisites

- **Docker**: [Download Docker](https://www.docker.com/products/docker-desktop)
- **Docker Compose**: Docker Compose is usually included with Docker Desktop, but if not, you can install it from [here](https://docs.docker.com/compose/install/).

## 1. Clone the Repository

- Start by cloning the repository to your local machine:

    ```bash
    git clone https://github.com/e-honceriu/BotX
    cd BotX
    ```

## 2. Configure the Environment Variables

- Modify or create the `.env` file located in the root directory of the project.

- The `.env` file should include the following variables:

    ```bash
    POSTGRES_USER=
    POSTGRES_PASSWORD=

    YOUTUBE_API_KEY=
    YOUTUBE_SAVE_PATH=
    YOUTUBE_MAX_PLAYLIST_SEARCH=50

    SPOTIFY_CLIENT_ID=
    SPOTIFY_CLIENT_SECRET=
    SPOTIFY_SAVE_PATH=
    SPOTIFY_TEMP_SAVE_PATH=
    SPOTIFY_CREDENTIALS_TIMEOUT=20

    SONG_MAX_DOWNLOAD_THREADS=10
    SONG_MAX_DOWNLOAD_TIME_SECONDS=600
    SONG_DEFAULT_PLATFORM=YOUTUBE

    LOL_SERVICE_MAX_BANS=5
    LOL_SERVICE_CHAMP_POOL_SIZE=15
    LOL_SERVICE_ELO_GAIN=20
    LOL_SERVICE_ELO_DIFFICULTY_FACTOR=20
    LOL_SERVICE_ELO_PERFORMANCE_FACTOR=20
    LOL_SERVICE_MAX_ELO_GAIN=50
    LOL_SERVICE_ELO_DIFFICULTY_K_FACTOR=200
    LOL_SERVICE_STARTING_ELO=400

    SERVICE_PORT=
    ```

- **`POSTGRES_USER`**:
    - The username for connecting to the PostgreSQL database.
    - **Example**: `mydbuser`.

- **`POSTGRES_PASSWORD`**:
    - The password for connecting to the PostgreSQL database.
    - **Example**: `randompassword`.

- **`YOUTUBE_API_KEY`**:
    - The API key for interacting with the YoutubeAPI,
    - You can see how to generate it [here](https://developers.google.com/youtube/v3/getting-started).

- **`YOUTUBE_SAVE_PATH`**:
    - The directory where Youtube song files will be saved.
    - This path should be an `absolute path` to a directory on your host machine.
    - **Example**: `/path/to/youtube/videos`.

- **`YOUTUBE_MAX_PLAYLIST_SEARCH`**:
    - The maximum number of items to retrieve per search when querying for a YouTube playlist. The maximum value is 50.

- **`SPOTIFY_CLIENT_ID`**:
    - The client ID for accessing the Spotify API.
    - You can see how to generate it [here](https://developer.spotify.com/documentation/web-api/concepts/apps).

- **`SPOTIFY_CLIENT_SECRET`**:
    - The client secret associated with yout Spotify Client ID.
    - You can see how to generate it [here](https://developer.spotify.com/documentation/web-api/concepts/apps).

- **`SPOTIFY_SAVE_PATH`**:
    - The directory where Spotify song files will be saved.
    - This path should be an `absolute path` to a directory on your host machine.
    - **Example**: `/path/to/spotify/videos`.

- **`SPOTIFY_TEMP_SAVE_PATH`**:
    - A temporary directory where Spotify music files will be stored before they are moved to the final location.
    - This path should be an `absolute path` to a directory on your host machine.
    - **Example**: `/path/to/temp/spotify/videos`.

- **`SPOTIFY_CREDENTIALS_TIMEOUT`**:
    - The timeout value (in seconds) for the Spotify authentication process. This is the maximum time to wait between refreshing Spotify credentials.

- **`SONG_MAX_DOWNLOAD_THREADS`**:
    - The maximum number of threads that can be used simultaneously to download songs. This helps manage resource allocation and improves download speed for multiple concurrent song downloads.

- **`SONG_MAX_DOWNLOAD_TIME_SECONDS`**:
    - The maximum amount of time (in seconds) to allow for downloading a song. If the download takes longer than this time, it will be aborted.

- **`SONG_DEFAULT_PLATFORM`**:
    - The default platform from where the song will be searched if none is provided. Supported platforms include YouTube, Spotify, etc. See [Platforms](#platforms) for the full list of available options.

- **`LOL_SERVICE_MAX_BANS`**:
    - The maximum number of champions that can be banned per match in the League of Legends service.

- **`LOL_SERVICE_CHAMP_POOL_SIZE`**:
    - The size of the champion pool for each team in a League of Legends match. This value determines how many champions each team will have available when champion pool generation is enabled for the match. See [Draft Types](#draft-types) for more information.

- **`LOL_SERVICE_ELO_GAIN`**:
    - The base Elo rating gain after a match in the League of Legends service.
    - *Example*:
        - `LOL_SERVICE_ELO_GAIN=20`
            - WIN → +20
            - LOSE → -20 

- **`LOL_SERVICE_ELO_DIFFICULTY_FACTOR`**:
    - A factor that adjusts Elo gains or losses based on the relative difficulty of the match in the League of Legends service. This determines the maximum Elo change (both gain and loss) after a match depending on the difficulty of the game.
    - *Example*:
        - `LOL_SERVICE_ELO_DIFFICULTY_FACTOR=20`
            - WIN when difficulty is 100% → +20
            - WIN when difficulty is 50% → +10
            - LOSE when difficulty is 50% → -10
            - LOSE when difficulty is 0% → -20

- **`LOL_SERVICE_ELO_DIFFICULTY_K_FACTOR`**:
    - The K-factor used to calculate Elo changes based on match difficulty. A higher K-factor results in larger Elo swings for difficult matches.
    - This factor is used to assess the relative difficulty of a player in comparison to the enemy team. It determines how much Elo a player needs to be above or below the enemy team's average Elo to achieve a 100% difficulty rating.
    - For example: 
        - `LOL_SERVICE_ELO_DIFFICULTY_K_FACTOR=200`
            - `PLAYER_ELO=400`
            - `ENEMY_TEAM_AVG_ELO=200` → difficulty = 0 (player is expected to win the match)
        - `LOL_SERVICE_ELO_DIFFICULTY_K_FACTOR=200`
            - `PLAYER_ELO=200`
            - `ENEMY_TEAM_AVG_ELO=400` → difficulty = 100 (player is not expected to win the match)

- **`LOL_SERVICE_ELO_PERFORMANCE_FACTOR`**:
    - A factor used to calculate a player's performance bonus based on their relative performance in a match. 
    - Formula: 
        - `PERFORMANCE_BONUS = LOL_SERVICE_ELO_PERFORMANCE_FACTOR * ((PlayerKills + PlayerAssists) / (TeamKills + TeamAssists) - PlayerDeaths / TeamDeaths)`

- **`LOL_SERVICE_MAX_ELO_GAIN`**:
    - The maximum amount of Elo a player can gain from a single match, no matter how high their performance or match difficulty.
    - The total match elo is computed as `ELO_GAIN + DIFFICULTY_BONUS + PERFORMANCE_BONUS`

- **LOL_SERVICE_STARTING_ELO`**:
    - The starting Elo rating for a new player in the League of Legends service. This is the initial rating before the player has played any games.

- **`SERVICE_PORT`**:
    - The port that your service will listen to.
    - The default is `8080`, but you can customize it.

## 3. Build and Run **BotCore**

- Note that this might take a while if it is the first time you are running it.

### **Linux/macOS**

- In the root directory of the project, run the following commands to load the environment variables using the env.sh script, build the Docker image (if not already built), and start the framework. 

- `env.sh` reads the `.env` file and sets the environment variables for the session.

    ```bash
    chmod +x env.sh
    source env.sh
    docker compose up --build
    ```

### **Windows**

- In the root directory of the project, run the following commands to load the environment variables using the env.bat script, build the Docker image (if not already built), and start the framework.

- `env.bat` reads the `.env` file and sets the environment variables for the session.

    ```bash
    call env.bat
    docker compose up --build
    ```

## 5. Stop **BotCore**

- You can stop all containers, by running in root directory of the project:

    ```bash
    docker compose down
    ```

## 6. Updating **BotCore**

To update BotCore with the latest changes from the repository:

1. Pull the latest changes from the repository

    ```bash
    git pull origin main
    ```

2. Rebuild the Docker images to apply the updates:

    ```bash
    docker compose up --build
    ```

# **Features**

- This section outlines the key features and functionalities of the BotCore framework. BotCore is designed to facilitate the integration of various services such as game management, music streaming, and bot management, providing a seamless experience for developers and users alike.
- Below are some of the core features.

## **Security Features**

### **Bot Management**
- **Bot Creation**: Easily create a new bot by providing the necessary details.

### **API Key Management**
- **Generate API Key**: Allow the creation of API keys for authentication and authorization when interacting with the bot services.

## **Music Features**

### **Playlist Management**

- **Playlist Management**: 
    - Create playlists within Discord servers, allowing users to organize songs for different purposes.
    - Modify existing playlists updating their contents as needed.
    - Delete playlists that are no longer needed.

- **Song Management**:
    - Add songs to playlists, letting users expand their music collection with selected tracks.
    - Remove songs from playlists when they are no longer needed or relevant.

### **Audio Management**

- **Retrieve Song Audio File**: Retrieve the audio file associated with a song by providing its unique identifier (e.g., song ID, YouTube ID, or Spotify ID).
- **Download Song Audio File**: Download audio files either by song ID or by song title and platform.
- **Track Download Progress**: Monitor the status of ongoing song downloads by tracking its unique download ID. This feature ensures that you can check the download progress in real-time.
- **Download Playlist**: Download multiple songs from a playlist by providing a platform playlist ID. This enables batch downloading, saving time and effort.

### **Engagement Tracking**

- **Listeners**: Track and submit listener data for a particular song. This feature helps monitor how many users listened to a specific song and provides engagement insights.
- **Reactions**: Capture user reactions (e.g., likes, dislikes) for a song. This feature allows you to gather feedback from your audience and measure their response to the content.
- **Streams**: Track and log streaming data (like requests) for a song. This feature allows you to capture how often a song is streamed, providing valuable engagement metrics.

### **Song Metadata**

- **Retrieve Song Metadata**: Retrieve metadata for songs using their unique ID, platform IDs, or platform playlist/album IDs.

## **Game Features**

### **League of Legends Series Management**
- **Series Creation**: Organize multi-match series.
- **Series Progress**: Track the status and results of matches within a series.
- **Series Information**: Retrieve detailed information about ongoing or completed series.

### **League of Legends Match Management**
- **Match Creation**: Create and organize new matches.
- **Match Details**: Retrieve and manage match information using unique match IDs.
- **Match Rosters**: Generate and update team rosters for ongoing or upcoming matches.
- **Champion Bans**: Ban champions during the draft phase to ensure balanced gameplay.
- **Match Results**: Set match results and track match status, including live updates.

### **League of Legends Team Management**
- **Team Champion Pools**: Generate, retrieve, and manage the champion pools for each team.

### **League of Legends Player Management**
- **Player Management**: Add, modify, delete, fetch players to the system with unique profiles.

### **League of Legends Player Stats Management**
- **Live Stats Tracking**: Continuously update player statistics during matches in real-time.
- **Stats Retrieval**: Retrieve specific player stats based on match IDs and player IDs.
- **Performance Monitoring**: Monitor player performance metrics during matches, including KDA and CS.

# Endpoint Overview

This section provides an overview of the available API endpoints for interacting with the BotCore framework. It categorizes the endpoints based on their functionality, including security features, music management, and game-related features. Each endpoint is designed to enable developers to easily integrate and manage bot operations, user interactions, and other related services.


## **Security Endpoints**

This section covers the API endpoints responsible for managing bot authentication and access control. These endpoints allow for secure bot creation and the generation and management of API keys, ensuring that only authorized users can interact with the bot services.

### Create Bot

- **HTTP Method**: `POST`
- **Path**: `/api/bot`
- **Description**: Creates a new bot with a specified name and configuration.

- **Request Body**

    ```json
    {
        "name": "MyBot"
    }
    ```

    - **name**: `str` - *required* → The name of the bot.

- **Response**:
    - `200 OK` → If the bot was sucessfully created, the response body with status `OK` is returned.
    - `400 Bad Request` → An invalid request body was received.
    - `409 Conflict` → A bot with this name already exists.

- **Response Body**:

    ```json
        "botId": "b18c46f2-5a14-45f3-9cdb-8196784cf9d9",
        "name": "ExampleBot",
        "apiKey": "0edhuvaGP5suv9kRkHvkH_L-VlYCRf5qDobePwHMCJg",
        "isBlocked": false
    ```

    - **botId**: `UUID` → The unique identifier of the created bot.
    - **name**: `str` → The name of the bot.
    - **apiKey**: `str` → The API key assigned to the bot.
    - **isBlocked**: `boolean` → Indicates whether the bot is blocked.

### API Key Generation

- **HTTP Method**: `POST`
- **Path**: `/api/bot/generate-api-key`
- **Description**: Generate an API Key for an already created bot. The bot is identified either by its name or its unique ID.

- **Request Body**:

    ```json
    {
        "botName": "ExampleBot",
        "botId": "b18c46f2-5a14-45f3-9cdb-8196784cf9d9"
    }
    ```

    - **`botName`**: `str` - *optional* → The name of the bot.
    - **`botId`**: `UUID` - *optional* → The unique identifier of the bot.
    - **Note**: Either `botName` or `botId` must be provided. If neither is provided, the request will fail.

- **Response**:
    - `200 OK` → If the API key was successfully generated.
    - `400 Bad Request` → If an invalid request body was received, meaning neither `botName` or `botId` were provided.
    - `403 Forbidden` → If the bot for which the request is made is blocked.
    - `404 Not Found` → The bot was not found.

- **Response Body**:

    ```json
    {
        "apiKey": "0edhuvaGP5suv9kRkHvkH_L-VlYCRf5qDobePwHMCJg",
        "botName": "ExampleBot"
    }
    ```

    - **apiKey**: `str` → The newly generated API key. This will replace any existing API key for the bot.
    - **botName**: `str` → The name of the bot associated with the API key.

## **Music Endpoints**

The Music API provides functionality for managing playlists, adding and removing songs, retrieving song metadata and audio files. These endpoints enable seamless integration with music services, allowing users to organize and interact with their favorite tracks.
- All endpoints require authentication via an API key. 
- Clients must include the API key in the request headers using the `X-API-KEY` field. 
- Requests without a valid API key will be rejected with a `403 Forbidden` response.

### Platforms

- The `platform` field will be referenced multiple times across various endpoints. The currently supported platforms are as follows:
    - `"YOUTUBE"`
    - `"SPOTIFY"`
- These platforms are used to identify the source or provider of music content, such as songs or playlists, in the system.

### Get Song Metadata By Id

- **HTTP Method**: `GET`
- **Path**: `/api/music/metadata/id`
- **Description**: Retrieve a song's metadata by its unique identifier or external platform IDs.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Parameters**:
    - **id**: `UUID` - *optional* → The unique identifier of the song.
    - **youtubeId**: `str` - *optional* → The unique identifier of the song on YouTube.
    - **spotifyId**: `str` - *optional* → The unique identifier of the song on Spotify.
    - **Note**: At least one of the `id`, `youtubeId`, or `spotifyId` must be provided to retrieve the song.

- **Response**:
    - `200 OK` → If the song is found, returns song metadata.
    - `400 Bad Request` → If none of `id`, `youtubeId` or `spotifyId` were provided.
    - `403 Forbidden` → If an invalid or no API key is provided.
    - `404 Not Found` → The song was not found.

- **Response Body**:
    ```json
    {
        "id": "d7f4b8a7-2b5d-4e25-bb19-9c2f3c82a927",
        "title": "Example Song Title",
        "thumbnailUrl": "http://example.com/song-thumbnail.jpg",
        "audioFileAvailable": true,
        "externalId": {
            "platform": "Spotify",
            "externalId": "4PTG3Z6ehGkBFwjybzWkR8"
        }
    }
    ```
    - **id**: `UUID` → The unique identifier of the song.
    - **title**: `str` → The title of the song.
    - **thumbnailUrl**: `str` → The URL of the song's thumbnail image.
    - **audioFileAvailable**: `boolean` → Indicates whether the audio file is available.
    - **externalId**: `ExternalId` → Contains platform-specific song information:
        - **platform**: `str` → The platform of the song, see [Platform](#platforms) for more information.
        - **externalId**: `str` → The unique identifier on the platform.

### Get Song Metadata by Title

- **HTTP Method**: `GET`
- **Path**: `/api/music/metadata/title`
- **Description**: Retrieve a song's metadata by its title.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Parameters**:
    - **title**: `str` - *required* → The title of the song.
    - **platform**: `str` - *optional* → The platform to search on, see [Platform](#platforms) for more information. If not platform is provided, the default one provided in the `.env` will be used.
  
- **Response**:
    - `200 OK` → If the song is found, returns song metadata.
    - `400 Bad Request` → Title was not provided.
    - `403 Forbidden` → If an invalid or no API key is provided.
    - `404 Not Found` → The song could not be found.

- **Response Body**:

    ```json
    {
        "id": "d7f4b8a7-2b5d-4e25-bb19-9c2f3c82a927",
        "title": "Example Song Title",
        "thumbnailUrl": "http://example.com/song-thumbnail.jpg",
        "audioFileAvailable": true,
        "externalId": {
            "platform": "YouTube",
            "externalId": "dQw4w9WgXcQ"
        }
    }
    ```

    - **id**: `UUID` → The unique identifier of the song.
    - **title**: `str` → The title of the song.
    - **thumbnailUrl**: `str` → The URL of the song's thumbnail image.
    - **audioFileAvailable**: `boolean` → Indicates whether the audio file is available.
    - **externalId**: `ExternalId` → Contains platform-specific song information:
        - **platform**: `str` → The platform of the song, see [Platform](#platforms) for more information.
        - **externalId**: `str` → The unique identifier on the platform.

### Get Metadata for Songs from an External Playlist

- **HTTP Method**: `GET`
- **Path**: `api/music/metadata/playlist`
- **Description**: Get the metadata of the songs in an external playlist.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Parameters**:
    - **youtubePlaylistId**: `str`- *optional*: The Youtube unique identifier of the playlist.
    - **spotifyPlaylistId**: `str`- *optional*: The Spotify unique identifier of the playlist.
    - **Note**: Either `youtubePlaylistId` or `spotifyPlaylistId` must be provided.

- **Response**:
    - `200 OK` → If the playlist is found, returns the response body with status `OK` is returned.
    - `400 Bad Request` → If neither the `youtubePlaylistId` or `spotifyPlaylistId` were provided.
    - `403 Forbidden` → If an invalid or no API key is provided.
    - `404 Not Found` → The playlist was not found.

- **Response Body**:

    ```json
    [
        {
            "id": "d7f4b8a7-2b5d-4e25-bb19-9c2f3c82a927",
            "title": "Example Song Title",
            "thumbnailUrl": "http://example.com/song-thumbnail.jpg",
            "audioFileAvailable": true,
            "externalId": {
                "platform": "YouTube",
                "externalId": "dQw4w9WgXcQ"
            }
        },
        {
            "id": "d7263ac8-25d5-4876-9e75-fdf70220dc2a",
            "title": "Example Song Title2",
            "thumbnailUrl": "http://example.com/song-thumbnail-1.jpg",
            "audioFileAvailable": true,
            "externalId": {
                "platform": "YouTube",
                "externalId": "CVsbTCdTyAM"
            }
        },
        ...
    ]
    ```
    - `List[Song]`
        - **id**: `UUID` → The unique identifier of the song.
        - **title**: `str` → The title of the song.
        - **thumbnailUrl**: `str` → The URL of the song's thumbnail image.
        - **audioFileAvailable**: `boolean` → Indicates whether the audio file is available.
        - **externalId**: `ExternalId` → Contains platform-specific song information:
            - **platform**: `str` → The platform of the song, see [Platform](#platforms) for more information.
            - **externalId**: `str` → The unique identifier on the platform.


### Get Metadata for Songs from an Album

- **HTTP Method**: `GET`
- **Path**: `api/music/metadata/album`
- **Description**: Get the metadata of the songs in an external album.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Parameters**:
    - **youtubeAlbumId**: `str`- *optional*: The YouTube unique identifier of the album.
    - **spotifyAlbumId**: `str`- *optional*: The Spotify unique identifier of the album.
    - **Note**: Either `youtubeAlbumId` or `spotifyAlbumId` must be provided.

- **Response**:
    - `200 OK` → If the album is found, returns the response body with status `OK` is returned.
    - `400 Bad Request` → If neither the `youtubeAlbumId` or `spotifyAlbumId` were provided.
    - `403 Forbidden` → If an invalid or no API key is provided.
    - `404 Not Found` → The album was not found.

- **Response Body**:

    ```json
    [
        {
            "id": "d7f4b8a7-2b5d-4e25-bb19-9c2f3c82a927",
            "title": "Example Song Title",
            "thumbnailUrl": "http://example.com/song-thumbnail.jpg",
            "audioFileAvailable": true,
            "externalId": {
                "platform": "YouTube",
                "externalId": "dQw4w9WgXcQ"
            }
        },
        {
            "id": "d7263ac8-25d5-4876-9e75-fdf70220dc2a",
            "title": "Example Song Title2",
            "thumbnailUrl": "http://example.com/song-thumbnail-1.jpg",
            "audioFileAvailable": true,
            "externalId": {
                "platform": "YouTube",
                "externalId": "CVsbTCdTyAM"
            }
        },
        ...
    ]
    ```
    - `List[Song]`
        - **id**: `UUID` → The unique identifier of the song.
        - **title**: `str` → The title of the song.
        - **thumbnailUrl**: `str` → The URL of the song's thumbnail image.
        - **audioFileAvailable**: `boolean` → Indicates whether the audio file is available.
        - **externalId**: `ExternalId` → Contains platform-specific song information:
            - **platform**: `str` → The platform of the song, see [Platform](#platforms) for more information.
            - **externalId**: `str` → The unique identifier on the platform.


### Get Audio File By Song ID

- **HTTP Method**: `GET`
- **Path**: `/api/music/audio/id`
- **Description**: Retrieve an audio file by its unique identifier, YouTube ID, or Spotify ID.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Parameters**:
    - **songId**: `UUID` - *optional* → The unique identifier of the song.
    - **youtubeId**: `str` - *optional* → The Youtube ID of the song.
    - **spotifyId**: `str` - *optional* → The Spotify ID of the song.
    - **Note**: You must provide at least one of the `songId`, `youtubeId`, or `spotifyId`.

- **Response**:
    - `200 OK` → If the audio file is found, it is returned in the response.
    - `400 Bad Request` → No `songId`, `youtubeId` or `spotifyId` were provided.
    - `403 Forbidden` → If an invalid or no API key is provided.
    - `404 Not Found` → If the audio file cannot be found.

- **Response Body**:
    - The response will contain the raw audio file (e.g., an MP3 file). 
    - You should handle the response as a binary stream, with `Content-Type: audio/mpeg` indicating the format of the file.

### Get Audio File By Title

- **HTTP Method**: `GET`
- **Path**: `/api/music/audio/title`
- **Description**: Retrieve an audio file by the song's title and platform.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Parameters**:
    - **title**: `str` - *required* → The title of the song.
    - **platform**: `str` - *optional* - The platform from which the song should be retrieved, see [Platform](#platforms) for more information. If not platform is provided, the default one provided in the `.env` will be used.

- **Response**:
    - `200 OK` → If the audio file is found, it is returned in the response.
    - `400 Bad Request` → If `title` was not provided.
    - `403 Forbidden` → If an invalid or no API key is provided.
    - `404 Not Found` → If the audio file cannot be found.

- **Response Body**:
    - The response will contain the raw audio file (e.g., an MP3 file). 
    - You should handle the response as a binary stream, with `Content-Type: audio/mpeg` indicating the format of the file.

### Download Audio By ID

- **HTTP Method**: `POST`
- **Path**: `/api/music/audio/download/id`
- **Description**: Request the download of an audio file by its unique identifier or external ID.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Body**:
    
    ```json
        "songId": "7f3e5c63-6b21-4b0c-bc57-27b6e1b70732",
        "platform": "YOUTUBE",
        "externalId": "dQw4w9WgXcQ"
    ```

    - **songId**: `UUID` - *optional* → The unique identifier of the song.
    - **platform**: `str` - *optional* → The external platform from which the song should be retrieved, see [Platform](#platforms) for more information.
    - **externalId**: `str` - *optional*  → The unique identifier on the platform.
    - **Note**: Either `songId` or `platform` and `externalId` must be provided.

- **Response**:
    - `200 OK` → If the download started, the response body with status `OK` is returned.
    - `400 Bad Request` → If an invalid request body was provided.
    - `403 Forbidden` → If an invalid or no API key is provided.
    - `404 Not Found` → The song was not found.

- **Response Body**:

    ```json
    {
        "id": "a048e316-02a3-43ed-9771-440f698e3f8a",
        "status": "DOWNLOADING",
        "song": {
            "id": "7f3e5c63-6b21-4b0c-bc57-27b6e1b70732",
            "title": "Song 2",
            "thumbnailUrl": "http://example.com/song2.jpg",
            "audioFileAvailable": true,
            "externalId": {
                "platform": "YOUTUBE",
                "externalId": "dQw4w9WgXcQ"
            }
        }
    }
    ```
    - **id**: `UUID` → The unique identifier for the download.
    - **status**: `str` → The current status of the download request. Possible values:
        - `DOWNLOADING` → The song is currently being downloaded.
        - `FAILED` → The download request has failed.
        - `DONE` → The download has completed successfully.
    - **song**: `Song` → The song that is downloading, see [Song Response](#get-song-metadata-by-id) for the full response details.

### Download Audio By Title

- **HTTP Method**: `POST`
- **Path**: `/api/music/audio/download/title`
- **Description**: Request the download of an audio file by its title.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Body**:
    
    ```json
    {
        "title": "Billie Jean",
        "platform": "YOUTUBE"
    }
    ```

    - **title**: `str` - *required* → The title of the song.
    - **platform**: `str` - *optional* → The external platform from which the song should be retrieved, see [Platform](#platforms) for more information. If no platform is provided, the default one is used.

- **Response**:
    - `200 OK` → If the download started, the response body with status `OK` is returned.
    - `400 Bad Request` → If `title` was not provided.
    - `403 Forbidden` → If an invalid or no API key is provided.
    - `404 Not Found` → The song was not found.

- **Response Body**:

    ```json
    {
        "id": "ab73762b-9e21-4b80-934e-e699731f99f1",
        "status": "DOWNLOADING",
        "song": {
            "id": "7f3e5c63-6b21-4b0c-bc57-27b6e1b70732",
            "title": "Song 2",
            "thumbnailUrl": "http://example.com/song2.jpg",
            "audioFileAvailable": true,
            "externalId": {
                "platform": "YOUTUBE",
                "externalId": "dQw4w9WgXcQ"
            }
        }
    }
    ```

    - **id**: `UUID` → The unique identifier for the download.
    - **status**: `str` → The current status of the download request. Possible values:
        - `DOWNLOADING` → The song is currently being downloaded.
        - `FAILED` → The download request has failed.
        - `DONE` → The download has completed successfully.
    - **song**: `Song` → The song that is downloading, see [Song Response](#get-song-metadata-by-id) for the full response details.

### Get Download

- **HTTP Method**: `GET`
- **Path**: `/api/music/audio/download`
- **Description**: Get a download by its ID.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Parameters**:
    - **downloadId**: `UUID` - *required* → The unique identifier of the download.

- **Response**:
    - `200 OK` → If the download was found, the response body with status `OK` is returned.
    - `400 Bad Request` → If `downloadId` was not provided.
    - `403 Forbidden` → If an invalid or no API key is provided.
    - `404 Not Found` → The download was not found.

- **Response Body**:

    ```json
    {
        "id": "a048e316-02a3-43ed-9771-440f698e3f8a",
        "status": "DOWNLOADING",
        "song": {
            "id": "7f3e5c63-6b21-4b0c-bc57-27b6e1b70732",
            "title": "Song 2",
            "thumbnailUrl": "http://example.com/song2.jpg",
            "audioFileAvailable": true,
            "externalId": {
                "platform": "YOUTUBE",
                "externalId": "dQw4w9WgXcQ"
            }
        }
    }
    ```

    - **id**: `UUID` → The unique identifier for the download.
    - **status**: `str` → The current status of the download request. Possible values:
        - `DOWNLOADING` → The song is currently being downloaded.
        - `FAILED` → The download request has failed.
        - `DONE` → The download has completed successfully.
    - **song**: `Song` → The song that is downloading, see [Song Response](#get-song-metadata-by-id) for the full response details.

### Download Songs By Playlist

- **HTTP Method**: `POST`
- **Path**: `/api/music/audio/download/playlist`
- **Description**: Download the songs of an external playlist.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Body**:

    ```json
    {
        "playlistId": "PLE0hg-LdSfycrpTtMImPSqFLle4yYNzWD",
        "platform": "YOUTUBE"
    }
    ```

    - **playlistId**: `str` - *required* → The external id of the playlist.
    - **platform**: `str`- *required* → The external platform from which the playlist should be retrieved, see [Platform](#platforms) for more information.

- **Response**:
    - `200 OK` → If the downloads started, the response body with status `OK` is returned.
    - `400 Bad Request` → If `playlistId` and `platform` were not provided.
    - `403 Forbidden` → If an invalid or no API key is provided.
    - `404 Not Found` → The playlist was not found.

- **Response Body**:

    ```json
    [
        {
            "id": "1234-abcd-5678-efgh",
            "status": "DOWNLOADING",
            "song": {
                "id": "7f3e5c63-6b21-4b0c-bc57-27b6e1b70732",
                "title": "Song 2",
                "thumbnailUrl": "http://example.com/song2.jpg",
                "audioFileAvailable": true,
                "externalId": {
                    "platform": "YOUTUBE",
                    "externalId": "dQw4w9WgXcQ"
                }
            }
        },
        {
            "id": "1234-abcd-5678-efgh",
            "status": "DOWNLOADING",
            "song": {
                "id": "7f3e5c63-6b21-4b0c-bc57-27b6e1b70732",
                "title": "Song 3",
                "thumbnailUrl": "http://example.com/song2.jpg",
                "audioFileAvailable": true,
                "externalId": {
                    "platform": "YOUTUBE",
                    "externalId": "CVsbTCdTyAM"
                }
            }
        },
        ...
    ]
    ```

    - `List[Download]`:
        - **id**: `UUID` → The unique identifier for the download.
        - **status**: `str` → The current status of the download request. Possible values:
            - `DOWNLOADING` → The song is currently being downloaded.
            - `FAILED` → The download request has failed.
            - `DONE` → The download has completed successfully.
        - **song**: `Song` → The song that is downloading, see [Song Response](#get-song-metadata-by-id) for the full response details.

### Add Stream

- **HTTP Method**: `POST`
- **Path**: `/api/music/engagement/stream`
- **Description**: Record a stream of a song.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Body**:

    ```json
    {
        "songId": "a048e316-02a3-43ed-9771-440f698e3f8a",
        "guildDiscordId": "6459713476",
        "requesterDiscordId": "756188956",
        "requestedAt": "2025-03-19T12:45:00Z"
    }
    ```
    - **songId**: `UUID` - *required* → The unique identifier of the song that is being streamed.  
    - **guildDiscordId**: `str` - *required* → The Discord unique identifier of the guild (server) where the song is being streamed.  
    - **requesterDiscordId**: `str` - *required* → The Discord unique identifier of the user who requested the song.  
    - **requestedAt**: `ISO 8601 datetime` - *required* → The timestamp indicating when the song was requested to be streamed. 

- **Response**: 
    - `200 OK` → If the stream was successfully recorded, the response body with status `OK` is returned.
    - `400 Bad Request` → If an invalid request body was provided.
    - `403 Forbidden` → If an invalid or no API key is provided.
    - `404 Not Found` → The song was not found.

- **Response Body**:

    ```json
    {
        "id": "503b36a4-6aeb-45b5-b8a7-3d000cf6c2ad",
        "songId": "a048e316-02a3-43ed-9771-440f698e3f8a",
        "guildDiscordId": "6459713476",
        "requesterDiscordId": "756188956",
        "requestedAt": "2025-03-19T12:45:00Z"
    }
    ```

    - **id**: `UUID` → The unique identifier of the recorded stream.  
    - **songId**: `UUID` → The unique identifier of the song that was streamed.  
    - **guildDiscordId**: `str` → The Discord unique identifier of the guild (server) where the song was streamed.  
    - **requesterDiscordId**: `str` → The Discord unique identifier of the user who requested the song.  
    - **requestedAt**: `ISO 8601 datetime` → The timestamp indicating when the song was requested to be streamed.

### Add Listeners

- **HTTP Method**: `POST`
- **Path**: `/api/music/engagement/listener`
- **Description**: Add a list of listeners to a stream.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Body**:

    ```json
    {
        "streamId": "ab73762b-9e21-4b80-934e-e699731f99f1",
        "listenersDiscordIds": ["1237984321321", "1237984741923"]
    }
    ```

    - **streamId**: `UUID` - *required* → The unique identifier of the stream to which the listeners will be added.
    - **listenersDiscordIds**: `List[str]` - *required* → The list the Discord unique identifiers of the users listening to the stream.

- **Response**:
    - `200 OK` → If the listeners were successfully added, the response body with status `OK` is returned.
    - `400 Bad Request` → If an invalid request body was provided.
    - `403 Forbidden` → If an invalid or no API key is provided.
    - `404 Not Found` → The stream was not found.

- **Response Body**:

    ```json
    {
        "streamId": "ab73762b-9e21-4b80-934e-e699731f99f1",
        "listenersDiscordIds": ["1237984321321", "1237984741923"]
    }
    ```

    - **streamId**: `UUID` → The unique identifier of the stream to which the listeners were added.
    - **listenersDiscordIds**: `List[str]`  → The list the Discord unique identifiers of listeners of the stream.

### Add Reaction

- **HTTP Method**: `POST`
- **Path**: `/api/music/engagement/reaction`
- **Description**: Add a user reaction to a song in a guild.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Body**:

    ```json
    {
        "songId": "6297ec28-1d0c-4079-a1e0-d07b21e35988",
        "guildDiscordId": "18461591123",
        "userDiscordId": "85612484",
        "type": "LIKE"
    }
    ```

    - **songId**: `UUID` - *required* → The unique identifier of the song to which the reaction will be added.
    - **guildDiscordId**: `str` - *required* → The Discord unique identifier of the guild (server) in which the reaction will be recorded.
    - **userDiscordId**: `str` - *required* → The Discord unique identifier of the user that the reaction belongs to.
    - **type**: `str` - *required* → The type of reaction. Possible values:
        - `LIKE` → The user liked the song.
        - `DISLIKE` → The user disliked the song.

- **Response**:
    - `200 OK` → If the reaction was successfully added, the response body with status `OK` is returned.
    - `400 Bad Request` → If an invalid request body was provided.
    - `403 Forbidden` → If an invalid or no API key is provided.
    - `404 Not Found` → The song was not found.

- **Response Body**:

    ```json
    {
        "songId": "6297ec28-1d0c-4079-a1e0-d07b21e35988",
        "guildDiscordId": "18461591123",
        "userDiscordId": "85612484",
        "type": "LIKE"
    }
    ```
    - **songId**: `UUID` → The unique identifier of the song to which the reaction was added.  
    - **guildDiscordId**: `str` → The Discord unique identifier of the guild (server) where the reaction was recorded.  
    - **userDiscordId**: `str` → The Discord unique identifier of the user who added the reaction.  
    - **type**: `str` → The type of reaction. Possible values:  
      - `"LIKE"` → Indicates that the user liked the song.  
      - `"DISLIKE"` → Indicates that the user disliked the song.  

### Get Song Reaction

- **HTTP Method**: `GET`
- **Path**: `/api/music/engagement/reaction/song`
- **Description**: Get all the reaction of a song.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Param**:
    - **songId**: `UUID` - *required* → The unique identifier of the song.
    - **guildDiscordId**: `str` - *required* → The Discord unique identifier of the guild(server) from which to retrieve the reactions.

- **Response**: 
    - `200 OK` → If the song was successfully found, the response body with status `OK` is returned.
    - `400 Bad Request` → If neither `songId` nor `guildDiscordId` were provided.
    - `403 Forbidden` → If an invalid or no API key is provided.
    - `404 Not Found` → The song was not found.

- **Response Body**:
    
    ```json
    {
        "songId": "a048e316-02a3-43ed-9771-440f698e3f8a",
        "likes": 10,
        "dislikes": 1
    }
    ```

    - **songId**: `UUID` → The unique identifier of the song.
    - **likes**: `int` → The number of the likes of the song in the provided guild.
    - **dislikes**: `int` → The number of the dislikes of the song in the provided guild.

### Get Song Engagement

- **HTTP Method**: `GET`
- **Path**:`/api/music/engagement/song`
- **Description**: Get the engagement of a song in a guild (server).

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Param**:
    - **songId**: `UUID` - *required* → The unique identifier of the song of which to retrieve the engagement.
    - **guildDiscordId**: `str` - *required* → The unique identifier of the guild (server) from where to retrieve the engagement.

- **Response**: 
    - `200 OK` → The engagement of the song was successfully found, the response body with status `OK` is returned.
    - `400 Bad Request` → If neither `songId` nor `guildDiscordId` were provided.
    - `403 Forbidden` → If an invalid or no API key is provided.
    - `404 Not Found` → The song was not found.

- **Response Body**:

    ```json
    {
        "songId": "aa3497ff-a601-4fb1-baa0-ad2aaffc0f1a",
        "likesCount": 10,
        "dislikesCount": 1,
        "streamsCount": 10,
        "listenersCount": 20
    }
    ```

    - **songId**: `UUID` → The unique identifier of the song for which engagement metrics are being returned.  
    - **likesCount**: `long` → The total number of likes received by the song.  
    - **dislikesCount**: `long` → The total number of dislikes received by the song.  
    - **streamsCount**: `long` → The total number of times the song has been streamed.  
    - **listenersCount**: `long` → The total number of listens the song has received.

### Get Playlist

- **HTTP Method**: `GET`
- **Path**: `/api/metadata/playlist`
- **Description**: Retrieve a specific playlist by its ID, title and guild Discord ID.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Parameters**:
    - **playlistId**: `UUID`- *optional* : The unique identifier of the playlist.
    - **title**: `str`- *optional* : The title of the playlist.
    - **guildDiscordId**: `str`- *optional* : The Discord unique identifier of the guild (server).
    - **Note**: Either the `playlistId` or `title` and `guildDiscordId` must be provided.

- **Response**:
    - `200 OK` → If the request was successfull, the response body with status `OK` is returned.
    - `400 Bad Request` → If `playlistId` or `title` and `guildDiscordId` were not provided.
    - `403 Forbidden` → If an invalid or no API key is provided.
    - `404 Not Found` → The playlist was not found. 

- **Response Body**:

    ```json

    {
        "playlistId": "b18c46f2-5a14-45f3-9cdb-8196784cf9d9",
        "title": "Chill Vibes Playlist",
        "ownerDiscordId": "123456789012345678",
        "guildDiscordId": "987654321098765432",
        "songs": [
            {
            "song": {
                "id": "ec9f8b71-9f50-44a5-8693-307d01e8b9d5",
                "title": "Song 1",
                "thumbnailUrl": "http://example.com/song1.jpg",
                "audioFileAvailable": true,
                "externalId": {
                    "platform": "SPOTIFY",
                    "externalId": "4PTG3Z6ehGkBFwjybzWkR8"
                }
            },
            "position": 1
            },
            {
            "song": {
                "id": "7f3e5c63-6b21-4b0c-bc57-27b6e1b70732",
                "title": "Song 2",
                "thumbnailUrl": "http://example.com/song2.jpg",
                "audioFileAvailable": true,
                "externalId": {
                    "platform": "YOUTUBE",
                    "externalId": "dQw4w9WgXcQ"
                }
            },
            "position": 2
            }
        ]
    }
    ```

    - **playlistId**: `UUID` → The unique identifier of the playlist.
    - **title**: `str` → The title of the playlist.
    - **ownerDiscordId**: `str` → The Discord unique identifier of the playlist owner.
    - **guildDiscordId**: `str` → The Discord unique identifier of the guild to which the playlist belongs to.
    - **songs**: `List[PlaylistSong]` → A list of playlist songs having the following format:
        - **song**: `Song` → see [Song Response](#get-song-metadata-by-id) for the full response details.
        - **position**: `int` → The position of the song in the playlist.

### Get Playlists by Guild Discord ID

- **HTTP Method**: `GET`
- **Path**: `/api/music/playlist/guild`
- **Description**: Retrieve all playlists associated with a specific guild by providing the Discord unique identifier of the guild (server).

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Parameters**:
    - **guildDiscordId**: `str` - *required* → The unique Discord unique identifier of the guild (server).

- **Response**:
    - `200 OK` → If the request was successfull, the response body with status `OK` is returned.
    - `400 Bad Request` → If `guildDiscordId` was not provided.
    - `403 Forbidden` → If an invalid or no API key is provided.

- **Response Body**: List of playlists associated with the provided guild Discord ID.

    ```json
    [
        {
            "playlistId": "b18c46f2-5a14-45f3-9cdb-8196784cf9d9",
            "title": "Chill Vibes Playlist",
            "ownerDiscordId": "123456789012345678",
            "guildDiscordId": "987654321098765432",
            "songs": [
                {
                "song": {
                    "id": "ec9f8b71-9f50-44a5-8693-307d01e8b9d5",
                    "title": "Song 1",
                    "thumbnailUrl": "http://example.com/song1.jpg",
                    "audioFileAvailable": true,
                    "externalId": {
                        "platform": "SPOTIFY",
                        "externalId": "4PTG3Z6ehGkBFwjybzWkR8"
                    }
                },
                "position": 1
                },
                {
                "song": {
                    "id": "7f3e5c63-6b21-4b0c-bc57-27b6e1b70732",
                    "title": "Song 2",
                    "thumbnailUrl": "http://example.com/song2.jpg",
                    "audioFileAvailable": true,
                    "externalId": {
                        "platform": "YOUTUBE",
                        "externalId": "dQw4w9WgXcQ"
                    }
                },
                "position": 2
                }
            ]
        },
        {
            "playlistId": "b18c46f2-5a14-4sf3-9cdb-8196784cf9d9",
            "title": "Chill Vibes Playlist2",
            "ownerDiscordId": "123456789012345678",
            "guildDiscordId": "987654321098765432",
            "songs": [
                {
                "song": {
                    "id": "ec9f8b11-9f50-44a5-8693-307d01e8b9d5",
                    "title": "Song 3",
                    "thumbnailUrl": "http://example.com/song1.jpg",
                    "audioFileAvailable": true,
                    "externalId": {
                        "platform": "SPOTIFY",
                        "externalId": "1zFk56mgNsMRqLngPaKM5o"
                    }
                },
                "position": 1
                },
                {
                "song": {
                    "id": "7d3e5c63-6b21-4b0c-bc57-27b6e1b70732",
                    "title": "Song 4",
                    "thumbnailUrl": "http://example.com/song2.jpg",
                    "audioFileAvailable": true,
                    "externalId": {
                        "platform": "YOUTUBE",
                        "externalId": "rTgj1HxmUbg"
                    }
                },
                "position": 2
                }
            ]
        }

    ]
    ```
    - `List[Playlist]`: A list of playlist, see [Playlist Response](#get-playlist) for the full response details. If no playlist is found, an empty list is returned.

### Create Playlist

- **HTTP Method**: `POST`
- **Path**: `/api/music/playlist/`
- **Description**: Create a new playlist by providing the playlist details, including the title, owner Discord unique identifier, bot ID, and guild (server) Discord unique identifier.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Body**:

    ```json
    {
        "title": "Chill Vibes Playlist",
        "ownerDiscordId": "123456789012345678",
        "guildDiscordId": "987654321098765432"
    }
    ```

    - **title**: `str` - *required* → The title of the playlist.
    - **ownerDiscordId**: `str` - *required* → The Discord unique identifier of the owner of the playlist.
    - **guildDiscordId**: `str` - *required* → The Discord unique identifier of the guild (server) where the playlist will be created.

- **Response**:
    - `200 OK` → If the song playlist was successfully created, the response body with status `OK` is returned.
    - `400 Bad Request` → If an invalid request body is provided.
    - `403 Forbidden` → If an invalid or no API key is provided.

- **Response Body**:
    - `Playlist`: See [Playlist Response](#get-playlist) for the full response details.

### Add Songs To Playlist By Id

- **HTTP Method**: `POST`
- **Path**: `/api/music/playlist/song/id`
- **Description**: Add songs to an existing playlist using a list of song IDs and their external IDs.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Body**:
    
    ```json
    {
        "playlistId": "b18c46f2-5a14-45f3-9cdb-8196784cf9d9",
        "requesterDiscordId": "123456789012345678",
        "songIds": ["ec9f8b71-9f50-44a5-8693-307d01e8b9d5", "7f3e5c63-6b21-4b0c-bc57-27b6e1b70732"],
        "songExternalIds": ["rTgj1HxmUbg", "dQw4w9WgXcQ"],
        "platform": "YOUTUBE"
    }
    ```
    - **playlistId**: `UUID` - *required* → The unique identifier of the playlist where the songs will be added.
    - **requesterDiscordId**: `string` - *required* → The Discord unique identifier of the user requesting the addition of songs.
    - **songIds**: `Array[UUID]` - *optional* → A list of the unique identifiers for the songs being added to the playlist.
    - **songExternalIds**: `Array[string]` - *optional* → A list of external IDs for the songs.
    - **platform**: `str` - *optional* → The platform from which the songs will be added, see [Platform](#platforms) for more information.
    - **Note**: Either the `songIds` or `songExternalIds` along with the `platform` must be provided.

- **Response**:
    - `200 OK` → If the song was added successfully, the response body with status `OK` is returned.
    - `400 Bad Request` → If an invalid request body is provided.
    - `403 Forbidden` → If the requester is not the owner of the playlist/an invalid or no API key was provided.
    - `404 Not Found` → The playlist/song was not found.

- **Response Body**:
    - `Playlist`: See [Playlist Response](#get-playlist) for the full response details.

### Add Songs To Playlist By Title

- **HTTP Method**: `POST`
- **Path**: `/api/music/playlist/song/title`
- **Description**: Add songs to an existing playlist using a list of song titles and the platform from which they originate.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Body**:

    ```json
    {
       "playlistId": "b18c46f2-5a14-45f3-9cdb-8196784cf9d9",
        "requesterDiscordId": "123456789012345678",
        "songTitles": ["Beat It", "Billie Jean"],
        "platform": "YOUTUBE"
    }
    ```

    - **playlistId**: `UUID` - *required* → The unique identifier of the playlist where the songs will be added.
    - **requesterDiscordId**: `string` - *required* → The Discord unique identifier of the user requesting the addition of songs.
    - **songTitles**: `Array[string]` - *optional* → A list of the titles of the songs being added to the playlist.
    - **platform**: `string` - *optional* → The platform from which the songs will be added, see [Platform](#platforms) for more information. If no platform is provided, the default one is used.

- **Response**:
    - `200 OK` → If the song was added successfully, the response body with status `OK` is returned.
    - `400 Bad Request` → If an invalid request body is provided.
    - `403 Forbidden` → If the requester is not the owner of the playlist/an invalid or no API key was provided.
    - `404 Not Found` → The playlist/song was not found.

- **Response Body**:
    - `Playlist`: See [Playlist Response](#get-playlist) for the full response details.

### Remove Song From Playlist

- **HTTP Method**: `DELETE`
- **Path**: `/api/music/playlist/song`
- **Description**: Remove a specific song from a playlist by either song unique identifier or position within the playlist.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Parameters**:
    - **playlistId**: `UUID` - *required* → The unique identifier of the playlist where the songs will be added.
    - **requesterDiscordId**: `string` - *required* → The Discord unqiue identifier of the user requesting the removal.
    - **songId**: `UUID` - *optional* → The unique identifier of the song to be removed from the playlist.
    - **position**: `int` - *optional* → The position of the song to be removed from the playlist.
    - **Note**: Either the `songId` or `position` must be provided.

- **Response**:
    - `200 OK` → If the song is successfully removed, the response body with status `OK` is returned.
    - `400 Bad Request` → If `playlistId`, `requesterDiscordId` and `songId` or `position` were not provided.
    - `403 Forbidden` → If the requester is not the owner of the playlist/an invalid or no API key was provided.
    - `404 Not Found` → The playlist/song was not found.

- **Response Body**:
    - `Playlist`: See [Playlist Response](#get-playlist) for the full response details.

### Delete Playlist

- **HTTP Method**: `DELETE`
- **Path**: `/api/music/playlist`
- **Description**: Delete a specific playlist by its unqiue identifier.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Parameters**:
    - **playlistId**: `UUID`- *required* → The unique identifier of the playlist to be deleted.
    - **requesterDiscordId**: `str` - *required* → The Discord unique identifier of the user requesting the deletion of the playlist.

- **Response**:
    - `200 OK` → If the playlist deletion is successful, an empty response body with status `OK` is returned.
    - `400 Bad Request` → If `playlistId` and `requesterDiscordId` were not provided.
    - `403 Forbidden` → If the requester is not the owner of the playlist/an invalid or no API key was provided.
    - `404 Not Found` → The playlist was not found.

---

## **League of Legends Integration Endpoints**

The League of Legends API provides functionality for integrating match-related services, player management, team rosters, and live statistics. This API allows the management of various aspects of League of Legends matches, including creating matches, managing rosters, banning champions, tracking match results, and updating live player stats.
- All endpoints require authentication via an API key. 
- Clients must include the API key in the request headers using the `X-API-KEY` field. 
- Requests without a valid API key will be rejected with a `403 Forbidden` response.

### Game Types

The game types are combinations of two key components: `draft type` and `map`. Here’s how it breaks down:

#### **Draft Types**:

- **Random Draft (RDAM)**  → In this draft mode, random champions are selected from those that have not been banned, ensuring that the champion selection is balanced across roles (e.g., mage, assassin, fighter, marksman, tank, support). This means that champions are distributed in a way that covers all roles, making sure the team has a variety of positions filled.

- **Random Fearless Draft (RFDAM)** → Similar to Random Draft, but with a twist. The champions are selected randomly from those that were not banned and were not played in previous games of the series. They are still balanced across roles, but this ensures that previously played champions don’t repeat in the series.

- **No Draft (SR)** → The standard League of Legends draft system is used, where players do the draft directly in the League of Legends Client.

#### **Maps**:

- **AM (All Mid)**:  
  The game mode is set to Howling Abyss, where players face off in a single lane (All Mid) battle.

- **SR (Summoner’s Rift)**:  
  The classic 5v5 map of Summoner's Rift, where teams play on the traditional map with three lanes and a jungle.

#### **Game Type Combinations**:

- **RDAM (Random Draft All Mid)** → Random champions (balanced across roles) selected for the All Mid (Howling Abyss) map.

- **RFDAM (Random Fearless Draft All Mid)** → Random champions (not played in previous games, balanced across roles) selected for the All Mid (Howling Abyss) map.

- **RDSR (Random Draft Summoner’s Rift)** → Random champions (balanced across roles) selected for Summoner’s Rift.

- **RFDSR (Random Fearless Draft Summoner’s Rift)** → Random champions (not played in previous games, balanced across roles) selected for Summoner’s Rift.

- **SR (Summoner's Rift)** → Standard draft system (No Draft) on Summoner's Rift.

### Get Player

- **HTTP Method**: `GET`
- **Path**: `/api/game/lol/player`
- **Description**: Retrieves player information based on either their unique identifier or Discord unique identifier.

- **Request Headers**:
    - **X-API-KEY**: str → The API key to authenticate the request.

- **Request Params**:
    - **playerId**: `UUID` - *optional* → The unique identifier of the player.
    - **discordPlayerId**: `str` - *optional* → The Discord unique identifier of the ise.
    - **Note**: Either `playerId` or `discordPlayerId` must be provided.

- **Response**:
    - `200 OK` → If the request was successfull, the response body with status `OK` is returned.
    - `400 Bad Request` → If neither `playerId` or `discordPlayerId` were not provided.
    - `403 Forbidden` → If an invalid or no API key was provided.
    - `404 Not found` → The player was not found.

- **Response Body**:

    ```json
    {
        "id": "b1e89c92-6541-456f-9810-1d72c94099a9",
        "discordId": "18901245",
        "riotId":"SummonerName#SMTH",
        "elo": {
            "RDAM": 400,
            "RFDAM": 400,
            "RDSR": 400,
            "RFDSR": 400,
            "SR": 400
        }
    }
    ```
    - **id**: `UUID` → The unique identifier of the player.
    - **discordId**: `str` → The Discord unique indentifier of the player.
    - **riotId**: `str` → The player's Riot Games summoner name and tag.
    - **elo**: `Map[GameType, int]` → The player's elo rating for different game types (see [Game Types](#game-types) for more information).

### Create Player

- **HTTP Method**: `POST`
- **Path**: `/api/game/lol/player`
- **Description**: Creates a player compatible with the system. This endpoint allows for the registration of a new player, associating them with both their Discord and Riot accounts.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Body**:

    ```json
    {
        "discordId": "18901245",
        "riotId": "SummonerName#SMTH"
    }
    ```

    - **discordId**: `str` - *required* → The Discord unique identifier of the player.
    - **riotId**: `str` - *optional* → The player's Riot Games summoner name along with their tag (e.g., SummonerName#SMTH). It is required for live game data tracking.

- **Response**:
    - `200 OK` → If the request was successfull, the response body with status `OK` is returned.
    - `400 Bad Request` → If an invalid request body was provided.
    - `403 Forbidden` → If an invalid or no API key was provided.
    - `409 Confict` → If a player with the provided Discord unique identifier already exists.

- **Response Body**:

    ```json
    {
        "id": "b1e89c92-6541-456f-9810-1d72c94099a9",
        "discordId": "18901245",
        "riotId":"SummonerName#SMTH",
        "elo": {
            "RDAM": 400,
            "RFDAM": 400,
            "RDSR": 400,
            "RFDSR": 400,
            "SR": 400
        }
    }
    ```
    - `Player` → See [Player Response](#get-player) for the full response details.

### Edit Player

- **HTTP Method**: `PUT`
- **Path**: `/api/game/lol/player`
- **Description**: Updates a player's details, including their Discord ID, Riot ID, or Elo ratings.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Body**:

    ```json
    {
        "playerId": "b1e89c92-6541-456f-9810-1d72c94099a9",
        "discordId": "18901245",
        "riotId": "SummonerName#SMTH",
        "elo": {
            "RDAM": 450,
            "RFDAM": 420,
            "RDSR": 410,
            "RFDSR": 430,
            "SR": 440
        }
    }
    ```
    - **playerId**: `UUID` - *required* → The unique identifier of the player.
    - **discordId**: `str` - *optional* → The updated Discord unique identifier of the player.
    - **riotId**: `str` - *optional* → The updated Riot Games summoner name and tag.
    - **elo**: `Map[GameType, int]` - *optional* → The player's updated Elo ratings per game type.
        - **RDAM**: `int` - *optional* → Elo for Random Draft All Mid.
        - **RFDAM** `int` - *optional* → Elo for Random Fearless Draft All Mid.
        - **RDSR**: `int` - *optional* → Elo for Random Draft Summoner’s Rift.
        - **RFDSR**: `int` - *optional* → Elo for Random Fearless Draft Summoner’s Rift.
        - **SR**: `int` - *optional* → Elo for Summoner’s Rift.

- **Response**:
    - `200 OK` → If the request was successfull, the response body with status `OK` is returned.
    - `400 Bad Request` → If an invalid request body was provided.
    - `403 Forbidden` → If an invalid or no API key was provided.
    - `404 Not found` → Player with the provided `playerId` was not found.

- **Response Body**:

    ```json
    {
        "id": "b1e89c92-6541-456f-9810-1d72c94099a9",
        "discordId": "18901245",
        "riotId": "SummonerName#SMTH",
        "elo": {
            "RDAM": 450,
            "RFDAM": 420,
            "RDSR": 410,
            "RFDSR": 430,
            "SR": 440
        }
    }
    ```
    - **id**: `UUID` → The unique identifier of the player.
    - **discordId**: `str` → The Discord unique indentifier of the player.
    - **riotId**: `str` → The player's Riot Games summoner name and tag.
    - **elo**: `Map[GameType, int]` → The player's elo rating for different game types (see [Game Types](#game-types) for more information).

### Delete Player

- **HTTP Method**: `DELETE`  
- **Path**: `/api/game/lol/player`  
- **Description**: Deletes a player from the system.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Parameters**:  
    - **playerId**: `UUID` - *required* → The unique identifier of the player.  

- **Response**:
    - `204 No Content` → Player successfully deleted.
    - `400 Bad Request` → Missing or invalid parameters.
    - `403 Forbidden` → If an invalid or no API key was provided.
    - `404 Not Found` → Player with the provided `playerId` not found.

### Ranking Type  

- **RANKED** → The matches follows a structured ranking system, affecting player's Elo.  
- **UNRANKED** → Matches do not impact player's Elo.

### Get Series

- **Http Method**: `GET`
- **Path**: `/api/game/lol/series`
- **Description**: Retrieve series information by its id.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Params**:
    - **seriesId**: `UUID` - *required* → The unique identifier of the series.

- **Response**:
    - `200 OK` → If the request was successfull, the response body with status `OK` is returned.
    - `400 Bad Request` → If `seriesId` was not provided.
    - `403 Forbidden` → If an invalid or no API key was provided.
    - `404 Not found` → The series was not found.

- **Response Body**:

    ```json
    {
        "id": "5139aa1c-8c74-41a9-86f4-468579948493",
        "gameType": "RDAM",
        "rankingType": "RANKED"
    }
    ```
    - **id**: `UUID` → The unique identifier of the series.6
    - **gameType**: `GameType` → The game type for the matches of the series
    - **rankingType**: `RankingType` → The ranking type for the matches of the series, see [Raking Type](#ranking-type) for more information.

### Create Series

- **HTTP Method**: `POST`  
- **Path**: `/api/game/lol/series`  
- **Description**: Creates a new series with the specified game type, ranking type, and guild details.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Body**:

    ```json
    {
        "type": "RDAM",
        "rankingType": "RANKED",
        "guildDiscordId": "123456789012345678"
    }
    ```
    - **type**: `GameType` - *required* → The game type for the matches of the series, see [Game Types](#game-types) for more information.
    - **rankingType**: `RankingType` - *required* → The ranking type for the matches of the series, see [Ranking Type](#ranking-type) for more information.
    - **guildDiscordId**: `str` - *required* → The Discord unique identifier of the guild (server) in which the series takes place.

- **Response**:
    - `200 OK` → If the request was successfull, the response body with status `OK` is returned.
    - `400 Bad Request` → Missing or invalid parameters.
    - `403 Forbidden` → If an invalid or no API key was provided.

- **Response Body**:
    
    ```json
    {
        "id": "5139aa1c-8c74-41a9-86f4-468579948493",
        "gameType": "RDAM",
        "rankingType": "RANKED"
    }
    ```
    - `Series`: See [Series Response](#get-series) for the full response details.

### Match Status

The system supports the following match statuses:

- `TEAM_GENERATION`: Teams are being formed.
- `BANNING`: Teams ban champions to prevent the other team from picking them.
- `DRAFTING`:` Teams generate their champion pool for this match.
- `PLAYING`: The match is actively being played.
- `ENDED`: The match has finished.

### Get Match

- **HTTP Method**: `GET`
- **Path**: `/api/game/lol/match`
- **Description**: Retrieve match information by its unique identifier.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Params**:
    - **matchId**: `UUID` - *required* → The unique identifier of the match.

- **Response**:
    - `200 OK` → If the request was successfull, the response body with status `OK` is returned.
    - `400 Bad Request` → If `matchId` was not provided.
    - `403 Forbidden` → If an invalid or no API key was provided.
    - `404 Not found` → The match was not found.

- **Response Body**:

    ```json
        {
        "id": "5139aa1c-8c74-41a9-86f4-468579948493",
        "series": {
            "id": "e5fcf62a-52fd-47a9-b6c5-00a73b6479fc",
            "gameType": "RDAM",
            "rankingType": "RANKED"
        },
        "status": "PLAYING",
        "startTime": "2025-03-21T10:00:00",
        "teams": [
            {
                "id": "26f8a1f4-76bc-4778-8c07-2be1b0b617ff",
                "players": [
                    {
                        "id": "b1e89c92-6541-456f-9810-1d72c94099a9",
                        "discordId": "18901245",
                        "riotId":"SummonerName#SMTH",
                        "elo": {
                            "RDAM": 400,
                            "RFDAM": 400,
                            "RDSR": 400,
                            "RFDSR": 400,
                            "SR": 400
                        }
                    },
                    {
                        "id": "17283bd2-94e2-48c7-8444-442bf2b55d83",
                        "discordId": "51901612",
                        "riotId":"SummonerName2#SMTH",
                        "elo": {
                            "RDAM": 400,
                            "RFDAM": 400,
                            "RDSR": 400,
                            "RFDSR": 400,
                            "SR": 400
                        }
                    }
                ],
                "bans": [
                    {
                        "id": "d0a929b3-d255-49ac-bf47-6b8e517d11f1",
                        "riotId": "Aatrox",
                        "name": "Aatrox"
                    }
                ]
            },
            {
                "id": "7d07d42d-5172-4e7c-9f18-5a66d970d454",
                "players": [
                    {
                        "id": "b1e89c92-6541-456f-9810-1d72c94099a9",
                        "discordId": "93601245",
                        "riotId":"SummonerName3#SMTH",
                        "elo": {
                            "RDAM": 400,
                            "RFDAM": 400,
                            "RDSR": 400,
                            "RFDSR": 400,
                            "SR": 400
                        }
                    },
                    {
                        "id": "5de9db4c-9ef8-4d52-9cc2-5fcc25d737de",
                        "discordId": "18846245",
                        "riotId":"SummonerName4#SMTH",
                        "elo": {
                            "RDAM": 400,
                            "RFDAM": 400,
                            "RDSR": 400,
                            "RFDSR": 400,
                            "SR": 400
                        }
                    }
                ],
                "bans": [
                    {
                        "id": "f3ac1ed9-c41e-4b3a-bd42-bf87145a1fd7",
                        "riotId": "Jinx",
                        "name": "Jinx"
                    }
                ]
            }
        ]
    }
    ```

    - **id**: `UUID` → The unique identifier of the match.
    - **series**: `Series` →  See [Series Response](#get-series) for the full response details.
    - **status**: `String` → The current status of the match (e.g., `PLAYING`).
    - **startTime**: `LocalDateTime` → The start time of the match.
    - **teams**: `List<Team>` → List of teams participating in the match.
        - **id**: `UUID` → The unique identifier of the team.
        - **players**: `List<Player>` →  See [Player Response](#get-player) for the full response details.
        - **bans**: `Set<Champion>` → Set of champions banned by the team.
            - **id**: `UUID` → The unique identifier of the champion.
            - **riotId**: `String` → The Riot ID of the champion.
            - **name**: `String` → The name of the champion.

### Create Match

- **HTTP Method**: `POST`
- **Path**: `/api/game/lol/match`
- **Description**: Create a new match by providing the series unique identifier.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Body**:

    ```json
    {
        "seriesId": "e5fcf62a-52fd-47a9-b6c5-00a73b6479fc",
    }
    ```
    - **seriesId**: `UUID` - *required* → The unique identifier of the series.

- **Response**:
    - `200 OK` → If the match is successfully created, the response body with status `OK` is returned.
    - `400 Bad Request` → If the request body is invalid or required fields are missing.
    - `403 Forbidden` → If an invalid or no API key was provided. 
    - `404 Not Found` → If the series with the provided `seriesId` was not found.

- **Response Body**:

    ```json
    {
        "id": "5139aa1c-8c74-41a9-86f4-468579948493",
        "series": {
            "id": "e5fcf62a-52fd-47a9-b6c5-00a73b6479fc",
            "gameType": "RDAM",
            "rankingType": "RANKED"
        },
        "status": "TEAM_GENERATION",
        "startTime": "2025-03-21T10:00:00",
        "teams": []
    }
    ```
    - `Match` →  See [Match Response](#get-match) for the full response details.

### Generate Rosters

- **HTTP Method**: `POST`
- **Path**: `/api/game/lol/match/rosters`
- **Description**: Generate teams (rosters) for a match by providing the match unqiue identifier and player unique identifiers.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Body**:

    ```json
    {
        "matchId": "5139aa1c-8c74-41a9-86f4-468579948493",
        "playerIds": [
            "b1e89c92-6541-456f-9810-1d72c94099a9",
            "17283bd2-94e2-48c7-8444-442bf2b55d83",
            "7d07d42d-5172-4e7c-9f18-5a66d970d454",
            "5de9db4c-9ef8-4d52-9cc2-5fcc25d737de"
        ]
    }
    ```

    - **matchId**: `UUID` - *required* → The unique identifier of the match.
    - **playerIds**: `List<UUID>` - *required* → The list of player IDs to be included in the match rosters.

- **Response**:
    - `200 OK` → If the rosters are successfully generated, the response body with status `OK` is returned.
    - `400 Bad Request` → If the request body is invalid, required fields are missing or the match is not in `TEAM_GENERATION` phase.
    - `403 Forbidden` → If an invalid or no API key was provided. 
    - `404 Not Found` → If a player or match was not found.

- **Response Body**:

    - **Response Body**:

    ```json
    {
        "id": "5139aa1c-8c74-41a9-86f4-468579948493",
        "series": {
            "id": "e5fcf62a-52fd-47a9-b6c5-00a73b6479fc",
            "gameType": "RDAM",
            "rankingType": "RANKED"
        },
        "status": "TEAM_GENERATION",
        "startTime": "2025-03-21T10:00:00",
        "teams": [
            {
                "id": "26f8a1f4-76bc-4778-8c07-2be1b0b617ff",
                "players": [
                    {
                        "id": "b1e89c92-6541-456f-9810-1d72c94099a9",
                        "discordId": "18901245",
                        "riotId":"SummonerName#SMTH",
                        "elo": {
                            "RDAM": 400,
                            "RFDAM": 400,
                            "RDSR": 400,
                            "RFDSR": 400,
                            "SR": 400
                        }
                    },
                    {
                        "id": "17283bd2-94e2-48c7-8444-442bf2b55d83",
                        "discordId": "51901612",
                        "riotId":"SummonerName2#SMTH",
                        "elo": {
                            "RDAM": 400,
                            "RFDAM": 400,
                            "RDSR": 400,
                            "RFDSR": 400,
                            "SR": 400
                        }
                    }
                ],
                "bans": []
            },
            {
                "id": "7d07d42d-5172-4e7c-9f18-5a66d970d454",
                "players": [
                    {
                        "id": "b1e89c92-6541-456f-9810-1d72c94099a9",
                        "discordId": "93601245",
                        "riotId":"SummonerName3#SMTH",
                        "elo": {
                            "RDAM": 400,
                            "RFDAM": 400,
                            "RDSR": 400,
                            "RFDSR": 400,
                            "SR": 400
                        }
                    },
                    {
                        "id": "5de9db4c-9ef8-4d52-9cc2-5fcc25d737de",
                        "discordId": "18846245",
                        "riotId":"SummonerName4#SMTH",
                        "elo": {
                            "RDAM": 400,
                            "RFDAM": 400,
                            "RDSR": 400,
                            "RFDSR": 400,
                            "SR": 400
                        }
                    }
                ],
                "bans": []
            }
        ]
    }
    ```
    - `Match` → See [Match Response](#get-match) for the full response details.

### Ban Champion

- **HTTP Method**: `POST`
- **Path**: `/api/game/lol/match/champion/ban`
- **Description**: Ban a champion for a player in a match.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Body**:

    ```json
    {
        "playerId": "17283bd2-94e2-48c7-8444-442bf2b55d83",
        "matchId": "5139aa1c-8c74-41a9-86f4-468579948493",
        "champion": "Aatrox"
    }
    ```

    - **playerId**: `UUID` - *required* → The unique identifier of the player banning the champion.
    - **matchId**: `UUID` - *required* → The unique identifier of the match.
    - **champion**: `String` - *required* → The name or riot ID of the champion being banned.

- **Response**:
    - `200 OK` → If the champion is successfully banned, the response body with status `OK` is returned.
    - `400 Bad Request` → If the request body is invalid, required fields are missing, or the match is not in `TEAM_GENERATION` or `BANNING` phase.
    - `403 Forbidden` → If an invalid or no API key was provided. 
    - `404 Not Found` → If the player, match, or champion was not found.

- **Response Body**:

    ```json
        {
        "id": "5139aa1c-8c74-41a9-86f4-468579948493",
        "series": {
            "id": "e5fcf62a-52fd-47a9-b6c5-00a73b6479fc",
            "gameType": "RDAM",
            "rankingType": "RANKED"
        },
        "status": "BANNING",
        "startTime": "2025-03-21T10:00:00",
        "teams": [
            {
                "id": "26f8a1f4-76bc-4778-8c07-2be1b0b617ff",
                "players": [
                    {
                        "id": "b1e89c92-6541-456f-9810-1d72c94099a9",
                        "discordId": "18901245",
                        "riotId":"SummonerName#SMTH",
                        "elo": {
                            "RDAM": 400,
                            "RFDAM": 400,
                            "RDSR": 400,
                            "RFDSR": 400,
                            "SR": 400
                        }
                    },
                    {
                        "id": "17283bd2-94e2-48c7-8444-442bf2b55d83",
                        "discordId": "51901612",
                        "riotId":"SummonerName2#SMTH",
                        "elo": {
                            "RDAM": 400,
                            "RFDAM": 400,
                            "RDSR": 400,
                            "RFDSR": 400,
                            "SR": 400
                        }
                    }
                ],
                "bans": [
                    {
                        "id": "d0a929b3-d255-49ac-bf47-6b8e517d11f1",
                        "riotId": "Aatrox",
                        "name": "Aatrox"
                    }
                ]
            },
            {
                "id": "7d07d42d-5172-4e7c-9f18-5a66d970d454",
                "players": [
                    {
                        "id": "b1e89c92-6541-456f-9810-1d72c94099a9",
                        "discordId": "93601245",
                        "riotId":"SummonerName3#SMTH",
                        "elo": {
                            "RDAM": 400,
                            "RFDAM": 400,
                            "RDSR": 400,
                            "RFDSR": 400,
                            "SR": 400
                        }
                    },
                    {
                        "id": "5de9db4c-9ef8-4d52-9cc2-5fcc25d737de",
                        "discordId": "18846245",
                        "riotId":"SummonerName4#SMTH",
                        "elo": {
                            "RDAM": 400,
                            "RFDAM": 400,
                            "RDSR": 400,
                            "RFDSR": 400,
                            "SR": 400
                        }
                    }
                ],
                "bans": []
            }
        ]
    }
    ```

### Set Match Result

- **HTTP Method**: `POST`
- **Path**: `/api/game/lol/match/result`
- **Description**: Set the result of the match by providing the match unique identifier, and the winning team unique identifier.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Body**:

    ```json
    {
        "matchId": "a1234567-b89c-4d7f-8888-xyz123456789",
        "winningTeamId": "26f8a1f4-76bc-4778-8c07-2be1b0b617ff"
    }
    ```

    - **matchId**: `UUID` - *required* → The unique identifier of the match.
    - **winningTeamId**: `UUID` - *required* → The unique identifier of the winning team.

- **Response**:
    - `200 OK` → If the match result is successfully set, the response body with status `OK` is returned.
    - `400 Bad Request` → If the request body is invalid or required fields are missing.
    - `403 Forbidden` → If an invalid or no API key was provided. 
    - `404 Not Found` → If the match or winning team were not found.

- **Response Body**:
    See [Match Result Response](#match-result-response) for the full response details.
  
- **Response Body**

    ```json
    {
        "matchId": "a1234567-b89c-4d7f-8888-xyz123456789",
        "winningTeam": {
            "id": "26f8a1f4-76bc-4778-8c07-2be1b0b617ff",
            "players": [
                {
                    "id": "b1e89c92-6541-456f-9810-1d72c94099a9",
                    "discordId": "18901245",
                    "riotId": "SummonerName#SMTH",
                    "elo": {
                        "RDAM": 400,
                        "RFDAM": 400,
                        "RDSR": 400,
                        "RFDSR": 400,
                        "SR": 400
                    }
                }
            ],
            "bans": [
                {
                    "id": "d0a929b3-d255-49ac-bf47-6b8e517d11f1",
                    "riotId": "Aatrox",
                    "name": "Aatrox"
                }
            ]
        },
        "losingTeam": {
            "id": "7d07d42d-5172-4e7c-9f18-5a66d970d454",
            "players": [
                {
                    "id": "b1e89c92-6541-456f-9810-1d72c94099a9",
                    "discordId": "93601245",
                    "riotId": "SummonerName3#SMTH",
                    "elo": {
                        "RDAM": 400,
                        "RFDAM": 400,
                        "RDSR": 400,
                        "RFDSR": 400,
                        "SR": 400
                    }
                }
            ],
            "bans": [
                {
                    "id": "f3ac1ed9-c41e-4b3a-bd42-bf87145a1fd7",
                    "riotId": "JINX",
                    "name": "JINX"
                }
            ]
        }
    }
    ```

    - **matchId**: `UUID` → The unique identifier of the match.
    - **winningTeam**: `Team` → The team that won the match: see `Team` in [Match Response](#get-match) for full details of the response.
    - **losingTeam**: `Team` → The team that lost the match: see `Team` in [Match Response](#get-match) for full details of the response.

### Get Live Client Match

- **HTTP Method**: `POST`
- **Path**: `/api/game/lol/match/live`
- **Description**: Retrieve information about a live match based on the team composition provided.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Body**:

    ```json
    {
        "teamOrder": ["SummonerName#SMTH", "SummonerName2#SMTH"],
        "teamChaos": ["SummonerName4#SMTH", "SummonerName3#SMTH"],
    }
    ```

    - **teamOrder**: `List<String>` - *required* → The list of player's riot Ids in Team Order (found in riot live client API, see [Live Client Data API](https://developer.riotgames.com/docs/lol#game-client-api_live-client-data-api) for more information).
    - **teamChaos**: `List<String>` - *required* → The list of player's riots Id in Team Chaos (found in riot live client API, see [Live Client Data API](https://developer.riotgames.com/docs/lol#game-client-api_live-client-data-api) for more information).

- **Response**:
    - `200 OK` → If the live match information is successfully retrieved, the response body with status `OK` is returned.
    - `400 Bad Request` → If the request body is invalid or required fields are missing.
    - `403 Forbidden` → If an invalid or no API key was provided. 
    - `404 Not Found` → If the match is not found.

- **Response Body**:

    ```json
    {
        "matchId": "5139aa1c-8c74-41a9-86f4-468579948493"
    }
    ```

    - **matchId**: `UUID` → The unique identifier of the match.

### Get Champion Pool

- **HTTP Method**: `GET`
- **Path**: `/api/game/lol/team/champion-pool`
- **Description**: Retrieve the champion pool for a specific team.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Params**:
    - **teamId**: `UUID` - *required* → The unique identifier of the team whose champion pool is being retrieved.

- **Response**:
    - `200 OK` → If the champion pool is successfully retrieved, the response body with status `OK` is returned.
    - `400 Bad Request` → If the request params are invalid or required fields are missing.
    - `403 Forbidden` → If an invalid or no API key was provided. 
    - `404 Not Found` → If the team or bot is not found.

- **Response Body**:

    ```json
    {
        "champions": [
            {
                "id": "b6a0e7db-777d-4421-9efb-c9aebdb4f179",
                "riotId": "AHRI",
                "name": "AHRI"
            },
            {
                "id": "f7227588-f42b-4bfc-a12f-96dbd8a9359d",
                "riotId": "AATROX",
                "name": "AATROX"
            }
            ...
        ]
    }
    ```

    - `Set<Champion>`:
        - **id**: `UUID` → The unique identifier of the champion.
        - **riotId**: `String` → The Riot ID of the champion.
        - **name**: `String` → The name of the champion.

### Generate Champion Pool

- **HTTP Method**: `POST`
- **Path**: `/api/game/lol/team/champion-pool`
- **Description**: Generate a champion pool for a specific team.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Body**:

    ```json
    {
        "teamId": "a1234567-b89c-4d7f-8888-xyz123456789"
    }
    ```

    - **teamId**: `UUID` - *required* → The unique identifier of the team for which the champion pool is being generated.

- **Response**:
    - `200 OK` → If the champion pool is successfully generated, the response body with status `OK` is returned.
    - `400 Bad Request` → If the request body is invalid or required fields are missing.
    - `403 Forbidden` → If an invalid or no API key was provided. 
    - `404 Not Found` → If the team is not found.

- **Response Body**:

    ```json
    {
        "champions": [
            {
                "id": "b6a0e7db-777d-4421-9efb-c9aebdb4f179",
                "riotId": "AHRI",
                "name": "AHRI"
            },
            {
                "id": "f7227588-f42b-4bfc-a12f-96dbd8a9359d",
                "riotId": "AATROX",
                "name": "AATROX"
            }
            ...
        ]
    }
    ```

    - See [Champion Pool Response](#get-champion-pool) for full details of the response.

### Update Live Stats

- **HTTP Method**: `POST`
- **Path**: `/api/game/lol/player/stats/live`
- **Description**: Update live player stats during an ongoing match.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Body**:

    ```json
    {
        "matchId": "5139aa1c-8c74-41a9-86f4-468579948493",
        "playerStats": [
            {
                "riotId": "SummonerName#SMTH",
                "champion": "Ahri",
                "kills": 5,
                "assists": 3,
                "deaths": 2,
                "creepScore": 150
            },
            {
                "riotId": "SummonerName2#SMTH",
                "champion": "LeeSin",
                "kills": 3,
                "assists": 5,
                "deaths": 4,
                "creepScore": 120
            }
        ]
    }
    ```

    - **matchId**: `UUID` - *required* → The unique identifier of the match.
    - **playerStats**: `List<LivePlayerStats>` - *required* → A list of live player stats updates for each player in the match:
        - `LivePlayerStats`:
            - **riotId**: `str` - *required* → The Riot ID of the player updating the stats.
            - **champion**: `str` - *required* → The name of the champion being played by the player.
            - **kills**: `int` - *required* → The number of kills the player has achieved.
            - **assists**: `int` - *required* → The number of assists the player has made.
            - **deaths**: `int` - *required* → The number of deaths the player has experienced.
            - **creepScore**: `int` - *required* → The player's creep score.

- **Response**:
    - `200 OK` → If the live stats are successfully updated, the empty body with status `OK` is returned.
    - `400 Bad Request` → If the request body is invalid, required fields are missing, match is in `ENDED` state, or the players are not a part of the match.
    - `403 Forbidden` → If an invalid or no API key was provided. 
    - `404 Not Found` → If the match, champion or players are not found.

### Get Player Stats

- **HTTP Method**: `GET`
- **Path**: `/api/game/lol/player/stats`
- **Description**: Retrieve the stats of a player in a specific match.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Params**:
    - **playerId**: `UUID` - *required* → The unique identifier of the player.
    - **matchId**: `UUID` - *required* → The unique identifier of the match.

- **Response**:
    - `200 OK` → If the player stats are successfully retrieved, the response body with status `OK` is returned.
    - `400 Bad Request` → If any of the required parameters are missing or invalid.
    - `403 Forbidden` → If an invalid or no API key was provided. 
    - `404 Not Found` → If the player, or match is not found.

- **Response Body**:

    ```json
    {
        "champion": {
            "id": "e3a4a11d-f9b9-4b61-9127-c9ad6c9d338a",
            "name": "AHRI",
            "riotId" : "AHRI"
        },
        "eloGain": 15,
        "kills": 5,
        "assists": 3,
        "deaths": 2,
        "creepScore": 150
    }
    ```

    - **champion**: `Champion` → The champion the player used in the match. If the champion was not recorded, this will be `null`.
        - **id**: `UUID` - The unique identifier of the champion.
        - **name**: `str` - The name of the champion.
        - **title**: `str` - The title of the champion.
    - **eloGain**: `int` → The Elo gain the player achieved after the match.
    - **kills**: `int` → The number of kills the player achieved.
    - **assists**: `int` → The number of assists the player achieved.
    - **deaths**: `int` → The number of deaths the player experienced.
    - **creepScore**: `int`  → The player's creep score (number of minions and monsters they have last-hit).

- **Response**:
    - `200 OK` → If the request is successful, returns the player stats in the response body.
    - `400 Bad Request` → If any required parameter is missing or invalid.
    - `404 Not Found` → If the player, match, or bot is not found.

# Configuration

In this section, we will cover how to configure the various parts of BotCore and its services for smooth integration.

## Music Service Configuration
