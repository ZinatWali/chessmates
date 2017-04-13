package com.chessmates.data

interface ItemRepository {
    DataItem getByID(String id)
    void save(DataItem item)
    void delete(String id)
}
