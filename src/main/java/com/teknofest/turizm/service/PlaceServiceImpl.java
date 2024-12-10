package com.teknofest.turizm.service;

import com.teknofest.turizm.exception.ResourceNotFoundException;
import com.teknofest.turizm.model.Place;
import com.teknofest.turizm.repository.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;

    public PlaceServiceImpl(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public Place savePlace(Place place) {
        return placeRepository.save(place);
    }

    @Override
    public Place getPlaceById(Long id) {
        return placeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Kayıt bulunamadı."));
    }

    @Override
    public Place updatePlace(Long id, Place uPlace) {
        Place dbPlace = placeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID:" + id + " ile eşleşen kayıt bulunamadı."));
        {
            dbPlace.setName(uPlace.getName());
            dbPlace.setDescription(uPlace.getDescription());
            dbPlace.setAddress(uPlace.getAddress());
            dbPlace.setLatitude(uPlace.getLatitude());
            dbPlace.setLongitude(uPlace.getLongitude());

            return placeRepository.save(dbPlace);
        }
    }

    @Override
    public void deletePlace(Long id) {
        Optional<Place> placeDb = placeRepository.findById(id);
        if (placeDb.isPresent()) {
            placeRepository.delete(placeDb.get());
        } else {
            throw new ResourceNotFoundException("ID:" + id + " ile eşleşen kayıt bulunamadı.");
        }
    }

    @Override
    public List<Place> searchByCityOrRegionOrAddress(String query) {
        return placeRepository.searchByCityOrRegionOrAddress(query);
    }

    @Override
    public List<Place> findAllApprovedPlaces() {
       return placeRepository.findAllApprovedPlaces();
    }
}
