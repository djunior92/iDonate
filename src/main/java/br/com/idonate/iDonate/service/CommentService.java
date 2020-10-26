package br.com.idonate.iDonate.service;

import br.com.idonate.iDonate.model.Comment;
import br.com.idonate.iDonate.service.exception.ProfileOrCampaignNotInformedException;
import br.com.idonate.iDonate.service.exception.RegisterNotFoundException;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Comment save(Comment comment) throws ProfileOrCampaignNotInformedException;
    Comment edit(Long id, Comment comment) throws RegisterNotFoundException;
    void delete(Long id);
    Optional<Comment> searchById(Long id);
    List<Comment> searchByProfile(Long profileId) throws RegisterNotFoundException;
    List<Comment> searchByCampaign(Long campaignId) throws RegisterNotFoundException;

}