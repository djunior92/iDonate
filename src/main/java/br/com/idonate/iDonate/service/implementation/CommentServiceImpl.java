package br.com.idonate.iDonate.service.implementation;


import br.com.idonate.iDonate.core.IDonateUtils;
import br.com.idonate.iDonate.model.Campaign;
import br.com.idonate.iDonate.model.Comment;
import br.com.idonate.iDonate.model.Profile;
import br.com.idonate.iDonate.repository.CampaignRepository;
import br.com.idonate.iDonate.repository.CommentRepository;
import br.com.idonate.iDonate.repository.ProfileRepository;
import br.com.idonate.iDonate.service.CommentService;
import br.com.idonate.iDonate.service.exception.ProfileOrCampaignNotInformedException;
import br.com.idonate.iDonate.service.exception.RegisterNotFoundException;
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

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    CampaignRepository campaignRepository;

    @Override
    public Comment save(Comment comment) throws ProfileOrCampaignNotInformedException{

        if (Objects.isNull(comment.getProfile()) && Objects.isNull(comment.getCampaign())) {
            throw new ProfileOrCampaignNotInformedException("Não foi informado um Perfil e nem uma Campanha.");
        }

        comment.setDateComment(LocalDateTime.now());
        return commentRepository.save(comment);
    }


    @Override
    public Comment edit(Long id, Comment comment) throws RegisterNotFoundException {
        Comment commentEditing = commentRepository.findById(id).orElseThrow(() -> new RegisterNotFoundException("Comentário " + id + " não encontrado."));
        IDonateUtils.copyNonNullProperties(comment, commentEditing, "id", "dateComment", "profile", "author", "campaign");

        return commentRepository.save(commentEditing);
    }

    @Override
    public void delete(Long id){
        commentRepository.findById(id).ifPresentOrElse((comment) -> commentRepository.delete(comment),
                () -> { throw new EmptyResultDataAccessException(1); });
    }

    @Override
    public Optional<Comment> searchById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public List<Comment> searchByProfile(Long profileId) throws RegisterNotFoundException {
        return commentRepository.findByProfile(profileRepository.findById(profileId).orElseThrow(() -> new RegisterNotFoundException("Perfil " + profileId + " não encontrado.")));
    }

    @Override
    public List<Comment> searchByCampaign(Long campaignId) throws RegisterNotFoundException {
        return commentRepository.findByCampaign(campaignRepository.findById(campaignId).orElseThrow(() -> new RegisterNotFoundException("Campanha " + campaignId + " não encontrada.")));
    }

}
