package com.javabyexamples.aws.dynamodb.simple;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

public class DynamoDbRepository {

    private static final String TABLE_NAME = "ProductCatalog";
    private final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
    private final DynamoDB dynamoDB = new DynamoDB(client);

    public void createItems() {
        final Table table = dynamoDB.getTable(TABLE_NAME);
        Item item = new Item().withPrimaryKey("Id", 120)
          .withString("Title", "Book 120 Title")
          .withString("ISBN", "120-1111111111")
          .withStringSet("Authors", new HashSet<>(Arrays.asList("Author12", "Author22")))
          .withNumber("Price", 20)
          .withString("Dimensions", "8.5x11.0x.75")
          .withNumber("PageCount", 500)
          .withBoolean("InPublication", false)
          .withString("ProductCategory", "Book");
        table.putItem(item);

        item = new Item().withPrimaryKey("Id", 121)
          .withString("Title", "Book 121 Title")
          .withString("ISBN", "121-1111111111")
          .withStringSet("Authors", new HashSet<>(Arrays.asList("Author21", "Author 22")))
          .withNumber("Price", 20)
          .withString("Dimensions", "8.5x11.0x.75")
          .withNumber("PageCount", 500)
          .withBoolean("InPublication", true)
          .withString("ProductCategory", "Book");
        table.putItem(item);
    }

    public void retrieveItem() {
        final Table table = dynamoDB.getTable(TABLE_NAME);
        Item item = table.getItem("Id", 120, "Id, ISBN, Title, Authors", null);

        System.out.println("Printing item after retrieving it....");
        System.out.println(item.toJSONPretty());
    }

    public void updateAddNewAttribute() {
        final Table table = dynamoDB.getTable(TABLE_NAME);
        UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("Id", 121)
          .withUpdateExpression("set #na = :val1")
          .withNameMap(new NameMap().with("#na", "NewAttribute"))
          .withValueMap(new ValueMap().withString(":val1", "Some value"))
          .withReturnValues(ReturnValue.ALL_NEW);
        UpdateItemOutcome outcome = table.updateItem(updateItemSpec);

        // Check the response.
        System.out.println("Printing item after adding new attribute...");
        System.out.println(outcome.getItem().toJSONPretty());
    }

    public void updateMultipleAttributes() {
        final Table table = dynamoDB.getTable(TABLE_NAME);
        UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("Id", 120)
          .withUpdateExpression("add #a :val1 set #na=:val2")
          .withNameMap(new NameMap().with("#a", "Authors").with("#na", "NewAttribute"))
          .withValueMap(new ValueMap()
            .withStringSet(":val1", "Author YY", "Author ZZ")
            .withString(":val2", "someValue"))
          .withReturnValues(ReturnValue.ALL_NEW);

        UpdateItemOutcome outcome = table.updateItem(updateItemSpec);

        // Check the response.
        System.out.println("Printing item after multiple attribute update...");
        System.out.println(outcome.getItem().toJSONPretty());
    }

    public void updateExistingAttributeConditionally() {
        final Table table = dynamoDB.getTable(TABLE_NAME);
        UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("Id", 120)
          .withReturnValues(ReturnValue.ALL_NEW)
          .withUpdateExpression("set #p = :val1")
          .withConditionExpression("#p = :val2")
          .withNameMap(new NameMap().with("#p", "Price"))
          .withValueMap(new ValueMap().withNumber(":val1", 25).withNumber(":val2", 20));

        UpdateItemOutcome outcome = table.updateItem(updateItemSpec);

        // Check the response.
        System.out.println("Printing item after conditional update to new attribute...");
        System.out.println(outcome.getItem().toJSONPretty());
    }

    public void deleteItem() {
        final Table table = dynamoDB.getTable(TABLE_NAME);
        DeleteItemSpec deleteItemSpec = new DeleteItemSpec().withPrimaryKey("Id", 120)
          .withConditionExpression("#ip = :val")
          .withNameMap(new NameMap().with("#ip", "InPublication"))
          .withValueMap(new ValueMap().withBoolean(":val", false)).withReturnValues(
            ReturnValue.ALL_OLD);

        DeleteItemOutcome outcome = table.deleteItem(deleteItemSpec);

        // Check the response.
        System.out.println("Printing item that was deleted...");
        System.out.println(outcome.getItem().toJSONPretty());
    }

    public static void main(String[] args) throws IOException {

        final DynamoDbRepository repository = new DynamoDbRepository();
        repository.createItems();
        repository.retrieveItem();

//    // Perform various updates.
//    repository.updateMultipleAttributes();
//    repository.updateAddNewAttribute();
//    repository.updateExistingAttributeConditionally();

        // Delete the item.
        repository.deleteItem();
    }
}
