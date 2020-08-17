package br.com.idonate.iDonate.service.implementation;


import br.com.idonate.iDonate.model.Campaign;
import br.com.idonate.iDonate.model.Comment;
import br.com.idonate.iDonate.model.Profile;
import br.com.idonate.iDonate.repository.CommentRepository;
import br.com.idonate.iDonate.service.CommentService;
import br.com.idonate.iDonate.service.exception.ProfileOrCampaignNotInformedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;


    @Override
    public Comment save(Comment comment) throws ProfileOrCampaignNotInformedException{

        if (Objects.isNull(comment.getProfile()) && Objects.isNull(comment.getCampaign())) {
            throw new ProfileOrCampaignNotInformedException("Não foi informado um Perfil e nem uma Campanha.");
        }

        comment.setDateComment(LocalDateTime.now());
        return commentRepository.save(comment);
    }


    @Override
    public Comment edit(Long id, Comment comment) {
        Optional<Comment> optionalComment = commentRepository.findById(id);

        if (!optionalComment.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }
        Comment commentSaved = optionalComment.get();
        commentSaved.setTitle(comment.getTitle());
        commentSaved.setDescription(comment.getDescription());

        return commentRepository.save(commentSaved);
    }

    @Override
    public void delete(Long id){
        commentRepository.delete(commentRepository.findById(id).get());
    }

    @Override
    public Optional<Comment> searchById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public List<Comment> searchByProfile(Profile profile) {
        return commentRepository.findByProfile(profile);
    }

    @Override
    public List<Comment> searchByCampaign(Campaign campaign) {
        return commentRepository.findByCampaign(campaign);
    }

    private Boolean commentExist(Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (!optionalComment.isPresent()) {
            return false;
        }
        return true;
    }
}
