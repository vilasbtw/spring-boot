package com.kaique.spring.controllers;

import com.kaique.spring.data.vo.v1.PersonVO;
import com.kaique.spring.services.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.kaique.spring.util.MediaType;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for managing people")
public class PersonController {

    @Autowired
    private PersonService service;
    // private PersonService service = new PersonService();

    @PostMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    // @RequestBody gets data from the body
    // Data may be inserted with Postman
    @Operation(summary = "Adds a new person", description = "Adds a new person by JSON, XML or YAML", tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public PersonVO create(@RequestBody PersonVO person) {
        return service.create(person);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Finds a person", description = "Finds a person", tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public PersonVO findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Finds everyone", description = "Finds everyone", tags = {"People"},
               responses = {
                       @ApiResponse(description = "Success", responseCode = "200",
                                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PersonVO.class)))}),
                       @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                       @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                       @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                       @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
               }
              )
    public List<PersonVO> findAll() {

        return service.findAll();
    }

    @PutMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Updates a person", description = "Updates a person info", tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public PersonVO update(@RequestBody PersonVO person) {
        return service.update(person);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a person", description = "Deletes a person info", tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
        // Returns the appropriated status code for deletions (204 NO_CONTENT)
    }
}