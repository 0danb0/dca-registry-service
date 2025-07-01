package com.danb.dca.registry_service.models.po;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.danb.dca.registry_service.utils.ConstantStrings.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "${aws.dynamodb.table}")
public class RegistryPO {
    @DynamoDBHashKey(attributeName = PK_KEY)
    private String pk;
    @DynamoDBRangeKey(attributeName = SK_KEY)
    private String sk;
    @DynamoDBAttribute(attributeName = USER_UUID_KEY)
    private String userUuid;
    @DynamoDBAttribute(attributeName = CREATION_DATE_KEY)
    private String creationDate;
    @DynamoDBAttribute(attributeName = UPDATE_DATE_KEY)
    private String updateDate;
    @DynamoDBAttribute(attributeName = LAST_ACCESS_DATE_KEY)
    private String lastAccessDate;
    @DynamoDBAttribute(attributeName = APPLICATION_ID_KEY)
    private String applicationId;
    @DynamoDBAttribute(attributeName = EMAIL_KEY)
    private String email;
    @DynamoDBAttribute(attributeName = ACTIVE_KEY)
    private String active;
}
