package thud.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import thud.entity.Bookable;
import thud.repository.BookableRepository;

@CrossOrigin
@RestController
public class BookableController {
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

    @PutMapping("/boolables/{id}")
    public ResponseEntity<Bookable> updateTutorial(@PathVariable("id") long id, @RequestBody Bookable bookable) {
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
