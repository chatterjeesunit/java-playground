package com.play.util.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.math.BigDecimal;

public class JsonUpdate {

    final static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {

        final String json =
                "{\n" +
                        "    \"id\": 1,\n" +
                        "    \"name\": \"Leanne Graham\",\n" +
                        "    \"username\": \"Bret\",\n" +
                        "    \"email\": \"Sincere@april.biz\",\n" +
                        "    \"address\": {\n" +
                        "      \"street\": \"Kulas Light\",\n" +
                        "      \"suite\": \"Apt. 556\",\n" +
                        "      \"city\": \"Gwenborough\",\n" +
                        "      \"zipcode\": \"92998-3874\",\n" +
                        "      \"geo\": {\n" +
                        "        \"lat\": \"-37.3159\",\n" +
                        "        \"lng\": \"81.1496\"\n" +
                        "      }\n" +
                        "    },\n" +
                        "    \"phone\": \"1-770-736-8031 x56442\",\n" +
                        "    \"website\": \"hildegard.org\",\n" +
                        "    \"company\": {\n" +
                        "      \"name\": \"Romaguera-Crona\",\n" +
                        "      \"catchPhrase\": \"Multi-layered client-server neural-net\",\n" +
                        "      \"bs\": \"harness real-time e-markets\"\n" +
                        "    }\n" +
                        "  }";


        String updatedJson = updateZipCode(json, "94065");

        updatedJson = updateLatitude(updatedJson, BigDecimal.valueOf(-55.55));
        System.out.println(updatedJson);

    }


    private static String updateZipCode(String json, String newValue) throws IOException {
        ObjectNode objectNode = (ObjectNode)mapper.readTree(json);
        final ObjectNode address = (ObjectNode)objectNode.get("address");
        address.put("zipcode", newValue);

        return objectNode.toString();
    }

    private static String updateLatitude(String json, BigDecimal value) throws IOException {
        ObjectNode objectNode = (ObjectNode) mapper.readTree(json);
        final ObjectNode node = (ObjectNode) objectNode.get("address").get("geo");
        node.put("lat", value);

        return objectNode.toString();
    }
}
