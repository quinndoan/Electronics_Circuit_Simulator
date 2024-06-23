package source_fxml;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ChatBotController {

    @FXML
    private TextArea chatArea;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    @FXML
    private void sendMessage() {
        String userMessage = userInput.getText();
        if (userMessage.isEmpty()) {
            return;
        }

        chatArea.appendText("Bạn: " + userMessage + "\n");
        userInput.clear();

        // Gọi API OpenAI
        String response = chatGPT(userMessage);
        chatArea.appendText("Chatbot: " + response + "\n");
    }

    public static String chatGPT(String message) {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = "YOUR_API_KEY";
        String model = "gpt-3.5-turbo";
        String fixString = "Remember that you are AI Assisstant for Electrical Circuit Simulator Group 7 of the Object-Oriented Programming in Bach Khoa University. You only answer the question relate Electrical problems. Below is the question: ";
        String promptFinal = fixString + message;
        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");

            // The request body
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \""
                    + promptFinal + "\"}]}";
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            // Response from ChatGPT
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            StringBuffer response = new StringBuffer();

            while ((line = br.readLine()) != null) {

                response.append(line);
            }
            br.close();

            // calls the method to extract the message.
            return extractMessageFromJSONResponse(response.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String extractMessageFromJSONResponse(String response) {
        int start = response.indexOf("content") + 11;
        int end = response.indexOf("\"", start);
        String message = response.substring(start, end);

        // Convert escaped newlines to actual newlines
        return message.replace("\\n", "\n");
    }

}