package com.wj.learnmvi.dao;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.SqlUtils;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import com.wj.learnmvi.dao.bean.ModInfo;

import com.wj.learnmvi.dao.bean.PageInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PAGE_INFO".
*/
public class PageInfoDao extends AbstractDao<PageInfo, Long> {

    public static final String TABLENAME = "PAGE_INFO";

    /**
     * Properties of entity PageInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property View_name = new Property(1, String.class, "view_name", false, "VIEW_NAME");
        public final static Property ModId = new Property(2, Long.class, "ModId", false, "MOD_ID");
    }

    private DaoSession daoSession;

    private Query<PageInfo> modInfo_PageInfoListQuery;

    public PageInfoDao(DaoConfig config) {
        super(config);
    }
    
    public PageInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PAGE_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"VIEW_NAME\" TEXT," + // 1: view_name
                "\"MOD_ID\" INTEGER);"); // 2: ModId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PAGE_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, PageInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String view_name = entity.getView_name();
        if (view_name != null) {
            stmt.bindString(2, view_name);
        }
 
        Long ModId = entity.getModId();
        if (ModId != null) {
            stmt.bindLong(3, ModId);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, PageInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String view_name = entity.getView_name();
        if (view_name != null) {
            stmt.bindString(2, view_name);
        }
 
        Long ModId = entity.getModId();
        if (ModId != null) {
            stmt.bindLong(3, ModId);
        }
    }

    @Override
    protected final void attachEntity(PageInfo entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public PageInfo readEntity(Cursor cursor, int offset) {
        PageInfo entity = new PageInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // view_name
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2) // ModId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, PageInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setView_name(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setModId(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(PageInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(PageInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(PageInfo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "pageInfoList" to-many relationship of ModInfo. */
    public List<PageInfo> _queryModInfo_PageInfoList(Long ModId) {
        synchronized (this) {
            if (modInfo_PageInfoListQuery == null) {
                QueryBuilder<PageInfo> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.ModId.eq(null));
                modInfo_PageInfoListQuery = queryBuilder.build();
            }
        }
        Query<PageInfo> query = modInfo_PageInfoListQuery.forCurrentThread();
        query.setParameter(0, ModId);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getModInfoDao().getAllColumns());
            builder.append(" FROM PAGE_INFO T");
            builder.append(" LEFT JOIN MOD_INFO T0 ON T.\"MOD_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected PageInfo loadCurrentDeep(Cursor cursor, boolean lock) {
        PageInfo entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        ModInfo modInfo = loadCurrentOther(daoSession.getModInfoDao(), cursor, offset);
        entity.setModInfo(modInfo);

        return entity;    
    }

    public PageInfo loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<PageInfo> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<PageInfo> list = new ArrayList<PageInfo>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<PageInfo> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<PageInfo> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
