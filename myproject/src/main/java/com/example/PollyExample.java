package com.example;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.polly.PollyClient;
import software.amazon.awssdk.services.polly.model.*;
import java.io.FileOutputStream;
import java.io.InputStream;

public class PollyExample {
    public static void main(String[] args)
    {
        // Create Polly client
        PollyClient polly = PollyClient.create();

        // Define text to  synthesize
        String text = "Hii Rashmi!";

        // Call Polly API
        SynthesizeSpeechRequest request = SynthesizeSpeechRequest.builder()
                .text(text)
                .voiceId("Aditi") // Change to "Aditi" for an Indian voice 😉
                .outputFormat(OutputFormat.MP3)
                .build();

        ResponseInputStream<SynthesizeSpeechResponse> response = polly.synthesizeSpeech(request);

        // Save the output
        try (InputStream in = response;  // No need to call response.audioStream()
             FileOutputStream out = new FileOutputStream("output.mp3")) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            System.out.println("Speech saved as output.mp3 🎵");
        } catch (Exception e) {
            e.printStackTrace();
        }


        // Close Polly client
        polly.close();
    }
}
