package com.chessmates.data

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable

/**
 * Created by zwali on 13/04/2017.
 */
@DynamoDBTable(tableName="DataItem")
class DataItem {
    String id

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    public String getId(){
        return id
    }
}
