package br.com.sqs_consomer.services;

import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.util.List;

public class SQSService {
    public static void messageReader(){
        AwsCredentialsProvider credentialsProvider = new AwsCredentialsProvider() {
            @Override
            public AwsCredentials resolveCredentials() {
                return new AwsCredentials() {
                    @Override
                    public String accessKeyId() {
                        return System.getenv("AWS_ACCESS_KEY");
                    }
        
                    @Override
                    public String secretAccessKey() {
                        return System.getenv("AWS_SECRET_KEY");
                    }
                };
            }
        };

        SqsClient sqsClient = SqsClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(credentialsProvider)
                .build();


        GetQueueUrlRequest request = GetQueueUrlRequest.builder()
                .queueName(System.getenv("AWS_QUEUE_NAME"))

                .queueOwnerAWSAccountId(System.getenv("AWS_ACCOUNT_ID")).build();
        GetQueueUrlResponse createResult = sqsClient.getQueueUrl(request);
        
        List<Message> messages = ReceiveMessages.receiveMessages(sqsClient, createResult.queueUrl());
        for (Message mess : messages) {
            System.out.println("Mensagem: " + mess.body());
        }

        DeleteMessages.deleteMessages(sqsClient, createResult.queueUrl(),  messages);

        sqsClient.close();
    }

}