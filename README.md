# Blahajbot

## Introduction

A discord bot themed after everyone's favorite swedish shark. It is built to simplify the scheduling of automatic messages, with a web-based dashboard for easy configuration.

In the future, more features to expand the utility of the bot are planned such as:
* Social Media Integration
* Ticket Creation and Storage
* Automatic Embeds
* Role Menus
* etc.

## Stack:
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
Once deployed onto a server, the bot can be configured either through the web dashboard, or through slash commands in discord.
Enabling or disabling features currently requires the web dashboard.
### Using Slash Commands
#### Scheduled Jobs
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

### Using Web Dashboard

#### Scheduled Jobs

## Selfhosting

[Cron Info Link]: https://www.ibm.com/docs/en/db2oc?topic=task-unix-cron-format
