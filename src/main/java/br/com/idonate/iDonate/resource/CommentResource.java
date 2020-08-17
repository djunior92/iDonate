package br.com.idonate.iDonate.resource;

import br.com.idonate.iDonate.model.Campaign;
import br.com.idonate.iDonate.model.Comment;
import br.com.idonate.iDonate.model.Profile;
import br.com.idonate.iDonate.service.CommentService;
import br.com.idonate.iDonate.service.exception.ProfileOrCampaignNotInformedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/comment")
public class CommentResource {

    @Autowired
    CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> save(@Valid @RequestBody Comment comment)throws ProfileOrCampaignNotInformedException {
        Comment savedComment = commentService.save(comment);
        return new ResponseEntity<>(savedComment, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> edit(@PathVariable Long id, @Valid @RequestBody Comment comment) {
        Comment updateComment = commentService.edit(id, comment);
        return new ResponseEntity<>(updateComment, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> searchById(@PathVariable Long id) {
        Optional<Comment> comment = commentService.searchById(id);
        return (comment.isPresent() ? ResponseEntity.ok(comment.get()) : ResponseEntity.notFound().build());
    }

    @GetMapping("/profile")
    public ResponseEntity<List<Comment>> searchByProfile(@RequestBody Profile profile) {
        return new ResponseEntity<>(commentService.searchByProfile(profile), HttpStatus.OK);
    }

    @GetMapping("/campaign")
    public ResponseEntity<List<Comment>> findByCampaign(@RequestBody Campaign campaign) {
        return new ResponseEntity<>(commentService.searchByCampaign(campaign), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> delete(@PathVariable Long id) {
        commentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
