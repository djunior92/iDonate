package br.com.idonate.iDonate.repository;

import br.com.idonate.iDonate.model.Campaign;
import br.com.idonate.iDonate.model.Comment;
import br.com.idonate.iDonate.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findById(Long id);
    List<Comment> findByProfile(Profile profile);
    List<Comment> findByCampaign(Campaign campaign);
}



