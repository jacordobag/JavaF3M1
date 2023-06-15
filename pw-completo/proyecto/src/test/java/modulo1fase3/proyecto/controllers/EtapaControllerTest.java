package modulo1fase3.proyecto.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import modulo1fase3.proyecto.model.Cliente;
import modulo1fase3.proyecto.model.Etapa;
import modulo1fase3.proyecto.services.ClienteService;
import modulo1fase3.proyecto.services.EtapaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@AutoConfigureRestDocs
@WebMvcTest(EtapaController.class)
class EtapaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EtapaService etapaService;

    @Test
    void getEtapa() throws Exception {
        given(etapaService.obtenEtapa(anyLong())).willReturn(Optional.of(Etapa.builder().etapaId(1L).nombre("Nombre").orden(0).build()));

        mockMvc.perform(get("/etapa/{etapaId}", 1)
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.orden", is(0)))
                .andExpect(jsonPath("$.nombre", is("Nombre")))

                .andDo(document("etapa/get-etapa",
                        pathParameters(
                                parameterWithName("etapaId").description("Identificador de la etapa")
                        ),
                        responseFields(
                                fieldWithPath("id").description("identificador de la etapa"),
                                fieldWithPath("nombre").description("nombre de la etapa"),
                                fieldWithPath("orden").description("orden de la etapa")
                        )));
    }

    @Test
    void getEtapas() throws Exception {

        List<Etapa> etapas = Arrays.asList(
                Etapa.builder().etapaId(1L).nombre("Nombre 1").orden(0).build(),
                Etapa.builder().etapaId(2L).nombre("Nombre 2").orden(1).build(),
                Etapa.builder().etapaId(3L).nombre("Nombre 3").orden(2).build()
        );

        given(etapaService.obtenEtapas()).willReturn(etapas);

        mockMvc.perform(get("/etapa")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[0].orden", is(0)))
                .andExpect(jsonPath("$[2].nombre", is("Nombre 3")))

                .andDo(document("etapa/get-etapas",
                        responseFields(
                                fieldWithPath("[].id").description("identificador de la etapa"),
                                fieldWithPath("[].nombre").description("nombre de la etapa"),
                                fieldWithPath("[].orden").description("orden de la etapa")
                        )));
    }

    @Test
    void creaEtapa() throws Exception {
        Etapa etapaParametro = Etapa.builder().nombre("Nombre").orden(0).build();
        Etapa etapaRespuesta = Etapa.builder().etapaId(1L).nombre("Nombre").orden(0).build();

        given(etapaService.guardaEtapa(etapaParametro)).willReturn(etapaRespuesta);

        mockMvc.perform(post("/etapa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(etapaParametro)))
                .andExpect(status().isCreated())

                .andDo(document("etapa/post-etapa",
                        requestFields(
                                fieldWithPath("id").description("El identificador de la nueva etapa"),
                                fieldWithPath("nombre").description("El nombre de la etapa"),
                                fieldWithPath("orden").description("el orden de la etapa")
                        ),
                        responseHeaders(
                                headerWithName("Location").description("La ubicación del recurso (su identificador generado")
                        ))
                );
    }

    @Test
    void actualizaEtapa() throws Exception {

        Etapa etapaParametro = Etapa.builder().etapaId(1L).nombre("Nombre").orden(1).build();

        mockMvc.perform(put("/etapa/{etapaId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(etapaParametro)))
                .andExpect(status().isNoContent())

                .andDo(document("etapa/put-etapa",
                        pathParameters(
                                parameterWithName("etapaId").description("Identificador de la etapa")
                        ),
                        requestFields(
                                fieldWithPath("id").description("El identificador de la nueva etapa"),
                                fieldWithPath("nombre").description("El nombre de la etapa"),
                                fieldWithPath("orden").description("el orden de la etapa")
                        )
                ));
    }

    @Test
    void eliminaEtapa() throws Exception {
        mockMvc.perform(delete("/etapa/{etapaId}", 1)
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent())

                .andDo(document("etapa/delete-etapa",
                        pathParameters(
                                parameterWithName("etapaId").description("Identificador de la etapa")
                        )));
    }
}