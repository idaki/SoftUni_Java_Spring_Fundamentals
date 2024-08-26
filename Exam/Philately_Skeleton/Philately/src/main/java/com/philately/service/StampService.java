package com.philately.service;

import com.philately.model.dto.AddStampDTO;
import com.philately.model.entity.Paper;
import com.philately.model.entity.Stamp;
import com.philately.model.entity.User;
import com.philately.repository.PaperRepository;
import com.philately.repository.StampRepository;
import com.philately.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StampService {
    private final StampRepository stampRepository;
    private final UserRepository userRepository;
    private final PaperRepository paperRepository;

    public StampService(StampRepository stampRepository, UserRepository userRepository, PaperRepository paperRepository) {
        this.stampRepository = stampRepository;
        this.userRepository = userRepository;
        this.paperRepository = paperRepository;
    }

    public List<Stamp> findStampsByUser(User user) {
        return stampRepository.findByOwner(user);
    }

    public List<Stamp> findOfferedStamps(User user) {
        return stampRepository.findByPurchasedIsFalseAndOwnerIdNot(user.getId());
    }

    public boolean create(AddStampDTO data, User user) {
        Stamp stamp = new Stamp();
        Paper paper = paperRepository.findByName(data.getPaper());

        if (paper == null) {
            return false;
        }

        stamp.setOwner(user);
        stamp.setName(data.getName());
        stamp.setDescription(data.getDescription());
        stamp.setPurchased(false);
        stamp.setWished(false);
        stamp.setImageUrl(data.getImageUrl());
        stamp.setPaper(paper);
        stampRepository.save(stamp);
        return true;
    }

    @Transactional
    public void addToWishlist(Long userId, long stampId) {
        Optional<User> userOpt = userRepository.findById(userId);

        if (userOpt.isEmpty()) {
            return;
        }

        Optional<Stamp> stampOpt = stampRepository.findById(stampId);
        if (stampOpt.isEmpty()) {
            return;
        }

        Stamp stamp = stampOpt.get();
        userOpt.get().addWishedStamp(stamp);
        userRepository.save(userOpt.get());
    }

    @Transactional
    public void removeFromWishlist(Long userId, long stampId) {
        Optional<User> userOpt = userRepository.findById(userId);

        if (userOpt.isEmpty()) {
            return;
        }

        Optional<Stamp> stampOpt = stampRepository.findById(stampId);
        if (stampOpt.isEmpty()) {
            return;
        }

        Stamp stamp = stampOpt.get();
        userOpt.get().removeWishedStamp(stamp);
        userRepository.save(userOpt.get());
    }

    @Transactional
    public void buyAllStamps(long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return;
        }
        User user = userOpt.get();
        List<Stamp> wishedStamps = user.getWishedStamps();
        for (Stamp stamp : wishedStamps) {
            user.purchaseStamp(stamp);
            stamp.setPurchased(true);
            stampRepository.save(stamp);
        }
        user.setWishedStamps(new ArrayList<>());
        userRepository.save(user);
    }
}
