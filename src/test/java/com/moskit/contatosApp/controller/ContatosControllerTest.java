package com.moskit.contatosApp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moskit.contatosApp.model.Contato;
import com.moskit.contatosApp.repository.ContatoRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ContatosController.class)
class ContatosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ContatoRepository contatoRepository;

    @Test
    void urlDeListaDeContatosAceitaGet() throws Exception {
        mockMvc
                .perform(get("/")
                .contentType("application/json"))
                .andExpect(status().isOk());

        mockMvc
                .perform(get("/list")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void urlDeListaDeContatosNaoAceitaPost() throws Exception {
        mockMvc
                .perform(post("/")
                        .contentType("application/json"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void getContatoUsaIdComoParametro() throws Exception {
        mockMvc.perform(
                get("/1")
                        .contentType("application/json"))
                .andExpect(status().isOk());

        mockMvc.perform(
                get("/bla")
                        .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void favoritarContatoPrecisaDeIdComoParametro() throws Exception {
        Optional<Contato> contato = Optional.of(new Contato());
        given(contatoRepository.findById(any())).willReturn(contato);

        mockMvc.perform(
                post("/favoritar/1")
                        .contentType("application/json"))
                .andExpect(status().isOk());

        mockMvc.perform(
                post("/favoritar/bla")
                        .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void favoritarRetorna404SeNaoAcharContato() throws Exception {
        mockMvc.perform(
                post("/favoritar/1")
                        .contentType("application/json"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createRetorna200() throws Exception {
        Contato contato = new Contato();
        contato.setNome("Rodrigo");

        mockMvc.perform(
                post("/create")
                    .contentType("application/json")
                        .content(objectMapper.writeValueAsString(contato)))
                .andExpect(status().isOk());
    }

    @Test
    void updateUsaPatchComoMetodoDeRequest() throws Exception {
        Contato contato = new Contato();
        contato.setId(1L);
        contato.setNome("Rodrigo");

        Optional<Contato> optContato = Optional.of(contato);

        given(contatoRepository.findById(any())).willReturn(optContato);
        given(contatoRepository.save(any())).willReturn(contato);

        mockMvc.perform(
                patch("/1")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(contato))
        ).andExpect(status().isOk());
    }

    @Test
    void updateRetornaNotFoundSeNaoAcharContatoParaAtualizar() throws Exception {
        Contato contato = new Contato();
        contato.setId(1L);
        contato.setNome("Rodrigo");

        mockMvc.perform(
                patch("/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(contato))
        ).andExpect(status().isNotFound());
    }

    @Test
    void updateRetornaBadRequestSeAcharContatoMasNaoConseguirAtualizar() throws Exception {
        Contato contato = new Contato();
        contato.setId(1L);
        contato.setNome("Rodrigo");

        Optional<Contato> optContato = Optional.of(contato);

        given(contatoRepository.findById(any())).willReturn(optContato);
        given(contatoRepository.save(any())).willThrow(new RuntimeException("qlqr erro"));

        mockMvc.perform(
                patch("/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(contato))
        ).andExpect(status().isBadRequest());
    }

    @Test
    void deleteRetornaNotFoundSeNaoAcharContatoParaDeletar() throws Exception {
        mockMvc.perform(
                delete("/1")
                        .contentType("application/json")
        ).andExpect(status().isNotFound());
    }

    @Test
    void deleteRetorna200() throws Exception {
        Contato contato = new Contato();
        contato.setId(1L);
        contato.setNome("Rodrigo");

        Optional<Contato> optContato = Optional.of(contato);

        given(contatoRepository.findById(any())).willReturn(optContato);

        mockMvc.perform(
                delete("/1")
                        .contentType("application/json")
        ).andExpect(status().isOk());
    }
}