/*
 * Copyright 2015 Antonio López Marín <tonilopezmr.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cc.data.utils.sqlite;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Class delegated charge of implementing CRUD methods for any object model.
 * <p>
 * Author: NT
 * Since: 10/24/2016.
 */
public class SQLiteDelegate<T> implements DataAccessObject<T> {

    protected SQLiteTransformer<T> transformer;
    protected SQLiteDatabase db;

    /**
     * Constructor with two parameters, database and transformer.
     *
     * @param db          SQLiteDatabase android
     * @param transformer SQLiteTransformer
     */
    public SQLiteDelegate(SQLiteDatabase db, SQLiteTransformer<T> transformer) {
        this.transformer = transformer;
        this.db = db;
    }

    /**
     * Convenience method for inserting a row into the database.
     *
     * @param dto any object
     * @return T object with the
     * @throws android.database.sqlite.SQLiteException Error inserting
     */
    @Override
    public synchronized T create(T dto) throws Exception {
        long rowid = db.insert(transformer.getTableName(), null, transformer.transform(dto));
        Log.i(this.getClass().getName(), "ROW ID: " + rowid);
        Log.i(this.getClass().getName(), "data insert: " + dto.toString());

        if (rowid == -1)
            throw new SQLiteException("Error inserting " + dto.getClass().toString());

        return transformer.setId(dto, (int) rowid);
    }


    /**
     * Convenience method for updating rows in the database.
     *
     * @param dto object
     * @return the number of rows affected
     * @throws Exception on error
     */
    @Override
    public synchronized int update(T dto) throws Exception {
        return db.update(transformer.getTableName(), transformer.transform(dto), transformer.getWhereClause(dto), null);
    }

    /**
     * Convenience method for deleting rows in the database.
     *
     * @param dto any object
     * @return true on success, false on failed
     * @throws Exception on error
     */
    @Override
    public synchronized boolean delete(T dto) throws Exception {
        int confirm = db.delete(transformer.getTableName(), transformer.getWhereClause(dto), null);
        return confirm != 0;
    }

    /**
     * Convenience method for deleting all rows in the table of database.
     *
     * @return the number of rows affected if a whereClause is passed in, 0
     * otherwise. To remove all rows and get a count pass "1" as the
     * whereClause.
     * @throws Exception on error
     */
    @Override
    public synchronized int deleteAll() throws Exception {
        return db.delete(transformer.getTableName(), null, null);
    }


    /**
     * Convenience method for reading rows in the database.
     *
     * @param dto row in database
     * @return T object
     * @throws Exception on error
     */
    @Override
    public synchronized T read(T dto) throws Exception {
        Log.i(this.getClass().getName(), "data read: " + transformer.getWhereClause(dto));
        Cursor cursor = db.query(transformer.getTableName(), transformer.getFields(), transformer.getWhereClause(dto), null, null, null, null);
        T object = null;
        if (cursor.moveToFirst()) {
            object = transformer.transform(cursor);
        }
        closeCursor(cursor);
        return object;
    }

    @Override
    public T read(T dto, String whereClause) throws Exception {
        Log.i(this.getClass().getName(), "data read: " + transformer.getWhereClause(dto));
        Cursor cursor = db.query(transformer.getTableName(), transformer.getFields(), whereClause, null, null, null, null);
        T object = null;
        if (cursor.moveToFirst()) {
            object = transformer.transform(cursor);
        }

        closeCursor(cursor);
        return object;
    }

    /**
     * Convenience method for reading all rows in the table of database.
     *
     * @return Collection objects
     * @throws Exception on error
     */
    @Override
    public synchronized Collection<T> readAll() throws Exception {
        Cursor cursor = db.query(transformer.getTableName(), transformer.getFields(), null, null, null, null, null);
        Collection<T> list = getAllCursor(cursor);
        closeCursor(cursor);
        return list;
    }

    @Override
    public Collection<T> readAll(String whereClause) throws Exception {
        Cursor cursor = db.query(transformer.getTableName(), transformer.getFields(), whereClause, null, null, null, null);
        Collection<T> list = getAllCursor(cursor);
        closeCursor(cursor);
        return list;
    }

    private synchronized Collection<T> getAllCursor(Cursor c) throws Exception {
        Collection<T> list = new LinkedList<>();
        if (c.moveToFirst()) {
            do {
                list.add(transformer.transform(c));
            } while (c.moveToNext());
        }
        closeCursor(c);
        return list;
    }
    private void closeCursor(Cursor cursor) {
        cursor.close();
    }
}