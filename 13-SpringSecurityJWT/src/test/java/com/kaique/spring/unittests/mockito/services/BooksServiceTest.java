package com.kaique.spring.unittests.mockito.services;

import com.kaique.spring.data.vo.v1.BooksVO;
import com.kaique.spring.exceptions.RequiredObjectIsNullException;
import com.kaique.spring.model.Books;
import com.kaique.spring.repositories.BooksRepository;
import com.kaique.spring.services.BooksService;
import com.kaique.spring.unittests.mapper.mocks.MockBooks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BooksServiceTest {

	MockBooks input;
	
	@InjectMocks
	private BooksService service;
	
	@Mock
	BooksRepository repository;
	
	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockBooks();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreate() {
		Books entity = input.mockEntity(1);
		
		Books persisted = entity;
		persisted.setId(1L);
		
		BooksVO vo = input.mockVO(1);
		vo.setKey(1L);
		
		when(repository.save(entity)).thenReturn(persisted);
		var result = service.create(vo);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());

		assertTrue(result.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));
		assertEquals("Book Name Test1", result.getBookName());
		assertEquals("Author Name Test1", result.getAuthorName());
		assertEquals(1, result.getNumberOfPages());
	}
	
	@Test
	void testCreateWithNullPerson() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.create(null);
		});
		
		String expectedMessage = "A null object is not allowed to persist.";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testFindById() {
		Books entity = input.mockEntity(1);
		entity.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		
		var result = service.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/books/v1>;rel=\"List of all books\"]"));
		assertEquals("Book Name Test1", result.getBookName());
		assertEquals("Author Name Test1", result.getAuthorName());
		assertEquals(1, result.getNumberOfPages());
	}

	@Test
	void testFindAll() {
		List<Books> entities = input.mockEntityList();
		
		when(repository.findAll()).thenReturn(entities);
		
		var books = service.findAll();
		
		assertNotNull(books);
		assertEquals(14, books.size());
		
		var books1 = books.get(1);
		assertNotNull(books1);
		assertNotNull(books1.getKey());
		assertNotNull(books1.getLinks());
		assertTrue(books1.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));
		assertEquals("Book Name Test1", books1.getBookName());
		assertEquals("Author Name Test1", books1.getAuthorName());
		assertEquals(1, books1.getNumberOfPages());
		
		var books4 = books.get(4);
		assertNotNull(books4);
		assertNotNull(books4.getKey());
		assertNotNull(books4.getLinks());
		assertTrue(books4.toString().contains("links: [</api/books/v1/4>;rel=\"self\"]"));
		assertEquals("Book Name Test4", books4.getBookName());
		assertEquals("Author Name Test4", books4.getAuthorName());
		assertEquals(4, books4.getNumberOfPages());

		var books7 = books.get(7);
		assertNotNull(books7);
		assertNotNull(books7.getKey());
		assertNotNull(books7.getLinks());
		assertTrue(books7.toString().contains("links: [</api/books/v1/7>;rel=\"self\"]"));
		assertEquals("Book Name Test7", books7.getBookName());
		assertEquals("Author Name Test7", books7.getAuthorName());
		assertEquals(7, books7.getNumberOfPages());
	}

	@Test
	void testUpdate() {
		Books entity = input.mockEntity(1);
		entity.setId(1L);

		Books persisted = entity;
		persisted.setId(1L);
		
		BooksVO vo = input.mockVO(1);
		vo.setKey(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);
		
		var result = service.update(vo);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());

		assertTrue(result.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));
		assertEquals("Book Name Test1", result.getBookName());
		assertEquals("Author Name Test1", result.getAuthorName());
		assertEquals(1, result.getNumberOfPages());
	}

	@Test
	void updateCreateWithNullBook() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.create(null);
		});
		
		String expectedMessage = "A null object is not allowed to persist.";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testDelete() {
		Books entity = input.mockEntity(1);
		entity.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		
	    service.delete(1L);
	}
}