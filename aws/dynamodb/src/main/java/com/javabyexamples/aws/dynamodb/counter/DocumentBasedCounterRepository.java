package com.javabyexamples.aws.dynamodb.counter;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.javabyexamples.aws.dynamodb.CommonOperations;
import java.io.IOException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DocumentBasedCounterRepository implements CounterRepository {

    private static final String TABLE_NAME = "counter";

    private final DynamoDB dynamoDB;

    public DocumentBasedCounterRepository(AmazonDynamoDB amazonDynamoDB) {
        this.dynamoDB = new DynamoDB(amazonDynamoDB);
    }

    @Override
    public void increment(String hashKey) {
        final Table table = dynamoDB.getTable(TABLE_NAME);

        final UpdateItemSpec updateItemSpec = new UpdateItemSpec()
          .withPrimaryKey("key", hashKey)
          .withUpdateExpression("set #p = #p + :val")
          .withConditionExpression("attribute_exists(#p)")
          .withNameMap(new NameMap().with("#p", "count"))
          .withValueMap(new ValueMap().withNumber(":val", 1))
          .withReturnValues(ReturnValue.UPDATED_NEW);

        UpdateItemOutcome outcome = table.updateItem(updateItemSpec);

        final Integer count = Optional
          .ofNullable(outcome.getUpdateItemResult().getAttributes().get("count"))
          .map(AttributeValue::getN)
          .map(Integer::parseInt)
          .orElse(0);
        System.out.println(count);
    }

    @Override
    public void deleteItem(String hashKey) {
        final Table table = dynamoDB.getTable(TABLE_NAME);

        final DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
          .withPrimaryKey("Id", hashKey)
          .withConditionExpression("#ip = :val")
          .withNameMap(new NameMap().with("#ip", "InPublication"))
          .withValueMap(new ValueMap().withBoolean(":val", false))
          .withReturnValues(ReturnValue.ALL_OLD);

        DeleteItemOutcome outcome = table.deleteItem(deleteItemSpec);

        printResponse(outcome.getItem());
    }

    private void printResponse(Item outcome) {
        System.out.println("Printing item that was deleted...");
        System.out.println(outcome.toJSONPretty());
    }

    public static void main(String[] args) throws IOException {
        final AmazonDynamoDB amazonDynamoDB = CommonOperations.getAmazonDynamoDB();
        final DocumentBasedCounterRepository repository = new DocumentBasedCounterRepository(amazonDynamoDB);
        repository.increment("key1");
    }
}
