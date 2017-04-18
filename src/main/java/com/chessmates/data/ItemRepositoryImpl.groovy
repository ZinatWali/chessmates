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
    private AmazonDynamoDB amazonDynamoDB

    @Autowired
    ItemRepositoryImpl(AmazonDynamoDB amazonDynamoDB){
       this.amazonDynamoDB = amazonDynamoDB
    }

    @Override
    DataItem getByID(String id) {
        Item item = new DynamoDB(amazonDynamoDB).getTable("DataItem").getItem("id", id)
        return new DataItem(id: item.get("id"))
    }

    @Override
    void save(DataItem item) {
        new DynamoDB(amazonDynamoDB).getTable("DataItem").putItem(new Item().with("id", item.id))
    }

    @Override
    void delete(String id) {
        new DynamoDB(amazonDynamoDB).getTable("DataItem").deleteItem("id", id)
    }
}