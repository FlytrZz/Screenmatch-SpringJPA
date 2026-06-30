package br.com.alura.screenmatch.service;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;

public class ConsultaChatGPT {

    private static final OpenAIClient cliente = OpenAIOkHttpClient.fromEnv();

    public static String obterTraducao(String texto) {

        ResponseCreateParams parametros = ResponseCreateParams.builder()
                .model(ChatModel.GPT_4_1)
                .input("Traduza para o português: " + texto)
                .build();

        Response response = cliente.responses().create(parametros);

        return response.output().stream()
                .filter(item -> item.isMessage())
                .map(item -> item.asMessage())
                .flatMap(message -> message.content().stream())
                .filter(content -> content.isOutputText())
                .map(content -> content.asOutputText().text())
                .findFirst()
                .orElse("Sem resposta");
    }
}