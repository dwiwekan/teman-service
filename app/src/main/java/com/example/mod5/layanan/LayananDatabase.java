package com.example.mod5.layanan;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Layanan.class,User.class},version = 1)
public abstract class LayananDatabase extends RoomDatabase {

    public abstract LayananDao LayananDao();
    public abstract UserDao UserDao();
    private static LayananDatabase INSTANCE;
    public static LayananDatabase getDbInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),LayananDatabase.class,"DB_NAME")
                    .allowMainThreadQueries()
                    .build();

        }
        return INSTANCE;
    }
//
//    private static LayananDatabase sInstance;
//
//    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();
//    @VisibleForTesting
//    private void setDatabaseCreated(){
//        mIsDatabaseCreated.postValue(true);
//    }
//
//    private void updateDatabaseCreated(final Context context){
//        if(context.getDatabasePath("DB_NAME").exists()){
//            setDatabaseCreated();
//        }
//    }
//
//
//    public static LayananDatabase buildDatabase(final Context context){
//    return Room.databaseBuilder(context,LayananDatabase.class,"DB_NAME")
//            .addCallback(new RoomDatabase.Callback() {
//                @Override
//                public void onCreate(@NonNull SupportSQLiteDatabase db) {
//                    super.onCreate(db);
//                    LayananDatabase database = LayananDatabase.getInstance(context);
//                    database.setDatabaseCreated();
//                }
//            }).allowMainThreadQueries().fallbackToDestructiveMigration().build();
//    }
//
//    public static LayananDatabase getInstance(final Context context){
//        if(sInstance==null){
//            synchronized (LayananDatabase.class){
//                if (sInstance==null){
//                    sInstance = buildDatabase(context);
//                    sInstance.updateDatabaseCreated(context.getApplicationContext());
//                }
//            }
//        }
//        return sInstance;
//    }


//    private static LayananDatabase sInstance;
//    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();
//    @VisibleForTesting
//    public static final String DATABASE_NAME = "MyAppDatabase";
//    public abstract UserDAO UserDAO();
//    private void setDatabaseCreated(){
//        mIsDatabaseCreated.postValue(true);
//    }
//    private void updateDatabaseCreated(final Context context){
//        if(context.getDatabasePath(DATABASE_NAME).exists()){
//            setDatabaseCreated();
//        }
//    }
//
//    public static AppDatabase buildDatabase(final Context context){
//        return Room.databaseBuilder(context,AppDatabase.class,DATABASE_NAME)
//                .addCallback(new RoomDatabase.Callback() {
//                    @Override
//                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
//                        super.onCreate(db);
//                        AppDatabase database = AppDatabase.getInstance(context);
//                        database.setDatabaseCreated();
//                    }
//                }).allowMainThreadQueries().fallbackToDestructiveMigration().build();
//    }
//
//    public static AppDatabase getInstance(final Context context){
//        if(sInstance==null){
//            synchronized (AppDatabase.class){
//                if (sInstance==null){
//                    sInstance = buildDatabase(context);
//                    sInstance.updateDatabaseCreated(context.getApplicationContext());
//                }
//            }
//        }
//        return sInstance;
//    }
}
