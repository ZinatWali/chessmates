package com.chessmates.data

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.document.Item
import com.amazonaws.services.dynamodbv2.document.Table
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

import javax.annotation.PostConstruct

/**
 * Repository for CRUD operations
 * talking to DynamoDB in AWS
 */
@Repository
class ItemRepositoryImpl implements ItemRepository {
    private Table table

    @Autowired
    AmazonDynamoDB amazonDynamoDB

    ItemRepositoryImpl(){
       table = new DynamoDB(amazonDynamoDB).getTable("DataItem")
    }

    @Override
    DataItem getByID(String id) {
        Item item = table.getItem("id", id)
        return new DataItem(id: item.get("id"))
    }

    @Override
    void save(DataItem item) {
        table.putItem(new Item().with("id", item.id))
    }

    @Override
    void delete(String id) {
        table.deleteItem("id", id)
    }
}