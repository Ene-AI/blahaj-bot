# Blahajbot

## Introduction

A discord bot themed after everyone's favorite swedish shark. It is built to simplify the scheduling of automatic messages, with a web-based dashboard for easy configuration.

In the future, more features to expand the utility of the bot are planned such as:
* Social Media Integration
* Ticket Creation and Storage
* Automatic Embeds
* Role Menus
* etc.

## Table of Contents
1. [Introduction](https://github.com/Ene-AI/blahaj-bot/tree/master#introduction)
2. [Table of Contents](https://github.com/Ene-AI/blahaj-bot/tree/master#table-of-contents)
3. [Tech Stack](https://github.com/Ene-AI/blahaj-bot/tree/master#tech-stack)
4. [Instructions for Use](https://github.com/Ene-AI/blahaj-bot/tree/master#instructions-for-use)
5. [Selfhosting](https://github.com/Ene-AI/blahaj-bot/tree/master#selfhosting)

## Tech Stack:
* Java
* Javascript
* HTML
* Spring Boot
* Spring Security
* Spring Data JPA
* Quartz
* Discord4J
* PostgreSQL
* Vue
* Vuetify
* Vuex
* Vue-Router

## Instructions For Use
Once deployed onto a server, the bot can be configured either through the [web dashboard](https://github.com/Ene-AI/blahaj-bot/tree/master#using-web-dashboard--), or through [slash commands in discord](https://github.com/Ene-AI/blahaj-bot/tree/master#using-slash-commands--).
Enabling or disabling features currently requires the web dashboard.

### Using Slash Commands - 
#### Scheduled Messages:

The list of commands is as follows:
* /listjobs - creates a list of all existing jobs for the server the command is run in
* /schedulejob - Schedules a new message to be sent periodically
  ..-label: The name of the new message
  ..-channel: The channel in which the message will be sent
  ..-message: The contents of the message to be sent
  ..-month, dayOfWeek, dayOfMonth, hour, and minute: These define the date and time the message will be sent with a maximum of sending once a minute. These values are compatible with cron formatting. [Learn more here][Cron Info Link]
* /scheduleonetimejob - Schedules a message to be sent only once. It is deleted from the database after it is sent.
  ..-label: The name of the new message
  ..-channel: The channel in which the message will be sent
  ..-message: The contents of the message to be sent
  ..-month, dayOfMonth, hour, and minute: The date and time to send the message. All values are required.
* /updatejob - Updates the values of an existing job
  ..-id: The id of the job you wish to update. Can be found using /listjobs
  ..-label: The name of the new message
  ..-channel: The channel in which the message will be sent
  ..-message: The contents of the message to be sent
  ..-month, dayOfWeek, dayOfMonth, hour, and minute: These define the date and time the message will be sent with a maximum of sending once a minute. These values are compatible with cron formatting. [Learn more here][Cron Info Link]
* /removejob - Removes a scheduled job from the database
  ..-id: The id of the job you wish to delete. Can be found using /listjobs

### Using Web Dashboard - 

Upon reaching the web dashboard, the first thing you will want to do is login to discord using the link at the top right.

Once you are logged in you should be able to select any of the discord servers you have appropriate permissions for from the dropdown menu at the top of the page.

You will be redirected to the config page, which allows you to toggle the functionality of any of the bot's features. As of writing, this only include the scheduled message functionality. You cam select the wrench icon on any of these features to configure them further.

#### Scheduled Messages:
On this configuration page you will see a list of all the scheduled messages that currently exist. You can use the labeled buttons to Add a new scheduled message, or Edit or Delete and existing message.

The Add and Edit forms are identical, but the Edit form will be prepopulated with the information for the existing message. 

The fields are as follows:
* Channel Id: This denotes the channel that the message will be sent in. Information on finding the channel id can be found [here][Channel ID Info Link]
* label: The name of the new message
* channel: The channel in which the message will be sent
* message: The contents of the message to be sent
* repeat: Choose weather to send a one time message on a specific calendar date and time set using a datetime selector, or to set a repeating message with the following fields
* month, dayOfWeek, dayOfMonth, hour, and minute: These define the date and time the message will be sent with a maximum of sending once a minute. These values are compatible with cron formatting. [Learn more 

## Selfhosting
### Requirements:
```
npm
Maven
Java 21
```
Instructions on creating an app and getting credentials may be found [here][Discord App Setup].

In addition, you will need to add the following url to the bot's "REDIRECT URI(S)" configuration:
* YourBackendUrlHere/login/oauth2/code/discord

Rename anonymized-application.yml to application.yml and make the following changes:

You will need to create a database and user in your PostgreSQL instance and place the credentials for a user that can access the database and the link to the database itself if not using the default in the below locations on lines 3, 4, and 5.
```
        url: jdbc:postgresql://localhost:5432/blahajbot
        username: 
        password: 
```
Place your client id and secret into 

[Cron Info Link]: https://www.ibm.com/docs/en/db2oc?topic=task-unix-cron-format
[Channel ID Info Link]: https://support.discord.com/hc/en-us/articles/206346498-Where-can-I-find-my-User-Server-Message-ID
[Discord App Setup]: https://github.com/reactiflux/discord-irc/wiki/Creating-a-discord-bot-&-getting-a-token
