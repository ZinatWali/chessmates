package com.chessmatestest

import com.chessmates.data.DataItem
import com.chessmates.data.ItemRepository
import com.chessmates.data.ItemRepositoryImpl
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

@SpringBootTest
@TestPropertySource(value = "classpath:test.properties")
class ItemRepositoryIntegrationTest {
    private ItemRepository repository

    @Before
    void setup() throws Exception{
        repository = new ItemRepositoryImpl()
    }

    @Test
    void shouldDeleteSaveAndReadData(){
        repository.delete("1")
        repository.save(new DataItem(id:"1"))
        DataItem result = repository.getByID("1")
        assert result.id == "1"
    }

    @After
    void tearDown() throws Exception{
    }
}
