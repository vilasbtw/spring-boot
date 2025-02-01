package com.kaique.spring.controllers;

import com.kaique.spring.data.vo.v1.BooksVO;
import com.kaique.spring.services.BooksService;
import com.kaique.spring.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books/v1")
@Tag(name = "Books", description = "Endpoints for managing books")
public class BooksController {

    @Autowired
    BooksService service;

    @PostMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(tags = {"Books"}, summary = "Adds a new book", description = "Adds a new book by JSON, XML or YAML",
    responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = BooksVO.class))),
                 @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                 @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                 @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)}
    )
    public BooksVO create(@RequestBody BooksVO bookVO) {
        return service.create(bookVO);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(tags = {"Books"}, summary = "Finds a book by its id", description = "Finds a book by its id",
    responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = BooksVO.class))),
                 @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                 @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                 @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                 @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)}
    )
    public BooksVO findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(tags = {"Books"}, summary = "Lists all books", description = "Lists all books",
    responses = {@ApiResponse(description = "Success", responseCode = "200",
        content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BooksVO.class)))}),
                 @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                 @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                 @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                 @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)}
    )
    public List<BooksVO> findAll() {
        return service.findAll();
    }

    @PutMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(tags = {"Books"}, summary = "Updates a book", description = "Updates a book information",
    responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = BooksVO.class))),
                 @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                 @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                 @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                 @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)}
    )
    public BooksVO update(@RequestBody BooksVO bookVO) {
        return service.update(bookVO);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(tags = {"Books"}, summary = "Finds a book by its id", description = "Finds a book by its id",
    responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content),
                 @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                 @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                 @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                 @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                 @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)}
    )
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}