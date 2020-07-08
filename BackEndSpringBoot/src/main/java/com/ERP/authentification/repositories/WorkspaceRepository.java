package com.ERP.authentification.repositories;

import com.ERP.authentification.Models.Board;
import com.ERP.authentification.Models.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace , Long> {
    @Query(" select b from Board b where b.id in  (select w.idboard from Workspace w where  w.idteam = ?1) ")
    public  List<Board> getboards(Long id) ;
    public  void deleteByIdteamAndIdboard(Long idteam , Long Idboard);
}
