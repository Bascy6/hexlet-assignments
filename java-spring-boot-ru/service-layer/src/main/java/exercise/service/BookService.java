package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    public List<BookDTO> getAll() {
        var books = bookRepository.findAll();
        var dto = books.stream()
                .map(bookMapper::map)
                .toList();
        return dto;
    }

    public BookDTO getById(Long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id: " + id + " not found"));
        var dto = bookMapper.map(book);
        return dto;
    }

    public BookDTO create(BookCreateDTO bookData) {
        var book = bookMapper.map(bookData);
        bookRepository.save(book);
        var dto = bookMapper.map(book);
        return dto;
    }

    public BookDTO update(BookUpdateDTO bookData, Long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id: " + id + " not found"));
        bookMapper.update(bookData, book);
        bookRepository.save(book);
        var dto = bookMapper.map(book);
        return dto;
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
    // END
}
