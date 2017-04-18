package com.chessmatestest

import com.chessmates.Application
import com.chessmates.data.DataItem
import com.chessmates.data.ItemRepository
import com.chessmates.data.ItemRepositoryImpl
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)
class ItemRepositoryIntegrationTest {
    @Autowired
    private ItemRepository repository

    @Before
    void setup() throws Exception {
    }

    @Test
    void shouldDeleteSaveAndReadData() {
        repository.delete("1")
        repository.save(new DataItem(id: "1"))
        DataItem result = repository.getByID("1")
        assert result.id == "1"
    }

    @After
    void tearDown() throws Exception {
    }
}
