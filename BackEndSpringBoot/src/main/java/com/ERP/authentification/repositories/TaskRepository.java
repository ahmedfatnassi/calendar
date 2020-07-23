package com.ERP.authentification.repositories;

import com.ERP.authentification.Models.BoardTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TaskRepository  extends JpaRepository<BoardTask, Long> {
    public List<BoardTask>  getAllByBoardID(Long id) ;
    @Query("select  t  from BoardTask t where t.columnID in (select  b.id from BoardColumn b where b.boardId = ?1) ")
    public List<Object> getall(Long Boardid) ;
    /*@Query(value = "SELECT  * FROM BoardTask GROUP BY columnID ORDER BY CustomerName ASC", nativeQuery = true)
    public List<BoardTask> yes(Long Boardid) ;*/

    public List<BoardTask> findAllByColumnIDAndPositionBetween(Long idColumn , Long Postioninit , Long positionfinal);
    public List<BoardTask> findAllByActivitiTaskIdIn(List<String> taskActivitiIds);
    public List<BoardTask> findAllByColumnIDIn(List<Long> columnsIds);
    public  List<BoardTask> findAllByActIdIn(List<Long> actIds) ;
}
