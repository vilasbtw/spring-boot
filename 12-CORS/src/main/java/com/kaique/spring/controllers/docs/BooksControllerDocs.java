package com.kaique.spring.controllers.docs;

import com.kaique.spring.data.vo.v1.BooksVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BooksControllerDocs {

    @Operation(tags = {"Books"}, summary = "Adds a new book", description = "Adds a new book by JSON, XML or YAML",
        responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = BooksVO.class))),
                     @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                     @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                     @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)}
        )
    BooksVO create(@RequestBody BooksVO bookVO);

    @Operation(tags = {"Books"}, summary = "Finds a book by its id", description = "Finds a book by its id",
    responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = BooksVO.class))),
                 @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                 @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                 @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                 @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)}
    )
    BooksVO findById(@PathVariable(value = "id") Long id);

    @Operation(tags = {"Books"}, summary = "Lists all books", description = "Lists all books",
    responses = {@ApiResponse(description = "Success", responseCode = "200",
        content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BooksVO.class)))}),
                 @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                 @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                 @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                 @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)}
    )
    List<BooksVO> findAll();

    @Operation(tags = {"Books"}, summary = "Updates a book", description = "Updates a book information",
        responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = BooksVO.class))),
                     @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                     @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                     @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                     @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)}
        )
    BooksVO update(@RequestBody BooksVO bookVO);

    @Operation(tags = {"Books"}, summary = "Finds a book by its id", description = "Finds a book by its id",
    responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content),
                 @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                 @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                 @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                 @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                 @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)}
    )
    ResponseEntity<?> delete(@PathVariable(name = "id") Long id);
}
