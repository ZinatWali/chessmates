package com.chessmates.data

import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.document.Item
import com.amazonaws.services.dynamodbv2.document.Table
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import org.springframework.util.StringUtils

/**
 * Repository for CRUD operations
 * talking to DynamoDB in AWS
 */
@Repository
class ItemRepositoryImpl implements ItemRepository {
    private Table table

    @Value('${amazon.dynamodb.endpoint}')
    String amazonDynamoDBEndpoint

    @Value('${amazon.aws.accesskey}')
    String amazonAWSAccessKey

    @Value('${amazon.aws.secretkey}')
    String amazonAWSSecretKey

    ItemRepositoryImpl(){
        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey))
        if (StringUtils.isEmpty(amazonDynamoDBEndpoint)) {
            throw new Exception("No endpoint found for database")
        }
        else{
            amazonDynamoDB.setEndpoint(amazonDynamoDBEndpoint)
        }

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