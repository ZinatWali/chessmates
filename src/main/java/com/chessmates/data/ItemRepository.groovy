package com.chessmates.data

/**
 * Created by zwali on 13/04/2017.
 */
interface ItemRepository {
    DataItem getByID(String id)
    void save(DataItem item)
    void delete(String id)
}
