package modulo1fase3.proyecto.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;

import modulo1fase3.proyecto.model.Cliente;
import modulo1fase3.proyecto.model.Producto;
import modulo1fase3.proyecto.services.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.Month;
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
@WebMvcTest(ProductoController.class)
class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate fecha = LocalDate.of(2023, Month.MAY,20);

    @Test
    void getProducto() throws Exception {
        given(productoService.obtenProducto(anyLong())).willReturn(Optional.of(Producto.builder().id(1L).nombre("Nombre").categoria("categoria").precio(200).numeroRegistro("1").fechaCreacion(LocalDate.now()).build()));

        mockMvc.perform(get("/producto/{productoId}", 1)
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nombre", is("Nombre")))
                .andExpect(jsonPath("$.categoria", is("categoria")))
                .andExpect(jsonPath("$.precio", is(200.0)))
                .andExpect(jsonPath("$.numeroRegistro", is("1")))
                .andExpect(jsonPath("$.fechaCreacion", is(LocalDate.now())))

                .andDo(document("producto/get-producto",
                        pathParameters(
                                parameterWithName("productoId").description("Identificador del producto")
                        ),
                        responseFields(
                                fieldWithPath("id").description("identificador del producto"),
                                fieldWithPath("nombre").description("nombre del producto"),
                                fieldWithPath("categoria").description("categoria del producto"),
                                fieldWithPath("precio").description("precio del producto"),
                                fieldWithPath("numeroRegistro").description("numero de registro del producto"),
                                fieldWithPath("fechaCreacion").description("fecha de creación del producto")
                        )));
    }

    @Test
    void getProductos() throws Exception {

        List<Producto> productos = Arrays.asList(
                Producto.builder().id(1L).nombre("Nombre 1").categoria("categoria").precio(200).numeroRegistro("1").fechaCreacion(LocalDate.now()).build(),
                Producto.builder().id(2L).nombre("Nombre 2").categoria("categoria").precio(201).numeroRegistro("2").fechaCreacion(LocalDate.now()).build(),
                Producto.builder().id(3L).nombre("Nombre 3").categoria("categoria").precio(202).numeroRegistro("3").fechaCreacion(LocalDate.now()).build()
        );

        given(productoService.obtenProductos()).willReturn(productos);

        mockMvc.perform(get("/producto")
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[0].categoria", is("categoria")))
                .andExpect(jsonPath("$[2].nombre", is("Nombre 3")))

                .andDo(document("producto/get-productos",
                        responseFields(
                                fieldWithPath("[].id").description("identificador del producto"),
                                fieldWithPath("[].nombre").description("nombre del producto"),
                                fieldWithPath("[].categoria").description("categoria del producto"),
                                fieldWithPath("[].precio").description("precio del producto"),
                                fieldWithPath("[].numeroRegistro").description("numero de registro del producto"),
                                fieldWithPath("[].fechaCreacion").description("fecha de creación del producto")
                        )));
    }

    @Test
    void creaProducto() throws Exception {
        Producto productoParametro = Producto.builder().id(1L).nombre("Nombre 1").categoria("categoria").precio(200).numeroRegistro("1").fechaCreacion(fecha).build();
        Producto productoRespuesta = Producto.builder().id(1L).nombre("Nombre 1").categoria("categoria").precio(200).numeroRegistro("1").fechaCreacion(fecha).build();

        given(productoService.guardaProducto(productoParametro)).willReturn(productoRespuesta);

        mockMvc.perform(post("/producto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(productoParametro)))
                .andExpect(status().isCreated())

                .andDo(document("producto/post-producto",
                        requestFields(
                                fieldWithPath("id").description("El identificador del nuevo producto"),
                                fieldWithPath("nombre").description("El nombre del producto"),
                                fieldWithPath("categoria").description("categoria del producto"),
                                fieldWithPath("precio").description("precio del producto"),
                                fieldWithPath("numeroRegistro").description("numero de registro del producto"),
                                fieldWithPath("fechaCreacion").description("fecha de creación del producto")
                        ),
                        responseHeaders(
                                headerWithName("Location").description("La ubicación del recurso (su identificador generado")
                        ))
                );
    }

    @Test
    void actualizaProducto() throws Exception {

        Producto productoParametro = Producto.builder().id(1L).nombre("Nombre 1").categoria("categoria").precio(200).numeroRegistro("1").fechaCreacion(LocalDate.now()).build();

        mockMvc.perform(put("/producto/{productoId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(productoParametro)))
                .andExpect(status().isNoContent())

                .andDo(document("producto/put-producto",
                        pathParameters(
                                parameterWithName("productoId").description("Identificador del producto")
                        ),
                        requestFields(
                                fieldWithPath("id").description("El identificador del nuevo producto"),
                                fieldWithPath("nombre").description("El nombre del producto"),
                                fieldWithPath("categoria").description("categoria del producto"),
                                fieldWithPath("precio").description("precio del producto"),
                                fieldWithPath("numeroRegistro").description("numero de registro del producto"),
                                fieldWithPath("fechaCreacion").description("fecha de creación del producto")
                        )
                ));
    }

    @Test
    void eliminaProducto() throws Exception {
        mockMvc.perform(delete("/producto/{productoId}", 1)
                        .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent())

                .andDo(document("producto/delete-producto",
                        pathParameters(
                                parameterWithName("productoId").description("Identificador del producto")
                        )));
    }
}