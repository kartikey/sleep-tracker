# Self-Tracker
In an world increasingly driven by data... people increasingly seek to gather quantifiable information about their own lives. From steps taken or calories consumed, to hours spent in REM or the number of times they've listened to their favorite song, products and applications for letting people assign numbers to their lives are exploding in popularity. Systems to support personal information tracking (also known as ["personal informatics"](http://dl.acm.org/citation.cfm?id=1753409), or ["the quantified self"](http://nyti.ms/18XZ1Zv)) are a natural fit for mobile phones.

For this assignment, you will build an Android app that will allow the user to record observations about a _single aspect_ of their own lives, allowing them to track their own behavior and engaging in self-improvement. You can pick any single aspect you want to track: for example: number of cups off coffee consumed, number of dogs cuddled, number of hours spent on the bus (one of your professor's earliest Android projects was tracking ["environmentally sustainable behaviors performed"](...)). Ideally, this aspect should be _quantifiable_ in some way--that is, each "event" recorded (e.g., "I just cuddled two dogs!") should have a number associated with it, even if that number is almost always "1".


### Objectives
By completing this challenge you will practice and master the following skills:
* Using fragments to implement a multi-pane Activity
* Dynamically changing displayed fragments
* Managing a history of screens and tasks in an app
* Creating and displaying pop-up dialogs
* Storing and retrieving data using SQLite


## User Stories
The user stories for the Self-Tracker app are:
* As a user, I want to record an event that occurs in  my life.
* As a user, I want to view events that I have previously recorded.
* As a user, I want to browse a history of observations so that I can make inferences about my life.
* As a user, I want to see a summary of my observations so that I can quickly describe my behavior.


## Implementation Details
For this assignment you will be creating a slightly larger app than the last. It has a few more moving pieces, but about the same number of overall concepts.


### Fork and Create
As with all assignments, you should start by **forking** and **cloning** this repository. It includes any starter code plus the `SUBMISSION.md` file you will need to complete.

You will need to create a new project through Android Studio. Make sure to name your project **selftracker**, so that the package is `edu.uw.uwnetid.selftracker`. _You will need to save the project inside your cloned repo!_ (i.e., in the top-level directory).

Once again, target API 15 (4.0.3 Ice Cream Sandwich) as your minimum SDK. Your application will contain a single Activity (though many fragments); you should start with a single empty Activity. Once you've created the app, go into the `build.gradle` file (Module level) and set the target SDK to be **21**.


### Appearance and Interaction
Your application will be made up of four (4) different "views". (You can almost think of these as screens, but you'll be using fragments to mix and match them on a single screen):

1. A "master" list of observations that have been made. This should be a scrollable list (e.g., using a `ListView` or a `GridView`). The list items themselves can just be short text descriptions, or you can include more complex layouts. The observations should be sorted in _reverse chronological order_.
  - This view should also offer a way to record an event (e.g., an "add event" button).

2. A "detail" view for a particular observation. Each observation should include at least the following elements:
  - A **quantity** associated with the observation. For example, number of cups of coffee or hours slept.
  - A **description or comment** to give more context to the observation. E.g., "2 cups, but half-decalf!" This description can also be a "title" for the observation if appropriate. Basically you need one text field in addition to the quantity.
  - The **time** that the observation was recorded.

  You can lay these items out however you want. I suggest making the _quantity_ stand out (e.g., a large font). You can also include additional fields as you feel are approprate; just don't get carried away!

3. A view for "recording" an observation. This view should provide a way for the user to enter the quantity and description. You should add the timestamp automatically when the user creates the observation; the idea is that the user enters an observation right after the event (the simplest versoin), though you can let add the ability for the user to specify the time of the event if you wish.
  - After the user records an observation, use a [Toast](http://developer.android.com/guide/topics/ui/notifiers/toasts.html) to let them know that the information has been saved!

4. Finally, include a view that displays some kind of "summary statistic" about the events recorded so far. This can be as simple as the total _quantity_ recorded so far, or more complex like the average quantity over the past week. This screen should let the user get some kind of inference about their recorded behavior.

#### Muli-Pane Layout
This application will be designed (optimized?) for screens in a __landscape__ orientation, as well as for larger devices such as tablets. However, we will be using [Fragments](http://developer.android.com/training/basics/fragments/index.html) to make it easy to support __portrait__-oriented devices as well (and you can add this functionality as an extra-credit extension).

- **Pro tip**: You can switch the emulator into _landscape mode_ by hitting `ctrl-F11`, You can also specify that you want the emulator to launch in landsape mode by editing the virtual device configuration (`Tools > Android > AVD Manager` in Android Studio)

In __landscape__ orientation, your app will use a "two-pane" layout: that is, it will show two Fragments (views) side by side. I'll refer to these as the "left pane" and the "right pane". These panes should support the following interaction:

- When the app starts, the app should show the "summary" view in the _left pane_, and the "master list" view in the _right pane_ (the user can see a summary of their behavior and the option to browse for more details).

- When the user clicks on any of the items in the "master list" view, the list should move to the _left pane_ and the _right pane_ should show the "details" view for the selected observation.
  - Use [FragmentTransactions](http://developer.android.com/guide/components/fragments.html#Transactions) to change what fragments are displayed. Sliding animations are bonus. Note that you should _not_ create a new "list" fragment when you do this; just move the existing one around.
    <!-- Note about saving state? -->

  - The user should be able to use the [_up navigation_](http://developer.android.com/training/implementing-navigation/ancestral.html) (either clicking the "back" button or using the back icon in the [ActionBar](http://developer.android.com/guide/topics/ui/actionbar.html)) to return to the "list and summary" fragment layout.

- The user should be able to click the "add event" button on the "list" view to create a [**popup dialog**](http://developer.android.com/guide/topics/ui/dialogs.html) that shows the "recording" view. Note that you should use a [DialogFragment](http://developer.android.com/guide/topics/ui/dialogs.html#DialogFragment) for this, so that your layouts remain flexible (e.g., if you decide you don't want to use a dialog on some configurations).

  - After adding the event, the user should be shown the "list + detail" layout (list on the _left_, detail on the _right_), with the detail for the newly recorded event shown. Again, using up navigation from here should return to the "list and summary" layout.

Overall, the navigation should be _intuitive_ and fluid. The user should be able to complete all the user stories easily, without getting lost in the application!


### Data Storage
Your data tracking app will need to **persist** recorded observations between sessions---the whole point is that the user can record observations and study their own behavior over time!

Android offers a number of different [data storage](http://developer.android.com/guide/topics/data/data-storage.html) options. However, because we want to save complex structured data (rather than just some simple primitives), we'll want to use an [SQLite database](http://developer.android.com/guide/topics/data/data-storage.html#db). Android comes with support for creating and querying SQLite databases that are private to each app. [SQLite](https://en.wikipedia.org/wiki/SQLite) is an incredibly simple [relational database](https://en.wikipedia.org/wiki/Relational_database_management_system), which is small and lightweight enough to work on mobile devices.

  - If you've taken INFO 340 or have otherwise worked with a database such as `MySQL`, this interaction will seem familiar. If you've never worked with a database, the simplest explanation is to think of them as a spreadsheet (like an Excel file) where you manipulate _rows_ of data given a set of pre-defined _columns_. [SQL](https://en.wikipedia.org/wiki/SQL) (Structured Query Language) is its own command language for working with these spreadsheets; I've included examples of the kinds of queries you'll need to make below. The full spec for SQLite's version of SQL can be found [here](http://sqlite.org/lang.html).

As explained in the [documentation](http://developer.android.com/training/basics/data-storage/databases.html) (**read this!**), in order to work with an SQLite database you should create an `abstract` inner **Contract** class to store column types, and subclass `SQLightOpenHelper` to be able to interact with the database. You can then instantiate this helper class and call `.getWritableDatbase()` on it to get access to the database for reading and writing.
- Your new subclass should create the database in the `onCreate()` method. You can do this by executing the an SQL [`CREATE TABLE`](http://sqlite.org/lang_createtable.html) query. This query creates the "columns" of your spreadsheet; you need to give them names and types. For example:

  ```sql
  CREATE TABLE people (name TEXT, age INTEGER);
  ```

  creates a new table (spreadsheet) called "people" with two columns: "name" that holds text, and "age" that holds an integer.

  - As mentioned in the documentation, it's a good idea to include a "primary key" (think an id that is unique for each row, making it easy to look them up). You make a primary key called `_id` that automatically counts up by declaring a row as `_id INTEGER PRIMARY KEY AUTOINCREMENT`. _You must include this `_id` field for your database to work easily with a ListView!_

  - SQLite doesn't support `DATE` or `TIME` as a separate type, so you'll have to store the datetime as either an `INTEGER` or `TEXT`. If you use `TIMESTAMP DEFAULT CURRENT_TIMESTAMP` as the type, then the "time" will default to a text string describing the time (in UTC time, _not_ local time), even if you don't try to specifically insert a time! You can then use the [`Calendar`](http://developer.android.com/reference/java/util/Calendar.html) and [`DateUtils`](http://developer.android.com/reference/android/text/format/DateUtils.html) classes to convert this to local time.
    - Dates and times in Java kind of suck, but are good practice for working with objects and data!

- You can add new values to by using the `INSERT` SQL statement along with <a href="http://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html#execSQL(java.lang.String)">`.execSQL()`</a>, but it's usually easier and cleaner to use the <a href="http://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html#insert(java.lang.String, java.lang.String, android.content.ContentValues)">`.insert()`</a> method. This takes in a [`ContentValues`](http://developer.android.com/reference/android/content/ContentValues.html) object, which is basically a HashMap you can store values in
  - Remember that if you declared `DEFAULT` values for any of your columns (e.g., the primary key or the timestamp) you don't need to insert values into the table.

- **Helpful hint:** You can directly explore the SQLite database that is on your device by using `adb` and the `sqlite3` tool. See [this link](http://developer.android.com/tools/help/sqlite3.html) for instruction. Note that you can run these commands directly from Android Studio by using the "Terminal" tab found at the very bottom (just below the Logcat window).
  - If you want to delete and recreate your database, you can run `DROP TABLE IF EXISTS tablename`, either from within the sqlite3 tool or as an `execSQL()` call in your Activity.

- Finally, you can read from the database using the <a href="http://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html#query(boolean, java.lang.String, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String, java.lang.String, java.lang.String, java.lang.String)">`.query()`</a> method. This method takes _a lot_ of parameters, which you basically use to "fill in the blanks" in an SQL query. See the method description for details. Alternatively, you can just write a raw SQL statement (e.g., `SELECT * FROM tablename` to get all of the data from your table), and execute that with the <a href="http://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html#rawQuery(java.lang.String, java.lang.String[])">`.rawQuery()`</a> method. Note that you should use `?` arguments rather than directly passing in user-entered Strings to avoid [SQL Injection attacks](https://en.wikipedia.org/wiki/SQL_injection).

  - Your can sort the returned elements in descending order by specifying `ORDER BY fieldname DESCR` in your query. This is the (\*counts\*) **7th** parameter in the `.query()` method&mdash;just leave off the words `"ORDER BY`.

  - Either method you use will return a [`Cursor`](http://developer.android.com/reference/android/database/Cursor.html). This is a lot like an Iterator in Java; it keeps track of where you are in a list (e.g., what `i` we'd be on in a loop), and then provides methods that let us fetch values from the object at that spot in the list. You can then call methods to move around the list (e.g., to move to the next item). For example:
    ```java
    cursor.moveToFront(); //move to the first item
    String field0 = cursor.getString(0); //get the first field (column you specified) as a String
    String name = cursor.getString(cursor.getColumnIndexOrThrow("name")); //get the "name" field as a String
    cursor.moveToNext(); //go to the next item
    ```

- The nice thing about `Cursors` though is that they can easily be fed into `AdapterViews` by using a [`CursorAdapter`](http://developer.android.com/reference/android/widget/CursorAdapter.html) (as opposed to the `ArrayAdapter` we've used previously). I recommend you use a [**`SimpleCursorAdapter`**](http://developer.android.com/reference/android/widget/SimpleCursorAdapter.html), as this abstracts out a lot of the detail and makes it almost as easy to use an an `ArrayAdapter`.

  - You instantiate a new `SimpleCursorAdapter`, passing it a `Context`, a layout resource to inflate, a `Cursor`, an array of column names to fetch from each entry in the Cursor, and a matching list of view resource ids (which should all be `TextViews`) to assign each column to. Then you can set this adapter just like you did with `ArrayAdapter` in the last assignment.

  - **Important Note** One of the constructors for `SimpleCursorAdapter` has been _deprecated_ because it runs on the **UI Thread**. So you'll need to also pass in `CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER` as the final parameter. Our database work will still all run on the UI Thread, but since we're not doing anything complex or updating the database frequently, things _should_ be okay. The preferred solution is to use a [ContentProvider](http://developer.android.com/guide/topics/providers/content-providers.html) and a [Loader](http://developer.android.com/guide/components/loaders.html), which act as wrappes around `ASyncTask`; however, these are larger, more complex topics that we'll get to later to "fix" our application. If I have time to produce drop-in code for you I will, but I'm not optimistic...

  - When you add a new item to your database, you'll want to tell the `SimpleCursorAdapter` to update by calling `adapter.notifyDataSetChanged()`. This is where that UI Thread work occurs!

This should let you be able to store observations across sessions, allowing the user to track their behavior!


## Extra Feature: Portrait Mode
By developing your application using Fragments, it becomes very easy to [re-use those layouts](http://developer.android.com/training/multiscreen/adaptui.html) and interactions across different device configurations. As an extra feature, add support for using the app in **portrait mode**--that is, with the screen taller than it is wide.

  - You will need to specify an alternative "root" layout for portrait mode&mdash;one that just has a single pane. You can use the `port` config keyword to name the folder containing these resources.

In **portrait mode**, the app should be displayed with a single pane (e.g., one fragment per Activity, though you can use a single Activity and just swap out the fragments as that can be a bit easier). Your app should support the following interaction:

  - The app should start the user on the "master list" view. Clicking on any of the items in the list view should take the user to the "details" view screen for that item. The user should be able to use up navigation to return to the master list.

  - From the "master list", the user should be able to click an "add event" button. This should take the user to the "recording" view. Once the user has recorded an observation, the user should be taken to the "details" view for that observation. From there the user should be able to use up navigation (e.g., from the `ActionBar`) to return to the "master list".

    - This is different than the pop-up dialog used in the landscape mode!

  - From the "master list", the user should also be able to click on a "see summary" button. This should take the user to the "summary" view (and again, the user can navigate back to the list). Note that this button is not present in landscape mode; think about how you can best structure and re-use your layouts!

Again, the goal is for the app usage "flow" to be as smooth and intuitive as possible.


## Submit Your Solution
In order to submit programming assignments in this class, you will need to both `push` your completed program to your GitHub repository (the one in the cloud that you created by forking), and submit a link to your repository to [Canvas](https://canvas.uw.edu/) (so that we know where to find your work)!

Before you submit your assignment, double-check the following:

* Test that your app builds (from `gradle`!), installs, and works without errors. It should fulfill all the user stories.
* Fill out the `SUBMISSION.md`included in the assignment directory, answering the questions.
* Commit the final version of your work, and push your code to your GitHub repository.

Submit a a link to your GitHub repository via [this canvas page](https://canvas.uw.edu/courses/1023396/assignments/3082083).

The assignment is due on **Tue Jan 26 at 6:00 AM**.

### Grading Rubric
See the assignment page on Canvas for the grading rubric.
