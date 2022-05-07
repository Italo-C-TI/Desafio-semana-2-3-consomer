package br.com.sqs_consomer;

import br.com.sqs_consomer.services.SQSService;
import br.com.sqs_consomer.services.KafkaService;
import java.util.concurrent.ExecutionException;

public class App 
{
     // para sqsSystem.getEnv(
    // public static v)oid main( String[] args ) throws InterruptedException
    // {
    //     System.out.println("Lendo mensagens ...");
    //     while(true){
    //         SQSService.messageReader();
    //     }
    // }

    // para kafka
    public static void main(String[] args) throws InterruptedException, ExecutionException{
         System.out.println("Lendo mensagens ...");
         while(true){
             KafkaService.readMessage(System.getenv("KAFKA_GROUP_ID_READER"));
         }
    }
}

