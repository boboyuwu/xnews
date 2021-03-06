package com.boboyuwu.xnews.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.boboyuwu.xnews.greendao.data.ChannelNewsData;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CHANNEL_NEWS".
*/
public class ChannelNewsDao extends AbstractDao<ChannelNewsData, Long> {

    public static final String TABLENAME = "CHANNEL_NEWS";

    /**
     * Properties of entity ChannelNewsData.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ChannelName = new Property(1, String.class, "channelName", false, "CHANNEL_NAME");
        public final static Property ChannelId = new Property(2, String.class, "channelId", false, "CHANNEL_ID");
        public final static Property ChannelType = new Property(3, String.class, "channelType", false, "CHANNEL_TYPE");
        public final static Property IsFixChannel = new Property(4, boolean.class, "isFixChannel", false, "IS_FIX_CHANNEL");
        public final static Property Type = new Property(5, int.class, "type", false, "TYPE");
        public final static Property ChannelManagerType = new Property(6, int.class, "channelManagerType", false, "CHANNEL_MANAGER_TYPE");
    };


    public ChannelNewsDao(DaoConfig config) {
        super(config);
    }
    
    public ChannelNewsDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CHANNEL_NEWS\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"CHANNEL_NAME\" TEXT," + // 1: channelName
                "\"CHANNEL_ID\" TEXT," + // 2: channelId
                "\"CHANNEL_TYPE\" TEXT," + // 3: channelType
                "\"IS_FIX_CHANNEL\" INTEGER NOT NULL ," + // 4: isFixChannel
                "\"TYPE\" INTEGER NOT NULL ," + // 5: type
                "\"CHANNEL_MANAGER_TYPE\" INTEGER NOT NULL );"); // 6: channelManagerType
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CHANNEL_NEWS\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ChannelNewsData entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String channelName = entity.getChannelName();
        if (channelName != null) {
            stmt.bindString(2, channelName);
        }
 
        String channelId = entity.getChannelId();
        if (channelId != null) {
            stmt.bindString(3, channelId);
        }
 
        String channelType = entity.getChannelType();
        if (channelType != null) {
            stmt.bindString(4, channelType);
        }
        stmt.bindLong(5, entity.getIsFixChannel() ? 1L: 0L);
        stmt.bindLong(6, entity.getType());
        stmt.bindLong(7, entity.getChannelManagerType());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ChannelNewsData entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String channelName = entity.getChannelName();
        if (channelName != null) {
            stmt.bindString(2, channelName);
        }
 
        String channelId = entity.getChannelId();
        if (channelId != null) {
            stmt.bindString(3, channelId);
        }
 
        String channelType = entity.getChannelType();
        if (channelType != null) {
            stmt.bindString(4, channelType);
        }
        stmt.bindLong(5, entity.getIsFixChannel() ? 1L: 0L);
        stmt.bindLong(6, entity.getType());
        stmt.bindLong(7, entity.getChannelManagerType());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ChannelNewsData readEntity(Cursor cursor, int offset) {
        ChannelNewsData entity = new ChannelNewsData( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // channelName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // channelId
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // channelType
            cursor.getShort(offset + 4) != 0, // isFixChannel
            cursor.getInt(offset + 5), // type
            cursor.getInt(offset + 6) // channelManagerType
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ChannelNewsData entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setChannelName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setChannelId(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setChannelType(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setIsFixChannel(cursor.getShort(offset + 4) != 0);
        entity.setType(cursor.getInt(offset + 5));
        entity.setChannelManagerType(cursor.getInt(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ChannelNewsData entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ChannelNewsData entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
