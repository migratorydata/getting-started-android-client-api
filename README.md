### Getting started with MigratoryData Android Client API V6

#### Prerequisites

> Android Studio 2021+

#### Clone Project

Clone the getting started project from GitHub using Android Studio or using the following command:
> git clone https://github.com/migratorydata/getting-started-android-client-api.git

#### Start MigratoryData server using docker

If you don’t have a MigratoryData server installed on your machine but there is docker installed you can run the following command 
to start MigratoryData server, otherwise you can download and install the latest version for your os from [here](https://migratorydata.com/downloads/migratorydata-6/]).

```bash
docker pull migratorydata/server:latest
docker run -d --name my_migratorydata -p 8800:8800 migratorydata/server:latest
```

#### Configure

In this example the MigratoryData Client API is set to connect to the MigratoryData server located at address localhost:8800 and subscribe to the subject /server/status.
Update the code snippet from the file com.migratorydata.androidexampleapplication.Config of your project.

#### Build & Run

Compile and run your project using Android Studio.

#### Documentation

For more information about the MigratoryData Client API for Android, please refer to the official documentation available at [MigratoryData Documentation](https://migratorydata.com/docs/client-api/android/).