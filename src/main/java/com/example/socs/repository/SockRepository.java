package com.example.socs.repository;

import com.example.socs.model.Sock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SockRepository extends JpaRepository<Sock,Integer> {
    Sock findByColorAndCottonPart(String color, Integer cottonPart);

    @Query(value = "select sum(quantity) from sock where color = :color and sock.cotton_part >= :cottonPart", nativeQuery = true)
    Integer getSocksCountByColorAndMoreThanCottonPart(@Param("color") String color, @Param("cottonPart") int cottonPart);

    @Query(value = "select sum(quantity) from sock where color = :color and sock.cotton_part < :cottonPart", nativeQuery = true)
    Integer getSocksCountByColorAndLessThanCottonPart(@Param("color") String color, @Param("cottonPart") int cottonPart);

    @Query(value = "select quantity from sock where color = :color and cotton_part = :cottonPart", nativeQuery = true)
    Integer getSocksCountByColorAndEqualCottonPart(@Param("color") String color, @Param("cottonPart") int cottonPart);
}
