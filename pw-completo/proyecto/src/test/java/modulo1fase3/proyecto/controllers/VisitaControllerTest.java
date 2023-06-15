package modulo1fase3.proyecto.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import modulo1fase3.proyecto.model.Cliente;
import modulo1fase3.proyecto.model.Visita;
import modulo1fase3.proyecto.services.VisitaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
@WebMvcTest(VisitaController.class)
class VisitaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VisitaService visitaService;

    @Test
    void getVisita() throws Exception {
        given(visitaService.obtenVisita(anyLong())).willReturn(Optional.of(Visita.builder().id(1L).cliente(new Cliente(10L,"Nombre","correo@c.c",25,"direccion")).fechaProgramada(LocalDateTime.now()).build()));

        mockMvc.perform(get("/visita/{visitaId}", 1)
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nombre", is("Nombre")))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.correoContacto", is("correo@c.c")))
                .andExpect(jsonPath("$.numeroEmpleados", is(25)))
                .andExpect(jsonPath("$.direccion", is("direccion")))
                .andExpect(jsonPath("$.fechaProgramada", is(LocalDateTime.now())))

                .andDo(document("visita/get-visita",
                        pathParameters(
                                parameterWithName("visitaId").description("Identificador de la visita")
                        ),
                        responseFields(
                                fieldWithPath("id").description("identificador de la visita"),
                                fieldWithPath("nombre").description("nombre del cliente"),
                                fieldWithPath("correoContacto").description("correo de contacto del cliente"),
                                fieldWithPath("numeroEmpleados").description("número de trabajadores del cliente"),
                                fieldWithPath("direccion").description("domicilio del cliente")
                        )));
    }

    @Test
    void getVisitas() throws Exception {

        List<Visita> visitas = Arrays.asList(
                Visita.builder().id(1L).cliente(new Cliente(10L,"Nombre 1","correo@c.c",25,"direccion")).fechaProgramada(LocalDateTime.now()).build(),
                Visita.builder().id(2L).cliente(new Cliente(10L,"Nombre 2","correo@c.c",25,"direccion")).fechaProgramada(LocalDateTime.now()).build(),
                Visita.builder().id(3L).cliente(new Cliente(10L,"Nombre 3","correo@c.c",25,"direccion")).fechaProgramada(LocalDateTime.now()).build()
        );

        given(visitaService.obtenVisitas()).willReturn(visitas);

        mockMvc.perform(get("/visita")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[0].correoContacto", is("contacto@cliente1.com")))
                .andExpect(jsonPath("$[2].nombre", is("Nombre 3")))

                .andDo(document("cliente/get-clientes",
                        responseFields(
                                fieldWithPath("[].id").description("identificador del cliente"),
                                fieldWithPath("[].nombre").description("nombre del cliente"),
                                fieldWithPath("[].correoContacto").description("correo de contacto del cliente"),
                                fieldWithPath("[].numeroEmpleados").description("número de trabajadores del cliente"),
                                fieldWithPath("[].direccion").description("domicilio del cliente")
                        )));
    }

    @Test
    void creaVisita() throws Exception {
        Visita visitaParametro = Visita.builder().id(1L).cliente(new Cliente(10L,"Nombre 1","correo@c.c",25,"direccion")).fechaProgramada(LocalDateTime.now()).build();
        Visita visitaRespuesta = Visita.builder().id(1L).cliente(new Cliente(10L,"Nombre 1","correo@c.c",25,"direccion")).fechaProgramada(LocalDateTime.now()).build();

        given(visitaService.guardaVisita(visitaParametro)).willReturn(visitaRespuesta);

        mockMvc.perform(post("/visita")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(visitaParametro)))
                .andExpect(status().isCreated())

                .andDo(document("visita/post-visita",
                        requestFields(
                                fieldWithPath("id").description("El identificador de la visita"),
                                fieldWithPath("nombre").description("El nombre del cliente"),
                                fieldWithPath("direccion").description("La dirección del cliente"),
                                fieldWithPath("correoContacto").description("La dirección de correo electrónico de contacto"),
                                fieldWithPath("numeroEmpleados").description("El número de personas que trabajan en las oficinas e cliente"),
                                fieldWithPath("fechaProgramada").description("la fecha programada de la visita")
                        ),
                        responseHeaders(
                                headerWithName("Location").description("La ubicación del recurso (su identificador generado")
                        ))
                );
    }

    @Test
    void actualizaVisita() throws Exception {

        Visita visitaParametro = Visita.builder().id(1L).cliente(new Cliente(10L,"Nombre 1","correo@c.c",25,"direccion")).fechaProgramada(LocalDateTime.now()).build();

        mockMvc.perform(put("/visita/{visitaId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(visitaParametro)))
                .andExpect(status().isNoContent())

                .andDo(document("visita/put-visita",
                        pathParameters(
                                parameterWithName("visitaId").description("Identificador de la visita")
                        ),
                        requestFields(
                                fieldWithPath("id").description("El identificador de la visita"),
                                fieldWithPath("nombre").description("El nombre del cliente"),
                                fieldWithPath("direccion").description("La dirección del cliente"),
                                fieldWithPath("correoContacto").description("La dirección de correo electrónico de contacto"),
                                fieldWithPath("numeroEmpleados").description("El número de personas que trabajan en las oficinas e cliente"),
                                fieldWithPath("fechaProgramada").description("la fecha programada de la visita")
                        )
                ));
    }

    @Test
    void elminaVisita() throws Exception {
        mockMvc.perform(delete("/visita/{visitaId}", 1)
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent())

                .andDo(document("visita/delete-visita",
                        pathParameters(
                                parameterWithName("visitaId").description("Identificador de la visita")
                        )));
    }
}