package thud.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import thud.entity.Bookable;
import thud.repository.BookableRepository;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    BookableRepository bookableRepository;

    @GetMapping("/bookables")
    public ResponseEntity<List<Bookable>> getAllBookables(@RequestParam(required = false) String title) {
        try {
            List<Bookable> bookables = new ArrayList<>();

            if (title == null)
                bookableRepository.findAll().forEach(bookables::add);
            else
                bookableRepository.findByTitleContaining(title).forEach(bookables::add);

            if (bookables.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(bookables, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/bookables/{id}")
    public ResponseEntity<Bookable> getBookableById(@PathVariable("id") long id) {
        Optional<Bookable> bookableData = bookableRepository.findById(id);

        if (bookableData.isPresent()) {
            return new ResponseEntity<>(bookableData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/bookables")
    public ResponseEntity<Bookable> createBookable(@RequestBody Bookable bookable) {
        try {
            Bookable _bookable = bookableRepository
                    .save(new Bookable(bookable.getGroup(), bookable.getImg(), bookable.getTitle(),
                            bookable.getNotes()));
            return new ResponseEntity<>(_bookable, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/bookables/{id}")
    public ResponseEntity<Bookable> updateBookable(@PathVariable("id") long id, @RequestBody Bookable bookable) {
        Optional<Bookable> bookableData = bookableRepository.findById(id);

        if (bookableData.isPresent()) {
            Bookable _bookable = bookableData.get();
            _bookable.setGroup(bookable.getGroup());
            _bookable.setImg(bookable.getImg());
            _bookable.setTitle(bookable.getTitle());
            _bookable.setNotes(bookable.getNotes());
            return new ResponseEntity<>(bookableRepository.save(_bookable), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/bookables/{id}")
    public ResponseEntity<HttpStatus> deleteBookable(@PathVariable("id") long id) {
        try {
            bookableRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @DeleteMapping("/bookables")
    // public ResponseEntity<HttpStatus> deleteAllBookables() {
    // try {
    // bookableRepository.deleteAll();
    // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    // } catch (Exception e) {
    // return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    // }

    // }

    @GetMapping("/bookables/find")
    public ResponseEntity<List<Bookable>> findByTitle(@PathVariable("title") String title) {
        try {
            List<Bookable> bookables = bookableRepository.findByTitleContaining(title);

            if (bookables.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(bookables, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
