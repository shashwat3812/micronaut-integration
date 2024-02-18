## Micronaut Kotlin App with Xero Integration

The app integrates with Xero using a Custom Connections app linked to the Demo organization.

### Xero Integration

- The app authenticates with Xero based on client_credential grant type based on
  OAuth2 ([API reference](https://developer.xero.com/documentation/guides/oauth2/custom-connections))
- Xero integration is done for ExecutiveSummary report ([API
  reference](https://developer.xero.com/documentation/api/accounting/reports/#executive-summary))

### Tech Stack

- Micronaut version - 4.3.2
- Kotlin version - 1.9.22
- JDK version - 17
- Localstack version - 3.1.0

### Application Build & Run

Run the following commands to build the application

```
    Command to install the dependencies
 1. mvn clean install
 
    Command to build the jar
 2. ./mvnw package

    Command to run the api
 3. ./mvnw mn:run

```

### LocalStack Infra Setup

Firstly, install localstack. Go through this documentation to install the localstack

https://docs.localstack.cloud/getting-started/installation/



After localstack installation, Run the following commands in the given order to setup the infrastructure of the application

```

1. localstack start

2. awslocal iam create-policy --policy-name full-access-policy --policy-document file://infra/lambda-policy.json

3. awslocal iam create-role --role-name lambda-ex --assume-role-policy-document file://infra/lambda-role.json

4. awslocal iam attach-role-policy --role-name lambda-ex --policy-arn arn:aws:iam::000000000000:policy/full-access-policy

5. awslocal lambda create-function --function-name syncReports --runtime java17 --timeout 900 --zip-file fileb://target/micronaut-integration-0.1.jar --handler com.assessment.lambda.SyncReports --role arn:aws:iam::000000000000:role/lambda-ex

6. awslocal dynamodb create-table --cli-input-json file://infra/reports-dynamo-db.json

7. awslocal sqs create-queue --cli-input-json file://infra/reports-queue.json

8. awslocal lambda create-event-source-mapping --function-name syncReports --event-source-arn arn:aws:sqs:us-east-1:000000000000:reports-queue

```

### Testing

After creating the resources on the local stack, run the following command

```
curl http://localhost:8080/reports/executive-summary
```

After running the above command, run the following command to see the save report in dynamodb table

```
awslocal dynamodb scan --table-name reports
```




