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
    SPOTIFY_CLIENT_ID=
    SPOTIFY_CLIENT_SECRET=
    SPOTIFY_SAVE_PATH=
    SPOTIFY_TEMP_SAVE_PATH=

    SERVICE_PORT=
    ```

- **`POSTGRES_USER`**:
    - The username for connecting to the PostgreSQL database.
    - **Example**: `mydbuser`.

- **`POSTGRES_PASSWORD`**:
    - The password for connecting to the PostgreSQL database.
    - **Example**: `randompassword`.

- **`YOUTUBE_API_KEY`**:
    - The API key for interting with the YoutubeAPI,
    - You can see how to generate it [here](https://developers.google.com/youtube/v3/getting-started).

- **`YOUTUBE_SAVE_PATH`**:
    - The directory where Youtube song files will be saved.
    - This path should be an `absolute path` to a directory on your host machine.
    - **Example**: `/path/to/youtube/videos`.

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

- **`SERVICE_PORT`**:
    - The port that your service will listen to.
    - The default is `8080`, but you can customize it.

## 3. Build and Run **BotCore**

- Note that this might take a while if it is the first time you are running it.

### **Linux/macOS**

- In the root directory of the project, run the following commands to load the environment variables, build the Docker image (if not already built), and start the framework. 
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

- 1. Pull the latest changes from the repository

    ```bash
    git pull origin main
    ```

- 2. Rebuild the Docker images to apply the updates:

    ```bash
    docker compose up --build
    ```

# **Features**

- This section outlines the key features and functionalities of the BotCore framework. BotCore is designed to facilitate the integration of various services such as game management, music streaming, and bot management, providing a seamless experience for developers and users alike.
- Below are some of the core features.

## **Music Features**

### **Playlist Management**

- **Playlist Management**: Create, modify, and delete playlists for Discord servers. Manage the order of songs and ensure easy retrieval for playback.
- **Song Management**: Add, remove, and update songs in playlists. BotCore allows you to manage song metadata and keep your playlists fresh by updating their content when necessary.

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
- **Performance Monitoring**: Monitor player performance metrics during gameplay.

## **Security Features**

### **Bot Management**
- **Bot Creation**: Easily create a new bot by providing the necessary details.

### **API Key Management**
- **Generate API Key**: Allow the creation of API keys for authentication and authorization when interacting with the bot services.

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
    - `400 Bad Request` → If an invalid request body was received.
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

The Music API provides functionality for managing playlists, adding and removing songs, and retrieving song metadata. These endpoints enable seamless integration with music services, allowing users to organize and interact with their favorite tracks.
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

### Get Song by Title

- **HTTP Method**: `GET`
- **Path**: `/api/music/metadata/title`
- **Description**: Retrieve a song's metadata by its title.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Parameters**:
    - **title**: `str` - *required* → The title of the song.
    - **platform**: `str` - *optional* → The platform to search on, see [Platform](#platforms) for more information. If not platform is provided, the default one will be used.
  
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

- **Response Fields**:
    - See [Song Metadata Response](#get-song-metadata-by-id) for the full response details.

### Get Songs By Playlist

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
    - `List[Song]` →  A list of songs within the playlist, see [Song Response](#get-song-metadata-by-id) for the full response details.

### Get Songs By Album

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
    - `List[Song]` → A list of songs within the playlist, see [Song Response](#get-song-metadata-by-id) for the full response details.

### Get Audio File By ID

- **HTTP Method**: `GET`
- **Path**: `/api/music/audio/id`
- **Description**: Retrieve an audio file by its unique song ID, YouTube ID, or Spotify ID.

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
    - **platform**: `str` - *optional* - The platform from which the song should be retrieved, see [Platform](#platforms) for more information. If not platform is provided, the default one will be used.

- **Response**:
    - `200 OK` → If the audio file is found, it is returned in the response.
    - `400 Bad Request` → If `title` was not provided.
    - `403 Forbidden` → If an invalid or no API key is provided.
    - `404 Not Found` → If the audio file cannot be found.

- **Response Body**:
    - The response will contain the raw audio file (e.g., an MP3 file). 
    - You should handle the response as a binary stream, with `Content-Type: audio/mpeg` indicating the format of the file.

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

### Download Audio By ID

- **HTTP Method**: `POST`
- **Path**: `/api/music/audio/download/id`
- **Description**: Request the download of an audio file by its unique song ID or external ID.

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

    - `Download` → See [Download Response](#get-download) to see for the full response details.

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

    - `Download` → See [Download Response](#get-download) to see for the full response details.

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
        }
    ]
    ```

    - `List[Download]` → See [Download Response](#get-download) to see for the full response details.

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
    - **listenersDiscordIds**: `List[str]` - *required* → The list the unique identifiers of the users listening to the stream.

- **Response**:
    - `200 OK` → If the listeners were successfully added, the response body with status OK is returned.
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
    - **listenersDiscordIds**: `List[str]`  → The list the unique identifiers of listeners of the stream.
    

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
    - **guildDiscordId**: `str` - *required* → The unique identifier of the guild(server) in which the reaction will be recorded.
    - **userDiscordId**: `str` - *required* → The Discord unique identifier of the user that the reaction belongs to.
    - **type**: `str` - *required* → The type of reaction. Possible values:
        - `LIKE` → The user liked the song.
        - `DISLIKE` → The user disliked the song.

- **Response**:
    - `200 OK` → If the reaction was successfully added, the response body with status OK is returned.
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
    - **guildDiscordId**: `str` → The unique identifier of the guild (server) where the reaction was recorded.  
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
    - `200 OK` → If the song was successfully found, the response body with status OK is returned.
    - `400 Bad Request` → If `songId` and `guildDiscordId` were not provided.
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
    - `200 OK` → If the stream was successfully recorded, the response body with status OK is returned.
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
    - **guildDiscordId**: `str` → The unique identifier of the guild (server) where the song was streamed.  
    - **requesterDiscordId**: `str` → The Discord unique identifier of the user who requested the song.  
    - **requestedAt**: `ISO 8601 datetime` → The timestamp indicating when the song was requested to be streamed.

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
    - `200 OK` → The engagement of the song was successfully found, the response body with status OK is returned.
    - `400 Bad Request` → If `songId` and `guildDiscordId` were not provided.
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
    - **listenersCount**: `long` → The total number of listens the song has received

### Get Playlist

- **HTTP Method**: `GET`
- **Path**: `/api/metadata/playlist`
- **Description**: Retrieve a specific playlist by its ID, title and guild Discord ID.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Parameters**:
    - **playlistId**: `UUID`- *optional* : The unique identifier of the playlist.
    - **title**: `str`- *optional* : The title of the playlist.
    - **guildDiscordId**: `str`- *optional* : The Discord ID of the guild.
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
    - **ownerDiscordId**: `str` → The Discord ID of the playlist owner.
    - **guildDiscordId**: `str` → The Discord ID of the guild to which the playlist belongs to.
    - **songs**: `List[PlaylistSong]` → A list of playlist songs having the following format:
        - **song**: `Song` → see [Song Response](#get-song-metadata-by-id) for the full response details.
        - **position**: `int` → The position of the song in the playlist.

### Get Playlists by Guild Discord ID

- **HTTP Method**: `GET`
- **Path**: `/api/music/playlist/guild`
- **Description**: Retrieve all playlists associated with a specific guild by providing the Discord ID of the guild.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Parameters**:
    - **guildDiscordId**: `str` - *required* → The unique Discord ID of the guild.

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
- **Description**: Create a new playlist by providing the playlist details, including the title, owner Discord ID, bot ID, and guild Discord ID.

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
    - **ownerDiscordId**: `str` - *required* → The Discord ID of the owner of the playlist.
    - **guildDiscordId**: `str` - *required* → The Discord ID of the guild where the playlist will be created.

- **Response**:
    - `200 OK` → If the song playlist was successfully created, the response body with status `OK` is returned.
    - `400 Bad Request` → If an invalid request body is provided.
    - `403 Forbidden` → If an invalid or no API key is provided.

- **Response Body**:
    - `Playlist`: See [Playlist Response](#get-playlist) for the full response details.

### Add Song To Playlist By Id

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
    - **requesterDiscordId**: `string` - *required* → The Discord ID of the person requesting the addition of songs.
    - **songIds**: `Array[UUID]` - *optional* → A list of the unique identifiers for the songs being added to the playlist.
    - **songExternalIds**: `Array[string]` - *optional* → A list of external Ids for the songs.
    - **platform**: `str` - *optional* → The platform from which the songs will be added, see [Platform](#platforms) for more information.
    - **Note**: Either the `songIds` or `songExternalIds` along with the `platform` must be provided.

- **Response**:
    - `200 OK` → If the song was added successfully, the response body with status `OK` is returned.
    - `400 Bad Request` → If an invalid request body is provided.
    - `403 Forbidden` → If the requester is not the owner of the playlist / an invalid or no API key was provided.
    - `404 Not Found` → The playlist/song was not found.

- **Response Body**:
    - `Playlist`: See [Playlist Response](#get-playlist) for the full response details.

### Add Song To Playlist By Title

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
    - **requesterDiscordId**: `string` - *required* → The Discord ID of the person requesting the addition of songs.
    - **songTitles**: `Array[string]` - *optional* → A list of the titles of the songs being added to the playlist.
    - **platform**: `string` - *optional* → The platform from which the songs will be added, see [Platform](#platforms) for more information. If no platform is provided, the default one is used.

- **Response**:
    - `200 OK` → If the song was added successfully, the response body with status `OK` is returned.
    - `400 Bad Request` → If an invalid request body is provided.
    - `403 Forbidden` → If the requester is not the owner of the playlist / an invalid or no API key was provided.
    - `404 Not Found` → The playlist/song was not found.

- **Response Body**:
    - `Playlist`: See [Playlist Response](#get-playlist) for the full response details.


### Remove Song From Playlist

- **HTTP Method**: `DELETE`
- **Path**: `/api/music/playlist/song`
- **Description**: Remove a specific song from a playlist by either song ID or position within the playlist.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Parameters**:
    - **playlistId**: `UUID` - *required* → The unique identifier of the playlist where the songs will be added.
    - **requesterDiscordId**: `string` - *required* → The Discord ID of the user requesting the removal.
    - **songId**: `UUID` - *optional* → The unique identifier of the song to be removed from the playlist.
    - **position**: `int` - *optional* → The position of the song to be removed from the playlist.
    - **Note**: Either the `songId` or `position` must be provided.

- **Response**:
    - `200 OK` → If the song is successfully removed, the response body with status `OK` is returned.
    - `400 Bad Request` → If `playlistId`, `requesterDiscordId` and `songId` or `position` were not provided.
    - `403 Forbidden` → If the requester is not the owner of the playlist / an invalid or no API key was provided.
    - `404 Not Found` → The playlist/song was not found.

- **Response Body**:
    - [Playlist]: See [Playlist Response](#get-playlist) for the full response details.


### Delete Playlist

- **HTTP Method**: `DELETE`
- **Path**: `/api/music/playlist`
- **Description**: Delete a specific playlist by its ID.

- **Request Headers**:
    - **X-API-KEY**: `str` → The API key to authenticate the request.

- **Request Parameters**:
    - **playlistId**: `UUID`- *required* → The unique identifier of the playlist to be deleted.
    - **requesterDiscordId**: `str` - *required* → The Discord ID of the user requesting the deletion of the playlist.

- **Response**:
    - `200 OK` → If the playlist deletion is successful, an empty response body with status `OK` is returned.
    - `400 Bad Request` → If `playlistId` and `requesterDiscordId` were not provided.
    - `403 Forbidden` → If the requester is not the owner of the playlist / an invalid or no API key was provided.
    - `404 Not Found` → The playlist was not found.
