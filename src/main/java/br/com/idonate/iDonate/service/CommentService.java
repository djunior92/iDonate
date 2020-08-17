package br.com.idonate.iDonate.service;

import br.com.idonate.iDonate.model.Campaign;
import br.com.idonate.iDonate.model.Comment;
import br.com.idonate.iDonate.model.Profile;
import br.com.idonate.iDonate.service.exception.ProfileOrCampaignNotInformedException;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Comment save(Comment comment) throws ProfileOrCampaignNotInformedException;
    Comment edit(Long id, Comment comment);
    void delete(Long id);
    Optional<Comment> searchById(Long id);
    List<Comment> searchByProfile(Profile profile);
    List<Comment> searchByCampaign(Campaign campaign);
}