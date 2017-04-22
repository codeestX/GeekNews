package com.codeest.geeknews.di.module;

import com.codeest.geeknews.app.App;
import com.codeest.geeknews.model.DataManager;
import com.codeest.geeknews.model.db.DBHelper;
import com.codeest.geeknews.model.db.RealmHelper;
import com.codeest.geeknews.model.http.HttpHelper;
import com.codeest.geeknews.model.http.RetrofitHelper;
import com.codeest.geeknews.model.prefs.ImplPreferencesHelper;
import com.codeest.geeknews.model.prefs.PreferencesHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by codeest on 16/8/7.
 */

@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }

    @Provides
    @Singleton
    DBHelper provideDBHelper(RealmHelper realmHelper) {
        return realmHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(ImplPreferencesHelper implPreferencesHelper) {
        return implPreferencesHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper, DBHelper DBHelper, PreferencesHelper preferencesHelper) {
        return new DataManager(httpHelper, DBHelper, preferencesHelper);
    }
}
