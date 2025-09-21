package com.ilianxin.quickstart;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.ai.deepseek.DeepSeekChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

@SpringBootTest
public class TestDeepseek {

    @Test
    public void testDeepseek(@Autowired DeepSeekChatModel deepSeekChatModel) {
        String content = deepSeekChatModel.call("Hello, Deepseek!");
        System.out.println(content);
    }

    @Test
    public void testDeepseekStream(@Autowired DeepSeekChatModel deepSeekChatModel) {

        Flux<String> stream = deepSeekChatModel.stream("Hello, Deepseek!");
        stream.toIterable().forEach(System.out::println);

    }

    @Test

    public void testDeepseekOnCondition(@Autowired DeepSeekChatModel deepSeekChatModel) {
        DeepSeekChatOptions options = DeepSeekChatOptions.builder()
                .temperature(1.5d)
                .build();
        ChatResponse res = deepSeekChatModel.call(new Prompt("请写一句诗描述清晨.", options));
        System.out.println(res.getResult().getOutput());

    }
}
