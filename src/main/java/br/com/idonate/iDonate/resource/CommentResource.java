package br.com.idonate.iDonate.resource;

import br.com.idonate.iDonate.model.Comment;
import br.com.idonate.iDonate.service.CommentService;
import br.com.idonate.iDonate.service.exception.ProfileOrCampaignNotInformedException;
import br.com.idonate.iDonate.service.exception.RegisterNotFoundException;
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
    public ResponseEntity<Comment> edit(@PathVariable Long id, @Valid @RequestBody Comment comment) throws RegisterNotFoundException {
        Comment updateComment = commentService.edit(id, comment);
        return new ResponseEntity<>(updateComment, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> searchById(@PathVariable Long id) {
        Optional<Comment> comment = commentService.searchById(id);
        return (comment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()));
    }

    //Alterado Gustavo
    @GetMapping("/profile/{profileId}")
    public ResponseEntity<List<Comment>> searchByProfile(@PathVariable Long profileId) throws RegisterNotFoundException {
        return new ResponseEntity<>(commentService.searchByProfile(profileId), HttpStatus.OK);
    }

    //Alterado Gustavo
    @GetMapping("/campaign/{campaignId}")
    public ResponseEntity<List<Comment>> findByCampaign(@PathVariable Long campaignId) throws RegisterNotFoundException {
        return new ResponseEntity<>(commentService.searchByCampaign(campaignId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> delete(@PathVariable Long id) {
        commentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
