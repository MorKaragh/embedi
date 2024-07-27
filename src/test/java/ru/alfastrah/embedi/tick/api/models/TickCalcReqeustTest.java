package ru.alfastrah.embedi.tick.api.models;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TickCalcReqeustTest {

    @Test
    public void jsonParsing() throws JsonMappingException, JsonProcessingException {
        String json = """
                        {
                          "insuredPersons": [
                            {
                              "firstName": "John",
                              "lastName": "Doe",
                              "middleName": "M",
                              "birthDate": "2000-01-01"
                            },
                            {
                              "firstName": "Jane",
                              "lastName": "Doe",
                              "middleName": "S",
                              "birthDate": "2002-03-15"
                            }
                          ],
                          "insurer": {
                            "firstName": "Company",
                            "lastName": "Inc",
                            "middleName": "olo",
                            "birthDate": "2002-03-15"
                          },
                          "address": "123 Main St",
                          "startDate": "2023-10-26T10:00:00",
                          "endDate": "2024-10-26T10:00:00"
                        }
                """;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.readValue(json, TickCalcReqeust.class);
    }
}
