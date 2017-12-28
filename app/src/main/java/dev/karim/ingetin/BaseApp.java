package dev.karim.ingetin;

import android.app.Application;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * Created by Karim on 12/27/2017.
 */

public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //kode konfirmasi Realm
        RealmConfiguration config = new RealmConfiguration.Builder()
//                .deleteRealmIfMigrationNeeded()
//                .build();
                .schemaVersion(0)
                .migration(new DataMigration())
                .build();
        Realm.setDefaultConfiguration(config);
    }

    private class DataMigration implements RealmMigration {

        @Override
        public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
            //Mengambil schema
            RealmSchema schema = realm.getSchema();

            //membuat schema baru jika versi 0
            if (oldVersion == 0) {
                schema.create("Tugas")
                        .addField("judul", String.class)
                        .addField("jenis", String.class)
                        .addField("deadline", String.class)
                        .addField("deskripsi", String.class)
                        .addField("done", String.class);
                schema.create("Organisasi")
                        .addField("judul", String.class)
                        .addField("jenis", String.class)
                        .addField("deadline", String.class)
                        .addField("deskripsi", String.class)
                        .addField("presensi", String.class)
                        .addField("notulensi", String.class)
                        .addField("done", String.class);
                oldVersion++;
            }
        }
    }
}