package com.capgemini.repositories;

import java.util.List;

import com.capgemini.Trophy;

public interface TrophyRepository {
    Trophy save(Trophy trophy);
    List<Trophy> findByUserId(Long userId);
}