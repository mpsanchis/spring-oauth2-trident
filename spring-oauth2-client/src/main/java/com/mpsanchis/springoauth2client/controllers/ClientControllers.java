package com.mpsanchis.springoauth2client.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

@Controller
public class ClientControllers {

    @Value("${oauth2-config.init-auth-path}")
    private String initAuthPath;
    @Value("${oauth2-config.resource-url}")
    private String resourceUrl;

    private final WebClient webClient;

    public ClientControllers(WebClient w) {
        this.webClient = w;
    }

    @GetMapping("/welcome")
    public String welcome(Model model, OAuth2AuthenticationToken token) {
        model.addAttribute("userName", token.getPrincipal().getName());
        return "welcome";
    }

    @PostMapping("/welcome")
    public String postWelcome(Model model, OAuth2AuthenticationToken token) {
        model.addAttribute("userName", token.getPrincipal().getName());

        String secret = this.webClient
            .get()
            .uri(resourceUrl)
            .attributes(clientRegistrationId("my-auth-server"))
            .retrieve()
            .bodyToMono(String.class)
            .block();

        model.addAttribute("secretInfo", secret);

        return "welcome";
    }

    @GetMapping("/loginpage")
    public String login(Model model) {
        model.addAttribute("initAuthPath", initAuthPath);
        return "loginpage";
    }
}
