package com.udemy.sbsapps.whistlr;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

/**
 * Created by michaelmallamo on 9/03/2018.
 */

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // password: tOynRlOU2lOR
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("f423f4016a82fc72617b9b22dc55906a3e5d6e51")
                .clientKey("9dbe5dbede535fb1d45fa88cca9412e0be9c9404")
                .server("http://52.63.93.103:80/parse/")
                .build()
        );
        ParseUser.logOut();
//        ParseUser.enableAutomaticUser();

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
}
