package com.itm.legbook.repository;

import com.itm.legbook.dto.MessageResponse;
import com.itm.legbook.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Message,Long>
{

    //@Query(nativeQuery = true,value = "call getChats(:senderId,:recipentId,:skip,:take)")   // call store procedure with arguments
    //List<Message> getChats(@Param("sender") Long senderId,@Param("recipent") Long recipentId,@Param("skipVal") int skip,@Param("takeVal") int take);


    @Query(value = "select * from message m where m.sender_id in (:senderId,:recipentId) and m.recipent_id in (:senderId,:recipentId)" ,nativeQuery = true)
    List<Message> getChats(Long senderId, Long recipentId);

}
