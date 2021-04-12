# Vertretungsfilter

The "Vertretungsfilter" takes school substitution plan raw data and presents it to each student in a customized view. The student no longer has to search for the entries that relate to his courses, but only has the "Vertretungsfilter" show him the information that is of interest to him. Optionally, it is also possible to activate e-mail notifications, so that a view of the substitution plan is completely taken over by notifications.

## Setup

The "Vertretungsfilter" is made with the [Google GWT framework](http://www.gwtproject.org/). Prior to running the app locally you need to [install the GWT SDK](http://www.gwtproject.org/download.html).

In the class *VertretungsalarmService.java* which can be found under [/src/de/zunk/vertretungsalarm/server](https://github.com/zunkelty/vertretungsfilter/blob/main/src/de/zunk/vertretungsalarm/server/VertretungsalarmService.java) you need to insert:

1. The link to your substitude plan
2. The username for the plan that is able to access all available information
3. The password for the username

## Build

To build a *.war*-file that can be deployed on your server you need to use the GWT build tools that are built into your IDE (e.g. Eclipse) when you install the GWT SDK (see *Setup*).

## Deploy
When you build the GWT application you get a *.war*-file that you can deploy on Tomcat. Setup a Tomcat server and then upload the *.war*-file.

## License
[GNU Affero General Public License v3.0](https://choosealicense.com/licenses/agpl-3.0/)
